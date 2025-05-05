CREATE TABLE license (
    id BIGSERIAL PRIMARY KEY,
    organization_id BIGINT NOT NULL,
    max_cameras INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status TEXT CHECK (status IN ('ACTIVE', 'INACTIVE', 'EXPIRED')),
    license_key TEXT NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organization(id)
);