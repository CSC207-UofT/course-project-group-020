# Phase 2 Design Document

### Major Design Changes

One major change that we decided on occurred when we realized an issue with Clean Architecture in our design. The issue was that AccountController was acting as both a controller and a use case when it was supposed to only act as a controller. For example, when AccountController received a request to create a new account, it would create an instance of the entity class Account, and then serialize it. Whenever the controller received data from the UI, it would have to manipulate the entity itself. This violated Clean Architecture because a class in the Interface Adapters Level was directly dependent on an Entity class. To fix this issue, we had the controller create instances of AccountManager and direct info to the AccountManager instead. Then, the AccountManager would perform manipulations on Accounts. This resolved the dependency issue because the controller is now dependent on a Use Case, and it also helps us fulfill the SRP because the controller is now only responsible for directing requests to the use cases. Furthermore, after Phase 1, we evaluated the responsibilities of each of the classes, and determined that the PrivateInfoManager class and AccountManager were both responsible for managing Accounts, so we merged them into just AccountManager, thereby lessening bloat in the program. Lastly, PrivateInfo instances were given a unique ID using Java’s built-in UUID feature, which allowed for easier communication between frontend, spring, and backend.

### Clean Architecture

At the entity layer, the classes LogIn, Note, Identification, and Contact are subclasses of PrivateInfo, an abstract entity class. Account is another entity class, which represents an Account within the program. These classes store the core business data, since the purpose of the program is to store PrivateInfo objects securely, and to do so, the accounts need to be secured as well. These classes are independent of the rest of the program, so changes in the “details” will not affect how these entity classes are structured.

The main use case classes include Serializer, AccountManager, PasswordCreation, SecureHashEncryption, and BlowfishEncryption. These use cases sometimes depend on each other (oftentimes indirectly through an interface, such as AccountManager and IMasterEncryptor) and also depend on entity objects.

At the interface adapter level, we have the AccountController, which directs user input from the UI to the use cases. At the Frameworks and Drivers level, there is the class RestServiceApplication, which is the driver class, and there is the Javascript classes, which create the front end of the program. 

In a typical scenario, the main method would run in the RestServiceApplication class, which would run Spring, and start up the local host. When the user visits the website, they can interact with the GUI. 

When the user tries to create an account by pressing the “Create an Account” tab, a request is sent to AccountController. The controller asks the AccountManager to create a new account through a method call. The AccountManager does this by instantiating a new Account class. If this Account does not already exist, the method call will return a true value, which the controller sends back to the user interface to display to the user. The controller currently also serves as the presenter, since it is telling the UI what to display based on what the method call returns. As the program becomes more complex, it would be optimal to create a separate presenter class in case we wanted to change how we were sending information to the UI. 

### SOLID Design Principles
In terms of clean architecture, these interfaces make it so these use case classes are not coupled together.

We tried to follow the Single Responsibility Principle by making sure each class is responsible to one actor. For example, there are two classes that encrypt PrivateInfo. The SecureHashEncryption class is responsible for encrypting PrivateInfo objects, while the BlowfishEncryption class is responsible for encrypting the passwords to Accounts. If in the future, we wanted to change how PrivateInfo is encrypted, we can do so without messing up how master passwords are encrypted. 

For the Open/Closed Principle, since our program generally follows Clean Architecture, this has made the program easy for extension but closed for modification. At the outer layers, the client only has access to the controller and other interface adapters, but they are unable to reach the use cases and the entities, so it is easy to change the user interface, such as the colour scheme, or the placement of buttons. Furthermore, if we wanted to create another type of PrivateInfo, different from the existing four, we would not have to delete any existing code, but we could simply create a new subclass of PrivateInfo and keep the rest of the program the same. The inheritance relationship between PrivateInfo and its subclasses allows us to easily extend our program to create new PrivateInfo types. 

For Liskov’s Substitution Principle, the only inheritance relationship we have is for PrivateInfo and its subclasses. This inheritance relationship makes sense because the subclasses share a “is a” relationship with PrivateInfo. In the AccountManager class, we define methods that provide functionality such as adding, editing, and deleting info, and no matter which subclass we use, such as LogIn or Note, they will not affect the performance of the program differently.

In terms of the Interface Segregation principle, as an example, there are two Encryption interfaces: IMasterEncryptor, and IPrivateInfoEncryptor. These interfaces were segregated because master passwords need a one way encryption algorithm like SHA-256, while PrivateInfo objects need a two way encryption algorithm, like Blowfish. We deliberately separated these interfaces instead of keeping them as one interface because this would have forced classes to implement methods that they didn’t need. For example, one-way encryption does not need a method to decrypt, but two-way encryption does. 

Lastly, for the Dependency Inversion Principle, following Clean Architecture meant that high level modules do not depend on low level modules. The use of interfaces also aided in decoupling the modules. For example, the AccountManager is dependent on the IMasterEncryptor interface, which allows us to decouple AccountManager from SecureHashEncryption. By introducing the abstraction, we allow the program not to depend on a detail such as an encryption algorithm, so in the future we could easily switch to a new algorithm in a class that implements the IMasterEncryptor. 

### Packaging Strategies

We decided to Package by Component. For example, the Encryption package encapsulates a set of related functions, which are encryption. Similarly, the classes related to the Accounts, namely Account and AccountManager, are also in the same package. We found that this organizational structure made the most intuitive sense for organizing the program, as the classes that performed related functions were grouped together.

### Design Patterns

For Phase 2, we implemented three different design patterns: Simple Factory design pattern, Strategy design pattern, and Dependency Injection design pattern.

The Simple Factory design pattern was implemented in the “Spring test” pull request(#9) in order to create instances of subclasses of PrivateInfo. We used this design pattern because we found there were often times where we had to create instances of subclasses but we were not exactly sure which we would have to create. By extracting the constructors into a PrivateInfoFactory class, we were able to write code to determine which type to create based on the input parameters. With more time, we could upgrade to a more complex factory design pattern, such as the Abstract Factory Design Pattern.

The Strategy design pattern was implemented in order to make our program more extendable. We applied the design pattern twice to encrypt the master password and the PrivateInfo objects, making it very easy to add/switch between different encryption methods other than the ones we used. The IMasterEncryption and IPrivateInfoEncryption outlines the necessary methods. In the future if we wanted to change encryption methods, we would create a new class that implements IMasterEncryption or IPrivateInfoEncryption, and we would not have to modify the existing code very much. This was implemented in pull request #34.

Dependency Injection was used twice in order to remove tight coupling between use case classes. This was used to decouple Serializer and AccountManager and SecureHashEncryption and AccountManager. Dependency Injection was mostly implemented in pull request #46 but some interfaces were created in earlier pull requests.

### Testing

From Phase 1, we significantly improved on testing our program. Instead of having only one test suite we now have five different test suites all with numerous test cases which test almost all of the methods implemented in our program. To elaborate more, some of the things we tested were successful serialization and deserialization, adding, editing, deleting info from a user’s account, as well the creation and deletion of said account, to go along with all the tests for the AccountController class and more.

### Open Questions

At what point were our classes too big? We had some classes with 200 to 250 lines of code and we were not sure if we were guilty of a code smell or not. Some people within our group claimed that this amount was not too much to be considered a code smell, yet others disagreed and said that we were at risk. One thing that we did also consider, was that if the class was even capable of splitting up, and in most cases, it was not possible. So how would one balance the fact that a class is very long, which is a code smell, but then also, the same class is not possible for splitting up.

### What Has Worked Well

For Phase 2, the implementation of Serialization has worked really well. Accounts and their respective vaults are being saved in our data folder and whenever necessary, they can be deserialized and used. This allows us to save accounts and allow the user to continue from where they left off. Also, the addition of UUID to every PrivateInfo instance also really helped to simplify communication between the front end and the back end as well as making some backend methods much simpler.

