USE myemployees;
SELECT * FROM employees;

SELECT 100;

#查询表达式
SELECT 100%98;

#查询函数
SELECT VERSION();

#起别名
SELECT last_name AS 姓,first_name AS 名 FROM employees;
#或者
SELECT last_name 姓,first_name 名 FROM employees;

#带空格的字段要用引号
SELECT salary AS "out put" FROM employees;

#查询去重,只输出不同的值
SELECT distinct department_id from employees;

#在mysql中，+只有数值运算的作用，并且可以对字符型数字进行转换
select '123'+90;
#如果字符转换失败，默认为0再进行相加。
select 'liuxin'+90; #输出90
#如果参与运算的对象只要有一个为null，结果为null
select null+90;

#字符的连接利用CONCAT函数
select concat(last_name,first_name) AS 姓名 from employees;

#IFNULL函数
select ifnull(commission_pct,0),commission_pct from employees;

#将所有输出都连接在一起并且进行
select concat(employee_id,first_name,last_name,email,ifnull(commission_pct,0)) as 'out put' from employees;