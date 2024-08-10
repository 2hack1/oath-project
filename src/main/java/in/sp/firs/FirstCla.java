package in.sp.firs;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
    private String rmess;
    
	public String getRmess() {
		return rmess;
	}

	public void setRmess(String rmess) {
		this.rmess = rmess;
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
// Default page come on web***********
	@RequestMapping("/")
	public String home() {
		System.out.println("hello start ho  gya");
		setMess("");
		setRmess(" ");
		
		return "index";
	}
 //Ragister page load********
	@RequestMapping("/form")
	public void form(Model m) {
		
		setOtpMatch("");
		if (getRmess()==null) {
			setRmess(" ");
		
		}else {	
			
		m.addAttribute("rmess", getRmess());	
		}
		return;
	}
// All the data of Register page are going on jsp page*******
	@RequestMapping(path = "aa", method = RequestMethod.GET)
	public String ga(@RequestParam("name") String ka, @RequestParam("email") String ea, @RequestParam("pass") int pass,
			@RequestParam("gender") String gender, Model model) {
       
		Iterator<Enty> iterator = hibernetDao.getfiFirstClas().iterator();
		while (iterator.hasNext()) {
			Enty enty = iterator.next();
			System.out.println(enty.getEmail() );
			if (enty.getEmail().equals(ea) && enty.getName().equals(ka)) {
				
				setRmess("Email or name orlady exist");
				return "redirect:/form";
			}
		}
		
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
	
	//Load and past on certificate data and page By Register page *************
	@RequestMapping(path = "cert", method = RequestMethod.POST)
	public String ga(@RequestParam("mobile") String num, Model model) {
		System.out.println("certificate bhi chal raha hai");
           
		Iterator<Enty> iterator = hibernetDao.getfiFirstClas().iterator();
		while (iterator.hasNext()) {
			Enty enty = iterator.next();
			System.out.println(enty.getEmail() );
			if (enty.getEmail().equals(getEmail())) {
				
			id=enty.getId();
			}
		}
			System.out.println("this is  id of user"+getId());
			//setId(id);
		if ((getEmail().equals("")) && (getName().equals(""))) {

			return "redirect:/form";
		} else {
			model.addAttribute("id", getId());
			model.addAttribute("name", getName());
			model.addAttribute("email", getEmail());

			return "certificate";
		}
	}

	//Load the LoginForm page *********************
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
 //Load and past on certificate data and page By a Login page**********
	@RequestMapping(path = "laa", method = RequestMethod.POST)
	public String Login(@RequestParam("pass") String password, @RequestParam("email") String ea, Model model) {
		Iterator<Enty> iterator = hibernetDao.getfiFirstClas().iterator();
		while (iterator.hasNext()) {
			Enty enty = iterator.next();
			System.out.println(enty.getEmail() + "" + enty.getPass());
			if (enty.getEmail().equals(ea) && String.valueOf(enty.getPass()).equals(password)) {
				  model.addAttribute("id",enty.getId());
				  model.addAttribute("name", enty.getName()); 
				  model.addAttribute("email",enty.getEmail());
				  System.out.println(getEmail()); 
				  return "certificate";				
			}
		}
		setMess("data not found!");
		return "redirect:/LoginForm";
	}
	//Forget page and load **********
	@RequestMapping("/forget")
	public void forge() {
	System.out.println("forget");
		return;
	}
	//Load the data and Redirect on otp page**************
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
// Load the otp page******************************
	@GetMapping("/otp")
	public String setOtpEX(Model m) {
		if(getOtpMatch()==null) {
			setOtpMatch("");
		}
		//Match the otp
		m.addAttribute("otpMessage", getOtpMatch());
		emailSenderEX.sendEmailTest(getOtp(),getStorEnty().getEmail());		
		return "OTP";
	}
	
	//Load the verify data********
	@PostMapping("/verify_otp")
	public String getOtpEX(Model m,@RequestParam("otp") String otp) {
		if(otp.equals(String.valueOf(getOtp()))){
			return "redirect:/updateData";
		}
		//message not Metch*********
		setOtpMatch("OTP NOT METCH!");
		return "redirect:/otp";
	}
	
	
	//Verify the otp and going in the updata page and enter new password**********
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
			
			x=true;
		} catch (Exception e) {
			e.printStackTrace();
			x=false;
		}
		if (x) {
			m.addAttribute("name", obj.getName());
			m.addAttribute("email", obj.getEmail());
			m.addAttribute("id", obj.getId());
		
			return "certificate";
			
	
		}else {
			
			return "redirect:/LoginForm";
		}			
	}	
}
