DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS persons;

CREATE TABLE `movies` (
    `movie_id` int AUTO_INCREMENT  PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL
);

INSERT INTO movies (name) VALUES ('The Shawshank Redemption');
INSERT INTO movies (name) VALUES ('The Godfather');
INSERT INTO movies (name) VALUES ('The Godfather: Part II');


