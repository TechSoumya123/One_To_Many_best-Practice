package com.developer.service.serviceImpl;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.developer.dto.requestDto.EmployeeRequestDto;
import com.developer.model.Employee;
import com.developer.repository.AddressRepository;
import com.developer.repository.EmployeeRepository;
import com.developer.service.Iservice.EmployeeService;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final AddressRepository addressRepository;

	final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Transactional
	@Override
	public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {
		var employee = new Employee().setName(employeeRequestDto.employeeName());
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmploees() {
		return employeeRepository.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public Employee getEmployeeBYId(Long empId) {
		var employee = getEmployee(empId).get();
		return employee;
	}

	@Transactional
	@Override
	public Employee updateEmployee(Long empId, EmployeeRequestDto employeeRequestDto) {
		return getEmployee(empId).map(emp -> {
			emp.setName(employeeRequestDto.employeeName());
			return emp;
		}).get();
	}

	@Transactional
	@Override
	public Employee deleteEmployeeButNotAddress(Long empId) {
		return getEmployee(empId).map(employee -> {
			if (employee.getAddressList().isEmpty()) {
				employeeRepository.delete(employee);
			}
			employee.getAddressList().stream().forEach(address -> address.setEmployee(null));
			employee.setAddressList(null);
			employeeRepository.delete(employee);
			return employee;
		}).get();
	}

	@Transactional
	@Override
	public Employee removeEmployeeFromAddress(Long empId) {
		return getEmployee(empId).map(emp -> {
			if (isEmpty(emp.getAddressList())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee could not have any address");
			}
			emp.getAddressList().stream().forEach(address -> address.setEmployee(null));
			emp.setAddressList(null);
			return emp;
		}).get();
	}

	@Transactional
	@Override
	public Employee removeParticularAddressFromEmployee(Long empId, Long addressId) {		
		return getEmployee(empId).map(emp -> {
			var address = addressRepository.findById(addressId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found with this id " + addressId));
			if (!emp.getAddressList().contains(address)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee could not have any address");
			}
			address.setEmployee(null);
			return emp;
		}).get();
	}

	@Override
	public Optional<Employee> getEmployee(Long empId) {
		return Optional.ofNullable(employeeRepository.findById(empId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, employee not found")));
	}

	@Transactional
	@Override
	public Employee addAddressToEmployee(Long empId, Long addressId) {
		return getEmployee(empId).map(emp -> {
			var address = addressRepository.findById(addressId).orElseThrow();
			if (emp.getAddressList().contains(address)) {
				throw new NoResultException("Employee already contain this address");
			}
			address.setEmployee(emp);
			return emp;
		}).get();

	}

}
