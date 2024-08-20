package com.developer.service.Iservice;

import com.developer.dto.requestDto.AddressRequestDto;
import com.developer.model.Address;

public interface AddressService {
	
	Address saveAddress(AddressRequestDto addressRequestDto);
}
