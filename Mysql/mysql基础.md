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
```

通配符

- % 任意多个字符a的员工
- _任意单个字符

```mysql
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
	last_name like '_$_%' escape '$'
```

between...and..左闭右闭

in(列表)

```mysql
>>>>>>> 6e32f3a22223ea4ec0449227b5ce00ab97049318
#in的使用
select
	*
from
	employees
where
	job_id in('AD_PRES','AD_VP');
<<<<<<< HEAD
    
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
					CASE

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

# 三. 日期函数
#1. NOW()返回当前系统日期加时间
SELECT NOW();
#2. CURDATE()返回当前系统日期，不包含时间
SELECT CURDATE();
#3. CURRENT_TIME() #返回时间
SELECT CURRENT_TIME();

#4.YEAR(date),`MONTH`(date),`DAY`(date),`HOUR`(time)...获取指定时间
SELECT YEAR(NOW()) AS 年;
#案例1：获取员工的入职年份
SELECT YEAR(hiredate) AS 年 FROM employees;
#案例2：计算工龄
SELECT *,YEAR(NOW())-YEAR(hiredate) AS 工龄 
FROM employees
ORDER BY 工龄 DESC;
#5. MONTHNAME(date)月份英文名
SELECT MONTHNAME(NOW()); #January

#6. STR_TO_DATE(str,format)日期字符串转日期
SELECT STR_TO_DATE('1996-12-02','%Y-%c-%d') AS date;
#查询入职日期为1992-4-3的员工信息
SELECT * FROM employees
WHERE hiredate = '1992-4-3';
WHERE hiredate = '1992,4,3';
WHERE hiredate = '1992/4/3'; #只要日期的顺序不变，有间隔符就能被识别

SELECT *
FROM employees
WHERE hiredate=STR_TO_DATE('4/3 1992','%c/%d %Y');
-- WHERE hiredate='4/3 1992';  #对于这种字符串就查不到

#7. DATE_FORMAT(date,format)
SELECT DATE_FORMAT(NOW(),'%Y年%m月%d日');

#8. DATEDIFF(expr1,expr2)#计算相差天数
SELECT DATEDIFF(NOW(),'2020/1/9');

#五、流程控制函数
#1. if函数：if else的效果
SELECT IF(10<5,1,0);
SELECT last_name,commission_pct,if(commission_pct IS NULL,'没有奖金','有奖金') AS 奖金情况
FROM employees;

#2. case函数的使用一：switch case的效果

/*
case 要判断的字段或者表达式
when 常量1 then要显示的值1或者语句1;
when 常量2 then要显示的值2或者语句2;
...
else 要显示的值n或者语句
end
*/
select salary as 原始工资,department_id,
CASE department_id
WHEN 30 THEN salary*1.1
WHEN 40 THEN salary*1.2
WHEN 50 THEN salary*1.3
ELSE salary 
END AS 新工资
FROM employees;

#2. case函数的使用二：多重if else的效果
/*
CASE
WHEN 条件一 THEN 要显示的值1或者语句1
WHEN 条件二 THEN 要显示的值2或者语句2
...
ELSE 要显示的值n或者语句n
*/

/*
案例：查询员工的工资的情况
如果工资>20000，显示A级别
如果工资>15000，显示B级别
如果工资>10000，显示C级别
否则,显示D级别
*/

SELECT last_name,salary,
CASE
WHEN salary>20000 THEN 'A'
WHEN salary>15000 THEN 'B'
WHEN salary>10000 THEN 'C'
ELSE 'D'
END AS 级别
FROM employees;
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

### sql192

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


CREATE TABLE job_grades
(grade_level VARCHAR(3),
lowest_sal INT,
highest_sal INT);

INSERT INTO job_grades
VALUES('A',1000,2999);
INSERT INTO job_grades
VALUES('B',3000,5999);
INSERT INTO job_grades
VALUES('C',6000,9999);
INSERT INTO job_grades
VALUES('D',10000,14999);
INSERT INTO job_grades
VALUES('E',15000,24999);
VALUES('F',25000,40000);

#2、非等值连接
#案例1：查询员工的工资和工资级别

SELECT salary,grade_level
FROM employees e,job_grades j
WHERE e.salary BETWEEN j.lowest_sal AND j.highest_sal;


#3、自连接
#案例：查询 员工名和上级的名称
SELECT e.last_name,e.employee_id,m.last_name,m.employee_id
FROM employees e,employees m
WHERE e.manager_id = m.employee_id;

#内连接练习：
#1. 显示所有员工的姓名，部门号和部门名称
SELECT last_name,d.department_id,d.department_name
FROM employees e,departments d
WHERE e.department_id=d.department_id;


#2.查询90号部门员工的job_id和90号部门的location_id
SELECT e.job_id,location_id,d.department_id
FROM employees e,departments d
WHERE e.department_id=d.department_id
AND e.department_id=90;

#3. 选择所有有奖金的员工的
last_name,department_name,location_id,city

select last_name,d.department_name,loc.location_id,loc.city
FROM employees e,departments d,locations loc
WHERE e.department_id = d.department_id
AND d.location_id=d.location_id
AND e.commission_pct IS NOT NULL;

#4. 选择city在Toronto工作的员工的
last_name,job_id,department_id,department_name


SELECT last_name,job_id,d.department_id,d.department_name
FROM employees e,departments d,locations l
WHERE e.department_id = d.department_id
AND d.location_id = l.location_id
AND l.city='Toronto';


#5. 查询每个工种、每个部门的部门名、工种名和最低工资
SELECT MIN(salary),d.department_name,j.job_title
FROM employees e,jobs j,departments d
WHERE e.department_id = d.department_id
AND e.job_id=j.job_id
GROUP BY e.department_id,e.job_id;


#6. 查询每个国家下的部门个数大于2的国家编号

SELECT count(*),country_id
FROM departments d,locations l
WHERE d.location_id=l.location_id
GROUP BY country_id
HAVING COUNT(*)>2;

#7. 选择指定员工的姓名，员工号，以及他的管理者的姓名和员工号，结果类似于下面的格式
employees Emp manager Mgr

SELECT e.last_name 'employees',e.employee_id 'Emp#',m.last_name 'manager',e.employee_id 'Mgr#'
FROM  employees e,employees m
WHERE e.manager_id=m.employee_id;

```

### sql199

```mysql
#二、sql199语法
/*
内连接（★）
			select 查询列表
			from 表1 别名 【连接类型】
			JOIN 表2 别名
			ON 连接条件
			【where 筛选条件】
外连接
			左外（★）：left 【outer】
			右外（★）：right 【outer】
			全外
交叉连接：cross

*/



# 一）内连接
/*
语法：
SELECT 查询列表
from 表1 别名
inner join 表2 别名
on 连接条件; 

分类：
等值
非等值
自连接

*/


#1、等值连接
#案例1.查询员工名、部门名
SELECT e.last_name,d.department_name
FROM employees e
INNER JOIN departments d
ON e.department_id=d.department_id;


#案例2. 查询名字中包含e的员工名和工种名（添加筛选）
SELECT last_name,job_title
FROM employees e
INNER JOIN jobs j
ON e.job_id=j.job_id
WHERE last_name LIKE '%e%';


#3. 添加部门个数>3的城市名和部门个数，（添加分组+筛选）
SELECT city,COUNT(*) 部门个数
FROM locations l
INNER JOIN departments d
ON l.location_id=d.location_id
GROUP BY city
HAVING COUNT(*)>3;

#4. 查询哪个部门的部门员工个数>3的部门名和员工个数，并按照降序（添加排序）

SELECT department_name,COUNT(*)
FROM departments d
INNER JOIN employees e
ON d.department_id=e.department_id
GROUP BY department_name
ORDER BY COUNT(*) DESC;

#5. 查询员工名、部门名、工种名、并按部门名降序（多表查询）
SELECT last_name,department_name,job_title
FROM employees e
INNER JOIN departments d
ON e.department_id=d.department_id
INNER JOIN jobs j
ON e.job_id=j.job_id
ORDER BY department_name DESC;


不等值连接和自连接同理可得

#二、外连接
/*
应用场景：用于查询一个表中有，另一个表没有的记录
特点：
1、外连接的查询结果为主表中的所有记录
		如果从表中没有和它匹配的，则显示匹配的值
		如果从表中没有和它匹配的，则显示null
		外连接查询结果=内连接结果+主表中有而从表中没有的记录
2、左外连接中，left JOIN左边的是主表
	 右外连接，right join右边的是主表
3、左外和右外交换两个表的顺序，可以实现同样的效果
4、全外连接=内连接的结果+表1中有但表2没有的+表2中有但表1中没有的
*/
#引入：查询没有男朋友的女神名
USE girls;
SELECT * FROM beauty;
SELECT * FROM boys;


SELECT b.name,bo.*
FROM beauty b
LEFT OUTER JOIN boys bo
ON b.boyfriend_id = bo.id
WHERE ISNULL(bo.id);


USE myemployees;
#查询哪个部门没有员工
#左外
SELECT d.*,e.employee_id
FROM departments d
LEFT OUTER JOIN employees e
ON d.department_id=e.department_id
WHERE e.employee_id IS NULL;
#右外
SELECT d.*,e.employee_id
FROM employees e
RIGHT OUTER JOIN departments d
ON d.department_id=e.department_id
WHERE e.employee_id IS NULL;


#不支持
use girls;
SELECT b.*,bo.*
FROM beauty b
FULL OUTER JOIN boys bo
ON b.boyfriend_id = bo.id;

#二）交叉连接(就是笛卡尔乘积)
SELECT b.*,bo.*
FROM beauty b
CROSS JOIN boys bo; 
```

## 子查询

```mysql
#7. 子查询
/*
含义：
出现在其他语句中的select语句，称为子查询或内查询
外部的查询语句，称为主查询或外查询

分类：
按子查询出现的位置：
		SELECT后面：仅仅支持标量子查询
	  FROM后面：支持表子查询
	★ WHERE或HAVING后面：标量子查询;列（行）子查询
		EXISTS后面（相关子查询）：表子查询

按结果集的行列数不同：
		标量子查询（结果集只有一行一列）
		列子查询（结果集只有一列多行）
		行子查询（结果集有一行多列）
		表子查询（结果集一般为多行多列）
*/
#一、where或having后面
1、标量子查询（单行子查询）
2、列子查询（多行子查询）
3、行子查询（多列多行）
特点：
①子查询放在小括号内
②子查询一般放在条件的右侧
③标量子查询，一般搭配着单行操作符使用
> < >= <= = <>

列子查询，一般搭配着多行操作符使用
IN、any/SOME、all


1.标量子查询
#案例1：谁的工资比Abel高？
#①查询Abel的工资
SELECT salary
FROM employees
WHERE last_name='Abel';

#②查询员工的信息，满足salary>①结果
SELECT *
FROM employees
WHERE salary>(
	SELECT salary
	FROM employees
	WHERE last_name='Abel'
);


#案例2：返回job_id与141员工相同，salary比143号员工多的员工 姓名,job_id和工资

#①查询141号员工的job_id
SELECT job_id
FROM employees
WHERE employee_id=141

#②查询143员工的salary
SELECT salary
FROM employees
WHERE employee_id=143

#③将①,②的结果作为条件
SELECT last_name,job_id,salary
FROM employees
WHERE job_id=(
	SELECT job_id
	FROM employees
	WHERE employee_id=141
)
AND salary>(
	SELECT salary
	FROM employees
	WHERE employee_id=143
);

#案例3：返回工资最少的员工的last_name,job_id和salary

#①先插最低工资
SELECT MIN(salary)
FROM employees;

#②再查salary为①结果的信息
SELECT last_name,job_id,salary
FROM employees
WHERE salary=(
	SELECT MIN(salary)
	FROM employees
);

#案例4：查询最低工资大于50号部门最低工资的部门id和其最低工资
#①查询50号部门的最低工资
SELECT MIN(salary)
FROM employees
WHERE department_id=50;

#②查询每个部门的最低工资大于①的结果
SELECT department_id,MIN(salary)
FROM employees
GROUP BY department_id
HAVING MIN(salary)>(
	SELECT MIN(salary)
	FROM employees
	WHERE department_id=50
);
#非法使用子查询
#子查询结果不是一行一列的查询对于标量子查询都是非法的


2. 多行子查询（列子查询）
#案例1：返回location_id是1400或1700的部门中的所有员工姓名
#①查询location_id是1400或1700的部门
SELECT department_id
FROM departments
WHERE location_id=1400
OR location_id=1700;

#②查询员工姓名，要求部门号是①列表中的某一个
SELECT last_name
FROM employees
WHERE department_id IN(
	SELECT department_id
	FROM departments
	WHERE location_id=1400
	OR location_id=1700
);

#案例2：返回其它工种中比job_id为`IT_PROG`的工种任一工资低的员工的员工号、姓名、job_id
#以及salary

#①先查询job_id为`IT_PROG`的工种的工资(去重)
SELECT DISTINCT salary
FROM employees
WHERE job_id='IT_PROG';

#②再查询比其任一低的员工信息
SELECT employee_id,last_name,job_id,salary
FROM employees
WHERE salary>ANY(
	SELECT DISTINCT salary
	FROM employees
	WHERE job_id='IT_PROG'
) AND job_id <> 'IT_PROG';

3.行子查询（结果集一行多列或多行多列）
#案例：查询员工编号最小并且工资最高的员工信息
SELECT *
FROM employees
WHERE (employee_id,salary)=(
				SELECT MIN(employee_id),MAX(salary)
				FROM employees
)
#①查询最小的员工编号
SELECT MIN(employee_id)
FROM employees

#②查询最高工资
SELECT MAX(salary)
FROM employees

#③查询员工信息
SELECT *
FROM employees
WHERE employee_id=( 
	SELECT MIN(employee_id)
	FROM employees
) AND salary=(
	SELECT MAX(salary)
	FROM employees
);


#二、select后面(仅仅支持标量子查询)
#案例1：查询每个部门的员工人数，并显示部门信息
SELECT d.*,(
	SELECT COUNT(*)
	FROM employees e
	WHERE e.department_id = d.department_id
) 个数
FROM departments d;

#案例2：查询员工号=102的部门名
SELECT (
			SELECT department_name
			FROM employees e
			INNER JOIN departments d
			ON e.department_id=d.department_id
			WHERE e.employee_id=102
);


#三、from后面
/*
将子查询结果充当一张表，要求必须起别名
*/
#案例：查询每个部门的平均工资的工资等级
#①先查询每个部门的平均工资

SELECT AVG(salary),department_id
FROM employees e
GROUP BY e.department_id;

#②连接①的结果集和job_grades表，筛选条件平均工资


SELECT ag_dep.*,g.grade_level
FROM(
	SELECT AVG(salary) ag,department_id
	FROM employees e
	GROUP BY e.department_id
) ag_dep
INNER JOIN job_grades g
ON ag_dep.ag BETWEEN lowest_sal and highest_sal;


#四、exists后面（相关子查询）
#判断子查询结果是否为空
SELECT EXISTS(SELECT employee_id FROM employees WHERE salary=30000);

#案例1：查询有员工的部门名
SELECT department_name
FROM departments d
WHERE EXISTS(
	SELECT *
	FROM employees e
	WHERE d.department_id=e.department_id
);

SELECT DISTINCT d.department_name
FROM employees e
RIGHT JOIN departments d
ON d.department_id=e.department_id
WHERE e.employee_id IS NOT NULL;

SELECT department_id
FROM departments d
WHERE d.department_id IN (
	SELECT department_id
	FROM employees
);


#作业
#1. 查询据额Zlotkey相同部门的员工姓名和工资
#①查询Zlotkey的部门
SELECT department_id
FROM employees
WHERE last_name='Zlotkey';

SELECT last_name,salary,department_id
FROM employees
WHERE department_id=(
	SELECT department_id FROM employees WHERE last_name='Zlotkey'
);

#查询工资比公司平均工资高的部门的员工的员工号，姓名和工资
#①先查询平均工资
SELECT AVG(salary)
FROM employees;

#②查询比①高的工资
SELECT employee_id,last_name,salary
FROM employees
WHERE salary>(
	SELECT AVG(salary)
	FROM employees
);


#3.查询各部门中工资比本部门平均工资高的员工的员工号，姓名和工资
#①先查询每个部门的平均工资
SELECT AVG(salary),department_id
FROM employees
GROUP BY department_id;

#②再用内连接进行工资查询
SELECT employee_id,last_name,salary
FROM employees e
INNER JOIN (
	SELECT AVG(salary) avg,department_id
	FROM employees 
	GROUP BY department_id
)avg_dp
ON e.department_id=avg_dp.department_id
WHERE e.salary>avg_dp.avg;

#4. 查询和姓名中包含字母u的员工在相同部门的员工的员工号和姓名
#①查询姓名中包含字母u的员工的部门
SELECT department_id
FROM employees
WHERE last_name LIKE '%u%';

SELECT employee_id,last_name
FROM employees
WHERE department_id IN (
SELECT department_id FROM employees WHERE last_name LIKE '%u%'
)


#5.查询部门的location_id为1700的部门工作的员工的员工号
#①查询location_id为1700的部门
SELECT department_id
FROM departments
WHERE location_id=1700

SELECT employee_id
FROM employees
WHERE department_id IN (
	SELECT department_id
	FROM departments
	WHERE location_id=1700
);

#6. 查询管理者是King的员工姓名和工资
#自连接方式
select e.last_name,e.salary
FROM employees e
INNER JOIN employees m
ON e.manager_id = m.employee_id
WHERE m.last_name='K_ing';
#标量子查询的方式
SELECT last_name,salary
FROM employees
WHERE manager_id IN (
	SELECT employee_id
	FROM employees
	WHERE last_name='K_ing'
);

#7.查询工资最高的员工的姓名，要求first_name和last_name显示为一列，列名为 姓名
#①查询最高工资
SELECT MAX(salary)
FROM employees


#②查询工资=①的姓,名
SELECT CONCAT(first_name,last_name) "姓.名"
FROM employees
WHERE salary =(
SELECT MAX(salary) FROM employees
)

#8.查询平均工资最低的部门信息(两步实现,精简版)
#①查询各部门平均工资最低的部门
SELECT department_id
FROM employees
GROUP BY department_id
ORDER BY AVG(salary) 
LIMIT 1;

SELECT *
FROM departments
WHERE department_id=(
	SELECT department_id
	FROM employees
	GROUP BY department_id
	ORDER BY AVG(salary) 
	LIMIT 1
)


#3.查询平均工资最低的部门信息和该部门的平均工资
#①查询平均工资最低的部门id和工资
SELECT AVG(salary) ag,department_id
FROM employees
GROUP BY department_id
ORDER BY  ag
LIMIT 1;

#利用内连接进行筛选
SELECT d.*,avg_dp.ag
FROM departments d
INNER JOIN (
	SELECT AVG(salary) ag,department_id
	FROM employees
	GROUP BY department_id
	ORDER BY  ag
	LIMIT 1
) avg_dp
ON d.department_id=avg_dp.department_id;
```

## 分页查询

```mysql
#进阶8：分页查询 ★
/*
应用场景：当要显示的数据，一页显示不全，需要分页提交sql请求
语法：
	select 查询列表
	from 表
	【join type】 join 表2
	on 连接条件
	where 筛选条件
	GROUP BY 分组字段
	having 分组后的筛选
	order by 排序的字段
	limit offset,size;
	
	offset要显示条目的其实索引（起始索引从0开始）
	size 要显示的条目个数
特点：
		①limit语句放在查询语句的最后
		②公式
		要显示的页数 page，每页的条目数size
		
		select 查询列表
		from 表
		limit (page-1)*size,size
*/

#案例1：查询前五条员工信息
select * from employees LIMIT 0,5;

#案例2：查询第11条——第25条
select * FROM employees LIMIT 10,15;

#案例3：有奖金的员工信息，并且工资较高的前10名显示出来
SELECT *
FROM employees
WHERE commission_pct IS NOT NULL
ORDER BY salary DESC
LIMIT 10;
```

## 联合查询

```mysql
#进阶9：联合查询
/*
UNION 联合 合并：将多条查询语句的结果合并成一个结果
语法：
查询语句1
UNION
查询语句2
UNION
...

应用场景：
要查询的结果来自于多个表，且多个表没有直接的连接关系，但查询的信息一致时

特点：
1、要求多条查询语句的查询列数是一致的；
2、要求多条查询语句的查询的每一列的类型和顺序最好一致
3、union关键字默认去重，加all关键字可避免
*/

#引入的案例：查询部门编号>90或者邮箱包含a的员工信息
#原本
SELECT * 
FROM employees 
WHERE email 
LIKE '%a%' 
OR department_id>90;

#联合查询方式
SELECT * 
FROM employees 
WHERE email 
LIKE '%a%' 
UNION
SELECT * 
FROM employees 
WHERE department_id>90;

#案例：查询中国用户中男性的信息以及外国用户中年男性的用户信息
SELECT id,cname,csex FROM t_ca WHERE csex='男'
UNION
SELECT t_id,tName,tGender FROM t_ua WHERE tGender='male';

```

# DML语言

数据操作语言：

- 插入：insert
- 修改：update
- 删除：delete

## 插入

```mysql
#一、插入语句
/*
语法：
INSERT INTO 表名(列名,...)
VALUES(值1,...)
*/
USE girls;
#1.插入的值的类型要与列对类型一致或兼容
INSERT INTO beauty(id,NAME,sex,borndate,phone,photo,boyfriend_id)
VALUES(13,'唐艺昕','女','1990-4-23','189999999',NULL,2);

#2.不可以为null的列必须插入值。可以为null的列如何插入值？
#方式一：直接填充null

#方式二：不要相关字段
INSERT INTO beauty(id,NAME,sex,borndate,phone,boyfriend_id)
VALUES(14,'唐溪','女','1996-4-23','1899449999',2);

#3.列的顺序可以调换
#4.列数和值必须一致
#5.可以省略字段名，默认所有列，而且列的顺序和表中列的顺序一致
INSERT INTO beauty
VALUES(18,'张飞','男',NULL,'119',NULL,NULL);


#方式二：
/*
语法：
insert INTO 表名
set 列名=值,列名=值,...
*/

INSERT INTO beauty
SET id=19,NAME='刘涛',phone='999';


#两种方式对比：
1、方式一支持多行
INSERT INTO beauty(id,NAME,sex,borndate,phone,boyfriend_id)
VALUES(20,'唐溪','女','1996-4-23','1899449999',2),
(21,'唐溪','女','1996-4-23','1899449999',2),
(22,'唐溪','女','1996-4-23','1899449999',2);


2、方式一支持子查询
INSERT INTO beauty(id,NAME,phone)
SELECT 26,'宋茜','11809866';
```

## 修改

```mysql
#二、修改语句
/*
1.修改单表的记录★
语法：
update 表名
set 列=新值,列=新值,...
WHERE 筛选条件；

2.修改多表的记录【补充】

*/



#1.修改单表的记录
#案例1：修改beauty表中姓唐的女神的电话为13838383838
UPDATE beauty
SET phone=13838383838
WHERE name LIKE '唐%';


#2.修改多表的记录
#案例1：修改张无忌的女朋友的手机号为114

#先找出张无忌的女朋友
update beauty
SET phone='114'
WHERE boyfriend_id=(
	SELECT id 
	FROM boys
	WHERE boyName='张无忌'
)

#案例2：修改没有男朋友的女神的男朋友编号都为2号
update beauty b
LEFT JOIN boys bo
ON b.boyfriend_id=bo.id
SET b.boyfriend_id=2
WHERE bo.id is NULL;
```

## 删除

```mysql
#三、删除语句
/*
方式一：delete
语法：
1.单表的删除【★】
delete from 表名 WHERE 筛选条件
2.多表的删除【补充】
sql192
delete 表1的别名，表2的别名
from 表1 别名，表2 别名
where 连接条件
and 筛选条件;

sql99语法：
delete 表1的别名,表2的别名
from 表1 别名
inner|left|right| join 表2 别名 on 连接条件
where 筛选条件


方式二：truncate
语法：truncate table 表名；

*/

#方式一：delete
#1.单表的删除
#案例1：删除手机号以9结尾的女神信息

DELETE FROM beauty WHERE phone LIKE '%9';

#2.多表的删除
#案例：删除张无忌的女朋友的信息
DELETE b
FROM beauty b
INNER JOIN boys bo ON bo.id=b.boyfriend_id
WHERE bo.boyName='张无忌';

#案例：删除黄晓明的信息以及他女朋友的信息
DELETE b,bo
FROM beauty b
INNER JOIN boys bo ON b.boyfriend_id=bo.id
WHERE bo.boyName='黄晓明'

#方式二：truncate语句（清空）

#案例：将魅力值>100的男神信息删除(不能实现)
TRUNCATE table boys;


delete与truncate的区别
1.truncate是清空不能加where
2.truncate效率更高
3.对于自增长列来说，如果用delete删除后再插入数据，自增长列的值从断电开始，而truncate删除后，
再插入数据，自增长列的值从1开始。
4.truncate删除没有返回值
5.truncate删除不能回滚，delete删除可以回滚
```

# DDL

