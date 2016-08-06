/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmtodolist;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import problemdetails.FullProblem;
import problemdetails.TopicDetail;

/**
 *
 * @author Kazi Nayeem
 */
public class ProblemDetailJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProblemDetailJPanel initialize all variables in the
     * object initialize all components in the panel
     */
    public ProblemDetailJPanel() {
        super();
        lastSelectInputOutput = -1;
        presentProblem = null;
        initComponents();
        initJComboBox();
    }

    /**
     * selected problem in table details update update problem all
     * details,input-output, topic list of the problem
     *
     * @param nowProblem
     */
    public void problemChange(FullProblem nowProblem) {
        saveLastProblem();
        presentProblem = nowProblem;
        refreshComponent();
    }

    /**
     * save the details of problem when the problem change or update
     */
    private void saveLastProblem() {
        if (presentProblem != null) {
            if (lastSelectInputOutput >= 0 && lastSelectInputOutput < presentProblem.getInputOutputNo()) {
                presentProblem.setInputOutput(lastSelectInputOutput, inputJTextArea.getText(), outputJTextArea.getText());
            }
        }
    }
    /**
     * when problem get change this update the whole 
     * panel for the previous problem
     */
    private void refreshComponent() {
        if (presentProblem == null) {
            nameJTextField.setText("");
            problemIDJLabel.setText("");
            judgeJLabel.setText("");
            diffOutJLabel.setText("");
            diffJTextField.setText("");
            refreshTopicJList();
            refreshTopicJCheckBox();
            refreshInputOutput();
            return;
        }
        nameJTextField.setText(presentProblem.getProblemName());
        problemIDJLabel.setText(presentProblem.getProblemID());
        judgeJLabel.setText(presentProblem.getJudgeName());
        diffOutJLabel.setText("");
        diffJTextField.setText("" + presentProblem.getDifficultyLevel());
        refreshTopicJList();
        refreshTopicJCheckBox();
        refreshInputOutput();
    }
    /**
     * update topic list for specific problem
     */
    private void refreshTopicJList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (presentProblem == null) {
            String[] s = new String[0];
            topicJList.setListData(s);
            return;
        }

        topicJList.setListData(presentProblem.getAllTopics());
    }

    /**
     * update input output for last selected
     * problem
     */
    private void refreshInputOutput() {
        if (presentProblem == null) {
            String str[] = new String[0];
            inputOutputJComboBox.setModel(new DefaultComboBoxModel(str));
            inputJTextArea.setText("");
            outputJTextArea.setText("");
            lastSelectInputOutput = -1;
            return;
        }

        if (presentProblem.getInputOutputNo() == 0) {
            presentProblem.addInputOutput("", "");
        }
        refeshInOutJComboBox();
        inputJTextArea.setText(presentProblem.getInput(0));
        outputJTextArea.setText(presentProblem.getOutput(0));
        lastSelectInputOutput = 0;
    }
    /**
     * update sample input output selection combo box
     * depending on number of sample input-output
     */
    private void refeshInOutJComboBox() {
        String str[] = new String[presentProblem.getInputOutputNo()];
        for (int i = 1; i <= str.length; i++) {
            str[i - 1] = "" + i;
        }
        inputOutputJComboBox.setModel(new DefaultComboBoxModel(str));
    }
    
    /**
     * update topic selection combo box
     * for present problems topic list
     * topics that not in the problem's topic list
     */
    void refreshTopicJCheckBox() {
        if (presentProblem == null) {
            String[] s = new String[0];
            selectTopicJComboBox.setModel(new DefaultComboBoxModel(s));
            return;
        }
        String[] allName = TopicDetail.getTopicNameList();
        allName[0] = TopicDetail.SELECT_ONE;
        ArrayList<String> finalArr = new ArrayList<>();
        for (int i = 0; i < allName.length; i++) {
            if (presentProblem.isTopicAdded(allName[i])) {
                continue;
            }
            finalArr.add(allName[i]);
        }
        selectTopicJComboBox.setModel(new DefaultComboBoxModel(finalArr.toArray()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        topicJList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        judgeJLabel = new javax.swing.JLabel();
        problemIDJLabel = new javax.swing.JLabel();
        topicRemoveJButton = new javax.swing.JButton();
        selectTopicJComboBox = new javax.swing.JComboBox();
        addTopicJButton = new javax.swing.JButton();
        nameJTextField = new javax.swing.JTextField();
        diffJLabel = new javax.swing.JLabel();
        diffJTextField = new javax.swing.JTextField();
        diffOutJLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputJTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputJTextArea = new javax.swing.JTextArea();
        inOutAddJButton = new javax.swing.JButton();
        inOutRemoveJButton = new javax.swing.JButton();
        inputOutputJComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        topicJList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(topicJList);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Judge:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID:");

        judgeJLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        problemIDJLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        topicRemoveJButton.setText("Remove");
        topicRemoveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topicRemoveJButtonActionPerformed(evt);
            }
        });

        selectTopicJComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        addTopicJButton.setText("Add Topic");
        addTopicJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTopicJButtonActionPerformed(evt);
            }
        });

        nameJTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameJTextFieldActionPerformed(evt);
            }
        });

        diffJLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diffJLabel.setText("Difficulty Level:");

        diffJTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diffJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diffJTextFieldActionPerformed(evt);
            }
        });

        diffOutJLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        diffOutJLabel.setForeground(new java.awt.Color(255, 0, 0));

        outputJTextArea.setColumns(20);
        outputJTextArea.setRows(5);
        jScrollPane2.setViewportView(outputJTextArea);

        jLabel4.setText("Sample Output");

        inputJTextArea.setColumns(20);
        inputJTextArea.setRows(5);
        jScrollPane3.setViewportView(inputJTextArea);

        inOutAddJButton.setText("Add");
        inOutAddJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOutAddJButtonActionPerformed(evt);
            }
        });

        inOutRemoveJButton.setText("Remove");
        inOutRemoveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOutRemoveJButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Sample Input");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(judgeJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(problemIDJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(diffJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(diffJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addTopicJButton))
                                .addComponent(selectTopicJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nameJTextField))
                                .addComponent(jLabel4)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                .addComponent(topicRemoveJButton, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(57, 57, 57)
                                .addComponent(inputOutputJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(inOutRemoveJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inOutAddJButton)))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diffOutJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(judgeJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(problemIDJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topicRemoveJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectTopicJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addTopicJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diffJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diffJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputOutputJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inOutAddJButton)
                        .addComponent(inOutRemoveJButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(diffOutJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * get remove topic button action event
     * update problem topic list of the problem
     * save the problem
     * update other components that need to update
     * @param evt 
     */
    private void topicRemoveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topicRemoveJButtonActionPerformed
        // TODO add your handling code here:

        int[] selected = topicJList.getSelectedIndices();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i] < 0) {
                continue;
            }
            String Topic = topicJList.getModel().getElementAt(selected[i]).toString();
            //System.err.println(Topic);

            TopicDetail.removeProblemFromTopic(Topic, presentProblem);
            presentProblem.removeTopic(Topic);
        }
        refreshTopicJList();
        refreshTopicJCheckBox();
    }//GEN-LAST:event_topicRemoveJButtonActionPerformed

    
    /**
     * get add topic button action event
     * update problem topic list of the problem
     * save the problem
     * update other components that need to update
     * @param evt 
     */
    private void addTopicJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTopicJButtonActionPerformed
        // TODO add your handling code here:
        String topic = selectTopicJComboBox.getSelectedItem().toString();

        if (presentProblem.isTopicAdded(topic)) {
            return;
        }
        //System.err.println(topic);
        presentProblem.addTopic(topic);
        TopicDetail.addProblemToTopic(topic, presentProblem);
        refreshTopicJList();
        refreshTopicJCheckBox();
    }//GEN-LAST:event_addTopicJButtonActionPerformed

    /**
     * change the name of current problem
     * save problem details
     * update problem list for the problem
     * @param evt 
     */
    private void nameJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameJTextFieldActionPerformed
        // TODO add your handling code here:

        if (presentProblem == null) {
            return;
        }
        String string = nameJTextField.getText().trim();

        if (string.equals("")) {
            nameJTextField.setText(presentProblem.getProblemName());
            return;
        }

        presentProblem.setProblemName(string);

        presentProblem.saveToFile();

        AcmToDoList.problemDetailsUpdated(presentProblem);

    }//GEN-LAST:event_nameJTextFieldActionPerformed

    /**
     * change difficulty level of current problem
     * save problem details
     * update problem list for the problem
     * @param evt 
     */
    private void diffJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diffJTextFieldActionPerformed
        // TODO add your handling code here:

        diffOutJLabel.setText("");
        String str = diffJTextField.getText().trim();
        int level;
        try {
            level = Integer.parseInt(str);
        } catch (Exception ex) {
            System.err.println("parse error");
            diffOutJLabel.setText("invalid char");
            return;
        }

        if (level > 10 || level < 0) {
            diffOutJLabel.setText("Level 0-10");
            return;
        }

        presentProblem.setDifficultyLevel(level);

        presentProblem.saveToFile();
        AcmToDoList.problemDetailsUpdated(presentProblem);
    }//GEN-LAST:event_diffJTextFieldActionPerformed

    /**
     * add an input-output of current problem with empty string
     * update components that need to update
     * @param evt 
     */
    private void inOutAddJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOutAddJButtonActionPerformed
        // TODO add your handling code here:

        if (presentProblem == null) {
            return;
        }
        presentProblem.addInputOutput("", "");
        refeshInOutJComboBox();

    }//GEN-LAST:event_inOutAddJButtonActionPerformed

    /**
     * remove last selected input-output of current problem
     * update components that need to update
     * @param evt 
     */
    private void inOutRemoveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOutRemoveJButtonActionPerformed
        // TODO add your handling code here:
        if (presentProblem == null) {
            return;
        }
        if (lastSelectInputOutput < 0 || lastSelectInputOutput >= presentProblem.getInputOutputNo()) {
            return;
        }

        presentProblem.removeInputOutput(lastSelectInputOutput);
        refreshInputOutput();

    }//GEN-LAST:event_inOutRemoveJButtonActionPerformed

    private int lastSelectInputOutput;
    private FullProblem presentProblem;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTopicJButton;
    private javax.swing.JLabel diffJLabel;
    private javax.swing.JTextField diffJTextField;
    private javax.swing.JLabel diffOutJLabel;
    private javax.swing.JButton inOutAddJButton;
    private javax.swing.JButton inOutRemoveJButton;
    private javax.swing.JTextArea inputJTextArea;
    private javax.swing.JComboBox inputOutputJComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel judgeJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JTextArea outputJTextArea;
    private javax.swing.JLabel problemIDJLabel;
    private javax.swing.JComboBox selectTopicJComboBox;
    private javax.swing.JList topicJList;
    private javax.swing.JButton topicRemoveJButton;
    // End of variables declaration//GEN-END:variables

    /**
     * initialize input output selection combo box item listener
     * it save last selected input output that might have changed
     * save the input output
     * and show the selected sample input output
     */
    private void initJComboBox() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        inputOutputJComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    if (presentProblem == null) {
                        return;
                    }
                    int index = inputOutputJComboBox.getSelectedIndex();
                    if (index < 0 || index >= presentProblem.getInputOutputNo()) {
                        return;
                    }

                    if (lastSelectInputOutput != -1) {
                        presentProblem.setInputOutput(lastSelectInputOutput, inputJTextArea.getText(), outputJTextArea.getText());
                    }

                    lastSelectInputOutput = index;
                    inputJTextArea.setText(presentProblem.getInput(index));
                    outputJTextArea.setText(presentProblem.getOutput(index));
                }
            }
        });
    }

}
