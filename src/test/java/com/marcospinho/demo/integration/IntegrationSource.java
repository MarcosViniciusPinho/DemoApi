package com.marcospinho.demo.integration;

import com.marcospinho.demo.DemoTestesAutomatizadosApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @author Marcos Pinho
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoTestesAutomatizadosApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class IntegrationSource {
}