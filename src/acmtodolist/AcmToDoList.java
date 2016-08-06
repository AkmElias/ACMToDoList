/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmtodolist;

import java.util.ArrayList;
import problemdetails.JudgeDetail;
import problemdetails.ProblemDemo;
import problemdetails.SimpleProblem;
import problemdetails.TopicDetail;

/**
 *
 * @author Kazi Nayeem
 */
public class AcmToDoList {

    public static ArrayList<SimpleProblem> All_Problem_List = new ArrayList<>();
    public static ArrayList<ProblemDemo> Export_Problem = new ArrayList<>();
    public static MainFrameToDoList main_Window;
    public static String lastSelectedTopic = TopicDetail.ALL_PROBLEM_Topic;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //startup.StartUpMain.Start();
        AcmToDoList.All_Problem_List = TopicDetail.getAllProblem(TopicDetail.ALL_PROBLEM_Topic);

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
            java.util.logging.Logger.getLogger(MainFrameToDoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameToDoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameToDoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameToDoList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                main_Window = new MainFrameToDoList();
                main_Window.setVisible(true);
            }
        });
        //JudgeDetail.addJudge("POJ");
        for (int i = 0; i < JudgeDetail.getNumberOfJudge(); i++) {
            System.out.println(JudgeDetail.getJudgeName(i));
        }
    }

    /**
     * *
     * get all problem to All_Problem_List of specific topicName Update Problem
     * List
     *
     * @param topicName
     */
    public static void changeSelectedTopic(String topicName) {
        //System.err.println(topicName);
        All_Problem_List = TopicDetail.getAllProblem(topicName);
        lastSelectedTopic = topicName;
        main_Window.refreshProblemJTable(topicName);
    }

    /**
     * update problems list for problem that updated
     *
     * @param problem
     */
    public static void problemDetailsUpdated(SimpleProblem problem) {
        boolean flag = false;
        for (int i = 0; i < All_Problem_List.size(); i++) {
            if (All_Problem_List.get(i).isSameProblem(problem)) {
                All_Problem_List.set(i, problem);
                flag = true;
            }
        }
        if (flag) {
            main_Window.refreshProblemJTable(problem);
        }
    }
}
