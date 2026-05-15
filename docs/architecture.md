# PayFlow Architecture

## Goals
- Demonstrate microservices design for payment infrastructure
- Event-driven communication with Kafka
- Fault tolerance: retry, DLQ, idempotency
- Secure APIs: JWT, RBAC, rate limiting
- Clean architecture: controller -> service -> domain -> repository

## Clean architecture layers (per service)
- **web/controller**: REST endpoints, request validation, DTO mapping
- **application/service**: business workflows (use-cases)
- **domain**: entities, value objects, enums, domain rules
- **infrastructure**: Kafka producers/consumers, JPA repositories, Redis clients
- **cross-cutting**: security, exception handling, observability, idempotency utilities

## Microservices
1. **api-gateway**
2. **auth-service**
3. **wallet-service**
4. **payment-service**
5. **transaction-service**
6. **notification-service**

## Data stores
- **PostgreSQL** (transaction history, balances, user metadata)
- **MongoDB** (audit/log style documents; e.g., fraud rules snapshots)
- **Redis** (caching, rate limiting counters, idempotency keys)

## Event-driven workflow
- Payment request produces events to Kafka
- Transaction service consumes, persists, and emits status changes
- Notification service consumes status change events

## Reliability patterns
- **Idempotency**: unique `Idempotency-Key` stored/checked
- **Retry**: exponential backoff for transient failures
- **DLQ**: failed Kafka events to `*.DLQ`
- **Saga (async)**: eventual consistency through event transitions


