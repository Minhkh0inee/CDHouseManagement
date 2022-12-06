/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogIn;

/**
 *
 * @author MSI
 */
public class Account {
     private static final String ACCOUNT_ID_FORMAT_STRING = "AC***";
    private static final String ACCOUNT_ID_FORMAT = "AC\\d{3}";
    private static final String[] ROLES = {"ACC-1", "ACC-2", "Admin"};
    private static final String SEPARATOR = ",";
    
    private static final int PASSWORD_MIN_LENGTH = 8;
    private String ID;
    private String role;
    private String password;

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleString() {
        for (String x: AccountManagement.getInstance().getLS())
            if(x.contains(ID)){
                String[] parts = x.split(SEPARATOR);
                return parts[1];
            }
        return "No Matching Role";
    }
    
    public int getRole(){
        switch(getRoleString().trim().toUpperCase()){
            case "ACC-1": return 0;
            case "ACC-2": return 1;
            case "BOSS": return 2;
            default: return -1;
        }
    }
    
    public static String getACCOUNT_ID_FORMAT_STRING() {
        return ACCOUNT_ID_FORMAT_STRING;
    }
    
    public static String getACCOUNT_ID_FORMAT() {
        return ACCOUNT_ID_FORMAT;
    }
    
    public Account(){
        
    }
    
    public Account(String ID, String password){
        this.ID = ID;
        this.password = password;
    }
}
