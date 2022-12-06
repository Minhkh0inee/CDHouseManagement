/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import LogIn.Account;
import LogIn.AccountManagement;
import Main.Management;
import static Tool.tool.inputInt;
import Entity.CD;
import Tool.tool;
import static Tool.tool.SC;
import static Tool.tool.readPattern;
import java.util.Arrays;

/**
 *
 * @author MSI
 */
public class Menu {
     private final Account current;
     public static final String[] ATTRIBUTES=  {"name","price", "publishing year"};
    private final Main.Management instance;
    
    public Menu(){
        current = AccountManagement.getInstance().getCurrent();
        instance = Management.getInstance();
    }
    
    private final MenuItem[] productOptions = {
        MenuItem.ADD_NEW_CD,
        MenuItem.SEARCH_CD,
        MenuItem.DELETE_CD,
        MenuItem.UPDATE_CD,
        MenuItem.SHOW_ALL_CD,
        MenuItem.SAVE_TO_FILE,
        MenuItem.EXIT
    };
    
    public MenuItem[] option(){
        MenuItem[] option = new MenuItem[7];
        int count = 0;
        for(MenuItem x: productOptions){
            if(current.getRole() >= x.getRole()){
                option[count] = x;
                count++;
            }
        }
        return option;
    }
    
    public int printOptions(){
        int count = 0;
        for(MenuItem x: option()){
            if(x!= null){
                System.out.println((count + 1) + ". " + x.getLabel());
                count++;
            }
        }
        return count - 1;
    }
    
    public void mainMenu(){
        int choice;
        int limit;
        do{
            limit = printOptions();
            choice = inputInt("Enter your choice: ", 1, limit + 1);
            switch(option()[choice - 1]){
                case ADD_NEW_CD:
                    System.out.println("---------------------ADDING NEW CD---------------------");
                    addNewCD();
                    System.out.println();
                    break;
                    
                 case SEARCH_CD:
                    System.out.println("---------------------SEARCHING CD---------------------");
                    searchCD();
                    System.out.println();
                    break;
                    
                case DELETE_CD:
                    System.out.println("---------------------DELETING CD---------------------");
                    deleteCD();
                    System.out.println();
                    break;
                    
                case UPDATE_CD:
                    System.out.println("---------------------UPDATING CD---------------------");
                    updateCD();
                    System.out.println();
                    break;
                case SHOW_ALL_CD:
                    System.out.println("---------------------LIST OF CD---------------------");
                    showAllCD();
                    System.out.println();
                    break;    

                case SAVE_TO_FILE:
                    System.out.println("---------------------SAVING SUCCESSFULLY---------------------");
                    saveToFile();
                    System.out.println();
                    break;
                        
                case EXIT:
                    System.exit(0);
                    System.out.println("------------------OUT OF PROGRAM---------------------");
                        break;
            }
        }while(choice >= 1 && choice <= limit);
       
    }
    
    public void addNewCD(){
        if(current.getRole() >= MenuItem.ADD_NEW_CD.getRole()){
            if(instance.getList().size() > 6){
                System.out.println("Can't add anymore CD! Out of storage");
                return;
            }else{
            CD x = new CD();
            x.input();
            instance.getList().add(x);
            System.out.println("Added successfully! Remember to 'Save to file before exit out of program!'");
            }
        }
    }
    
    public void deleteCD(){
        if(current.getRole() >= MenuItem.DELETE_CD.getRole()){
            int pos;
            String ID;
            do{
                ID = tool.inputString("Enter Product ID to be updated: ", CD.ID_PATTERN);
                pos = instance.getList().searchCDByID(ID);
                if(pos < 0){
                    System.out.println("No CD found!");
                    if(tool.readBool("Would you like to view full list of CDs?")) instance.getList().show();
                    System.out.println("Now try again.");
                }
            }while(pos < 0);
            System.out.println("Here's the full information of CD " + ID);
            System.out.println(instance.getList().get(pos).toString());
            if(tool.readBool("Are you sure to delete this CD?")) {
                instance.getList().remove(pos);
                System.out.println("CD " + ID + " has been removed!");
            }
            //getUpdated(true);
        }
    }
    
    public void updateCD(){
        if(current.getRole() >= MenuItem.UPDATE_CD.getRole()){
            int pos;
            String ID;
            String choice;
            String type;
            do{
                ID = tool.inputString("Enter CD ID to be updated: ", CD.ID_PATTERN);
                pos = instance.getList().searchCDByID(ID);
                if(pos < 0){
                    System.out.println("No CD found!");
                    if(tool.readBool("Would you like to view full list of CD?")) instance.getList().show();
                    System.out.println("Now try again.");
                }
            }while(pos < 0);
            System.out.println("Here's the full information of CD " + ID);
            System.out.println(instance.getList().get(pos).toString());
            if(tool.readBool("Are you sure to update this CD?")){
                do{
                    System.out.println("Choose parameter to update " +Arrays.toString(ATTRIBUTES) );
                    choice = SC.nextLine().trim().toUpperCase();
                    switch(choice){
                        case "NAME":
                            instance.getList().get(pos).setCDname(tool.inputString("Enter new CD's name: "));
                            break;

                        case "UNITPRICE":
                            instance.getList().get(pos).setPrice(tool.inputsPrice("Enter new CD's unit price: ", 0,10000000));
                            break;

                        case "PUBLISHINGYEAR":
                            instance.getList().get(pos).setPublishingYear(tool.inputString("Enter new publishing year: "));
                            break;

                        default:
                            System.out.println("No matching option!");        
                            break;
                    }
                }while(!tool.readBool("Do you want to keep updating this CD?"));
            }
            if(tool.readBool("Do you want to keep updating CD?")) updateCD();
            //getUpdated(true);
        }
    }
    
    public void searchCD(){
        String cdID = readPattern("Nhap ma CD de tim kiem, format of CD***",CD.ID_PATTERN);
        int pos = instance.getList().searchCDByID(cdID);
        if(pos < 0)System.out.println("KHONG TIM THAY");
        else System.out.println(instance.getList().get(pos));
    }
    
    public void showAllCD(){
        if(current.getRole() >= MenuItem.SHOW_ALL_CD.getRole()){
            instance.getList().show();
        }
    }
    
    public void saveToFile(){
        if(current.getRole() >= MenuItem.SAVE_TO_FILE.getRole()){
            instance.getList().save();
        }
    }
}
