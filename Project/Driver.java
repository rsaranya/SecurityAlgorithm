
/**
 * @author Saranya, Dixita
 * @CWID 20062589,20061841
 * @Program Utilizes the AES Key generation class to generate round keys and
 * create a cipher text from a given plain text
 */
import java.util.*;

public class Driver {

    private static Scanner lscanInput;

    /**
     * Is the Entry point to the program Fetches the key from the user and calls
     * the Round key generator.
     *
     * @param args the command line argument
     */
    public static void main(String[] args) {
        try {
            lscanInput = new Scanner(System.in);
            String inputKey = "";
            if (lscanInput.hasNext()) {
                inputKey = lscanInput.nextLine();
            } else {
                System.out.println("Input Key not found.");
                System.exit(0);
            }
            String inputPlainText = "";

            if (lscanInput.hasNext()) {
                inputPlainText = lscanInput.nextLine();
            } else {
                System.out.println("Input Text not found.");
                System.exit(0);
            }
            // If condition makes sure that user has entered
            // exactly 32 hexadecimal digits

            if ((inputKey.matches("[0-9A-F]+$"))
                    && (inputPlainText.matches("[0-9A-F]+$"))) {

                // Add padding if the size is less than the required size
                // Size will be 32- , 48- , 64 - bits
                int lintInputKeySize = inputKey.length();

                // RowSize : size(in bits) / 2 => size(in bytes)
                // RowSize : size(in bytes)/ 4 => No of row size,
                // when column size for each
                // key will be set at 4.
                GlobalObjects.gintArrayRowSize = 4;

                // Number of Rounds based on Bits size
                GlobalObjects.genumNoOfRounds = GlobalObjects.BYTE_ROUND
                        .getEnum(lintInputKeySize);

                GlobalObjects.gintArrayColSize = (lintInputKeySize / 8);

                GlobalObjects.gintWArrayColSize = 4 * (GlobalObjects.BYTE_ROUND
                        .getValue(GlobalObjects.genumNoOfRounds) + 1);

                // Call the aesKeyGen class and pass the plain text
                // and input key as string to the constructor
                AesEncryption aesKeyGen = new AesEncryption();

                // Call the AES function to process the input key
     
                int startIndex = 0;
                int loopCount = inputPlainText.length() / GlobalObjects.gintInputBlockSize + 1;
                String strBlock[] = new String[loopCount];
                int blockSent = 0;
                for (int noOfBlocks = 0; noOfBlocks < loopCount; noOfBlocks++) {
                    int minLength = Math.min(GlobalObjects.gintInputBlockSize, 
                            (inputPlainText.length() - blockSent * GlobalObjects.gintInputBlockSize));
                    strBlock[noOfBlocks] = inputPlainText.substring(startIndex,startIndex+ minLength);
                    blockSent++;

                    String temp = aesKeyGen.addPadding(strBlock[noOfBlocks]);
                    String lstrCipherText = aesKeyGen.aes(temp, inputKey);
                    String decryptMsgList = AesDecryption.DecryptUsingAes(lstrCipherText);
                    AesDecryption.removePadding(decryptMsgList);
                    startIndex += GlobalObjects.gintInputBlockSize;

                }

            } else {
                // If user input is incorrect, terminate the program
                System.out.println("Invalid Values in the Input File.");
                System.exit(0);
            }

        } catch (Exception ex) {
            System.out.println("Exception in main : " + ex.getMessage());
        }
    }

}
