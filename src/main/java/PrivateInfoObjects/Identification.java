package PrivateInfoObjects;

/**
 * This class represents an Identification of a user.
 */

public class Identification extends PrivateInfo {
    private static final long serialVersionUID = 1L;

    /**
     * This is a constructor for Identification that takes in three parameters and assigns them into its inherited HashMap from
     * PrivateInfo
     *
     * @param IdType           A string that represents the type of the Identification, e.g. "passport", "health card", etc.
     * @param IdNumber         A string that represents the Identification Number of the Identification, e.g. "AK1793B78"
     * @param IdExpirationDate A string that represents the expiry date of the Identification, e.g. "25/07/2025"
     */


    public Identification(String IdType, String IdNumber, String IdExpirationDate) {
        super();
        this.buildInfo("IDType", IdType);
        this.buildInfo("IDNumber", IdNumber);
        this.buildInfo("IDExpirationDate", IdExpirationDate);
        this.setType("ID");
    }


}
