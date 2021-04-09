-- 계정 권한 부여
grant all 
   on erp_jsp_exam.* 
   to 'erp_jsp_exam'@'localhost' identified by 'rootroot';

-- file 권한(backup, load) -- root만 권한 부여 가능
GRANT File 
   ON *.* 
   TO 'erp_jsp_exam'@'localhost';