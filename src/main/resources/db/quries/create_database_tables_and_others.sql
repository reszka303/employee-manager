CREATE DATABASE employee CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;

CREATE TABLE employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    email VARCHAR(70) UNIQUE,
    job_title VARCHAR(70) NOT NULL,
    phone VARCHAR(20),
    image_url VARCHAR(70),
    employee_code VARCHAR(36) UNIQUE
);