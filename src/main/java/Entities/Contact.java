package Entities;

public class Contact extends PrivateInfo {

    public Contact(String name, String number, String address){

        super();
        info.put("name", name);
        info.put("number", number);
        info.put("address", address);
    }

//    @Override
//    public String GetInfo(String attribute) {
//        return null;
//    }
//
//    @Override
//    public boolean ChangeInfo(String attribute, String newValue) {
//        return true;
    }