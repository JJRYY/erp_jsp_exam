select * from employee;
select * from title;
select * from department;

select EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, HIREDATE from employee;

-- EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, HIREDATE
-- TITLE_NO, TITLE_NAME
-- DEPT_NO, DEPT_NAME, FLOOR

select EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE from vw_full_employee;
select EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE from vw_full_employee where EMP_NO = ?;
insert into employee values (?, ?, ?, ?, ?, ?, ?);
update employee set EMP_NAME = ?, TNO = ?, MANAGER = ?, SALARY = ?, DNO = ?, HIREDATE = ? where EMP_NO = ?;
delete from employee where EMP_NO = ?;


-- view
create or replace view vw_full_employee
as
select e.EMP_NO
	, e.EMP_NAME
	, t.TITLE_NO as TITLE_NO
	, t.TITLE_NAME as TITLE_NAME
	, e.MANAGER as MANAGER_NO
	, m.EMP_NAME as MANAGER_NAME
	, e.SALARY
	, d.DEPT_NO
	, d.DEPT_NAME
	, d.FLOOR
	, e.HIREDATE
	from employee e join title t on e.TNO = t.TITLE_NO
		left join employee m on e.MANAGER = m.EMP_NO
		join department d on e.DNO = d.DEPT_NO ;
		
select * from vw_full_employee;