package in.Order_API.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="Product")
@Getter
@Setter
public class ProductEntity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	    private String name;
	    private String description;
	    private String title;
	    private BigDecimal unitPrice;
	    private String imageUrl;
	    private boolean active;
	    private int unitsInStock;

	    @CreationTimestamp
		private Date dateCreated;
		
		@UpdateTimestamp
		private Date lastUpdate;

	    @ManyToOne
	    @JoinColumn(name="category_id", nullable = false)
	    private ProductCategoryEntity category;

}
