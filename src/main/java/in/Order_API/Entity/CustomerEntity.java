package in.Order_API.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Customer")
@Getter
@Setter
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
    private String name;
    private String email;
    private String password;
    private String pwdUpdated;
    private String phoneNo;
    @CreationTimestamp
    @Column(name="date_created", updatable = false)
    private LocalDateTime  dateCreated;

    @UpdateTimestamp
    @Column(name="last_updated")
    private LocalDateTime  lastUpdated;
}
