/*======================================*/
/* Table: season                        */
/*======================================*/

CREATE TABLE IF NOT EXISTS season
(
    season_id   BIGSERIAL NOT NULL,   -- Уникальный идентификатор сезона
    year        INTEGER NOT NULL,     -- Год сезона

    CONSTRAINT pk_season PRIMARY KEY (season_id),
    CONSTRAINT uq_season_year UNIQUE (year) -- Год должен быть уникальным
);
