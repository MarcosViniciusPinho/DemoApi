# DemoApi ![Build Status](https://sonarcloud.io/api/project_badges/measure?project=com.marcospinho%3Ademo-testes-automatizados&metric=alert_status) ![Build Status](https://sonarcloud.io/api/project_badges/measure?project=com.marcospinho%3Ademo-testes-automatizados&metric=coverage) [![Build Status](https://travis-ci.org/MarcosViniciusPinho/DemoApi.svg?branch=master)](https://travis-ci.org/MarcosViniciusPinho/DemoApi)
<h3>Projeto visando a implementação de alguns testes de software mais utilizados no mercado, que são os seguintes:</h3>
<ul>
  <li>
    Comportamental(BDD): Se preocupa com a questão do código, se atende as regras de negócio; ou seja; como o sistema irá se comportar          conforme determinado cenário.
  </li>
  <li>
    Unidade(TDD): Se preocupa com a questão dos métodos isolados por classe; ou seja; todas as classes(que tenha relevância) devem ser testadas afim de garantir que o método que se propõe a fazer alguma coisa, o faça. Neste tipo de teste não é levado em consideração a dependência entre as classes.
  </li>
  <li>
    Integração: Se preocupa com a questão da interação da aplicação fora de sua fronteira, assim como as dependências entre classes; ou seja; os testes são realizados no inicio que começa a requisição(Camada de apresentação) passando por todo o fluxo de seus componentes até finalizar a mesma por completo.
  </li>
</ul>  

<h3>Para este projeto também existe uma configuração de CI, feita com Travis e Sonar.</h3>
<ul>
  <li>
    Para ter acesso ao Travis(Para ver o processo de build), acessar: https://travis-ci.org/MarcosViniciusPinho/DemoApi
  </li>
  <li>
    Para ter acesso ao Sonar(Para ver o dashboard de qualidade), acessar: https://sonarcloud.io/dashboard?id=com.marcospinho%3Ademo-testes-automatizados
  </li>
</ul>  

<h3>API RestFull construída com Spring Boot, utilizando: Flyway, Cucumber, Junit e etc.</h3>

<h4>Para visualizar o dashboard localmente dos testes BDDs(Que particularmente são os que eu mais gosto), faça o seguinte:</h4>
<ul>
    <li>Execute os goals do maven clean e install</li>
    <li>E acesse o link: http://localhost:63342/DemoApi/target/bdd/cucumber-html-reports/overview-features.html, que terá a visualização como o da imagem abaixo:</li>
</ul>
<img src="http://i65.tinypic.com/25tkaz7.jpg" border="0" alt="Image and video hosting by TinyPic">
<img src="http://i65.tinypic.com/zjwhlc.png" border="0" alt="Image and video hosting by TinyPic">
