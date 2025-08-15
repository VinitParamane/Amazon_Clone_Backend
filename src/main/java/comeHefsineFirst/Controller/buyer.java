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
import comeHefsineFirst.Model.Rating;
import comeHefsineFirst.Model.Cart;
import comeHefsineFirst.Model.MyOrder;
import comeHefsineFirst.Model.OrderProducts;
import comeHefsineFirst.Projection.CartUi;
import comeHefsineFirst.Projection.ProductBuyer;
import comeHefsineFirst.Repo.CartRepo;
import comeHefsineFirst.Repo.OrderProductsRepo;
import comeHefsineFirst.Repo.OrderRepo;
import comeHefsineFirst.Repo.ProductRepo;
import comeHefsineFirst.Repo.RatingRepo;

@CrossOrigin@RestController
@RequestMapping("/buyer")
public class buyer {
	
	@Autowired
	ProductRepo productrepo;
	@Autowired
	RatingRepo ratingRepo;
	@Autowired 
	CartRepo cartRepo;
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	OrderProductsRepo orderProductsRepo;
	
	@RequestMapping("/place{id}")
	public int place(@PathVariable int id,@RequestBody int [] [] a)
	{
		
		try
		{
			MyOrder order=new MyOrder();
			order.setDate(new Date());
			order.setUserid(id);
			order=orderRepo.save(order);
			
			double totalamount=0;
			for(int i=0;i<a.length;i++)
			{
				int [] a1=a[i];
				int cartid=a1[0];
				int quantity=a1[1];
				System.out.println(cartid);
				
				Cart cart=cartRepo.findById(cartid).get();
				int productid=cart.getProductid();
				Product product=productrepo.findById(productid);
				OrderProducts obj=new OrderProducts();
				
				double amount=product.getPrice()*quantity;
				amount=amount-(amount*product.getDiscount()/100);
				obj.setAmount(amount);
				
				totalamount=totalamount+amount;
				
				obj.setDate(new Date());
				obj.setOrderid(order.getId());
				obj.setProductid(productid);
				orderProductsRepo.save(obj);
				
				cartRepo.delete(cart);
	}
			
			order.setAmount(totalamount);
			orderRepo.save(order);
			
			return 1;
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
			
		}
		
		
	}
	
	@RequestMapping("/delete{id}")
	public int delete(@PathVariable int id)
	{
		try
		{
			cartRepo.deleteById(id);
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	@RequestMapping("/getCart{id}")
	public List<CartUi> getCart(@PathVariable int id)
	{
		
		return cartRepo.getCart(id);
	}
	
	@RequestMapping("/addToCart{userid}and{productid}")
	public int addToCart(@PathVariable int userid,@PathVariable int productid )
	{
		int count=cartRepo.countByProductidAndUserid(productid, userid);
				if(count==1)
				{
					return 1;//alreay added
				}
				else if(count==0)
				{
					Cart cart=new Cart();
					cart.setProductid(productid);
					cart.setUserid(userid);
					cartRepo.save(cart);
					return 2;//added
				}
				else
					return 0;//error
	}
	
	
	@RequestMapping("/newRating{userId}and{prouctId}and{star}")
	public int newRating(@PathVariable int userId,@PathVariable int prouctId,@PathVariable int star ) 
	{ 
		int count=ratingRepo.countByProuctidAndUserid(prouctId, userId);
		if(count==1)
		{
			Rating rate=ratingRepo.findByProuctidAndUserid(prouctId, userId);
			rate.setDate(new Date());
			rate.setStar(star);
			ratingRepo.save(rate);
			
			
		}
		else if(count==0)
		{
			Rating rate=new Rating();
			rate.setDate(new Date());
			rate.setProuctid(prouctId);
			rate.setStar(star);
			rate.setUserid(userId);
			ratingRepo.save(rate);
			
		}
		else
		{
			return 0;//error
		}
		 
		double a=ratingRepo.getAvg(prouctId);
		Product pro=productrepo.findById(prouctId);
		pro.setRating(a);
		productrepo.save(pro);
		
		return 1;
	}
	
	@RequestMapping("/filter")
	public List<ProductBuyer> filter(@RequestBody int [] a)
	{
		System.out.println(productrepo.findByFilter(a[0], a[1], a[2], a[3]));
		return productrepo.findByFilter(a[0], a[1], a[2], a[3]);
	}

}
