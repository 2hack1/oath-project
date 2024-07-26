package in.sp.firs;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.persistence.Entity;
import javax.swing.undo.AbstractUndoableEdit;
import javax.xml.transform.Source;

import org.hibernate.boot.model.naming.ImplicitNameSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.firs.email.EmailSenderEX;



@Controller
public class FirstCla {
	@Autowired
	EmailSenderEX emailSenderEX;
	String otpMatch;
	
	public String getOtpMatch() {
		return otpMatch;
	}

	public void setOtpMatch(String otpMatch) {
		this.otpMatch = otpMatch;
	}

	@Autowired
	
	HibernetDao hibernetDao;
	private Enty storEnty;
	private int otp;
     public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public Enty getStorEnty() {
		return storEnty;
	}

	public void setStorEnty(Enty storEnty) {
		this.storEnty = storEnty;
	}

	private String mess;
     
	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

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
		setMess("");
		return "index";
	}

	@RequestMapping("/form")
	public void form() {
		System.out.println("form bhi chal gya");
		return;
	}

	@RequestMapping(path = "aa", method = RequestMethod.GET)
	public String ga(@RequestParam("name") String ka, @RequestParam("email") String ea, @RequestParam("pass") int pass,
			@RequestParam("gender") String gender, Model model) {

		System.out.println("google bhi chal raha hai");

//		HibernetDao dao=new HibernetDao();

		Enty hh = new Enty(ka, ea, pass, gender);

		hibernetDao.creatt(hh);

		System.out.println(ka);
		System.out.println(ea);
		System.out.println(pass);
		System.out.println(gender);

		setName(ka);
		setEmail(ea);

		model.addAttribute("name", ka);
		model.addAttribute("email", ea);
		model.addAttribute("city", pass);
		model.addAttribute("gender", gender);

		return "ga";
	}

	@RequestMapping(path = "cert", method = RequestMethod.POST)
	public String ga(@RequestParam("mobile") String num, Model model) {
		System.out.println("certificate bhi chal raha hai");

		if ((getEmail().equals("")) && (getName().equals(""))) {

			return "redirect:/form";
		} else {
			model.addAttribute("name", getName());
			model.addAttribute("email", getEmail());
			Date date = new Date();
			int day = date.getDay();
			int mon = date.getMonth();
			int year = date.getYear();
			model.addAttribute("date", "Date " + day + "-" + mon + "-" + (year + 1900));
//			System.out.println(getEmail());
//			System.out.println(getName());
			return "certificate";
		}

	}

	@RequestMapping("/LoginForm")
	public void Logi(Model model) {
		setOtpMatch("");
		if (getMess()==null) {
			setMess("");
		}
		
	
		model.addAttribute("mess", getMess());
		System.out.println("Login form bhi chal gya");

		return;

	}

	@RequestMapping(path = "laa", method = RequestMethod.POST)
	public String Login(@RequestParam("pass") String password, @RequestParam("email") String ea, Model model) {
		Iterator<Enty> iterator = hibernetDao.getfiFirstClas().iterator();
		while (iterator.hasNext()) {
			Enty enty = iterator.next();
			System.out.println(enty.getEmail() + "" + enty.getPass());
			if (enty.getEmail().equals(ea) && String.valueOf(enty.getPass()).equals(password)) {
				
				  model.addAttribute("name", enty.getName()); 
				  model.addAttribute("email",enty.getEmail());
				   Date date = new Date();
				   int day = date.getDay(); 
				   int mon = date.getMonth(); 
				   int year = date.getYear();
				   model.addAttribute("date", "Date " + day + "-" + mon + "-" + (year + 1900)); 
				  System.out.println(getEmail()); 
				  // System.out.println(getName()); 
				  return "certificate";
				
			}

		}
		setMess("data not found!");
		return "redirect:/LoginForm";
	}
	
	@RequestMapping("/forget")
	public void forge() {
	System.out.println("forget");
		return;
	}
	@RequestMapping(path="forGet" ,method=RequestMethod.GET)
	public String forg(@RequestParam("email")String email,Model model) {
		System.out.println(email);
		
		
		
		
		Iterator<Enty> iterator = hibernetDao.getfiFirstClas().iterator();
		while (iterator.hasNext()) {
			Enty enty = iterator.next();
			System.out.println(enty.getEmail() + "" + enty.getPass());
			if (enty.getEmail().equals(email)) {
				Random random = new Random();
				setStorEnty(enty);
				setOtp(random.nextInt(999999));
				return "redirect:/otp";
			}
		}
		setMess("data not found!");
		return "redirect:/LoginForm";
	}

	@GetMapping("/otp")
	public String setOtpEX(Model m) {
		if(getOtpMatch()==null) {
			setOtpMatch("");
		}
		m.addAttribute("otpMessage", getOtpMatch());
		emailSenderEX.sendEmailTest(getOtp(),getStorEnty().getEmail());
//		System.out.println("OTP : "+getOtp());
//		System.out.println("User : "+ getStorEnty().getEmail()+" : "+getStorEnty().getName());
		return "OTP";
	}
	
	
	@PostMapping("/verify_otp")
	public String getOtpEX(Model m,@RequestParam("otp") String otp) {
		if(otp.equals(String.valueOf(getOtp()))){
			return "redirect:/updateData";
		}
		setOtpMatch("OTP NOT METCH!");
		return "redirect:/otp";
	}
	
	
	
	@GetMapping("/updateData")
	public String updateData(Model model) {
		model.addAttribute("userName", getStorEnty().getName());
		model.addAttribute("userEmail",getStorEnty().getEmail());
		return "update";
	}
	
	@PostMapping("/xyz")
	public String updateSuccess(@RequestParam("userPassword")String password, Model m) {
		boolean x = false;
		Enty obj = getStorEnty();
		try {
			obj.setPass(Integer.parseInt(password));
			obj.setId(obj.getId());
			hibernetDao.update(obj);
//			hibernetDao.creatt(obj);
			
			x=true;
		} catch (Exception e) {
			e.printStackTrace();
			x=false;
		}
		if (x) {
			m.addAttribute("name", obj.getName());
			m.addAttribute("email", obj.getEmail());
			Date date = new Date();
			int day = date.getDay();
			int mon = date.getMonth();
			int year = date.getYear();
			m.addAttribute("date", "Date " + day + "-" + mon + "-" + (year + 1900));
			return "certificate";
			
	
		}else {
			return "redirect:/LoginForm";
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
