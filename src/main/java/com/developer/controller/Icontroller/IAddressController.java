package com.developer.controller.Icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.developer.dto.requestDto.AddressRequestDto;

@RequestMapping(path = { "/address" })
public interface IAddressController {

	@PostMapping(path = { "/saveAddress" })
	ResponseEntity<?> address(@RequestBody final AddressRequestDto addressRequestDto);
}
