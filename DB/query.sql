SELECT first_name, last_name
FROM employees
WHERE email IN (
	SELECT email 
	FROM employee_manager
);
