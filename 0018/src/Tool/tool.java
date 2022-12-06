/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Entity.CD;
import Main.Management;

/**
 *
 * @author MSI
 */
public class tool {
    public static final double MAX_PRICE = 10000000;
    public static final double MIN_PRICE = 0;
        private static final String IGNORE_CASE_PATTERN = "(?i)";
    public static Scanner SC = new Scanner(System.in);
    
//validate
    
//    public static boolean validateString(String str, String regex, boolean ignoreCase) {
//        if (str != null && regex != null) {
//            if (ignoreCase) {
//                regex = tool.IGNORE_CASE_PATTERN + regex;
//            }
//            return str.matches(regex);
//        }
//        return false;
//    }
//        public static boolean validStr(String str, String pattern){
//        return str.toLowerCase().matches(pattern.toLowerCase());
//    }
    
        public static boolean validateString(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = tool.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }
    public static boolean validString(String str, String regEx){
        return str.matches(regEx);
    }
    
//        
    public static boolean validInt(int number, int minValue, int maxValue) {
        if (number >= minValue && number <= maxValue) {
            return true;
        }
        return false;
    }
    public static boolean validDouble(double number, double minValue, double maxValue) {
        if (number >= minValue && number <= maxValue) {
            return true;
        }
        return false;
    }
    
     private static String getThisYear(){
        Date date = new Date();
 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy");
        String format = simpleDateFormat.format(date);
        return format;
    }
    public static boolean validYear(String year){
        if(year.compareTo(getThisYear()) > 0)return false;
        return true;
    }
    public static boolean isBlank(String cs){
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
//Input    
     public static int inputInt(String message, int minValue, int maxValue) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                Scanner sc = new Scanner(System.in);
                number = Integer.parseInt(sc.nextLine());
                if (validInt(number, minValue, maxValue)) {
                    return number;
                } else {
                    System.out.println("Please Input An Integer From " + minValue + " to " + maxValue + "!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please Input An Integer From " + minValue + " to " + maxValue + "!");
            }
        }
    }
    
    public static boolean inputBoolean(String message) {
        String input;
        System.out.print(message + " [1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }
    
    public static String inputString(String message) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            inputString = sc.nextLine();
        } while (inputString.isEmpty());
        return inputString;
    }
    
        public static String inputString(String msg, String pattern){
        String input ="";
        boolean stay = false;
        do {   
            System.out.print(msg+": ");
            input = SC.nextLine();
            if(validString(input, pattern)) {stay = false;}
            else{
                stay = true;
            }
        } while (stay);
        return input;
    }
    
        public static String inputID(String message,String pattern) {
        String inputString;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            inputString = sc.nextLine();
        } while (inputString.isEmpty());
        return inputString;
    }
    
     public static double inputsPrice(String message,double minValue, double maxValue){
        double input;
        boolean valid;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print(message + ": ");
            sc = new Scanner(System.in);
            input = Double.parseDouble(sc.nextLine());
             valid = input <= MAX_PRICE && input >= MIN_PRICE;
            if(!valid) System.out.println("Price must be less than greater than " + minValue + " and less than " + maxValue + "!");
        }while (!valid);
        return input;
    }
     
     public static String chooseOneFromAList(String message, String[] list){
        String input;
        boolean valid = false;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print(message + ": ");
            sc = new Scanner(System.in);
            input = sc.nextLine().trim().toUpperCase();
            for(String x: list) if (x.toUpperCase().equals(input)){
                valid = true;
                break;
            }
            if(!valid) System.out.println("Sorry, try again!");
        }while(!valid);
        return input;
    }
     
//read     
     
       public static boolean readBool(String message){
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        sc = new Scanner(System.in);
        input = sc.nextLine().trim();
        if(input.isEmpty()) return false;
        char c = Character.toUpperCase(input.charAt(0));
        return c == '1' || c == 'T' || c == 'Y';
    }
     
    
    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<String>();
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String newLine;
            while ((newLine = bf.readLine()) != null) {
                newLine = newLine.trim();
                if (!newLine.equals("")) {
                    list.add(newLine);
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + ": ");
            SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            valid = validString(input, pattern);
        } while (!valid);
        return input;
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + ": ");
            SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("You've entered no literal text, please try again!");
            }
        } while (input.isEmpty());
        return input;
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && //Containing at least 1 Character
                str.matches(".*[\\d]+.*")
                && //Containing at least 1 Number
                str.matches(".*[\\W]+.*");        //Containing at least 1 special character
    }
    
     public static String toTitleCase(String line){
        String[] list = line.split(" ");
        for(int i = 0; i < list.length; i++)
            list[i] = toTitleCaseWord(list[i]);
        ArrayList<String> newList = new ArrayList();
        for (String x : list)
            if (!x.trim().equals(""))
                newList.add(x);
        return String.join(" ", newList);
    }
      public static String toTitleCaseWord(String line){
        if(line.length() <= 1) return line.toUpperCase();
        return line.substring(0,1).toUpperCase() + line.substring(1).toLowerCase();
    }
      
       public static boolean checkExistID(String id){
        if(id!=null && !isBlank(id)){
            CD obj = new CD();
            obj.setCDid(id);
            for(int i = 0; i <Management.getInstance().getList().size();i++){
                if(Management.getInstance().getList().get(i).getCDid().equalsIgnoreCase(id)){
                    return true;
                }
            }
        }
        return false;
    }
    
}
