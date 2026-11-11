# Peer Review: Lab 12, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab12`, diagrams `docs/diagrams/lab12_*`, runner output of `parcelplus.lab12.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?

## Task 1. Shipments on the map (Flyweight)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `ShipmentTypeFactory` (cache), `ShipmentType` (flyweight with a package-private constructor), `ShipmentMarker` (context with own data and a reference to the shared type). |
| Problem removed | Yes | Icon, color and label exist once per type. A marker stores only waybill, coordinates, status and a reference. Asking the factory for a known type returns the cached object and logs nothing. |
| Runner reproduces scenario | Yes | 20 000 markers with random types (fixed seed), the log shows 5 type objects created, then two FRAGILE markers with the same type object (`==` is true, identity hash codes are equal). |

Findings:

- Fixed, medium: the factory cache is a plain `HashMap`. Several map-loading threads could create two objects for one type. `get()` and `getCreatedCount()` are now `synchronized`.
- Minor, not fixed: the saving is shown by object counts only, memory is not measured, as the assignment asks only for counts.
- Design decision: `ShipmentType` has a package-private constructor, so the only way to get a type is the factory.

## Task 2. Client notifications (Bridge)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Abstraction `Notification` holds a `Channel`, three refined notifications, four concrete channels. |
| Problem removed | Yes | Kinds and channels are two independent hierarchies. Any kind works with any channel, chosen at construction time. Three kinds and four channels need 7 classes instead of 12 combinations. |
| Runner reproduces scenario | Yes | Arrival through SMS and e-mail, delay through Viber, then Telegram added as one new class and used for the payment notification. The log shows both the notification and the channel as separate sources. |

Findings:

- Minor, not fixed: the channel is fixed in the constructor, a notification cannot switch the channel later. The situation only requires choosing it at runtime, not changing it afterwards.
- Note: all four channels differ in the log output, so different delivery looks are visible.

## Task 3. Cargo consolidation (Composite)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `CargoItem` component, `Parcel` leaf, `CargoGroup` composite with `0..*` children of type `CargoItem`. |
| Problem removed | Yes | Weight, declared value and shipment count are recursive sums in `CargoGroup`. There are no loops per level. A bag inside a bag works with no changes. |
| Runner reproduces scenario | Yes | Trip, 2 pallets, bags (including bag B-2 inside bag B-1), parcels. The tree is printed with totals for every node, then the same `report()` call is used for the trip (25.90 kg, 10 500.00 UAH, 8 shipments) and for one pallet. The totals were checked by hand. |

Findings:

- Minor, not fixed: nothing prevents a group from being added to itself or to its own descendant, which would cause infinite recursion. The scenario does not require protection.
- Design decision: bag, pallet and trip are one class `CargoGroup` with a `kind` field. Their behavior is identical, so three classes would only add duplication.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`.
- Only the object counts for the large collection (Task 1) are printed, as required.
- No comments, UI, database or third-party libraries. Code of labs 9 to 11 is unchanged.
- Each of the three patterns is used exactly once.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the others |
|---|---|---|
| Map markers: tens of thousands of objects with identical type data | Flyweight | The problem is memory taken by duplicated shared data. Prototype or Singleton do not share state between many objects. |
| Notifications: kind and channel vary independently, class per combination | Bridge | Two dimensions of change are separated into two hierarchies. Decorator would stack behavior on one object, not separate two dimensions. |
| Consolidation: parcels and groups of any depth treated uniformly | Composite | The data is a tree where a part and a group share one interface. Decorator and Facade do not model part-whole hierarchies. |
