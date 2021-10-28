### Tech Stack

- Java
- Spring Boot
- Maven
- [GraphQL](https://github.com/leangen/graphql-spqr)

### O que é?

CRUD utilizando GraphQL

### Como rodar?

- Execute **`mvn clean package`**
- Execute **`mvn spring-boot:run`**

> **POST** http://localhost:8080/graphql

### GraphiQL Playground

http://localhost:8080/

### Exemplos

```
    query {
        findAllPerson {
            id
            name
            dtBirth
            age
            genre
        }
    }
    
    query {
        findByIdPerson (id:1) {
            id, name, genre, age
        }
    }

    mutation {
        createPerson (
            person: {
            name: "Luciano"
            dtBirth: "1984-01-31"
            genre: MALE
            age: 36
        }) {
            id, name, age, genre
        }
    }
    
    mutation {
        updatePerson(person: {
            id: 1,
            name:"Ortiz",
            age: 99,
            dtBirth:"2020-09-18"
        }) {
            id
            name
            age
            dtBirth    
        }
    }
    
    mutation {
        deletePerson(id:1)      
    }
```