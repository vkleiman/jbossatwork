package jaw;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JawController {
	
	 @GetMapping(path = "/{path}")
	 public String getPath(@PathVariable String path, Model model) {
		 
		 CarDAO carDAO = new CarDAO();
		 model.addAttribute("carList", carDAO.findAll());
		 
	     if(path.equals(""))
	    	 	return "index";
	     return path;
	 }
}
