-- ========================================
-- DATABASE: Event Management System
-- ========================================

CREATE DATABASE IF NOT EXISTS event_management;
USE event_management;

-- ========================================
-- USERS TABLE (Login + Role-based access)
-- ========================================
CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('ADMIN', 'USER') DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================================
-- EVENTS TABLE
-- ========================================
CREATE TABLE events (
                        event_id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        description TEXT,
                        location VARCHAR(100),
                        event_date DATE NOT NULL,
                        price DECIMAL(10,2) DEFAULT 0.00,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ========================================
-- BOOKINGS TABLE
-- ========================================
CREATE TABLE bookings (
                          booking_id INT PRIMARY KEY AUTO_INCREMENT,
                          user_id INT,
                          event_id INT,
                          booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          tickets INT DEFAULT 1,

                          FOREIGN KEY (user_id) REFERENCES users(user_id)
                              ON DELETE CASCADE,

                          FOREIGN KEY (event_id) REFERENCES events(event_id)
                              ON DELETE CASCADE
);

-- ========================================
-- INDEXES (Performance optimization)
-- ========================================
CREATE INDEX idx_username ON users(username);
CREATE INDEX idx_event_date ON events(event_date);

-- ========================================
-- SAMPLE DATA (Optional but useful)
-- ========================================

-- 🔐 Default Admin (password should be hashed in real app)
INSERT INTO users (username, password, role)
VALUES
    ('admin', '$2a$10$examplehashedpassword', 'ADMIN'),
    ('user1', '$2a$10$examplehashedpassword', 'USER');

-- 🎉 Sample Events
INSERT INTO events (name, description, location, event_date, price)
VALUES
    ('Tech Conference', 'Annual tech meetup', 'Delhi', '2026-05-10', 999.00),
    ('Music Fest', 'Live music concert', 'Mumbai', '2026-06-15', 1499.00),
    ('Startup Pitch', 'Startup networking event', 'Bangalore', '2026-07-20', 500.00);

-- 🎟️ Sample Bookings
INSERT INTO bookings (user_id, event_id, tickets)
VALUES
    (2, 1, 2),
    (2, 2, 1);

-- ========================================
-- DONE
-- ========================================