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
 *
 * @author Kazi Nayeem
 */
public class SimpleProblem {

    private String judgeName;
    private String problemID;
    private String problemName;
    private String addDate, solveDate;
    private Boolean solved, inqueue, mark;
    int queuePosition;

    public SimpleProblem(String judge, String probID, String pName) {
        judgeName = judge;
        problemID = probID;
        problemName = pName;

        addDate = MyDateClass.getCurrentBoth();
        solveDate = MyDateClass.invalidBoth;

        solved = false;
        inqueue = false;
        mark = false;

        queuePosition = -1;
    }

    public SimpleProblem(String judge, String probID, String pName, String addedDate) {
        judgeName = judge;
        problemID = probID;
        problemName = pName;

        addDate = addedDate;
        solveDate = MyDateClass.invalidBoth;

        solved = false;
        inqueue = false;
        mark = false;

        queuePosition = -1;
    }

    public SimpleProblem(String judge, String probID, String pName, String addedDate, String solvedDate) {
        judgeName = judge;
        problemID = probID;
        problemName = pName;

        addDate = addedDate;
        solveDate = solvedDate;

        solved = false;
        inqueue = false;
        mark = false;

        queuePosition = -1;
    }
    
    public SimpleProblem(String judge, String probID, String pName, String addedDate, String solvedDate, Boolean solv, Boolean inq, int quePos) {
        judgeName = judge;
        problemID = probID;
        problemName = pName;

        addDate = addedDate;
        solveDate = solvedDate;

        solved = solv;
        inqueue = inq;
        mark = false;

        queuePosition = quePos;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public String getProblemID() {
        return problemID;
    }

    public String getProblemName() {
        return problemName;
    }

    public String getAddedDate() {
        return addDate;
    }

    public String getSolvedDate() {
        return solveDate;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved() {
        if (solved) {
            return;
        }
        solveDate = MyDateClass.getCurrentBoth();
        solved = true;
    }

    public boolean isInQueue() {
        return inqueue;
    }

    public void setQueue(int pos) {
        inqueue = true;
        queuePosition = pos;
    }

    public boolean isMarked() {
        return mark;
    }

    public void setMarked(boolean flag) {
        mark = flag;
    }

    public String getAddedDateDay() {
        return addDate.substring(11);
    }

    public String getSolvedDateDay() {
        return solveDate.substring(11);
    }
    
    public int queuePos(){
        return queuePosition;
    }

    public String[] getTableModel() {
        String[] str = new String[5];
        str[0] = getJudgeName();
        str[1] = getProblemID();
        str[2] = getProblemName();
        str[3] = getAddedDateDay();
        str[4] = getSolvedDateDay();

        return str;
    }

    public void saveToFile() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("judge", getJudgeName());
            obj.put("problemID", getProblemID());
            obj.put("problemName", getProblemName());
            obj.put("addDate", getAddedDate());
            obj.put("solveDate", getSolvedDate());
            obj.put("isSolved", isSolved());
            obj.put("isInqueue", isInQueue());
            obj.put("queuePos", queuePos());
            
            Formatter file = new Formatter(new File("C:\\ACMToDoList\\simpleproblem\\"+getJudgeName()+getProblemID()+".txt"));
            file.format("%s", obj.toString());
            file.close();
            
        } catch (JSONException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.JSONObject error  simpleproblem savetofile");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(SimpleProblem.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problem write failed.File error  simpleproblem savetofile");
        }
        
    }
    
    public static SimpleProblem getFromFile(String judgeName, String problemID) {
        try {
            Scanner file = new Scanner(new File("C:\\ACMToDoList\\simpleproblem\\"+judgeName+problemID+".txt"));
            file.close();
            String input = file.nextLine();
            JSONObject obj = new JSONObject(input);
            SimpleProblem prob = new SimpleProblem(obj.getString("judge"), obj.getString("problemID"), obj.getString("problemName"), 
                    obj.getString("addDate"), obj.getString("solveDate"), obj.getBoolean("isSolved"), obj.getBoolean("isInqueue"), obj.getInt("queuePos"));
            
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
}
