package com.kwan.CRUDe;

import javax.persistence.*;
import org.apache.commons.math3.util.Precision;
import lombok.Data;

//main data class with lombok setters and getters
@Entity
@Data
public class Oil {
	
	//generated ID for easier referencing
	@Id
	@GeneratedValue
	private long batchId;
	
	//the refinement level of the current batch
	private String type;
	
	//the quantity in barrels
	private double quantity;
	
	//constructor setting base type and random quantity
	public Oil() {
		type = "crude";
		quantity = Precision.round((Math.random() * 3) + .1, 1);
	}
	
}
