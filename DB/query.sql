SELECT CONCAT(e.first_name, e.last_name) 
FROM employees e
INNER JOIN employee_manager em
ON e.email = em.email;

