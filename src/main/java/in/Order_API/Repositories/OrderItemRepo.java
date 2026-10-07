package in.Order_API.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.Order_API.Entity.OrderItemEntity;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItemEntity, Integer> {

}
