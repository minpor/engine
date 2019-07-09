drop table if exists license;
drop table if exists person;

CREATE TABLE if not exists license  (
    id bigserial,
    data json
);

CREATE INDEX data_idx ON license USING GIN (to_tsvector('russian', data));

create table if not exists person(
    id bigserial,
    last_name varchar(100)
);