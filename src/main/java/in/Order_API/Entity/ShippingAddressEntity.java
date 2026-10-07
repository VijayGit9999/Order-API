package in.Order_API.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ShpinngAddress")
@Setter
@Getter
public class ShippingAddressEntity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer addrId;
	    private String hno;
	    private String street;
	    private String city;
	    private String state;
	    private String zipCode;
	    private String addrType;
	    private String deleteSw;

	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    private CustomerEntity customer;

}
