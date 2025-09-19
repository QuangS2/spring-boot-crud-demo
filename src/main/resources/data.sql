-- Roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('MODERATOR');
INSERT INTO roles (name) VALUES ('AUTHOR');

-- Profiles
INSERT INTO user_profiles (address, phone, bio) VALUES ('Hanoi', '0123456789', 'Loves coding');
INSERT INTO user_profiles (address, phone, bio) VALUES ('HCM', '0987654321', 'Spring Boot learner');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Danang', '0901234567', 'Enjoys reading');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Hue', '0934567890', 'Music enthusiast');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Haiphong', '0945678901', 'Traveler');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Cantho', '0956789012', 'Gamer');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Nha Trang', '0967890123', 'Writer');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Quang Ninh', '0978901234', 'Coffee lover');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Bac Giang', '0989012345', 'Photographer');
INSERT INTO user_profiles (address, phone, bio) VALUES ('Vinh', '0990123456', 'Designer');

-- Users (profile_id phải khớp với profile đã tạo ở trên: 1..10)
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Nguyen Van A', 25, 'nguyena', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 1);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Tran Thi B', 22, 'tranb', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 2);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Le Van C', 30, 'lec', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 3);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Pham Thi D', 28, 'phamd', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 4);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Hoang Van E', 26, 'hoange', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 5);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Do Thi F', 21, 'dof', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 6);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Bui Van G', 27, 'buig', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 7);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Nguyen Thi H', 24, 'nguyenh', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 8);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Pham Van I', 29, 'phami', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 9);
INSERT INTO users (name, age, username, password, profile_id)
VALUES ('Tran Van J', 23, 'tranj', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke', 10);

-- User Roles (chú ý: id role = 1..4, id user = 1..10)
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (7, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (8, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (9, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (10, 2);

-- Posts
INSERT INTO post (title, content, user_id) VALUES ('Hello World', 'This is my first post!', 1);
INSERT INTO post (title, content, user_id) VALUES ('Learning Spring Boot', 'Today I learned about JPA.', 2);
INSERT INTO post (title, content, user_id) VALUES ('My Travel Story', 'I went to Da Nang last week.', 3);
INSERT INTO post (title, content, user_id) VALUES ('Cooking Tips', 'How to cook Pho.', 4);
INSERT INTO post (title, content, user_id) VALUES ('Gaming Review', 'I played Elden Ring.', 6);
INSERT INTO post (title, content, user_id) VALUES ('Photography Tricks', 'How to take night photos.', 9);
INSERT INTO post (title, content, user_id) VALUES ('UI/UX Design', 'Simple is better.', 10);
INSERT INTO post (title, content, user_id) VALUES ('Second Post', 'Just another post.', 1);
INSERT INTO post (title, content, user_id) VALUES ('Writer Diary', 'Thoughts on writing novels.', 7);
INSERT INTO post (title, content, user_id) VALUES ('Coffee Review', 'Best coffee shops in Hanoi.', 8);
