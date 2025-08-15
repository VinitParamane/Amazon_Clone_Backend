package comeHefsineFirst.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comeHefsineFirst.Model.LoginReturn;
import comeHefsineFirst.Model.User;
import comeHefsineFirst.Repo.UserRepo;

@CrossOrigin@RestController
@RequestMapping("/login")
public class login {
	
	@Autowired
	UserRepo userrepo;
	
	
	@RequestMapping("/getName{userId}")
	public String[] getName(@PathVariable int userId) 
	{
	  User user=  userrepo.findById(userId).get();	
	  String s=user.getName();
	  String [] sa= {s};
	  return sa;
	}
	
	@RequestMapping("/log")
	public LoginReturn log(@RequestBody String [] sa)
	{
		
		if(sa[0]==null)
			return new LoginReturn(-1,-1,"blank username");
		if(sa[1]==null)
			return new LoginReturn(-1,-1,"blank password");
		
		String username=sa[0];
		String password=sa[1];
		
		int count=userrepo.countByUsername(sa[0]);
		if(count>1)
			return new LoginReturn(-1,-1,"something wrong");
		else if(count==0)
			return new LoginReturn(-1,-1,"no user");
		else
		{
			User user=userrepo.findByUsername(username);
			if(user.getPassword().equals(password))
				return new LoginReturn(user.getId(),user.getAccountType(),"null");
			else
				return new LoginReturn(-1,-1,"password wrong");
		}
		
	}

}
