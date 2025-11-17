use eazybank;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT IGNORE INTO `users` VALUES ('user','{noop}password','1');
INSERT IGNORE INTO `authorities` VALUES ('user','read');

INSERT IGNORE INTO `users` VALUES ('admin','{bcrypt}$2a$12$Y2/aSSPvMf1TDYYRnRXO9.j4DTWDB4RS3bSmraidg31usKFYaKSjW','1');
INSERT IGNORE INTO `authorities` VALUES ('admin','ADMIN');

SELECT * from users;

create table `customer` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(45) NOT NULL,
    `pwd` varchar(200) NOT NULL,
    `role` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

SELECT * from customer;

INSERT INTO `customer` (email, pwd, role) VALUES ('happy@example.com','{noop}EazyBytes@12345','read');
INSERT INTO `customer` (email, pwd, role) VALUES ('admin@example.com','{bcrypt}$2a$12$Y2/aSSPvMf1TDYYRnRXO9.j4DTWDB4RS3bSmraidg31usKFYaKSjW','ADMIN');