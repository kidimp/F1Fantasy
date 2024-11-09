/*======================================*/
/* Table: driver                        */
/*======================================*/

CREATE TABLE IF NOT EXISTS driver
(
    driver_id       bigserial       NOT NULL, -- Уникальный идентификатор водителя
    broadcast_name  varchar(256),             -- Имя водителя в трансляции
    country_code    varchar(3),               -- Код страны (3 символа)
    driver_number   integer,                  -- Номер водителя
    first_name      varchar(256),             -- Имя водителя
    full_name       varchar(256),             -- Полное имя водителя
    headshot_url    varchar(512),             -- URL на фото водителя
    last_name       varchar(256),             -- Фамилия водителя
    meeting_key     integer,                  -- Ключ встречи
    name_acronym    varchar(3),               -- Аббревиатура имени водителя (3 символа)
    session_key     integer,                  -- Ключ сессии
    team_colour     varchar(7),               -- Цвет команды (в шестнадцатеричном формате)
    team_name       varchar(256),             -- Название команды
    cost            decimal(10, 2),           -- Стоимость водителя

    CONSTRAINT pk_driver PRIMARY KEY (driver_id)
);
