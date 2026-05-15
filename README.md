# PayFlow — Distributed Payment Processing System

A production-grade, fintech-inspired backend simulation built with **Java 17 + Spring Boot + microservices + Kafka + PostgreSQL/MongoDB/Redis + JWT + Docker Compose**.

> Note: This repo is scaffolded to demonstrate architecture, event-driven flow, reliability patterns (retries, DLQ, idempotency), and enterprise-quality code structure.

## Services
- **api-gateway**: Spring Cloud Gateway entrypoint + routing + rate limiting
- **auth-service**: JWT auth, RBAC, user/account credentials
- **wallet-service**: wallet/account creation, balance adjustments with idempotency
- **payment-service**: validates payment requests, performs fraud checks, publishes events
- **transaction-service**: persists transaction states, enforces idempotency, saga/async workflow
- **notification-service**: sends real-time notifications (Web/SSE placeholder) and consumes events

## High-level payment flow
1. User logs in (JWT issued).
2. Wallet/account created.
3. User initiates payment.
4. Payment service validates balance + fraud + daily limits.
5. Payment event published to Kafka.
6. Transaction service stores records + transitions status.
7. Wallet/service performs balance reservation/commit (async).
8. Notification service consumes updates and emits confirmation.
9. Payment status updates asynchronously.

## Running locally (Docker)
1. Ensure Docker + Docker Compose are installed.
2. Start infrastructure and services:
   ```bash
   cd PayFlow
   docker compose up --build
   ```
3. Access:
   - Gateway: `http://localhost:8080`
   - Swagger (via gateway): `/auth-service/v3/api-docs`, etc.

## Architecture docs
- `docs/architecture.md`
- `docs/api-flow-diagrams.md`
- `docs/kafka-event-flow.md`
- `docs/interview-points.md`

