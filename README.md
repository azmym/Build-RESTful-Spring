# 1 Build-RESTful-Spring
* This service to show how create Secure Restful Web service using Spring Boot, MVC and security.
# 2 The architecture
* The architecture of the web service is built with the following components:
   * DataTransferObjects: Objects which are used for outside communication via the API
   * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
   * Service: Implements the business logic and handles the access to the DataAccessObjects.
   * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * DomainObjects: Functional Objects which might be persisted in the database.
# 3 Technologies
* The Restful web service which uses the following technologies:
   * Java 1.8
   * Spring MVC with Spring Boot
   * Spring Data
   * Spring Security
   * Swagger-ui
   * Jackson
   * Hibernate
   * Database H2 (In-Memory)
   * Maven
# 4 How to start
* open/import the project in your favorite IDE
* execute com.mazmy.MainServerApplicant::main to start an embedded http server
* open http://localhost:8080/ to see documentation and test existing endpoints

# 5 Endpoints
* / will be shown the API documentaion an a http test client
    * in this overview you can also read about the existing APIs 
      * /v1/drivers
      * /v1/cars
      * /v1/manufacturers
      and test it 
* /h2-console will give you the ability to check the existing and new created data in the db #dbclient
    * Saved Settings: Generic H2
    * Setting Name: Generic H2
    * Driver Class: org.h2.Driver
    * jdbc URL: jdbc:h2:mem:testdb
    * User Name: sa
    * Password: <empty>
