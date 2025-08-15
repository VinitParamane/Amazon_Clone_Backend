package comeHefsineFirst.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comeHefsineFirst.Model.Product;
import comeHefsineFirst.Model.Rating;
import comeHefsineFirst.Model.User;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
	
	int countByProuctidAndUserid(int productid,int userid);
	Rating findByProuctidAndUserid(int productid,int userid);
	
	@Query(value="SELECT avg(star) FROM amazon.rating\r\n"
			+ " where prouctid=?1",nativeQuery = true)
	double getAvg(int  prouctid );

}
