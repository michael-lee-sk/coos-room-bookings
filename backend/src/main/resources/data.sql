-- Clear existing data (order matters due to foreign key constraints)
DELETE FROM user_role;
DELETE FROM booking;
DELETE FROM role;
DELETE FROM app_user;
DELETE FROM room;

-- Insert initial roles
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

-- Insert initial users
INSERT INTO app_user (username, password, email, enabled)
VALUES ('admin', 'adminpassword', 'admin@example.com', TRUE);
INSERT INTO app_user (username, password, email, enabled)
VALUES ('john', 'userpassword', 'john@example.com', TRUE);
INSERT INTO app_user (username, password, email, enabled)
VALUES ('jane', 'anotherpassword', 'jane@example.com', TRUE);
INSERT INTO app_user (username, password, email, enabled)
VALUES ('alice', 'alicepassword', 'alice@example.com', TRUE);

-- Assign roles to users
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);  -- admin is an ADMIN
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);  -- john is a USER
INSERT INTO user_role (user_id, role_id) VALUES (3, 1);  -- jane is a USER
INSERT INTO user_role (user_id, role_id) VALUES (4, 1);  -- alice is a USER

-- Insert initial rooms
INSERT INTO room (name, capacity) VALUES ('Room 101', 10);
INSERT INTO room (name, capacity) VALUES ('Room 102', 5);
INSERT INTO room (name, capacity) VALUES ('Room 103', 20);
INSERT INTO room (name, capacity) VALUES ('Room 104', 15);
INSERT INTO room (name, capacity) VALUES ('Room 105', 8);
INSERT INTO room (name, capacity) VALUES ('Room 106', 12);
INSERT INTO room (name, capacity) VALUES ('Room 107', 6);
INSERT INTO room (name, capacity) VALUES ('Room 108', 10);
INSERT INTO room (name, capacity) VALUES ('Room 109', 14);
INSERT INTO room (name, capacity) VALUES ('Room 110', 18);

-- Insert various booking data

-- Room 101
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (1, 1, '2024-09-10 09:00:00', '2024-09-10 12:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (1, 2, '2024-09-10 13:00:00', '2024-09-10 15:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (1, 3, '2024-09-11 09:00:00', '2024-09-11 11:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (1, 4, '2024-09-11 14:00:00', '2024-09-11 16:00:00');  -- alice

-- Room 102
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (2, 2, '2024-09-12 08:00:00', '2024-09-12 10:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (2, 3, '2024-09-13 11:00:00', '2024-09-13 13:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (2, 4, '2024-09-14 09:00:00', '2024-09-14 12:00:00');  -- alice

-- Room 103
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (3, 1, '2024-09-15 09:00:00', '2024-09-15 12:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (3, 2, '2024-09-16 13:00:00', '2024-09-16 15:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (3, 3, '2024-09-17 09:00:00', '2024-09-17 11:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (3, 4, '2024-09-18 14:00:00', '2024-09-18 16:00:00');  -- alice

-- Room 104
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (4, 1, '2024-09-19 10:00:00', '2024-09-19 12:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (4, 2, '2024-09-20 09:00:00', '2024-09-20 11:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (4, 3, '2024-09-21 13:00:00', '2024-09-21 15:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (4, 4, '2024-09-22 08:00:00', '2024-09-22 10:00:00');  -- alice

-- Room 105
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (5, 1, '2024-09-23 14:00:00', '2024-09-23 16:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (5, 2, '2024-09-24 11:00:00', '2024-09-24 13:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (5, 3, '2024-09-25 09:00:00', '2024-09-25 11:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (5, 4, '2024-09-26 15:00:00', '2024-09-26 17:00:00');  -- alice

-- Room 106
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (6, 1, '2024-09-27 10:00:00', '2024-09-27 12:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (6, 2, '2024-09-28 13:00:00', '2024-09-28 15:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (6, 3, '2024-09-29 09:00:00', '2024-09-29 11:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (6, 4, '2024-09-30 14:00:00', '2024-09-30 16:00:00');  -- alice

-- Room 107
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (7, 1, '2024-09-05 10:00:00', '2024-09-05 12:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (7, 2, '2024-09-06 14:00:00', '2024-09-06 16:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (7, 3, '2024-09-07 09:00:00', '2024-09-07 11:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (7, 4, '2024-09-08 13:00:00', '2024-09-08 15:00:00');  -- alice

-- Room 108
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (8, 1, '2024-09-09 08:00:00', '2024-09-09 10:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (8, 2, '2024-09-10 11:00:00', '2024-09-10 13:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (8, 3, '2024-09-11 14:00:00', '2024-09-11 16:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (8, 4, '2024-09-12 09:00:00', '2024-09-12 11:00:00');  -- alice

-- Room 109
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (9, 1, '2024-09-13 13:00:00', '2024-09-13 15:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (9, 2, '2024-09-14 10:00:00', '2024-09-14 12:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (9, 3, '2024-09-15 11:00:00', '2024-09-15 13:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (9, 4, '2024-09-16 14:00:00', '2024-09-16 16:00:00');  -- alice

-- Room 110
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (10, 1, '2024-09-17 09:00:00', '2024-09-17 11:00:00');  -- admin
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (10, 2, '2024-09-18 12:00:00', '2024-09-18 14:00:00');  -- john
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (10, 3, '2024-09-19 15:00:00', '2024-09-19 17:00:00');  -- jane
INSERT INTO booking (room_id, user_id, startTime, endTime) VALUES (10, 4, '2024-09-20 08:00:00', '2024-09-20 10:00:00');  -- alice
