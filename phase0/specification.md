# Specification

The password manager is a multi-user application that securely stores and retrieves sensitive data for its accounts. The user can create new accounts, sign in to an account, and sign out of an account. To create a new account, the user must set a username, password, and key. The password is salted and hashed (one-way encrypted) as a security measure. Each account can store entries of login information, notes, contacts, and/or identification information. The entries in each account are private to the other accounts, and are also only accessible to the user when they are signed in. Each entry is encrypted using Blowfish, a symmetric block cipher. The encrypted entry is stored in a text file located at a specified file path given by the user. 

When the user is creating a new login entry, the application should rate the strength of the password and should also give the user the option to generate a strong password.

The user can access the entries they have stored through two methods. They can navigate the main menu of the application, which displays all the entries of the data that the manager is currently storing. The user can also use a search function that finds the data entry directly. The entry is accessed by decrypting the ciphertext from the text file using the key, and displayed for the user. An exception to this is when the user is accessing login information. In that case, the password is displayed in asterisks, but can be copied to the user’s clipboard. 

The user should also be able to edit and delete entries as needed. 
