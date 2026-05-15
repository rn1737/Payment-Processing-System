# PayFlow TODO (execution checklist)

## Phase 1 — Build baseline (Maven + modules)
- [ ] Verify/extend `PayFlow/payflow-parent/pom.xml` for complete dependency/plugin management.
- [ ] Audit each service `pom.xml` for missing enterprise dependencies (Actuator, validation, security, Jackson, retry).
- [ ] Ensure all modules build with Java 17.

## Phase 2 — Add missing platform services
- [ ] Add `discovery-server` module (Eureka server).
- [ ] Add `config-server` module (Spring Cloud Config).
- [ ] Wire each microservice to Eureka + Config.
- [ ] Update `PayFlow/docker-compose.yml` to include the new servers.

## Phase 3 — Implement shared `common` module
- [ ] Add API response standardization (ApiResponse + error model).
- [ ] Add base/global exception handling helpers.
- [ ] Add Kafka event envelope + DTO mapping helpers.
- [ ] Implement Redis-backed idempotency repository/util.
- [ ] Add JWT utility shared code.

## Phase 4 — Implement microservices code
- [ ] api-gateway: Spring Cloud Gateway routes, JWT validation, rate limiting, correlation id.
- [ ] auth-service: register/login JWT issuance, RBAC, user profile APIs.
- [ ] wallet-service: account + balance adjustments with idempotency, publishes `wallets.updated`.
- [ ] payment-service: validate + publish `payments.transfer.initiated`, status workflow.
- [ ] transaction-service: persistence + idempotency + state machine, publishes status updates.
- [ ] notification-service: consumes status events, async notifications.

## Phase 5 — Kafka reliability
- [ ] Configure Kafka topic names + DLQ topics.
- [ ] Add consumer retries with backoff + DLQ publishing.
- [ ] Implement event dedupe/idempotency for consumers.

## Phase 6 — Docker hardening
- [ ] Replace placeholder Dockerfiles with multi-stage Maven build + `java -jar` runtime.
- [ ] Ensure all service containers expose correct ports and start commands.

## Phase 7 — Observability
- [ ] Structured logging with correlation id across services.
- [ ] Actuator health endpoints per service.
- [ ] Optional: centralized logging stack (Loki/ELK) if desired.

## Phase 8 — Swagger + docs + test data
- [ ] Swagger/OpenAPI configuration for each service.
- [ ] Update `PayFlow/README.md` with endpoints + API flow + Kafka workflow.
- [ ] Add sample test data (curl commands) for JWT + payment initiation.

## Phase 9 — CI/CD
- [ ] Add GitHub Actions workflow (build/test + docker build).
- [ ] (Optional) Add Jenkinsfile alternative.

## Validation
- [ ] `docker compose up --build` succeeds.
- [ ] Swagger endpoints reachable through gateway.
- [ ] End-to-end payment event flow works (initiate → events → transaction → notification).

