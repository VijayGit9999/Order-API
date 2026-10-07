package in.Order_API.Mapper;

import org.modelmapper.ModelMapper;

import in.Order_API.Dto.OrderDto;
import in.Order_API.Entity.OrderEntity;

public class OrderMapper {
	
	   private static ModelMapper mapper = new ModelMapper();

	 /*   public static OrderDto convertToDto(OrderEntity orderEntity) {
	        return mapper.map(orderEntity, OrderDto.class);
	    } */
	   
	   public static OrderDto convertToDto(OrderEntity orderEntity) {

		    OrderDto orderDto = new OrderDto();

		    orderDto.setOrderId(orderEntity.getOrderId());
		    orderDto.setOrderTrackingNum(orderEntity.getOrderTrackingNum());
		    orderDto.setCustomerEmail(orderEntity.getCustomerEmail());
		    orderDto.setTotalPrice(orderEntity.getTotalPrice());
		    orderDto.setTotalQuantity(orderEntity.getTotalQuantity());
		    orderDto.setOrderStatus(orderEntity.getOrderStatus());
		    orderDto.setPaymentStatus(orderEntity.getPaymentStatus());
		    orderDto.setRazorpayOrderId(orderEntity.getRazorpayOrderId());
		    orderDto.setRazorpayPaymentId(orderEntity.getRazorpayPaymentId());

		    return orderDto;
		}

		

	    public static OrderEntity convertToEntity(OrderDto orderDto) {
	        return mapper.map(orderDto, OrderEntity.class);
	    }

}
