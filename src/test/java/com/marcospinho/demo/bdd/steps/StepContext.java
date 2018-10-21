package com.marcospinho.demo.bdd.steps;

import com.marcospinho.demo.DemoTestesAutomatizadosApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * @author Marcos Pinho
 */
@ContextConfiguration(classes = DemoTestesAutomatizadosApplication.class, loader = SpringBootContextLoader.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class StepContext {
}
