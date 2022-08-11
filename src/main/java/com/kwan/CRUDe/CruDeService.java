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
	
	//principal read method, gets all the available batches
	public List<Oil> readList() {
		return (List<Oil>)repo.findAll();
	}
	
	//create method, new oil batch
	public void createOil() {
		repo.save(new Oil());
	}
	
	//update method by batchId
	public void updateOil(Long id) {
		
		Oil upOil = repo.findById(id).get();
		
		//checks oil type then refines it one step
		switch(upOil.getType()) {
		case CRUDE: 
			upOil.setType(Type.FUEL); break;
		case FUEL:
			upOil.setType(Type.DIESEL); break;
		case DIESEL:
			upOil.setType(Type.KEROSENE); break;
		case KEROSENE:
			upOil.setType(Type.NAPHTHA); break;
		case NAPHTHA:
			upOil.setType(Type.GASOLINE); break;
		case GASOLINE:
			break;
		default:
			break;
		}
		
		repo.save(upOil);
	}
	
	//delete method by batchId
	public void deleteOil(Long id) {
		repo.deleteById(id);
	}
}
