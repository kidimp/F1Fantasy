/*======================================*/
/* Table: driver_constructor_season     */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver_constructor_season
(
    driver_constructor_season_id BIGSERIAL PRIMARY KEY,
    driver_id            BIGINT NOT NULL REFERENCES driver(driver_id),
    constructor_season_id BIGINT NOT NULL REFERENCES constructor_season(constructor_season_id),
    is_main              BOOLEAN NOT NULL,

    CONSTRAINT uq_driver_constructor UNIQUE (driver_id, constructor_season_id)
);
