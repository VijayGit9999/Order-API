package in.Order_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.Order_API.Entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,Integer>{

	public CustomerEntity findByEmail(String email);
	
	

}
