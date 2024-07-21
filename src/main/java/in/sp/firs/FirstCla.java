package in.sp.firs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstCla {

	@RequestMapping("/")
	public String home() {
		System.out.println("hello chal gya");
		
		return"index";
	}
}
