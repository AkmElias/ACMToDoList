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
public class TopicDetail {

    final static public String ALL_PROBLEM_Topic = "All Problems";
    final static public String SELECT_ONE = "Select One";

    static {
        File file = new File("C:\\ACMToDoList\\TopicDetails");
        if (!(file.isDirectory())) {
            System.err.println("topicdetails directory");
            file.mkdirs();
        }
        allProblem = new TopicClass(ALL_PROBLEM_Topic);

        initAllTopic();
    }

    private static TopicClass allProblem;
    private static TopicClass topicList[];

    /**
     * add problem to specific topic and return status of adding problem
     *
     * @param topic topic name
     * @param problem problem that add to topic
     * @return status of adding problem
     */
    static public int addProblemToTopic(String topic, ProblemDemo problem) {
        if (topic.equalsIgnoreCase(ALL_PROBLEM_Topic)) {
            return allProblem.addProblem(problem);
        }
        try {
            for (int i = 0; i < topicList.length; i++) {
                if (topic.equalsIgnoreCase(topicList[i].getName())) {
                    return topicList[i].addProblem(problem);
                }
            }
        } catch (NullPointerException ex) {

        }
        return TopicClass.TOPIC_NOT_FOUND;
    }

    /**
     * remove problem from specific topic
     *
     * @param topic topic name
     * @param problem problem that remove
     * @return status of removing problem
     */
    static public int removeProblemFromTopic(String topic, ProblemDemo problem) {
        if (topic.equalsIgnoreCase(ALL_PROBLEM_Topic)) {
            return allProblem.removeProblem(problem);
        }
        try {
            for (int i = 0; i < topicList.length; i++) {
                if (topic.equalsIgnoreCase(topicList[i].getName())) {
                    return topicList[i].removeProblem(problem);
                }
            }
        } catch (NullPointerException ex) {

        }
        return TopicClass.TOPIC_NOT_FOUND;
    }

    /**
     * get all problems of specific topic. if topic not found return all problem
     *
     * @param topic topic name
     * @return list of problem of topic
     */
    static public ArrayList<SimpleProblem> getAllProblem(String topic) {
        TopicClass topicObj = allProblem;
        try {
            for (int i = 0; i < topicList.length; i++) {
                if (topic.equalsIgnoreCase(topicList[i].getName())) {
                    topicObj = topicList[i];
                    break;
                }
            }
        } catch (NullPointerException ex) {
            System.err.println("null pointer ");
        }

        ArrayList<SimpleProblem> simPro = new ArrayList<>();
        ArrayList<ProblemDemo> demoPro = topicObj.getProblems();

        for (int i = 0; i < demoPro.size();) {
            SimpleProblem prob = demoPro.get(i).getSimpleProblem();
            if (prob == null) {
                topicObj.removeProblem(demoPro.get(i));
            } else {
                simPro.add(prob);
                i++;
            }
        }
        return simPro;
    }

    /**
     * get list of topic and initialize all topic
     */
    private static void initAllTopic() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("initAllTopic");
        File file = new File("C:\\ACMToDoList\\TopicList.txt");
        if (file.isFile()) {
            readAllTopicFile();
        } else {
            System.err.println("Topic list not found");
            writeAllTopicFile();
        }
    }

    /**
     * get the list of topic from file "C:\\ACMToDoList\\TopicList.txt"
     */
    synchronized private static void readAllTopicFile() {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            File file = new File("C:\\ACMToDoList\\TopicList.txt");
            Scanner scan = new Scanner(file);
            JSONArray arr = new JSONArray(scan.nextLine());

            System.out.println("----topic list----");
            topicList = new TopicClass[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                topicList[i] = new TopicClass(arr.getJSONObject(i).getString("topic"));
                System.out.println(topicList[i].getName());
            }
            System.out.println("------------------");
            scan.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(TopicDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Topic list file missing.... topicdetail...4000");
        } catch (JSONException ex) {
            //Logger.getLogger(TopicDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Topic list format error.... topicdetail...4001");
        }

    }

    /**
     * save the list of topic to file "C:\\ACMToDoList\\TopicList.txt"
     */
    synchronized private static void writeAllTopicFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {

            JSONArray arr = new JSONArray();
            if (topicList != null) {
                for (int i = 0; i < topicList.length; i++) {
                    JSONObject obj = new JSONObject();
                    obj.put("topic", topicList[i].getName());
                    arr.put(obj);
                }
            }

            File file = new File("C:\\ACMToDoList\\TopicList.txt");
            Formatter output = new Formatter(file);
            output.format("%s", arr.toString());
            output.close();

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(TopicDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Topic list file missing.... topicdetail...4000");
        } catch (JSONException ex) {
            //Logger.getLogger(TopicDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Topic list format error.... topicdetail...4001");
        }
    }

    /**
     * add new topic and return status of adding topic
     *
     * @param name topic name
     * @return status of adding topic
     */
    public static int addTopic(String name) {
        name = name.trim();
        if (name.equalsIgnoreCase("Enter Name") || name.equals("")) {
            return TopicClass.FORMAT_ERROR;
        }
        TopicClass top[];
        if (topicList != null) {
            top = new TopicClass[topicList.length + 1];
        } else {
            top = new TopicClass[1];
        }

        try {
            for (int i = 0; i < topicList.length; i++) {
                if (topicList[i].getName().equalsIgnoreCase(name)) {
                    return TopicClass.ALREADY_TOPIC_ADDED;
                }
                top[i] = topicList[i];
            }
        } catch (NullPointerException ex) {

        }
        top[top.length - 1] = new TopicClass(name);

        topicList = top;

        for (int i = topicList.length - 2; i >= 0; i--) {
            if (topicList[i].getName().compareToIgnoreCase(topicList[i + 1].getName()) > 0) {
                TopicClass temp = topicList[i];
                topicList[i] = topicList[i + 1];
                topicList[i + 1] = temp;
            }
        }

        writeAllTopicFile();
        return TopicClass.SUCCESSFULLY_ADDED;
    }

    /**
     * remove topic and return status of removing topic
     *
     * @param name topic name that remove
     * @return status of removing topic
     */
    public static int removeTopic(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        name = name.trim();
        if (name.equalsIgnoreCase("Enter Name") || name.equals("") || name.equalsIgnoreCase(SELECT_ONE)) {
            return TopicClass.FORMAT_ERROR;
        }
        TopicClass top[] = new TopicClass[topicList.length - 1];
        int pos = -1;
        for (int i = 0; i < topicList.length; i++) {
            if (topicList[i].getName().equalsIgnoreCase(name)) {
                pos = i;
                topicList[i].deleteTopic();
                break;
            }
            top[i] = topicList[i];
        }
        if (pos == -1) {
            return TopicClass.TOPIC_NOT_FOUND;
        }

        for (int i = pos; i < top.length; i++) {
            top[i] = topicList[i + 1];
        }

        topicList = top;
        writeAllTopicFile();
        return TopicClass.SUCCESSFULLY_REMOVE;
    }

    /**
     * get list of topics name
     *
     * @return list of topics
     */
    public static String[] getTopicNameList() {
        if (topicList == null) {
            return new String[]{ALL_PROBLEM_Topic};
        }
        String str[] = new String[topicList.length + 1];
        str[0] = ALL_PROBLEM_Topic;

        for (int i = 0; i < topicList.length; i++) {
            str[i + 1] = topicList[i].getName();
        }
        return str;
    }

    /**
     * add imported problem to all of this topic list
     *
     * @param problem problem that add
     */
    public static void addImportedProblem(FullProblem problem) {
        int result = addProblemToTopic(ALL_PROBLEM_Topic, problem);

        if (result == TopicClass.ALREADY_IN_THIS_TOPIC) {

            FullProblem existProb = new ProblemDemo(problem.getJudgeName(), problem.getProblemID()).getFullProblem();

            for (int i = 0; i < problem.getNoOfTopic(); i++) {
                String name = searchMyTopicOrAdd(problem.getTopic(i));
                if (existProb.isTopicAdded(name)) {
                    continue;
                }
                existProb.addTopic(name);
                addProblemToTopic(name, existProb);
            }
            for (int i = 0; i < problem.getInputOutputNo(); i++) {
                existProb.addInputOutput(problem.getInput(i), problem.getOutput(i));
            }

            existProb.saveToFile();

        } else if (result == TopicClass.SUCCESSFULLY_ADDED) {
            for (int i = 0; i < problem.getNoOfTopic(); i++) {
                String name = searchMyTopicOrAdd(problem.getTopic(i));
                problem.setTopic(i, name);
                addProblemToTopic(name, problem);
            }
            problem.saveToFile();
        }
    }

    /**
     * search topic from topic list.if not found add the topic
     *
     * @param name topic that search
     * @return name of topic
     */
    private static String searchMyTopicOrAdd(String name) {
        String str[] = getTopicNameList();
        for (int i = 0; i < str.length; i++) {
            if (str[i].equalsIgnoreCase(name)) {
                return str[i];
            }
        }
        System.err.println(name);
        addTopic(name);
        return name;
    }

}
