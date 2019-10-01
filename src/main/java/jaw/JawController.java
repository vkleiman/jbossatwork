package jaw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import jaw.entity.CarDAO;
import jaw.entity.CarDTO;

@Controller
public class JawController {
	
	@Autowired
	CarDAO carDAO;
	
	@GetMapping({"/", "/index"})
	public String index(Model model) {
		System.out.println("in /index");
		return "index";
	}
	
	@GetMapping({"/carList"})
	public String carList(Model model) {
		model.addAttribute("carList", carDAO.findAll());
	    	return "carList";
	}
	
	@GetMapping({"/carForm"})
	public String editForm(@RequestParam Long id, Model model) {

		CarDTO car;
		if(id==0)
			car = new  CarDTO();
		else
			car = carDAO.findById(id);
		model.addAttribute("car", car);
	    	return "carForm";
	}
	
	@PostMapping("/carForm")
    public String submitForm(@ModelAttribute CarDTO carDto, Model model) {
		
		Long id = carDto.getId();
		if(id==null || id==0)
			carDAO.save(carDto);
		else //update
			carDAO.update(carDto);
		model.addAttribute("carList", carDAO.findAll());
		return "carList";
    }

	@PostMapping("/deleteCar")
	public String submitForm(@RequestParam(name="id", required=false) List<Long> idValues, Model model) {
	    
		if (idValues!=null) {            
	    		carDAO.delete(idValues);
		}
		model.addAttribute("carList", carDAO.findAll());
		return "redirect:carList";
	}
	
	@GetMapping("/error")
	public String foo(HttpServletRequest request, Model model) {
	    String action = (String) request.getAttribute(
	        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    System.out.println("action = " + action);
		String errMsg = "[" + action + "] is not a valid action";
		model.addAttribute("message", errMsg);
		return "error";
	}
}

