insert into address (id, address, zip, created_by, created_date, last_modified_by, last_modified_date, version)
values ('7da79912-978a-4754-9cb6-c16f8a44f6ea', 'Grzybowska 62, Warszawa, Poland', '00855', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1),
       ('08dd7158-2a5c-4389-a5b8-f8bf0c90e2f6', 'Kpt. M. Medweckiego 1, Balice, Poland', '32083', 'SYSTEM', '2022-02-15 14:43:22.310444', null, now(), 1),
       ('8b0a9d7b-9910-484f-9675-4187fad69f8d', 'Pogodna 2, Wroclaw, Poland', '51188', 'SYSTEM', '2022-02-15 14:43:22.310444', null, now(), 1),
       ('8a20a8ad-2faa-4650-b831-e19a36413590', 'Wolności 90, Ożarowice, Poland', '42625', 'SYSTEM', '2022-02-15 14:43:22.310444', null, now(), 1);

insert into customer (id, name, address_id, created_by, created_date, last_modified_by, last_modified_date, version)
values ('d43366ab-9661-456a-a1da-0cccccda9429', 'John Paul II Krakow-Balice International Airport Ltd.', '08dd7158-2a5c-4389-a5b8-f8bf0c90e2f6', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1),
       ('ac9ef628-d30a-4abc-a337-ac6381947225', 'Katowice International Airport ', '8a20a8ad-2faa-4650-b831-e19a36413590', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1);

insert into supplier (id, name, created_by, created_date, last_modified_by, last_modified_date, address_id, version)
values ('940dd39d-8b56-4647-bfbe-2f4de8435464', 'Castrol Polska', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, '7da79912-978a-4754-9cb6-c16f8a44f6ea', 1),
       ('b663d156-6b18-48b4-be92-2b5e63c9a1ed', 'MAHLE GmbH', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, '8b0a9d7b-9910-484f-9675-4187fad69f8d', 1);

insert into product (id, name, description, created_by, created_date, last_modified_by, last_modified_date, version)
values ('be1b1d7b-b5f4-4875-98e9-bc710f735237', 'Engine Oil', 'Full synthetic engine oil', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1),
       ('0cbd2e7d-19ff-47a9-83e9-d9c3cf877c3d', 'Air Filter', 'Set Air Filters', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1);

insert into supplier_product (supplier_id, product_id, quantity, unit_price, created_by, created_date, last_modified_by, last_modified_date, version)
values ('940dd39d-8b56-4647-bfbe-2f4de8435464', 'be1b1d7b-b5f4-4875-98e9-bc710f735237', 2, 17.5, 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1),
       ('b663d156-6b18-48b4-be92-2b5e63c9a1ed', '0cbd2e7d-19ff-47a9-83e9-d9c3cf877c3d', 5, 23.5, 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1);

insert into "order" (id, supplier_id, status, created_by, created_date, last_modified_by, last_modified_date, version)
values ('5c0676de-9396-4a16-8c65-c1cdd47bee20', '940dd39d-8b56-4647-bfbe-2f4de8435464', 'COMPLETED', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1),
       ('64207071-b186-4d34-907c-b25f6ced5ffc', '940dd39d-8b56-4647-bfbe-2f4de8435464', 'DRAFT', 'SYSTEM', '2022-02-16 12:41:21.916343', 'SYSTEM', now(), 1),
       ('d3dfc549-c33c-40fa-984b-e2b2ba710afd', 'b663d156-6b18-48b4-be92-2b5e63c9a1ed', 'DRAFT', 'SYSTEM', '2022-02-16 12:41:21.916343', 'SYSTEM', now(), 1);

insert into order_item (order_id, product_id, created_by, created_date, last_modified_by, last_modified_date, version) values
    ('5c0676de-9396-4a16-8c65-c1cdd47bee20', 'be1b1d7b-b5f4-4875-98e9-bc710f735237', 'SYSTEM', '2022-02-15 14:43:22.310444', null, null, 1);