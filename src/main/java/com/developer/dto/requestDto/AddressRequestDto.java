package com.developer.dto.requestDto;

import java.io.Serializable;

public record AddressRequestDto(

		String city,

		String state,

		String district,

		Integer zipCode,

		Long empId) implements Serializable {

}
