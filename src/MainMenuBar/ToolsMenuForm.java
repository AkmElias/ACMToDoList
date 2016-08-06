/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Kazi Nayeem
 */
public class ToolsMenuForm extends JMenu {

    public ToolsMenuForm() {
        super("Tools");
        initSettingJMenuItem();
        initAboutJMenuItem();

    }

    private JMenuItem settingJMenuItem;
    private JMenuItem aboutJMenuItem;

    private void initSettingJMenuItem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        settingJMenuItem = new javax.swing.JMenuItem("Setting");
        settingJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                SettingFrame.MainSettingJFrame.main(null);
            }
        });
        add(settingJMenuItem);
    }

    private void initAboutJMenuItem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        aboutJMenuItem = new javax.swing.JMenuItem("About..");
        
        aboutJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                JOptionPane.showMessageDialog(acmtodolist.AcmToDoList.main_Window, "Build by Kazi Nayeem", "Credits and About", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        add(aboutJMenuItem);
    }
}
