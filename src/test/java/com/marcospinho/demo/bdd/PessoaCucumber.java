package com.marcospinho.demo.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Marcos Pinho
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        glue = {"com.marcospinho.demo.bdd.steps.pessoa"},
        features = {"classpath:bdd/pessoa"},
        format = {"pretty", "html:target/bdd/pessoa", "json:target/bdd/pessoa.json"}
)
@ActiveProfiles(value = "test")
public class PessoaCucumber {
}