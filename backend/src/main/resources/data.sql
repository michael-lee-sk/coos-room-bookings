-- Clear all existing data (order matters due to foreign key constraints)
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

-- Assign roles to users
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);  -- admin is an ADMIN
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);  -- john is a USER

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

-- Insert sample bookings with valid dates
INSERT INTO booking (user_id, room_id, start_date, end_date)
VALUES (2, 1, '2024-09-10', '2024-09-10');
INSERT INTO booking (user_id, room_id, start_date, end_date)
VALUES (2, 2, '2024-09-11', '2024-09-11');
