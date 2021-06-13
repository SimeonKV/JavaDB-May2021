CREATE DATABASE `DEMO`;
USE `DEMO`;

#01. Mountains and Peaks
#Write a query to create two tables – mountains and peaks and link their fields properly. Tables should have:
#-	Mountains:
#•	id 
#•	name
#-	Peaks: 
#•	id
#•	name
#•	mountain_id
#Check your solutions using the "Run Queries and Check DB" strategy.

CREATE TABLE `Mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE `Peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`mountain_id` INT NOT NULL,
FOREIGN KEY(`mountain_id`) REFERENCES `Mountains`(`id`)
);


#02. Trip Organization
#Write a query to retrieve information about SoftUni camp's transportation organization.
#Get information about the drivers (name and id) and their vehicle type. Submit your queries using the "MySQL prepare DB and Run Queries"
#strategy.

USE `camp`;

SELECT * FROM `campers`;
SELECT * FROM `vehicles`;

SELECT `v`.`driver_id`,`v`.`vehicle_type`, CONCAT(`first_name`,' ',`last_name`) AS `driver_name`
FROM `campers` AS `c` JOIN `vehicles` AS `v`
ON `c`.`id` = `v`.`driver_id`;


#03. SoftUni Hiking
#Get information about the hiking routes – starting point and ending point, and their leaders – name and id.
#Submit your queries using the "MySQL prepare DB and Run Queries" strategy.

SELECT * FROM `routes`;
SELECT * FROM `campers`;

SELECT r.`starting_point`,r.`end_point`,r.`leader_id`, CONCAT(`first_name`,' ',`last_name`) AS `leader_name`
FROM `routes` AS `r` JOIN `campers` AS `c`
ON r.`leader_id` = c.`id`;

#04. Delete Mountains
#Drop tables from the task 1.
#Write a query to create a one-to-many relationship between a table, holding information about 
#mountains (id, name) and other - about peaks (id, name, mountain_id), so that when a mountain 
#gets removed from the database, all his peaks are deleted too.
#Submit your queries using the "MySQL run queries & check DB" strategy.

DROP TABLE `mountains`;
DROP TABLE `peaks`;


CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`mountain_id` INT NOT NULL,
FOREIGN KEY(`mountain_id`) REFERENCES `mountains`(`id`) ON DELETE CASCADE
);

INSERT INTO  `mountains`(`id`,`name`)
VALUES (1,'Pirin'),(2,'Rila');

SELECT * FROM `mountains`;

INSERT INTO `peaks`(`id`,`name`,`mountain_id`)
VALUES(1,'Vihren',1),(2,'Vihren2',1),(3,'Musala',2),(4,'Musala2',2);

SELECT * FROM `peaks`;

DELETE FROM `mountains`
WHERE id = 1;


