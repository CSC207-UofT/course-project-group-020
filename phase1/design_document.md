
# Phase 1 Design Document

### Specification

The password manager is a multi-user application that securely stores and retrieves sensitive data for its accounts. The user can create new accounts, sign in to an account, and sign out of an account. To create a new account, the user must set a username, password, and key. The password is salted and hashed (one-way encrypted) as a security measure. Each account can store entries of login information, notes, contacts, and/or identification information. The entries in each account are private to the other accounts, and are also only accessible to the user when they are signed in. Each entry is encrypted using Blowfish, a symmetric block cipher. The encrypted entry is stored in a text file located at a specified file path given by the user.

When the user is creating a new login entry, the application should rate the strength of the password and should also give the user the option to generate a strong password.
The user can access the entries they have stored through two methods. They can navigate the main menu of the application, which displays all the entries of the data that the manager is currently storing. The user can also use a search function that finds the data entry directly. The entry is accessed by decrypting the ciphertext from the text file using the key, and displayed for the user. An exception to this is when the user is accessing login information. In that case, the password is displayed in asterisks, but can be copied to the user’s clipboard.

The user should also be able to edit and delete entries as needed.

### Design Changes

Previously, we required the user to enter a 3 digit key as well as a master password. The master password was required to enter the account, and the key was used to encrypt the vault. We have decided to get rid of the 3 digit key necessary altogether, and only keep the master password. It did not make sense to have the user memorize 2 passwords, and it also did not make sense to store the 3 digit key in the program. Instead, we are encrypting with their master password. However, we never store the user’s master password. The program will remember the encrypted password during the run duration of the program, and it will forget as soon as the program shuts down. We only keep hashed versions of their master password for logging-in purposes.

### Clean Architecture

In the innermost layer, the classes LogIn, Note, ID, and Contact are subclasses of PrivateInfo, an abstract entity class.  These classes are independent to the rest of the program, as they do not depend on any other classes. They should only have getter and setter methods. 

Currently, the Account entity class is dependent on the PrivateInfoManager class, and this is a violation of Clean Architecture because an Entity Class is dependent on a Use Case. The plan to fix this dependency issue is to store PrivateInfo objects directly in an Account, and keep the Accounts stored in AccountManager. Then, a PrivateInfoManager will take an Account in its constructor, and that is how PrivateInfoManager stores PrivateInfo objects. 

PrivateInfoManager, AccountManager, Serializer, EncryptMaster, and EncryptPrivInfo are all examples of Use Cases. Some use cases interact with the entities directly such as AccountManager, while others such as the encryption classes, tend to interact with other use cases. The use cases all interact with other use cases or depend on other entities. 

At the Interface Adapter Level, there should be controller classes that process and validate user input and that assign it to the appropriate use case of AccountManager or PrivateInfoManager. We have one driver class called PasswordManagerProgram where the main method is located. 

The UIMain used to be the command line interface for the program, but it is not in use anymore, as we are moving to a gui. The classes for this are in a separate branch called springTest, and they do not appear in the main branch as they are still in development, so they do not appear on the class diagram. 

Furthermore, there are not big jumps from outer layers to inner layers. Interface Adapters only depend on Use Cases, and use cases only depend on other use cases or entities. 

### SOLID Principles

### Packaging Strategies


### Design Patterns
The Factory Method design pattern is a good solution for when a framework needs to standardize the architectural model for a range of applications. Using this design pattern, we will create an interface that provides the outline for creating an object. However, the class that implements the interface will not be instantiated, one of the subclasses will be!
So, why is this relevant to our program? Well, our program has one main entity, PrivateInformation, but there are many different types (such as a LogIn, Contact, Notes, etc). These subclass entities share some similarities, but also have unique attributes and methods associated with them. For example, a LogIn has only a username and password (so, its methods are based around those two attributes), but Notes have an id, title, and content (so, its methods are based around those). At the same time, they both objects need to be able to represent themselves as strings, they need to be able to be encrypted and decrypted, and they share some other common functionalities as well.
We can take advantage of the Factory Method design pattern by having the parent class entity PrivateInformation implement an interface that will have methods that are common to each subclass of PrivateInformation. However, we will be instantiating one of the subclasses (LogIn, Contact, Notes) depending on what object we want to create.


### Open Questions
Some open questions we had were:
- What things should we keep in mind if we want to ensure our program is scalable? More specifically, if we wanted to grow the types of PrivateInfo our program has, how should we structure/restructure our current entity classes?

### What Has Worked

What has worked so far is having an inheritance relationship between PrivateInfo and LogIn, ID, Contact, and Note. In Phase 0, we only implemented LogIn, so we could not reap the benefits of the inheritance relationships, but now that every subclass has been implemented, we are able to use polymorphism with respect to PrivateInfo. For example, all the methods to add private info, delete private info, and so on in PrivateInfoManager take a PrivateInfo object as a parameter. This way, we don’t need to differentiate between LogIn, ID, and so on, as long as we know it is a PrivateInfo object. 

### Individual Contributions

Hayk and Yousef worked to add new logins, and also implemented the subclasses of PrivateInfo that had not been implemented in Phase 0. They also implemented the functionality to add, edit, and delete entries in a vault. For several features of this project, Hayk (hayk-bvb) and Yousuf (yousufhassan) coded together using the IntelliJ CodeWithMe feature. As a result, several of Hayk's commits include Yousuf's contributions and several of Yousuf's commits include Hayk's contributions. Cliff and Ryan are working on the springTest branch to create the endpoints that connect the front end and the back end of the program. Kelian and Patricia worked together on data serialization. All the group members plan on refactoring the code to get rid of code smells and dependency issues. We also want to add more documentation and tests. 


