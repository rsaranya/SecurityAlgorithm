
/**
 * @author Saranya, Dixita
 * @CWID 20062589, 20061841
 * @Program Generates 10, 12 or 14 round keys for AES algorithm based on an
 * input key size. Uses the input key and input plain text to generate cipher
 * text
 */
/**
 * Encrypts the Input Text entered by the user with the Encryption Key entered
 * by the user.
 */
public class AesEncryption {

  /**
   * Constructor of the class. Sets the Algo mode as ENCRYPT.
   */
  public AesEncryption() {
    try {
      GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.ENCRYPT;
    } catch (Exception ex) {
      System.out.println("Exception in AesEncryption() in AesEncryption.java : "
              + ex.getMessage());
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
      // GlobalObjects.printRoundKeys();
      // Step 4 : Generate a Matrix of the user entered data or plain
      // text.
      GlobalObjects.generateDataMatrix(pstrInputText);

    } catch (Exception ex) {
      System.out.println("Exception in aesRoundKeys() in " + 
              "AesEncryption.java "  + ex.getMessage());
    }
  }

  /**
   * Computes data for one full round of AES encryption
   *
   * @param larRoundData : Intermediate Round Data
   * @param larRoundKey : Intermediate Round Keys
   * @param pintRoundCount : Round Number
   * @return larInputToNextStep: Intermediate Round Data to be used in the next
   * step.
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
      if (pintRoundCount != GlobalObjects.BYTE_ROUND.getValue(
              GlobalObjects.genumNoOfRounds)) {
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
      System.out.println("Exception in aes() in AesEncryption.java "
              + ex.getMessage());
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
      String[][] larRoundKey = new String[GlobalObjects.gintArrayRowSize]
              [GlobalObjects.gintArrayRowSize];
      String[][] larRoundData = new String[GlobalObjects.gintArrayRowSize]
              [GlobalObjects.gintArrayRowSize];

      // Copy the Input Data matrix into a local Matrix.
      for (int row = 0; row < GlobalObjects.gintArrayRowSize; row++) {
        for (int col = 0; col < GlobalObjects.gintArrayRowSize; col++) {
          larRoundData[row][col] = GlobalObjects.larInputData[row][col];
        }
      }
      int noOfRounds = GlobalObjects.BYTE_ROUND.getValue(
              GlobalObjects.genumNoOfRounds);
      // Take 4x4 matrix from 4x44 | 4x48 | 4x56 as round keys
      for (int increment = 0; increment < GlobalObjects.gintWArrayColSize
              && roundCount <= noOfRounds;) {
        // Copy the 4x4 Round Key matrix from the global 4x44 | 4x48 |
        // 4x56 matrix
        // into a local Matrix.
        for (int col = 0; col < 4 && increment
                < GlobalObjects.gintWArrayColSize; col++, increment++) {
          for (int row = 0; row < 4; row++) {
            larRoundKey[row][col] = GlobalObjects.larWMatrix[row][increment];
          }
        }
        // In the last round, perform only State XOR with the last key.
        if (roundCount == noOfRounds) {
          larRoundData = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);
          roundCount++;
        } else {
          // Compute data for other rounds.
          roundCount++;
          larRoundData = computeDataForEachRound(larRoundData,
                  larRoundKey, roundCount);
        }
      }
      // Print the data at the end of all the rounds
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
   * This function pads the text with hex value of '&' at the end of the string,
   * followed by hex value of the remaining string length.
   *
   * @param pstrPlainText contains the text that needs to be padded
   * @return the padded string
   */
  protected String addPadding(String pstrPlainText) {
    try {
      StringBuilder lstrPaddedText = new StringBuilder();
      if ((pstrPlainText.length() % GlobalObjects.gintInputBlockSize) != 0) {
        lstrPaddedText.append(pstrPlainText);
        lstrPaddedText.append(Integer.toHexString(
                (int) (GlobalObjects.lstrDelimiter)));
        int noCharsToPad;
        if (lstrPaddedText.length() % GlobalObjects.gintInputBlockSize != 0) {
          noCharsToPad = (((lstrPaddedText.length()
                  / GlobalObjects.gintInputBlockSize) + 1)
                  * GlobalObjects.gintInputBlockSize) - lstrPaddedText.length();
        } else {
          noCharsToPad = (lstrPaddedText.length()
                  / GlobalObjects.gintInputBlockSize)
                  * GlobalObjects.gintInputBlockSize - lstrPaddedText.length();
        }

        while (noCharsToPad > 0) {
          String hexVal = Integer.toHexString(noCharsToPad);
          if (hexVal.length() < 2) {
            lstrPaddedText.append("0");
          }
          lstrPaddedText.append(hexVal);
          noCharsToPad -= 2;
        }
        System.out.println("Padded str:" + lstrPaddedText.toString());
        return lstrPaddedText.toString();
      } else {
        System.out.println("No Padding added:" + lstrPaddedText.toString());
        return pstrPlainText;
      }
    } catch (Exception ex) {
      System.out.println("Exception in addPadding() :" + ex.getMessage());
      return "";
    }
  }
}
