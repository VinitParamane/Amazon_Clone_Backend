package comeHefsineFirst.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comeHefsineFirst.Model.Cart;
import comeHefsineFirst.Projection.CartUi;


public interface CartRepo extends JpaRepository<Cart, Integer> {
	int countByProductidAndUserid(int productid,int userid);
	
	@Query(value="SELECT c.id,p.discount,p.name,p.price,p.quantity FROM amazon.cart c join amazon.product p on c.productid=p.id\r\n"
			+ "where c.userid=?1",nativeQuery = true)
	List<CartUi> getCart(int id);
}
