-- Insert roles
-- =========================
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');
-- =========================
-- Insert users
-- =========================
INSERT INTO users (name, age, username, password)
VALUES ('Normal', 25, 'user', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke');

INSERT INTO users (name, age, username, password)
VALUES ('Admin', 30, 'admin', '$2a$10$pbtDNm7cwwohxIJ2cTj3numxgp5qA4OzdWbNyZYz1eRaeszV961Ke');
-- Map user to roles
-- =========================
-- user có role USER
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);

-- admin có role ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);