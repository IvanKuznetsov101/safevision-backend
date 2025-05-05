CREATE TABLE organization
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    email      TEXT,
    phone      TEXT,
    address    TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    is_active  BOOLEAN   DEFAULT TRUE
);