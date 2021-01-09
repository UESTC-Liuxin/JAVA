# 前言

## 基本概念

- DB(数据库)：存储数据的“仓库”。它保存了一些列有组织的数据。
- DBMS：数据库管理系统。数据库是通过DBMS创建和操作的容器
- SQL:结构化查询语言：专门用来与数据库通信的语言。

## MySQL基础

### 基础命令

- select version();//查看mysql版本

- `show databases;`显示数据库

  默认自带数据库：

  information_schema：主要存储系统中的一些数据库对象信息，如用户表信息，列信息，权限信息，字符集信息，分区信息等等。

  performance_schema：主要存储数据库服务器性能参数。

  mysql：主要存储系统用户的权限信息

  test：是Mysql自动创建的测试数据库，无重要意义，任何用户都可以使用

- use database名；//进入数据库

- show tables from mysql; //显示数据库表

- create database if not exists test; //创建以test命名的数据库

- select database(); //查看当前所在数据库

- mysql> create table stuinfo(
      -> id int,
      -> name varchar(20));
  Query OK, 0 rows affected (0.01 sec)//创建表

- desc stuinfo; //查看表的结构

  ![image-20210105192546833](https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20210105192546833.png)

- select * from stuinfo;//查看表数据

- insert into stuinfo (id,name) values(1,'liuxin');//插入信息

- update stuinfo set name='liusiyu' where name='liuxin';//修改信息

- delete from stuinfo where id = 1;//删除指定数据

### 语法规范

1. 不区分大小写，但建议关键字大写，表名，列名小写

2. 每条命令最好用分好结尾；

3. 每条命令根据需要，可以进行缩进或换行。

4. 关键字单独一行。

5. 注释

   ​	单行注释：#注释文字

   ​	单行注释：-- 注释文字

   ​	多行注释：/* 注释文字 */

# DQL语言

## 查询

基础SELECT语句。

```mysql
/*
语法：
SELECT 查询列表
FROM 表名;
特点：
1、 查询列表可以是：表中的字段、常量值、表达式、函数
2、 查询的结果是一个虚拟的表格
*/	

#单个字段查询
SELECT last_name FROM employees;
#多个字段查询
SELECT last_name,salary,email FROM employees;
#全查
SELECT * FROM employees;

#查询常量值
SELECT 100;
SELECT 'john';
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

#查询去重,只输出不同的值（去重关键字只能接一个字段）
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
```

条件查询

```mysql
/*
select 
		查询条件
from 
		表名
where
		筛选条件

分类：
	一、按条件表达式筛选
    条件运算符：> < = != <> >= <=
    二、按逻辑表达式筛选
    逻辑运算符： 
			&& || !
            and or not
	三、模糊查询
			like
            between and
            in
            is null
            
*/



#查询工资大于12000的员工
select 
	*
from
	employees
where
	salary>12000;
    
#查询部门编号不等于90的员工名和部门编号
select 
	last_name,department_id
from
	employees
where
	department_id <> 90;
    
#查询部门编码不是在90到110之间，或者工资高于15000的员工信息
select 
	*
from
	employees
where
	not (department_id >= 90 and department_id <= 110)
or 
	salary >15000;
    
    
#模糊查询
# 查询员工名中包含字符a的员工信息
select
	*
from
	employees
where
	last_name like '%a%';
    
    
#查询员工名中第三个字符为n，第五个字符为l的员工名和工资
select
	last_name,
    salary
from
	employees
where
	last_name like '__n_l%';
    
#字符转义
select
	last_name
from
	employees
where
	last_name like '_\_%';
    
#可以指定转义字符
select
	last_name
from
	employees
where
	last_name like '_$_%' escape '$';


#in的使用
select
	*
from
	employees
where
	job_id in('AD_PRES','AD_VP');
    
#is null的使用
select
	last_name,
    commission_pct
from
	employees
where
	commission_pct is null;#这里不能写 = null，没有这种表达式

# 安全等于
select
	last_name,
    commission_pct
from
	employees
where
	commission_pct <=> null;
    
# 查询员工号为176的员工的姓名和部门号和年薪
select 
	last_name,
    department_id,
    salary*12*(1+ifnull(commission_pct,0)) AS 年薪  #这里要注意奖金率可能为null的情况
from
	employees;
    
#查询没有奖金，且工资小于18000的salary，姓名
select
	concat(last_name,first_name) as 姓名,salary,commission_pct
from
	employees
where
	commission_pct is null
and
	salary < 18000;
    
#查询employees表中，job_id
SELECT
	*
FROM 
	employees;

select commission_pct from  employees where commission_pct like '%%'  and last_name like '%%';
```

面试题

试问：select * from employees; 和select * from  employees where commission_pct like '%%'  and last_name like '%%'；运行结果是否一致。

答案是不一致的，因为like '%%'不能匹配null。

排序查询

```mysql
/*
引入
SELECT * FROM employees;

语法：

SELECT 查询列表
FROM
[WHERE 筛选条件]
ORDER BY 排序信息 [ASC|DESC]

特点：
ASC表示升序
DESC表示降序
默认升序
order支持单个字段、多个字段、表达式、函数、别名
*/
#案例1：查询员工信息，要求工资从高到底
SELECT 
	*
FROM
	employees
ORDER BY
	salary;

#案例2：查询部门编号>=90的员工信息，按入职时间的先后进行排序。
select
	*
FROM
	employees
WHERE
	department_id >=90
ORDER BY
	hiredate
ASC;


# 案例3：按年薪的高低显示员工的信息和年薪 [按表达式排序]
SELECT
	*,salary*12*(1+IFNULL(commission_pct,0)) AS 年薪
FROM
	employees
ORDER BY
	年薪
DESC;

#案例五：按姓名的长度显示员工的姓名和工资 [按函数排序]

SELECT CONCAT(last_name,first_name) as 姓名,salary
FROM employees
ORDER BY LENGTH(姓名);


#案例6：查询员工信息，要求先按工资升序，再按员工编号降序 [按多个字段排序]

#首先看一看工资有没有重复的
SELECT salary
FROM employees;
SELECT FOUND_ROWS();#获取结果长度

SELECT DISTINCT salary
FROM employees;
SELECT FOUND_ROWS();#一共只有57个 因此一定有重复的


#大排序写在前面，小排序写在后面（相当于excel的主要关键字，次要关键字）
SELECT *
FROM employees
ORDER BY salary ASC,employee_id DESC

#案例七:选择工资不在8000到17000的员工的名字和工资，按工资降序
SELECT last_name,salary
FROM employees
WHERE salary NOT BETWEEN 8000 AND 17000
ORDER BY salary DESC;

#案例8：查询邮箱中包含e的员工信息，并先按邮箱的字节数降序，再按部门号升序
SELECT *
FROM employees
WHERE email LIKE '%e%'
ORDER BY LENGTH(email) DESC,department_id ASC
```

## 常用函数

概念：类似于java的方法，将一组逻辑语句封装在方法体中，对外暴露方法名

好处：1、隐藏了实现细节 2、提高代码的重用性

调用：select 函数名() [from 表]（如果用到了表中的字段）
特点：
	1、叫什么（函数名）
	2、干什么（函数功能）
分类：
	1、单行函数
	如 concat、length、ifnull
	2、分组函数
	功能：做统计使用，又称为统计函数、聚合函数、组函数
	常见函数总结：
		字符函数：
					LENGTH(str)
					CONCAT(str1,str2,...)
					SUBSTR(str,pos)
					INSTR(str,substr)
					TRIM([remstr FROM] str)
					UPPER(str)
					LOWER(str)
					LPAD(str,len,padstr)
					RPAD(str,len,padstr)
					REPLACE
		数学函数：
					ROUND(X)
					CEIL(X)
					FLOOR(X)
					TRUNCATE(X,D)
					MOD
		日期函数：
					NOW()
					CURDATE()
					CURTIME()
					YEAR(date)
					MONTH(date)
					MONTHNAME(date)
					DAY(date)
					HOUR(time)
					MINUTE(time)
					SECOND(time)
					STR_TO_DATE(str,format)
					DATE_FORMAT(date,format)
		其余：
					IF(expr1,expr2,expr3)
					CAST(expr AS type)

### 单行


```mysql
一、字符函数
#1. LENGTH(str)
SELECT LENGTH('ABC');
#对于汉字要注意字符集来计算长度(GB2312一个汉字3个字节)
SELECT LENGTH('中国人');
#2. CONCAT(str1,str2,...)
SELECT CONCAT(last_name,' ',first_name) AS 姓名
FROM employees;
#3. UPPER(str) LOWER(str)
SELECT UPPER('joHN');
SELECT LOWER('joHN');
#4. SUBSTR(str FROM pos),SUBSTR(str FROM pos FOR len),SUBSTR(str,pos,len),SUBSTR(str,pos)
# SUBSTRING(str FROM pos FOR len)...
# 注意：sql语句中的索引都是从1开始的。
SELECT SUBSTR('123456',4);#从下标4开始截取
SELECT SUBSTR('123456',2,5);#从下标2开始截取，截取长度为4（除了length指的是字节长度，其余都是字符）

#案例1：将姓名中的首字母大写，其余小写
SELECT CONCAT(UPPER(SUBSTR(last_name,1,1)),
    LOWER(SUBSTR(last_name,2))) as 'name'
FROM employees;

#5. INSTR(str,substr)寻找字串并返回索引,如果找不到返回0
SELECT INSTR('mysql is easy to learn','easy');

#6. TRIM([remstr FROM] str)去掉前后
SELECT TRIM('-'FROM '--liuxin----');

#7. LPAD(str,len,padstr)在左边对str进行填充至长度为len,如果str本身就超过，会阶段str
SELECT LPAD('1000',5,'0');
#8. RPAD(str,len,padstr)
SELECT RPAD('10.0',5,'0');
#9. REPLACE(str,from_str,to_str)
SELECT REPLACE('aaabbbb','ab','');#并不能进行递归替换


#### 二、数学函数
#1. ROUND(X)
SELECT ROUND(-1.65);#默认取整
SELECT ROUND(-1.65,1);#保留一位小数
#2. CEIL(X)向上取整
SELECT CEIL(1.1);#2
SELECT CEIL(-1.1);#-1
#3. FLOOR(X)向下取整

#4. TRUNCATE(X,D) #截断小数
SELECT TRUNCATE(1.11,1);

#5. MOD(N,M)取余，同N%M(a-a/b*b),余数的符号跟被除数有关
SELECT MOD(3,2);
SELECT MOD(10,-3);#1
SELECT MOD(-10,-3);#-1
```

<img src="https://piggo1996.oss-cn-beijing.aliyuncs.com/img/image-20210109104414023.png" alt="image-20210109104414023" style="zoom: 50%;" />

### 分组函数

```mysql
#二、分组函数
/*
功能：用作统计使用。又称为聚合函数或者统计函数或者组函数

分类：
sum求和、avg平均值、max最大值、min最小值、count计算个数


*/

#1、简单的使用
SELECT SUM(salary) FROM employees;
SELECT AVG(salary) FROM employees;
SELECT MIN(salary) FROM employees;
SELECT MAX(salary) FROM employees;
SELECT COUNT(salary) FROM employees;

#2. 参数支持哪些类型

#仅数值型
SELECT SUM(last_name),AVG(last_name) FROM employees;
SELECT SUM(hiredate),AVG(hiredate) FROM employees;

#字符型可按照字典序进行递归排序(a...最大)
SELECT MAX(last_name),MIN(last_name) FROM employees;
#日期型可按照数字递归排序
SELECT MAX(hiredate),MIN(hiredate) FROM employees;

#COUNT(expr)统计不为null的结果
SELECT COUNT(commission_pct) FROM employees;

#3.以上函数均忽略null值
#4.可以distinct搭配
SELECT SUM(DISTINCT salary),SUM(salary) FROM employees;
SELECT COUNT(DISTINCT commission_pct),COUNT(commission_pct)
FROM employees;
#5.count函数的详细介绍
#只能统计salary
SELECT COUNT(salary) FROM employees;
#只要所有字段当中有一个不为null就可以被统计
SELECT COUNT(*) FROM employees;]

#这里先是表示1，相当于增加了一个为1的字段，然后count再去统计，相当于统计表的条数
SELECT COUNT(1) FROM employees;

效率：
MYISAM(5.0)之前,COUNT(*)效率最高
INNODB(5.0)之后,COUNT(*)与COUNT(1)效率一致

#6. 和分组函数一同查询的字段有限制（一般是group_by后的字段）
#下面这种没有意义(新版本会直接报错)
SELECT AVG(salary),employee_id FROM employees;
 



```

## 分组查询

```mysql
#分组数据
#1.GROUP BY 子句语法

#进程5：分组查询
/*
语法：
		SELECT 分组函数，列（要求出现在group by的后面）
		FROM 表
		[WHERE 筛选条件]
		group by 分组的列表1,列表2
		[ORDER BY 子句]
*/
#引入：查询每个部门的平均工资
SELECT AVG(salary) FROM employees;

#案例1：查询每个工种的最高工资
SELECT MAX(salary),job_id
FROM employees
GROUP BY job_id;

#添加筛选条件
#案例1：查询邮箱中包含a字符的，每个部门的平均工资
SELECT AVG(salary),department_id
FROM employees
WHERE email LIKE '%a%'
GROUP BY department_id;

#案例2：查询有奖金的人中，每个领导手下员工的最高工资
SELECT MAX(salary),manager_id
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY manager_id;

#添加分组后的筛选
#案例1：查询哪个部门的员工个数>2

#先统计部门的员工人数,再用HAVING连接
SELECT COUNT(*),department_id
FROM employees
GROUP BY department_id
HAVING COUNT(*)>2;

#案例2: 查询每个工种有奖金的员工的最高工资>12000的工种编号和最高工资
#先查询每个工种有奖金的员工的最高工资
SELECT MAX(salary),job_id
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY job_id
HAVING MAX(salary)>12000;


#案例3：查询每个领导编号>102的每个领导手下的最低工资>5000的领导编号，以及其最低工资
/*下面这种查询是错误的，因为先过滤掉工资<5000的员工，会使得所有领导的最低工资都大于5000
#先查询最低工资大于5000的员工，且领导编号>102
#再在这群人中进行分组
SELECT *
FROM employees
WHERE salary>5000 AND manager_id >102;


SELECT MIN(salary),manager_id
FROM employees
WHERE salary>5000 AND manager_id >102
GROUP BY manager_id;
*/

#正确的查询方式
#先查询每个领导的手下员工的最低工资
SELECT MIN(salary),manager_id
FROM employees
GROUP BY manager_id;
#再添加限制条件,根据限制条件来源于表,还是来源于统计进行添加
SELECT MIN(salary),manager_id
FROM employees
WHERE manager_id>102
GROUP BY manager_id
HAVING MIN(salary)>5000;


#按表达或函数分组
#案例：按员工姓名的长度分组，查询每一组员工个数，筛选员工个数>5的有哪些
#查询每个长度的员工个数

SELECT COUNT(*), LENGTH(last_name)
FROM employees
GROUP BY LENGTH(last_name)
HAVING COUNT(*)>5;


#按多个字段分组
#案例：查询每个部门每个工种的员工的平均工资,并且按平均工资的高低显示

SELECT AVG(salary),department_id,job_id
FROM employees
GROUP BY department_id,job_id
ORDER BY AVG(salary);

```

## 连接查询

```mysql
#进阶6：连接查询
/*
含义：又称多表查询，当查询的字段来自于多个表时，就会用到连接查询

笛卡尔乘积现象：表1 有m行，表2有n行，结果=m*n行

发生原因：没有有效的连接条件
如何避免：添加有效的连接条件
分类：
	按年代分类：
	sq192标准：仅仅支持内连接
	sq199标准[推荐]：支持内连接+外连接（左外和右外）+交叉连接
	
	按功能分类：
			内连接：
				等值连接
				非等值连接
				自连接
			外连接：
				左外连接
				右外连接
				全外连接
			交叉连接
*/
```

### 等值查询

```mysql
SELECT * from beauty;
SELECT * FROM boys;

#一、sq192标准
#1、等值连接
#案例1：查询每个美女的男朋友
SELECT NAME,boyName
FROM boys,beauty
WHERE beauty.boyfriend_id=boys.id;

#案例2：查询员工名和对应的部门名
USE myemployees;
SELECT last_name,department_name
FROM employees,departments
WHERE employees.department_id = departments.department_id;


#2.为表取别名
/*
注意：如果为表起了别名，则查询的字段就不能使用原来的表名去限定
*/
#查询员工名、工种号、工种名

SELECT e.last_name,e.job_id,j.job_title
FROM employees e,jobs j
WHERE e.job_id = j.job_id;

#3 加筛选
# 案例1：查询有奖金的员工名、部门名
SELECT last_name,department_name,commission_pct
FROM employees e,departments d
WHERE e.department_id = d.department_id
AND e.commission_pct IS NOT NULL;

#案例2：查询城市名中第二个字符为o的部门名和城市名
SELECT department_name,city
FROM departments,locations
WHERE departments.location_id = locations.location_id
AND city LIKE '_o%';

#4.加分组
#案例1：查询每个城市的部门个数
SELECT COUNT(*),loc.city
FROM departments d,locations loc
WHERE d.location_id = loc.location_id
GROUP BY loc.city;
#5.加排序
#案例1：查询每个工种的工种名和员工的个数，并且按员工个数降序
SELECT j.job_title,COUNT(*)
FROM jobs j,employees e
WHERE j.job_id=e.job_id
GROUP BY j.job_title
ORDER BY COUNT(*)DESC;
#6.三表查询
#案例：查询员工名、部门名和所在的城市
SELECT last_name,department_name,city
FROM employees e,departments d,locations loc
WHERE e.department_id = d.department_id
AND d.location_id = loc.location_id;
```

