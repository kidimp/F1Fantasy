/*======================================*/
/* Table: constructor_season            */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor_season
(
    constructor_season_id BIGSERIAL PRIMARY KEY,
    constructor_id        BIGINT NOT NULL REFERENCES constructor(constructor_id),
    season_id             BIGINT NOT NULL REFERENCES season(season_id)
);
