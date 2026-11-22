# Week 4 Notes & Self-Assessment: Making Swing Applications Interactive

## Core Event Handling Terms
- **Event Source:** The component that fires the event (e.g., `JButton`).
- **Event:** The event object created when an action happens (e.g., `ActionEvent`, `ItemEvent`, `KeyEvent`).
- **Event Listener:** The interface/handler waiting for the event.
- **Event Handler:** The method/code that runs in response (e.g., `actionPerformed()`, `itemStateChanged()`).

## ActionListener Styles
1. **Named Class:** Reusable across components or when state is needed.
2. **Anonymous Inner Class:** Common in legacy code or when a dedicated inner handler is needed.
3. **Lambda Expression:** Concise and preferred for modern Java single-method handlers (plus method references when a helper method already exists).

## Component-to-Listener Reference
- `JButton` / Menu Item: `ActionListener`
- `JCheckBox` / `JRadioButton`: `ItemListener` (or `ActionListener`)
- `JTextField` / `JTextArea` text changes: `DocumentListener` on `.getDocument()`
- Mouse clicks / enter / exit: `MouseListener` / `MouseAdapter`
- Mouse movement / dragging: `MouseMotionListener` / `MouseAdapter`
- Key presses: `KeyListener` / `KeyAdapter` (requires focus) or Key Bindings (`WHEN_IN_FOCUSED_WINDOW`)