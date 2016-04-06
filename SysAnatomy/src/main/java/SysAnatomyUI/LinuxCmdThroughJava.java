/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysAnatomyUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Saranya
 */
public class LinuxCmdThroughJava {

    public static void main(String args[]) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("cat /proc/cpuinfo");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            System.out.println("File print starts here");
            String[] lstrKeyValue = null;
            while ((s = br.readLine()) != null) {
                JSONObject cpuInfoObject = new JSONObject();
                if (!s.isEmpty()) {
                    lstrKeyValue = s.split(":");
                }
                
                if (lstrKeyValue != null & lstrKeyValue.length == 2) {
                    cpuInfoObject.put(lstrKeyValue[0], lstrKeyValue[1]);
                    System.out.println("Key : "+ lstrKeyValue[0] + " Value : "+ lstrKeyValue[1]);
                }
            }
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (IOException | InterruptedException e) {
        }
    }

}
