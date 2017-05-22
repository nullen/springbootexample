# Microservice Project Template

This is a template or skeleton for creating new microservice modules.
Copy the whole structure and update the pom.xml for maven.

## Lombok

This project is using Lombok and require Lombok plugin.
* For Intellij - Search for "Lombok Plugin", install and restart.
* For Eclipse - Run lombok.jar as a java app (i.e. doubleclick it, usually) to install. Also add lombok.jar to your project. 

## Testing the skeleton with maven. You need to install maven locally.

    $ mvn test

## Start Springboot with Maven
  
    $ mvn spring-boot:run
    
## Run RestFul services
  
    Find User by Id:
    $ curl localhost:8080/user/{id}
 
