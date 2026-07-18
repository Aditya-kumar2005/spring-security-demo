package spring.ai.example.spring_ai_demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	private record Product(int productId,String productName,int price){}
		List <Product> products=new ArrayList<>(
			List.of(
				new Product(1, "Ajay", 1000),
				new Product(2, "Abhi", 3000)
			)
		);
	@GetMapping 
	public List<Product> getProducts(){
		return products;
	}

	@PostMapping 
	public List<Product> addProducts(@RequestBody Product product){
		products.add(product);
		return products;
	}
 
	
}