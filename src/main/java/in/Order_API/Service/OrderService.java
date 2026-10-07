package in.Order_API.Service;

import java.util.List;

import in.Order_API.Dto.OrderDto;
import in.Order_API.Dto.PurchaseOrderRequestDto;
import in.Order_API.Dto.PurhaseOrderResponseDto;
import in.Order_API.Dto.UpdateOrderRequestDto;

public interface OrderService {

	
	public PurhaseOrderResponseDto createOrder(PurchaseOrderRequestDto orderRequestDto) throws Exception;
	
	public PurhaseOrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto);
	
	public PurhaseOrderResponseDto cancelOrder(String orderTrackingNumber) throws Exception;
	
	public List<OrderDto> getCustomerOrders(String customerEmail);
	
}
