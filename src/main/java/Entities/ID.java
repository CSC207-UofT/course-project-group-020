package Entities;

/**
 * This class represents an ID of a user.
 */
// Java warns that Class ID is never used, but we will not solve this style warning for the beta program.
public class ID extends PrivateInfo{

    /**
     * This is a constructor for ID that takes in three parameters and assigns them into its inherited HashMap from
     * PrivateInfo
     *
     * @param IDType A string that represents the type of the ID, e.g. "passport", "health card", etc.
     * @param IDNumber A string that represents the ID Number of the ID, e.g. "AK1793B78"
     * @param IDExpirationDate A string that represents the expiry date of the ID, e.g. "25/07/2025"
     */

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public ID(String IDType, String IDNumber, String IDExpirationDate){
        super();
        info.put("IDType", IDType);
        info.put("IDNumber", IDNumber);
        info.put("IDExpirationDate", IDExpirationDate);
        this.type = "ID";


    }


}
