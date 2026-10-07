package in.Order_API.Mapper;

import org.modelmapper.ModelMapper;

import in.Order_API.Dto.AddressDto;
import in.Order_API.Entity.ShippingAddressEntity;

public class AddressMapper {
	
    private static ModelMapper mapper = new ModelMapper();

    public static AddressDto convertToDto(ShippingAddressEntity addrEntity) {
        return mapper.map(addrEntity, AddressDto.class);
    }

    public static ShippingAddressEntity toEntity(AddressDto addrDto) {
        return mapper.map(addrDto, ShippingAddressEntity.class);
    }

}
