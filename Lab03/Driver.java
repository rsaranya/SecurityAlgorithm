/**
 * @author Saranya
 * @CWID 20062589
 * @Program Utilizes the AES Key generation class to generate round keys and
 * create a cipher text from a given plain text
 */
import java.util.*;

public class Driver {

  /**
   * Is the Entry point to the program Fetches the key from the user and calls
   * the Round key generator.
   *
   * @param args the command line argument
   */
  public static void main(String[] args) {
    try {
      //Takes input from user and stores in inputkey
      Scanner in1 = new Scanner(System.in);
      String inputKey = "";
      if (in1.hasNext()) {
        inputKey = in1.nextLine();
      } else {
        System.out.println("Input Key not found.");
        System.exit(0);
      }
      String inputPlainText = "";

      if (in1.hasNext()) {
        inputPlainText = in1.nextLine();
      }else {
        System.out.println("Input Text not found.");
        System.exit(0);
      }

      //If condition makes sure that user has entered 
      //exactly 32 hexadecimal digits
      if ((inputKey.matches("[0-9A-F]{32}"))
        && (inputPlainText.matches("[0-9A-F]{32}"))) {

        //Call the aesKeyGen class and pass the plain text 
        //and input key as string to the constructor
        AesCipher aesKeyGen = new AesCipher();

        //Call the aes function to process the input key
        //and generate 10 more round keys
        aesKeyGen.aes(inputPlainText, inputKey);

      } else {
        //If user input is incorrect, terminate the program
        System.out.println("Invalid Values in the Input File.");
        System.exit(0);
      }
    } catch (Exception ex) {
      System.out.println("Exception in main : " + ex.getMessage());
    }
  }
}