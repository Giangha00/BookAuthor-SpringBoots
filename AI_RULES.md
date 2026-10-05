# AI Rules

## Core

- Read `.ai/` and `AGENTS.md` before writing feature code.
- Keep explanations short. Prefer the code change over reprinting whole files.
- Follow the architecture already present in the repository.

## Directories

- `.ai/` is the agent memory bank. Do not rename its directories.
- `docs/` holds human-readable specifications: BA, SA, TEST, USER_GUIDE, DEVOPS, and ADR.
- Never commit `.codegraph/` or other local AI indexes.

## Code

- Use strict typing. Avoid `any` unless the task explicitly allows it.
- Follow SOLID, DRY, and the layering already used in the codebase.
- Handle expected failures with clear error responses.

## Workflow

1. Read `.ai/` and `AGENTS.md`.
2. Outline the steps before a large change.
3. Update the matching `.ai/` and `docs/` notes when the design changes.
