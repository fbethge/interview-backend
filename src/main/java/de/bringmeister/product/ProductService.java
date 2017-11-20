package de.bringmeister.product;

import de.bringmeister.errorpage.EntityNotFoundException;
import de.bringmeister.masterData.MasterDataReaderService;
import de.bringmeister.price.PriceReference;
import de.bringmeister.price.PriceReferenceReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.bringmeister.price.PriceReferenceReaderService.readPriceReferences;

@Service
public class ProductService {
	
	private static final String PRODUCTS_XML = "src/main/resources/products/products.xml";

	private List<Product> masterData = new ArrayList<Product>();
	private List<Product> monetizedProducts = new ArrayList<Product>();
	private List<PriceReference> priceReference =new ArrayList<PriceReference>();


	@Autowired
	private PriceReferenceReaderService priceReferenceReaderService;



	@PostConstruct
	private void initProductService() {
		try {
			masterData = MasterDataReaderService.parseDocument(PRODUCTS_XML);
			priceReference = readPriceReferences();
			monetizedProducts = monetizeProducts();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProducts(){
		
		return masterData;
	}	

	List<Product> getProductByName(String name) throws EntityNotFoundException {
		
		List<Product> productsByName =  monetizedProducts.stream().
											filter(product -> product.getName().toLowerCase().equals(name)).
											collect(Collectors.toList());
		if (productsByName.isEmpty()){
			throw new EntityNotFoundException(Product.class, name, name);
		}

		return productsByName;

	}
	private List<Product> monetizeProduct(Product masterDataProduct) {

		List<Product> monetizedProducts = new ArrayList<Product>();

		this.priceReference.forEach(
				priceRef -> {
					if (priceRef.getId().equalsIgnoreCase(masterDataProduct.getSku())){
						Product monetizedProduct = new Product();
						monetizedProduct.setId(masterDataProduct.getId());
						monetizedProduct.setName(masterDataProduct.getName());
						monetizedProduct.setDescription(masterDataProduct.getDescription());
						monetizedProduct.setSku(masterDataProduct.getSku());
						monetizedProduct.setCurrency(priceRef.getPrice().getCurrency());
						monetizedProduct.setPrice(new BigDecimal(priceRef.getPrice().getValue()));
						monetizedProduct.setUnit(priceRef.getUnit());

						monetizedProducts.add(monetizedProduct);
					}
				});

		return monetizedProducts;
	}

	private List<Product> monetizeProducts() {


		List<Product> monetizedProducts = new ArrayList<Product>();

		this.masterData.forEach(
			masterPiece -> this.priceReference.forEach(
                    priceRef -> {
                        if (priceRef.getId().equalsIgnoreCase(masterPiece.getSku())){
                            Product monetizedProduct = new Product();
                            monetizedProduct.setId(masterPiece.getId());
                            monetizedProduct.setName(masterPiece.getName());
                            monetizedProduct.setDescription(masterPiece.getDescription());
                            monetizedProduct.setSku(masterPiece.getSku());
                            monetizedProduct.setCurrency(priceRef.getPrice().getCurrency());
                            monetizedProduct.setPrice(new BigDecimal(priceRef.getPrice().getValue()));
                            monetizedProduct.setUnit(priceRef.getUnit());

                            monetizedProducts.add(monetizedProduct);
                        }
                    })

		);

		return monetizedProducts;
	}


	List<Product> getProductByNameSortedByUnit(String name, String unit) {

		List<Product> productList =  monetizedProducts.stream().
				filter(product -> product.getName().toLowerCase().equals(name)).
				filter(p -> p.getUnit().toLowerCase().equals(unit)).
								collect(Collectors.toList());

		return productList;
	}
}


