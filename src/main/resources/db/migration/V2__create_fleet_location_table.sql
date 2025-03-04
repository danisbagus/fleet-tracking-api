CREATE SEQUENCE IF NOT EXISTS fleet_location_id_seq;

CREATE TABLE IF NOT EXISTS tr_fleet_location (
    id INTEGER PRIMARY KEY DEFAULT NEXTVAL('fleet_location_id_seq'),
    fleet_id INTEGER NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_fleet FOREIGN KEY (fleet_id) REFERENCES ms_fleet(id) ON DELETE CASCADE
);