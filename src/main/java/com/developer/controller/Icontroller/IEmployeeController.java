package com.developer.controller.Icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.developer.dto.requestDto.EmployeeRequestDto;

@RequestMapping(path = { "/employee" })
public interface IEmployeeController {

	@PostMapping(path = { "/save" })
	ResponseEntity<?> saveEmployee(@RequestBody final EmployeeRequestDto employeeRequestDto);

	@GetMapping(path = { "/getAll" })
	ResponseEntity<?> getAllEmployee();

	@GetMapping(path = { "/get-By/{id}" })
	ResponseEntity<?> getEmployeeBYId(@PathVariable("id") final Long empId);

	@PutMapping(path = { "/update/{id}" })
	ResponseEntity<?> updateEmployee(@PathVariable("id") final Long empId,
			@RequestBody final EmployeeRequestDto employeeRequestDto);

	@DeleteMapping(path = { "/deleteEmployeeButNotAddress/{id}" })
	ResponseEntity<?> deleteEmployeeButNotAddress(@PathVariable("id") final Long empId);

	@PostMapping(path = { "/removeEmployee/{id}" })
	ResponseEntity<?> removeEmployeeFromAddress(@PathVariable("id") final Long empId);

	@PostMapping(path = { "/particularAddress/{id}/{addressId}" })
	ResponseEntity<?> removeParticularAddressFromEmployee(@PathVariable("id") final Long empId,
			@PathVariable("addressId") final Long addressId);

	@PostMapping(path = { "/employee/{eId}/address/{addId}" })
	ResponseEntity<?> addAddressToEmployee(@PathVariable("eId")final Long empId, @PathVariable("addId") final Long addressId);
}
