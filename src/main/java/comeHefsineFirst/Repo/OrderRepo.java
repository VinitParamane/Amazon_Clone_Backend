package comeHefsineFirst.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import comeHefsineFirst.Model.MyOrder;

public interface OrderRepo extends JpaRepository<MyOrder, Integer> {

}
