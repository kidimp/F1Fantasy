/*======================================*/
/* Table: circuit                       */
/*======================================*/

CREATE TABLE IF NOT EXISTS circuit
(
    circuit_key        INTEGER PRIMARY KEY,
    circuit_short_name VARCHAR(255) NOT NULL,
    country_code       VARCHAR(3) NOT NULL,
    country_key        INTEGER NOT NULL,
    country_name       VARCHAR(255) NOT NULL,
    location           VARCHAR(255) NOT NULL
);
