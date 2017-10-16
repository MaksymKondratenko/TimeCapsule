package com.maksymkondratenko.timecapsule.model;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {
    public static UserValidator instance = new UserValidator();
    private static Map<String, String> users = new HashMap<>();
    static {
        users.put("maks", "maks");
        users.put("qwerty", "qwerty");
    }
    private UserValidator(){

    }

    public Map getUsers(){
        return users;
    }

    public boolean addUser(String username, String pass){
        if (!users.containsKey(username)) {
                users.put(username, pass);
                return true;
            } else {
            return false;
        }
    }

    public boolean passCheck(String pass, String rep){
        if(pass.equals(rep)){
            return true;
        } else {
            return false;
        }
    }

    public static UserValidator getInstance() {
        return instance;
    }
}
