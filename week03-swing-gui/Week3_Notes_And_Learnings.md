# Week 3: Self-Checks, Assessment & What I've Learned

## What I've Learned in Week 3
- Created top-level Swing windows using `JFrame` and configured close behavior, sizing, and centering.
- Built forms and displays using core Swing components (`JLabel`, `JTextField`, `JPasswordField`, `JCheckBox`, `JRadioButton` with `ButtonGroup`, `JComboBox`, `JTextArea` inside `JScrollPane`, and `JButton`).
- Arranged and nested layouts using `FlowLayout`, `BorderLayout`, `GridLayout`, and `BoxLayout`.
- Understood the Event Dispatch Thread (`AWT-EventQueue-0`) and why long operations must run on a background `Thread` with GUI updates dispatched via `SwingUtilities.invokeLater(...)`.
- Built a complete static Student Portal UI mockup combining nested layout managers and panels.

## Activity 1 Self-Check
- **JFrame vs. Content Pane:** `JFrame` is the top-level window container; the content pane holds added components (`frame.add(...)` adds to the content pane).
- **`EXIT_ON_CLOSE`:** Terminates the JVM when the window closes.
- **`SwingUtilities.invokeLater(...)`:** Schedules GUI creation/updates on the Event Dispatch Thread (EDT) for thread safety.

## Activity 2 Self-Check
- **`JTextField` vs. `JTextArea`:** `JTextField` is single-line input; `JTextArea` is multi-line input.
- **`ButtonGroup`:** Ensures mutually exclusive selection among `JRadioButton` options.
- **`JScrollPane` around `JTextArea`:** Provides scrollbars when text exceeds visible bounds.

## Activity 3 Self-Check
- **`GridLayout` vs. `FlowLayout`:** `GridLayout` creates equal-sized cells in rows/columns; `FlowLayout` flows items naturally and wraps lines.
- **Nesting layout managers:** Allows combining different layout behaviors inside one application window.
- **`BorderLayout.CENTER`:** Expands to fill all remaining space not used by `NORTH`, `SOUTH`, `EAST`, or `WEST`.

## Activity 4 Self-Check
- **Why `Thread.sleep()` on EDT freezes UI:** Blocks the event loop so repaints, clicks, and window events cannot be processed.
- **Updating GUI from background thread:** Wrap GUI updates in `SwingUtilities.invokeLater(() -> ...)`.
- **Consequences of violating EDT rules:** UI freezes, visual glitches, stale state, or unpredictable Swing behavior.

## Activity 5 Self-Check
- **Layouts combined:** `BorderLayout`, `GridLayout`, `BoxLayout`, and `FlowLayout`.
- **`Box.createVerticalStrut(20)`:** Adds a 20px vertical spacer between components in a `BoxLayout`.
- **`setPreferredSize(...)` on sidebar:** Sets a fixed width (e.g., `180px`) on the `WEST` region.

## Final Self-Assessment
- **LO1 (JFrame):** Created and displayed `JFrame` windows with `SwingUtilities.invokeLater(...)`.
- **LO2 (Layout Managers & Use Cases):**
  - `FlowLayout`: Toolbars or wrapping button rows.
  - `BorderLayout`: Main application frame (`NORTH`/`SOUTH`/`EAST`/`WEST`/`CENTER`).
  - `GridLayout`: Calculator keypad or uniform card grids.
  - `BoxLayout`: Vertical or horizontal stacking of sections.
- **LO3 (Two Golden Rules of Swing Threading):**
  1. All GUI creation and updates must occur on the EDT (`AWT-EventQueue-0`).
  2. Never run long-blocking operations on the EDT; run work in a background thread and update the UI via `SwingUtilities.invokeLater(...)`.
- **LO4 (Static Mockup):** Built and ran `StudentPortal.java` cleanly.