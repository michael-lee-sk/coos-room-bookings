-- First, remove all data by truncating the tables
SET FOREIGN_KEY_CHECKS = 0;  -- Temporarily disable foreign key checks

-- Truncate all tables to remove existing data
TRUNCATE TABLE user_role;
TRUNCATE TABLE booking;
TRUNCATE TABLE role;
TRUNCATE TABLE "user";
TRUNCATE TABLE room;

SET FOREIGN_KEY_CHECKS = 1;  -- Re-enable foreign key checks

-- Insert initial roles
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

-- Insert initial users (escaping "user" table)
INSERT INTO "user" (username, password, email, enabled)
VALUES ('admin', 'adminpassword', 'admin@example.com', TRUE);
INSERT INTO "user" (username, password, email, enabled)
VALUES ('john', 'userpassword', 'john@example.com', TRUE);

-- Assign roles to users
INSERT INTO user_role (user_id, role_id)
VALUES (1, 2);  -- admin is an ADMIN
INSERT INTO user_role (user_id, role_id)
VALUES (2, 1);  -- john is a USER

-- Insert initial rooms
INSERT INTO room (name, capacity)
VALUES ('Conference Room', 10);
INSERT INTO room (name, capacity)
VALUES ('Meeting Room', 5);
INSERT INTO room (name, capacity)
VALUES ('Training Room', 20);

-- Insert initial bookings (escaping "user" table reference)
INSERT INTO booking (user_id, room_id, start_time, end_time)
VALUES (2, 1, '2024-09-10 09:00:00', '2024-09-10 10:00:00');
INSERT INTO booking (user_id, room_id, start_time, end_time)
VALUES (2, 2, '2024-09-11 14:00:00', '2024-09-11 15:00:00');
