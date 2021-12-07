package Exceptions;

public class AttributeNotFoundException extends Exception{
    public AttributeNotFoundException() {
        super("Attribute not found");
    }
}
