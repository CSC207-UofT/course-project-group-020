# Phase 2 Design Document

### Specification

The password manager is a multi-user application that securely stores and retrieves sensitive data for its accounts. The user can create new accounts, sign in to an account, and sign out of an account, and delete accounts. To create a new account, the user must set a username and a master password. The password is salted and hashed (one-way encrypted) as a security measure. Each account can store entries of login information, notes, contacts, and/or identification information. The entries in each account are private to the other accounts and are also only accessible to the user when they are signed in. Each entry is stored in our program but encrypted using Blowfish, a symmetric block cipher. It is encrypted by the plaintext of the master password of the given account which is unattainable for developers. The encrypted entry is stored in a serialized bin file located at a specified file path given by the user.
When the user is creating a new login entry, the application gives the user the option to generate a strong password based on an algorithm developed in-file. The user can access the entries they have stored through the main menu of the application, which displays all the entries of the data that the manager is currently storing. The entry is accessed by decrypting the ciphertext from the serialized bin file and displayed for the user. Furthermore, any given entry of any type can be also edited and deleted by the user. An exception to this is when the user is accessing login information. In that case, the password is displayed in asterisks but it can be revealed by clicking the monkey icon.


### Major Design Changes

One major change that we decided on occurred when we realized an issue with Clean Architecture in our design. The issue was that AccountController was acting as both a controller and a use case when it was supposed to only act as a controller. For example, when AccountController received a request to create a new account, it would create an instance of the entity class Account, and then serialize it. This also violated Clean Architecture because a class in the Interface Adapters Level was directly dependent on an entity class. To fix this issue, we had the controller create instances of AccountManager. And through AccountManager it was allowed to perform its desired actions. We also implemented a PrivateInfo Factory, which creates instances of PrivateInfo, such as LogIns, Notes, Contacts, and Ids, which help the controller class to adhere to the dependency rules of clean architecture. Furthermore, PrivateInfoManager was removed as it was deemed useless and bloated the structure of our program. Its methods were either completely removed or rather, moved into AccountManager. And Private info instances were given a unique ID using Java’s built-in UUID feature, which allowed for easier communication between frontend, spring, and backend.



### Clean Architecture

In the innermost layer, the classes LogIn, Note, ID, and Contact are subclasses of PrivateInfo, an abstract entity class. These classes are independent of the rest of the program, as they do not depend on any other classes. They should only have getter and setter methods. Next up, we have the use case classes, these include the Serializer, AccountManager, PasswordCreation, and the Encryption classes, MasterEncryption and PrivateInfoEncryption. Some use cases interact with the entities directly such as AccountManager, while others such as the encryption classes, tend to interact with other use cases. The use cases all interact with other use cases or depend on other entities.
From phase 1, we have completely established a good and clean architecture within our Password Manager Program. Everything is dependant on the level below it, besides obviously the Entity classes. We fixed dependency issues where the controller would instantiate and call entity classes by having an entity class, AccountManager, stationed between them with useful methods. 
Furthermore, we implemented a couple of interfaces to help with not only clean architecture, but also organization. SecureHashEncryption implements IMasterEncryptor, BlowfishEncryption implements IPrivateInfoEncryptor, and Serializer implements ISerializer. In terms of clean architecture, these interfaces make it so these use case classes are not coupled together.



### SOLID Design Principles

Our program follows the Single Responsibility Principle because each class only has one responsibility, and it is laid out in the name of the classes. For example, our original design only had one Encryption class to encrypt the private info, but we decided to split the class into two classes, SecureHashEncryption and BlowfishEncryption because they perform different functions in that they encrypt different objects, and these objects require different encryption systems so it made sense to put them in two different classes.
For the Open/Closed Principle, since our program generally follows Clean Architecture, this has made the program easy for extension but closed for modification. At the outer layers, the client only has access to the controller and other interface adapters, but they are unable to reach the use cases and the entities by bypassing these interface adapter classes. The core of the program: the entities, are protected by all the layers of Clean Architecture.
For Liskov’s Substitution Principle, the only inheritance relationship we have is for PrivateInfo and its subclasses. This inheritance relationship makes sense because the subclasses share a “is a” relationship with PrivateInfo. In the AccountManager, we define methods that provide functionality such as adding info and deleting info, and no matter which subclass we use, such as LogIn or Note, they will not affect the performance of the program differently.


### Packaging Strategies

We decided to Package by Component. For example, the Encryption classes encapsulate a set of related functions, which are encryption. Similarly, the classes related to the Accounts, namely Account and AccountManager, are also in the same package. We found that this organizational structure made the most intuitive sense for organizing the program, as the classes that performed related functions were grouped together.


### Design Patterns



### Testing

From Phase 1, we significantly improved on testing our program. Instead of having only one test suite we now have five different test suites all with numerous test cases which test almost all of the methods implemented in our program. To elaborate more, some of the things we test are successful serialization and deserialization, adding, editing, deleting info from a user’s account, as well the creation and deletion of said account, so go along with all the tests for the AccountController class.


### Open Questions

At what point were our classes too big? We had some classes with 200 to 250 lines of code and we were not sure if we were guilty of a code smell or not. Some people within our group claimed that this amount was not too much to be considered a code smell, yet others disagreed and said that we were at risk. One thing that we did also consider, was that if the class was even capable of splitting up, and in most cases, it was not possible. So how would one balance the fact that a class is very long, which is a code smell, but then also, the same class is not possible for splitting up.


### What Has Worked Well

For Phase 2, the implementation of Serialization has worked really well. Accounts and their respective vaults are being saved in our data folder and whenever necessary, they can be deserialized and used. This allows us to save accounts and allow the user to continue from where they left off. Also, the addition of UUID to every PrivateInfo instance also really helped to simple communication between front end and back end as well as making some backend methods much simpler.


