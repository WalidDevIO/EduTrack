-- Crée les deux bases nécessaires au bon fonctionnement de notre application
CREATE DATABASE IF NOT EXISTS auth;
CREATE DATABASE IF NOT EXISTS student;

-- Crée la table access dans la BDD auth pour insérer le compte admin
USE auth;
CREATE TABLE IF NOT EXISTS access (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    password VARCHAR(255) NOT NULL,
    student BIGINT(20) UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

-- Crée le compte admin, mot de passe: password
INSERT INTO access (username, password) VALUES ('admin', '$2a$10$WjU3RnODROHfprtK1/S23ehe54ZiJQJMSJc0v5o8jsiPJsPeO.B5K');