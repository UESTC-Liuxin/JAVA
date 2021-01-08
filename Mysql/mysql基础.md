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
#in的使用
select
	*
from
	employees
where
	job_id in('AD_PRES','AD_VP');
```

