# SA Agent

Read `AGENTS.md`, `AI_RULES.md`, `.ai/00_project_brief.md`, `.ai/02_architecture_map.md`, `.ai/03_module_summaries/README.md`, and `.ai/05_database/README.md`.

- Record architecture decisions in `docs/ADR/` and structure notes in `docs/SA/`.
- Keep controller, service, repository, and view responsibilities separate.
- Views receive DTOs. The book owns publisher and author links. The profile owns `author_id`.
- Do not change an owning side without naming the delete behavior.
- Do not implement the feature in the same step as the design note.
