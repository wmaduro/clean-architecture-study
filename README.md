

# Clean Architecture Study Series

This series intends to be a hands-on discussion about ways to get a better code-design. It will mainly cover the following perspectives:
* **Decoupling**
* **Testability**
* **Scalability** 

The initial questions that motivated this series were: 
1. *Why sometimes the code is hard to maintain?*
2. *Why is it so hard to implement tests?*
3. *Why implementing tests seems to be too expensive?*

Now, after some progress working on those dilemmas, I would guess that: "**It is hard to implement tests in code that was not built meant to be tested! The way we think and implement our solutions impacts directly on its testability and maintainability.**"


## The Problem 
As a target problem, It was chosen something relatively simple: Read lines from a file, process them and show the result.

The problem itself is just a poker hands evaluation and show basic statistics.

## Episodes

- **Episode I: Decoupling the Problem Aiming Testability - (WE ARE HERE!)**
- Episode II: Increasing Decoupling (Event Queue) https://github.com/wmaduro/clean-architecture-study/tree/master-eventbus
- Episode III: Increasing Decoupling, Testability and Scalability (SpringBoot and Docker) https://github.com/wmaduro/clean-architecture-study-ms-modules/tree/master
- Episode IV: High Scalability (SpringBoot Cloud MicroServices) - https://github.com/wmaduro/clean-architecture-study-ms-modules/tree/master-episodeIV 

# Episode I: Decoupling the Problem Aiming Testability

The main idea of this episode is to breakdown the problem in small services. Each service produces its output object (DTO) that can be shared with other services as input parameters. **It is important to mention that "the service" must enclose all resources and logic needed to process its data and generate its outcome.**

### Decoupling

![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-sync/md-files/the-problem.svg)

**File Parser Service**
- Responsibility: Process the file content and parse the lines to a list of hand's objects.
- Consume: Hand's file.
- Produce: List of hand's objects.

**Hand Mapper Service**

- Responsibility: Organize the hand's objects in blocks per hand code.

- Consume: Outcome of "File Parser Service".
- Produce: Map of hand's grouped by hand code.

**Hand Evaluator Service**

- Responsibility: 
    - Identify the best cards in the hand.
    - Evaluate if the best card had won the hand.
    - Filter the content by player name and/or aggressivity behaviour (optional).

- Consume: Outcome of "Hand Mapper Service".
- Produce: List of the winner's hands and its evaluation.

**Hand Type Service**

- Responsibility: Organize the data in groups of hand types ("Best Hand Win", "Worst Hand Win" and "Tied").

- Consume: Outcome of "Hand Evaluator Service".
- Produce: Hand Types Mapped.

**Hand Type Viewer Service**

- Responsibility: Show the evaluation statistic.

- Consume: Outcome of "Hand Type Service".

### Project Overview

![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-sync/md-files/overview.svg)


### Tests

In this episode, It was implemented only the unit tests.

## How to run

### Requirement(s)
- **GIT**
- **JDK 8+**

### Step by Step (LINUX)
1. Clone the repository: **git clone -b master-sync https://github.com/wmaduro/clean-architecture-study.git**
2. Jump into the project folder: **cd clean-architecture-study**
3. Change permission: **chmod +x mvnw**
4. Compile using built-in maven  (notice that all unit tests will be triggered): **./mvnw clean package**
5. Run: **java -cp 'target/lib/*:target/clean-architecture-study-0.0.1-SNAPSHOT-jar-with-dependencies.jar' com.maduro.poker.ClenArchitectureStudyApplication**


Optionally, you can import the project in Eclipse 4+ with maven plugins installed. 

## Backlog

1. Implement the integrated test.


