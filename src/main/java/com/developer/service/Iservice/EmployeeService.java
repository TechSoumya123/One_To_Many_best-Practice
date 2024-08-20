package com.developer.service.Iservice;

import java.util.List;
import java.util.Optional;

import com.developer.dto.requestDto.EmployeeRequestDto;
import com.developer.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(EmployeeRequestDto employeeRequestDto);

	List<Employee> getAllEmploees();

	Employee getEmployeeBYId(Long empId);

	Optional<Employee> getEmployee(Long empId);

	Employee updateEmployee(Long empId, EmployeeRequestDto employeeRequestDto);

	Employee deleteEmployeeButNotAddress(Long empId);

	Employee removeEmployeeFromAddress(Long empId);

	Employee removeParticularAddressFromEmployee(Long empId, Long addressId);
	Employee 	addAddressToEmployee(Long empId,Long addressId);

}
