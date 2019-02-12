# University-system-INFO5100

This is the midterm project for **INFO5100 in Northeastern University**. It's a student management system which supports Student, Teacher, Course Manager, Finance Manager to registration, transfer, etc.

For more specific introduction of functions, please see [here](https://github.com/LiXuanqi/university-system/blob/master/INFO5100-2018-Fall%20Midterm%20Exam.pdf).


## Getting Started
### Prerequisites

- Java8
- Maven

### Example

This project provides all entities that you might use in an university management system. You can use it to build your own application such as CLI or Web Application.



In order to see a demo, you should do as follow.

- Firstly, change the file path in `Database` class to fit your computer.

```java
    public static String[] ASSIGNMENTS_PATHS = {
            "/Users/lixuanqi/Github/university-system/src/main/resources/assignment1",
            "/Users/lixuanqi/Github/university-system/src/main/resources/assignment2"
    };

    public static String[] SUBMISSION_PATHS = {
            "/Users/lixuanqi/Github/university-system/src/main/resources/submission1",
            "/Users/lixuanqi/Github/university-system/src/main/resources/submission2"
    };
```

- And then, use `mvn clean install` to compile.

- Finally, run the file `/target/university-1.0-SNAPSHOT.jar` with the command.

```shell
java -cp university-1.0-SNAPSHOT.jar university.University
```

More test cases are in the `/src/test` folder.

I'm sorry there is no CLI mode.

### Running the tests

```bash
mvn test
```

## Authors

- **Xuanqi Li** - *Initial work* 

See also the list of [contributors](https://github.com/LiXuanqi/university-system/graphs/contributors) who participated in this project.