/*======================================*/
/* Table: constructor                   */
/*======================================*/

CREATE TABLE IF NOT EXISTS constructor
(
    constructor_id     BIGSERIAL    NOT NULL,       -- Уникальный идентификатор конструктора
    broadcast_name     VARCHAR(256) NOT NULL,       -- Название команды в трансляциях
    full_name          VARCHAR(256) NOT NULL,       -- Полное название команды
    name_acronym       VARCHAR(10)  NOT NULL,       -- Аббревиатура названия команды
    logo_url           VARCHAR(512) NOT NULL,       -- Ссылка на логотип команды
    country_code       VARCHAR(3)   NOT NULL,       -- Код страны (ISO 3166-1 alpha-3)
    country_full_name  VARCHAR(256) NOT NULL,       -- Полное название страны
    base               VARCHAR(256) NOT NULL,       -- База команды
    team_colour        VARCHAR(7)   NOT NULL,       -- Цвет команды (Hex-код)

    CONSTRAINT pk_constructor PRIMARY KEY (constructor_id)
);
