#MySQL EXAM DATABASE - 20 JUNE 2021

#01. CREATE TABLE 
CREATE DATABASE `exam_db`;
USE `exam_db`;

CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE `clients`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`phone_number` VARCHAR(20) NOT NULL
);


CREATE TABLE `drivers`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`rating` FLOAT DEFAULT 5.5
);


CREATE TABLE `cars`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`make` VARCHAR(20) NOT NULL,
`model` VARCHAR(20),
`year` INT NOT NULL DEFAULT 0,
`mileage` INT DEFAULT 0,
`condition` CHAR(1) NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT `fk_cars_categories`
FOREIGN KEY(`category_id`) REFERENCES `categories`(`id`)
);

CREATE TABLE `courses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`from_address_id` INT NOT NULL,
`start` DATETIME NOT NULL,
`bill` DECIMAL(10,2) DEFAULT 10,
`car_id` INT NOT NULL,
`client_id` INT NOT NULL,

CONSTRAINT `fk_courses_addresses` 
FOREIGN KEY(`from_address_id`) REFERENCES `addresses`(`id`),

CONSTRAINT `fk_courses_cars`
FOREIGN KEY(`car_id`) REFERENCES `cars`(`id`),

CONSTRAINT `fk_courses_clients`
FOREIGN KEY(`client_id`) REFERENCES `clients`(`id`)
);


CREATE TABLE `cars_drivers`(
`car_id` INT NOT NULL,
`driver_id` INT NOT NULL,

PRIMARY KEY(`car_id`,`driver_id`),

CONSTRAINT `fk_cars_drivers_cars`
FOREIGN KEY(`car_id`) REFERENCES `cars`(`id`),

CONSTRAINT `fk_cars_drivers_drivers`
FOREIGN KEY(`driver_id`) REFERENCES `drivers`(`id`)
);

#02. Insert
#When drivers are not working and need a taxi to transport them, they will also be registered 
#at the database as customers.
#You will have to insert records of data into the clients table, based on the drivers table. 
#For all drivers with an id between 10 and 20 (both inclusive), insert data in the clients table with the following values:
#•	full_name – get first and last name of the driver separated by single space
#•	phone_number – set it to start with (088) 9999 and the driver_id multiplied by 2

INSERT INTO `clients`(`full_name`,`phone_number`)
SELECT CONCAT(`first_name`,' ',`last_name`) AS `full_name`, CONCAT('(088) 9999',`id` * 2) AS `phone_number` FROM `drivers`
WHERE `id` BETWEEN 10 AND 20;

#03. Update
#After many kilometers and over the years, the condition of cars is expected to deteriorate.
#Update all cars and set the condition to be 'C'. The cars  must have a mileage greater than 800000 (inclusive) or NULL and must be older than 2010(inclusive).
#Skip the cars that contain a make value of Mercedes-Benz. They can work for many more years.

UPDATE `cars`
SET `condition` = 'C'
WHERE (`mileage` IS NULL OR `mileage` >= 800000 ) AND (`year` <= 2010) AND `make` != 'Mercedes-Benz';

#04. Delete
#Some of the clients have not used the services of our company recently, so we need to remove them 
#from the database.	
#Delete all clients from clients table, that do not have any courses and the count of the characters in the full_name is more than 3 characters. 

DELETE `cl` FROM `clients` AS `cl`  
LEFT  JOIN `courses` AS `c` ON cl.`id` = c.`client_id`
WHERE (c.`id` IS NULL) AND CHAR_LENGTH(cl.`full_name`) > 3;

#05.	Cars
#Extract the info about all the cars. 
#Order the results by car’s id.
#Required Columns
#•	make
#•	model
#•	condition

SELECT `make`,`model`,`condition` FROM `cars`
ORDER BY `id`;

#06.	Drivers and Cars
#Now, we need a more detailed information about drivers and their cars.
#Select all drivers and cars that they drive. Extract the driver’s first and last name from the drivers table and the make, the model and the mileage from the cars table. Order the result by the mileage in descending order, then by the first name alphabetically. 
#Skip all cars that have NULL as a value for the mileage.

#Required Columns
#•	first_name
#•	last_name 
#•	make
#•	model
#•	mileage

SELECT d.`first_name`,d.`last_name`,`make`,`model`,`mileage`
FROM `drivers` AS `d` INNER JOIN `cars_drivers` AS  `cd` ON d.`id` = cd.`driver_id`
INNER JOIN cars AS `c` ON cd.`car_id` = c.`id`
WHERE c.`mileage` IS NOT NULL
ORDER BY `mileage` DESC,`first_name`;

#07.	Number of courses for each car
#Extract from the database all the cars and the count of their courses. Also display the average bill of each course by the car, rounded to the second digit.
#Order the results descending by the count of courses, then by the car’s id. 
#Skip the cars with exactly 2 courses.
#Required Columns
#•	car_id
#•	make
#•	mileage
#•	count_of_courses
#•	avg_bill

SELECT c.`id` ,c.`make`,c.`mileage`,COUNT(cou.`id`) AS `count_of_courses`, ROUND(AVG(`bill`),2) AS `avg_bill`
FROM `cars` AS `c` LEFT JOIN `courses` AS `cou` ON c.`id` = cou.`car_id`
GROUP BY c.`id`
HAVING `count_of_courses` <> 2
ORDER BY `count_of_courses` DESC, c.`id`;

#08. Regular clients
#Extract the regular clients, who have ridden in more than one car. The second letter of the customer's full name must be 'a'. Select the full name, the count of cars that he ridden and total sum of all courses.
#Order clients by their full_name.

#Required Columns
#•	full_name
#•	count_of_cars
#•	total_sum

SELECT cl.`full_name`,COUNT(crs.`id`) AS `count_of_cars`,SUM(cou.`bill`) AS `total_sum` FROM
`clients` AS `cl` JOIN `courses` AS `cou` ON cl.`id` = cou.`client_id`
JOIN `cars` AS `crs` ON cou.`car_id` = crs.`id`
WHERE `full_name` LIKE '_a%'
GROUP BY cl.`full_name`
HAVING `count_of_cars` > 1
ORDER BY cl.`full_name`;

#09. Full information of courses

#The headquarters want us to make a query that shows the complete information about all courses in the database. The information that they need is the address, if the course is made in the Day (between 6 and 20(inclusive both)) or in the Night (between 21 and 5(inclusive both)), the bill of the course, the full name of the client, the car maker, the model and the name of the category.
#Order the results by course id.
#Required Columns
#•	name (address)
#•	day_time
#•	bill
#•	full_name (client)
#•	make
#•	model
#•	category_name (category)


SELECT `add`.`name`,
CASE
WHEN SUBSTRING(TIME(c.`start`),1,2) BETWEEN 6 AND 20 THEN "Day"
ELSE "Night"
END AS `day_time`,
c.`bill`,
cl.`full_name`,
cars.`make`,
cars.`model`,
cat.`name`
FROM `courses` AS `c` 
JOIN `addresses` AS `add` ON c.`from_address_id` = `add`.`id`
JOIN `clients` AS `cl` ON c.`client_id` = cl.`id`
JOIN `cars` ON c.`car_id` = cars.`id`
JOIN `categories` AS `cat` ON cars.`category_id` = cat.`id`
ORDER BY c.`id`;

#10.	Find all courses by client’s phone number
#Create a user defined function with the name udf_courses_by_client (phone_num VARCHAR (20)) that receives a client’s phone number and returns the number of courses that clients have in database.

DELIMITER $$
CREATE FUNCTION udf_courses_by_client (`phone_num` VARCHAR(20))
RETURNS INT deterministic

BEGIN
RETURN (SELECT COUNT(c.`id`) FROM `clients` AS `c`
JOIN `courses` AS `cou` ON c.`id` = cou.`client_id`
WHERE c.`phone_number` = `phone_num`
GROUP BY c.`full_name`);

END $$
DELIMITER ;

#11.	Full info for address
#Create a stored procedure udp_courses_by_address which accepts the following parameters:
#•	address_name (with max length 100)

#Extract data about the addresses with the given address_name. The needed data is the name of the address, full name of the client, level of bill (depends of course bill – Low – lower than 20(inclusive), Medium – lower than 30(inclusive), and High), make and condition of the car and the name of the category.
#Order addresses by make, then by client’s full name.
#Required Columns
#•	name (address)
#•	full_name
#•	level_of_bill
#•	full_name (client)
#•	make
#•	condition
#•	cat_name (category

DELIMITER $$
CREATE PROCEDURE udp_courses_by_address(`address_name` VARCHAR(100))
BEGIN
SELECT
 a.`name`,
cl.`full_name`,
CASE 
WHEN ROUND(cou.`bill`,0) BETWEEN 0 AND 20 THEN 'Low'
WHEN ROUND(cou.`bill` ,0) BETWEEN 21 AND 30 THEN 'Medium'
ELSE 'High'
END AS `level_of_bill`,
crs.`make`,
crs.`condition`,
cat.`name`
FROM `addresses` AS `a` JOIN `courses` AS `cou` ON a.`id` = cou.`from_address_id`
JOIN `clients` AS `cl` ON cou.`client_id` = cl.`id`
JOIN `cars` AS `crs` ON cou.`car_id` = crs.`id`
JOIN `categories` AS `cat` ON crs.`category_id` = cat.`id`
WHERE a.`name` = `address_name`
ORDER BY crs.`make`,cl.`full_name`;


END$$
DELIMITER ;