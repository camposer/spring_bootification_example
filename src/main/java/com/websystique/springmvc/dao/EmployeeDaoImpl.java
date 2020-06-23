package com.websystique.springmvc.dao;

import com.websystique.springmvc.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	public Employee findById(int id) {
		return getByKey(id);
	}

	public void saveEmployee(Employee employee) {
		persist(employee);
	}

	public void deleteEmployeeBySsn(String ssn) {
		entityManager.createQuery("delete from Employee e where e.ssn = :ssn")
				.setParameter("ssn", ssn)
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		return entityManager.createQuery("from Employee e", Employee.class).getResultList();
	}

	public Employee findEmployeeBySsn(String ssn) {
		List<Employee> employees = entityManager.createQuery("from Employee e where e.ssn = :ssn", Employee.class).setParameter("ssn", ssn).getResultList();
		if (employees.isEmpty()) return null;
		return employees.get(0);
	}
}
