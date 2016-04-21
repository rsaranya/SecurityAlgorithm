
/**
 * @author Saranya
 * @CWID 20062589
 * @Program Utilizes the AES Key generation class to generate round keys from an
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
      //Fetch the file name from the console
      Scanner sc = new Scanner(System.in);
      String lstrInputKey = sc.nextLine();

      //Check if the file has valid key
      if (lstrInputKey.isEmpty()) {
        System.out.println("No key found to process. Please Enter a valid key in the file");
        System.exit(0);
      } else if ((lstrInputKey.length() % 4 != 0) || (lstrInputKey.length() == 34)) {
        System.out.println("Please enter a key of valid length");
        System.exit(0);
      }
      //Call the aesKeyGen class and pass the input key as string to the constructor
      AesCipher aesKeyGen = new AesCipher(lstrInputKey);

      //Call the aesRoundKeys function to process the input key
      //and generate 10 more round keys
      aesKeyGen.aesRoundKeys();
    } catch (Exception ex) {
      System.out.println("Exception in main is : " + ex);
    }
  }
}