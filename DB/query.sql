SELECT CONCAT(e.first_name, e.last_name) 
FROM employees e
INNER JOIN employee_manager em
ON e.email = em.email;

-- Testing to see if I can grab a list of employees names by manager_id
SELECT CONCAT(e.first_name, e.last_name) 
FROM employees e
INNER JOIN employee_manager em
ON e.email = em.email
WHERE em.manager_id IN (
	SELECT manager_id 
	FROM managers 
	WHERE manager_id = 100
);

-- Getting all records and ordering by manager_id
SELECT * FROM employee_manager 
ORDER BY manager_id;
