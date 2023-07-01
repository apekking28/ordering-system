# Ordering System Documentacion

## Project Description

Ordering System is a project built using Spring Boot, Spring Web, JPA, PostgreSQL, Maven, Lombok, and logging using @Slf4j annotations. This project aims to provide a RESTful API to manage the product ordering system.

The technologies and libraries used in this project include:

- **Spring Boot** : A Java framework used to build Spring-based applications in an easier and faster way. Spring Boot provides automatic configuration and simplifies the development of Spring-based applications.

- **Spring Web** : Spring module used to develop web applications using RESTful API architecture. Spring Web provides features such as routing, HTTP request processing, and HTTP response delivery.

- **JPA (Java Persistence API)** : A technology used to manage objects in a database using the ORM (Object-Relational Mapping) approach. JPA eases the development of database applications by abstracting interactions with relational databases.

- **PostgreSQL** : A relational database management system used as data storage in this project. PostgreSQL provides powerful features and good scalability.

- **Maven** : A project management tool used to manage dependencies, build projects, and manage the overall project lifecycle. Maven simplifies the development process and ensures project dependencies can be managed easily.
  
- **Lombok** : A Java library that reduces the writing of boilerplate code by automatically generating getter, setter, constructor, etc. code through annotations. Lombok increases developer productivity by reducing repetitive code writing.

- **Slf4j** : A logging library for Java that provides a common interface (API) to interact with various logging frameworks such as Logback, Log4j, and others. Slf4j allows developers to use a common interface independent of specific logging implementations.


This Ordering System project uses Spring Web to develop a RESTful API that allows clients to interact with the ordering system. Through this API, users can create new orders, manage products and customers, calculate the total price of the order, and so on.

By using the above-mentioned technologies, this project can be developed quickly and efficiently and provides the convenience of managing a reliable and scalable ordering system.


## Database Relationship

The **Ordering** System project uses PostgreSQL database to store data related to `orders`, `customers`, and `products`. The following is a description of the database relations used in this project:

<img width="350" alt="Screen Shot 2023-07-01 at 11 38 40" src="https://github.com/apekking28/ordering-system/assets/106460262/16294f0d-c644-41b7-ba83-a06765c966fb">

With this database relationship, the **Ordering System** project can connect customer, product, and order data by using foreign keys to build relationships between these tables.


## Getting Started

These instructions will guide you on how to get the project up and running on your local machine.

### Prerequisites
Make sure you have installed the following prerequisites before proceeding:

- `JDK 17`: Download and install JDK 17 according to your operating system.
- `Maven`: Download and install Maven according to your operating system.
- `JDBC-compatible relational database such as`  : MySQL, PostgreSQL, or Oracle. Make sure the database is installed and running.

### Installation Steps

Here are the steps to install and run the `Ordering System` application in your development environment:

1.**Clone Repository** :
Open a terminal or command prompt at the desired location on your computer.
Run the following command to clone the `Ordering System` repository :

```bash
git clone https://github.com/apekking28/ordering-system.git

```

2.**Database Configuration** :
Open the application configuration file application.properties in the project directory.
Change the database connection settings according to the database settings you are using. For example, if you are using PostgreSQL, change the settings as follows:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

```
3.**Build and Run Apps** : 
- Open a terminal or command prompt in the `Ordering System` project directory.
- Run the following command to build the application using Maven:

```bash
mvn clean install
```
- Once the building process is complete, run the application with the following command :
``` bash
java -jar target/ordering-system.jar
```

4.**Test Applications with Postman** :
- Open Postman or a similar tool to test the API.
- Use the URL `http://localhost:8282` to access the `Ordering System` application API.
- Start testing and using the various endpoints provided by the application, such as creating new orders, managing products and customers, and so on.

Now you have successfully installed and run the `Ordering System` application in your development environment. Have fun using it!














