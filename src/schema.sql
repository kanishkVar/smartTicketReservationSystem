CREATE DATABASE IF NOT EXISTS ticketdb;
USE ticketdb;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100)
);

CREATE TABLE trips (
  id INT AUTO_INCREMENT PRIMARY KEY,
  source VARCHAR(100),
  destination VARCHAR(100),
  trip_date DATE,
  time VARCHAR(10),
  fare DECIMAL(8,2)
);

CREATE TABLE tickets (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  trip_id INT,
  seat_no VARCHAR(10),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (trip_id) REFERENCES trips(id)
);
