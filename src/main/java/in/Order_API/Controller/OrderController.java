package in.Order_API.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.Order_API.Dto.OrderDto;
import in.Order_API.Dto.PurchaseOrderRequestDto;
import in.Order_API.Dto.PurhaseOrderResponseDto;
import in.Order_API.Dto.UpdateOrderRequestDto;
import in.Order_API.Response.ApiResponse;
import in.Order_API.Service.OrderServiceImpl;

@RestController
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<PurhaseOrderResponseDto>> createOrder(@RequestBody PurchaseOrderRequestDto requestResponseDto) throws Exception
	{
		ApiResponse<PurhaseOrderResponseDto> response = new ApiResponse<PurhaseOrderResponseDto>();
		
		
		PurhaseOrderResponseDto responseDto=orderService.createOrder(requestResponseDto);
		
		if(responseDto!=null)
		{
			response.setStatus(201);
			response.setMsg("Order Created");
			response.setData(responseDto);
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		else
		{
			response.setStatus(500);
			response.setMsg("Order Failed");
			response.setData(null);
			
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<PurhaseOrderResponseDto>> updateOrder(@RequestBody UpdateOrderRequestDto requestResponseDto) throws Exception
	{
		ApiResponse<PurhaseOrderResponseDto> response = new ApiResponse<PurhaseOrderResponseDto>();
		
		
		PurhaseOrderResponseDto responseDto=orderService.updateOrder(requestResponseDto);
		
		if(responseDto!=null)
		{
			response.setStatus(200);
			response.setMsg("Order updated");
			response.setData(responseDto);
			
			return new ResponseEntity<>(response,HttpStatus.CREATED);
		}
		else
		{
			response.setStatus(500);
			response.setMsg("Order Updation Failed");
			response.setData(null);
			
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/cancel/{orderTrackingNumber}")
	public ResponseEntity<ApiResponse<PurhaseOrderResponseDto>> cancelOrder(@PathVariable String orderTrackingNumber) throws Exception
	{
		ApiResponse<PurhaseOrderResponseDto> response = new ApiResponse<PurhaseOrderResponseDto>();
		
		
		PurhaseOrderResponseDto responseDto=orderService.cancelOrder(orderTrackingNumber);
		
		if(responseDto!=null)
		{
			response.setStatus(200);
			response.setMsg("Order Cancelled Succesfully");
			response.setData(responseDto);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			response.setStatus(500);
			response.setMsg("Order Cancellation Failed");
			response.setData(null);
			
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/customer-order/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getOrder(@PathVariable String email) throws Exception
	{
		ApiResponse<List<OrderDto>> response = new ApiResponse<List<OrderDto>>();
		
		
		List<OrderDto> responseDto=orderService.getCustomerOrders(email);
		
		if(responseDto!=null  && !responseDto.isEmpty())
		{
			response.setStatus(200);
			response.setMsg("Order Details Fetched Succesfully");
			response.setData(responseDto);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			response.setStatus(500);
			response.setMsg("Order Details are not Fetched Succesfully ");
			response.setData(null);
			
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
