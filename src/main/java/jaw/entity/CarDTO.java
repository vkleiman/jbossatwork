package jaw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "cars")
public class CarDTO {

	public static final String STATUS_AVAILABLE = "Available";
	public static final String STATUS_SOLD = "SOLD";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String make;
	@Column
	private String model;
	@Column(name="modelyear")
	private Integer modelYear;
	@Column(name="status")
	private String status;
	
	public CarDTO() { 
		this.status = CarDTO.STATUS_AVAILABLE;
	}

	public CarDTO(String make, String model, Integer modelYear) {
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
		this.status = CarDTO.STATUS_AVAILABLE;
	}
	
	public Long getId() {
        return id;
    }
	
	public void setId(Long id) {
		this.id = id;
    }
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
