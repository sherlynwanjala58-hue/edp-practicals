# Event-Driven System Design

## System Name: Smart Home Controller

## Part 1: Event Sources
List all components that can generate events:
1. **Lights Toggle (`lightsToggle`)** - Generates toggle action events when switched ON or OFF.
2. **Security Toggle (`securityToggle`)** - Generates toggle action events when armed or disarmed.
3. **Door Sensor Button (`doorButton`)** - Generates action events when a door-open event is triggered or simulated.
4. **Motion / Smoke Sensor (Simulated/Input)** - Generates sensor alert events when movement or smoke is detected.
5. **Thermostat Control** - Generates temperature change or target-setpoint events.

## Part 2: Events
List all possible events in the system:
| Event Name | Source | Description |
| --- | --- | --- |
| `LightsToggledEvent` | `lightsToggle` | Triggered when the user clicks the lights toggle switch. |
| `SecurityModeChangedEvent` | `securityToggle` | Triggered when the user arms or disarms the security system. |
| `DoorOpenedEvent` | `doorButton` | Triggered when the door sensor detects or simulates an opened door. |
| `MotionDetectedEvent` | Motion Sensor | Triggered when motion is detected in a monitored zone. |
| `TargetTempChangedEvent` | Thermostat Control | Triggered when desired room temperature is adjusted. |

## Part 3: Event Handlers
For each event, describe what should happen:
| Event | Handler Action |
| --- | --- |
| `LightsToggledEvent` | Updates the toggle label (`Lights: ON` / `Lights: OFF`), updates `statusLabel`, and appends a timestamped log entry to `eventLog`. |
| `SecurityModeChangedEvent` | Updates the toggle label (`Security: ARMED` / `Security: OFF`), updates `statusLabel`, and records arm/disarm state in `eventLog`. |
| `DoorOpenedEvent` | Logs the door trigger; if security is armed, triggers `ALARM ACTIVE!`, logs an intruder alert, and highlights the background red; otherwise logs a normal door open. |
| `MotionDetectedEvent` | Logs motion activity and triggers an alert if away/armed mode is active. |
| `TargetTempChangedEvent` | Updates the displayed target temperature and logs the setpoint change. |

## Part 4: Event Flow Diagram
```text
User Action / Sensor Trigger
        │
        ▼
  Event Source (e.g., JToggleButton / JButton)
        │  fires
        ▼
  Event Object (ActionEvent)
        │  placed into
        ▼
  Event Queue (AWT / Swing Event Queue)
        │  polled by
        ▼
  Event Loop (Event Dispatch Thread - EDT)
        │  invokes registered
        ▼
  Event Handler (ActionListener callback)
        │  updates
        ▼
  GUI State & Event Log (on EDT)
  