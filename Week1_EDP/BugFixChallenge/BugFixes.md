# Bug Fix Documentation

## Bug 1: Blocking the EDT
- **Symptom:** UI freezes when the "Process" button is clicked; window cannot be moved, resized, or clicked for 5 seconds.
- **Cause:** `Thread.sleep(5000)` was executed directly inside the `ActionListener` on the Event Dispatch Thread (EDT).
- **Fix:** Moved the 5-second sleep into a background `Thread` and scheduled the UI label and button updates back onto the EDT using `SwingUtilities.invokeLater(...)`.
- **Code Change:** Wrapped `Thread.sleep(5000)` inside `new Thread(() -> { ... }).start()`, disabled `processButton` while running, and updated `statusLabel` and re-enabled `processButton` inside `SwingUtilities.invokeLater(...)`.

## Bug 2: Missing Listener
- **Symptom:** Clicking the "Increment" button does nothing and the count remains `0`.
- **Cause:** No `ActionListener` was registered on `incrementButton`.
- **Fix:** Added an `ActionListener` to `incrementButton` that increments `count` and updates `countLabel`.
- **Code Change:** Attached `incrementButton.addActionListener(e -> { count++; countLabel.setText("Count: " + count); });`.

## Bug 3: Wrong Thread
- **Symptom:** Updating `timeLabel` directly from a background `while` loop violates Swing's single-thread rule and can cause erratic UI behavior or race conditions.
- **Cause:** `timeLabel.setText(...)` was called directly from a background thread instead of the EDT.
- **Fix:** Used `SwingUtilities.invokeLater(...)` to perform the `timeLabel.setText(...)` update on the EDT, and guarded against starting multiple clock threads.
- **Code Change:** Captured `currentTime` in the background thread and passed `timeLabel.setText(currentTime)` into `SwingUtilities.invokeLater(...)`.

## Bug 4: Memory Leak
- **Symptom:** Memory usage grows over time as buttons are repeatedly added and removed because old `JButton` references remain retained in `createdButtons`.
- **Cause:** `buttonPanel.removeAll()` removed buttons from the UI container, but `createdButtons.clear()` was never called.
- **Fix:** Cleared `createdButtons` inside the `removeButton` action handler after removing components from `buttonPanel`.
- **Code Change:** Added `createdButtons.clear();` inside `removeButton.addActionListener(...)`.