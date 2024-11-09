/*======================================*/
/* Table: constructor                   */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor
(
    constructor_id    bigserial     NOT NULL,  -- Уникальный идентификатор конструктора
    broadcast_name    varchar(256)  NOT NULL,  -- Название конструктора в трансляциях
    full_name         varchar(256)  NOT NULL,  -- Полное название конструктора
    logo_url          varchar(256)  NOT NULL,  -- Ссылка на логотип конструктора
    country_code      varchar(3)    NOT NULL,  -- Код страны
    country_full_name varchar(256)  NOT NULL,  -- Полное название страны
    base              varchar(256)  NOT NULL,  -- Местоположение базы конструктора
    name_acronym      varchar(3)    NOT NULL,  -- Аббревиатура названия конструктора
    team_colour       varchar(7)    NOT NULL,  -- Цвет конструктора (в шестнадцатеричном формате)
    cost              decimal(10, 2),          -- Стоимость конструктора
    CONSTRAINT pk_constructor PRIMARY KEY (constructor_id)
);

/*======================================*/
/* Table: constructor_drivers           */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor_drivers
(
    constructor_id   bigint NOT NULL,         -- ID конструктора
    driver_id        bigint NOT NULL,         -- ID гонщика
    CONSTRAINT pk_constructor_drivers PRIMARY KEY (constructor_id, driver_id),
    CONSTRAINT fk_constructor FOREIGN KEY (constructor_id) REFERENCES constructor (constructor_id) ON DELETE CASCADE,
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES driver (driver_id) ON DELETE CASCADE
);
