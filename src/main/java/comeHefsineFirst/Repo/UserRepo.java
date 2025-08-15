package comeHefsineFirst.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import comeHefsineFirst.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	int countByUsername(String username);
	User findByUsername(String username);
	
	

}
