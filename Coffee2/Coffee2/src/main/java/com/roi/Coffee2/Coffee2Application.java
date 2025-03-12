package com.roi.Coffee2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class Coffee2Application {

	public static void main(String[] args) {
		SpringApplication.run(Coffee2Application.class, args);
	}

	@Controller
	public class HomeController {
		private List<Coffee> coffeeList = new ArrayList<>();

		public HomeController() {
			coffeeList.add(new Coffee(1, "Espresso", "Arabica", "Small", 3.50, "Dark", "Ethiopia", false, 10, Arrays.asList("Chocolate", "Nutty"), "Espresso"));
			coffeeList.add(new Coffee(2, "Latte", "Arabica", "Medium", 4.50, "Medium", "Brazil", false, 8, Arrays.asList("Creamy", "Sweet"), "Drip"));
			coffeeList.add(new Coffee(3, "Cappuccino", "Robusta", "Large", 5.00, "Medium", "Colombia", false, 12, Arrays.asList("Fruity", "Bold"), "French Press"));
			coffeeList.add(new Coffee(4, "Mocha", "Arabica", "Medium", 4.75, "Dark", "Guatemala", false, 6, Arrays.asList("Chocolate", "Smooth"), "Espresso"));
			coffeeList.add(new Coffee(5, "Americano", "Robusta", "Large", 3.25, "Light", "Kenya", false, 15, Arrays.asList("Citrus", "Balanced"), "Drip"));
		}

		@GetMapping("/")
		public String getCoffees(Model model) {
			model.addAttribute("coffees", coffeeList);
			return "index";
		}

		@GetMapping("/delete")
		public String deleteCoffee(@RequestParam int id) {
			coffeeList.removeIf(coffee -> coffee.getId() == id);
			return "redirect:/";
		}

		@GetMapping("/add")
		public String add() {
			return "add";
		}

		@PostMapping("/save")
		public String saveCoffee(@RequestParam String name,
								 @RequestParam String type,
								 @RequestParam String size,
								 @RequestParam Double price,
								 @RequestParam String roastLevel,
								 @RequestParam String origin,
								 @RequestParam boolean isDecaf,
								 @RequestParam int stock,
								 @RequestParam List<String> flavorNotes,
								 @RequestParam String brewMethod) {
			Coffee cof = new Coffee();
			cof.setId(coffeeList.size() + 1);
			cof.setName(name);
			cof.setType(type);
			cof.setSize(size);
			cof.setPrice(price);
			cof.setRoastLevel(roastLevel);
			cof.setOrigin(origin);
			cof.setDecaf(isDecaf);
			cof.setStock(stock);
			cof.setFlavorNotes(flavorNotes);
			cof.setBrewMethod(brewMethod);
			coffeeList.add(cof);
			return "redirect:/";
		}

		@GetMapping("/edit")
		public String edit(@RequestParam int id, Model model) {
			for (Coffee coffee : coffeeList) {
				if (coffee.getId() == id) {
					model.addAttribute("coffee", coffee);
					return "edit";
				}
			}
			return "redirect:/";
		}

		@PostMapping("/update")
		public String update(@RequestParam int id,
							 @RequestParam String name,
							 @RequestParam String type,
							 @RequestParam String size,
							 @RequestParam double price,
							 @RequestParam String roastLevel,
							 @RequestParam String origin,
							 @RequestParam(required = false) boolean isDecaf,
							 @RequestParam int stock,
							 @RequestParam String flavorNotes,
							 @RequestParam String brewMethod) {

			for (Coffee coffee : coffeeList) {
				if (coffee.getId() == id) {
					coffee.setName(name);
					coffee.setType(type);
					coffee.setSize(size);
					coffee.setPrice(price);
					coffee.setRoastLevel(roastLevel);
					coffee.setOrigin(origin);
					coffee.setDecaf(isDecaf);
					coffee.setStock(stock);
					coffee.setFlavorNotes(Arrays.asList(flavorNotes.split(", ")));
					coffee.setBrewMethod(brewMethod);
					break;
				}
			}
			return "redirect:/";
		}
	}
}

