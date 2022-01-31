#Which Exercise


This application is about to locate the missing kittens using forensics API provided by http://which-technical-exercise.herokuapp.com/.

## Get the code

Git:

    git clone https://github.com/vimleshgupta/whichexercise.git
    cd whichexercise
    
### The project directory structure
This project is a Spring boot application and has Gradle build script. We have used following technology stacks:
1. Java
2. Spring boot
3. Gradle
4. Groovy - For unit test
5. Spock Framework - For unit test

It follows the standard directory structure used in most projects:
```Gherkin
src
  + main
    + java                         Main production code
    + resources                    Configuration properties
  + test
    + groovy                       Tests
```


## Run the tests
You can run either just the main test `MainApplicationSpec` or to run all the tests, you can use the gradle command `./gradlew clean build test` from command line.
This will run all the tests and you can find the html report in the `build/reports/tests/test/index.html` path.


![image](https://user-images.githubusercontent.com/7358843/151858400-82819720-5588-4b88-aff2-2284e4ae0586.png)

