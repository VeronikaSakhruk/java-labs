# Peer Review: Lab 14, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab14`, diagrams `docs/diagrams/lab14_*`, runner output of `parcelplus.lab14.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?
4. Does the log show who initiated the event and who reacted to it?

## Task 1. Shipment status tracking (Observer)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `Shipment` (subject) holds `0..*` `StatusObserver`, five concrete observers, the `ShipmentStatus` enum. |
| Problem removed | Yes | `Shipment.changeStatus()` knows only the `StatusObserver` interface. A new subscriber (for example another store API) is one new class and needs no change in the shipment. Subscribers join and leave at runtime. Each observer decides how to react, `AccountingDepartment` reacts only to `DELIVERED`. |
| Runner reproduces scenario | Yes | Sender, receiver and accounting subscribed, status `IN_TRANSIT` gives a reaction of each (accounting logs that it ignored the status). Online cabinet and online store subscribed, status `DELIVERED` is handled by all five, accounting records the cash on delivery payment. A receiver unsubscribes and the next status change is delivered only to the remaining subscriber. |
| Log shows initiator and reactor | Yes | `Shipment PS-xxxx` logs the change and the number of subscribers, every observer logs its own reaction under its own name. |

Findings:

- Fixed, medium: an exception in one observer (for example a store API failure) would stop the loop and the remaining subscribers would not be notified. `changeStatus()` now catches a runtime exception per observer, logs it and continues.
- Deviation from the literal scenario, minor: the unsubscribe step is shown on a second shipment `PS-7002`. On `PS-7001` the status is already `DELIVERED`, and no status follows it, so a further change on the same shipment would look unrealistic. The behavior shown is the same.
- Minor, not fixed: `Shipment` stores the current status but has no getter, because no scenario needs to read it.
- Design decision: the subscriber list is a `CopyOnWriteArrayList`, so subscribing or unsubscribing during a notification is safe.

## Task 2. Sorting center (Mediator)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `SortingMediator` interface, `SortingCenterController` as the concrete mediator, devices as colleagues. Scanner, scale and loading zone hold the mediator, the controller holds belt, sorter and loading zone. |
| Problem removed | Yes | Devices never call each other. They report events to the mediator, and all rules are in `SortingCenterController`: routing, the 50 kg overload limit and loading only with a docked truck. Replacing the scanner means writing a new class that reports the same event, no other device changes. |
| Runner reproduces scenario | Yes | Scanner, coordinator, sorter directs `PS-8001` to Khotyn. The scale reports 58 kg, the coordinator stops the belt. Loading is denied while there is no truck, after the truck arrives the same request is allowed and loading starts. |
| Log shows initiator and reactor | Yes | The device logs what it reports, the coordinator logs the event and its decision, the target device logs the action. |

Findings:

- Fixed, medium: the mediator keeps the docked trucks in a plain `HashMap`, and devices report from different threads. Its event methods are now `synchronized`.
- Minor, not fixed: a docked truck stays registered after loading, departure is not modeled because the scenario does not need it.
- Minor, not fixed: `connect()` must be called before the first event, otherwise the controller would fail with a `NullPointerException`. This is needed to resolve the circular reference between the mediator and the loading zone.
- Design decision: the scanner and the scale are only event sources, so the mediator does not hold references to them.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`.
- No comments, UI, database or third-party libraries. Code of labs 9 to 13 is unchanged.
- Each of the two patterns is used exactly once.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the other |
|---|---|---|
| Status tracking: one object changes, an open set of subscribers must react, joining and leaving at runtime | Observer | One-to-many notification with the subject unaware of concrete subscribers. Mediator would centralize rules, but here there are no rules between subscribers. |
| Sorting center: many devices interact with each other, rules must be in one place | Mediator | Many-to-many direct calls are replaced by communication through one coordinator that owns the rules. Observer is one-to-many and has no place for coordination rules. |
