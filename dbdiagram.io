Table driver {
    driver_id       BIGINT [primary key]
    broadcast_name  VARCHAR(256) [not null]
    first_name      VARCHAR(256) [not null]
    last_name       VARCHAR(256) [not null]
    full_name       VARCHAR(256) [not null]
    name_acronym    VARCHAR(3) [not null]
    country_code    VARCHAR(3) [not null]
    driver_number   INTEGER [not null]
    headshot_url    VARCHAR(512) [not null]
}

Table constructor {
    constructor_id   BIGINT [primary key]
    broadcast_name   VARCHAR(256) [not null]
    full_name        VARCHAR(256) [not null]
    name_acronym     VARCHAR(256) [not null]
    logo_url         VARCHAR(512) [not null]
    country_code     VARCHAR(3) [not null]
    country_full_name VARCHAR(256) [not null]
    base            VARCHAR(256) [not null]
    team_colour     VARCHAR(7) [not null]
}

Table season {
    season_id   BIGINT [primary key]
    year        INTEGER [unique, not null]
}

Table constructor_season {
    constructor_season_id BIGINT [primary key]
    constructor_id        BIGINT [not null]
    season_id             BIGINT [not null]
}

Table driver_constructor_season {
    driver_constructor_season_id BIGINT [primary key]
    driver_id            BIGINT [not null]
    constructor_season_id BIGINT [not null]
    is_main              BOOLEAN [not null] // true — основной пилот, false — запасной
}

Table circuit {
    circuit_key           INTEGER [primary key]
    circuit_short_name    VARCHAR(255) [not null]
    country_code          VARCHAR(3) [not null]
    country_key           INTEGER [not null]
    country_name          VARCHAR(255) [not null]
    location              VARCHAR(255) [not null]
}

Table grand_prix {
    grand_prix_id         BIGINT [primary key]
    season_id             BIGINT [not null]
    circuit_key           INTEGER [not null, ref: > circuit.circuit_key]
    date_start            DATETIME [not null]
    gmt_offset            VARCHAR(8) [not null]
    meeting_key           INTEGER [not null]
    meeting_name          VARCHAR(255) [not null]
    meeting_official_name VARCHAR(255) [not null]
}

Table driver_grand_prix {
    driver_grand_prix_id BIGINT [primary key]
    driver_id            BIGINT [not null]
    grand_prix_id        BIGINT [not null]
    driver_constructor_season_id BIGINT [not null] // Добавляем связь с командой
}

Ref: grand_prix.season_id > season.season_id

Ref: driver_grand_prix.driver_id > driver.driver_id
Ref: driver_grand_prix.grand_prix_id > grand_prix.grand_prix_id
Ref: driver_grand_prix.driver_constructor_season_id > driver_constructor_season.driver_constructor_season_id

Ref: constructor_season.constructor_id > constructor.constructor_id
Ref: constructor_season.season_id > season.season_id

Ref: driver_constructor_season.driver_id > driver.driver_id
Ref: driver_constructor_season.constructor_season_id > constructor_season.constructor_season_id

// Проверяем, что пилот участвовал в сезоне
Ref: driver_grand_prix.driver_id > driver_constructor_season.driver_id
Ref: grand_prix.season_id > constructor_season.season_id

![](C:\Users\Chous\IdeaProjects\f1fantasy\f1fantasy_DB_scheme.png)
