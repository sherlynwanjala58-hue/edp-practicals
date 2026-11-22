# Week 7 Notes: Concurrency & SwingWorker

## Key Rules
- **Never block the Event Dispatch Thread (EDT)** with `Thread.sleep()`, heavy loops, or slow I/O.
- **`doInBackground()`** runs on a worker thread for long-running work and returns a final result.
- **`publish()` + `process()`** safely pass intermediate updates back to the EDT.
- **`done()`** runs on the EDT after `doInBackground()` finishes or is cancelled.