/*======================================*/
/* Table: driver                        */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver
(
    driver_id       BIGSERIAL NOT NULL,     -- Уникальный идентификатор пилота
    broadcast_name  VARCHAR(256) NOT NULL,  -- Имя пилота в трансляции
    first_name      VARCHAR(256) NOT NULL,  -- Имя пилота
    last_name       VARCHAR(256) NOT NULL,  -- Фамилия пилота
    full_name       VARCHAR(256) NOT NULL,  -- Полное имя пилота
    name_acronym    VARCHAR(3) NOT NULL,    -- Аббревиатура имени пилота (3 символа)
    country_code    VARCHAR(3) NOT NULL,    -- Код страны (3 символа)
    driver_number   INTEGER NOT NULL,       -- Номер пилота
    headshot_url    VARCHAR(512) NOT NULL,  -- URL на фото пилота

    CONSTRAINT pk_driver PRIMARY KEY (driver_id)
);
