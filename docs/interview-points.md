# Interview Explanation Points (PayFlow)

## Distributed Systems & Microservices
- Clear separation of services by bounded context:
  - Auth (identity + RBAC)
  - Wallet (balances + accounts)
  - Payment (validation, fraud, publishing)
  - Transaction (state machine + persistence)
  - Notification (event consumption)
- API Gateway provides external-facing control (routing, rate limits).

## Event-driven architecture
- Kafka topics decouple services.
- Services react asynchronously to state changes.
- Correlation IDs link events across the workflow.

## Saga / Async transaction handling
- Avoids distributed synchronous DB transactions.
- Payment workflow uses a state machine:
  - Initiated -> Reserved/Recorded -> Succeeded/Failed -> Compensated/Rollback
- Rollback is handled via compensating events.

## Idempotency
- Every payment/command includes an `idempotencyKey`.
- Transaction service persists processed keys to prevent duplicate payments.
- Wallet service enforces idempotency for balance adjustments.

## Reliability: retries, DLQ, failure recovery
- Consumers use exponential backoff retry for transient errors.
- Non-recoverable errors go to DLQ.
- DLQ messages can be replayed after fixing root causes.

## Fraud detection & risk controls
- Rules engine checks:
  - amount thresholds
  - velocity / frequency
  - suspicious patterns (placeholder)
- Daily transaction limit enforcement.

## Security
- JWT auth.
- Role-based access control: `USER`, `ADMIN`.
- Centralized exception handling for consistent error responses.

## Observability
- Structured logs with correlation IDs.
- Tracing hooks (OpenTelemetry-ready scaffold).

## Scalability
- Stateless microservices behind gateway.
- Kafka handles bursts; consumers scale horizontally.
- Redis caching for hot reads and rate limiting counters.


