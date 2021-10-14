package Entities;

import java.util.HashMap;

/**
 * TODO: Write Javadoc
 */
public abstract class PrivateInfo {

    public HashMap<String, String> info;

    /**
     * TODO: Write Javadoc
     */
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

}







