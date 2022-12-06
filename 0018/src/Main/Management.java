/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import LogIn.AccountManagement;
import Entity.CDlist;
import Menu.Menu;

/**
 *
 * @author asout
 */
public class Management {
    private static final Management instance = new Management(); 
    private final CDlist ls;
    
    private Management(){
        ls = new CDlist();
    }
    
    public static Management getInstance(){
        return instance;
    }
    
    public CDlist getList(){
        return ls;
    }
    
    private void init(){
        ls.load();
    }
    
    public static void main(String[] args) {
        if(AccountManagement.getInstance().getCurrent() != null){
            Management main = Management.getInstance();
            main.init();
            Menu menu = new Menu();
            menu.mainMenu();
        }else{
            System.out.println("Login Failed!");
        }
    }
}
