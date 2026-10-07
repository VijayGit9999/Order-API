package in.Order_API.Mapper;

import org.modelmapper.ModelMapper;

import in.Order_API.Dto.OrderItemDto;
import in.Order_API.Entity.OrderItemEntity;

public class OrderItemMapper {
	
	  private static ModelMapper mapper = new ModelMapper();

	    public static OrderItemDto convertToDto(OrderItemEntity orderItemEntity) {
	        return mapper.map(orderItemEntity, OrderItemDto.class);
	    }

	    public static OrderItemEntity convertToEntity(OrderItemDto orderItemDto) {
	        return mapper.map(orderItemDto, OrderItemEntity.class);
	    }

}
