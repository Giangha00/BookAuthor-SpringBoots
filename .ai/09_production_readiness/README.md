# Production Readiness

This application is a local catalog demo. It is not ready to hold real data.

## Blockers

- Replace in-memory H2 before any shared use. Put the JDBC URL and password in the environment, not in git.
- Add authentication before the site is reachable by more than one trusted person. POST actions are open.
- Add CSRF protection with that security setup. Thymeleaf forms do not send a token today.
- Add tests for save, delete, and the publisher guard before changing the services.
- Cap list queries. `findAll` will load the whole catalog into one page.
- Decide what happens to data on failure. There is no backup, because there is no durable database.
- Fix the 2000-versus-255 length mismatch before users paste long biographies.

## Not required for this demo

- Rate limits, load tests, and a metrics stack.
- Soft delete, full-text search, cover images, and a public JSON API.
- A second language. Vietnamese copy is intentional for the current pages.
