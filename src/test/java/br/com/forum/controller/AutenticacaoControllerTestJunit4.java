package br.com.forum.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc 
@ActiveProfiles("test")
public class AutenticacaoControllerTestJunit4 {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void deveriaDevolver400CadoDadosDeAutenticacaoEstejamIncorretos() throws Exception {

	URI uri = new URI("/auth");
	String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123456\"}";
    
	mockMvc.perform(MockMvcRequestBuilders
        		.post(uri)
        		.content(json)
        		.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers
			.status()
			.is(400)); // verificando se o status retornado é o 400
    }

}
