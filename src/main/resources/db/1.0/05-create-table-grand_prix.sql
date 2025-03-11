/*======================================*/
/* Table: grand_prix                    */
/*======================================*/

CREATE TABLE IF NOT EXISTS grand_prix
(
    grand_prix_id         BIGSERIAL PRIMARY KEY,
    season_id             BIGINT NOT NULL REFERENCES season(season_id),
    circuit_key           INTEGER NOT NULL REFERENCES circuit(circuit_key),
    date_start            TIMESTAMP NOT NULL,
    gmt_offset            VARCHAR(8) NOT NULL,
    meeting_key           INTEGER NOT NULL,
    meeting_name          VARCHAR(255) NOT NULL,
    meeting_official_name VARCHAR(255) NOT NULL
);
