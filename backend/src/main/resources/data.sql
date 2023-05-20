INSERT INTO address(
    city, country, number, street)
VALUES ('Kovilj', 'Srbija', '50', 'Živojina Mišića');

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

INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Develop a fully functional e-commerce website that enables online transactions, integrates secure payment gateways, and provides a user-friendly interface for customers to browse and purchase products.', 1, 1, '20');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Create a mobile application for a specific business or service, incorporating features such as real-time notifications, user authentication, and seamless integration with existing backend systems, aiming to enhance user engagement and convenience.', 2, 1, '30');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Build a data analytics dashboard that visualizes key performance indicators, generates insights, and facilitates data-driven decision making by aggregating and presenting data from various sources in a visually appealing and easy-to-understand format.', 3, 1, '14');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Design and develop an AI-powered chatbot capable of understanding natural language queries, providing relevant responses, and automating certain tasks, serving as a virtual assistant for customer support or information retrieval.', 4, 1, '35');
INSERT INTO software_engineer_project(description, project_id, software_engineer_id, duration) VALUES ('Develop an Internet of Things (IoT) project, such as a smart home automation system, by integrating various sensors, devices, and platforms to enable remote control and monitoring of household appliances, enhancing convenience and energy efficiency.', 5, 1, '17');


