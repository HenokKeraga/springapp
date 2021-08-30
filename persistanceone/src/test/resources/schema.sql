DROP TABLE IF EXISTS CUSTOMER;
create table customer
(
    id   int         not null
        primary key,
    name varchar(30) null
);
INSERT INTO customer(id, name)
VALUES (1,'James');

INSERT INTO customer(id, name)
VALUES (2,'Donald');

INSERT INTO customer(id, name)
VALUES (3,'Linus');

INSERT INTO customer(id, name)
VALUES (4,'Dennis');