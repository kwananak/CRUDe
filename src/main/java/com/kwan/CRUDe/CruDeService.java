package com.kwan.CRUDe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Service handling the different requests from the controller
@Service
public class CruDeService {

	//instance of the oil repository
	@Autowired
	private OilRepo repo;
	
	//principal read method
	public List<Oil> readList() {
		return (List<Oil>)repo.findAll();
	}
	
	//create method
	public void createOil() {
		repo.save(new Oil());
	}
	
	//update method
	public void updateOil(Long id) {
		
		Oil upOil = repo.findById(id).get();
		
		switch(upOil.getType()) {
		case "crude": 
			upOil.setType("fuel"); break;
		case "fuel":
			upOil.setType("diesel"); break;
		case "diesel":
			upOil.setType("kerosene"); break;
		case "kerosene":
			upOil.setType("naphtha"); break;
		case "fuel oil":
			upOil.setType("gasoline"); break;
		}
		
		repo.save(upOil);
	}
	
	//delete method
	public void deleteOil(Long id) {
		repo.deleteById(id);
	}
}
