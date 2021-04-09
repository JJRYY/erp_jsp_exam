package erp_jsp_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import erp_jsp_exam.dao.EmployeeDao;
import erp_jsp_exam.dto.Department;
import erp_jsp_exam.dto.Employee;
import erp_jsp_exam.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	private Connection con;

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		// EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE
		String sql = "select EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, "
				+ "MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE " 
				+ "from vw_full_employee";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if (rs.next()) {
				List<Employee> list = new ArrayList<Employee>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		// EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE
		int empNo = rs.getInt("EMP_NO");
		String empName = rs.getString("EMP_NAME");
		Title title = new Title(rs.getInt("TITLE_NO"));
		Employee manager = new Employee(rs.getInt("MANAGER_NO"));
		int salary = rs.getInt("SALARY");
		Department dept = new Department(rs.getInt("DEPT_NO"));
		Date hireDate = rs.getTimestamp("HIREDATE");
		
		try {
			title.setName(rs.getString("TITLE_NAME"));
		} catch (SQLException e) {}
		
		try {
			manager.setName(rs.getString("MANAGER_NAME"));
		} catch (SQLException e) {}
		
		try {
			dept.setName(rs.getString("DEPT_NAME"));
		} catch (SQLException e) {}
		
		try {
			dept.setFloor(rs.getInt("FLOOR"));
		} catch (SQLException e) {}
		
		
		return new Employee(empNo, empName, title, manager, salary, dept, hireDate);
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select EMP_NO, EMP_NAME, TITLE_NO, TITLE_NAME, MANAGER_NO, "
				+ "MANAGER_NAME, SALARY, DEPT_NO, DEPT_NAME, FLOOR, HIREDATE " 
				+ "from vw_full_employee "
				+ "where EMP_NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getNo());
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getNo());
			pstmt.setString(2, employee.getName());
			pstmt.setInt(3, employee.getTitle().getNo());
			pstmt.setInt(4, employee.getManager().getNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDept().getNo());
			pstmt.setTimestamp(7, new Timestamp(employee.getHireDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee set EMP_NAME = ?, TNO = ?, MANAGER = ?, SALARY = ?, DNO = ?, HIREDATE = ? where EMP_NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, employee.getName());
			pstmt.setInt(2, employee.getTitle().getNo());
			pstmt.setInt(3, employee.getManager().getNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDept().getNo());
			pstmt.setTimestamp(6, new Timestamp(employee.getHireDate().getTime()));
			pstmt.setInt(7, employee.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "delete from employee where EMP_NO = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, employee.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
