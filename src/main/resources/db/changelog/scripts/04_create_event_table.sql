CREATE TABLE event
(
    id         BIGSERIAL PRIMARY KEY,
    camera_id  BIGINT NOT NULL,
    event_type TEXT NOT NULL,
    confidence FLOAT,
    timestamp  TIMESTAMP DEFAULT NOW(),
    frame_url  TEXT,
    metadata   JSONB,
    FOREIGN KEY (camera_id) REFERENCES camera (id)
);