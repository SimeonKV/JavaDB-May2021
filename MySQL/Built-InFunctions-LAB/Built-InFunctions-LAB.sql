#01. Find Book Titles

#Write a SQL query to find books which titles start with "The".
#Order the result by id. Submit your query statements as Prepare DB &amp; run queries.

#Query ->>>

SELECT `title` FROM `books`
WHERE SUBSTRING(`title`,1,3) = 'The'
ORDER BY `id`;

#02. Replace Titles

#Write a SQL query to find books which titles start with "The" 
#and replace the substring with 3 asterisks. Retrieve data about the updated titles.
#Order the result by id. Submit your query statements as Prepare DB &amp; run queries.

#Query ->>>

SELECT REPLACE(`title`,"The","***") AS 'title' FROM `books`
WHERE SUBSTRING(`title`,1,3) = "The";

#03. Sum Cost of All Books

#Write a SQL query to sum prices of all books.Format the output to 2 digits after decimal point.
#Submit your query statements as Prepare DB &amp; run queries.

#Query ->>>

SELECT ROUND(SUM(`cost`),2) FROM `books`;


#04. Days Lived

#Write a SQL query to calculate the days that the authors have lived. NULL values mean that the author is still alive.
#Submit your query statements as Prepare DB &amp; run queries.

#Query ->>>

SELECT CONCAT(`first_name`," ",`last_name`) AS 'Full Name',ABS(DATEDIFF(`born`,`died`)) AS `Days Lived`  FROM `authors`;


#05. Harry Potter Books

#Write a SQL query to retrieve titles of all the Harry Potter books. Order the information by id. Submit your query
#statements as Prepare DB &amp; run queries.


#Query ->>>

SELECT `title` FROM `books`
WHERE `title` LIKE 'Harry Potter%'
ORDER BY `id`;