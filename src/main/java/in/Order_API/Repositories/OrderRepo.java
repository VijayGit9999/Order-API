package in.Order_API.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.Order_API.Entity.OrderEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer>{

	public OrderEntity findByOrderTrackingNum(String orderTrackingNum);

	public List<OrderEntity> findByCustomerEmail(String customerEmail);

}
