# Limitations of java.util.Observer and java.util.Observable

## Limitation 1: Single Inheritance
**Explain:** Why is it a problem that Observable is a CLASS, not an INTERFACE?
**Your answer:** Because `Observable` is a concrete class rather than an interface, a class that extends `Observable` cannot extend another superclass due to Java's single inheritance rule, restricting class design and reuse.

## Limitation 2: Cannot Change the Notification Order
**Explain:** In what order are observers notified? Can you control this?
**Your answer:** Observers are notified in an unspecified order depending on internal registration/list handling, and you cannot reliably control or guarantee notification order through the API.

## Limitation 3: setChanged() is Confusing
**Explain:** Why must you call setChanged() before notifyObservers()?
**Your answer:** `setChanged()` marks the `Observable` state as changed so `notifyObservers()` knows there is an update to broadcast; forgetting to call `setChanged()` causes notifications to be silently skipped.

## Limitation 4: Pull Model by Default
**Explain:** How does the observer get the data? Is this efficient?
**Your answer:** When `notifyObservers()` is called with no argument, observers pull state back from the `Observable` via getter methods. It can be less direct and require extra getter calls or type casting even if an observer only needs part of the state.

## Limitation 5: Deprecated Since Java 9
**Explain:** Why did Java deprecate these classes?
**Your answer:** They were deprecated because `Observable` is a class rather than an interface (limiting inheritance), notification order is unspecified, `setChanged()` is easy to forget (causing silent missed updates), data retrieval relies on pulling/casting, and the design is not thread-safe or easily extensible.