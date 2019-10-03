package jaw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;

import jaw.entity.CarDTO;

@Controller
public class JawController {
	
	@Autowired
	InventoryService inventory;
	
	@GetMapping({"/", "/index"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping({"/carList"})
	public String carList(Model model) {
		model.addAttribute("carList", inventory.listAvailableCars());
	    return "carList";
	}
	
	@GetMapping({"/carForm"})
	public String editForm(@RequestParam Long id, Model model) {
		
		CarDTO car = inventory.findCar(id);
		model.addAttribute("car", car);
	    return "carForm";
	}
	
	@PostMapping("/carForm")
    public String submitForm(@ModelAttribute CarDTO carDto, Model model) {
		
		inventory.saveCar(carDto);
		model.addAttribute("carList", inventory.listAvailableCars());
		return "carList";
    }
	
	@GetMapping({"/buyForm"})
	public String buyForm(@RequestParam Long id, Model model) {		
		CarDTO car = inventory.findCar(id);
		model.addAttribute("car", car);
	    return "buyCarForm";
	}
	
	@PostMapping("/buyForm")
    public String buyForm(@RequestParam Long id, @RequestParam Integer price, Model model) {
	
		try {
			inventory.buyCar(id, price);
			return "redirect:carList";
		} catch (UnexpectedRollbackException e) {
			System.out.println("buyForm Exception" + e);
			model.addAttribute("message", e.getMessage());
			return "error";
		}
    }
	
	@PostMapping("/carDelete")
	public String submitForm(@RequestParam(name="id", required=false) List<Long> idValues, Model model) {
	              
	    inventory.deleteCars(idValues);
		return "redirect:carList";
	}
	
	@GetMapping("/error")
	public String error(HttpServletRequest request, Model model) {
	    String action = (String) request.getAttribute(
	        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	    System.out.println("action = " + action);
		String errMsg = "[" + action + "] is not a valid action";
		model.addAttribute("message", errMsg);
		return "error";
	}
}
