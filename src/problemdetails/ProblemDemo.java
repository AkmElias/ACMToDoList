/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemdetails;

import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * only contains problems judge's name and problem ID. super class of all
 * problems classes.
 *
 * @author Kazi Nayeem
 */
public class ProblemDemo {

    private String judgeName;
    private String problemID;

    /**
     * create an object which judge name is judgeName and problem ID is
     * problemID
     *
     * @param judgeName judge name of new problem
     * @param problemID problemID of new problem
     */
    public ProblemDemo(String judgeName, String problemID) {
        this.judgeName = judgeName;
        this.problemID = problemID;
    }

    /**
     * get the judge name of problem
     *
     * @return judge name of problem
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * get problem ID of problem
     *
     * @return problem ID of problem
     */
    public String getProblemID() {
        return problemID;
    }

    /**
     * get problem judge name and problem ID as JSONObject. "judge" key for
     * judge name and "problemID" key for problem ID
     *
     * @return judge name and problem ID as JSONObject
     */
    public final JSONObject getJSONObjectDemo() {
        JSONObject obj = null;
        try {
            obj = new JSONObject();
            obj.put("judge", getJudgeName());
            obj.put("problemID", getProblemID());
        } catch (JSONException ex) {
            //Logger.getLogger(ProblemDemo.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("JSON make error... ProblemDemo 2000");
        }
        return obj;
    }

    /**
     * read from file and get SimpleProblem object that is same problem
     *
     * @return simple problem with same judge and problem ID
     */
    public SimpleProblem getSimpleProblem() {
        return SimpleProblem.getFromFile(judgeName, problemID);
    }

    /**
     * read from file and get FullProblem object that is same problem
     *
     * @return Full problem with same judge and problem ID
     */
    public FullProblem getFullProblem() {
        return FullProblem.readFromFile(judgeName, problemID);
    }

    /**
     * Indicates whether some other object is "equal to" this one
     *
     * @param prob the reference object with which to compare.
     * @return true if this problem is the same as the prob argument; false
     * otherwise
     */
    public boolean isSameProblem(ProblemDemo prob) {
        return prob.getJudgeName().equalsIgnoreCase(judgeName) && prob.getProblemID().equalsIgnoreCase(problemID);
    }

    /**
     * delete the problem from file and all topics the added
     */
    public void deleteProblem() {
        FullProblem problem = getFullProblem();

        problem.removeAllTopic();

        File file = new File("C:\\ACMToDoList\\simpleproblem\\" + getJudgeName() + getProblemID() + ".txt");
        if (file.isFile()) {
            file.delete();
        }
    }
}
