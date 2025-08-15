package comeHefsineFirst.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import comeHefsineFirst.Model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
