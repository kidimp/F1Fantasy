/*======================================*/
/* Table: driver_grand_prix             */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver_grand_prix
(
    driver_grand_prix_id BIGSERIAL PRIMARY KEY,
    driver_id            BIGINT NOT NULL,
    grand_prix_id        BIGINT NOT NULL REFERENCES grand_prix(grand_prix_id),
    driver_constructor_season_id BIGINT NOT NULL REFERENCES driver_constructor_season(driver_constructor_season_id),

    CONSTRAINT fk_driver_constructor FOREIGN KEY (driver_constructor_season_id)
    REFERENCES driver_constructor_season(driver_constructor_season_id)
);