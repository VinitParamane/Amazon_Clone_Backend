package comeHefsineFirst.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comeHefsineFirst.Model.Category;
import comeHefsineFirst.Repo.CategoryRepo;


@CrossOrigin@RestController
@RequestMapping("/admin")
public class admin {
	
	@Autowired
	CategoryRepo categoryrepo;
	
	@RequestMapping("/addComponent{userId}")
	public Category addComponent(@RequestBody String name,@PathVariable  int userId)
	{   
		System.out.println(userId);
		Category ca=new Category();
		ca.setDate(new Date());
		ca.setName(name);
		ca.setUserId(userId);
		
		return categoryrepo.save(ca);
		
		
	}
	
	
	@RequestMapping("getAll")
	public List<Category> getAll()
	{
		return categoryrepo.findAll();
	}

}
