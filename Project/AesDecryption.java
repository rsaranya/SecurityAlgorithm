/**
 * @author Saranya, Dixita
 * @CWID 20062589
 * @Program Utilizes the AES Key generation class to generate round keys and
 * create a cipher text from a given plain text
 */
public class AesDecryption {

    public static String DecryptUsingAes(String pstrCipherText) {
        GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.DECRYPT;

        GlobalObjects.generateDataMatrix(pstrCipherText);

        return decryptWithRoundKeys();
    }

    private static String decryptWithRoundKeys() {
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

            for (int lintDecrement = GlobalObjects.gintWArrayColSize
                    - 4; lintDecrement >= 0
                    && roundCount <= noOfRounds; lintDecrement -= 8) {
                // Copy the 4x4 Round Key matrix from the global 4x44 matrix
                // into a local Matrix.
                for (int col = 0; col < 4
                        && lintDecrement < GlobalObjects.gintWArrayColSize; col++, lintDecrement++) {
                    for (int row = 0; row < 4; row++) {
                        larRoundKey[row][col] = GlobalObjects.larWMatrix[row][lintDecrement];
                    }
                }

                if (roundCount == 0 || roundCount == GlobalObjects.BYTE_ROUND
                        .getValue(GlobalObjects.genumNoOfRounds)) {

                    // Step 1: Perform XOR of the data and round key.
                    larRoundData = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);

                    if (roundCount == 0) {
                        // Step 3: Perform Shifting on Rows
                        if (larRoundData != null) {
                            larRoundData = GlobalObjects.aesShiftRow(larRoundData);
                        }

                        // Step 2: Perform Nibble Substitution of the XOR
                        if (larRoundData != null) {
                            larRoundData = GlobalObjects.aesNibbleSub(larRoundData);
                        }
                    }
                    roundCount++;
                } else {
                    larRoundData = computeDataForEachRound(larRoundData, larRoundKey,
                            roundCount);
                    roundCount++;
                }
            }
            if (larRoundData != null) {
                return GlobalObjects.printRoundData(larRoundData);
            }
            return "";
        } catch (Exception ex) {
            System.out.println("Exception in decryptWithRoundKeys");
            return "";
        }
    }

    private static String[][] computeDataForEachRound(String[][] larRoundData,
            String[][] larRoundKey, int pintRoundCount) {

        String[][] larInputToNextStep;
        larInputToNextStep = larRoundData;
        try {
            // Step 1: Perform XOR of the data and round key.
            larInputToNextStep = GlobalObjects.aesStateXOR(larInputToNextStep,
                    larRoundKey);

            // Step 4: Perform Mixing of column if not
            // the last round
            if (pintRoundCount != GlobalObjects.BYTE_ROUND
                    .getValue(GlobalObjects.genumNoOfRounds)) {
                if (larInputToNextStep != null) {
                    larInputToNextStep = GlobalObjects.aesMixColumn(larInputToNextStep);
                }
            }

            // Step 3: Perform Shifting on Rows
            if (larInputToNextStep != null) {
                larInputToNextStep = GlobalObjects.aesShiftRow(larInputToNextStep);
            }

            // Step 2: Perform Nibble Substitution of the XOR
            if (larInputToNextStep != null) {
                larInputToNextStep = GlobalObjects.aesNibbleSub(larInputToNextStep);
            }
        } catch (Exception ex) {
            System.out.println("Exception in decryptWithRoundKeys");
        }
        return larInputToNextStep;
    }

    public static String removePadding(String paddedText) {
        try {
            String str = "";
            if ((paddedText.length() % GlobalObjects.gintInputBlockSize) == 0) {
                String padHex=Integer.toHexString((int)'&');
               
                if (paddedText.contains(padHex)) {
                    int index = paddedText.lastIndexOf(padHex);
                    str = paddedText.substring(0, index);
                }
                System.out.println("Padding removed: " + str);
                return str;
            } else {
                return "";
            }
        } catch (Exception ex) {
            System.out.println("Exception in removePadding():" + ex.getMessage());
            return "";
        }

    }
}
