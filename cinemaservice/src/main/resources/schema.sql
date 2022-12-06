DROP TABLE IF EXISTS cinema;
DROP TABLE IF EXISTS cinemaHall;
DROP TABLE IF EXISTS location;

CREATE TABLE cinema (
                        cinema_id SERIAL PRIMARY KEY,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        name VARCHAR(255) NOT NULL,
                        location_id int NOT NULL
);

CREATE TABLE cinemaHall (
                            cinema_hall_id SERIAL PRIMARY KEY,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            hall_number int NOT NULL,
                            seats int NOT NULL,
                            cinema_id int NOT NULL
);

CREATE TABLE location (
                          location_id SERIAL PRIMARY KEY,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          city VARCHAR(255) NOT NULL,
                          street VARCHAR(255) NOT NULL,
                          zip VARCHAR(255) NOT NULL,
                          house_number VARCHAR(255) NOT NULL
);