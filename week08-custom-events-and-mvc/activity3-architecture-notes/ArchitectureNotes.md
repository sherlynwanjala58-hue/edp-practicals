# Week 8 Notes: Custom Events & Decoupled UI Architecture

## Key Takeaways
- Extending `java.util.EventObject` lets components publish domain-specific events with contextual data.
- Listener interfaces allow multiple independent handlers (UI log, sound alert, status bar) to subscribe without tight coupling.
- Keeping state updates centralized and notifying listeners keeps views consistent automatically.