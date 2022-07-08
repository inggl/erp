create table if not exists archive
(
    archive_id    uuid
        constraint archive_pk
            primary key,
    title              varchar(50),
    message            varchar(100),
    created_by         varchar(100),
    created_date       timestamp,
    last_modified_by   varchar(100),
    last_modified_date timestamp
);

