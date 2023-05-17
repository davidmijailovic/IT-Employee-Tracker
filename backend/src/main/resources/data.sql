INSERT INTO address(
    city, country, number, street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');

INSERT INTO role (name) VALUES ('ROLE_SOFTWARE_ENGINEER');
INSERT INTO role (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ROLE_PROJECT_MANAGER');
INSERT INTO role (name) VALUES ('ROLE_HR_MANAGER');

INSERT INTO users(
                 name, surname, email, password, salt, phone, title, enabled, approved, address_id)
VALUES ('David', 'Mijailovic', 'email@mail.com', '$2a$10$8dtbLQHbL0puRvCo8Zr9SupdC51xzYsmaqGm.cGMpvEZLiaElMkq6', '753789389370638c4b0da7c29cabe4e9', '06400493', 'Student', true, true, 1);


INSERT INTO user_role(
                      user_id, role_id)
VALUES (1,1);

INSERT INTO software_engineer(id) VALUES (1);

INSERT INTO skill(name) VALUES ('Python');
INSERT INTO skill(name) VALUES ('Angular');
INSERT INTO skill(name) VALUES ('Java');
INSERT INTO skill(name) VALUES ('C#');

INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('5', 1, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('4', 2, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('2', 3, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('1', 4, 1);


