# Clean Architecture Study Series

This series intends to implement some projects in which we will discuss the concepts regarding clean, decoupled and testable code strategies. 
The main  purpose is to create structures that would be easy to read (understand), maintain and evolve.

## Episodes
- **Episode I: Service/DTO Model (Synchronicity) - (WE ARE HERE!)**
- Episode II: Service/DTO Model (Parallelism) - (WORKING ON...)  
- Episode III: Micro Service

## The Problem 
The purpose of the project is to analyze if the best hand had won or lost after a pre-flop all-in action.
For that, it will consume a file containing the outcome of poker hands.

The logic must:
	
1. Separate the hands in blocks containing the player's data (cards, earned values etc).
2. Evaluate if the winner hand held the best cards (before pre-flop action).
3. Show the result: 
		total tied: ?
		total best: ?
		total worst: ?
	
Obs: Optionally, the evaluation service could be filtered by "player name" and/or "aggressivity behaviour".   

# Episode I: Service/DTO Model (Synchronicity) 

The main idea is to breakdown the problem above in small units (Services). Each unit produces its output object (DTO) that can be shared with other units as input parameters. **It is important to mention that "the unit" must enclose all resources and logic needed to process its data and generate its outcome.**

### Project Overview

![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-sync/md-files/overview.svg)

### Decoupled Units

The main problem was separated into smaller "units" to establish concise and clear boundaries.  

**File Unit (FileParserService)**
- Responsibility: Process the file content and parse the lines to a list of hand's objects.
- Consume: Hand's file.
- Produce: List of hand's objects.

**Mapper Unit (HandMapperService)**

- Responsibility: Organize the hand's objects in blocks per hand code.

- Consume: Outcome of "File Unit".
- Produce: Map of hand's grouped by hand code.

**Evaluator Unit (HandEvaluatorService)**

- Responsibility: 
    - Identify the best cards in the hand.
    - Evaluate if the best card had won the hand.
    - Filter the content by player name and/or aggressivity behaviour (optional).

- Consume: Outcome of "Mapper Unit".
- Produce: List of the winner's hands and its evaluation.

**Statistic Hand Type Unit (StatisticHandTypeService)**

- Responsibility: Organize the data in separated groups of hand types ("Best Hand Win", "Worst Hand Win" and "Tied").

- Consume: Outcome of "Evaluator Unit".
- Produce: Hand Types Mapped.

**Statistic Viewer Unit (StatisticHandTypeViewerSevice)**

- Responsibility: Show the evaluation statistic.

- Consume: Outcome of "Statistic Hand Type".

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

The only certainty we have is that our code will change in the future. So, if It would be hard to understand and maintain, most likely we will spend more time navigating in the chaotic code than implementing the changes.  
     
The most important message here is "Stop, Think, Plan and then Implement/Test".  

