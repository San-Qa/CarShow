Coverage: 84.3%
# Front-End Car-Showroom web development

a Spring Boot web application that serves as a framework for naming and categorising cars. 
My application's back-end was created with the Java Spring framework and a MySQL 
database hosted on H2 database. The front-end was created with HTML and CSS, 
with API calls made with JavaScript to retrieve data from my database.

## Installation

Dependencies

**1.Git, of course, installed on your local machine.

**2.Maven to compile and run the project.

Assuming you have Git and Maven on your local machine you will run the following commands.
On the terminal of your choice change directories to where you want the cloned project 
files to download and run:

````
git clone https://github.com/San-Qa/Carproject.git
````
Since we are using Maven to run/build this project you will execute the following at the
project root.

`````
mvn spring-boot:run
`````
This will compile and startup a Tomcat server on your localhost. In
your Web browser go to http://localhost:9045/ and check it out.
- 

### Backend

*Spring Boot
Spring Boot is the base for the server side, as previously said. The data is stored in
an H2 (in memory Java-based) SQL database. H2 was chosen solely for the demo's simplicity, 
as no installation is necessary. The Spring implementation of the Java Persistence API is 
also included (JPA). This enables easy access to the database through annotations and 
*Repository interfaces.

Simply add the Starter you want as a dependency in your POM file and that's it, 
you're ready to roll.

There are three used here.
```
| Name | Description |
| --- | --- |
| spring-boot-starter-data-rest | Starter for exposing Spring Data repositories over REST using Spring Data REST. |
|spring-boot-starter-web | Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container. |
| spring-boot-starter-data-jpa| Starter for using Spring Data JPA with Hibernate. |
| spring-boot-mysql | mysql database |

### MVC

We're using a @SpringBootApplication annotation to specifically define our main application 
class, as defined in the Spring Boot Docs, with an Application class on the root package. 
This class accomplishes two notable goals. Our Spring Boot app is started first, and then 
our WebConfiguration class is imported.
```
The WebConfiguration is also very simple doing one thing. By extending the 
RepositoryRestConfigurerAdapter class and overriding its configureRepositoryRestConfiguration 
method it routes all of the RESTful API endpoints through the "/api" path.

## Database

- MySQL - The default RDBMS.
- H2 - Simple RDBMS to use for testing purposes.
- Hibernate - ORM

### Testing 

-JUnit - The main Testing Framework and Integration Tests 
-Mocikito - Mocking framework for Unit Tests
-Selenium - Front end testing 

```
an example
```

### FrontEnd
-HTML/CSS/JS - Just HTML/CSS/JS
-Bootstrap - Design Framework
- Axios - API

## Authors

* **Sanru Mathy** - *Initial work* - https://github.com/San-Qa

## WireFrame

* **Jira** - https://smathyqa.atlassian.net/jira/software/c/projects/CR/

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
