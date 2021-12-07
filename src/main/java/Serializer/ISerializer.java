package Serializer;

import Account.Account;

/**
 * This interface describes the necessary methods that are required for a Serializer class used for persisting data
 * between sessions
 */
public interface ISerializer {
    void serialize(Account account);

    Account deserialize(String username);
}
