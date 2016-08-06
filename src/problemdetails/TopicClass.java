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
public class TopicClass {

    public static final int ALREADY_IN_THIS_TOPIC = 5000;

    public static final int SUCCESSFULLY_ADDED = 5001;
    public static final int ERROR_IN_ADDING = 5002;
    public static final int ALREADY_TOPIC_ADDED = 5500;
    public static final int FORMAT_ERROR = 5502;

    public static final int SUCCESSFULLY_REMOVE = 5552;
    public static final int ERROR_IN_REMOVE = 5592;
    public static final int TOPIC_NOT_FOUND = 5522;
    public static final int PROBLEM_NOT_FOUND = 5525;

    private final String name;
    private ArrayList<ProblemDemo> problems;
    private JSONArray probJSON;
    private JSONObject probMAP;

    /**
     * create new object for specific name topic. all data read from file.if
     * file not found, then create new file
     *
     * @param name topic name
     */
    TopicClass(String name) {
        this.name = name;
        problems = new ArrayList<>();

        probMAP = new JSONObject();

        if (!readFromFile()) {
            problemsToProbJSON();
            writeToFile();
        }
    }

    /**
     * add problem prob to this topic and return status of problem adding
     * process
     *
     * @param prob problem that to add
     * @return status of problem addition
     */
    int addProblem(ProblemDemo prob) {
        if (isElement(prob)) {
            return ALREADY_IN_THIS_TOPIC;
        }
        String problemname = prob.getJudgeName() + prob.getProblemID();

        try {
            probMAP.put(problemname, new Boolean(true));
            problems.add(prob);

            problemsToProbJSON();
            writeToFile();
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(problemname + "  error adding problem ... 5009");
            return ERROR_IN_ADDING;
        }
        return SUCCESSFULLY_ADDED;
    }

    /**
     * remove problem prob from this topic and return removing status
     *
     * @param prob problem that remove
     * @return removing problem from this topic status
     */
    int removeProblem(ProblemDemo prob) {
        if (!isElement(prob)) {
            return PROBLEM_NOT_FOUND;
        }
        String problemname = prob.getJudgeName() + prob.getProblemID();

        try {
            probMAP.put(problemname, new Boolean(false));

            for (int i = 0; i < problems.size(); i++) {
                if (problems.get(i).isSameProblem(prob)) {
                    problems.remove(i);
                }
            }
            problemsToProbJSON();
            writeToFile();
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(problemname + "  error adding problem ... 5009");
            return ERROR_IN_ADDING;
        }
        return SUCCESSFULLY_REMOVE;
    }

    /**
     * read all data from file and initialize all variable
     *
     * @return return true if successfully readed otherwise false
     */
    synchronized private boolean readFromFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File file = new File("C:\\ACMToDoList\\TopicDetails\\" + name + ".txt");
        if (!(file.isFile())) {
            System.err.println("topicdetails " + name + " not found");
            return false;
        }

        try {
            Scanner scan = new Scanner(file);
            String input = scan.nextLine();
            scan.close();

            JSONObject main = new JSONObject(input);
            probJSON = main.getJSONArray("problemList");
            probMAP = main.getJSONObject("problemMap");

            probJSONToProblems();

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("read error  topic class.... 5000");
            return false;
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Format miss match topic class... 5001");
            return false;
        }

        return true;
    }

    /**
     * save all data related to topic to file named as topic name and directory
     * "C:\ACMToDoList\TopicDetails\"
     */
    synchronized private void writeToFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File file = new File("C:\\ACMToDoList\\TopicDetails\\" + name + ".txt");
        Formatter print = null;

        try {

            JSONObject main = new JSONObject();
            main.put("problemList", probJSON);
            main.put("problemMap", probMAP);

            print = new Formatter(file);
            print.format("%s", main.toString());
            print.close();

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("write error  topic class.... 5005");
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Format miss match topic class... 5006");
        } finally {
            if (print != null) {
                print.close();
            }
        }
    }

    /**
     * update problem arraylist
     */
    private void probJSONToProblems() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        problems.clear();
        try {
            for (int i = 0; i < probJSON.length(); i++) {

                JSONObject obj = probJSON.getJSONObject(i);
                ProblemDemo prob = new ProblemDemo(obj.getString("judge"), obj.getString("problemID"));
                problems.add(prob);
            }
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Format miss match topic class... 5002");
        }
    }

    /**
     * update probJSON from problems array list
     */
    private void problemsToProbJSON() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        probJSON = new JSONArray();
        for (int i = 0; i < problems.size(); i++) {
            probJSON.put(problems.get(i).getJSONObjectDemo());
        }
    }

    /**
     * is this problem prob in this topic
     *
     * @param prob problem
     * @return if prob is in this topic returns true otherwise false
     */
    private boolean isElement(ProblemDemo prob) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String problemname = prob.getJudgeName() + prob.getProblemID();
        try {
            if (probMAP.getBoolean(problemname)) {
                return true;
            }
        } catch (JSONException ex) {
            //Logger.getLogger(TopicClass.class.getName()).log(Level.SEVERE, null, ex);
            //System.err.println(problemname + " problem not found");
        }
        return false;
    }

    /**
     * get all problems that in this topic
     *
     * @return list of problems
     */
    ArrayList<ProblemDemo> getProblems() {
        return problems;
    }

    /**
     * get topic name
     *
     * @return topic name
     */
    String getName() {
        return name;
    }

    /**
     * get number of problems
     *
     * @return number of problems
     */
    int countProblems() {
        return problems.size();
    }

    /**
     * get problem of specific index
     *
     * @param i index of problem
     * @return problem of i index
     */
    ProblemDemo getProblem(int i) {
        return problems.get(i);
    }

    /**
     * delete this topic from list and delete file of this topic
     */
    void deleteTopic() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File file = new File("C:\\ACMToDoList\\TopicDetails\\" + name + ".txt");
        if (file.isFile()) {
            file.delete();
        }
    }
}
