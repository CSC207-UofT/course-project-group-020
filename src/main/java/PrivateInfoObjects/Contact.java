package PrivateInfoObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for representing a contact of the user.
 *
 */

// Java warns that Class Contact is never used, but we will not solve this style warning for the beta program.
public class Contact extends PrivateInfo {

    /**
     * This is the constructor for Contact. It has four parameters which are necessary to construct an instance of
     * Contact.
     * @param name A string for the name of the contact ("Erling Haaland")
     * @param number A string representation for the number of the contact, e.g ("6471234567")
     * @param address A string representation for the address of the contact, e.g. ("3170-25 Bloor Street West")
     */

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public Contact(String name, String number, String address) {

        super();
        info.put("name", name);
        info.put("number", number);
        info.put("address", address);
        this.type = "Contact";
    }

    public Contact decryptInfoType(String key){
        List<String> decrypted = new ArrayList<>();
        decrypted.add(decryptInfo(key, "name"));
        decrypted.add(decryptInfo(key, "number"));
        decrypted.add(decryptInfo(key, "address"));
        return new Contact(decrypted.get(0), decrypted.get(1), decrypted.get(2));
    }
}