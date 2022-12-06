INSERT INTO address (city, street, zip, house_number) VALUES ('Berlin', 'Kurfürstendamm', '10719', '1');
INSERT INTO address (city, street, zip, house_number) VALUES ('Köln', 'Meisterstrasse', '50483', '2');
INSERT INTO address (city, street, zip, house_number) VALUES ('Erfurt', 'Nettelbeckufer', '99086', '45');

INSERT INTO credit_card (number, cvc, exp_month, exp_year) VALUES ('1234567890123456', '123', '12', '2020');
INSERT INTO credit_card (number, cvc, exp_month, exp_year) VALUES ('1234567890123457', '123', '12', '2020');
INSERT INTO credit_card (number, cvc, exp_month, exp_year) VALUES ('1234567890123458', '123', '12', '2020');

INSERT INTO visitor (first_name, last_name, email, phone_number, credit_card_id, membership, address_id) VALUES ('Max', 'Mustermann', 'max.mustermann@microkino.de', '123456789', 1, true, 1);
INSERT INTO visitor (first_name, last_name, email, phone_number, credit_card_id, membership, address_id) VALUES ('Hans', 'Peter', 'hans.peter@microkino.de', '123456789', 2, false, 2);
INSERT INTO visitor (first_name, last_name, email, phone_number, credit_card_id, membership, address_id) VALUES ('Marie', 'Meister', 'marie.meister@microkino.de', '123456789', 2, true, 2);
INSERT INTO visitor (first_name, last_name, email, phone_number, credit_card_id, membership, address_id) VALUES ('Test', 'User', 'test.user@microkino.de', '123456789', 2, true, 3);


INSERT INTO ticket (cinema, schedule, movie, seat, cinema_hall, order_id, visitor_id) VALUES ('Cinema 1', '2018-01-01 10:00:00', 'Stirb Langsam', 1, 1, '234234', 1);
INSERT INTO ticket (cinema, schedule, movie, seat, cinema_hall, order_id, visitor_id) VALUES ('Cinema 2', '2018-01-01 10:00:00', 'Stirb noch Langsamer', 2, 1, '234234', 2);
INSERT INTO ticket (cinema, schedule, movie, seat, cinema_hall, order_id, visitor_id) VALUES ('Cinema 2', '2018-01-01 10:00:00', 'Stirb so langsam, dass es fast gegen 0 geht', 3, 1, '234234', 3);
INSERT INTO ticket (cinema, schedule, movie, seat, cinema_hall, order_id, visitor_id) VALUES ('Cinema 3', '2018-01-01 10:00:00', 'Werde neu geboren Teil 2', 4, 1, '234234', 4);
INSERT INTO ticket (cinema, schedule, movie, seat, cinema_hall, order_id, visitor_id) VALUES ('Cinema 3', '2018-01-01 10:00:00', 'Hallo', 5, 1, '234234', 4);







