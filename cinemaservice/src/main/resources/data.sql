DROP TABLE IF EXISTS cinemas;
DROP TABLE IF EXISTS persons;

CREATE TABLE `cinemas` (
    `cinema_id` int AUTO_INCREMENT  PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

INSERT INTO cinemas (name) VALUES ('The Shawshank Redemption');
INSERT INTO cinemas (name) VALUES ('The Godfather');
INSERT INTO cinemas (name) VALUES ('The Godfather: Part II');


