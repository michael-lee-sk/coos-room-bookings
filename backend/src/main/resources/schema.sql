CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       google_id VARCHAR(255),
                       email VARCHAR(255),
                       name VARCHAR(255)
);

CREATE TABLE rooms (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       capacity INT
);

CREATE TABLE bookings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT,
                          room_id BIGINT,
                          start_time DATETIME,
                          end_time DATETIME,
                          CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES users(id),
                          CONSTRAINT FK_ROOM FOREIGN KEY (room_id) REFERENCES rooms(id)
);
