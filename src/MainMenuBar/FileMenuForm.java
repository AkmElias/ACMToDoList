/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenuBar;

import acmtodolist.AcmToDoList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import problemdetails.TopicClass;
import problemdetails.TopicDetail;
import exportimportframe.ExportMainFrame;
import exportimportframe.ImportMainFrame;

/**
 * create File menu and it's menu items.
 *
 * @author Kazi Nayeem
 */
public class FileMenuForm extends JMenu {

    /**
     * Creates new menu FileMenuBar initialize file menu bar
     */
    public FileMenuForm() {
        super("File");

        initAddTopicJMenuItem();

        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        initExportJMenu();

        initImportJMenuItem();

        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        initExitJMenuItem();

        add(addTopicJMenuItem);
        add(jSeparator1);

        add(exportJMenuItem);

        add(importJMenuItem);
        add(jSeparator2);

        add(exitJMenuItem);
    }

    private JMenuItem addTopicJMenuItem;
    private final JPopupMenu.Separator jSeparator1;
    private JMenuItem exportJMenuItem;
    private JMenuItem importJMenuItem;
    private final JPopupMenu.Separator jSeparator2;
    private JMenuItem exitJMenuItem;
    private static ExportMainFrame exportMainFrame;

    /**
     * add topic menu initialize add action listener to add topic
     */
    private void initAddTopicJMenuItem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        addTopicJMenuItem = new javax.swing.JMenuItem("Add Topic");

        addTopicJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                String topicName = JOptionPane.showInputDialog(AcmToDoList.main_Window, "Input Topic Name");
                if (topicName != null) {
                    if (TopicDetail.addTopic(topicName) != TopicClass.SUCCESSFULLY_ADDED) {
                        JOptionPane.showMessageDialog(AcmToDoList.main_Window, "Error", "Cannot Add", JOptionPane.ERROR_MESSAGE);

                    } else {
                        AcmToDoList.main_Window.refreshTopicList();
                    }
                }
            }
        });
    }

    /**
     * export menu initialize add action listener to export menu
     */
    private void initExportJMenu() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        exportJMenuItem = new JMenuItem("Export");

        exportJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //ExportMainFrame.main(null);
                    /* Set the Nimbus look and feel */
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
                 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                 */
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Windows".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(ExportMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(ExportMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(ExportMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(ExportMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        exportMainFrame = new ExportMainFrame();
                        exportMainFrame.setVisible(true);
                    }
                });
            }
        });

    }

    /**
     * import menu initialize add action listener to import menu
     */
    private void initImportJMenuItem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        importJMenuItem = new javax.swing.JMenuItem("Import");

        importJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                ImportMainFrame.main(null);

            }
        });
    }

    /**
     * exit menu initialize add action listener to exit menu
     */
    private void initExitJMenuItem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        exitJMenuItem = new javax.swing.JMenuItem("Exit");

        exitJMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.exit(0);
            }
        });
    }

    public static void refreshExportProblemList() {
        if (exportMainFrame == null) {
            return;
        }
        if (!exportMainFrame.isVisible()) {
            return;
        }

        exportMainFrame.refeshExportTable();
    }
}
