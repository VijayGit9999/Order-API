package in.Order_API.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Refund;

@Service
public class RazorpayService {
	
	private RazorpayClient razorpayClient;
	
	@Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;
	
	
	public String createRazorPayOrder(Double amount) throws Exception
	{
		this.razorpayClient= new RazorpayClient(keyId,keySecret);
		
		JSONObject request =new JSONObject();
		
		request.put("amount", amount * 100);
		request.put("currency", "INR");
		request.put("payment_capture", 1);
		
		Order order =razorpayClient.Orders.create(request);
		
		
		return order.get("id");
	}
	
	public String refundPayment(String razorpayPaymentId, Integer amount) throws Exception
	{
		this.razorpayClient= new RazorpayClient(keyId,keySecret);
		
		
		JSONObject refundRequest =new JSONObject();
		refundRequest.put("amount", amount);
		
	    System.out.println("Payment ID = " + razorpayPaymentId);
	    System.out.println("Amount = " + amount);

		
		System.out.println("printing refundRequest" +refundRequest);
		
		Refund refund = razorpayClient.Payments.refund(razorpayPaymentId,refundRequest);
		
		System.out.println("printing refund" +refund);
		
		return refund.toString();
		
	}

}
