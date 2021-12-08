# Progress Report
<u>Cliff</u>
Cliff has been working on the front end while also giving feedback and suggestions for design tips in the backend.

PR: https://github.com/CSC207-UofT/course-project-group-020/pull/30/files

In this pull request you will find everything to do with the front end of the MonKeyPass Program.

<u>Ryan</u>

Ryan has been implementing the AccountController class which is responsible for
interaction between the frontend and backend. He has also been writing numerous 
test cases for AccountController. He has been providing input on solutions
to violations of Clean Architecture and SOLID along with helping with bug fixes
generally. 

PR: https://github.com/CSC207-UofT/course-project-group-020/pull/9

This pull request includes the first implementation of the Controller class. In this 
PR, Spring boot is integrated into the project through gradle and all the required 
java files are create. the POST and GET requests were created and implemented, although
they will be later changed to better fit with the Clean Architecure. Furthermore, the 
Factory Design Pattern was implemented in this PR as well. Without this PR, the backend
would stay disconnected from the frontend.

<u>Kelian</u>

PR: 

<u>Patricia</u>

Patricia has been implementing the PasswordCreation Class. She has also been working on redesigning the code structure since Phase 1 to resolve the Clean Architecture issues that were brought up. She has also been refactoring the code to remove code smells. 

PR: https://github.com/CSC207-UofT/course-project-group-020/pull/20

This is the implementation of the feature that LogIn PrivateInfo objects use to make sure that passwords are created properly. The main features of this class includes password generation and password rating. 

<u>Hayk</u>
Hayk has been working on backend and testing since Phase 1. Notably, he is moving specific methods from Account to Account Manager to adhere to clean architecture and also implementing test classes and test cases to test each newly implemented method.

PR: https://github.com/CSC207-UofT/course-project-group-020/pull/14
In this PR, as per the request of my team, I updated the architecture of our backend. More specifically, I transferred crucial methods,
addInfo, editInfo, deleteInfo from account class to the accountmanager usecase class to adhere to clean architecture. Furthermore, I changed createAccount, getAccount to work with new architecture, as well as made a new method, deleteAccount to remove files using java.File. Lastly, I fixed a small spelling error in Serializer class.

<u>Yousuf</u>

Yousuf has been helping to restructure the program to fix dependency issues, adding more test cases to AccountManagerTest.java (formerly named PrivateInfoTest.java), creating the test suite AccountTest.java, and documenting code.

PR: https://github.com/CSC207-UofT/course-project-group-020/pull/8

In this pull request, Yousuf fixed the issues causing 3 tests to fail in AccountManagerTest.Java and added 9 more, changed the implementation of deleteInfo which was causing it to throw an Exception previously, and also changed the return types of some methods from void to booleans since it made more logical sense. Another significant contribution was from Phase 0, which was implementing a functioning command-line interface that would allow us to demo our skeleton program. However, back then we didn't use pull requests to merge the code, so there is no PR link associated with that feature.
