# Kafka Event Flow

## Topics (suggested naming)
- `payments.transfer.initiated`
- `transactions.recorded`
- `wallets.updated`
- `payments.status.updated`
- DLQ topics:
  - `payments.transfer.initiated.DLQ`
  - `transactions.recorded.DLQ`

## Event schema (envelope)
- `eventId` (UUID)
- `eventType`
- `occurredAt`
- `correlationId` (paymentId)
- `idempotencyKey`
- `payload` (service-specific)

## Producer/Consumer responsibilities
- **payment-service (producer)**
  - Emits `payments.transfer.initiated`
  - Contains paymentId, payerId, payeeId, amount, idempotencyKey

- **transaction-service (consumer)**
  - Consumes initiated events
  - Persists transaction state transitions
  - Emits `transactions.recorded` and/or `payments.status.updated`

- **wallet-service (consumer/producer)**
  - Consumes wallet update instructions (often via transactions events)
  - Updates balances (reserve/commit)
  - Emits `wallets.updated`

- **notification-service (consumer)**
  - Consumes `payments.status.updated`
  - Sends user notifications

## Retry + DLQ
- Consumer uses retry with backoff
- After max attempts, the message is forwarded to DLQ topic
- DLQ handler can re-drive events after inspection


