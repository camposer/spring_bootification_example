# Spring Bootification Example

This repo contains a step by step history of a Spring Bootification for the following codebase:
http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-testing-example-using-annotations/

A branch is created for each step of the process and then rebased into the main branch, that way you can easily observe the executed tasks for each step. The process:

1. Compile
2. Build
3. Run

## 1. Compile

North star: Make the project compile. You should be able to successfully execute: `mvn compile test-compile`.

- Focus on POM
- Comment out old dependencies
- Replace group of spring annotations by spring boot starters, e.g. spring-jdbc by spring-boot-starter-jdbc
- Include new dependencies one by one, ideally upgrading old dependencies
- Replace dependencies to old classes by new ones (e.g. hibernate4)

## 2. Build

North star: Make the project build. You should be able to successfully execute: `mvn package`.

- Focus on Tests
- Change testing annotations when needed
- Use Dirties Contexts when needed
- Create a simple main Spring Boot class (@SpringBootApplication) 
- Add -at least- one Smoke Test that allows you to validate the Spring Context loading
  - Modify the main Spring Boot class accordingly 
  - TODO: add comments here

## 3. Run & Validate

North star: Make the project build. You should be able to successfully execute: `java -jar file.jar` and hit key endpoints

- Focus on Execution and Endpoints
- Add actuators to your classpath 
- Validate state of the app using actuator/health 
- Check endpoints
- Run e2e test harness
- Improve tests

**Rinse and repeat**

## Database configuration

The project uses mysql 5. This can be changed later, but I'll keep that config for now.

In order to start a mysql:5 server enter:

```bash
docker run -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d -p 3306:3306 mysql:5
```

In order to connect to the database enter:

```bash
docker run -it --net="host" --rm mysql:5 mysql -h 127.0.0.1 -u root -p
```

In order to execute the project, you'll have to create some objects in DB:

```bash
docker run -it --net="host" --rm mysql:5 mysql -h 127.0.0.1 -u root -e "CREATE DATABASE websystique"
cat websystique.sql | docker run -i --net="host" --rm mysql:5 mysql -h 127.0.0.1 -u root websystique
```
