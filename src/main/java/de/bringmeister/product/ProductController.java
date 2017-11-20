package de.bringmeister.product;

import de.bringmeister.errorpage.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public List<Product> getAllProducts() throws EntityNotFoundException{
		return productService.getAllProducts();
	}

	@RequestMapping("/products/{name}")
	public List<Product> getProductByNameWithPrice(@PathVariable String name,
												   @RequestParam(value="unit",
														   required = false) String unit) throws EntityNotFoundException
	{

		if (!StringUtils.isEmpty(unit)){
			return productService.getProductByNameSortedByUnit(name, unit);
		} else {
			return productService.getProductByName(name);
		}
	}

}
