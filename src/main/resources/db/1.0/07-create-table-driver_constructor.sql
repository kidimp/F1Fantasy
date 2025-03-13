/*======================================*/
/* Table: driver_constructor_season     */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver_constructor (
    driver_id      BIGINT NOT NULL,
    constructor_id BIGINT NOT NULL,

    PRIMARY KEY (driver_id, constructor_id),
    CONSTRAINT fk_driver_constructor_driver FOREIGN KEY (driver_id) REFERENCES driver (driver_id),
    CONSTRAINT fk_driver_constructor_constructor FOREIGN KEY (constructor_id) REFERENCES constructor (constructor_id)
);
