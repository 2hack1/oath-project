package in.sp.firs;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstCla {
	
	private String name;
	private String email;
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@RequestMapping("/")
	public String home() {
		System.out.println("hello start ho  gya");
		
		return"index";
	}
	
	@RequestMapping("/form")
	public  void form() {
		System.out.println("form bhi chal gya");
		return;
	}
	
	@RequestMapping(path = "aa" , method = RequestMethod.GET)
	public  String ga(@RequestParam("name")String ka, @RequestParam("email")String ea , Model model) {
		System.out.println("google bhi chal raha hai");
		
	System.out.println(ka);
	setName(ka);
	setEmail(ea);
	
		model.addAttribute("name", ka);
		model.addAttribute("email", ea);
		return"ga"; 
	}
	
	@RequestMapping(path = "cert" , method = RequestMethod.POST)
	public String ga(@RequestParam("mobile")String num,  Model model) {
		System.out.println("certificate bhi chal raha hai");		
	    
	if(( getEmail().equals("")) && ( getName().equals(""))) {
		
    return "redirect:/form";
	}else {
		model.addAttribute("name", getName());
		model.addAttribute("email", getEmail());
		Date date=new Date();
		int day=date.getDay();
		int mon=date.getMonth();
		int year=date.getYear();
		model.addAttribute("date","Date "+day+"-"+mon+"-"+(year+1900) );
		System.out.println(getEmail());
		System.out.println(getName());
		return"certificate"; 
	}

		
	}
	@RequestMapping("/LoginForm")
	public  void Logi() {
		System.out.println("Login form bhi chal gya");
		return;
	}
	
	@RequestMapping(path = "laa" , method = RequestMethod.POST)
	public  String Login(@RequestParam("name")String ka, @RequestParam("email")String ea , Model model) {
		System.out.println("google bhi chal raha hai");
		
	System.out.println(ka);
	setName(ka);
	setEmail(ea);
	
		model.addAttribute("name", ka);
		model.addAttribute("email", ea);
		return"certificate"; 
	}
	
}
