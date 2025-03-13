Table circuit {
  circuit_key        integer [primary key]
  circuit_short_name varchar(255) [not null]
  country_code       char(3) [not null]
  country_key        integer [not null]
  country_name       varchar(255) [not null]
  location           varchar(255) [not null]
}

Table grand_prix {
  grand_prix_id         integer [primary key]
  circuit_key           integer [not null]
  season_id             integer [not null]
  date_start            datetime [not null]
  gmt_offset            varchar(8) [not null]
  meeting_key           integer [not null]
  meeting_name          varchar(255) [not null]
  meeting_official_name varchar(255) [not null]

  Note: "Unique constraint (circuit_key, season_id) should be handled in DB."
}

Table season {
  season_id integer [primary key]
  year      integer [not null, unique]
}

Table driver {
  driver_id     integer [primary key]
  broadcast_name varchar(255) [not null]
  first_name     varchar(255) [not null]
  last_name      varchar(255) [not null]
  full_name      varchar(255) [not null]
  name_acronym   char(3) [not null]
  country_code   char(3) [not null]
  driver_number  integer [not null, unique]
  headshot_url   varchar(255)
}

Table constructor {
  constructor_id    integer [primary key]
  broadcast_name    varchar(255) [not null]
  full_name         varchar(255) [not null]
  name_acronym      char(3) [not null]
  logo_url          varchar(255) [not null]
  country_code      char(3) [not null]
  country_full_name varchar(255) [not null]
  base             varchar(255) [not null]
  team_colour      char(7) [not null]
}

Table constructor_season {
  season_id      integer [not null]
  constructor_id integer [not null]

  Primary Key (season_id, constructor_id)
}

Table driver_constructor {
  driver_id      integer [not null]
  constructor_id integer [not null]

  Primary Key (driver_id, constructor_id)
}

Table driver_grand_prix {
  driver_id     integer [not null]
  grand_prix_id integer [not null]

  Primary Key (driver_id, grand_prix_id)
}

Ref: grand_prix.circuit_key > circuit.circuit_key
Ref: grand_prix.season_id > season.season_id
Ref: constructor_season.season_id > season.season_id
Ref: constructor_season.constructor_id > constructor.constructor_id
Ref: driver_constructor.driver_id > driver.driver_id
Ref: driver_constructor.constructor_id > constructor.constructor_id
Ref: driver_grand_prix.driver_id > driver.driver_id
Ref: driver_grand_prix.grand_prix_id > grand_prix.grand_prix_id
------------------------------------------------------------------------------------------------------------------------
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
------------------------------------------------------------------------------------------------------------------------
Table circuit {
  circuit_key integer [pk]
  circuit_short_name varchar(255)
  country_code varchar(3)
  country_key integer
  country_name varchar(255)
  location varchar(255)
}

Table constructor {
  constructor_id bigint [pk, increment]
  broadcast_name varchar
  full_name varchar
  name_acronym varchar
  logo_url varchar
  country_code varchar
  country_full_name varchar
  base varchar
  team_colour varchar
}

Table driver {
  driver_id bigint [pk, increment]
  broadcast_name varchar
  first_name varchar
  last_name varchar
  full_name varchar
  name_acronym varchar(3)
  country_code varchar(3)
  driver_number integer
  headshot_url varchar
  constructor_id bigint
}

Table grand_prix {
  grand_prix_id bigint [pk, increment]
  season_id bigint
  circuit_key integer
  date_start datetime
  gmt_offset varchar(8)
  meeting_key integer
  meeting_name varchar(255)
  meeting_official_name varchar(255)
}

Table season {
  season_id bigint [pk, increment]
  year integer [unique]
}

Table constructor_season {
  season_id bigint
  constructor_id bigint
  indexes {
    (season_id, constructor_id) [unique]
  }
}

Table driver_grand_prix {
  driver_id bigint
  grand_prix_id bigint
  indexes {
    (driver_id, grand_prix_id) [unique]
  }
}

Ref: driver.constructor_id > constructor.constructor_id
Ref: grand_prix.season_id > season.season_id
Ref: grand_prix.circuit_key > circuit.circuit_key
Ref: constructor_season.season_id > season.season_id
Ref: constructor_season.constructor_id > constructor.constructor_id
Ref: driver_grand_prix.driver_id > driver.driver_id
Ref: driver_grand_prix.grand_prix_id > grand_prix.grand_prix_id

