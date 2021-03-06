# Spring Bootification Example

This repo contains a step by step history of a Spring Bootification for the following codebase:
http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-testing-example-using-annotations/

A branch is created for each step of the process and then rebased into the main branch, that way you can easily observe the executed tasks for each step. The process:

1. Compile
2. Build
3. Run
4. Modernise (optional)

## Disclaimer

This example assumes the codebase has an important level of tests maturity. Tests can/should be improved before bootification or during the process.

- Remove custom unneeded Spring custom config (e.g. database)

## Spring Bootification Steps

### 0. Prepare

A step zero was executed in order to make the original application run with jdk12, maven3.6 on a MaCOS.

### 1. Compile

North star: Make the project compile. You should be able to successfully execute: `mvn compile test-compile`.

- Focus on POM
- Comment out old dependencies
- Replace group of spring annotations by spring boot starters, e.g. spring-jdbc by spring-boot-starter-jdbc
- Include new dependencies one by one, ideally upgrading old dependencies
- Replace dependencies to old classes by new ones (e.g. hibernate4)

### 2. Build

North star: Make the project build. You should be able to successfully execute: `mvn package`.

- Focus on Tests
- Change testing annotations when needed
- Use Dirties Contexts when needed
- Create a simple main Spring Boot class (@SpringBootApplication) 
- Add -at least- one Smoke Test that allows you to validate the Spring Context loading and one important endoiint
  - Modify the main Spring Boot class accordingly 
  - Copy smoke test config into Spring Boot main class
  - TODO: Add more info here

### 3. Run & Validate

North star: Run the project. You should be able to successfully execute: `java -jar file.jar` and hit key endpoints

- Focus on Execution and Endpoints
- Add actuators to your classpath 
- Validate state of the app using actuator/health 
- Check endpoints
- Run e2e test harness
- Improve tests

### 4. Modernise

North star: Make the project full spring-boot and follow cloud native patterns. Of course, there's not "a way" of doing this, 
there are different approaches and levels of compliance with a 12-factor app.

- Use smoke tests and actuator/health for validating state
- Remove custom not needed Spring configurations

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
