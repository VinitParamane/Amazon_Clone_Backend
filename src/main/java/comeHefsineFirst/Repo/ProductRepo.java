package comeHefsineFirst.Repo;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comeHefsineFirst.Model.Product;
import comeHefsineFirst.Projection.ProductBuyer;
import comeHefsineFirst.Projection.ProductUi;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	Product findById(int prouctId);
	
	
	
	@Query(value="SELECT p.id,description,discount, p.name as prod ,price,c.name as cat FROM amazon.product p join amazon.category c on p.categoryid=c.id\r\n"
			+ "where p.userid=2;",nativeQuery = true)
	List<ProductUi> findByUserId(int ID);
	
	@Query(value="SELECT p.id,description,discount, p.name as prod ,price,c.name as cat FROM amazon.product p join amazon.category c on p.categoryid=c.id\r\n"
			+ "where p.id=?1",nativeQuery = true)
	ProductUi findByIdPro(int ID);
	
	@Query(value="SELECT id,description,discount,name,price,quantity,rating,datediff(now(),date) as days FROM amazon.product\r\n"
			+ "where categoryid=?1 and price>=?2 and price<=?3 and rating>=?4;",nativeQuery = true)
	List<ProductBuyer> findByFilter(int a,int b,int c, int d);
     
}
