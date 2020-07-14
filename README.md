# **Travel Agency Spring application**

Stack of technologies:
1. Spring Framework (Spring MVC, Spring Security, Spring Core)
2. Hibernate
3. MySQL
4. SLF4J
5. Templating engine - JSP/JSTL (+ Twitter Bootstrap library)
6. Lombok (to avoid boiler-plate code)

Instruction:
1. In a root package of the project there is a file init.sql.
It is an SQL script that creates schema pictured below.

Database schema visualisation:

![Alt text](travel_agency.png?raw=true "DB Schema") 

2. In the resources package you will see db_e.properties file. 
It's an example of db.properties file that should be created in the same package.

3. Just for convenience I have added TestDBConnection class that expects JDBC url, username and password to verify connection.
