/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogIn;

import static Tool.tool.readLinesFromFile;
import static Tool.tool.readNonBlank;
import java.util.List;

/**
 *
 * @author MSI
 */
public class AccountManagement {
    private static final String FILE = "D:\\FPT\\Semester 3\\LAB\\0018\\data\\Account.txt";
    private static final String SEPARATOR = ",";
    private static final List<String> LS = readLinesFromFile(FILE);
    private static final AccountManagement instance = new AccountManagement();
    private final Account current;

    public static AccountManagement getInstance() {
        return instance;
    }

    public Account getCurrent() {
        return current;
    }
    
    public static List<String> getLS(){
        return LS;
    }
    
    private AccountManagement(){
        this.current = logIn();
    }
    
    private Account logIn(){
        System.out.println("---------------LOG IN ---------------");
        String ID = Tool.tool.readPattern("Enter your ID, format " + Account.getACCOUNT_ID_FORMAT_STRING(), Account.getACCOUNT_ID_FORMAT());
        String password = readNonBlank("Enter password: ");
        Account acc = new Account(ID, password);
        if(validAccount(acc)) {
            System.out.println("Logged in with " + acc.getRoleString() + " authority!");
            return acc;
        }
        return null;
    }
        
    public boolean validAccount(Account a){
        List<String> ls = AccountManagement.getLS();
        for(String x: ls){
            String parts[] = x.split(SEPARATOR);
            if(a.getID().equalsIgnoreCase(parts[0]) && a.getPassword().equals(parts[2])) return true;
        }
        return false;
    }
    
    
}
