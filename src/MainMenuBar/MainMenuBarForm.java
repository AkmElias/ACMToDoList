/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenuBar;

import javax.swing.JMenu;

/**
 *
 * @author Kazi Nayeem
 */
public class MainMenuBarForm extends javax.swing.JMenuBar {

    /**
     * Creates new menu bar MainMenuBarForm initialize file menu and tools menu
     */
    public MainMenuBarForm() {
        super();

        fileJMenu = new FileMenuForm();
        this.add(fileJMenu);

        toolsJMenu = new ToolsMenuForm();
        this.add(toolsJMenu);
    }

    private final JMenu fileJMenu;
    private final JMenu toolsJMenu;

}
