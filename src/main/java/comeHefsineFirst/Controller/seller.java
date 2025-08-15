package comeHefsineFirst.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comeHefsineFirst.Model.Product;
import comeHefsineFirst.Projection.ProductUi;
import comeHefsineFirst.Repo.ProductRepo;

@CrossOrigin@RestController
@RequestMapping("/seller")
public class seller {
     
	@Autowired
	ProductRepo productrepo;
	
	
	@RequestMapping("/getAll{ID}")
	public List<ProductUi> getAll(@PathVariable int ID)
	{
		
		return productrepo.findByUserId(ID);
	}
	
   @RequestMapping("/addProduct")
   public ProductUi addProduct(@RequestBody Product obj)
   {
	   obj.setDate(new Date());
	   
	   Product product=productrepo.save(obj);
	   int id=product.getId();
	   return productrepo.findByIdPro(id);
   }
}
