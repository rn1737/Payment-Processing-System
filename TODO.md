# PayFlow Backend Project - TODO

## Progress
- [x] Project root: `/Users/riteshranka/Desktop/PayFlow`
- [x] Parent multi-module structure created under `PayFlow/payflow-parent`
- [x] Maven wrapper files added (mvnw, mvnw.cmd, wrapper properties)
- [x] Accidental placeholder file removed
- [x] README + architecture docs created (`docs/`)
- [x] Docker Compose + placeholder Dockerfiles scaffolded
- [x] Common module created + shared idempotency value object scaffolded
- [x] Per-service Maven module `pom.xml`s scaffolded

## Remaining (core implementation)
- [ ] Add full parent/build config (dependency BOM, plugin mgmt, compiler/source settings) (partially present; verify completeness)
- [ ] Create `common` module utilities (DTOs, exceptions, Kafka event envelope, idempotency contracts, security shared utils)

- [ ] Implement each microservice skeleton:
  - [ ] api-gateway (routes + rate limiting)
  - [ ] auth-service (JWT issuance + RBAC)
  - [ ] wallet-service (accounts + balances + redis cache)
  - [ ] payment-service (fraud rules + daily limits + publish events)
  - [ ] transaction-service (state machine + postgres + idempotency)
  - [ ] notification-service (consume + notify)
- [ ] Add real (non-placeholder) service Dockerfiles/entrypoints
- [ ] Add CI workflow `.github/workflows/ci.yml`
- [ ] Add Swagger/OpenAPI configuration per service
- [ ] Add centralized exception handling + validation
- [ ] Add DLQ + retry config for Kafka consumers
- [ ] Add distributed tracing/logging scaffolding (logs + correlation id)
- [ ] Add API flow diagrams + Kafka event flow + interview explanation docs (already scaffolded under docs/)

## Run/Validation Notes
- Local build/run requires Java 17 runtime (currently missing in this environment).
- After code generation, builds will validate in CI and via Docker.

