package com.kwan.CRUDe;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//Controller handling the html requests through http paths
@Controller
public class CruDeController {
	
	//instance of the service class
	@Autowired
	private CruDeService service;
	
	//home page request with list of existing batches
	@RequestMapping("/crude")
	public String homePage(Model model) {
		List<Oil> oils = (List<Oil>)service.readList();
		model.addAttribute("oillist", oils);
		return "Home";
	}
	
	//html request to create an Oil object
	@RequestMapping("/new")
	public String newBatch(Model model) {
		service.createOil();
		List<Oil> oils = (List<Oil>)service.readList();
		model.addAttribute("oillist", oils);
		return "Pumped";
	}
	
	//html request to refine a specified batch
	@RequestMapping("/refine/{id}")
	public String refineBatchById(Model model, @PathVariable Long id) {
		service.updateOil(id);
		List<Oil> oils = (List<Oil>)service.readList();
		model.addAttribute("oillist", oils);
		return "Boiled";
	}
	
	//html request to refine a specified batch
	@RequestMapping("/destroy/{id}")
	public String destroyBatchById(Model model,@PathVariable Long id) {
		service.deleteOil(id);
		List<Oil> oils = (List<Oil>)service.readList();
		model.addAttribute("oillist", oils);
		return "Spilled";
	}
}
