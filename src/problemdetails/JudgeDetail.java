/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemdetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kazi Nayeem
 */
public class JudgeDetail {

    private static String[] judgeName = new String[0];

    static {   //judge name initialize
        readFromFile();
    }

    /**
     * get specific index judge name
     *
     * @param i index of judge
     * @return judge name of i index
     */
    public static String getJudgeName(int i) {
        return judgeName[i];
    }

    /**
     * get number of judge
     *
     * @return number of judge
     */
    public static int getNumberOfJudge() {
        return judgeName.length;
    }

    /**
     * add new judge named as name and save all data of judge. if successfully
     * added return true, otherwise false
     *
     * @param name new judge name
     * @return if successfully added return true, otherwise false
     */
    public static boolean addJudge(String name) {
        name = name.trim();
        if (name.equalsIgnoreCase("Enter Name") || name.equals("")) {
            return false;
        }
        String str[] = new String[getNumberOfJudge() + 1];

        for (int i = 0; i < getNumberOfJudge(); i++) {

            str[i] = getJudgeName(i);
            if (str[i].equalsIgnoreCase(name)) {
                return false;
            }
        }

        str[getNumberOfJudge()] = name;

        judgeName = str;

        for (int i = judgeName.length - 2; i > 0; i--) {
            if (judgeName[i].compareToIgnoreCase(judgeName[i + 1]) > 0) {
                String temp = judgeName[i];
                judgeName[i] = judgeName[i + 1];
                judgeName[i + 1] = temp;
            }
        }

        return writeToFile();
    }

    /**
     * remove specific judge del from judge list. if successfully removed return
     * true, otherwise false.
     *
     * @param del judge name to remove
     * @return if successfully removed return true, otherwise false
     */
    public static boolean removeJudge(int del) {
        if (del < 1) {
            return false;
        }
        String str[] = new String[getNumberOfJudge() - 1];

        for (int i = 0; i < del; i++) {
            str[i] = getJudgeName(i);
        }

        for (int i = del + 1; i < getNumberOfJudge(); i++) {
            str[i - 1] = getJudgeName(i);
        }

        judgeName = str;

        return writeToFile();
    }

    /**
     * save all data related to judge save to "C:\\ACMToDoList\\judgelist.txt"
     * file and return true if successfully write to file otherwise false
     *
     * @return return true if successfully write all data to file otherwise
     * false
     */
    private static boolean writeToFile() {
        JSONArray arr = new JSONArray();
        JSONObject obj;
        for (int i = 0; i < getNumberOfJudge(); i++) {
            try {
                obj = new JSONObject();
                obj.put("judgename", getJudgeName(i));
                arr.put(obj);
            } catch (JSONException ex) {
                //Logger.getLogger(JudgeDetail.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Judge add failed.... format error.....1006");
                return false;
            }
        }
        Formatter print;
        try {
            print = new Formatter(new File("C:\\ACMToDoList\\judgelist.txt"));
            print.format("%s", arr.toString());
            print.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(JudgeDetail.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Judge add failed.... file not found.....1005");
            return false;
        }
        return true;
    }

    /**
     * read all information about judge from "C:\\ACMToDoList\\judgelist.txt"
     * file if file not found then two judge add
     */
    private static void readFromFile() {
        Scanner scan = null;
        try {
            if (!(new File("C:\\ACMToDoList").isDirectory())) {
                System.err.println("directory");
                new File("C:\\ACMToDoList").mkdirs();
            }
            scan = new Scanner(new File("C:\\ACMToDoList\\judgelist.txt"));

            String input = scan.nextLine();
            JSONArray arr = new JSONArray(input);
            judgeName = new String[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                JSONObject ob = arr.getJSONObject(i);
                judgeName[i] = ob.getString("judgename");
            }
        } catch (JSONException ex) {
            System.err.println("Judge Name can not read. format not match 1001");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Judge Name can not read. file missing or can\'t open 1002");
            addJudge("Select One");
            addJudge("UVa");
            addJudge("LightOJ");
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }
}
