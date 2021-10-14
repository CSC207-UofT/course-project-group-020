package Entities;

import java.util.HashMap;

/**
 * An abstract class that is a parent to the following classes:
 *      - Contact.java
 *      - ID.java
 *      - LogIn.java
 *      - Note.java
 *
 * It contains a Hashmap in which the keys are Strings representing instance attributes of the subclass that inherits
 * the HashMap and maps it to values that are Strings representing
 *
 */
public abstract class PrivateInfo {

    public HashMap<String, String> info;


    public PrivateInfo(){

        info = new HashMap<>();
    }

    /**
     * For whatever subclass, it returns its appropriate information;
     * For example, for child class LogIn, it would return either username or password,
     * the overridden method in LogIn, will give prompt to choose which one to Get.
     * Input, could be a string, 'username' or 'password' and based on that, it will return
     * the appropriate thing
     */

    public String GetInfo(String attribute) {

        if (info.containsKey(attribute)){
            return info.get(attribute);
        }
        else {
            return("This datatype does not have " + attribute + " attribute");

            // RAISE AN ERROR HERE
        }
    }

    public boolean ChangeInfo(String attribute, String newValue){

        if (info.containsKey(attribute)){
            info.put(attribute, newValue);
            return true;

        } else {

            System.out.println("This datatype does not have the " + attribute + "attribute");
            return false;
        }
    }

    @Override
    public String toString(){
        return this.info.toString();
    }

}







