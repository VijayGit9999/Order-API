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
@Table(name="Order_Items")
@Getter
@Setter
public class OrderItemEntity {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String imageUrl;
    private Integer quantity;
    private Double unitPrice;
    private String productName;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

}
