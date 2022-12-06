/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogIn;

import java.util.List;

/**
 *
 * @author MSI
 */
public class Config {

    private static final String FOLDER_PATH = "D:\\FPT\\Semester 3\\LAB\\J1.LP0016\\J1.LP0016\\data\\";
    private static final String CONFIG_FILE = FOLDER_PATH + "config.txt";
    private String accountFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = Tool.tool.readLinesFromFile(CONFIG_FILE);
        for (String x : lines) {
            x = x.toUpperCase();
            String[] parts = x.split(":");
            if (x.contains("ACCOUNT")) {
                accountFile = FOLDER_PATH + parts[1].trim().toLowerCase();
            }

        }
    }

    public String getAccounttFile() {
        return accountFile;
    }

}
