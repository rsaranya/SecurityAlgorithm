/**
 * @author Saranya
 * @CWID 20062589
 * @Program Generates 10 round keys for AES algorithm based on an input key
 */
public class AesCipher {

  private String lstrInputKey = "";

  //S-Box array
  private static final String[][] S_BOX = {
    {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B",
      "FE", "D7", "AB", "76"},
    {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF",
      "9C", "A4", "72", "C0"},
    {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1",
      "71", "D8", "31", "15"},
    {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2",
      "EB", "27", "B2", "75"},
    {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3",
      "29", "E3", "2F", "84"},
    {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39",
      "4A", "4C", "58", "CF"},
    {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F",
      "50", "3C", "9F", "A8"},
    {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21",
      "10", "FF", "F3", "D2"},
    {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D",
      "64", "5D", "19", "73"},
    {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14",
      "DE", "5E", "0B", "DB"},
    {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62",
      "91", "95", "E4", "79"},
    {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA",
      "65", "7A", "AE", "08"},
    {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F",
      "4B", "BD", "8B", "8A"},
    {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9",
      "86", "C1", "1D", "9E"},
    {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9",
      "CE", "55", "28", "DF"},
    {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F",
      "B0", "54", "BB", "16"}};

  //Round Key generation Array
  private static final String[][] ROUND_KEY_TABLE = {
    {"8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C",
      "D8", "AB", "4D", "9A"},
    {"2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA",
      "EF", "C5", "91", "39"},
    {"72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66",
      "CC", "83", "1D", "3A"},
    {"74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80",
      "1B", "36", "6C", "D8"},
    {"AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4",
      "B3", "7D", "FA", "EF"},
    {"C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A",
      "94", "33", "66", "CC"},
    {"83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10",
      "20", "40", "80", "1B"},
    {"36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97",
      "35", "6A", "D4", "B3"},
    {"7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2",
      "9F", "25", "4A", "94"},
    {"33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02",
      "04", "08", "10", "20"},
    {"40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC",
      "63", "C6", "97", "35"},
    {"6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3",
      "BD", "61", "C2", "9F"},
    {"25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB",
      "8D", "01", "02", "04"},
    {"08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A",
      "2F", "5E", "BC", "63"},
    {"C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39",
      "72", "E4", "D3", "BD"},
    {"61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A",
      "74", "E8", "CB", "8D"}};

  //Used to hold the entered key in the form of an Array
  private final String[][] larInputKey = new String[4][4];

  //Used to hold the W Matrix, which contains the user entered 
  //and the computed columns 
  private String[][] larWMatrix = new String[4][44];

  /**
   * Constructor to the Key generation class It accepts a hex key as its input
   *
   * @param pstrInputKey : Contains hex key from the user
   */
  public AesCipher(String pstrInputKey) {
    lstrInputKey = pstrInputKey;
  }

  /**
   * Makes the function calls to generate the round keys and print them
   */
  public final void aesRoundKeys() {
    try {
      //Step 1: Generate a Matrix of the user entered key.
      generateKeyMatrix();

      //Step 2: Generate the W matrix containing the key generated.
      generateWMatrix();

      //Step 3: Print first 10 round keys generated.
      printRoundKeys();
    } catch (Exception ex) {
      System.out.println("Exception in aesRoundKeys is : " + ex);
    }
  }

  /**
   * Generates W Matrix which contains the key generated at each round
   */
  private void generateWMatrix() {
    try {
      // Used to keep track of number of columns
      // for which a value has been assigned.
      int colCount = 0;

      // Step 1: Put the input key into W Matrix
      for (int row = 0; row < larInputKey.length; colCount = ++row) {
        System.arraycopy(larInputKey[row], 0, larWMatrix[row], 0, larInputKey[row].length);
      }

      //Step 2: Fill the remaining 40 columns with the generated keys 
      for (; colCount < 44; colCount++) {
        //Step 3: Check if the column is a multiple of 4
        // a. If the column is not a multiple of 4, 
        //   W(j) = W(j - 4) XOR W(j -1)
        // b. Else if the column is a multiple of 4,
        //   W(j) = W(j - 4) XOR W_new
        //   where W_new = [(Rcon(j) ^ S(W[1][j-1])) S(W[2][j-1]) S(W[3][j-1]) S(W[0][j-1])] 
        if (colCount % 4 != 0) {
          for (int row = 0; row < 4; row++) {
            larWMatrix[row][colCount]
              = computeXOR(larWMatrix[row][colCount - 4], larWMatrix[row][colCount - 1]);
          }
        } else {
          //Declare a W_new variable to hold the transformed matrix
          String[][] larWnew = new String[1][4];

          for (int col = 0; col < 4; col++) {
            // Combining Step 3b. (i) (ii) & (iii)
            // (i)  W_new 
            // = [W[0][j - 1]  W[1][j - 1]  W[2][j - 1]  W[3][j - 1]] 
            // (ii) W_new 
            // = [W[1][j - 1]  W[2][j - 1]  W[3][j - 1]  W[4][j - 1]]
            // (iii)W_new 
            // = [S(W[1][j-1]) S(W[2][j-1]) S(W[3][j-1]) S(W[0][j-1])]
            // Hence, 
            //W_new 
            // = [S(W[1][j-1]) S(W[2][j-1]) S(W[3][j-1]) S(W[0][j-1])]
            int wRowValue = col == 3 ? 0 : col + 1;
            String[] larSplitHexValue
              = larWMatrix[wRowValue][colCount - 1].split("");
            larWnew[0][col] = aesSBox(larSplitHexValue);
          }

          // Step 3b.(iv) Get the Round value, Rcon(j/4),from the Table
          // (v) EOR it with the first element in the array
          // i.e. : W_new  
          // = [(Rcon(j) XOR S(W[1][j-1])) S(W[2][j-1]) S(W[3][j-1]) S(W[0][j-1])]
          // The row element in larRoundTable[row][col] is calculated 
          // as (colCount/4)/16 = (colCount / 64)
          String lstrRconValue = aesRcon((int) Math.floor(colCount / 64), colCount / 4);

          larWnew[0][0] = computeXOR(lstrRconValue, larWnew[0][0]);

          // Step 3b. (vi) :  W(j) = W(j - 4) ^ W_new 
          for (int rowCount = 0; rowCount < 4; rowCount++) {
            larWMatrix[rowCount][colCount]
              = computeXOR(larWMatrix[rowCount][colCount - 4], larWnew[0][rowCount]);
          }
        }
      }
    } catch (Exception ex) {
      System.out.println("Exception in generateWMatrix is : " + ex);
    }
  }

  /**
   * Creates a 4 * 4 Matrix from the given input key
   */
  private void generateKeyMatrix() {
    try {
      int col = 0;
      for (int colCounter = 0; colCounter < (lstrInputKey.length() - 1);
        colCounter += 8, col++) {
        int row = 0;
        for (int rowCounter = colCounter; rowCounter < (colCounter + 8);
          rowCounter += 2, row++) {
          larInputKey[row][col]
            = lstrInputKey.substring(rowCounter, (rowCounter + 2));
        }
      }
    } catch (Exception ex) {
      System.out.println("Exception in generateKeyMatrix is : " + ex);
    }
  }

  /**
   * XOR's the input string values by converting them into Integers
   *
   * @param pstrFirstHexValue : Contains the first hex input value in string
   * format
   * @param pstrSecondHexValue : Contains the second hex input value in string
   * format
   * @return lstrXorResult : Contains the XOR of pstrFirstHexValue and
   * pstrSecondHexValue in string format
   */
  private String computeXOR(String pstrFirstHexValue, String pstrSecondHexValue) {
    try {
      // Convert the Hex value to Integer
      int lintFirst = Integer.parseInt(pstrFirstHexValue, 16);
      int lintSecond = Integer.parseInt(pstrSecondHexValue, 16);

      // Get the XOR of the integer value
      int lintXorResult = lintFirst ^ lintSecond;

      // Convert the integer to its equivalent Hex 
      // and return in string format
      String lstrXorResult = Integer.toHexString(lintXorResult);
      return lstrXorResult.length() == 1
        ? ("0" + lstrXorResult) : lstrXorResult;
    } catch (Exception ex) {
      System.out.println("Exception in computeXOR is : " + ex);
      return "";
    }
  }

  /**
   * Prints 10 round keys other than the entered key
   */
  private void printRoundKeys() {
    try {
      //Used to keep track of the number of keys to be printed
      int counter = 11;
      int col = 0;
      do {
        //Loop to print 16 hex values as 1 round key
        for (int colCount = 0; colCount < 4; col++, colCount++) {
          for (int row = 0; row < 4; row++) {
            System.out.print(larWMatrix[row][col]);
          }
        }
        System.out.println();
        counter--;
      } while (counter != 0);
    } catch (Exception ex) {
      System.out.println("Exception in printRoundKeys is : " + ex);
    }
  }

  /**
   * Fetches the hex value from the S-Box and Returns it in string format
   *
   * @param larSplitHexValue : Contains the hex value in string format which is
   * used to retrieve the equivalent value from the S-Box
   * @return : string value retrieved from the S-Box
   */
  private String aesSBox(String[] larSplitHexValue) {
    try {
      int sRowValue
        = Integer.parseInt(larSplitHexValue[0], 16);
      int sColValue
        = Integer.parseInt(larSplitHexValue[1], 16);
      return S_BOX[sRowValue][sColValue];
    } catch (Exception ex) {
      System.out.println("Exception in aesSBox is : " + ex);
      return "";
    }
  }

  /**
   * Returns a value from the Round-Key Table using the specified row and column
   * values
   *
   * @param pintRow : Contains the row number from which the value is to be
   * fetched from the Round key table.
   * @param pintCol : Contains the column number from which the value is to be
   * fetched from the Round key table.
   * @return : The retrieved value from the round table.
   */
  private String aesRcon(int pintRow, int pintCol) {
    try {
      return ROUND_KEY_TABLE[pintRow][pintCol];
    } catch (Exception ex) {
      System.out.println("Exception in aesRcon is : " + ex);
      return "";
    }
  }
}
