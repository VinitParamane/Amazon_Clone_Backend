package comeHefsineFirst.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import comeHefsineFirst.Model.OrderProducts;

public interface OrderProductsRepo extends JpaRepository<OrderProducts, Integer>{

}
