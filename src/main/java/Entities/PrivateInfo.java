package Entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

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
public abstract class PrivateInfo implements Serializable {

    public HashMap<String, String> info;
    public String id;

    /**
     * This is the basic constructor for PrivateInfo. It is responsible for instantiating an empty Hashmap as well as
     * giving this instance of PrivateInfo a unique UUID..
     */
    public PrivateInfo() {
        this.id = UUID.randomUUID().toString();
        info = new HashMap<>();
    }

    /**
     * This method returns the value of the attribute.
     *
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
        }
        // TODO: Instead of returning these strings, raise a custom exception.
        // Do this for every place in the code that can raise an exception.
        else {
            return ("This datatype does not have the " + attribute + " attribute");

        }
    }

    /**
     * A getter method that is responsible for getting and returning the string representation of this PrivateInfo's
     * unique UUID.
     * @return A string representation of this PrivateInfo's unique UUID.
     */
    public String getId(){return this.id;}
    
    public String SetInfo(String attributeToChange, String newValue) {
        if (info.containsKey(attributeToChange)){
            info.put(attributeToChange, newValue);
        }
        // TODO: Same as above TODO.
        else{
            return "Error";
        }
        return null; // remove this once we fix the code
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





