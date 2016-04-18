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

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

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
          System.out.println("Key : " + lstrKeyValue[0] + " Value : " + lstrKeyValue[1]);
        }
      }
      p.waitFor();
      System.out.println("exit: " + p.exitValue());
      p.destroy();
    } catch (IOException | InterruptedException e) {
    }
    try {
      FetchUsingSigar();
    } catch (SigarException sex) {
      System.err.println("Exception : "+ sex);
    }
  }

  public static void FetchUsingSigar() throws SigarException {
    Sigar sigar = new Sigar();
    Cpu cpuInfo = new Cpu();
    cpuInfo.gather(sigar);
    while (true) {
      System.out.println("Total system cpu idle time : " + cpuInfo.getIdle());
      System.out.println("Total system cpu time servicing interrupts : " + cpuInfo.getIrq());
      System.out.println("Total system cpu nice time : " + cpuInfo.getNice());
      System.out.println("Total system cpu time servicing softirqs : " + cpuInfo.getSoftIrq());
      System.out.println("Total system cpu involuntary wait time : " + cpuInfo.getStolen());
      System.out.println("Total system cpu kernel time : " + cpuInfo.getSys());
      System.out.println("Total system cpu time : " + cpuInfo.getTotal());
      System.out.println("Total system cpu user time : " + cpuInfo.getUser());
      System.out.println("Total system cpu io wait time : " + cpuInfo.getWait());
    }
  }

}
