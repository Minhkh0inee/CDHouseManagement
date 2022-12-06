/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import Tool.tool;
import static Tool.tool.readBool;
import static Tool.tool.readNonBlank;
import static Tool.tool.readPattern;
import static Tool.tool.toTitleCase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author MSI
 */
public class CDlist extends ArrayList<CD> {
   

    protected String file = "";
    protected boolean updated = false;
    public static final String CD_ID_FORMAT = "CD\\d{3}";
    private String filePath = "D:\\FPT\\Semester 3\\LAB\\0018\\data\\CD.txt";
//    
//    public CDlist(LogIn loginObj){
//        this.loginObj = loginObj;
//    }
//    
    public String getFilePath() {
        return filePath;
    }
    
    public final void setFilePath(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            this.filePath = filePath;
        }
    }
    
    public CDlist() {
    }
        
    
    public CDlist(String filePath){
       setFilePath(filePath);
    }
    
    public List<CD> filter(String id )
    {
        String cdId = id.toUpperCase();
        return stream().filter((cd -> cd.getCDid().toUpperCase().equals(cdId))).collect(Collectors.toList());
    }

    protected CD parseString(String stringObject) {
        CD obj = new CD();
        obj.parseString(stringObject);
        return obj;
    }
    
    public boolean load() {
        try ( Scanner sc = new Scanner(new File(this.filePath))) {
            CD obj;
            String data;
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                if (!data.isEmpty()) {
                    obj = parseString(data);
                    if (obj != null) {
                        add(obj);
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CDlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (CD e : this) {
                writer.append(e.toString());
                writer.append("\n");
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CDlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public int indexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < size(); i++) {
            if (((CD) o).getCDid().equals(get(i).getCDid())) {
                idx = i;
                break;
            }
        }
        return idx;
    }
    
    @Override
     public boolean add(CD e) {
        int idx = indexOf(e);
        if (idx >= 0) {
            remove(idx);
        }
       
        return super.add(e);
    }
     
     
      public void show() {
        for (CD e : this) {
            e.output();
        }
    }

    public void showFilter(String id) {
        List<CD> filterList = filter(id);
        for (CD e : filterList) {
            e.output();
        }
    }
    
    public void showFilter(Date date) {
        List<CD> filterList = filter(date.toString());
        for (CD e : filterList) {
            e.output();
        }
    }
    
    public int searchCDByID(String ID){
        for (CD x : this) {
            if (x.getCDid().equals(ID))
                return this.indexOf(x);
        }
        return -1;
    }
     
    public int searchCDByName(String name){
        for (CD x : this) {
            if (x.getCDname().equals(name))
                return this.indexOf(x);
        }
        return -1;
    }
    
   
     
     public void updateCDByParamater(String parameter){
        boolean answer;
        boolean stay = false;
        String[] options = {"id", "name", "type", "collection", "price"};
        do{
            String input = readNonBlank("Enter CD " + parameter + " to be updated");
            int pos = -1;
            if(parameter.equals("ID")) pos = searchCDByID(input.toUpperCase());
            
            if(pos < 0){
                System.out.println("No matching " + parameter + " found with " + input.trim());
                answer = readBool("Will you try again?");
                if(answer) stay = true;
            }else{
                System.out.println("Here is the full information of matching CD:");
                this.get(pos).output();
                answer = readBool("Are you sure to update this CD?");
                if(answer){
                   CD c = this.get(pos);
                   boolean cont;
                   do{
                       String choice = tool.chooseOneFromAList("What field do you want to update? Select one from \n"+ Arrays.toString(options), options);
                       switch(choice.toUpperCase()){
                            case "ID":
                                String newCDid;
                                do{
                                    newCDid = readPattern("New ID of the Doctor, format CD****", CD.ID_FORMAT);
                                    pos = searchCDByID(newCDid.toUpperCase());
                                    if(pos >= 0) System.out.println("ID: " + newCDid.toUpperCase() + " already exists!");
                                }while(pos >= 0);
                                c.setCDid(newCDid);
                                break;
                                
                            case "NAME":
                                String name = toTitleCase(readNonBlank("Update name of the CD to"));
                                c.setCDname(name);
                                break;
                                
                            case "TYPE":
                                String type = toTitleCase(readNonBlank("Update type of the CD to"));
                                c.setType(type);
                                break;
                                
                            case "COLLECTION":
                                String collection = toTitleCase(readNonBlank("Update collection of the CD to"));
                                c.setCDcollection(collection);
                                break;
                                
                            case "PRICE":
                                double newPrice = tool.inputsPrice("Update price of the CD to:",0,10000000);
                                c.setPrice(newPrice);
                                break;
                                
                            default:
                                System.out.println("No matching field!");
                       }
                       cont = readBool("Will you continue updating this CD of ID: " + c.getCDid());
                   }while(cont);
                }
                answer= readBool("Will you continue updating CD List?");
                if(answer) stay = true;
                else stay = false;
            }
        }while(stay);
        updated= true;
    } 
     
     public void removeCDByParameter(String parameter){
        boolean answer;
        boolean stay = false;
        do{
            String input = readNonBlank("Enter the " + parameter + " of the CD to be removed");
            int pos = -1;
            if(parameter.equals("ID")) pos = searchCDByID(input.toUpperCase());
            
            if(pos < 0){
                System.out.println("No matching " + parameter + " found!");
                answer = readBool("Will you try again?");
                if(answer) stay = true;
            }else{
                System.out.println("Here is the full information of matching CD:");
                this.get(pos).output();
                answer = readBool("Are you sure to remove this CD?");
                if(answer){
                    this.remove(pos);
                    System.out.println("CD removed!");
                    updated = true;
                }else System.out.println("Removal request declined!");
                answer = readBool("Will you continue your removal?");
                if(answer) stay = true;
            }
        }while(stay);
        updated= true;
    }
     

}



