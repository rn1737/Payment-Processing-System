package com.payflow.common;

import java.util.Objects;

/**
 * Simple value object for idempotency keys.
 * In production, enforce length/format and persist server-side.
 */
public final class IdempotencyKey {

    private final String value;

    public IdempotencyKey(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("idempotencyKey must not be blank");
        }
        this.value = value.trim();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdempotencyKey that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

