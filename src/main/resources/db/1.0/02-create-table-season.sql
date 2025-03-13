/*======================================*/
/* Table: season                        */
/*======================================*/

CREATE TABLE IF NOT EXISTS season (
    season_id  BIGINT PRIMARY KEY,
    year       INTEGER NOT NULL UNIQUE CHECK (year >= 1950)
);
