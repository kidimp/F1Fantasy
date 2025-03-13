/*======================================*/
/* Table: driver_grand_prix             */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver_grand_prix (
    driver_id      BIGINT NOT NULL,
    grand_prix_id  BIGINT NOT NULL,

    PRIMARY KEY (driver_id, grand_prix_id),
    CONSTRAINT fk_driver_grand_prix_driver FOREIGN KEY (driver_id) REFERENCES driver (driver_id),
    CONSTRAINT fk_driver_grand_prix_grand_prix FOREIGN KEY (grand_prix_id) REFERENCES grand_prix (grand_prix_id)
);