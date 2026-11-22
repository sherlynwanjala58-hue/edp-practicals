# Week 1 Notes, Comparison Table, Reflections & Self-Assessment

## Activity 1: Spot the Components Table
| System | Event Source | Event | Listener | Handler/Action |
| :--- | :--- | :--- | :--- | :--- |
| ATM Machine Operation | Keypad button | Button pressed | ATM software | Verify PIN |
| Microwave Oven Use | Start button | Button clicked | Microwave controller | Start heating timer |
| Traffic Light System | Timer / Sensor | Interval elapsed / Vehicle detected | Traffic signal controller | Switch signal light state |
| Video Game Gameplay | Keyboard / Controller | Key pressed | Input listener | Move player character |
| Elevator Operation | Floor call button | Button pressed | Elevator dispatch controller | Queue and move elevator car |
| Smartphone Touchscreen | Screen digitizer | Tap / Swipe | Touch event listener | Open app or scroll view |
| Car Dashboard | Ignition / Speed sensor | Key turned / Speed changed | Dashboard controller | Power instruments or update speedometer |
| Security Alarm System | Motion / Door sensor | Sensor tripped | Alarm control panel | Sound siren or send alert |

### Activity 1 Self-Check Answers
1. **Difference between event source and event listener:** An event source generates/fires an event (e.g., a button or key), while an event listener waits for and receives notification of that event.
2. **Can one event source have multiple listeners?** Yes. For example, a single "Submit Order" button can have one listener that validates the form and another listener that updates the UI or logs analytics.
3. **Can one listener handle events from multiple sources?** Yes. For example, one shared `ActionListener` can be attached to "+", "-", and "Reset" buttons and check `e.getSource()` or `e.getActionCommand()` to see which button was clicked.

## Activity 2: Event Loop & Queue Questions and Reflection
1. **What happened to the other events while stuck on the slow handler?** They remained waiting in the queue and were not processed until the slow handler finished.
2. **What would a user see in a real application?** The window would freeze ("Not Responding"), buttons wouldn't click, and the screen wouldn't redraw or resize smoothly.
3. **How could this problem be fixed?** Run long tasks on a background thread (such as `new Thread(...)` or a worker/task) and send UI updates back to the Event Dispatch Thread (EDT).

**Reflection Paragraph:**
In an event-driven system, the event queue acts as a holding line where incoming events—such as button clicks, key presses, and timer triggers—are stored in order until they can be handled. The event loop continuously checks the queue, takes the next event, and runs its corresponding event handler or callback. If a handler takes too long on the main UI thread (the Event Dispatch Thread in Swing), the event loop is blocked and cannot process other events. Blocking the event loop causes the user interface to freeze, ignore user clicks, and fail to repaint. Therefore, slow tasks should always run off the UI thread so the application stays responsive.

## Activity 3: Calculator Questions, Comparison Table & Reflection
### Procedural Calculator Questions
1. **Exact order of steps:** Read first number, read operator, read second number, perform calculation via `switch`, and print the result.
2. **Wrong input at step 2:** If an invalid operator is entered, it prints `"Invalid operator"` and exits the program immediately (`return`).
3. **Calculate again without restarting?** No, once it finishes or hits an invalid operator/division by zero, the program ends.
4. **Who controls the flow?** The program controls the strict step-by-step sequence.

### Event-Driven Calculator Questions
1. **Click buttons in any order?** Yes, the user can click digits, operators, clear (`C`), or equals (`=`) whenever they choose.
2. **Mistake recovery:** The user can click `C` to clear the display and reset state, or type a new number after an error/result.
3. **Calculate again without restarting?** Yes, clicking `C` or starting a new calculation lets the user keep calculating without restarting.
4. **Who controls the flow?** The user controls the flow by triggering events in response to what appears on screen.
5. **How many event handlers?** Four main event handler setups (number buttons, operator buttons, equals button `=`, and clear button `C`).

### Procedural vs. Event-Driven Comparison Table
| Aspect | Procedural | Event-Driven |
| :--- | :--- | :--- |
| Who controls the flow? | The program (fixed sequence of steps) | The user and external events |
| Can user make mistakes and recover easily? | Harder; often exits or requires restarting | Easier; can clear (`C`) or retry actions in the UI |
| Can user interact in any order? | No; must follow steps in order | Yes; events can occur in different orders |
| How many possible execution paths? | Limited, predictable linear/branching paths | Many paths depending on event sequences |
| Code organization | Sequential steps / functions from top to bottom | Event sources registered with listeners and handlers |
| User experience | Predictable for simple scripts, rigid for GUIs | Interactive, flexible, and responsive for GUIs |

**Reflection Paragraph:**
Procedural programming executes instructions in a fixed sequence controlled by the program, whereas event-driven programming waits for events and executes handlers in response to user or system actions. Event-driven programming makes it much easier for users to change their mind, recover from mistakes, and interact in a natural order. For interactive applications and graphical user interfaces (GUIs), event-driven programming is far better because real users do not follow a rigid script. It keeps the interface reactive to clicks, typing, and window changes while separating actions into clear event handlers. Procedural code is still useful for simple, linear batch scripts or step-by-step calculations that need no UI.

## Activity 6: Final Self-Assessment (Questions 1–15)
1. **What is an event? (2 examples):** An occurrence or signal indicating something happened in the system or UI. Examples: clicking a button, pressing a key.
2. **What is an event source? (2 examples):** The component or object that generates and fires an event. Examples: a `JButton`, a `JTextField`.
3. **What is an event listener? (2 examples):** An object or interface implementation that listens for events from a source. Examples: an `ActionListener`, a `KeyListener`.
4. **What is an event handler? (2 examples):** The method or code block that runs to respond when an event occurs. Examples: `actionPerformed(ActionEvent e)`, `keyPressed(KeyEvent e)`.
5. **Component relationship diagram:**
   `Event Source` --fires--> `Event` --dispatched to--> `Event Listener` --invokes--> `Event Handler` (updates UI/state).
6. **Procedural vs. event-driven:** Procedural follows a program-controlled sequence of steps; event-driven responds asynchronously to events triggered by users or the environment.
7. **Main advantage for GUIs:** Users can interact freely in any order while the UI stays responsive to input.
8. **When to choose procedural over event-driven:** Simple, linear scripts or batch tasks with a fixed start-to-end flow and no interactive GUI.
9. **What is the event loop and what does it do?** A continuous loop that polls the event queue and dispatches each event to its handler.
10. **What is the event queue and how does it work?** A queue (typically FIFO) that holds events waiting to be processed by the event loop.
11. **Why should the event loop never be blocked?** Blocking it prevents new events from being processed, freezing the UI and stopping repaints or clicks.
12. **What is the EDT in Java Swing?** The Event Dispatch Thread—Swing's dedicated UI thread where event handlers run and Swing components should be created/updated.
13. **Five real-world event-driven systems:** ATM machine, elevator controller, microwave oven, traffic light system, smartphone UI.
14. **Four EDP components for one system (ATM):** Source = Keypad button; Event = Key press (`ActionEvent`); Listener = ATM keypad listener; Handler = PIN digit entry / verification logic.
15. **Why most modern applications are event-driven:** Modern apps rely on continuous user input, GUIs, network/timer notifications, and real-time responsiveness.

### Week 1 Summary
- **Key definitions:** Event, Event Source, Event Listener, Event Handler, Event Queue, Event Loop, and EDT.
- **Important code/patterns:** Separating UI/event handlers from long-running work (`new Thread(...)` + `SwingUtilities.invokeLater(...)`), and cleaning up collections (`list.clear()`) to avoid memory leaks.
- **Questions to review:** How `SwingWorker` compares to manual `Thread` + `SwingUtilities.invokeLater` in larger Swing apps.