create table if not exists address
(
    id         uuid         not null
        constraint address_pk
            primary key,
    address            varchar(100),
    zip                varchar(5),
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer
);

create table if not exists supplier
(
    id        uuid         not null
        constraint supplier_pk
            primary key,
    name               varchar(100),
    address_id         uuid
        constraint supplier_address_address_id_fk
            references address,
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1
);

create table if not exists customer
(
    id        uuid         not null
        constraint customer_pk
            primary key,
    name               varchar(100),
    address_id         uuid
        constraint customer_address_address_id_fk
            references address,
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1
);

create table if not exists product
(
    id         uuid         not null
        constraint product_pk
            primary key,
    name               varchar(100),
    description        varchar(250),
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1
);

create table if not exists supplier_product
(
    supplier_id        uuid         not null
        constraint supplier_product_supplier_id_fk
            references supplier,
    product_id         uuid         not null
        constraint supplier_product_product_id_fk
            references product,
    quantity    int,
    unit_price       numeric,
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1,
    constraint supplier_product_pk
        primary key (supplier_id, product_id)
);

create table if not exists "order"
(
    id  uuid         not null
        constraint order_pk
            primary key,
    supplier_id        uuid
        constraint order_supplier_supplier_id_fk
            references supplier,
    status             varchar(100),
    created_by         varchar(100) not null,
    created_date       timestamp    not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1
);

create table if not exists order_item
(
    order_id  uuid              not null
        constraint item_order_order_id_fk
            references "order",
    product_id         uuid              not null
        constraint item_product_product_id_fk
            references product,
    quantity           integer default 1 not null,
    created_by         varchar(100)      not null,
    created_date       timestamp         not null,
    last_modified_by   varchar(100),
    last_modified_date timestamp,
    version            integer default 1,
    constraint item_pk
        primary key (order_id, product_id)
);