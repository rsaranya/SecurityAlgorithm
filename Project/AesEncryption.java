
/**
 * @author Saranya, Dixita
 * @CWID 20062589
 * @Program Generates 10 round keys for AES algorithm based on an input key.
 * Used the input key and input plain text to generate cipher text
 */
public class AesEncryption {

    public AesEncryption() {
        try {
            GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.ENCRYPT;
        } catch (Exception ex) {
            System.out.println("Exception in AesEncryption() in AesEncryption.java : " + ex.getMessage());
        }
    }

    /**
     * Makes the function calls to generate the round keys and print them
     *
     * @param pstrInputText : Contains the plain text from the input file.
     * @param pstrInputKey : Contains the input key from the input file.
     */
    public final void aesRoundKeys(String pstrInputText, String pstrInputKey) {
        try {
            // Step 1: Generate a Matrix of the user entered key.
            GlobalObjects.generateKeyMatrix(pstrInputKey);

            // Step 2: Generate the W matrix containing the key generated.
            GlobalObjects.generateWMatrix();

            // Step 3: Print first 10 round keys generated.
            //GlobalObjects.printRoundKeys();
            // Step 4 : Generate a Matrix of the user entered data or plain
            // text.
            GlobalObjects.generateDataMatrix(pstrInputText);

        } catch (Exception ex) {
            System.out.println("Exception in aesRoundKeys() in "
                    + "AesEncryption.java " + ex.getMessage());
        }
    }

    /**
     * Computes data for one full round of AES encryption
     *
     * @param larRoundData : Intermediate Round Data
     * @param larRoundKey : Intermediate Round Keys
     * @param pintRoundCount : Round Number
     * @return larInputToNextStep: Intermediate Round Data to be used in the
     * next step.
     */
    protected String[][] computeDataForEachRound(String[][] larRoundData,
            String[][] larRoundKey, int pintRoundCount) {
        String[][] larInputToNextStep;
        larInputToNextStep = null;
        try {

            // Step 1: Perform XOR of the data and round key.
            larInputToNextStep = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);

            // Step 2: Perform Nibble Substitution of the XOR
            if (larInputToNextStep != null) {
                larInputToNextStep = GlobalObjects.aesNibbleSub(larInputToNextStep);
            }

            // Step 3: Perform Shifting on Rows
            if (larInputToNextStep != null) {
                larInputToNextStep = GlobalObjects.aesShiftRow(larInputToNextStep);
            }

            // Step 4: Perform Mixing of column if not the last round
            if (pintRoundCount != GlobalObjects.BYTE_ROUND
                    .getValue(GlobalObjects.genumNoOfRounds)) {
                if (larInputToNextStep != null) {
                    larInputToNextStep = GlobalObjects.aesMixColumn(larInputToNextStep);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error in computeDataForEachRound() "
                    + "in AesEncryption.java: " + ex.getMessage());
        }
        return larInputToNextStep;
    }

    /**
     * Encrypts the plaintext with the key to produce a ciphertext.
     *
     * @param pstrInputText : Plain text taken from the input file
     * @param pstrInputKey : Key taken from the input file
     * @return
     */
    protected String aesEncrypt(String pstrInputText, String pstrInputKey) {
        try {
            GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.ENCRYPT;
            // If the Input key and Text is not empty
            // Process the keys and Input to generate cipher text.
            if (!(pstrInputText.trim().equals("")
                    && pstrInputKey.trim().equals(""))) {
                // Generate Matrices for the given Key and Text.
                aesRoundKeys(pstrInputText, pstrInputKey);
                return EncryptUsingAes();
            }
        } catch (Exception ex) {
            System.out.println("Exception in aes() in AesEncryption.java " + ex.getMessage());
        }
        return "";
    }

    /**
     * This function gets the round key and round data and performs encryption
     * operations based on the round number.
     *
     * @return the encrypted text in string format.
     */
    private String EncryptUsingAes() {
        try {
            int roundCount = 0;
            String[][] larRoundKey = new String[GlobalObjects.gintArrayRowSize][GlobalObjects.gintArrayRowSize];
            String[][] larRoundData = new String[GlobalObjects.gintArrayRowSize][GlobalObjects.gintArrayRowSize];

            // Copy the Input Data matrix into a local Matrix.
            for (int row = 0; row < GlobalObjects.gintArrayRowSize; row++) {
                for (int col = 0; col < GlobalObjects.gintArrayRowSize; col++) {
                    larRoundData[row][col] = GlobalObjects.larInputData[row][col];
                }
            }
            int noOfRounds = GlobalObjects.BYTE_ROUND
                    .getValue(GlobalObjects.genumNoOfRounds);
            // Take 4x4 matrix from 4x44 as round keys
            for (int increment = 0; increment < GlobalObjects.gintWArrayColSize
                    && roundCount <= noOfRounds;) {
                // Copy the 4x4 Round Key matrix from the global 4x44 matrix
                // into a local Matrix.
                for (int col = 0; col < 4
                        && increment < GlobalObjects.gintWArrayColSize; col++, increment++) {
                    for (int row = 0; row < 4; row++) {
                        larRoundKey[row][col] = GlobalObjects.larWMatrix[row][increment];
                    }
                }
                if (roundCount == noOfRounds) {
                    larRoundData = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);
                    roundCount++;
                } else {
                    roundCount++;
                    larRoundData = computeDataForEachRound(larRoundData, larRoundKey,
                            roundCount);
                }
            }
            if (larRoundData != null) {
                return GlobalObjects.printRoundData(larRoundData);
            }
        } catch (Exception ex) {
            System.out.println("Exception in EncryptUsingAes() in "
                    + "AesEncryption.java:" + ex.getMessage());
        }
        return "";
    }

    /**
     * This function pads the text with hex value of '&' at the end of the
     * string, followed by hex value of the remaining string length.
     *
     * @param plaintext contains the text that needs to be padded
     * @return the padded string
     */
    protected String addPadding(String plaintext) {
        try {
            StringBuilder str = new StringBuilder();
            if ((plaintext.length() % GlobalObjects.gintInputBlockSize) != 0) {
                //len 63..63/32=1+1*32-63=1
                str.append(plaintext);
                str.append(Integer.toHexString((int)(GlobalObjects.lstrDelimiter)));
                int noCharsToPad;
                if (str.length() % GlobalObjects.gintInputBlockSize != 0) {
                    noCharsToPad = (((str.length() / GlobalObjects.gintInputBlockSize) + 1)
                            * GlobalObjects.gintInputBlockSize) - str.length();
                } else {
                    noCharsToPad = (str.length() / GlobalObjects.gintInputBlockSize)
                            * GlobalObjects.gintInputBlockSize - str.length();
                }

                //String paddedStr = Integer.toHexString(noCharsToPad);
                while (noCharsToPad > 0) {
                    String hexVal = Integer.toHexString(noCharsToPad);
                    if (hexVal.length() < 2) {
                        str.append("0");
                    }
                    str.append(hexVal);
                    noCharsToPad -= 2;
                }
                System.out.println("Padded str:" + str.toString());
                return str.toString();
            } else {
                System.out.println("No Padding added:" + str.toString());
                return plaintext;
            }
        } catch (Exception ex) {
            System.out.println("Exception in addPadding():" + ex.getMessage());
            return "";
        }

    }
}
