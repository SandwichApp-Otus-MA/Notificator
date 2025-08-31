--liquibase formatted sql
--changeset AVoronov:v1.0.0/notifications

CREATE TABLE IF NOT EXISTS notifications
(
    id uuid NOT NULL PRIMARY KEY,
    user_id uuid,
    message text,
    created_at timestamp
);