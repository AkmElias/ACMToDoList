/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmtodolist;

import MainMenuBar.FileMenuForm;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import problemdetails.ProblemDemo;
import problemdetails.SimpleProblem;
import problemdetails.TopicDetail;

/**
 *
 * @author Kazi Nayeem
 */
public final class ProblemsJPanelForm extends javax.swing.JPanel {

    static Object[][] problemInfoJTable;

    /**
     * Creates new form ProblemsJPanelForm. initialize all components in this
     * panel
     */
    public ProblemsJPanelForm() {
        super();
        initComponents();
        lastTopicName = TopicDetail.ALL_PROBLEM_Topic;
        initRadioButtons();

        refreshProblemJTable();
        ProblemJTable.setAutoCreateRowSorter(true);
    }

    /**
     * initialize radiobutton its actoin listener
     */
    private void initRadioButtons() {
        RadioButtonHandler handler = new RadioButtonHandler();
        allProblemJRadioButton.addItemListener(handler);
        solvedProblemJRadioButton.addItemListener(handler);
        unsolvedProblemJRadioButton.addItemListener(handler);
        inqueueProblemJRadioButton.addItemListener(handler);

        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(allProblemJRadioButton);
        radioButtonGroup.add(solvedProblemJRadioButton);
        radioButtonGroup.add(unsolvedProblemJRadioButton);
        radioButtonGroup.add(inqueueProblemJRadioButton);

        lastSelectJRadioButton = allProblemJRadioButton;
        allProblemJRadioButton.setSelected(true);

    }

    /**
     * update topic name and update problem list for this topic name
     *
     * @param topicName topic name
     */
    void refreshProblemJTable(String topicName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        topicJLabel.setText(topicName);
        lastTopicName = topicName;
        refreshProblemJTable();
    }

    /**
     * update table for specific problem
     *
     * @param problem problem that updated
     */
    void refreshProblemJTable(SimpleProblem problem) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DefaultTableModel table = (DefaultTableModel) ProblemJTable.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            ProblemDemo last = new ProblemDemo(table.getValueAt(i, 0).toString(), table.getValueAt(i, 1).toString());
            if (last.isSameProblem(problem)) {
                table.setValueAt(problem.getProblemName(), i, 2);
                table.setValueAt(problem.getDifficultyLevel(), i, 3);
            }
        }
    }

    /**
     * problem type radio button handler. it implements itemlistener for radio
     * button
     */
    private class RadioButtonHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //refreshProblemJTable();
            //System.err.println(ie.getSource().toString());
            if (ie.getSource().equals(lastSelectJRadioButton)) {
                //System.err.println("all");
                return;
            }
            lastSelectJRadioButton = ie.getSource();
            refreshProblemJTable();
        }

    }

    /**
     * update problem list
     */
    void refreshProblemJTable() {
        problemInfoJTable = getCurrentProblemsToShow();

        tableItemsSet(problemInfoJTable);
    }

    /**
     * get problem list and its details that to show in problem table
     *
     * @return get table contents
     */
    private Object[][] getCurrentProblemsToShow() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Integer> curProb = new ArrayList<>();

        if (allProblemJRadioButton.isSelected()) {
            curProb = getAllProblem();
        } else if (solvedProblemJRadioButton.isSelected()) {
            curProb = getSolvedProblem();
        } else if (unsolvedProblemJRadioButton.isSelected()) {
            curProb = getUnsolvedProblem();
        } else if (inqueueProblemJRadioButton.isSelected()) {
            curProb = getInQueueProblem();
        }

        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;
        Object[][] probDetail = new Object[curProb.size()][];
        for (int i = 0; i < curProb.size(); i++) {
            probDetail[i] = allProb.get(curProb.get(i)).getTableModel();
        }
        return probDetail;
    }

    /**
     * get all problem index list
     *
     * @return all problem index list
     */
    private ArrayList<Integer> getAllProblem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Integer> prob = new ArrayList<>();
        for (int i = 0; i < AcmToDoList.All_Problem_List.size(); i++) {
            prob.add(i);
        }
        return prob;
    }

    /**
     * in-queue problem index list
     *
     * @return in-queue problem index list
     */
    private ArrayList<Integer> getInQueueProblem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Integer> prob = new ArrayList<>();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;
        for (int i = 0; i < allProb.size(); i++) {
            SimpleProblem now = allProb.get(i);
            if (now.isInQueue() && (!now.isSolved())) {
                prob.add(i);
                for (int j = prob.size() - 2; j >= 0; j--) {

                    int jpos = allProb.get(prob.get(j)).queuePos();
                    int jpos1 = allProb.get(prob.get(j + 1)).queuePos();

                    if (jpos > jpos1) {
                        Integer tem = prob.get(j);
                        prob.set(j, prob.get(j + 1));
                        prob.set(j + 1, tem);
                    }
                }
            }
        }
        return prob;
    }

    /**
     * all solved problem index list
     *
     * @return all solved problem index list
     */
    private ArrayList<Integer> getSolvedProblem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Integer> prob = new ArrayList<>();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;
        for (int i = 0; i < allProb.size(); i++) {
            SimpleProblem now = allProb.get(i);
            if (now.isSolved()) {
                prob.add(i);
            }
        }
        return prob;
    }

    /**
     * all unsolved problem index list
     *
     * @return all unsolved problem index list
     */
    private ArrayList<Integer> getUnsolvedProblem() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Integer> prob = new ArrayList<>();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;
        for (int i = 0; i < allProb.size(); i++) {
            SimpleProblem now = allProb.get(i);
            if (!now.isSolved()) {
                prob.add(i);
            }
        }
        return prob;
    }

    /**
     * update tabel contents
     *
     * @param problemInfoJTable table contents
     */
    private void tableItemsSet(Object[][] problemInfoJTable) {
        ProblemJTable.setModel(new javax.swing.table.DefaultTableModel(
                problemInfoJTable,
                new String[]{
                    "Judge", "Problem ID", "Problem Name", "Level", "Add Date", "Solve Date", "Mark"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        ProblemJTable = new javax.swing.JTable();
        allProblemJRadioButton = new javax.swing.JRadioButton();
        solvedProblemJRadioButton = new javax.swing.JRadioButton();
        unsolvedProblemJRadioButton = new javax.swing.JRadioButton();
        inqueueProblemJRadioButton = new javax.swing.JRadioButton();
        markAllProblemJButton = new javax.swing.JButton();
        toggleProblemJButton = new javax.swing.JButton();
        removeQueueJButton = new javax.swing.JButton();
        addToQueueJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        markSolvedJButton = new javax.swing.JButton();
        markUnsolvedJButton = new javax.swing.JButton();
        problemJTableJPanel = new javax.swing.JPanel();
        topicNameJLabel = new javax.swing.JLabel();
        topicJLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        addToExportJButton = new javax.swing.JButton();

        ProblemJTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ProblemJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Judge", "Problem ID", "Problem Name", "Level", "Add Date", "Solve Date", "Mark"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProblemJTable.setFillsViewportHeight(true);
        ProblemJTable.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        ProblemJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ProblemJTable.getTableHeader().setReorderingAllowed(false);
        ProblemJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProblemJTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ProblemJTable);

        allProblemJRadioButton.setText("All Problems");

        solvedProblemJRadioButton.setText("Solved Problems");

        unsolvedProblemJRadioButton.setText("Unsolved Problems");

        inqueueProblemJRadioButton.setText("To Do Problems");

        markAllProblemJButton.setText("Mark all");
        markAllProblemJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAllProblemJButtonActionPerformed(evt);
            }
        });

        toggleProblemJButton.setText("Toggle");
        toggleProblemJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleProblemJButtonActionPerformed(evt);
            }
        });

        removeQueueJButton.setText("Remove from Queue");
        removeQueueJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeQueueJButtonActionPerformed(evt);
            }
        });

        addToQueueJButton.setText("Add To Queue");
        addToQueueJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToQueueJButtonActionPerformed(evt);
            }
        });

        deleteJButton.setText("Delete");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });

        markSolvedJButton.setText("Solved");
        markSolvedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markSolvedJButtonActionPerformed(evt);
            }
        });

        markUnsolvedJButton.setText("Unsolved");
        markUnsolvedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markUnsolvedJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout problemJTableJPanelLayout = new javax.swing.GroupLayout(problemJTableJPanel);
        problemJTableJPanel.setLayout(problemJTableJPanelLayout);
        problemJTableJPanelLayout.setHorizontalGroup(
            problemJTableJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        problemJTableJPanelLayout.setVerticalGroup(
            problemJTableJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        topicNameJLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        topicNameJLabel.setText("Topic Name:");

        topicJLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        topicJLabel.setText("All Problems");

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        addToExportJButton.setText("Add For Export");
        addToExportJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToExportJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markSolvedJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markUnsolvedJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToQueueJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeQueueJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToExportJButton))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(topicNameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(allProblemJRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(solvedProblemJRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unsolvedProblemJRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inqueueProblemJRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(markAllProblemJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toggleProblemJButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(topicJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(problemJTableJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topicNameJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topicJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allProblemJRadioButton)
                    .addComponent(solvedProblemJRadioButton)
                    .addComponent(unsolvedProblemJRadioButton)
                    .addComponent(inqueueProblemJRadioButton)
                    .addComponent(toggleProblemJButton)
                    .addComponent(markAllProblemJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(problemJTableJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addToQueueJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteJButton)
                        .addComponent(markSolvedJButton)
                        .addComponent(markUnsolvedJButton)
                        .addComponent(removeQueueJButton)
                        .addComponent(addToExportJButton)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * problem selection event.update problemDetailsPanel
     *
     * @param evt
     */
    private void ProblemJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProblemJTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
        int index = ProblemJTable.getSelectedRow();
        if (index < 0 || index >= model.getRowCount()) {
            return;
        }
        ProblemDemo problem = new ProblemDemo(model.getValueAt(index, 0).toString(), model.getValueAt(index, 1).toString());
        AcmToDoList.main_Window.tableLastSelectedProblemUpdate(problem.getFullProblem());
    }//GEN-LAST:event_ProblemJTableMouseClicked

    /**
     * delete marked problem from problem list
     *
     * @param evt
     */
    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
        int flagPos = model.getColumnCount() - 1;
        boolean flag = true;
        for (int i = 0; i < model.getRowCount();) {
            if (model.getValueAt(i, flagPos).equals(true)) {
                //System.err.println(model.getValueAt(i, 0).toString()+model.getValueAt(i, 1).toString());
                System.err.println(model.getValueAt(i, 0).toString() + "  " + model.getValueAt(i, 1).toString());
                new ProblemDemo(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString()).deleteProblem();

                model.removeRow(i);
                flag = false;
            } else {
                i++;
            }
        }
        if (flag) {
            int index = ProblemJTable.getSelectedRow();
            if (index >= 0 && index < model.getRowCount()) {
                new ProblemDemo(model.getValueAt(index, 0).toString(), model.getValueAt(index, 1).toString()).deleteProblem();
                model.removeRow(index);
            }
        }
    }//GEN-LAST:event_deleteJButtonActionPerformed

    /**
     * mark all problem in table that currently show
     *
     * @param evt
     */
    private void markAllProblemJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markAllProblemJButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
        int flagPos = model.getColumnCount() - 1;
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true, i, flagPos);
        }
    }//GEN-LAST:event_markAllProblemJButtonActionPerformed

    /**
     * toggle all problem in table that currently show
     *
     * @param evt
     */

    private void toggleProblemJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleProblemJButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
        int flagPos = model.getColumnCount() - 1;
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true ^ model.getValueAt(i, flagPos).equals(true), i, flagPos);
        }
    }//GEN-LAST:event_toggleProblemJButtonActionPerformed

    /**
     * add all marked problem to queue
     *
     * @param evt
     */

    private void addToQueueJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToQueueJButtonActionPerformed
        // TODO add your handling code here:

        ArrayList<ProblemDemo> markProb = getMarkedProblemList();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;

        //System.err.println(markProb.size());
        for (int i = 0; i < markProb.size(); i++) {
            //System.err.println(markProb.get(i));
            for (int j = 0; j < allProb.size(); j++) {
                if (markProb.get(i).isSameProblem(allProb.get(j))) {

                    allProb.get(j).addToQueue();
                    allProb.get(j).saveToFile();
                    break;
                }
            }
        }


    }//GEN-LAST:event_addToQueueJButtonActionPerformed

    /**
     * remove all marked problem from queue
     *
     * @param evt
     */

    private void removeQueueJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeQueueJButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<ProblemDemo> markProb = getMarkedProblemList();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;

        //System.err.println(markProb.size());
        for (int i = 0; i < markProb.size(); i++) {
            //System.err.println(markProb.get(i));
            for (int j = 0; j < allProb.size(); j++) {
                if (markProb.get(i).isSameProblem(allProb.get(j))) {

                    allProb.get(j).removeFromQueue();
                    allProb.get(j).saveToFile();
                    break;
                }
            }
        }
        if (inqueueProblemJRadioButton.isSelected()) {
            refreshProblemJTable();
        }
    }//GEN-LAST:event_removeQueueJButtonActionPerformed

    /**
     * set all marked problem as solved
     *
     * @param evt
     */
    private void markSolvedJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markSolvedJButtonActionPerformed
        // TODO add your handling code here:
        ArrayList<ProblemDemo> markProb = getMarkedProblemList();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;

        //System.err.println(markProb.size());
        for (int i = 0; i < markProb.size(); i++) {
            //System.err.println(markProb.get(i));
            for (int j = 0; j < allProb.size(); j++) {
                if (markProb.get(i).isSameProblem(allProb.get(j))) {

                    allProb.get(j).setSolved();
                    allProb.get(j).saveToFile();
                    break;
                }
            }
        }
        refreshProblemJTable();
    }//GEN-LAST:event_markSolvedJButtonActionPerformed

    /**
     * set all marked problem as unsolved
     *
     * @param evt
     */

    private void markUnsolvedJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markUnsolvedJButtonActionPerformed
        // TODO add your handling code here:

        ArrayList<ProblemDemo> markProb = getMarkedProblemList();
        ArrayList<SimpleProblem> allProb = AcmToDoList.All_Problem_List;

        //System.err.println(markProb.size());
        for (int i = 0; i < markProb.size(); i++) {
            //System.err.println(markProb.get(i));
            for (int j = 0; j < allProb.size(); j++) {
                if (markProb.get(i).isSameProblem(allProb.get(j))) {

                    allProb.get(j).setUnsolved();
                    allProb.get(j).saveToFile();
                    break;
                }
            }
        }
        refreshProblemJTable();

    }//GEN-LAST:event_markUnsolvedJButtonActionPerformed

    /**
     * search problem that contains the text
     *
     * @param evt
     */
    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:

        refreshProblemJTable();
        String search = searchTextField.getText();
        search = search.trim();
        System.err.println(search);
        if (search.equals("")) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();

        for (int i = 0; i < model.getRowCount();) {
            if (!isFound(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), search)) {
                model.removeRow(i);
            } else {
                i++;
            }
        }

    }//GEN-LAST:event_searchTextFieldActionPerformed

    /**
     * add all marked problem for export
     *
     * @param evt
     */
    private void addToExportJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToExportJButtonActionPerformed
        // TODO add your handling code here:

        AcmToDoList.Export_Problem.addAll(getMarkedProblemList());
        FileMenuForm.refreshExportProblemList();
        //exportimportframe.ExportMainFrame.refreshtable();
        //System.err.println("Size : " + AcmToDoList.Export_Problem.size());

        /*ArrayList<ProblemDemo> problems = AcmToDoList.Export_Problem;

         DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
         int flagPos = model.getColumnCount() - 1;
         boolean flag = true;
         for (int i = 0; i < model.getRowCount(); i++) {
         if (model.getValueAt(i, flagPos).equals(true)) {
         //System.err.println(model.getValueAt(i, 0).toString()+model.getValueAt(i, 1).toString());
         problems.add(new ProblemDemo(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString()));
         flag = false;
         }
         }
         if (flag) {
         int index = ProblemJTable.getSelectedRow();
         if (index >= 0 && index < model.getRowCount()) {
         problems.add(new ProblemDemo(model.getValueAt(index, 0).toString(), model.getValueAt(index, 1).toString()));
         }
         }*/

    }//GEN-LAST:event_addToExportJButtonActionPerformed

    /**
     * is search text found in probID or probName
     *
     * @param probID problem ID
     * @param probName Problem Name
     * @param search search text
     * @return
     */
    private boolean isFound(String probID, String probName, String search) {
        probID = probID.toLowerCase();
        search = search.toLowerCase();
        probName = probName.toLowerCase();
        if (probID.contains(search)) {
            return true;
        }
        return probName.contains(search);
    }

    /**
     * get all marked problems list
     *
     * @return marked problems
     */
    private ArrayList<ProblemDemo> getMarkedProblemList() {
        ArrayList<ProblemDemo> problems = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) ProblemJTable.getModel();
        int flagPos = model.getColumnCount() - 1;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, flagPos).equals(true)) {
                //System.err.println(model.getValueAt(i, 0).toString()+model.getValueAt(i, 1).toString());
                problems.add(new ProblemDemo(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString()));
            }
        }
        if (problems.isEmpty()) {
            int index = ProblemJTable.getSelectedRow();
            if (index >= 0 && index < model.getRowCount()) {
                problems.add(new ProblemDemo(model.getValueAt(index, 0).toString(), model.getValueAt(index, 1).toString()));
            }
        }
        return problems;
    }

    //private final String[] tableColumsName = {"Judge", "Problem No", "Problem Name", "Add Date", "Solve Date"};
    private ButtonGroup radioButtonGroup;
    private Object lastSelectJRadioButton;
    private String lastTopicName;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ProblemJTable;
    private javax.swing.JButton addToExportJButton;
    private javax.swing.JButton addToQueueJButton;
    private javax.swing.JRadioButton allProblemJRadioButton;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JRadioButton inqueueProblemJRadioButton;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton markAllProblemJButton;
    private javax.swing.JButton markSolvedJButton;
    private javax.swing.JButton markUnsolvedJButton;
    private javax.swing.JPanel problemJTableJPanel;
    private javax.swing.JButton removeQueueJButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JRadioButton solvedProblemJRadioButton;
    private javax.swing.JButton toggleProblemJButton;
    private javax.swing.JLabel topicJLabel;
    private javax.swing.JLabel topicNameJLabel;
    private javax.swing.JRadioButton unsolvedProblemJRadioButton;
    // End of variables declaration//GEN-END:variables
}
