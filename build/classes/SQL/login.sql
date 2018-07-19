CREATE PROCEDURE `login`(IN `u` VARCHAR(50), IN `p` VARCHAR(100))
    NO SQL
select 1 from users where username = u and password = p

