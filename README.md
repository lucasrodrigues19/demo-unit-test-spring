# TESTES UNITÁRIOS COM SPRING



##  1 - Testes com SpringDataJPA
Considerando Que vamos Testar um **[Repository](https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html)** devemos considerar as Seguintes anotações:

- **[@Test](https://junit.org/junit5/docs/current/user-guide/)**
  - Para que seja considerado um método de teste.
 
- **[@DisplayName](https://junit.org/junit5/docs/5.0.3/api/org/junit/jupiter/api/DisplayName.html)**
  - O nome que vai aparecer quando o teste for exexutado.

- **[@DataJpaTest](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html)** 
  - Anotação para um teste JPA que se concentra apenas em componentes JPA. Só vai aplicar as configurações para testes JPA.

## 2 - Testes com a Classe de serviço
Considerando Que vamos Testar uma classe de serviço e classe tenho dependencias que precisam serem instanciadas para que eu possa realizar os testes. Com isso devemos nos atentar as Seguintes anotações:
 - **[@InjectMocks](https://javadoc.io/doc/org.mockito/mockito-core/1.9.5/org/mockito/InjectMocks.html)**
    - Ultiliza quando quero testar a classe em si. Vou fazer uma inejão(instancia) mocada, e injeta os mocks que são criados com as anotações @Mock

- **[@Mock](https://javadoc.io/doc/org.mockito/mockito-core/1.9.5/org/mockito/InjectMocks.html)** 
    - Ultilizo para que eu possa usar alguma dependencia que está sendo ultilizada na classe, vou criar um comportamento mocado.

- **[@SpringBootTest](https://spring.io/guides/gs/testing-web/)**
    - Tenta executar o contexto, vai inicializar a apliação.

- **[@ExtendWith(SpringExtension.class)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit/jupiter/SpringExtension.html)**
     - Ultiliza spring com junit, o contexto não é totalmente inicializado

- **[@BeforeEach](https://junit.org/junit5/docs/5.0.2/api/org/junit/jupiter/api/BeforeEach.html)** 
    - Comportamentos que Serão executados antes dos testes.
    - Bom, para fazer o comportamento mocado de algum método, de alguma dependencia que está com a anotação @Mock. Devemos considerar os seguintes exemplos:
     
       - **BDDMockito.when(animeRepository.findAll()).thenReturn(Arrays.asList(new Object(), new Object()));**
         - Quando o metodo findAll for chamado, terá como retorno  um array de Objeto.
		 
       - **BDDMockito.when(animeRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(new Object));**       
	       - Quando o metodo findById for chamado, Passando qualquer valor do tipo UUID, irá retornar um Optional de Object.
		 
       - **BDDMockito.doNothing().when(animeRepository).delete(ArgumentMatchers.any(Object.class));**
		   - Aqui, uma sintaxe diferente, quando métodos que retornam void.

## 3 - Testes de integração.
Aqui, algumas anotações ultilizadas para testes de integração:

- **[@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)](https://spring.io/guides/gs/testing-web/)**
  -  Quando é intrgração preciso que inicie toda a aplicação, executando em uma porta aleatoria
- **[@AutoConfigureTestDatabase](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/jdbc/AutoConfigureTestDatabase.html)** 
  - Configuração de banco de dados ultilizara o valor que tem em memoria 
- **[@TestConfiguration](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/TestConfiguration.html)**
  - Usado para definir beans ou customizações adicionais para um teste.
- **[@Lazy](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Lazy.html)**
   -  indica se um bean deve ser inicializado lentamente.
- **[@MockBean](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Lazy.html)** 
  -   Usado para adicionar objetos simulados(mocados) ao contexto do aplicativo Spring. 

