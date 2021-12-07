package PrivateInfoObjects;

import Exceptions.AttributeNotFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * An abstract class that is a parent to the following classes:
 * - Contact.java
 * - Identification.java
 * - LogIn.java
 * - Note.java
 * <p>
 * It contains a Hashmap in which the keys are Strings representing instance attributes of the subclass that inherits
 * the HashMap, it then maps the keys to Strings values that are the actual instance attributes of the subclass.
 */
public abstract class PrivateInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public HashMap<String, String> info;
    private String id;
    private String type;


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
     * <p>
     * For example, for child class LogIn, it would return either username, password, webpage, or url.
     * Input, could be a string, 'username', 'password', etc. and based on that, using .get(), it will
     * return the appropriate value. Furthermore, if that child class does not have that attribute,
     * it wil return an error message.
     *
     * @param attribute A string input which indicates which attribute to get
     */

    public String getInfo(String attribute) throws AttributeNotFoundException {
        if (this.info.containsKey(attribute)) {
            return info.get(attribute);
        } else {
            throw new AttributeNotFoundException();
        }
    }

    /**
     * A getter method that is responsible for getting and returning the string representation of this PrivateInfo's
     * unique UUID.
     *
     * @return A string representation of this PrivateInfo's unique UUID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * This method is responsible to print out the string version of the Hashmap stored in this.info
     *
     * @return Returns the string representation of the Hashmap stored in this.info
     */
    @Override
    public String toString() {
        return this.info.toString();
    }

    /**
     * A setter method that sets the id attribute of this object
     *
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A getter method that gets type value
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * A setter method that sets the type attribute of this object
     *
     * @param id String
     */
    public void setType(String id) {
        this.type = id;
    }

    /**
     * A setter method that builds the HashMap of the PrivateInfo.
     *
     * @param key   The key, attribute, value of the PrivateInfo.
     * @param value The actual value of the attribute.
     */
    public void buildInfo(String key, String value) {
        this.info.put(key, value);
    }
}
