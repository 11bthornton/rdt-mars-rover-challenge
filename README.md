# Mars Rover Challenge, Programming Exercise - Ben Thornton
For RDT interview process. 

Contains tests and documentation too. 

**The version of Java used was Java SE 8** and I used a mix of **VsCode** (this markdown file) and **Eclipse IDE** (all the Java). 

The program is split into three packages:
- Control package - for reading input and launching the program.
- Models - houses models such as Rover, Instruction, Heading, Position, etc.
- Tests

**Time spent: estimates only**
- Planning, figuring out steps: 45 mins
- Writing the models and related unit tests: 2 hours
- Writing the mechanism to read inputs from file: 30 mins
- Writing the main algorithm: 45 mins
- Extra tests: 1 hr
- Documentation and commenting: 1hr
  + extra formatting
**Total: 6.5hrs roughly** but not all at once

**TEST COVERAGE PERCENTAGE: 92.6%**

**Example Input and Output**

In:

5 5

1 2 N

LMLMLMLMM

3 3 E

MMRMMRMRRM

Out:

1 3 N

5 1 E


Run example with ``java -jar .\mars-rover-challenge-executable.jar .\exampleprogram.txt``


More inputs:

6 5

0 0 N

MRMLMRMLMMR

6 1 W

MMMMMMM // Makes illegal move by going into west barrier, so stops at 0 1

6 2 N

LMMMMMMM // Same as above

1 0 N

MLM // Would crash into second rover at 0 1 so must stop at 1 1 

should provide

Rover 2 made an illegal move, stopping...

Rover 3 made an illegal move, stopping...

Rover 4 made an illegal move, stopping...

2 4 E

0 1 W

0 2 W

1 1 W


## Overview

This repository contains code for the mars-rover-challenge written in Java, using Object-Oriented design patterns. The code **is fully tested** (tests were written before coding where appropriate) **and commented with javadoc**. I had help from a tutorial online to capture print statement output for my units tests  [here](https://www.baeldung.com/java-testing-system-out-println).

### Assumptions
Here, I am making the following assumptions, which influenced how I designed the code:

- Inputs are in the form of text file. First line is bounds of plateau. Then two lines for every rover. First is heading and position and then program sequence.

- **If any rover input is illegal, the rovers defined before this rover (correctly) get run, and then parsing of the input file stops. This means that if the first rover is ill-defined, none will get run, and if only the last rover is, then all before it will.**

- Although the rovers are run sequentially, the rovers are all somewhere on the plateau at the same time.

- If the starting position of a rover wants to be where a rover is already placed, this rover is not placed, and its program does not get run.

- If a rover wants to make a move that is illegal, i.e. go out of bounds of the plateau or run into another rover, the rover's program terminates then and there. 

### Design Philosophy
The aim was to make each class be representative of their "real-life" (however sci-fi it may be) counterpart as much as possible, and for them to manage only the concerns as they might in real-life. For instance, a **MissionControl** class which keeps a list of Rovers and orders them to move is kept separate from the **Plateau** class (which merely houses some dimensions and an inBounds method) to make the program more semantically readable. **Rovers** themselves manage their own **Position** and are told to do so when **MissionControl** ***iterates*** over its **Program** (essentially a list of **Instruction** instances. Having each class worry about things only it should helped maintain **high cohesion**, although the classes were generally small anyway so abstracting out functionality more may have worsened readability. Rovers do not have a Plateau instance, for example, because it did not make sense that a Rover has a plateau, and this might be construed to imply that a rover knows inherently about all other rovers. They do not seem that smart. A mission control center might oversee everything, and this seemed the more intuitive, real-world matching way to go.

**Enums** were used to improve readability. For instance "R" is translated to **Instruction.ROTATE_RIGHT_NINETY_DEGREES** and "W" is translated to **Heading.WEST**. Not only does this help improve readability but it also helps manage legal state effectively, and easily detect when an **invalid input** is passed into the system. 

**Overriding** certain methods like **toString** and taking advantage of **Object-Oriented features** made tasks like outputting a Rover to stdout to produce the final result of the program easier and more natural.

**Input Validation** is performed when the inputs are parsed from strings to integers and exceptions are caught in try-catch blocks as and when appropriate. For instance, the use of  **Enums** as already mentioned can switch over the input character for the instructions and if and invalid character is provided, throw an exception which is then caught and displayed to the user. 

A **regular expression** is used to validate **Program** strings like "LRLMRMLLRLRL". 

Collisions are errors which are handled by program termination of the offending rover. A string is output to the terminal.
I tried boundary testing where possible (e.g. for edges of the plateaus), and covered scenarios in my main ApplicationTest where rovers would try to leave the plateau or hit another rover. I also tested for malformed inputs in the config file and how the program would handle that. 

#### Possible Improvements
- I could extend versions of classes like **IllegalStateException** / **IllegalArgumentException** to **IllegalHeadingException** or **IllegalInstructionException** so errors in the Inputs can be better dealt with. 
-  I'm providing default behaviour for when a **Rover** tries to make an illegal **Position** change. I could define an **Interface** that future developers could **implement** to define their own behaviour for what to do in this scenario.
- It won't scale so well, trying to move multiple **Rover**s at once in different threads may lead to data race type scenarios and having multiple **Rover**s in the same position on the **Plateau**.
- Could write a **ProgramOptimiser** which shortens programs by replacing things like "LR" with nothing for example, and "LLL" with "R".
- I wanted to use a lexer / parser generator but that was a bit overkill so I stuck with string-splitting and exception handling :)



