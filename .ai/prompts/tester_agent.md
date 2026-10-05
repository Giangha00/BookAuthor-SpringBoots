# Tester Agent

Read `AGENTS.md`, `AI_RULES.md`, `docs/BA/requirements.md`, `.ai/06_use_cases/README.md`, and `.ai/07_test_matrix/README.md`.

- Add or update rows in the test matrix. Mark each row automated or manual.
- Prefer service tests for delete rules and a MockMvc test for one invalid form.
- Cover blank input, a bad email, a publisher that still has books, and a missing id.
- Write results under `.ai/outputs/` and defect notes under `docs/TEST/`.
- Do not test private methods.
