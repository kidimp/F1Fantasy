/*======================================*/
/* Table: constructor_season            */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor_season (
    season_id      BIGSERIAL NOT NULL,
    constructor_id BIGSERIAL NOT NULL,

    PRIMARY KEY (season_id, constructor_id),
    CONSTRAINT fk_constructor_season_season FOREIGN KEY (season_id) REFERENCES season (season_id),
    CONSTRAINT fk_constructor_season_constructor FOREIGN KEY (constructor_id) REFERENCES constructor (constructor_id)
);
