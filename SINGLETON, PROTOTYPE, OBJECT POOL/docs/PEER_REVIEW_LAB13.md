# Peer Review: Lab 13, Variant 3 "Parcel+"

Scope: `src/main/java/parcelplus/lab13`, diagrams `docs/diagrams/lab13_*`, runner output of `parcelplus.lab13.Main`.

Review checks required by the assignment:

1. Does the class diagram match the code?
2. Does the solution remove the problem from the situation description?
3. Does the runner reproduce the demonstration scenario?
4. Does the log show which object received the request and which one actually processed or executed it?

## Task 1. Shipment acceptance checks (Chain of Responsibility)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | Abstract `ShipmentCheck` (handler) with the `next` link (`1` to `0..1`), six concrete checks, `Shipment` record as the request. |
| Problem removed | Yes | Every check is a separate class that either rejects with a reason or passes the shipment on. The order and the set of checks are defined by `ShipmentCheck.chain(...)`, separately for the domestic and the international chain. A new check is a new class, existing checks are not changed. |
| Runner reproduces scenario | Yes | A correct international shipment passes five checks. A 45 kg shipment stops at `WeightLimitCheck` with the reason, `DimensionsCheck` and `CustomsDeclarationCheck` do not run (not in the log). `LithiumBatteryCheck` is added to the chain and rejects a shipment with batteries. A domestic shipment passes the shorter chain without the customs check. |
| Log shows the performer | Yes | Every check logs "Checking", then either "Passed, forwarding to X" or "rejected: reason" under its own name. The runner logs which chain received the shipment and the final result. |

Findings:

- Fixed, minor: `ShipmentCheck.chain()` with no arguments failed with `ArrayIndexOutOfBoundsException`. It now throws `IllegalArgumentException` with a clear message.
- Minor, not fixed: the lithium battery rule (rejected for international shipments only) is an assumption, the situation only says to add the check. It is easy to change inside `LithiumBatteryCheck`.
- Design decision: the chain is built by a static helper in the handler, so no extra builder class is needed. The last check of a chain always gets `next = null`, so a check instance can be reused in a new chain.

## Task 2. Sorting warehouse operations (Command)

| Check | Result | Evidence |
|---|---|---|
| Diagram matches code | Yes | `Command` interface, three concrete commands holding the `Warehouse` receiver, `OperationQueue` invoker with the pending queue, the executed stack and the journal. |
| Problem removed | Yes | Every operation is an object with `execute()` and `undo()`. Operations are queued first and then run one by one, none is dropped. The last executed operation can be undone, all actions are recorded in the journal. |
| Runner reproduces scenario | Yes | Five operations are queued (pending count 1 to 5), then dispatched one by one (pending 4 to 0). The mistaken loading of PS-2005 is undone and the parcel returns to sector A1. The journal lists 5 executed entries and 1 undone entry. |
| Log shows the performer | Yes | `OperationQueue` logs which concrete command it dispatches to, `Warehouse` logs the real state change. |

Findings:

- Fixed, medium: the situation says operations arrive faster than the system handles them, which means several scanner threads. `OperationQueue` used non-thread-safe collections. Its public methods are now `synchronized`, so a concurrent `submit()` cannot corrupt the queue.
- Minor, not fixed: only the last operation can be undone, as required. Calling `undo()` on a command that was never executed would put `null` as the location. The queue never does this.
- Design decision: the journal stores strings and keeps both the executed and the undone actions in time order.

## Cross-cutting remarks

- All output goes through `Log.log(source, event)` in the format `[time] [source] event`.
- No comments, UI, database or third-party libraries. Code of labs 9 to 12 is unchanged.
- Each of the two patterns is used exactly once.

## Defense notes: why these patterns

| Situation | Pattern | Why this one and not the other |
|---|---|---|
| Acceptance checks: sequence of independent checks, each rejects or passes on, configurable order | Chain of Responsibility | A request goes through handlers that decide themselves whether to stop it. Command would not model "pass to the next handler". |
| Warehouse operations: must be queued, executed one by one, undone and journaled | Command | The request is turned into an object that can be stored, postponed, undone and logged. A chain has no queue and no undo. |
