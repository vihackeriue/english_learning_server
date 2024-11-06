create database english_learning_app;
use english_learning_app;

CREATE TABLE user (
    id int(11) AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    avatar VARCHAR(255),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50),
    full_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    updated_by VARCHAR(50),
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER',
    status TINYINT(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE token (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
	token varchar(250) NOT NULL,
	token_type varchar(255) NOT NULL,
	expired varchar(250) NOT NULL,
	revoked varchar(255) NOT NULL,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

