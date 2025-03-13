/*======================================*/
/* Table: grand_prix                    */
/*======================================*/

CREATE TABLE IF NOT EXISTS grand_prix (
    grand_prix_id         BIGINT PRIMARY KEY,
    circuit_key           INTEGER NOT NULL,
    season_id             BIGINT NOT NULL,
    date_start            TIMESTAMP NOT NULL,
    gmt_offset            VARCHAR(8) NOT NULL,
    meeting_key           INTEGER NOT NULL,
    meeting_name          VARCHAR(255) NOT NULL,
    meeting_official_name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_grand_prix_circuit FOREIGN KEY (circuit_key) REFERENCES circuit (circuit_key),
    CONSTRAINT fk_grand_prix_season FOREIGN KEY (season_id) REFERENCES season (season_id),
    CONSTRAINT uq_grand_prix UNIQUE (circuit_key, season_id)
);
