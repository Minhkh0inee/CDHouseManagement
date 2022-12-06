/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import static Entity.CDlist.CD_ID_FORMAT;
import Tool.tool;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mkhoi
 */
public class CD {

    public static final String ID_FORMAT = "CD***";
    public static final String ID_PATTERN = "CD\\d{3}";
    public static final String COLLECTION_1 = "Game";
    public static final String COLLECTION_2 = "Movie";
    public static final String COLLECTION_3 = "Music";
    public static final String TYPE_1 = "Audio";
    public static final String TYPE_2 = "Video";
    public static String SEPARATOR = ",";
    private static final int ATTRIBUTE_COUNT = 6;

    private String CDid;
    private String CDname;
    private String CDcollection;
    private String type;
    private double price;
    private String publishingYear;

    public CD() {
    }

    public CD(String CDid, String CDname, String CDcollection, String type, double price, String publishingYear) {
        setCDid(CDid);
        setCDname(CDname);
        setCDcollection(CDcollection);
        setType(type);
        setPrice(price);
        setPublishingYear(publishingYear);
    }

    public String getCDid() {
        return CDid;
    }

    public final void setCDid(String CDid) {
        if (tool.validString(CDid, ID_PATTERN)) {
            this.CDid = CDid;
        }
    }

    public String getCDname() {
        return CDname;
    }

    public final void setCDname(String CDname) {
        this.CDname = CDname;
    }

    public String getCDcollection() {

        return CDcollection;
    }

    public final void setCDcollection(String CDcollection) {
        if (tool.validString(CDcollection, COLLECTION_1) || tool.validString(CDcollection, COLLECTION_2)
                || tool.validString(CDcollection, COLLECTION_3)) {
            this.CDcollection = CDcollection;
        }
    }

    public String getType() {
        return type;
    }

    public final void setType(String type) {
        if (tool.validString(type, TYPE_1) || tool.validString(type, TYPE_2)) {
            this.type = type;
        }

    }

    public double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        if (tool.validDouble(price, 0, 1000000)) {
            this.price = price;
        }
    }

    public String getPublishingYear() {
        return publishingYear;
    }

    public final void setPublishingYear(String publishingYear) {
        if (tool.validYear(publishingYear)) {
            this.publishingYear = publishingYear;
        }
    }

    @Override
    public String toString() {
        return CDid + "," + CDname + "," + CDcollection + "," + type + "," + price + "," + publishingYear;
    }

    public void input() {
        System.out.println();
        inputCDID();
        this.CDname = tool.inputString("Enter name of CD");
        inputCDColection();
        inputCDType();
        this.price = tool.inputsPrice("Enter the  price of CD", 0, 10000000);
        this.publishingYear = tool.inputString("Enter publish year");

    }

    protected String getIdFormatString() {
        return ID_FORMAT;
    }

    protected String getIdPattern() {
        return ID_PATTERN;
    }

    protected int getAttributeCount() {
        return CD.ATTRIBUTE_COUNT;
    }

    private String inputId() {
        String inputId;
        String idFormat = getIdFormatString();
        String regex = getIdPattern();

        do {
            inputId = tool.inputString("Please enter the id with the pattern(" + idFormat + ")");
        } while (!tool.validateString(inputId, regex, true));

        return inputId;
    }

    private void inputCDID() {
        boolean isExists;
        do {
            this.setCDid(tool.inputString("Input ID of CD (CD***): ", CD_ID_FORMAT));
            isExists = tool.checkExistID(this.CDid);
            if (isExists) {
                System.out.println("This ID is already exist!Please try again");
            }
        } while (isExists);
    }

    private void inputCDColection() {
        System.out.println("Please select CD Collection");
        System.out.println("1-game");
        System.out.println("2-movie");
        System.out.println("3-music");
        int choice = tool.inputInt("Select your choice: ", 1, 3);
        switch (choice) {
            case 1:
                this.CDcollection = CD.COLLECTION_1;
                break;
            case 2:
                this.CDcollection = CD.COLLECTION_2;
                break;
            case 3:
                this.CDcollection = CD.COLLECTION_3;
                break;
        }
    }

    private void inputCDType() {
        System.out.println("Please select CD type");
        System.out.println("1-Audio");
        System.out.println("2-Video");
        int choice = tool.inputInt("Select your choice: ", 1, 2);
        switch (choice) {
            case 1:
                this.type = CD.TYPE_1;
                break;
            case 2:
                this.type = CD.TYPE_2;
                break;

        }
    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(SEPARATOR));
        }
        return 0;
    }

    public int setAttribute(String attributes[]) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setCDid(attributes[idx++].trim());
            setCDname(attributes[idx++].trim());
            setCDcollection(attributes[idx++].trim());
            setType(attributes[idx++].trim());
            setPrice(Double.parseDouble(attributes[idx++].trim()));
            setPublishingYear(attributes[idx++].trim());

        }
        return idx;
    }

    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" {");
        sb.append(toString());
        sb.append("}");
        System.out.println(sb.toString());
    }

    public static Comparator compareByName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            CD cd1 = (CD) o1;
            CD cd2 = (CD) o2;
            return cd1.getCDname().compareTo(cd2.getCDname());
        }
    };
}
