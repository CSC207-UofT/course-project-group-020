package Entities;

// Java warns that Class Contact is never used, but we will not solve this style warning for the beta program.
public class Contact extends PrivateInfo {

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public Contact(String name, String number, String address) {

        super();
        info.put("name", name);
        info.put("number", number);
        info.put("address", address);
    }
}