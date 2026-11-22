# Week 2: Self-Checks, Coupling Comparison, Activity 5 Assessment & Final Reflection

## Activity 1 Self-Check (Step 8)
1. **What is the role of the Subject interface?**
   It defines the contract for registering observers, removing observers, and notifying registered observers when state changes.
2. **What happens if you call notifyObservers() without registering any observers?**
   The loop over the observers list runs zero times and nothing breaks or throws an error.
3. **What is the difference between registerObserver() and removeObserver()?**
   `registerObserver()` adds an observer to the list so it receives future updates; `removeObserver()` removes an observer from the list so it stops receiving updates.
4. **Why is the observers list stored as an instance variable?**
   Each subject instance needs to maintain its own list of subscribed observers across method calls (`registerObserver`, `removeObserver`, `notifyObservers`, `setMeasurements`).

## Activity 2 Self-Check (Step 4)
1. **Why can't you extend another class if you already extend Observable?**
   Java only supports single class inheritance, so extending `Observable` uses up the class's single superclass slot.
2. **What is the difference between the push model and the pull model?**
   In the push model, the subject passes updated data values directly to `update(...)`; in the pull model, the observer receives a notification/reference and calls getter methods on the subject to retrieve the data it needs.
3. **Why did Java deprecate these classes?**
   Because `Observable` is a concrete class rather than an interface, notification order is unspecified, `setChanged()` can be forgotten (resulting in missed notifications), data retrieval relies on pulling/casting, and the API lacks flexibility and thread-safety guarantees.

## Activity 3 Coupling Problem (Step 2) & Comparison (Step 4)
1. **What happens if we want to add a third display class? Do we have to modify DataModel?**
   In the tightly-coupled version, yes—you must edit `DataModel.setValue(...)` to instantiate and call the new display. In the refactored Observer version, you do not modify `DataModel`; you simply create a new `DataObserver` and register it.
2. **What happens if LabelDisplay is deleted or renamed?**
   In the tightly-coupled version, `DataModel` fails to compile until updated. In the refactored version, `DataModel` only depends on `DataObserver`, so `DataModel` itself does not break.
3. **Can we easily test DataModel in isolation?**
   In the tightly-coupled version, testing `DataModel` in isolation is difficult because it directly instantiates/calls display classes. With the Observer pattern, `DataModel` can be tested independently or with lightweight test/mock `DataObserver` implementations.

| Aspect | Tightly Coupled | Observer Pattern |
| --- | --- | --- |
| Adding a new display | Modify `DataModel` | Just register a new observer |
| Testing DataModel in isolation | Hard — it needs display classes | Easy — no concrete display classes required |
| Removing a display | Modify `DataModel` | Just don't register it (or remove it) |

## Activity 4 Self-Check (Step 4)
1. **Which mechanism carries both the old and new value of a property?**
   `PropertyChangeListener` (via `PropertyChangeEvent`, which provides `getOldValue()` and `getNewValue()`).
2. **Which mechanism requires a helper class to manage the listener list?**
   `PropertyChangeListener` (via `PropertyChangeSupport`).
3. **Which two mechanisms are recommended for modern Java?**
   Custom Observer and JavaFX Bindings.
4. **Which mechanism is deprecated and should be avoided?**
   `java.util.Observer` / `java.util.Observable`.

---

## Activity 5: Final Self-Assessment

### Learning Outcome 1: Design and Implement the Observer Pattern
1. **UML Diagram of the Observer Pattern:**
   ```text
   +------------------------+          +----------------------+
   | <<interface>>          |          | <<interface>>        |
   | Subject                |<>------->| Observer             |
   +------------------------+    *     +----------------------+
   | + registerObserver(o)  |          | + update(...)        |
   | + removeObserver(o)    |          +----------------------+
   | + notifyObservers()    |                     ^
   +------------------------+                     | implements
               ^                        +----------------------+
               | implements             | ConcreteObserver     |
   +------------------------+          +----------------------+
   | ConcreteSubject        |          | - state              |
   +------------------------+          | + update(...)        |
   | - observers            |          +----------------------+
   | - state                |
   | + registerObserver(o)  |
   | + removeObserver(o)    |
   | + notifyObservers()    |
   +------------------------+