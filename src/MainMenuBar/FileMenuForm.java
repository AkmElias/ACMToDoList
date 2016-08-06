/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenuBar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Kazi Nayeem
 */
public class FileMenuForm extends JMenu {

    public FileMenuForm() {
        super("File");
        
        addTopicJMenuItem = new javax.swing.JMenuItem("Add Topic");
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exportJMenu = new javax.swing.JMenu("Export");
        exportAllJMenu = new javax.swing.JMenu("All");
        exportInqueueJMenuItem = new javax.swing.JMenuItem("In-queue");
        exportSolvedJMenuItem = new javax.swing.JMenuItem("Solved");
        unsolvedJMenuItem = new javax.swing.JMenuItem("Unsolved");
        exportMarkedJMenuItem = new javax.swing.JMenuItem("Marked");
        importJMenuItem = new javax.swing.JMenuItem("Import");
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem("Exit");

        add(addTopicJMenuItem);
        add(jSeparator1);

        exportAllJMenu.add(exportInqueueJMenuItem);
        exportAllJMenu.add(exportSolvedJMenuItem);
        exportAllJMenu.add(unsolvedJMenuItem);

        exportJMenu.add(exportAllJMenu);
        exportJMenu.add(exportMarkedJMenuItem);

        add(exportJMenu);

        add(importJMenuItem);
        add(jSeparator2);

        add(exitJMenuItem);
    }

    private final JMenuItem addTopicJMenuItem;
    private final JPopupMenu.Separator jSeparator1;
    private final JMenu exportJMenu;
    private final JMenu exportAllJMenu;
    private final JMenuItem exportInqueueJMenuItem;
    private final JMenuItem exportSolvedJMenuItem;
    private final JMenuItem unsolvedJMenuItem;
    private final JMenuItem exportMarkedJMenuItem;
    private final JMenuItem importJMenuItem;
    private final JPopupMenu.Separator jSeparator2;
    private final JMenuItem exitJMenuItem;
}
