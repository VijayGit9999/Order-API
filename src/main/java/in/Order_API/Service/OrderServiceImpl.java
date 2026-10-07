package in.Order_API.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.Order_API.Dto.AddressDto;
import in.Order_API.Dto.CustomerDto;
import in.Order_API.Dto.OrderDto;
import in.Order_API.Dto.OrderItemDto;
import in.Order_API.Dto.PurchaseOrderRequestDto;
import in.Order_API.Dto.PurhaseOrderResponseDto;
import in.Order_API.Dto.UpdateOrderRequestDto;
import in.Order_API.Entity.CustomerEntity;
import in.Order_API.Entity.OrderEntity;
import in.Order_API.Entity.OrderItemEntity;
import in.Order_API.Entity.ShippingAddressEntity;
import in.Order_API.Mapper.AddressMapper;
import in.Order_API.Mapper.CustomerMapper;
import in.Order_API.Mapper.OrderItemMapper;
import in.Order_API.Mapper.OrderMapper;
import in.Order_API.Repositories.CustomerRepo;
import in.Order_API.Repositories.OrderItemRepo;
import in.Order_API.Repositories.OrderRepo;
import in.Order_API.Repositories.ProductCategoryRepo;
import in.Order_API.Repositories.ProductRepo;
import in.Order_API.Repositories.ShippingAddressRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private OrderItemRepo orderItemRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductCategoryRepo productCategoryRepo;
	
	@Autowired
	private ShippingAddressRepo shippingAddressRepo;
	
	@Autowired
	private RazorpayService razorpayService;
	
	
	

	@Override
	public PurhaseOrderResponseDto createOrder(PurchaseOrderRequestDto orderRequestDto) throws Exception{
		// TODO Auto-generated method stub
		
		CustomerDto customerDto=orderRequestDto.getCustomerDto();
		AddressDto addressDto=orderRequestDto.getAddressDto();
		OrderDto orderDto=orderRequestDto.getOrderDto();
		List<OrderItemDto> orderItemListDto=orderRequestDto.getOrderItemDtoList();
		
		// saving customer
		CustomerEntity customerEntity=customerRepo.findByEmail(customerDto.getEmail());
		
		if(customerEntity==null)
		{
			 customerEntity =CustomerMapper.convertToEntity(customerDto);
			
		customerRepo.save(customerEntity);
		
		// To do Interservice communication
		}
		
		// saving address
		
		ShippingAddressEntity addressEntity=null;
		
		if(addressDto.getAddrId()==null)
		{
			addressEntity = AddressMapper.toEntity(addressDto);
			addressEntity.setCustomer(customerEntity);
			
			shippingAddressRepo.save(addressEntity);
		}
		else
		{
			addressEntity = shippingAddressRepo.findById(addressDto.getAddrId()).get();
		}
		
		// save order
		
		String orderTrackingNumber= generateRamdomTrackingNumber();
		
		orderDto.setOrderTrackingNum(orderTrackingNumber);
		
		String razorPayOrderId=razorpayService.createRazorPayOrder(orderDto.getTotalPrice());
		
		orderDto.setRazorpayOrderId(razorPayOrderId);
		orderDto.setOrderStatus("CREATED");
		orderDto.setPaymentStatus("PENDING");
		
		OrderEntity orderEntity=OrderMapper.convertToEntity(orderDto);
		orderEntity.setCustomer(customerEntity);
		orderEntity.setShippingAddress(addressEntity);
		
		orderEntity.setCustomerEmail(customerEntity.getEmail());
		
		orderEntity = orderRepo.save(orderEntity);
		
		// save order Items
		
		for(OrderItemDto itemDto: orderItemListDto )
		{
		OrderItemEntity orderItemEntity=	OrderItemMapper.convertToEntity(itemDto);
		
		orderItemEntity.setOrder(orderEntity);
		
		orderItemRepo.save(orderItemEntity);
		}
		
		
		// Prepare final response
		
		PurhaseOrderResponseDto responseDto= new PurhaseOrderResponseDto();
		
		responseDto.setRazorpayOrderId(razorPayOrderId);
		responseDto.setOrderTrackingNumber(orderTrackingNumber);
		responseDto.setOrderStatus("CREATED");
		responseDto.setPaymentStatus("PENDING");
			
		
		
		return responseDto;
	}

	private String generateRamdomTrackingNumber()
	{

// To get the current DateTimestamp

SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");

String timeStamp=	sdf.format(new Date());

String uuid=UUID.randomUUID().toString().substring(1,5).toUpperCase();

return  "OD" + timeStamp + uuid;

	}

	@Override
	public PurhaseOrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
		// TODO Auto-generated method stub
		
	OrderEntity orderEntity =	orderRepo.findByOrderTrackingNum(updateOrderRequestDto.getOrderTrackingNum());
		
	orderEntity.setRazorpayPaymentId(updateOrderRequestDto.getRazorpayPaymentId());
	
	orderEntity.setPaymentStatus("COMPLETED");
	orderEntity.setOrderStatus("CREATED");
	orderRepo.save(orderEntity);
	
	// prepare final response
	PurhaseOrderResponseDto responseDto= new PurhaseOrderResponseDto();
	responseDto.setRazorpayOrderId(orderEntity.getRazorpayOrderId());
	responseDto.setOrderTrackingNumber(orderEntity.getOrderTrackingNum());
	responseDto.setPaymentStatus("COMPLETED");
	responseDto.setOrderStatus(orderEntity.getOrderStatus());
	
	
	
	
	return responseDto;
	
	
	}

	@Override
	public PurhaseOrderResponseDto cancelOrder(String orderTrackingNumber) throws Exception {
		// TODO Auto-generated method stub
		
		OrderEntity orderEntity=orderRepo.findByOrderTrackingNum(orderTrackingNumber);
		
		orderEntity.setOrderStatus("CANCELLED");
		orderEntity.setPaymentStatus("REFUND-IS-IN-PROGRESS");
		orderEntity.setDeliveyDate(null);
		
		orderRepo.save(orderEntity);
		
	Integer totalprice =	orderEntity.getTotalPrice().intValue();
	
	razorpayService.refundPayment(orderEntity.getRazorpayPaymentId(), totalprice * 100);
	
	// prepare final response
	
	PurhaseOrderResponseDto responseDto= new PurhaseOrderResponseDto();
			responseDto.setRazorpayOrderId(orderEntity.getRazorpayOrderId());
	responseDto.setOrderTrackingNumber(orderEntity.getOrderTrackingNum());
	responseDto.setOrderStatus(orderEntity.getOrderStatus());
	responseDto.setPaymentStatus("REFUND-IS-IN-PROGRESS");
	
	
			
	
		return responseDto;
	}

	@Override
	public List<OrderDto> getCustomerOrders(String customerEmail) {
		// TODO Auto-generated method stub
		
		List<OrderEntity> orderList=orderRepo.findByCustomerEmail(customerEmail);
		return orderList.stream().map(OrderMapper::convertToDto).toList();
	}

}
