/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SysAnatomyBL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Saranya
 */
public class LinuxCmdThroughJava {

  public static void main(String args[]) {
    String s;
    Process p;
    try {
      p = Runtime.getRuntime().exec("ls -aF");
      BufferedReader br = new BufferedReader(
        new InputStreamReader(p.getInputStream()));
      while ((s = br.readLine()) != null) {
        System.out.println("line: " + s);
      }
      p.waitFor();
      System.out.println("exit: " + p.exitValue());
      p.destroy();
    } catch (IOException | InterruptedException e) {
    }
  }
}
