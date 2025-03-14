/*======================================*/
/* Table: driver                        */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver (
    driver_id      BIGSERIAL PRIMARY KEY,
    broadcast_name VARCHAR(255),
    first_name     VARCHAR(255) NOT NULL,
    last_name      VARCHAR(255) NOT NULL,
    full_name      VARCHAR(255) NOT NULL,
    name_acronym   VARCHAR(3),
    country_code   VARCHAR(3) NOT NULL,
    driver_number  INTEGER UNIQUE NOT NULL,
    headshot_url   VARCHAR
);
