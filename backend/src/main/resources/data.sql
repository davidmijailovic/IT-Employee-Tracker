INSERT INTO address(
    city, country, number, street)
VALUES ('Novi Sad', 'Srbija', '20', 'Živojina Mišića');

INSERT INTO role (name) VALUES ('ROLE_SOFTWARE_ENGINEER');
INSERT INTO role (name) VALUES ('ROLE_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ROLE_PROJECT_MANAGER');
INSERT INTO role (name) VALUES ('ROLE_HR_MANAGER');


--David2000!
INSERT INTO users(
                 name, surname, email, password, salt, phone, title, enabled, approved, address_id)
VALUES ('David', 'Mijailovic', 'email@mail.com', '$2a$10$nzPV75Z8awCThfP/TEoHLOsCypm2pWRWilnbGBWFneC3ITTcbtOfm', '3eadd21832894b28b44e9ebd88836529', '06400493', 'Student', true, true, 1);


INSERT INTO user_role(
                      user_id, role_id)
VALUES (1,1);

INSERT INTO software_engineer(id) VALUES (1);

INSERT INTO skill(name) VALUES ('Python');
INSERT INTO skill(name) VALUES ('Angular');
INSERT INTO skill(name) VALUES ('Java');
INSERT INTO skill(name) VALUES ('C#');
INSERT INTO skill(name) VALUES ('React');
INSERT INTO skill(name) VALUES ('SpringBoot');
INSERT INTO skill(name) VALUES ('.NET Core');

INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('5', 1, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('4', 2, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('3', 7, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('2', 3, 1);
INSERT INTO software_engineer_skill(value, skill_id, software_engineer_id) VALUES ('1', 4, 1);


INSERT INTO project(name) VALUES ('E-commerce Website Development');
INSERT INTO project(name) VALUES ('Mobile App Development');
INSERT INTO project(name) VALUES ('Data Analytics Dashboard');
INSERT INTO project(name) VALUES ('Chatbot Development');
INSERT INTO project(name) VALUES ('IoT Project');

INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Performed full-stack development for a robust e-commerce website, implementing secure payment gateways, optimizing user experience, and enabling seamless online transactions.', 1, 1, '20');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Designed and developed a feature-rich mobile application for a specific business, integrating real-time notifications, user authentication, and backend system integration to enhance user engagement and convenience.', 2, 1, '30');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Built an interactive data analytics dashboard, visualizing key performance indicators and enabling data-driven decision making by aggregating and presenting data from multiple sources in a visually appealing format.', 3, 1, '14');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Created an AI-powered chatbot capable of understanding natural language queries, providing relevant responses, and automating tasks, serving as a virtual assistant for customer support and information retrieval.', 4, 1, '35');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Developed an IoT project, integrating sensors, devices, and platforms to enable remote control and monitoring of household appliances for enhanced convenience and energy efficiency.', 5, 1, '17');

