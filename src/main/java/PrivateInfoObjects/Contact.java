package PrivateInfoObjects;

/**
 * This class is responsible for representing a contact of the user.
 */


public class Contact extends PrivateInfo {
    private static final long serialVersionUID = 1L;

    /**
     * This is the constructor for Contact. It has four parameters which are necessary to construct an instance of
     * Contact.
     *
     * @param name    A string for the name of the contact ("Erling Haaland")
     * @param number  A string representation for the number of the contact, e.g ("6471234567")
     * @param address A string representation for the address of the contact, e.g. ("3170-25 Bloor Street West")
     */

    public Contact(String name, String number, String address) {

        super();
        this.buildInfo("name", name);
        this.buildInfo("number", number);
        this.buildInfo("address", address);
        this.setType("Contact");
    }

}