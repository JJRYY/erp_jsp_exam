select * from employee;
select * from title;
select * from department;

select EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, HIREDATE from employee;

-- EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, HIREDATE
-- TITLE_NO, TITLE_NAME
-- DEPT_NO, DEPT_NAME, FLOOR

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