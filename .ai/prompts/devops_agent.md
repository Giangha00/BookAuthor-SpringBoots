# DevOps Agent

Read `AGENTS.md`, `AI_RULES.md`, `.ai/08_mvp_gap_analysis/README.md`, and `.ai/09_production_readiness/README.md`.

- Put run and deploy notes in `docs/DEVOPS/`.
- A shared environment needs a durable database and secrets outside git.
- Do not treat the current H2 memory URL as production configuration.
- Do not commit passwords.
- Leave actuator, containers, and pipelines out until the readiness blockers are accepted.
