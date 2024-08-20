package com.developer.controller.controllerImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.developer.controller.Icontroller.IEmployeeController;
import com.developer.dto.requestDto.EmployeeRequestDto;
import com.developer.model.Employee;
import com.developer.service.Iservice.EmployeeService;

import jakarta.validation.constraints.Min;

@RestController
public class EmployeeControllerImpl implements IEmployeeController {

	private final EmployeeService employeeService;

	public EmployeeControllerImpl(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public ResponseEntity<?> saveEmployee(EmployeeRequestDto employeeRequestDto) {
		try {
			Employee saveEmployee = employeeService.saveEmployee(employeeRequestDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getAllEmployee() {
		try {
			List<Employee> emploees = employeeService.getAllEmploees();
			return emploees.isEmpty() ? ResponseEntity.noContent().build()
					: ResponseEntity.status(HttpStatus.OK).body(emploees);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> getEmployeeBYId(Long empId) {
		try {
			Employee employee = employeeService.getEmployeeBYId(empId);
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateEmployee(Long empId, EmployeeRequestDto employeeRequestDto) {
		try {
			Employee updateEmployee = employeeService.updateEmployee(empId, employeeRequestDto);
			return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteEmployeeButNotAddress(Long empId) {
		try {
			Employee deleteEmployee = employeeService.deleteEmployeeButNotAddress(empId);
			return ResponseEntity.ok(deleteEmployee);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> removeEmployeeFromAddress(Long empId) {
		try {
			Employee removeEmployee = employeeService.removeEmployeeFromAddress(empId);
			return ResponseEntity.status(HttpStatus.OK).body(removeEmployee);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> removeParticularAddressFromEmployee(Long empId, Long addressId) {
		try {
			Employee removeParticularAddress = employeeService.removeParticularAddressFromEmployee(empId, addressId);
			return ResponseEntity.status(HttpStatus.OK).body(removeParticularAddress);
		} catch (ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}

	}

	@Override
	public ResponseEntity<?> addAddressToEmployee(
			@Min(value = 1, message = "Employee id must be grater than 0") Long empId,
			@Min(value = 1, message = "Address id must be grater than 0") Long addressId) {
		try {
			Employee employee=employeeService.addAddressToEmployee(empId, addressId);
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		}catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
