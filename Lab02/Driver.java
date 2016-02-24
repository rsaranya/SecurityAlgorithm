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
      Scanner sc = new Scanner(System.in);
      String lstrInputKey = sc.nextLine();

      if(lstrInputKey.isEmpty())
      {
        System.out.println("No key found to process. Please Enter a valid key in the file");
        System.exit(0);
      }else if((lstrInputKey.length() % 4 != 0) || (lstrInputKey.length() == 34) )
      {
        System.out.println("Please enter a key of valid length");
        System.exit(0);
      }
      AesCipher aesKeyGen = new AesCipher(lstrInputKey);
      aesKeyGen.aesRoundKeys();
    } catch (Exception ex) {
      System.out.println("Exception in main is : " + ex);
    }
  }
}
