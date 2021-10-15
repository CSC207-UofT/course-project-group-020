package Entities;

import java.util.HashMap;

/**
 * An abstract class that is a parent to the following classes:
 * - Contact.java
 * - ID.java
 * - LogIn.java
 * - Note.java
 * <p>
 * It contains a Hashmap in which the keys are Strings representing instance attributes of the subclass that inherits
 * the HashMap, it then maps the keys to Strings values that are the actual instance attributes of the subclass.
 */
public abstract class PrivateInfo {

    public HashMap<String, String> info;

    /**
     * This is the basic constructor for PrivateInfo. It is solely responsible for instantiating an empty Hashmap.
     */
    public PrivateInfo() {

        info = new HashMap<>();
    }

    /**
     * This method, whatever subclass, it returns its appropriate information;
     * For example, for child class LogIn, it would return either username, password, webpage, or url.
     * Input, could be a string, 'username', 'password', etc. and based on that, using .get(), it will
     * return the appropriate value. Furthermore, if that child class does not have that attribute,
     * it wil return an error message.
     *
     * @param attribute A string input which indicates which attribute to get
     */

    public String GetInfo(String attribute) {

        if (info.containsKey(attribute)) {
            return info.get(attribute);
        } else {
            return ("This datatype does not have the " + attribute + " attribute");

        }
    }

    /**
     * This method, for whatever subclass, is responsible for changing the information of
     * the necessary attribute. Works with any subclass. It inputs the attribute to be changed,
     * and the new value to be changed to. If the subclass does not have the given attribute,
     * an error message is returned.
     *
     * @param attribute A string input to indicate which attribute to change
     * @param newValue A string input that is the new value that is to be changed to
     */
    public void ChangeInfo(String attribute, String newValue) {

        if (info.containsKey(attribute)) {
            info.put(attribute, newValue);

        } else {
            System.out.println("This datatype does not have the " + attribute + " attribute");
        }
    }

    /**
     * This method is responsible to print out the string version of the Hashmap stored in this.info
     * @return Returns the string representation of the Hashmap stored in this.info
     */
    @Override
    public String toString() {
        return this.info.toString();
    }

}







