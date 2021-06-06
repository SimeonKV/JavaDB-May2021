#01. Find Names of All Employees by First Name

#Write a SQL query to find first and last names of all employees whose first name starts with "Sa" 
#(case insensitively). Order the information by id.
#Submit your query statements as Prepare DB & run queries.

#Query ->>>

SELECT `first_name`,`last_name` FROM `employees` 
WHERE `first_name` LIKE 'Sa%'
ORDER BY `employee_id`;

#Query 2 ->>>

SELECT `first_name`,`last_name` FROM `employees` 
WHERE SUBSTRING(`first_name`,1,2) = 'Sa'
ORDER BY `employee_id`;

#02. Find Names of All Employees by Last Name

#Write a SQL query to find first and last names of all employees whose last name contains "ei" 
#(case insensitively). Order the information by id.
#Submit your query statements as Prepare DB & run queries.

#Query ->>>

SELECT `first_name`,`last_name` FROM `employees`
WHERE `last_name` LIKE '%ei%'
ORDER BY `employee_id`;

