
import java.util.*;

public class Driver {
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
