# demo-unit-test-spring

## Demo para representação de Testes unitarios com Spring test.

###  1 - Testes com SpringDataJPA
- @Test
- @DisplayName
- **[@DataJpaTest](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html)** 
  - Anotação para um teste JPA que se concentra apenas em componentes JPA.
### 2 - Testes com a Classe de serviço
Considerando Que vamos Testar uma classe de serviço e classe tenho dependencias que precisam serem instanciadas para que eu possa realizar os testes. Com isso devemos nos atentar as Seguintes anotações:
 - **[@InjectMocks](https://javadoc.io/doc/org.mockito/mockito-core/1.9.5/org/mockito/InjectMocks.html)**
    - Ultiliza quando quero testar a classe em si. Vou fazer uma inejão(instancia) mocada, e injeta os mocks que são criados com as anotações @Mock
 - **[@Mock](https://javadoc.io/doc/org.mockito/mockito-core/1.9.5/org/mockito/InjectMocks.html)** 
    - Ultilizo para que eu possa usar alguma dependencia que estão sendo ultilizada na classe, vou criar um comportamento mocado da classe para a dependencia.
 - **[@SpringBootTest](https://spring.io/guides/gs/testing-web/)**
    - Tenta executar o contexto, vai inicializar a apliação.
 - **[@ExtendWith(SpringExtension.class)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit/jupiter/SpringExtension.html)**
     - Ultiliza spring com junit, o contexto não é totalmente inicializado
- **[@BeforeEach]** 
    - Comportamentos que Serão executados antes dos testes.
### 3 - Testes de integração com a Contrlloer.

