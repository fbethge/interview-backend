package de.bringmeister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by fabi on 19.11.17.
 */


@RunWith(SpringJUnit4ClassRunner.class)   // 1
@WebAppConfiguration   // 3
@ContextConfiguration
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
//    private static final String

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnMainPartsOfMasterRecord() throws Exception {
        this.mockMvc.perform(get("/products")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("43b105a0-b5da-401b-a91d-32237ae418e4")))
                .andExpect(jsonPath("$[0].name", is("Banana")))
                .andExpect(jsonPath("$[0].sku", is("BA-01")))
                .andExpect(jsonPath("$[1].id", is("b867525e-53f8-4864-8990-5f13a5dd9d14")))
                .andExpect(jsonPath("$[1].name", is("Tomato")))
                .andExpect(jsonPath("$[1].sku", is("TO-02")))
        ;
    }

    @Test
    public void shouldReturnMasterDataAndAllPricesOfSpecificProduct() throws Exception {
        this.mockMvc.perform(get("/products/banana")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("43b105a0-b5da-401b-a91d-32237ae418e4")))
                .andExpect(jsonPath("$[0].name", is("Banana")))
                .andExpect(jsonPath("$[0].price", is(2.45)))
                .andExpect(jsonPath("$[1].price", is(10.99)))
        ;
    }

    @Test
    public void shouldReturnProductFilteredByUnitPiece() throws Exception {
        this.mockMvc.perform(get("/products/banana?unit=piece")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("43b105a0-b5da-401b-a91d-32237ae418e4")))
                .andExpect(jsonPath("$[0].name", is("Banana")))
                .andExpect(jsonPath("$[0].unit", is("piece")))
        ;
    }
    @Test
    public void shouldReturnProductFilteredByUnitPackage() throws Exception {
        this.mockMvc.perform(get("/products/banana?unit=package")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("43b105a0-b5da-401b-a91d-32237ae418e4")))
                .andExpect(jsonPath("$[0].name", is("Banana")))
                .andExpect(jsonPath("$[0].unit", is("package")))
        ;
    }
}