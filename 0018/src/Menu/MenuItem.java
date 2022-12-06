/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author MSI
 */
    public enum MenuItem {
    EXIT("Exit", 0),
    ADD_NEW_CD("Add new CD", 2),
    SEARCH_CD("Search CD",0),
    DELETE_CD("Delete CD", 2),
    UPDATE_CD("Update CD", 2),
    SHOW_ALL_CD("Show all CD",0),
    SAVE_TO_FILE("Save to File", 1);
    private final int role;
    private final String label;

    public int getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, int role) {
        this.role = role;
        this.label = label;
    }
}
