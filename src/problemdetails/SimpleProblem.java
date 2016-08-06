/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemdetails;

import dateoperations.MyDateClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * contains problems name, added date, solved date, queue position and
 * difficulty level
 *
 * @author Kazi Nayeem
 */
public class SimpleProblem extends ProblemDemo {

    private String problemName;
    private String addDate, solveDate;
    private Boolean solved, inqueue;
    int queuePosition, diffLevel;

    /**
     * create new object of SimpleProblem that judge name is judge, problem ID
     * is pronID, problem name is pName, added date is current time and not
     * solved,not in queue
     *
     * @param judge Judge Name of problem
     * @param probID Problem ID of problem
     * @param pName Problem Name of problem
     */
    public SimpleProblem(String judge, String probID, String pName) {
        super(judge, probID);
        problemName = pName;

        addDate = MyDateClass.getCurrentBoth();
        solveDate = MyDateClass.invalidBoth;

        solved = false;
        inqueue = false;
        diffLevel = 0;
        queuePosition = -1;
    }

    /*public SimpleProblem(String judge, String probID, String pName, String addedDate) {
     super(judge, probID);
     problemName = pName;

     addDate = addedDate;
     solveDate = MyDateClass.invalidBoth;

     solved = false;
     inqueue = false;

     diffLevel = 0;
     queuePosition = -1;
     }*/
    /*public SimpleProblem(String judge, String probID, String pName, String addedDate, String solvedDate) {
     super(judge, probID);
     problemName = pName;

     addDate = addedDate;
     solveDate = solvedDate;

     solved = false;
     inqueue = false;

     diffLevel = 0;
     queuePosition = -1;
     }*/
    /**
     * create new object of SimpleProblem that judge name is judge, problem ID
     * is pronID, problem name is pName, added date is addedDate, solved date is
     * solvedDate, queue position is quePos
     *
     * @param judge Judge Name of problem
     * @param probID Problem ID of problem
     * @param pName Problem Name of problem
     * @param addedDate Added Date of problem
     * @param solvedDate Solved Date of problem
     * @param solv true if solved otherwise false
     * @param inq true if problem added to queue otherwise false
     * @param quePos queue position
     */
    public SimpleProblem(String judge, String probID, String pName, String addedDate, String solvedDate, Boolean solv, Boolean inq, int quePos) {
        super(judge, probID);
        problemName = pName;

        addDate = addedDate;
        solveDate = solvedDate;

        solved = solv;
        inqueue = inq;

        diffLevel = 0;
        queuePosition = quePos;
    }

    /**
     * get Difficulty Level of problem
     *
     * @return Difficulty Level of problem
     */
    public int getDifficultyLevel() {
        return diffLevel;
    }

    /**
     * set Difficulty Level of problem(0-10)
     *
     * @param level Difficulty Level of problem
     */
    public void setDifficultyLevel(int level) {
        diffLevel = level;
    }

    /**
     * get Problem Name of problem
     *
     * @return Problem name of problem
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * set Problem name of problem
     *
     * @param name problem name of problem
     */
    public void setProblemName(String name) {
        problemName = name;
    }

    /**
     * get the added date of problem
     *
     * @return added date
     */
    public String getAddedDate() {
        return addDate;
    }

    /**
     * get solved date of problem
     *
     * @return solved date of problem
     */
    public String getSolvedDate() {
        return solveDate;
    }

    /**
     * is this problem solved
     *
     * @return true if solved, otherwise false
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * set the problem as solved and set solved date as current time
     *
     */
    public void setSolved() {
        if (solved) {
            return;
        }
        solveDate = MyDateClass.getCurrentBoth();
        solved = true;
    }

    /**
     * set the problem as unsolved
     */
    public void setUnsolved() {
        if (!solved) {
            return;
        }
        solveDate = MyDateClass.invalidBoth;
        solved = false;
    }

    /**
     * is this problem in queue
     *
     * @return true if this is in-queue, otherwise false
     */
    public boolean isInQueue() {
        return inqueue;
    }

    /**
     * add this problem to queue
     */
    public void addToQueue() {
        if (inqueue || solved) {
            return;
        }
        inqueue = true;
        queuePosition = getCurrentQueuePos();
    }

    /**
     * remove this from queue
     */
    public void removeFromQueue() {
        inqueue = false;
        queuePosition = -1;
    }

    /**
     * get added date without time
     *
     * @return added date without time
     */
    public String getAddedDateDay() {
        return addDate.substring(11);
    }

    /**
     * get solved date without time
     *
     * @return solved date without time
     */
    public String getSolvedDateDay() {
        return solveDate.substring(11);
    }

    /**
     * get queue position of this problem
     *
     * @return queue position of problem
     */
    public int queuePos() {
        return queuePosition;
    }

    /**
     * get problem list table model of this problem
     *
     * @return Problem list table model
     */
    public Object[] getTableModel() {
        Object[] str = new Object[7];
        str[0] = getJudgeName();
        str[1] = getProblemID();
        str[2] = getProblemName();
        str[3] = getDifficultyLevel();
        str[4] = getAddedDateDay();
        str[5] = getSolvedDateDay();
        str[6] = false;
        return str;
    }

    /**
     * get Export Import table model of this problem
     *
     * @return Export Import table model
     */
    public Object[] getExImTableModel() {
        Object[] str = new Object[4];
        str[0] = getJudgeName();
        str[1] = getProblemID();
        str[2] = getProblemName();
        str[3] = false;
        return str;
    }

    /**
     * save this problem information to file as JSONObject to
     * "C:\\ACMToDoList\\simpleproblem" directory, if file exists then marge the
     * informations. file name is judge name and problem ID without any blank
     * space
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
     * create problem and read problem details from file from
     * "C:\\ACMToDoList\\simpleproblem" directory
     *
     * @param judgeName judge name of problem
     * @param problemID problem ID of problem
     * @return object of specific judge name and problem ID read from file
     */
    synchronized static SimpleProblem getFromFile(String judgeName, String problemID) {
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
            SimpleProblem prob = new SimpleProblem(obj.getString("judge"), obj.getString("problemID"), obj.getString("problemName"),
                    obj.getString("addDate"), obj.getString("solveDate"), obj.getBoolean("isSolved"), obj.getBoolean("isInqueue"), obj.getInt("queuePos"));
            prob.setDifficultyLevel(obj.getInt("diffLevel"));
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
     * get last queue position from file and update the position adding one and
     * save and return the value
     *
     * @return current queue position
     */
    synchronized public static int getCurrentQueuePos() {
        File file = new File("C:\\ACMToDoList");
        if (!file.isDirectory()) {
            file.mkdirs();
        }

        file = new File("C:\\ACMToDoList\\defaultdata.txt");
        int pos = 0;
        JSONObject obj = new JSONObject();
        if (file.isFile()) {
            Scanner scan = null;
            try {
                scan = new Scanner(file);
                obj = new JSONObject(scan.nextLine());
                pos = obj.getInt("queuePos");

            } catch (FileNotFoundException ex) {
                //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("defaultdata file not found ..... 9000");
            } catch (JSONException ex) {
                //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("defaultdata format error..... 9001");
            } finally {
                if (scan != null) {
                    scan.close();
                }
            }
        }

        pos++;

        Formatter format = null;
        try {
            format = new Formatter(file);
            obj.put("queuePos", pos);
            format.format("%s", obj.toString());
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("defaultdata file not found ..... 9002");
        } catch (JSONException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("defaultdata format error..... 9003");
        } finally {
            if (format != null) {
                format.close();
            }
        }

        return pos;
    }
}
