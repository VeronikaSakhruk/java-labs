# Peer Review: Lab 10, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab10`, diagrams `docs/diagrams/lab10_*`, runner output of `parcelplus.lab10.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?

## Task 1. Shipment packaging (Abstract Factory)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `PackagingKitFactory` with 4 concrete factories, 3 abstract products (`Box`, `Filler`, `Label`) and 12 concrete products. The products are private nested records of their factory, as in the code. |
| Problem removed | Yes | A factory is the only way to get packaging elements and it creates elements of one type only. Concrete products are private, so a fragile box cannot be combined with a cold label. `PackingStation` uses only interfaces. |
| Runner reproduces scenario | Yes | Standard, fragile and cold shipments are packed by the same `pack()` call, the log shows every element with its type and the consistency check. The oversized type is added with `OversizedKitFactory` only and packed by the same station. |

Findings:

- Minor, not fixed: the consistency check in `PackingStation` is informational. The guarantee comes from the factory, the check only makes it visible in the log, as the scenario requires.
- Minor, not fixed: the diagram is wide because of 12 concrete products. Kept complete so that it matches the code.

## Task 2. Delivery methods (Factory Method)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Abstract `DeliveryProcess` with `createDelivery()` and the final `arrange()`, 4 concrete creators, `Delivery` interface and 4 nested concrete products. |
| Problem removed | Yes | There is no branching by delivery method. `arrange()` is `final`, written once, and gets the product from `createDelivery()`. A new method means a new subclass only. |
| Runner reproduces scenario | Yes | One shipment is arranged by courier, parcel locker and branch with different cost and term in the log. Floor delivery is added with `FloorDeliveryProcess` only and arranged by the same `arrange()`. |

Findings:

- Minor, not fixed: an invalid address stops the process with a log message and no exception. Acceptable here because the situation does not define error handling for this step.
- Note: costs and terms are hardcoded in the concrete products, as allowed by the task.

## Task 3. Waybill creation (Builder)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `WaybillBuilder` (package-private fields, fluent steps, `build()`, private `validate()`), immutable `Waybill` with package-private constructor, `WaybillDirector` with two typical waybills. |
| Problem removed | Yes | There is no constructor with many parameters. Mandatory parts (sender, receiver, receiver phone, weight above zero) are checked in `build()`. On error the message is logged and an exception stops the creation, so no incomplete waybill exists. `Waybill` has only final fields. |
| Runner reproduces scenario | Yes | Full waybill with declared value, cash on delivery and insurance, two typical waybills made by the director, and a waybill without receiver phone rejected with "Cannot create waybill: receiver phone is not specified". |

Findings:

- Fixed, medium: the weight check `weightKg <= 0` let `NaN` pass, so a waybill with an undefined weight could be created. The condition is now `!(weightKg > 0)`.
- Minor, not fixed: optional amounts (declared value, cash on delivery) are not validated for negative values. The situation does not require it.
- Design decision: the director creates a new builder for every typical waybill, so no state leaks from one waybill to the next.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`. Created objects are printed in readable form (`describe()` lines and `Waybill.toString()`).
- No comments, UI, database or third-party libraries. Lab 9 code is unchanged and still runs with `parcelplus.Main`.
- Only the classes needed for the three situations were added.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the others |
|---|---|---|
| Packaging: related elements (box, filler, label) must always match one type, new type must not change the packing code | Abstract Factory | The problem is a family of related products that must not be mixed. Factory Method creates one product, Builder assembles one object step by step. |
| Delivery: one common process, the concrete delivery object differs, branching by method must disappear | Factory Method | One product is chosen by a subclass inside a common algorithm. Abstract Factory would be excessive because there is no family of products. |
| Waybill: many parts, some mandatory, no invalid objects, typical sequences | Builder | The problem is the construction of one complex object in steps with validation at the end. The Director holds the typical sequences. |
