package PrivateInfoObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Identification of a user.
 */
// Java warns that Class Identification is never used, but we will not solve this style warning for the beta program.
public class Identification extends PrivateInfo{
    private static final long serialVersionUID = 1L;

    /**
     * This is a constructor for Identification that takes in three parameters and assigns them into its inherited HashMap from
     * PrivateInfo
     *
     * @param IdType A string that represents the type of the Identification, e.g. "passport", "health card", etc.
     * @param IdNumber A string that represents the Identification Number of the Identification, e.g. "AK1793B78"
     * @param IdExpirationDate A string that represents the expiry date of the Identification, e.g. "25/07/2025"
     */

    // Java warns that Class constructor is never used, but we will not solve this style warning for the beta program.
    public Identification(String IdType, String IdNumber, String IdExpirationDate){
        super();
        info.put("IdType", IdType);
        info.put("IdNumber", IdNumber);
        info.put("IdExpirationDate", IdExpirationDate);
        this.type = "Identification";
    }


}
