create database if not exists travel_agency;

use travel_agency;

create table if not exists `hotels` (
    `id` int primary key auto_increment,
    `name` varchar(100) not null,
    `description` varchar(200) not null,
    `country` varchar(30) not null
);

create table if not exists `rooms` (
  `id` int primary key auto_increment,
  `room_number` int not null,
  `hotel_id` bigint not null,
   foreign key (hotel_id)
      references hotels(`id`)
        on update restrict on delete cascade
);

create table if not exists `users` (
  `id` int primary key  auto_increment,
  `email` varchar(100) not null unique,
  `name` varchar(200) not null,
  `password` varchar(200) not null
);

create table if not exists `roles` (
  `id` int primary key auto_increment,
  `name` varchar(50) not null unique
);

create table if not exists `user_roles` (
  `id` int primary key auto_increment,
  `user_id` int not null,
  `role_id` int not null,
        foreign key (user_id)
            references users(`id`)
            on update restrict on delete cascade,
        foreign key (role_id)
            references roles(`id`)
            on update restrict on delete cascade
);

create table if not exists `countries` (
    `id` int primary key auto_increment,
    `name` varchar(100) not null
);

create table if not exists `orders` (
  `id` int primary key auto_increment,
  `customer_id` int not null,
  `room_id` int not null,
  `date_from` date not null,
  `date_till` date not null,
      foreign key (customer_id)
          references users(`id`)
          on update restrict on delete cascade,
      foreign key (room_id)
          references rooms(`id`)
          on update restrict on delete cascade
);