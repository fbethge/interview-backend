package de.bringmeister.masterData;

import de.bringmeister.product.Product;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MasterDataReaderService {
	
	private static final String ID = "id";
	private static final String NAME = "Name";
	private static final String DESCRIPTION = "Description";
	private static final String SKU = "sku";
	
	public static List<Product> parseDocument(String pathOfXml) throws ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File(pathOfXml));
		List<Product> products = new ArrayList<Product>();
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {

			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;

				String id = node.getAttributes().getNamedItem(ID).getNodeValue();

				String name = elem.getElementsByTagName(NAME).item(0).getChildNodes().item(0).getNodeValue();
				
				String description = "";
				
				try {
					Element elemDescription = (Element) elem.getElementsByTagName(DESCRIPTION).item(0);
					
					description  = getCharacterDataFromElement(elemDescription);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String sku = elem.getElementsByTagName(SKU).item(0).getChildNodes().item(0).getNodeValue();

				Product tmpProduct = new Product(id,name,description,sku);
				products.add(tmpProduct);
				
			}
		}
		return products;
	}
	
	
	
	
	
	/**
	 * retrieves cdata content from node
	 * @param element - org.w3c.dom.Element
	 * @return cdata as String
	 */
	public static String getCharacterDataFromElement(Element element) {
		
		NodeList list = element.getChildNodes();
		String data = "";
		
		for(int index = 0; index < list.getLength(); index++){
			if(list.item(index) instanceof CharacterData){
				CharacterData child = (CharacterData) list.item(index);
				data = child.getData();
				
				if(data != null && data.trim().length() > 0)
					return child.getData();
			}
		}
		return ""; 
		
		
	}
}
