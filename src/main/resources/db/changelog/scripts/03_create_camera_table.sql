CREATE TABLE camera
(
    id              BIGSERIAL PRIMARY KEY,
    organization_id BIGINT NOT NULL,
    name            TEXT NOT NULL,
    rtsp_url        TEXT NOT NULL,
    location        TEXT,
    is_active       BOOLEAN   DEFAULT TRUE,
    created_at      TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (organization_id) REFERENCES organization (id)
);