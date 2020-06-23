
# Clean Architecture Study Series

This series intends to apply a code hands-on approach to discuss the following code perspectives:
* **Decoupling**
* **Testability**
* **Scalabiilty** 

The inital questions that motivated this series were: 
1. *Why sometimes my code is hard to mantain?*
2. *Why is it so hard to implement tests?*
3. *Why implementing tests seems to be too expesive?*

Now, after some progress working on the problem, I would say: "**It is hard to implement tests in something that was not built to be tested!. The way that we follow to think and implement our solutions impacts directly on its testability and manutenibility.**"


## The Problem 
As a target problem, It was chosen something realtively simple: Read lines from a file, process them and  show the result.
The problem itself is just a poker hands evaluation and show basic statistics.

## Episodes

- **Episode I: Decoupling the Problem Aiming Testability - (WE ARE HERE!) **
- Episode II: Increasing decoupling (Event Queue) https://github.com/wmaduro/clean-architecture-study/tree/master-eventbus
- Episode III: Increasing decoupling and scalability (SpringBoot Services Containarization) https://github.com/wmaduro/clean-architecture-study-ms-modules/tree/master
- Episode IV: High sacalability approach (SpringBoot Cloud MicroServices) - Coming Soon... 

# Episode I: Decoupling the Problem Aiming Testability

The main idea is to breakdown the problem in small services. Each service produces its output object (DTO) that can be shared with other services as input parameters. **It is important to mention that "the service" must enclose all resources and logic needed to process its data and generate its outcome.**

### Decoupling

![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-sync/md-files/the-problem.svg)

The main problem was separated into smaller "services" to establish concise and clear boundaries.  

**File Parser Service**
- Responsibility: Process the file content and parse the lines to a list of hand's objects.
- Consume: Hand's file.
- Produce: List of hand's objects.

**Hand MapperService**

- Responsibility: Organize the hand's objects in blocks per hand code.

- Consume: Outcome of "File Unit".
- Produce: Map of hand's grouped by hand code.

**Hand Evaluator Service**

- Responsibility: 
    - Identify the best cards in the hand.
    - Evaluate if the best card had won the hand.
    - Filter the content by player name and/or aggressivity behaviour (optional).

- Consume: Outcome of "Mapper Unit".
- Produce: List of the winner's hands and its evaluation.

**Statistic Hand Type Service**

- Responsibility: Organize the data in separated groups of hand types ("Best Hand Win", "Worst Hand Win" and "Tied").

- Consume: Outcome of "Evaluator Unit".
- Produce: Hand Types Mapped.

**Statistic Hand Type Viewer Sevice**

- Responsibility: Show the evaluation statistic.

- Consume: Outcome of "Statistic Hand Type".

### Project Overview

![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-sync/md-files/overview.svg)


### Tests

The principle here is: "Each test must be atomic". So, It must have run in a completely independent environment (when necessary, creating its resources).

- **Unit Tests**

    The unit tests aimed to cover each "service" scenario and all of its exceptions.

- **Integrated Tests** (Not implemented yet*)

    The integrated test is responsible for:
    
    - Create the simulation's input file.
    - Process all services in the right order.
    - Evaluate the outcome.

## How to run

### Requirement(s)
- **GIT**
- **JDK 8+**

### Step by Step (LINUX)
1. Clone the repository: **git clone -b master-sync https://github.com/wmaduro/clean-architecture-study.git**
2. Jump into the project folder: **cd clean-architecture-study**
3. Change permission: **chmod +x mvnw**
4. Compile using built-in maven  (notice that all unit tests will be triggered): **./mvnw clean compile package**
5. Run: **java -cp 'target/lib/*:target/clean-architecture-study-0.0.1-SNAPSHOT-jar-with-dependencies.jar' com.maduro.poker.ClenArchitectureStudyApplication**


Optionally, you can import the project in Eclipse 4+ with maven plugins installed. 

## Backlog

1. Implement the integrated test.


## Conclusion

The only certainty we have is that our code will change in the future. So, if it would be hard to understand and maintain, most likely we will spend more time navigating in the chaotic code than implementing the changes.  
     
The most important message here is "Stop, Think, Plan and then Implement/Test".  

