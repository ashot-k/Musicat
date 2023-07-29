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
    username  varchar(50) NOT NULL,
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

///////////////////////////////////////


CREATE TABLE `authorities`
(
    `username`  varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  COLLATE = latin1_swedish_ci;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`)
VALUES ('ashot', 'ROLE_ADMIN'),
       ('ashot', 'ROLE_CUSTOMER'),
       ('jason', 'ROLE_CUSTOMER'),
       ('susan', 'ROLE_CUSTOMER');


CREATE TABLE `images`
(
    `id`         bigint(20) NOT NULL,
    `imagedata`  varchar(255) DEFAULT NULL,
    `product_id` bigint(20)   DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `inventory`
(
    `id`       bigint(20) NOT NULL,
    `quantity` int(11)    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `quantity`)
VALUES (1, 23),
       (2, 24),
       (3, 2),
       (4, 4),
       (5, 45);



CREATE TABLE `local_user`
(
    `id`         bigint(20)    NOT NULL,
    `first_name` varchar(255)  NOT NULL,
    `last_name`  varchar(255)  NOT NULL,
    `passwrd`    varchar(1000) NOT NULL,
    `username`   text          NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `order_details`
(
    `id`         bigint(20) NOT NULL,
    `fk_user_id` bigint(20) NOT NULL,
    `payment_id` bigint(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
CREATE TABLE `order_items`
(
    `id`         bigint(20) NOT NULL,
    `quantity`   int(11)    NOT NULL,
    `order_id`   bigint(20) NOT NULL,
    `product_id` bigint(20) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
CREATE TABLE `payment_details`
(
    `id`       bigint(20) NOT NULL,
    `amount`   double     NOT NULL,
    `order_id` bigint(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `product`
(
    `id`              bigint(20)    NOT NULL,
    `artist`          varchar(255)  NOT NULL,
    `description`     varchar(1000) NOT NULL,
    `genre`           varchar(40)   NOT NULL,
    `name`            varchar(255)  NOT NULL,
    `price`           double        NOT NULL,
    `year`            int(11)       NOT NULL,
    `fk_inventory_id` bigint(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `artist`, `description`, `genre`, `name`, `price`, `year`, `fk_inventory_id`)
VALUES (1, 'Tool', 'Ænima is the second studio album by American rock band Tool.', 'Alternative Metal', 'Ænima', 45,
        1996, 1),
       (2, 'Tool', 'Lateralus is the third studio album by American rock band Tool.', 'Alternative Metal', 'Lateralus',
        30, 2001, 2),
       (3, 'Blind Guardian',
        'Imaginations from the Other Side is the fifth studio album by German power metal band Blind Guardian.',
        'Power Metal', 'Imaginations from the Other Side', 25, 1995, 3),
       (4, 'Opeth', 'Damnation is the seventh studio album by Swedish progressive metal band Opeth.',
        'Progressive Rock', 'Damnation', 30, 2003, 4),
       (14, 'Black Sabbath', 'Master Of Reality is the third studio album by British band Black Sabbath', 'Heavy Metal',
        'Master Of Reality ', 55, 1971, 5);


CREATE TABLE `users`
(
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `enabled`  tinyint(4)  NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1
  COLLATE = latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`)
VALUES ('ashot', '{noop}5646', 1),
       ('jason', '{noop}1234', 1),
       ('susan', '{noop}sussy', 1);



CREATE TABLE `user_address`
(
    `id`             bigint(20)   NOT NULL,
    `address_line_1` varchar(255) NOT NULL,
    `address_line_2` varchar(255) DEFAULT NULL,
    `city`           varchar(255) NOT NULL,
    `country`        varchar(80)  NOT NULL,
    `postal_code`    int(11)      NOT NULL,
    `user_id`        bigint(20)   DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
CREATE TABLE `user_payment`
(
    `id`             bigint(20)  NOT NULL,
    `account_number` bigint(20)  NOT NULL,
    `type`           varchar(15) NOT NULL,
    `user_id`        bigint(20)  NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


ALTER TABLE `images`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_jvinkc7xcd0x0pk49c1me6hb6` (`product_id`);
ALTER TABLE `inventory`
    ADD PRIMARY KEY (`id`);
ALTER TABLE `local_user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_93d93k106ik2383youkc9bixl` (`username`) USING HASH;
ALTER TABLE `order_details`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_k8iuy7qvocndeisnqjms9c82d` (`payment_id`),
    ADD KEY `FK22470nsqgceouhmgf1tpgxui7` (`fk_user_id`);
ALTER TABLE `order_items`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKfaco7kgw6uoepp39m74cy7j6o` (`order_id`),
    ADD KEY `FKlf6f9q956mt144wiv6p1yko16` (`product_id`);
ALTER TABLE `payment_details`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `UK_of2hvjrt3h42uja5aibie81tp` (`order_id`);
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `unique_product_name` (`name`),
    ADD UNIQUE KEY `UK_6usmrien8b8s6utl0sjuil6lg` (`fk_inventory_id`);
ALTER TABLE `user_address`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FK1h2ccyqwrv3l9sdgdiceutii1` (`user_id`);
ALTER TABLE `user_payment`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKkugnevoqb5xiqktiy0grou3rj` (`user_id`);
ALTER TABLE `images`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `inventory`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 49;
ALTER TABLE `local_user`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `order_details`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `order_items`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `payment_details`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 37;
ALTER TABLE `user_address`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `user_payment`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE `images`
    ADD CONSTRAINT `FK8sfun6tj1hqb85ed52o8mkqyh` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
ALTER TABLE `order_details`
    ADD CONSTRAINT `FK22470nsqgceouhmgf1tpgxui7` FOREIGN KEY (`fk_user_id`) REFERENCES `local_user` (`id`),
    ADD CONSTRAINT `FKe2t35c1i6f93nhvxbru3wy4s5` FOREIGN KEY (`payment_id`) REFERENCES `payment_details` (`id`);
ALTER TABLE `order_items`
    ADD CONSTRAINT `FKfaco7kgw6uoepp39m74cy7j6o` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`id`),
    ADD CONSTRAINT `FKlf6f9q956mt144wiv6p1yko16` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
ALTER TABLE `payment_details`
    ADD CONSTRAINT `FKqnay946png0id4ie8oxe6ec65` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`id`);
ALTER TABLE `product`
    ADD CONSTRAINT `FKmurxufobr1g1qojpqkrqduk6n` FOREIGN KEY (`fk_inventory_id`) REFERENCES `inventory` (`id`);
ALTER TABLE `user_address`
    ADD CONSTRAINT `FK1h2ccyqwrv3l9sdgdiceutii1` FOREIGN KEY (`user_id`) REFERENCES `local_user` (`id`);
ALTER TABLE `user_payment`
    ADD CONSTRAINT `FKkugnevoqb5xiqktiy0grou3rj` FOREIGN KEY (`user_id`) REFERENCES `local_user` (`id`);

COMMIT;


