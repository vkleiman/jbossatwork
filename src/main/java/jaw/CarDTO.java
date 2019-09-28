package jaw;

public class CarDTO {

	private String make;
	private String model;
	private Integer modelYear;

	public CarDTO(String make, String model, Integer modelYear) {
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
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

}
