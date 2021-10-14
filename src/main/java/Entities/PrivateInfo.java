package Entities;

/**
 * TODO: Write Javadoc
 */
public abstract class PrivateInfo {

    /**
     * TODO: Write Javadoc
     */
    public PrivateInfo(){

    }

    /**
     * For whatever subclass, it returns its appropriate information;
     * For example, for child class Login, it would return either username or password,
     * the overridden method in Login, will give prompt to choose which one to Get.
     * Input, could be a string, 'username' or 'password' and based on that, it will return
     * the appropriate thing
     */

    public abstract String GetInfo(String attribute);

    public abstract void ChangeInfo(String attribute, String newValue);

}







