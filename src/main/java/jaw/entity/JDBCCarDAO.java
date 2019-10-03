package jaw.entity;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//@Repository
public class JDBCCarDAO implements CarDAO
{   
    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    public List<CarDTO> findAll()
    {
        return jdbcTemplate.query(
                "select * from cars",
                (rs, rowNum) ->
                        new CarDTO(
                                rs.getString("make"),
                                rs.getString("model"),
                                rs.getInt("modelYear")
                        )
        );
    }
    
    public void save(CarDTO car) {
    		jdbcTemplate.update(
    	        "insert into cars (make, model, modelyear) values (?, ?, ?)",
    	        car.getMake(), car.getModel(), car.getModelYear());
    }
    
    public void update(CarDTO car) {
		jdbcTemplate.update(
	        "update cars set make = ?, model = ?, modelyear = ?, where id = ?",
	        car.getMake(), car.getModel(), car.getModelYear(), car.getId());
    }
    
    public CarDTO findById(Long id) {
    		String sql = "select * from cars where id = ?";
		CarDTO car = (CarDTO) jdbcTemplate.query(sql, new Object[] {id},
	        new BeanPropertyRowMapper<CarDTO>(CarDTO.class));
		return car;
    }
    
	public void delete(Long id) { }
	
    public List<CarDTO> findByStatus(String status)
    {
        return jdbcTemplate.query(
                "select * from cars where status = ?", new Object[]{status},
                (rs, rowNum) ->
                        new CarDTO(
                                rs.getString("make"),
                                rs.getString("model"),
                                rs.getInt("modelYear")
                        )
        );
    }
}