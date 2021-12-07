package Spring;

/**
 * Resource representation class that takes in JSON objects from the frontend to be processed. Used when user
 * wants to generate a password.
 */
public class GeneratePassForm {
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
