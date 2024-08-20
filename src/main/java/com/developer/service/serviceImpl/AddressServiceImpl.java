package com.developer.service.serviceImpl;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.developer.dto.requestDto.AddressRequestDto;
import com.developer.model.Address;
import com.developer.repository.AddressRepository;
import com.developer.service.Iservice.AddressService;
import com.developer.service.Iservice.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final EmployeeService employeeService;

	@Transactional
	@Override
	public Address saveAddress(AddressRequestDto addressRequestDto) {
		Address address = new Address().setCity(addressRequestDto.city()).setState(addressRequestDto.state())
				.setDistrict(addressRequestDto.district()).setZipCode(addressRequestDto.zipCode());

		if (Objects.isNull(addressRequestDto.empId()))
			return addressRepository.save(address);

		employeeService.getEmployee(addressRequestDto.empId()).map(emp -> {
			address.setEmployee(emp);
			return emp;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, Employee not fonnd"));
		return addressRepository.save(address);
	}

}
