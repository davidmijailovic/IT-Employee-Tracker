INSERT INTO address(
    city, country, "number", street)
VALUES ('Beograd', 'Srbija', '12A', 'Nemanjina');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Novi Sad', 'Srbija', '15', 'Brankova');

INSERT INTO address(
    city, country, "number", street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');

INSERT INTO users(
    email, gender, jmbg, name, occupation, password, surname, address_id, enabled, last_password_reset_date)
VALUES ('donor@mail.com', 0, '7132312321321', 'Marko', 'Poljoprivrednik', 'password', 'Bozic', 3, true, '2022-11-28 18:17:53.840417');