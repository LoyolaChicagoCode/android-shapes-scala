## Android Shapes Project


### Learning Objectives

This example is intended as a starting point for Android apps involving
simple drawing. Its learning objectives are:

- Android application development using Scala
    - Use of Scala primarily as a "better Java"
      (including mutable state where appropriate)
    - using the Simple Build Tool (sbt) for Scala in conjunction with
      [pfn's well-maintained plugin](https://github.com/pfn/android-sdk-plugin)
    - using IntelliJ IDEA (optional)
- Simple graphical drawing (output-only) in Android
- Basic object-oriented design patterns and their purpose
    - Composite pattern
    - Decorator pattern
    - Visitor pattern
- Corresponding Scala idioms
    - case classes implementing a common trait
    - recursive functions with pattern matching on the case classes
- Testing techniques
    - behavior-driven design (BDD)
    - mock-based unit testing of objects with dependencies

### Functional Requirements

Start with a private fork of [this code skeleton](https://github.com/LoyolaChicagoCode/shapes-android-scala).

The functional requirements are embodied in the BDD tests in the src/androidTest folder; instructions for running the test are included in the README file. When your code passes all the tests, you will have fulfilled the functional requirements for grading purposes. If some of the tests do not pass, you will receive partial credit. In addition, your app should look exactly like the attached screenshot.

Specifically, complete the code in the various Java source files within the src folder. Look in the IntelliJ IDEA TODO view for sections marked as TODO (or FIXME) and use the test cases as your guide.

*Besides perhaps other minor tasks, the main implementation tasks are implementing the size, boundingBox, and Draw visitors.*

### NonFunctional Requirements

You must not make any other changes to the code skeleton or the test cases.

Also, this project requires you to understand a lot of things but not necessarily to write a lot of code. For example, the difference between this skeleton and my full solution is only 71 lines. You should try to stay within about 100 lines if you can.

### Building and Running

Please refer to [these notes](http://lucoodevcourse.bitbucket.org/notes/scalaandroiddev.html) for details.
