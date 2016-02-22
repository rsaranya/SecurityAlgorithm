/**
 * @author Saranya
 * @CWID 20062589
 * @Program Utilizes the AES Key generation class to generate round keys
 *     from an 
 */
import java.util.*;

public class Driver {

    /**
     * Is the Entry point to the program
     * Fetches the key from the user and calls the Round key generator.
     * 
     * @param args the command line argument
     */
    public static void main(String[] args) {
        try {
            System.out.println("Please Enter the first key : ");
            Scanner sc = new Scanner(System.in);
            String lstrInputKey = sc.nextLine();

            AesCipher aesKeyGen = new AesCipher(lstrInputKey);
            aesKeyGen.aesRoundKeys();
        } catch (Exception ex) {
            System.out.println("Exception in main is : " + ex);
        }
    }
}
