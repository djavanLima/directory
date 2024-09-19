
CREATE TABLE IF NOT EXISTS `directory` (
    `id_directory` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    `id_parent_directory` BIGINT,
    FOREIGN KEY (id_parent_directory) REFERENCES directory(id_directory) ON DELETE CASCADE  
);

CREATE TABLE IF NOT EXISTS `file` (
    `id_file` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    `id_directory_of_file` BIGINT,
    FOREIGN KEY (id_directory_of_file) REFERENCES directory(id_directory) ON DELETE CASCADE
);