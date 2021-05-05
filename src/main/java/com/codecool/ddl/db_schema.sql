create table if not exists player
(
    id          serial            not null
        constraint player_pkey
            primary key,
    player_name text              not null,
    hp          integer           not null,
    x           integer           not null,
    y           integer           not null,
    defense     integer default 0 not null,
    attack      integer default 0 not null
);

alter table player
    owner to luq;

create table if not exists item
(
    id   serial      not null
        constraint item_pk
            primary key,
    type varchar(50) not null
);

create table if not exists inventory
(
    id        serial  not null
        constraint inventory_pk
            primary key,
    player_id integer not null
        constraint player_id_fk
            references player
            on update cascade on delete cascade,
    type      integer not null
        constraint inventory_item_type_fk
            references item
            on update cascade on delete cascade,
    value     real
);

create table if not exists monster
(
    id         serial      not null
        constraint monster_pk
            primary key,
    type       varchar(20) not null,
    player_id  integer     not null
        constraint monster_player_id_fk
            references player
            on update cascade on delete cascade,
    hp         integer     not null,
    x          integer     not null,
    y          integer     not null,
    defense    integer     not null,
    attack     integer     not null,
    item_type  integer     not null
        constraint monster_item_type_fk
            references item
            on update cascade on delete cascade,
    item_value real
);

create table if not exists game_state
(
    id          serial                              not null
        constraint game_state_pkey
            primary key,
    current_map varchar(50)                         not null,
    saved_at    timestamp default CURRENT_TIMESTAMP not null,
    player_id   integer                             not null
        constraint state_player_id_fk
            references player
            on update cascade on delete cascade,
    save_name   varchar(20)                         not null
);
