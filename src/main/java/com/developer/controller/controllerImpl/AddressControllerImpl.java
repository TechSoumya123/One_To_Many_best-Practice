package com.developer.controller.controllerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.developer.controller.Icontroller.IAddressController;
import com.developer.dto.requestDto.AddressRequestDto;
import com.developer.model.Address;
import com.developer.service.Iservice.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements IAddressController {

	private final AddressService addressService;

	@Override
	public ResponseEntity<?> address(AddressRequestDto addressRequestDto) {
		try {
			Address saveAddress = addressService.saveAddress(addressRequestDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveAddress);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
