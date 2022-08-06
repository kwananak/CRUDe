package com.kwan.CRUDe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Controller handling the initial http request
@Controller
public class CruDeController {
	
	//displays the home page when receiving a request
	@RequestMapping("/crude")
	public String homePage() {
		return "Home";
	}
	
}
