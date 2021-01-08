
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
	
    