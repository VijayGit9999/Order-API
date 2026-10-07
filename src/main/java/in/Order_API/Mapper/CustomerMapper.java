package in.Order_API.Mapper;

import org.modelmapper.ModelMapper;

import in.Order_API.Dto.CustomerDto;
import in.Order_API.Entity.CustomerEntity;

public class CustomerMapper {
	
	   private static ModelMapper mapper = new ModelMapper();

	    public static CustomerDto convertToDto(CustomerEntity customerEntity) {
	        return mapper.map(customerEntity, CustomerDto.class);
	    }

	    public static CustomerEntity convertToEntity(CustomerDto customerDto) {
	        return mapper.map(customerDto, CustomerEntity.class);
	    }

}
