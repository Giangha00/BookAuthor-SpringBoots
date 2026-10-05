# AGENTS

## Permissions

- Read the repository before changing code.
- Do not implement a feature until `.ai/` and this file have been read.
- Do not delete or rename directories under `.ai/`.
- Do not commit `.codegraph/`, local AI indexes, or temporary snapshots.
- Ask before changing product behavior that is not covered by the current task.

## Required reading

Read these files before planning or writing code:

1. `AI_RULES.md`
2. `.ai/00_project_brief.md`
3. `.ai/02_architecture_map.md`
4. The `.ai/` notes for the module being changed

## Output format

- State the plan in a few steps before a large change.
- Prefer a short explanation plus the code change.
- Record new architecture decisions, API designs, and test cases under `.ai/` and `docs/`.
- Keep human-facing specifications in `docs/`.

## Pull requests

- One purpose per pull request.
- Title in imperative mood: `feat:`, `fix:`, `docs:`, `refactor:`, `test:`, or `chore:`.
- Describe what changed and why.
- List the checks that were run.
- Do not include generated indexes or secrets.
