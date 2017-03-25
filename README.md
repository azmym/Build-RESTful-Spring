# 1 Build-RESTful-Spring

# 2 How to start
* open/import the project in your favorite IDE
* execute com.mazmy.MainServerApplicant::main to start an embedded http server
* open http://localhost:8080/ to see documentation and test existing endpoints

# 3 Endpoints
* / will be shown the API documentaion an a http test client
    * in this overview you can also read about the existing APIs 
      /v1/drivers
      /v1/cars
      /v1/manufacturers
      and test it 
* /h2-console will give you the ability to check the existing and new created data in the db #dbclient
    * Saved Settings: Generic H2
    * Setting Name: Generic H2
    * Driver Class: org.h2.Driver
    * jdbc URL: jdbc:h2:mem:testdb
    * User Name: sa
    * Password: <empty>
