# Peer Review: Lab 15, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab15`, diagrams `docs/diagrams/lab15_*`, runner output of `parcelplus.lab15.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?
4. Does the log show which algorithm, state or process step is executed at the moment?

## Task 1. Shipment handover (Template Method)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Abstract `HandoverProcedure` with the final `handOver()`, two abstract steps, one hook, three private common steps, three concrete handover classes. |
| Problem removed | Yes | The sequence of six steps exists only in `handOver()`, which is `final`. The cash on delivery check is a private method of the base class, so a rule change is made once and applies to the branch, the courier and the locker. Subclasses define only identification and confirmation. Contents inspection is a hook with default behavior. |
| Runner reproduces scenario | Yes | Handover at the branch (all six steps, inspection overridden), through the locker with the same procedure (inspection default), by the courier with cash on delivery 850.00 UAH verified by the same shared logic. |
| Log shows the step | Yes | Every step is logged as "Step n/6" with its title, followed by the detail of the concrete class or the shared logic. |

Findings:

- Minor, not fixed: the payment step only logs the verification, there is no real payment system, as the task allows.
- Design decision: only the hook `inspectContents()` is overridable among the optional steps. Steps 2, 4 and 6 are private so that no handover method can change them, which is exactly the situation requirement.

## Task 2. Shipment state (State)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `Parcel` context holds a `ParcelState`, six concrete states, each transition is created by the state itself. |
| Problem removed | Yes | `Parcel` has no branching by status, every operation is delegated to the current state. Not allowed operations are refused by the default implementations in `ParcelState` with a clear reason. A new state (for example customs hold) is a new subclass, `Parcel` is not changed. |
| Runner reproduces scenario | Yes | PS-9101 goes through all states to `DELIVERED`. PS-9102: the address is changed before dispatch, in transit the change is refused and the redirect succeeds. PS-9103: 3 days at the branch are kept, at 5 days the parcel moves to `RETURNED_TO_SENDER`. |
| Log shows the state | Yes | Transitions are logged as "State A -> B" under the parcel, refusals and the storage decision are logged under the name of the current state class. |

Findings:

- Fixed, medium: operations on one parcel could come from different threads (a scanner moving the parcel and a customer asking for a redirect), and the `state` field was not protected. The public operations of `Parcel` are now `synchronized`.
- Deviation from the literal scenario, minor: the scenario names the address change and the redirect for a parcel "in transit". The demo first shows the address change before dispatch (allowed) on the same parcel, then in transit (refused), then the redirect (allowed).
- Minor, not fixed: states are stateless and a new instance is created on every transition. They could be shared constants, but this is not needed here.

## Task 3. Courier route building (Strategy)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `RouteStrategy` with five implementations, `CourierRoutePlanner` holding one strategy, `Address` record. |
| Problem removed | Yes | Every principle is a separate class and they are interchangeable. The planner calls only the interface and the strategy can be replaced at runtime with `setStrategy()`. A new principle is one new class. |
| Runner reproduces scenario | Yes | The same 6 addresses are routed with different principles with a visibly different order and length: shortest distance (30.10 km), urgent first (34.70 km), fewest left turns (35.76 km). On Friday the strategy is switched to congestion aware (36.91 km). The ecological route is added as one new class (36.29 km). |
| Log shows the algorithm | Yes | The planner logs which strategy is chosen, the strategy logs its route under its own class name, the planner logs the length. |

Findings:

- Fixed, medium: the first version of `EcoRouteStrategy` (low congestion first) produced exactly the same order as `CongestionAwareStrategy`, so the demo did not show the new principle at all. The rule was changed to visit congested addresses first, while the roads are empty, then the rest by nearest neighbor.
- Fixed, minor: `setStrategy()` and `plan()` of the planner are now `synchronized`, because a logist may switch the principle while a route is being built.
- Minor, not fixed: "fewest left turns" is approximated by a clockwise traversal around the depot, because real turns need a road graph. The coordinates and congestion values are hardcoded, as the task allows.
- Design decision: the shared nearest-neighbor loop is a static method of the interface, so three strategies do not repeat it and no extra class is needed.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`.
- No comments, UI, database or third-party libraries. Code of labs 9 to 14 is unchanged.
- Each of the three patterns is used exactly once.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the others |
|---|---|---|
| Handover: same steps in the same order, only some steps differ, code was copied three times | Template Method | The skeleton is fixed in a base class and subclasses fill in the differences. Strategy would swap a whole algorithm, but here the order must not be changeable. |
| Parcel: behavior of every operation depends on the current state, transitions between states | State | Each state is a class that knows what is allowed and where to go next. Strategy has no transitions, the choice of strategy is made from outside. |
| Route building: interchangeable algorithms chosen at runtime by the logist | Strategy | The whole algorithm is replaced by another one behind one interface. Template Method fixes the structure and varies steps by inheritance, so it cannot be switched at runtime. |
