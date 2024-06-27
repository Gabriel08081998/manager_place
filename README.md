# Projeto Sistema - Gerenciamento de Locais

Este projeto é uma aplicação Spring Boot que permite o gerenciamento de locais (places). Ele inclui funcionalidades para criar e armazenar informações sobre diferentes locais. A aplicação utiliza Spring Data JPA para interações com o banco de dados e implementa validação e geração de slugs para os locais.

## Estrutura do Projeto

O projeto está organizado em pacotes conforme descrito abaixo:

- **com.projeto.sistema.controller**: Contém o controlador REST para gerenciar as requisições HTTP relacionadas aos locais.
- **com.projeto.sistema.model**: Contém as classes de modelo que representam as entidades do banco de dados.
- **com.projeto.sistema.placeConfig**: Contém a configuração para habilitar a auditoria JPA.
- **com.projeto.sistema.repository**: Contém as interfaces de repositório que interagem com o banco de dados.
- **com.projeto.sistema.service**: Contém a lógica de negócios para criar e gerenciar locais.
- **com.projeto.sistema.view**: Contém as classes DTO (Data Transfer Objects) que são usadas para transferir dados entre as camadas da aplicação.
- **com.projeto.sistema**: Contém a classe principal para executar a aplicação Spring Boot.

## Requisitos

- Java 8 ou superior
- Maven
- Banco de dados relacional (configurado no arquivo `application.properties`)

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/projeto-sistema.git
   cd projeto-sistema

## Compile o projeto usando Maven:



mvn clean install
Configure o banco de dados no arquivo src/main/resources/application.properties.

## Execute a aplicação:

mvn spring-boot:run
Endpoints da API
Criar Local
URL: /places

Método: POST

## Corpo da Requisição:


{
"name": "Nome do Local",
"state": "Estado do Local"
}
## Resposta de Sucesso:

Status: 201 Created
Corpo:

{"id": 1,

"name": "Nome do Local",

"slug": "nome-do-local",

"state": "Estado do Local",

"createdAt": "2023-01-01T00:00:00",

"updatedAt": "2023-01-01T00:00:00"
}
## Resposta de Erro:

Status: 400 Bad Request
Corpo:

{

"error": "Place name exists"

}

## Estrutura de Classes

PlaceController  
Controlador responsável por lidar com as requisições HTTP para criar locais.

    @RestController
    @RequestMapping("places")
    public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @PostMapping
    public ResponseEntity<?> createPlace(@RequestBody @Valid PlaceDTO placeDTO) {
        try {
            Optional<Place> optionalPlace = placeService.createPlace(placeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPlace.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    }

## Place
Entidade que representa um local no banco de dados.


      @Entity
      @Data
      @Builder
      @NoArgsConstructor
      @AllArgsConstructor
      @EntityListeners(AuditingEntityListener.class)
      public class Place {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private long id;
      private String name;
      private String slug;
      private String state;
      @CreatedDate
      private LocalDateTime createdAt;
      @LastModifiedDate
      private LocalDateTime updatedAt;
      }
## JpaConfig

Configuração para habilitar a auditoria JPA.


      @Configuration
      @EnableJpaAuditing
      public class JpaConfig {
      }
## PlaceRepository
Repositório que gerencia as operações CRUD para a entidade Place.


      public interface PlaceRepository extends CrudRepository<Place, Long> {
      boolean existsByName(String name);
      }
## PlaceService
Interface que define os métodos de serviço para gerenciar locais.


      public interface PlaceService {
      Optional<Place> createPlace(PlaceDTO placeDTO);
      Optional<Place> save(Place place);
      }
## PlaceServiceImpl
Implementação do serviço que contém a lógica de negócios para criar e gerenciar locais.

    @Service
    public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Optional<Place> createPlace(PlaceDTO placeDTO) {
        if (placeRepository.existsByName(placeDTO.getName())) {
            throw new RuntimeException("Place name exists");
        }
        Place place = Place.builder().build();
        BeanUtils.copyProperties(placeDTO, place);

        Slugify slugify = new Slugify();
        place.setSlug(slugify.slugify(place.getName()));

        return Optional.of(placeRepository.save(place));
    }

    @Override
    public Optional<Place> save(Place place) {
        return Optional.of(placeRepository.save(place));
    }
    }
## ManagePlaceApplication
Classe principal para executar a aplicação Spring Boot.


      @SpringBootApplication
      @ComponentScan(basePackages = "com.projeto.sistema")
      @EnableJpaRepositories(basePackages = "com.projeto.sistema.repository")
      @EntityScan(basePackages = "com.projeto.sistema.model")
      public class ManagePlaceApplication {
      public static void main(String[] args) {
      SpringApplication.run(ManagePlaceApplication.class, args);
      }
      }
