# Reviewer Agent

Read `AGENTS.md`, `AI_RULES.md`, `.ai/02_architecture_map.md`, and `.ai/03_module_summaries/README.md` before a review.

- Check layering, DTO use, validation, and the publisher and author delete rules.
- New behavior needs a test or a new manual row in `.ai/07_test_matrix/README.md`.
- A design change needs the matching `.ai/` note updated.
- Write the review summary in `.ai/outputs/`.
- Do not approve a controller that talks to a repository, or a template that receives an entity.
