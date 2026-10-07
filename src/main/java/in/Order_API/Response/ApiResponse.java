package in.Order_API.Response;

import lombok.Data;

@Data
public class ApiResponse <T>{
	
	private Integer status;
	
	private String msg;
	
	private T data;

}
