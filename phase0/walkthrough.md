## Scenario Walkthrough for Password Manager Program

The scenario walkthrough is represented in the form of a decision tree. Any path that is denoted as [beta] is not 
possible for our skeleton program. Furthermore, any feature that a user tries to call that is not part of the skeleton program will just return
print a message stating that the feature is not implemented in the beta.

<br/> 
Decision tree for scenario walkthrough of skeleton program:

<ol>
<li>Create new Password Manager account</li>
    <ol>
        <li>LogIns</li>
            <ol>
                <li>Add Login</li>
                <li>Edit Login [beta]</li>
                <li>Delete Login [beta]</li>
            </ol>
        <li>Notes [beta]</li>
        <li>Contacts [beta]</li>
        <li>IDs [beta]</li>
        <li>Display vault</li>
        <li>No thanks, I would like to log out</li>
    </ol>

<br/>
<li>Log in to an existing account [beta]</li>
</ol>

<br/>
Below is an example scenario walkthrough you can take so that you can check all parts of our skeleton
program.
<ul>
<li>First, create a new Password Manager account (type “1”)</li>
    <ul><li>Enter the account credentials, as prompted in the console.</li></ul>

<li>Next, display the vault associated with your account (type “5”)</li>
    <ul><li>This should print an empty ArrayList since you have not added any items to the vault
yet!</li></ul>
<li>Next, go into the LogIns submenu (type “1”)</li>
<li>Next, add a LogIn (type “1”)</li>
    <ul><li>Enter the LogIn information, as prompted in the console.</li></ul>
<li>Next, add another LogIn (Type “1”)</li>
    <ul><li>Again, enter the LogIn information, as prompted in the console.</li></ul>
<li>Next, display the vault again (type “5”)</li>
    <ul><li>This time, you should see an ArrayList with two HashMaps that contain the LogIns you
created.</li></ul>
<li>Finally, log out from the program (type “6”)</li>
    <ul><li>This is the end of the scenario walkthrough!</li></ul>
</ul>
