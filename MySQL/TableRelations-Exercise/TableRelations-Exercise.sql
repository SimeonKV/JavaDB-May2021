#1. One-To-One Relationship

CREATE TABLE `people`(
`person_id` INT,
`first_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(7,2) NOT NULL,
`passport_id` INT
);

CREATE TABLE `passports`(
`passport_id` INT PRIMARY KEY AUTO_INCREMENT,
`passport_number` CHAR(8) NOT NULL UNIQUE
);

INSERT INTO `people`(`person_id`,`first_name`,`salary`,`passport_id`)
VALUES(1,'Roberto',43300,102),(2,'Tom',56100,103),(3,'Yana',60200,101);


INSERT INTO `passports`(`passport_id`,`passport_number`)
VALUES(101,'N34FG21B'),(102,'K65LO4R7'),(103,'ZE657QP2');

ALTER TABLE `people` ADD PRIMARY KEY(`person_id`);
ALTER TABLE `people` MODIFY `person_id` INT AUTO_INCREMENT;

ALTER TABLE `people` ADD FOREIGN KEY(`passport_id`) REFERENCES `passports`(`passport_id`);

#02. One-To-Many Relationship

CREATE DATABASE `car_info`;
USE `car_info`;

CREATE TABLE `manufacturers`(
`manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`established_on` DATE NOT NULL
);

CREATE TABLE `models`(
`model_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL,
`manufacturer_id` INT NOT NULL,
FOREIGN KEY(`manufacturer_id`) REFERENCES `manufacturers`(`manufacturer_id`)
);

INSERT INTO `manufacturers`(`manufacturer_id`,`name`,`established_on`)
VALUES(1,'BMW','1916-03-01'),(2,'Tesla','2003-01-01'),(3,'Lada','1966-05-01');

UPDATE  `manufacturers` 
SET `established_on` = '1916-03-01'
WHERE `id` = 1;

INSERT INTO `models`(`model_id`,`name`,`manufacturer_id`)
VALUES(101,'X1',1),(102,'i6',1),(103,'Model S',2),(104,'Model X',2),(105,'Model 3',2),(106,'Nova',3);


#3.Many-To-Many Relationship

CREATE TABLE `students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE `exams`(
`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE `students_exams`(
`student_id` INT NOT NULL,
`exam_id` INT NOT NULL,
PRIMARY KEY(`student_id`,`exam_id`),
FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`),
FOREIGN KEY (`exam_id`) REFERENCES `exams`(`exam_id`)
);


INSERT INTO `students`(`student_id`,`name`) VALUES (1,'Mila'),(2,'Toni'),(3,'Ron');
INSERT INTO `exams`(`exam_id`,`name`) VALUES (101,'Spring MVC'),(102,'Neo4j'),(103,'Oracle 11g');
INSERT INTO `students_exams`(`student_id`,`exam_id`) VALUES (1,1),(1,2),(2,1),(3,3),(2,2),(2,3);

#04. Self-referencing table

CREATE TABLE `teachers`(
	`teacher_id` INT PRIMARY KEY NOT NULL,
    `name` VARCHAR(30) NOT NULL,
    `manager_id` INT NULL
    );

INSERT INTO `teachers`(`teacher_id`, `name`, `manager_id`)
VALUES (101, 'John', NULL),
		(102, 'Maya', 106),
        (103, 'Silvia', 106),
        (104, 'Ted', 105),
        (105, 'Mark', 101),
        (106, 'Greta', 101);
        
ALTER TABLE `teachers`
ADD CONSTRAINT FOREIGN KEY (`manager_id`) REFERENCES `teachers`(`teacher_id`);

#05. 5.	Online Store Database
#Create a new database and design the following structure:

CREATE DATABASE `online_store`;
USE `online_store`;

CREATE TABLE `cities`(
`city_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `customers`(
`customer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`birthday` DATE,
`city_id` INT NULL,
FOREIGN KEY(`city_id`) REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders`(
`order_id` INT PRIMARY KEY AUTO_INCREMENT,
`customer_id` INT NOT NULL,
FOREIGN KEY(`customer_id`) REFERENCES `customers`(`customer_id`)
);


CREATE TABLE `item_types`(
`item_type_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `items`(
`item_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`item_type_id` INT NOT NULL,
FOREIGN KEY (`item_type_id`) REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items`(
`order_id` INT,
`item_id` INT,
PRIMARY KEY(`order_id`,`item_id`),
FOREIGN KEY(`order_id`) REFERENCES `orders`(`order_id`),
FOREIGN KEY(`item_id`) REFERENCES `items`(`item_id`)
);


#06. University Database

CREATE TABLE `majors`(
`major_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE`students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`student_number` VARCHAR(12) NOT NULL,
`student_name` VARCHAR(50) NOT NULL,
`major_id` INT NOT NULL,
FOREIGN KEY(`major_id`) REFERENCES `majors`(`major_id`)
);

CREATE TABLE `payments`(
`payment_id` INT PRIMARY KEY AUTO_INCREMENT,
`payment_date` DATE NOT NULL,
`payment_amount` DECIMAL(8,2) NOT NULL,
`student_id` INT NOT NULL,
FOREIGN KEY(`student_id`) REFERENCES `students`(`student_id`)
);

CREATE TABLE `agenda`(
`student_id` INT,
`subject_id` INT
);

CREATE TABLE `subjects`(
`subject_id` INT PRIMARY KEY AUTO_INCREMENT,
`subject_name` VARCHAR(50) NOT NULL
);


ALTER TABLE `agenda` ADD PRIMARY KEY(`student_id`,`subject_id`);
ALTER TABLE `agenda` ADD FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`);
ALTER TABLE `agenda` ADD FOREIGN KEY (`subject_id`) REFERENCES `subjects`(`subject_id`);

#09. Peaks in Rila

#Display all peaks for "Rila" mountain_range. Include:
#•	mountain_range
#•	peak_name
#•	peak_elevation
#Peaks should be sorted by peak_elevation descending.

SELECT * FROM `mountains`;
SELECT * FROM `peaks`;

SELECT m.`mountain_range`,p.`peak_name`,p.`elevation` FROM `mountains` AS `m` JOIN `peaks` AS `p`
ON m.`id` = p.`mountain_id`
WHERE m.`mountain_range` = "Rila"
ORDER BY p.`elevation` DESC; 
