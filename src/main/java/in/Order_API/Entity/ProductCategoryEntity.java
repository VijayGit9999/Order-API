package in.Order_API.Entity;

import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ProductCategory")
public class ProductCategoryEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String categoryName;

    private String active;
    
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private Set<ProductEntity> products;

}
