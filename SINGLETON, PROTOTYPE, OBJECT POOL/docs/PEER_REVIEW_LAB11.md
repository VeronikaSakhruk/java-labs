# Peer Review: Lab 11, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab11`, diagrams `docs/diagrams/lab11_*`, runner output of `parcelplus.lab11.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?
4. Does the log show which object really performs the action (wrapper or wrapped object)?

## Task 1. Shipment registration (Facade)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `ShipmentFacade` holds six subsystems (composition, multiplicity 1). `WaybillGenerator` uses `WaybillService` from lab 9, shown on the diagram. |
| Problem removed | Yes | The order of the six steps exists only in `ShipmentFacade.register()`. Branch, online cabinet and terminal call this one operation. Subsystem classes are public, so they can still be used separately. |
| Runner reproduces scenario | Yes | Registration from a branch and from the online cabinet with the same call, every subsystem logs its own step in the correct order. Then `CostCalculator` is called alone. |
| Log shows the performer | Yes | The facade logs the request and the result, each subsystem logs its own action under its own name. |

Findings:

- Minor, not fixed: payment is taken before the waybill is generated, and there is no rollback if a later step fails. The situation does not describe failures after payment.
- Note: the terminal channel is not shown in the runner, only branch and online cabinet, as in the scenario. It would use the same `register()` call.

## Task 2. Additional services (Decorator)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `Shipment`, `BasicShipment`, abstract `ShipmentDecorator` wrapping one `Shipment`, five concrete decorators. |
| Problem removed | Yes | Services are stacked at runtime in any order and number (SMS notification twice in the demo). The number of classes grows by one per service, not per combination. A decorated shipment is used through the same `Shipment` interface. |
| Runner reproduces scenario | Yes | Plain shipment 80.00 UAH, then insurance, packaging and cash on delivery give 175.00 UAH, then a second shipment with weekend delivery and two SMS notifications gives 142.00 UAH. The arithmetic was checked by hand. |
| Log shows the performer | Yes | `BasicShipment` logs the base cost, every decorator logs its own surcharge, so the chain of wrappers is visible. |

Findings:

- Minor, not fixed: `ShipmentDecorator` accepts a null `wrapped` object and would fail later with a `NullPointerException`. Acceptable for the scope of the lab.
- Design decision: description and cost logic is written once in `ShipmentDecorator`, concrete decorators only provide `label()` and `surcharge()`. A new service needs one tiny class.

## Task 3. Partner tracking (Proxy)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `TrackingService`, `PartnerTrackingService`, `CachingTrackingProxy` with the private `CacheEntry` record. |
| Problem removed | Yes | Repeated requests inside the TTL are answered from the cache, an expired entry causes a new paid request. The online cabinet uses only `TrackingService`. |
| Runner reproduces scenario | Yes | 1 paid request, 3 requests answered from the cache, time moved by 10 minutes, a new paid request with the updated status. Final counter: 2 paid requests. |
| Log shows the performer | Yes | `CachingTrackingProxy` logs cache hits and misses, `PartnerTrackingService` logs only the real paid requests. |

Findings:

- Fixed, medium: `getStatus()` of the proxy used a plain `HashMap` and was not synchronized, so two simultaneous page refreshes could both miss the cache and make two paid requests. The method is now `synchronized`.
- Design decision: time is injected as a `LongSupplier`, so the demo can move the clock without waiting.

## Task 4. Partner scales (Adapter)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `Scale` is the target, `PartnerScale` the adaptee, `PartnerScaleAdapter` holds it and converts units. `LegacyScale` implements the target directly. |
| Problem removed | Yes | `ShipmentIntake` knows only `Scale`. Pound to kilogram and inch to centimeter conversion exists only in `PartnerScaleAdapter`. Another vendor needs another adapter only. |
| Runner reproduces scenario | Yes | The old scale in Branch #1 and the partner scale in Branch #2 are quoted by the same code. Raw values (11.02 lb, inches) and converted values (5.00 kg, 40 x 30 x 20 cm) are in the log, the cost is calculated from converted values. |
| Log shows the performer | Yes | `PartnerScale` logs raw readings, `PartnerScaleAdapter` logs conversions, `ShipmentIntake` logs the quote. |

Findings:

- Minor, not fixed: `sizeCm()` returns an array in the fixed order length, width, height. A small value class would be clearer but adds a class that the task does not need.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`. A small `Log.fmt()` helper was added for number formatting, other lab 9 code is unchanged.
- No comments, UI, database or third-party libraries.
- Each of the four patterns is used exactly once.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the others |
|---|---|---|
| Registration: many subsystems called in one order, duplicated in three clients | Facade | One simple entry point hides the sequence and the subsystems stay available. Adapter would change an interface, not hide a sequence. |
| Additional services: arbitrary combinations, class explosion | Decorator | Behavior is added at runtime by wrapping, and the wrapper keeps the same interface. Subclassing per combination causes the explosion. |
| Partner tracking: paid remote calls, repeated requests | Proxy | The proxy controls access to the same interface (here by caching) without the client knowing. Decorator adds new features, proxy controls access to the real object. |
| Partner scales: incompatible interface and units, vendor code cannot be changed | Adapter | The goal is to make an existing class fit an interface the system already uses. Facade would simplify, not convert. |
