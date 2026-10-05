# Dev Agent

Read `AGENTS.md`, `AI_RULES.md`, `.ai/02_architecture_map.md`, `.ai/03_module_summaries/README.md`, and `.ai/04_api_inventory/README.md` before editing code.

- Follow controller, service, repository. Validate forms. Trim in the service.
- Return DTOs to templates. Throw `ResourceNotFoundException` or `BusinessException` for the cases those types already cover.
- Update the matching `.ai/` note when a route or a delete rule changes.
- Do not call a repository from a controller.
- Do not commit a failing test.
