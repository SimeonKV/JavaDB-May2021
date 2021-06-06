#01. Select Employee Information

#Write a query to select all employees and retrieve information about 
#their id, first_name, last_name and job_title ordered by id.

#Query ->>>

SELECT id,first_name,last_name,job_title from employees
ORDER BY id;

#02. Select Employees with Filter

#Write a query to select all employees (id, first_name and last_name (as full_name), job_title, salary)
#whose salaries are higher than 1000.00, ordered by id. Concatenate fields first_name and last_name
#into 'full_name'.

#Query ->>>

SELECT id,CONCAT(first_name,' ',last_name) AS full_name,job_title,salary FROM employees
WHERE salary > 1000
ORDER BY id;

#03. Update Employees Salary

#Update all employees; salaries whose job_title is &quot;Manager&quot; by adding 100.
#Retrieve information about salaries from table employees.

#Query ->>>

UPDATE `employees`
SET `salary` =  `salary` * 1.10
WHERE `job_title` = 'Manager'
SELECT `salary` FROM employees
order by `salary`;

#04. Top Paid Employee

#Write a query to create a view that selects all information about
#the top paid employee from the "employees" table in the hotel database.

#Query ->>>

SELECT * FROM `employees`
ORDER BY `salary` DESC
LIMIT 1;

#05. Select Employees by Multiple Filters

#Write a query to retrieve information about employees, who are in department 4 and has a salary higher or equal to 1000.
#Order the information by id.

#Query ->>>

SELECT * FROM `employees`
WHERE `department_id` IN (4) AND `salary` > 1000
ORDER BY `id`;

#06. Delete from Table

#Write a query to delete all employees from the "employees" table who are in department 2 or 1.
#Then select all from table `employees` and order the information by id.

#Query ->>>

DELETE FROM `employees`
WHERE `department_id` IN (2,1);

SELECT * FROM `employees`
ORDER BY `id`;


