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

    private static String[] judgeName;

    static {   //judge name initialize
        Scanner scan = null;
        try {
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
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    public static String getJudgeName(int i) {
        return judgeName[i];
    }

    public static int getNumberOfJudge() {
        return judgeName.length;
    }

    public static boolean addJudge(String name) {
        if (name.equalsIgnoreCase("Enter Name")) {
            return false;
        }
        boolean flag = true;
        
        try {
            String str[] = new String[getNumberOfJudge() + 1];
            JSONArray arr = new JSONArray();
            JSONObject obj;
            for (int i = 0; i < getNumberOfJudge(); i++) {
                obj = new JSONObject();
                str[i] = getJudgeName(i);
                if (str[i].equalsIgnoreCase(name)) {
                    flag = false;
                }
                obj.put("judgename", str[i]);
                arr.put(obj);
            }
            if (flag) {
                str[getNumberOfJudge()] = name;
                obj = new JSONObject();
                obj.put("judgename", str[getNumberOfJudge()]);
                arr.put(obj);
                Formatter print = new Formatter(new File("C:\\ACMToDoList\\judgelist.txt"));
                print.format("%s", arr.toString());
                print.close();
                judgeName = str;
            }

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(AcmToDoList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            //Logger.getLogger(AcmToDoList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
