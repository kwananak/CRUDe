package com.kwan.CRUDe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//the main controller handling http requests
@RestController
public class CruDeRestController {

	//Instance of the oil service
	@Autowired
	private CruDeService service;
	
	//http request to receive th list of all existing oil batches
	@GetMapping("/get")
	public List<Oil> homeCall() {
		return (List<Oil>)service.readList();
	} 
	
	//http request to create an Oil object
	@PostMapping("/pumpy")
	public List<Oil> pumpCall() {
		service.createOil();
		return (List<Oil>)service.readList();
	}
	
	//http request to refine a specified batch
	@PostMapping("/boily/{id}")
	public List<Oil> boilCall(@PathVariable Long id) {
		service.updateOil(id);
		return (List<Oil>)service.readList();		 
	}
	
	//http request to destroy a specified batch
	@PostMapping("/spilly/{id}")
	public List<Oil> spillCall(@PathVariable Long id) {
		service.deleteOil(id);
		return (List<Oil>)service.readList();		 
	}
	
}
