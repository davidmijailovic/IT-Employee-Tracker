-- INSERT INTO address(
--     city, country, "number", street)
-- VALUES ('Beograd', 'Srbija', '12A', 'Nemanjina');
--
-- INSERT INTO address(
--     city, country, "number", street)
-- VALUES ('Novi Sad', 'Srbija', '15', 'Brankova');

INSERT INTO address(
    city, country, number, street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');

INSERT INTO role (name) VALUES ('ROLE_SOFTWARE_ENGINEER');
INSERT INTO role (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ROLE_PROJECT_MANAGER');
INSERT INTO role (name) VALUES ('ROLE_HR_MANAGER');

INSERT INTO users(
                 name, surname, email, password, salt, phone, title, enabled, approved, address_id)
VALUES ('David', 'Mijailovic', 'email@mail.com', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', '32g32jgjugfjugfj', '06400493', 'Student', true, true, 1);

INSERT INTO user_role(
                      user_id, role_id)
VALUES (1,2)
