CREATE SEQUENCE IF NOT EXISTS "fleet_location_id_seq";
CREATE TABLE fleet_location (
    id INT4 DEFAULT NEXTVAL('fleet_location_id_seq'),
    fleet_id INTEGER NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMPZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_fleet FOREIGN KEY (fleet_id) REFERENCES fleet(id) ON DELETE CASCADE
);