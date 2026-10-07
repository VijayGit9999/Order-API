package in.Order_API.Dto;

import lombok.Data;

@Data
public class PurhaseOrderResponseDto {
	 private String razorpayOrderId;
	    private String orderStatus;
	    private String orderTrackingNumber;
	    private String paymentStatus;

}
