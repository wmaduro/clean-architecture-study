# Clean Architecture Study Series

## Episodes

- Episode I: Decoupling the Problem Aiming Testability - https://github.com/wmaduro/clean-architecture-study/tree/master-sync
- **Episode II: Increasing Decoupling (Event Queue)  (WE ARE HERE!)**
- Episode III: Increasing Decoupling and Scalability (SpringBoot Services Containerization) https://github.com/wmaduro/clean-architecture-study-ms-modules/tree/master
- Episode IV: High Scalability Approach (SpringBoot Cloud MicroServices) - Coming Soon... 

# Episode II: Increasing Decoupling (Event Queue)

The purpose of this episode is to increase the decoupling among services by using publish/subscribe queues (EventBus). 
Here, we have a additional service responsible for monitoring the folder (FolderMonitorService).

Please, get back to the first episode ([https://github.com/wmaduro/clean-architecture-study/tree/master-sync](https://github.com/wmaduro/clean-architecture-study/tree/master-sync)) if you would need to more 
information to understand the problem solved here.

### Project Overview
![alt text](https://raw.githubusercontent.com/wmaduro/clean-architecture-study/master-eventbus/md-files/overview.svg)

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
4. Compile using built-in maven  (notice that all unit tests will be triggered): **./mvnw clean compile package**
5. Run: **java -cp 'target/lib/*:target/clean-architecture-study-0.0.1-SNAPSHOT-jar-with-dependencies.jar' com.maduro.poker.ClenArchitectureStudyApplication**

Optionally, you can import the project in Eclipse 4+ with maven plugins installed. 

## Backlog

1. Implement the integrated test.

