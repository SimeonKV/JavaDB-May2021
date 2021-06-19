#01. Employee Address
#Write a query that selects:
#•	employee_id
#•	job_title
#•	address_id
#•	address_text
#Return the first 5 rows sorted by address_id in ascending order.

SELECT * FROM `employees`;

SELECT e.`employee_id`,e.`job_title`,e.`address_id`,a.`address_text` FROM `employees` AS `e` JOIN `addresses` AS `a`
ON e.`address_id` = a.`address_id`
ORDER BY e.`address_id`
LIMIT 5;

#02. Addresses with Towns
#Write a query that selects:
#•	first_name
#•	last_name
#•	town
#•	address_text
#Sort the result by first_name in ascending order then by last_name. Select first 5 employees

SELECT e.`first_name`,e.`last_name`,t.`name`,a.`address_text` FROM `employees` AS `e` 
JOIN `addresses` AS `a` ON e.`address_id` = a.`address_id` 
JOIN `towns` AS `t` ON a.`town_id` = t.`town_id`
ORDER BY e.`first_name` ASC,e.`last_name`
LIMIT 5;

#03.	Sales Employee
#Write a query that selects:
#•	employee_id
#•	first_name
#•	last_name
#•	department_name
#Sort the result by employee_id in descending order. Select only employees from the "Sales" department.


SELECT e.`employee_id`,e.`first_name`,e.`last_name`,d.`name` 
FROM `employees` AS `e` JOIN `departments` AS `d`
ON e.`department_id` = d.`department_id`
WHERE d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;

#04. Employee Departments
#Write a query that selects:
#•	employee_id
#•	first_name
#•	salary
#•	department_name
#Filter only employees with salary higher than 15000. Return the first 5 rows sorted by department_id in descending order.

SELECT e.`employee_id`,e.`first_name`,e.`salary`,d.`name` FROM
`employees` AS `e` JOIN `departments` AS `d` 
ON e.`department_id` = d.`department_id`
WHERE e.`salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;

#05. Employees Without Project
#Write a query that selects:
#•	employee_id
#•	first_name
#Filter only employees without a project. Return the first 3 rows sorted by employee_id in descending order.

SELECT e.`employee_id`,e.`first_name`FROM 
`employees` AS `e` LEFT JOIN `employees_projects` AS `ep` 
ON e.`employee_id` = ep.`employee_id`
WHERE ep.`project_id` IS NULL
ORDER BY `employee_id` DESC
LIMIT 3;

#06.	Employees Hired After
#Write a query that selects:
#•	first_name
#•	last_name
#•	hire_date
#•	dept_name
#Filter only employees hired after 1/1/1999 and from either the "Sales" or the "Finance" departments. Sort the result by hire_date (ascending).

SELECT e.`first_name`,e.`last_name`,e.`hire_date`,`d`.`name` 
FROM `employees` AS `e` JOIN `departments` AS `d`
ON e.`department_id` = d.`department_id`
WHERE e.`hire_date` > '1999-01-01' AND d.`name` IN ('Sales','Finance')
ORDER BY `hire_date` ASC;

#07. Employees with Project
#Write a query that selects:
#•	employee_id
#•	first_name
#•	project_name
#Filter only employees with a project, which has started after 13.08.2002 and it is still ongoing (no end date).
#Return the first 5 rows sorted by first_name then by project_name both in ascending order.

SELECT * FROM `projects`;

SELECT e.`employee_id`, e.`first_name`,p.`name` FROM 
`employees` `e` JOIN `employees_projects` AS `ep` ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS `p` ON ep.`project_id` = p.`project_id`
WHERE p.`start_date` > '2002-08-13' AND p.`end_date` IS NULL
ORDER BY e.`first_name` ASC, p.`name` ASC
LIMIT 5;

#08. Employee 24
#Write a query that selects:
#•	employee_id
#•	first_name
#•	project_name
#Filter all the projects of employees with id 24. If the project has started after 2005 inclusively the return value should be NULL.
#Sort the result by project_name alphabetically.

SELECT * FROM `employees`;
SELECT * FROM `projects`;

SELECT e.`employee_id`,e.`first_name`,IF(DATE(p.`start_date`) >= '2005-01-01',NULL,p.`name`) AS `project_name` FROM `employees` AS `e`
JOIN `employees_projects`  `ep` ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS `p` ON ep.`project_id` = p.`project_id`
WHERE e.`employee_id` = 24 
ORDER BY `project_name`; 

#09. 9.	Employee Manager
#Write a query that selects:
#•	employee_id
#•	first_name
#•	manager_id
#•	manager_name
#Filter all employees with a manager who has id equal to 3 or 7. Return all rows sorted by employee first_name in ascending order.

SELECT * FROM `employees`;

SELECT emp.`employee_id`,emp.`first_name`,emp.`manager_id`,managers.`first_name` FROM `employees` AS `emp`
JOIN `employees` AS `managers` ON emp.`manager_id` = managers.`employee_id`
WHERE emp.`manager_id` IN (3,7)
ORDER BY emp.`first_name` ASC;

#10.	Employee Summary
#Write a query that selects:
#•	employee_id
#•	employee_name
#•	manager_name		
#•	department_name
#Show the first 5 employees (only for employees who have a manager) with their managers
#and the departments they are in (show the departments of the employees). Order by employee_id.


SELECT emp.`employee_id`,concat_ws(" ",emp.`first_name`,emp.`last_name`),concat_ws(" ",managers.`first_name`,managers.`last_name`),dep.`name`
FROM `employees` AS `emp` JOIN `employees` AS `managers` ON emp.`manager_id` = managers.`employee_id`
JOIN `departments` AS `dep` ON emp.`department_id` = dep.`department_id`
ORDER BY emp.`employee_id`
LIMIT 5;

#11. Min Average Salary
#Write a query that returns the value of the lowest average salary of all departments.

SELECT AVG(`salary`) AS `avg_salary` FROM `employees`
GROUP BY `department_id`
ORDER BY `avg_salary`
LIMIT 1;

#12.	Highest Peaks in Bulgaria
#Write a query that selects:
#•	country_code	
#•	mountain_range
#•	peak_name
#•	elevation
#Filter all peaks in Bulgaria with elevation over 2835. Return all rows sorted by elevation in descending order.

SELECT coun.`country_code`,moun.`mountain_range`,pk.`peak_name`,pk.`elevation` 
FROM `countries` AS `coun` JOIN `mountains_countries` AS `mc` ON coun.`country_code` = mc.`country_code`
JOIN `mountains` AS `moun` ON mc.`mountain_id` = moun.`id`
JOIN `peaks` AS `pk` ON moun.`id` = pk.`mountain_id`
WHERE coun.`country_code` = 'BG' AND pk.`elevation` > 2835
ORDER BY pk.`elevation` DESC;

#13.	Count Mountain Ranges
#Write a query that selects:
#•	country_code
#•	mountain_range
#Filter the count of the mountain ranges in the United States, Russia and Bulgaria. Sort result by mountain_range count in decreasing order.

SELECT country_name FROM countries;

SELECT coun.`country_code`,COUNT(moun.`mountain_range`) AS `mountain_range` FROM `countries` AS `coun` 
JOIN `mountains_countries` AS `mc` ON coun.`country_code`  = mc.`country_code`
JOIN `mountains` AS `moun` ON mc.`mountain_id`  = moun.`id`
WHERE coun.`country_name` IN ("Bulgaria","Russia","United States")
GROUP BY coun.`country_code`
ORDER BY `mountain_range` DESC;


#14.	Countries with Rivers
#Write a query that selects:
#•	country_name
#•	river_name
#Find the first 5 countries with or without rivers in Africa. Sort them by country_name in ascending order.

SELECT * FROM `countries`;

SELECT coun.`country_name`,r.`river_name` FROM `countries` AS `coun` 
LEFT JOIN `countries_rivers` AS `cr` ON coun.`country_code` = cr.`country_code`
LEFT JOIN `rivers` AS `r` ON  cr.`river_id`  = r.`id`
LEFT JOIN `continents` AS `cts` ON coun.`continent_code` = `cts`.`continent_code`
WHERE cts.`continent_name` = 'Africa'
ORDER BY coun.`country_name`
LIMIT 5;




