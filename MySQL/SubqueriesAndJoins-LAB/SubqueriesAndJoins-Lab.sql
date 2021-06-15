#1.	Managers
#Write a query to retrieve information about the managers – id, full_name, deparment_id and department_name.
#Select the first 5 departments ordered by employee_id. Submit your queries using the "MySQL prepare DB and Run Queries" strategy. 

SELECT * FROM `departments`;

SELECT e.`employee_id`, CONCAT(e.`first_name`,' ',e.`last_name`) AS `full_name`, d.`department_id`, d.`name` FROM
`employees` AS `e` JOIN `departments` AS `d` 
ON e.`employee_id` = d.`manager_id`
ORDER BY e.`employee_id`
LIMIT 5;

#2. Towns Addresses
#Write a query to get information about the addresses in the database, which are in San Francisco, Sofia or Carnation.
#Retrieve town_id, town_name, address_text. Order the result by town_id, then by address_id. Submit your queries using the "MySQL prepare DB
#and Run Queries" strategy. 

SELECT * FROM `addresses`;
SELECT * FROM `towns`;

SELECT t.`town_id`,t.`name`, a.`address_text` FROM
`towns` AS `t` JOIN `addresses` AS `a` 
ON t.`town_id` = a.`town_id`
WHERE t.`name` IN ('Sofia','San Francisco','Carnation')
ORDER BY `town_id`,a.`address_id`;

#3. Employees Without Managers
#Write a query to get information about employee_id, first_name, last_name, department_id and salary for all employees
#who don't have a manager. Submit your queries using the "MySQL prepare DB and Run Queries" strategy.

#04.	Higher Salary
#Write a query to count the number of employees who receive salary higher than the average.
#Submit your queries using the "MySQL prepare DB and Run Queries" strategy.

SELECT COUNT(`employee_id`) AS `count` FROM `employees`
WHERE `salary` > 
(SELECT AVG(`salary`) FROM `employees`);

