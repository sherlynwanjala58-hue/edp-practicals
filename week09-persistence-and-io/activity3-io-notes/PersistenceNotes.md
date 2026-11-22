# Week 9 Notes: Persistence & Event-Driven File I/O

## Key Takeaways
- Use `java.nio.file.Files` and `java.util.Properties` with try-with-resources streams so file handles close reliably.
- Handle `IOException` cleanly with user-friendly messages instead of crashing the Swing application.
- Keep heavy file reads/writes off the EDT if files grow large.