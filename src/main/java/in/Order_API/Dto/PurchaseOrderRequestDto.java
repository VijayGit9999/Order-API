package in.Order_API.Dto;

import java.util.List;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {
	
	private CustomerDto customerDto;
    private AddressDto addressDto;
    private OrderDto orderDto;
    private List<OrderItemDto> orderItemDtoList;

}
