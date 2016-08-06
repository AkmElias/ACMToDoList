/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemdetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kazi Nayeem
 */
public class FullProblem extends SimpleProblem {

    private ArrayList<String> topicList;
    private ArrayList<InputOutput> inputOutput;

    /**
     * create new FullProblem object that has no topics and input output.
     * problem judge is judge problem ID is probID and name id pName
     *
     * @param judge judge name of problem
     * @param probID problem ID of problem
     * @param pName problem name of problem
     */
    public FullProblem(String judge, String probID, String pName) {
        super(judge, probID, pName);
        topicList = new ArrayList<>();
        inputOutput = new ArrayList<>();
    }

    /**
     * create new object of FullProblem that judge name is judge, problem ID is
     * pronID, problem name is pName, added date is addedDate, solved date is
     * solvedDate, queue position is quePos without any topics and input-output
     *
     * @param probjudge Judge Name of problem
     * @param probID Problem ID of problem
     * @param probname Problem Name of problem
     * @param adddate Added Date of problem
     * @param solvedate Solved Date of problem
     * @param issolve true if solved otherwise false
     * @param isque true if problem added to queue otherwise false
     * @param aInt queue position
     */
    private FullProblem(String probjudge, String probID, String probname, String adddate, String solvedate, boolean issolve, boolean isque, int aInt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super(probjudge, probID, probname, adddate, solvedate, issolve, isque, aInt);
        topicList = new ArrayList<>();
        inputOutput = new ArrayList<>();
    }

    /**
     * remove from all topics
     */
    void removeAllTopic() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < topicList.size(); i++) {
            TopicDetail.removeProblemFromTopic(topicList.get(i), this);
        }
        TopicDetail.removeProblemFromTopic(TopicDetail.ALL_PROBLEM_Topic, this);
    }

    /**
     * set all input output from jsonArray
     *
     * @param jsonArray input-output JSONArray
     */
    public void setInputOutputJSON(JSONArray jsonArray) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        JSONObject obj;
        inputOutput = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                obj = jsonArray.getJSONObject(i);
                inputOutput.add(new InputOutput(obj));
            } catch (JSONException ex) {
                //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("topic format error.... 6006");
            }
        }
        return;
    }

    /**
     * class contains two strings, input and output. this manipulate operation
     * on input-output
     */
    private class InputOutput {

        private String input, output;

        /**
         * create new InputOutput which input is input and output is output
         *
         * @param input input of sample test case
         * @param output output of sample test case
         */
        public InputOutput(String input, String output) {
            this.input = input;
            this.output = output;
        }

        /**
         * create new InputOutput which input and output get from obj using keys
         * "input" and "output"
         *
         * @param obj contains input and output as format
         */
        public InputOutput(JSONObject obj) {
            try {
                input = obj.getString("input");
                output = obj.getString("output");
            } catch (JSONException ex) {
                //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
                input = "";
                output = "";
                System.err.println("Full problem input output format....... 6001");
            }
        }

        /**
         * get JSONObject of input-output that has input and output of this
         * object. input can get using "input" key and output can get using
         * "output" key
         *
         * @return input-output JSONObject
         */
        JSONObject getJSON() {
            JSONObject obj = null;
            try {
                obj = new JSONObject();
                obj.put("input", input);
                obj.put("output", output);

            } catch (JSONException ex) {
                //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Full problem input output format....... 6000");
            }
            return obj;
        }

        /**
         * get input sample
         *
         * @return sample input
         */
        String getInput() {
            return input;
        }

        /**
         * get sample output
         *
         * @return sample output
         */
        String getOutput() {
            return output;
        }

        /**
         * set this input as input
         *
         * @param input new input
         */
        void setInput(String input) {
            this.input = input;
        }

        /**
         * set this output as output
         *
         * @param output new output
         */
        void setOutput(String output) {
            this.output = output;
        }
    }

    /**
     * get all topics list of this problem as object array
     *
     * @return all topics of this problem
     */
    public Object[] getAllTopics() {
        return topicList.toArray();
    }

    /**
     * save problems all information as JSONObject to file that name is this
     * judge name and problem ID and directory is
     * "C:\\ACMToDoList\\simpleproblem"
     */
    synchronized public void saveToFile() {
        if (!new File("C:\\ACMToDoList\\simpleproblem").isDirectory()) {
            System.err.println("simple problem not directory");
            new File("C:\\ACMToDoList\\simpleproblem").mkdirs();
            System.err.println("C:\\ACMToDoList\\simpleproblem  make");
        }
        try {
            JSONObject obj = new JSONObject();
            File file = new File("C:\\ACMToDoList\\simpleproblem\\" + getJudgeName() + getProblemID() + ".txt");
            if (file.isFile()) {
                Scanner scan = new Scanner(file);
                obj = new JSONObject(scan.nextLine());
                scan.close();
            }
            obj.put("judge", getJudgeName());
            obj.put("problemID", getProblemID());
            obj.put("problemName", getProblemName());
            obj.put("addDate", getAddedDate());
            obj.put("solveDate", getSolvedDate());
            obj.put("isSolved", isSolved());
            obj.put("isInqueue", isInQueue());
            obj.put("queuePos", queuePos());
            obj.put("diffLevel", getDifficultyLevel());
            obj.put("topics", getJSONTopic());
            obj.put("inputoutput", getInputOutputJSON());

            Formatter formatter = new Formatter(file);
            formatter.format("%s", obj.toString());
            formatter.close();

        } catch (JSONException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.JSONObject error  simpleproblem savetofile");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.File error  simpleproblem savetofile");
        }
    }

    /**
     * get all topics as JSONArray.to get topic name use token "topicName"
     *
     * @return all topics as JSONArray
     */
    private JSONArray getJSONTopic() {
        JSONArray arr = new JSONArray();

        for (int i = 0; i < topicList.size(); i++) {
            try {
                JSONObject obj = new JSONObject();
                obj.put("topicName", topicList.get(i));
                arr.put(obj);
            } catch (JSONException ex) {
                //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("topic format error.... 6005");
            }
        }
        return arr;
    }

    /**
     * set all topics that in arr
     *
     * @param arr topic list
     */
    public void setTopicJSON(JSONArray arr) {
        JSONObject obj;
        topicList = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try {
                obj = arr.getJSONObject(i);
                topicList.add(obj.getString("topicName"));
            } catch (JSONException ex) {
                //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("topic format error.... 6006");
            }
        }
        return;
    }

    /**
     * add new topic name to problem at last and save all information to file as
     * JSONObject
     *
     * @param name new topic name
     */
    synchronized public void addTopic(String name) {
        for (int i = 0; i < topicList.size(); i++) {
            if (topicList.get(i).equalsIgnoreCase(name)) {
                return;
            }
        }
        topicList.add(name);
        saveToFile();
    }

    /**
     * remove topic name from topics list and save all information
     *
     * @param name topic name that have to remove
     */
    synchronized public void removeTopic(String name) {
        for (int i = 0; i < topicList.size(); i++) {
            if (topicList.get(i).equalsIgnoreCase(name)) {
                topicList.remove(i);
            }
        }
        saveToFile();
    }

    /**
     * is topic name in the topic list of this problem
     *
     * @param name topic name
     * @return if name found as topic return true, otherwise false
     */
    synchronized public boolean isTopicAdded(String name) {
        for (int i = 0; i < topicList.size(); i++) {
            if (topicList.get(i).equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * create problem and read problem details from file from
     * "C:\\ACMToDoList\\simpleproblem" directory
     *
     * @param judgeName judge name of problem
     * @param problemID problem ID of problem
     * @return object of specific judge name and problem ID read from file
     */
    synchronized static FullProblem readFromFile(String judgeName, String problemID) {
        if (!new File("C:\\ACMToDoList\\simpleproblem").isDirectory()) {
            System.err.println("simple problem not directory");
            new File("C:\\ACMToDoList\\simpleproblem").mkdirs();
            System.err.println("C:\\ACMToDoList\\simpleproblem  make");
        }
        try {
            Scanner file = new Scanner(new File("C:\\ACMToDoList\\simpleproblem\\" + judgeName + problemID + ".txt"));
            String input = file.nextLine();
            file.close();
            JSONObject obj = new JSONObject(input);
            FullProblem prob = new FullProblem(obj.getString("judge"), obj.getString("problemID"), obj.getString("problemName"),
                    obj.getString("addDate"), obj.getString("solveDate"), obj.getBoolean("isSolved"), obj.getBoolean("isInqueue"), obj.getInt("queuePos"));

            prob.setDifficultyLevel(obj.getInt("diffLevel"));

            prob.setTopicJSON(obj.getJSONArray("topics"));
            prob.setInputOutputJSON(obj.getJSONArray("inputoutput"));
            //obj.put("inputoutput", getInputOutputJSON());

            return prob;
        } catch (JSONException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.JSONObject error  simpleproblem savetofile");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.File error  simpleproblem savetofile");
        }
        return null;
    }

    /**
     * get for export problem details as judge,problemID,problemName,level,no.
     * of topic,topics
     *
     * @return CSV export formated string
     */
    public String getExportProblemCSV() {
        String result = getJudgeName() + ",";
        result = result + getProblemID() + ",";
        result = result + getProblemName() + ",";
        result = result + getDifficultyLevel() + ",";
        result = result + topicList.size();
        for (int i = 0; i < topicList.size(); i++) {
            result = result + "," + topicList.get(i);
        }
        return result;
    }

    /**
     * get for export problem details as JSONObject
     *
     * @return JSON export formated string
     */
    public String getExportProblemTXT() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("judge", getJudgeName());
            obj.put("problemID", getProblemID());
            obj.put("problemName", getProblemName());
            obj.put("diffLevel", getDifficultyLevel());
            obj.put("topics", getJSONTopic());
            obj.put("inputoutput", getInputOutputJSON());

        } catch (JSONException ex) {
            //Logger.getLogger(FullProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem export failed.JSONObject error.... 6050");
        }
        return obj.toString();
    }

    /**
     * get number of topics
     *
     * @return number of topics
     */
    public int getNoOfTopic() {
        return topicList.size();
    }

    /**
     * get topic name of specific index
     *
     * @param i index of topic
     * @return topic name of i index topic
     */
    public String getTopic(int i) {
        return topicList.get(i);
    }

    /**
     * set topic name of specific index
     *
     * @param i index of topic
     * @param name repleced topic name
     */
    public void setTopic(int i, String name) {
        topicList.set(i, name);
    }

    /**
     * get number of sample input-output
     *
     * @return number of sample input-output
     */
    public int getInputOutputNo() {
        return inputOutput.size();
    }

    /**
     * get input of specific index
     *
     * @param i index of sample input
     * @return sample input
     */
    public String getInput(int i) {
        return inputOutput.get(i).getInput();
    }

    /**
     * get output of specific index
     *
     * @param i index of sample output
     * @return sample output
     */
    public String getOutput(int i) {
        return inputOutput.get(i).getOutput();
    }

    /**
     * add new sample input-output which input is inp and output is out
     *
     * @param inp
     * @param out
     */
    public void addInputOutput(String inp, String out) {
        inputOutput.add(new InputOutput(inp, out));
        saveToFile();
    }

    /**
     * remove specific index input-output and save all data
     *
     * @param i index of remove input-output
     */
    public void removeInputOutput(int i) {
        inputOutput.remove(i);
        saveToFile();
    }

    /**
     * set specific index input and output
     *
     * @param i index of input-output
     * @param input sample input
     * @param output sample output
     */
    public void setInputOutput(int i, String input, String output) {
        inputOutput.set(i, new InputOutput(input, output));
        saveToFile();
    }

    /**
     * get all sample input-output as JSONArray
     *
     * @return all sample input-output as JSONArray
     */
    private JSONArray getInputOutputJSON() {
        JSONArray arr = new JSONArray();
        for (int i = 0; i < getInputOutputNo(); i++) {
            arr.put(inputOutput.get(i).getJSON());
        }
        return arr;
    }
}
