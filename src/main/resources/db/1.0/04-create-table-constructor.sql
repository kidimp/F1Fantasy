/*======================================*/
/* Table: constructor                   */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor (
    constructor_id    BIGSERIAL PRIMARY KEY,
    broadcast_name    VARCHAR(255) NOT NULL,
    full_name         VARCHAR(255) NOT NULL,
    name_acronym      VARCHAR(3) NOT NULL,
    logo_url          VARCHAR NOT NULL,
    country_code      VARCHAR(3) NOT NULL,
    country_full_name VARCHAR(255) NOT NULL,
    base              VARCHAR(255) NOT NULL,
    team_colour       VARCHAR(7) NOT NULL
);
