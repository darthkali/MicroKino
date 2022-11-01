DROP TABLE IF EXISTS cinema;
DROP TABLE IF EXISTS cinemaHall;
DROP TABLE IF EXISTS location;

CREATE TABLE `cinema` (
    `cinema_id` int AUTO_INCREMENT  PRIMARY KEY,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `name` VARCHAR(255) NOT NULL,
    `location_id` int NOT NULL
);

CREATE TABLE `cinemaHall` (
    `cinema_hall_id` int AUTO_INCREMENT  PRIMARY KEY,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `hall_number` int NOT NULL,
    `seats` int NOT NULL,
    `cinema_id` int NOT NULL
);

CREATE TABLE `location` (
    `location_id` int AUTO_INCREMENT  PRIMARY KEY,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `city` VARCHAR(255) NOT NULL,
    `street` VARCHAR(255) NOT NULL,
    `zip` VARCHAR(255) NOT NULL,
    `house_number` VARCHAR(255) NOT NULL
);


INSERT INTO `location` ( city, street, zip, house_number)  VALUES ('Berlin', 'Kurfürstendamm', '10719', '1');
INSERT INTO `location` ( city, street, zip, house_number)  VALUES ('Köln', 'Meisterstrasse', '50483', '2');
INSERT INTO `location` ( city, street, zip, house_number)  VALUES ('Erfurt', 'Nettelbeckufer', '99086', '45');

INSERT INTO `cinema` (`name`, `location_id`) VALUES ('Cinema 1', 1);
INSERT INTO `cinema` (`name`, `location_id`) VALUES ('Cinema 2', 2);
INSERT INTO `cinema` (`name`, `location_id`) VALUES ('Cinema 3', 3);

INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (1, 100, 1);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (2, 200, 1);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (3, 300, 1);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (1, 100, 2);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (2, 200, 2);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (3, 300, 2);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (1, 100, 3);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (2, 200, 3);
INSERT INTO `cinemaHall` (`hall_number`, `seats`, `cinema_id`) VALUES (3, 300, 3);




