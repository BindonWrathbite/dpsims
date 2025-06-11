-- Instrument main table
CREATE TABLE instrument (
    id                  BIGSERIAL PRIMARY KEY,
    type                VARCHAR(100),
    brand               VARCHAR(100),
    serial_number       VARCHAR(100),
    inventory_number    VARCHAR(100),
    condition           VARCHAR(50),
    purchase_date       DATE,
    purchase_price      NUMERIC(10, 2),
    location            VARCHAR(100),
    assigned_student    VARCHAR(100)
);

-- Instrument repairs (embeddable)
CREATE TABLE instrument_repairs (
    instrument_id       BIGINT NOT NULL,
    date                DATE,
    note                TEXT,

    CONSTRAINT fk_instrument_repairs FOREIGN KEY (instrument_id)
        REFERENCES instrument(id) ON DELETE CASCADE
);

-- Instrument notes (embeddable)
CREATE TABLE instrument_notes (
    instrument_id       BIGINT NOT NULL,
    date                DATE,
    note                TEXT,

    CONSTRAINT fk_instrument_notes FOREIGN KEY (instrument_id)
      REFERENCES instrument(id) ON DELETE CASCADE
);
