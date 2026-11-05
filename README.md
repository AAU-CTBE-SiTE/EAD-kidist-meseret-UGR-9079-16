# Spring IoC and Dependency Injection

## 1. Introduction
This project demonstrates Spring IoC (Inversion of Control) and Dependency Injection (DI). It shows how Spring manages objects (beans) and injects their dependencies.

## 2. XML-Based Configuration

Initially, the project used XML configuration in `beansConfig.xml` to define Spring beans and their dependencies.

The XML configuration demonstrated:

- Bean creation
- Setter injection
- Constructor injection
- Object dependency injection
- Collection injection
- Singleton and Prototype bean scopes

Spring used the XML file to create and manage objects.

## 3. Conversion to Annotation-Based Configuration

The XML configuration was replaced with Java annotations. Instead of defining beans and dependencies in `beansConfig.xml`, Spring now uses annotations and a Java configuration class.

The main annotations used are:

- `@Configuration` – identifies the configuration class.
- `@Bean` – defines Spring beans.
- `@Component` – marks a class as a Spring-managed component.
- `@ComponentScan` – tells Spring where to find components.
- `@Value` – provides simple values to beans.
- `@Autowired` – injects dependencies automatically.
- `@Scope` – defines bean scope, such as Singleton and Prototype.

The `AppConfig.java` class replaces the XML configuration file, and `AnnotationConfigApplicationContext` is used to start the Spring container.

## 4. Main Java Classes

- **App.java:** Runs the application and displays the results.
- **Student.java:** Represents a student and demonstrates dependency injection.
- **Address.java:** Represents the student's address.
- **Subject.java:** Represents student subjects.
- **AppConfig.java:** Configures and manages Spring beans using annotations.

## 5. Conclusion

The project was initially implemented using XML-based configuration and later converted to annotation-based configuration.

Both approaches demonstrate Spring IoC and Dependency Injection. Annotation-based configuration reduces XML usage and allows bean configuration directly in Java classes.

The final application runs using annotation-based configuration without requiring the previous `beansConfig.xml` file.