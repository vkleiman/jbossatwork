package jaw.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounting")
public class AccountingDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long car_id;
	@Column
	private Integer price;
	@Column(name="sale_date")
	private Date date;
	
	public AccountingDTO() {
		this.date = new Date(System.currentTimeMillis());
	}
	
	public AccountingDTO(Long car_id, Integer price) { 
		this.car_id = car_id;
		this.price = price;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getCar_id() {
		return car_id;
	}
	public void setCar_id(Long car_id) {
		this.car_id = car_id;
	}
}
