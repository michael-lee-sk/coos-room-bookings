-- H2-compatible data.sql

-- Truncate all tables to remove existing data (H2-specific truncation)
DELETE FROM user_role;
DELETE FROM booking;
DELETE FROM role;
DELETE FROM "user";
DELETE FROM room;

-- Insert initial roles
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

-- Insert initial users
INSERT INTO "user" (username, password, email, enabled)
VALUES ('admin', 'adminpassword', 'admin@example.com', TRUE);
INSERT INTO "user" (username, password, email, enabled)
VALUES ('john', 'userpassword', 'john@example.com', TRUE);

-- Assign roles to users
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);  -- admin is an ADMIN
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);  -- john is a USER

-- Insert initial rooms
INSERT INTO room (name, capacity) VALUES ('Conference Room', 10);
INSERT INTO room (name, capacity) VALUES ('Meeting Room', 5);
INSERT INTO room (name, capacity) VALUES ('Training Room', 20);

-- Insert initial bookings
INSERT INTO booking (user_id, room_id, start_time, end_time)
VALUES (2, 1, '2024-09-10 09:00:00', '2024-09-10 10:00:00');
INSERT INTO booking (user_id, room_id, start_time, end_time)
VALUES (2, 2, '2024-09-11 14:00:00', '2024-09-11 15:00:00');