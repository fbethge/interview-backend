package de.bringmeister.price;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by fabi on 18.11.17.
 */
@Service
public class PriceReferenceReaderService {

    private static final String PRICES_JSON = "src/main/resources/products/prices.json";

    public PriceReferenceReaderService(){

    }

    public static List<PriceReference> readPriceReferences() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<PriceReference> priceReferences = objectMapper.
                readValue(new FileInputStream(PRICES_JSON), new TypeReference<List<PriceReference>>() {
                });
        System.out.println(priceReferences.toString());
        return priceReferences;

    }
}
