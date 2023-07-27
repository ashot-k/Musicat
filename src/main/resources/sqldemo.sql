alter table order_details
    drop foreign key FKe2t35c1i6f93nhvxbru3wy4s5;

drop table if exists order_items;

drop table if exists payment_details;

drop table if exists order_details;

drop table if exists product;

drop table if exists inventory;

drop table if exists user_address;

drop table if exists user_payment;

drop table if exists local_user;

create table inventory
(
    id       bigint  not null auto_increment,
    quantity integer not null,
    primary key (id)
) engine = InnoDB;
create table local_user
(
    id         bigint        not null auto_increment,
    first_name varchar(255)  not null,
    last_name  varchar(255)  not null,
    passwrd    varchar(1000) not null,
    username   TEXT          not null,
    primary key (id)
) engine = InnoDB;
create table order_details
(
    id         bigint not null auto_increment,
    fk_user_id bigint not null,
    payment_id bigint,
    primary key (id)
) engine = InnoDB;
create table order_items
(
    id         bigint  not null auto_increment,
    quantity   integer not null,
    order_id   bigint  not null,
    product_id bigint  not null,
    primary key (id)
) engine = InnoDB;
create table payment_details
(
    id       bigint    not null auto_increment,
    amount   float(53) not null,
    order_id bigint,
    primary key (id)
) engine = InnoDB;
create table product
(
    id              bigint        not null auto_increment,
    artist          varchar(255)  not null,
    description     varchar(1000) not null,
    genre           varchar(40)   not null,
    name            varchar(255)  not null,
    price           float(53)     not null,
    year            integer       not null,
    fk_inventory_id bigint,
    primary key (id)
) engine = InnoDB;
create table user_address
(
    id             bigint       not null auto_increment,
    address_line_1 varchar(255) not null,
    address_line_2 varchar(255),
    city           varchar(255) not null,
    country        varchar(80)  not null,
    postal_code    integer      not null,
    user_id        bigint,
    primary key (id)
) engine = InnoDB;
create table user_payment
(
    id             bigint      not null auto_increment,
    account_number bigint      not null,
    type           varchar(15) not null,
    user_id        bigint      not null,
    primary key (id)
) engine = InnoDB;
alter table local_user
    drop index UK_93d93k106ik2383youkc9bixl;
alter table local_user
    add constraint UK_93d93k106ik2383youkc9bixl unique (username);
alter table order_details
    drop index UK_k8iuy7qvocndeisnqjms9c82d;
alter table order_details
    add constraint UK_k8iuy7qvocndeisnqjms9c82d unique (payment_id);
alter table payment_details
    drop index UK_of2hvjrt3h42uja5aibie81tp;
alter table payment_details
    add constraint UK_of2hvjrt3h42uja5aibie81tp unique (order_id);
alter table product
    drop index unique_product_name;
alter table product
    add constraint unique_product_name unique (name);
alter table product
    drop index UK_6usmrien8b8s6utl0sjuil6lg;
alter table product
    add constraint UK_6usmrien8b8s6utl0sjuil6lg unique (fk_inventory_id);
alter table order_details
    add constraint FK22470nsqgceouhmgf1tpgxui7 foreign key (fk_user_id) references local_user (id);
alter table order_details
    add constraint FKe2t35c1i6f93nhvxbru3wy4s5 foreign key (payment_id) references payment_details (id);
alter table order_items
    add constraint FKfaco7kgw6uoepp39m74cy7j6o foreign key (order_id) references order_details (id);
alter table order_items
    add constraint FKlf6f9q956mt144wiv6p1yko16 foreign key (product_id) references product (id);
alter table payment_details
    add constraint FKqnay946png0id4ie8oxe6ec65 foreign key (order_id) references order_details (id);
alter table product
    add constraint FKmurxufobr1g1qojpqkrqduk6n foreign key (fk_inventory_id) references inventory (id);
alter table user_address
    add constraint FK1h2ccyqwrv3l9sdgdiceutii1 foreign key (user_id) references local_user (id);
alter table user_payment
    add constraint FKkugnevoqb5xiqktiy0grou3rj foreign key (user_id) references local_user (id);


INSERT INTO inventory(quantity)
VALUES (23),
       (24),
       (2),
       (4),
       (45);
INSERT INTO product(name, artist, description, price, fk_inventory_id, year, genre)
VALUES ('Ænima', 'Tool',
        'Ænima is the second studio album by American rock band Tool.',
        45, 1,
        1996, 'Alternative Metal');
INSERT INTO product(name, artist, description, price, fk_inventory_id, year, genre)
VALUES ('Lateralus', 'Tool',
        'Lateralus is the third studio album by American rock band Tool.',
        30, 3,
        2001, 'Alternative Metal');
INSERT INTO product(name, artist, description, price, fk_inventory_id, year, genre)
VALUES ('Imaginations from the Other Side', 'Blind Guardian',
        'Imaginations from the Other Side is the fifth studio album by German power metal band Blind Guardian.',
        25, 5,
        1995, 'Power Metal');

INSERT INTO product(name, artist, description, price, fk_inventory_id, year, genre)
VALUES ('Master Of Reality ', 'Black Sabbath',
        'Master Of Reality is the third studio album by British band Black Sabbath.',
        55, 28,
        1971, 'Heavy Metal');






DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
     username varchar(50) NOT NULL,
     password varchar(50) NOT NULL,
     enabled  tinyint     NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

CREATE TABLE authorities
(
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


INSERT INTO users
VALUES ('ashot', '{noop}5646', 1),
       ('jason', '{noop}1234', 1),
       ('susan', '{noop}sussy', 1);

INSERT INTO authorities
VALUES ('ashot', 'ROLE_ADMIN'),
       ('ashot', 'ROLE_CUSTOMER'),
       ('jason', 'ROLE_CUSTOMER'),
       ('susan', 'ROLE_CUSTOMER');
