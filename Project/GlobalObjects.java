/**
 * 
 * @author Saranya, Dixita
 * @CWID
 * @Program
 *
 */
public class GlobalObjects {
	
	public static enum ALGO_MODE {
		ENCRYPT, DECRYPT, NONE
	}
	
	public static enum BYTE_ROUND {
		BYTE_32(10), BYTE_48(12), BYTE_64(14);
		
		private int value;
		
		private BYTE_ROUND(int value) {
			this.value = value;
		}
		
		public static int getValue(BYTE_ROUND pbyteRound) {
			for (BYTE_ROUND lbyteRound : BYTE_ROUND.values()) {
				if (lbyteRound.compareTo(pbyteRound) == 0)
					return lbyteRound.value;
			}
			return 0;
		}
		
		public static BYTE_ROUND getEnum(int pintBytes) {
			switch (pintBytes) {
				case 32:
					return BYTE_32;
				case 48:
					return BYTE_48;
				case 64:
					return BYTE_64;
				default:
					return BYTE_32;
			}
		}
	}
	
	public static ALGO_MODE genumAlgoMode = ALGO_MODE.NONE;
	
	public static BYTE_ROUND genumNoOfRounds = BYTE_ROUND.BYTE_32;
	
	public static int gintArrayRowSize = 0;
	
	public static int gintArrayColSize = 0;
	
	public static int gintWArrayColSize = 0;
	
	public static String[][] larInputKey = null;
	
	// Used to hold the entered Data in the form of an Array
	public static String[][] larInputData = null;
	
	// Used to hold the W Matrix, which contains the user entered
	// and the computed columns
	public static String[][] larWMatrix = null;
	
	// S-Box array
	private static final String[][] S_BOX = {
	    { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B",
	        "FE", "D7", "AB", "76" },
	    { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF",
	        "9C", "A4", "72", "C0" },
	    { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1",
	        "71", "D8", "31", "15" },
	    { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2",
	        "EB", "27", "B2", "75" },
	    { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3",
	        "29", "E3", "2F", "84" },
	    { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39",
	        "4A", "4C", "58", "CF" },
	    { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F",
	        "50", "3C", "9F", "A8" },
	    { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21",
	        "10", "FF", "F3", "D2" },
	    { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D",
	        "64", "5D", "19", "73" },
	    { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14",
	        "DE", "5E", "0B", "DB" },
	    { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62",
	        "91", "95", "E4", "79" },
	    { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA",
	        "65", "7A", "AE", "08" },
	    { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F",
	        "4B", "BD", "8B", "8A" },
	    { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9",
	        "86", "C1", "1D", "9E" },
	    { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9",
	        "CE", "55", "28", "DF" },
	    { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F",
	        "B0", "54", "BB", "16" } };
	
	// Inverse S-Box array
	private static final String[][] INV_S_BOX = {
	    { "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3", "9E",
	        "81", "F3", "D7", "FB" },
	    { "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44",
	        "C4", "DE", "E9", "CB" },
	    { "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B",
	        "42", "FA", "C3", "4E" },
	    { "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49",
	        "6D", "8B", "D1", "25" },
	    { "72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC",
	        "5D", "65", "B6", "92" },
	    { "6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15", "46", "57",
	        "A7", "8D", "9D", "84" },
	    { "90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4", "58", "05",
	        "B8", "B3", "45", "06" },
	    { "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF", "BD", "03",
	        "01", "13", "8A", "6B" },
	    { "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2", "CF", "CE",
	        "F0", "B4", "E6", "73" },
	    { "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37", "E8",
	        "1C", "75", "DF", "6E" },
	    { "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E",
	        "AA", "18", "BE", "1B" },
	    { "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE",
	        "78", "CD", "5A", "F4" },
	    { "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59",
	        "27", "80", "EC", "5F" },
	    { "60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F",
	        "93", "C9", "9C", "EF" },
	    { "A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C",
	        "83", "53", "99", "61" },
	    { "17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69", "14", "63",
	        "55", "21", "0C", "7D" } };
	
	// Round Key generation Array
	private static final String[][] ROUND_KEY_TABLE = {
	    { "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C",
	        "D8", "AB", "4D", "9A" },
	    { "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA",
	        "EF", "C5", "91", "39" },
	    { "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66",
	        "CC", "83", "1D", "3A" },
	    { "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80",
	        "1B", "36", "6C", "D8" },
	    { "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4",
	        "B3", "7D", "FA", "EF" },
	    { "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A",
	        "94", "33", "66", "CC" },
	    { "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10",
	        "20", "40", "80", "1B" },
	    { "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97",
	        "35", "6A", "D4", "B3" },
	    { "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2",
	        "9F", "25", "4A", "94" },
	    { "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02",
	        "04", "08", "10", "20" },
	    { "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC",
	        "63", "C6", "97", "35" },
	    { "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3",
	        "BD", "61", "C2", "9F" },
	    { "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB",
	        "8D", "01", "02", "04" },
	    { "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A",
	        "2F", "5E", "BC", "63" },
	    { "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39",
	        "72", "E4", "D3", "BD" },
	    { "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A",
	        "74", "E8", "CB", "8D" } };
	
	// The fixed matrix used for column mixing
	static String[][] GALOIS_MATRIX = { { "02", "03", "01", "01" },
	    { "01", "02", "03", "01" }, { "01", "01", "02", "03" },
	    { "03", "01", "01", "02" } };
	
	// The fixed matrix used for column mixing
	static String[][] INV_GALOIS_MATRIX = { { "0E", "0B", "0D", "09" },
	    { "09", "0E", "0B", "0D" }, { "0D", "09", "0E", "0B" },
	    { "0B", "0D", "09", "0E" } };
	
	/**
	 * Returns a value from the Round-Key Table using the specified row and column
	 * values
	 *
	 * @param pintRow
	 *          : Contains the row number from which the value is to be fetched
	 *          from the Round key table.
	 * @param pintCol
	 *          : Contains the column number from which the value is to be fetched
	 *          from the Round key table.
	 * @return : The retrieved value from the round table.
	 */
	public static String aesRcon(int pintRow, int pintCol) {
		try {
			return ROUND_KEY_TABLE[pintRow][pintCol];
		} catch (Exception ex) {
			System.out.println("Exception in aesRcon is : " + ex.getMessage());
			return "";
		}
	}
	
	/**
	 * Shifts the value by 1 and performs the desired operations for matrix
	 * multiplication.
	 *
	 * @param pstrHexValue
	 *          : Value for matrix multiplication.
	 * @return : Value that has been bit shifted.
	 */
	public static String shiftBit(String pstrHexValue) {
		// Binary value in string format for the input integer
		String binary = Integer.toBinaryString(Integer.parseInt(pstrHexValue, 16));
		/*
		 * while (binary.length() < 8) { binary = "0" + binary; }
		 */
		switch (genumAlgoMode) {
			case ENCRYPT:
				// If the length of the binary string is less than 8 then pad.
				while (binary.length() < 8) {
					binary = "0" + binary;
				}
				break;
			case DECRYPT: // If the length of the binary string is less than 8
				// then pad.
				while (binary.length() < 8) {
					binary = binary + "0";
				}
				break;
			case NONE:
			default:
				binary = "";
		}
		
		// Shift to left by 1
		int intvalueIn = Integer.parseInt(binary, 2);
		String shiftedBinary = "";
		/*
		 * shiftedBinary = Integer.toBinaryString(intvalueIn << 1); shiftedBinary =
		 * (shiftedBinary.length() > 8 ? shiftedBinary.substring(1) :
		 * shiftedBinary);
		 */
		
		switch (genumAlgoMode) {
			case ENCRYPT:
				shiftedBinary = Integer.toBinaryString(intvalueIn << 1);
				shiftedBinary = (shiftedBinary.length() > 8 ? shiftedBinary.substring(1)
				    : shiftedBinary);
				break;
			case DECRYPT:
				shiftedBinary = Integer.toBinaryString(intvalueIn >> 1);
				shiftedBinary = (shiftedBinary.length() > 8 ? shiftedBinary
				    .substring(shiftedBinary.length() - 8, shiftedBinary.length())
				    : shiftedBinary);
				break;
			case NONE:
			default:
				shiftedBinary = "";
				break;
			
		}
		
		// check if upper significant bit is 1,
		// then XOR by 27
		// else return the original value
		if (binary.substring(0, 1).equals("1")) {
			String constant = Integer.toString(27, 2);
			while (constant.length() < 8) {
				constant = "0" + constant;
			}
			return computeXOR(Integer.toHexString(Integer.parseInt(shiftedBinary, 2)),
			    Integer.toHexString(Integer.parseInt(constant, 2)));
		} else {
			return Integer.toHexString(Integer.parseInt(shiftedBinary, 2));
		}
	}
	
	/**
	 * XOR's the input string values by converting them into Integers
	 *
	 * @param pstrFirstHex
	 *          : Contains the first hex input value in string format
	 * @param pstrSecondHex
	 *          : Contains the second hex input value in string format
	 * @return lstrXorResult : Contains the XOR of pstrFirstHexValue and
	 *         pstrSecondHexValue in string format
	 */
	public static String computeXOR(String pstrFirstHex, String pstrSecondHex) {
		try {
			// Convert the Hex value to Integer
			int lintFirst = Integer.parseInt(pstrFirstHex, 16);
			int lintSecond = Integer.parseInt(pstrSecondHex, 16);
			
			// Get the XOR of the integer value
			int lintXorResult = lintFirst ^ lintSecond;
			
			// Convert the integer to its equivalent Hex
			// and return in string format
			String lstrXorResult = Integer.toHexString(lintXorResult);
			return lstrXorResult.length() == 1 ? ("0" + lstrXorResult)
			    : lstrXorResult;
		} catch (Exception ex) {
			System.out.println("Exception in computeXOR is : " + ex.getMessage());
			return "";
		}
	}
	
	/**
	 * Fetches the hex value from the S-Box and Returns it in string format
	 *
	 * @param larSplitHexValue
	 *          : Contains the hex value in string format which is used to
	 *          retrieve the equivalent value from the S-Box
	 * @return : string value retrieved from the S-Box
	 */
	public static String aesSBox(String[] larSplitHexValue) {
		try {
			int sRowValue = Integer.parseInt(larSplitHexValue[0], 16);
			int sColValue = Integer.parseInt(larSplitHexValue[1], 16);
			
			switch (genumAlgoMode) {
				case ENCRYPT:
					return S_BOX[sRowValue][sColValue];
				case DECRYPT:
					return INV_S_BOX[sRowValue][sColValue];
				case NONE:
				default:
					return "00";
			}
		} catch (Exception ex) {
			System.out.println("Exception in aesSBox is : " + ex.getMessage());
			return "";
		}
	}
	
	/**
	 * Substitutes each element of a 4*4 matrix using the S-box
	 * 
	 * @param inStateHex
	 *          : Output received from State XOR.
	 * 
	 * @return outStateHex : S-box substituted matrix.
	 */
	public static String[][] aesNibbleSub(String[][] inStateHex) {
		String[][] outStateHex = new String[inStateHex.length][inStateHex[0].length];
		for (int i = 0; i < inStateHex.length; i++) {
			for (int j = 0; j < inStateHex[0].length; j++) {
				outStateHex[i][j] = aesSBox(inStateHex[i][j].split(""));
			}
		}
		return outStateHex;
	}
	
	/**
	 * Generates W Matrix which contains the key generated at each round
	 */
	public static void generateWMatrix() {
		try {
			// Used to keep track of number of columns
			// for which a value has been assigned.
			int colCount = 0;
			larWMatrix = new String[gintArrayRowSize][gintWArrayColSize];
			
			// Step 1: Put the input key into W Matrix
			for (int col = 0; col < larInputKey[0].length; colCount++, col++) {
				for (int row = 0; row < larInputKey.length; row++) {
					larWMatrix[row][col] = larInputKey[row][col];
				}
			}
			
			// Step 2: Fill the remaining 40 columns with the generated keys
			for (; colCount < larWMatrix[0].length; colCount++) { // larWMatrix[0].length
				// Step 3: Check if the column is a multiple of 4
				// a. If the column is not a multiple of 4,
				// W(j) = W(j - 4) XOR W(j -1)
				// b. Else if the column is a multiple of 4,
				// W(j) = W(j - 4) XOR W_new
				// where W_new = [(Rcon(j) ^ S(W[1][j-1])) S(W[2][j-1])
				// S(W[3][j-1]) S(W[0][j-1])]
				
				if (colCount % gintArrayColSize != 0) {
					// System.out.println("No Exception for : " + colCount);
					if (genumNoOfRounds == BYTE_ROUND.BYTE_64 && colCount % 4 == 0) {
						for (int row = 0; row < gintArrayRowSize; row++) {
							larWMatrix[row][colCount] = aesSBox(
							    larWMatrix[row][colCount - 1].split(""));
							
							larWMatrix[row][colCount] = computeXOR(
							    larWMatrix[row][colCount - gintArrayColSize],
							    larWMatrix[row][colCount]);
						}
					} else {
						for (int row = 0; row < gintArrayRowSize; row++) {
							larWMatrix[row][colCount] = computeXOR(
							    larWMatrix[row][colCount - gintArrayColSize],
							    larWMatrix[row][colCount - 1]);
						}
						
					}
				} else {
					// Declare a W_new variable to hold the transformed matrix
					String[][] larWnew = new String[1][gintArrayRowSize];
					
					for (int col = 0; col < gintArrayRowSize; col++) {
						// Combining Step 3b. (i) (ii) & (iii)
						// (i) W_new
						// = [W[0][j - 1] W[1][j - 1] W[2][j - 1] W[3][j - 1]]
						// (ii) W_new
						// = [W[1][j - 1] W[2][j - 1] W[3][j - 1] W[4][j - 1]]
						// (iii)W_new
						// = [S(W[1][j-1]) S(W[2][j-1]) S(W[3][j-1])
						// S(W[0][j-1])]
						// Hence,
						// W_new
						// = [S(W[1][j-1]) S(W[2][j-1]) S(W[3][j-1])
						// S(W[0][j-1])]
						int wRowValue = (col == 3 ? 0 : col + 1);
						
						String[] larSplitHexValue = larWMatrix[wRowValue][colCount - 1]
						    .split("");
						larWnew[0][col] = aesSBox(larSplitHexValue);
					}
					
					// Step 3b.(iv) Get the Round value, Rcon(j/4),from the
					// Table
					// (v) XOR it with the first element in the array
					// i.e. : W_new
					// = [(Rcon(j) XOR S(W[1][j-1])) S(W[2][j-1]) S(W[3][j-1])
					// S(W[0][j-1])]
					// The row element in larRoundTable[row][col] is calculated
					// as (colCount/(4 | row_size ))/16 = (colCount / 64)
					String lstrRconValue = aesRcon(
					    (int) Math.floor(colCount / (gintArrayColSize * 16)),
					    colCount / gintArrayColSize);
					
					larWnew[0][0] = computeXOR(lstrRconValue, larWnew[0][0]);
					
					// Step 3b. (vi) : W(j) = W(j - 4) ^ W_new
					for (int rowCount = 0; rowCount < gintArrayRowSize; rowCount++) {
						larWMatrix[rowCount][colCount] = computeXOR(
						    larWMatrix[rowCount][colCount - gintArrayColSize],
						    larWnew[0][rowCount]);
					}
					
				}
			}
		} catch (Exception ex) {
			System.out
			    .println("Exception in generateWMatrix is : " + ex.getMessage());
		}
	}
	
	/**
	 * Creates a 4 * 4 Matrix from the given input key
	 *
	 * @param pstrInputKey
	 *          : Input key from the input file.
	 */
	public static void generateKeyMatrix(String pstrInputKey) {
		larInputKey = new String[gintArrayRowSize][gintArrayColSize];
		try {
			int col = 0;
			for (int colCounter = 0; colCounter < (pstrInputKey.length()
			    - 1); colCounter += 8, col++) {
				int row = 0;
				for (int rowCounter = colCounter; rowCounter < (colCounter
				    + 8); rowCounter += 2, row++) {
					larInputKey[row][col] = pstrInputKey.substring(rowCounter,
					    (rowCounter + 2));
				}
			}
		} catch (Exception ex) {
			System.out
			    .println("Exception in generateKeyMatrix is : " + ex.getMessage());
		}
	}
	
	/**
	 * Prints 10 round keys other than the entered key
	 */
	public static void printRoundKeys() {
		try {
			int counter = GlobalObjects.BYTE_ROUND
			    .getValue(GlobalObjects.genumNoOfRounds) + 1;
			int col = 0;
			System.out.println("larWMatrix[0].length = " + larWMatrix[0].length);
			do {
				// Loop to print 16 hex values as 1 round key
				for (int colCount = 0; colCount < gintArrayRowSize; col++, colCount++) {
					for (int row = 0; row < larWMatrix.length; row++) {
						System.out.print(larWMatrix[row][col]);
					}
				}
				System.out.println();
				counter--;
			} while (counter != 0);
		} catch (Exception ex) {
			System.out.println("Exception in printRoundKeys is : " + ex.getMessage());
		}
	}
	
	/**
	 * XOR's two 4*4 matrices and outputs the result
	 * 
	 * @param sHex
	 *          : Input plain text Matrix.
	 * 
	 * @param keyHex
	 *          : Input Key Matrix.
	 * 
	 * @return outStateHex : XOR result of key and plain text
	 */
	public static String[][] aesStateXOR(String[][] sHex, String[][] keyHex) {
		String[][] outStateHex = new String[sHex.length][sHex[0].length];
		for (int i = 0; i < sHex.length; i++) {
			for (int j = 0; j < sHex[i].length; j++) {
				outStateHex[i][j] = computeXOR(sHex[i][j], keyHex[i][j]);
			}
		}
		return outStateHex;
	}
	
	/**
	 * Left shifts each element by their row number
	 * 
	 * @param inStateHex
	 *          : Output received from Nibble Substitution.
	 * 
	 * @return outStateHex : Matrix after Shifting the Rows.
	 */
	protected static String[][] aesShiftRow(String[][] inStateHex) {
		String[][] outStateHex = new String[4][4];
		int row = 0;
		try {
			for (int i = 0; i < inStateHex.length; i++, row++) {
				for (int j = 0; j < inStateHex[0].length; j++) {
					switch (genumAlgoMode) {
						case ENCRYPT:
							outStateHex[i][j] = inStateHex[i][(j + row) % 4];
							break;
						case DECRYPT:
							int lintDiff = ((j - row) % 4);
							int outpuTCol = (lintDiff < 0 ? inStateHex.length + lintDiff
							    : lintDiff);
							
							outStateHex[i][j] = inStateHex[i][outpuTCol];
							break;
						case NONE:
						default:
							System.out.println("Algo Mode not set");
							break;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception in aesShiftRow : " + ex.getMessage());
		}
		return outStateHex;
	}
	
	/**
	 * GaloisMatrix is used to multiply the columns of the 4*4 input matrix
	 *
	 * @param inStateHex
	 *          : Output from ShiftRows
	 * 
	 * @return returnMatrix : Matrix after row shift
	 */
	public static String[][] aesMixColumn(String[][] inStateHex) {
		String sum = "0";
		
		String[][] returnMatrix = new String[inStateHex.length][inStateHex[0].length];
		int rowCount = 0;
		int stateCol = 0;
		while (stateCol <= 3) {
			for (int row = 0; row < 4 && stateCol < 4; row++, rowCount++) {
				sum = "0";
				for (int col = 0, stateRow = 0; col < 4; col++, stateRow++) {
					String lstrGalois_Matrix = (genumAlgoMode == ALGO_MODE.ENCRYPT
					    ? GALOIS_MATRIX[row][col] : INV_GALOIS_MATRIX[row][col]);
					// System.out.println("lstrGalois_Matrix " + lstrGalois_Matrix);
					if (null != lstrGalois_Matrix) {
						switch (lstrGalois_Matrix) {
							case "02":
								sum = outputOfCase2(sum, inStateHex[stateRow][stateCol]);
								break;
							case "03":
								// Split it as 02 and 01
								// Multiply 02 with first part of hex
								String temp = computeXOR(inStateHex[stateRow][stateCol],
								    shiftBit(inStateHex[stateRow][stateCol]));
								sum = computeXOR(sum, temp);
								break;
							case "01":
								sum = computeXOR(sum, inStateHex[stateRow][stateCol]);
								break;
							case "0E":
								int inputInHex = Integer
								    .parseInt(inStateHex[stateRow][stateCol], 16);
								sum = computeXOR(sum,
								    Integer.toHexString(multiplyE(inputInHex)));
								
								break;
							case "0B":
								inputInHex = Integer.parseInt(inStateHex[stateRow][stateCol],
								    16);
								sum = computeXOR(sum,
								    Integer.toHexString(multiplyB(inputInHex)));
								
								break;
							case "0D":
								inputInHex = Integer.parseInt(inStateHex[stateRow][stateCol],
								    16);
								sum = computeXOR(sum,
								    Integer.toHexString(multiplyD(inputInHex)));
								
								break;
							case "09":
								inputInHex = Integer.parseInt(inStateHex[stateRow][stateCol],
								    16);
								sum = computeXOR(sum,
								    Integer.toHexString(multiply9(inputInHex)));
								
								break;
							default:
								break;
						}
					}
				}
				returnMatrix[row][stateCol] = sum;
				if (rowCount == 3) {
					stateCol++;
					rowCount = -1;
				}
			}
		}
		
		return returnMatrix;
	}
	
	private static String outputOfCase2(String sum, String string) {
		sum = computeXOR(sum, shiftBit(string));
		return sum;
	}
	
	/**
	 * Generates the matrix for the input text from the file.
	 * 
	 * @param pstrInputText
	 *          : Input text received from the file
	 */
	public static void generateDataMatrix(String pstrInputText) {
		larInputData = new String[GlobalObjects.gintArrayRowSize][4];
		
		try {
			int col = 0;
			for (int colCounter = 0; colCounter < (pstrInputText.length()
			    - 1); colCounter += (GlobalObjects.gintArrayRowSize * 2), col++) {
				int row = 0;
				for (int rowCounter = colCounter; rowCounter < (colCounter
				    + (GlobalObjects.gintArrayRowSize * 2)); rowCounter += 2, row++) {
					larInputData[row][col] = pstrInputText.substring(rowCounter,
					    (rowCounter + 2));
				}
			}
		} catch (Exception ex) {
			System.out
			    .println("Exception in generateDataMatrix is : " + ex.getMessage());
		}
	}
	
	/**
	 * Prints the cipher generated by AES algorithm
	 * 
	 * @param larRoundData
	 *          : Final Cipher text generated by the algorithm.
	 */
	public static String printRoundData(String[][] larRoundData) {
		String lstrCipherText = "";
		try {
			for (int cols = 0; cols < 4; cols++) {
				for (int row = 0; row < 4; row++) {
					lstrCipherText += larRoundData[row][cols].toUpperCase();
				}
			}
			switch (genumAlgoMode) {
				case ENCRYPT:
					System.out.println("Cipher Text : " + lstrCipherText);
					break;
				case DECRYPT:
					System.out.println("Original Text : " + lstrCipherText);
					break;
				case NONE:
				default:
					System.out.println("Algo Mode not set.");
					break;
			}
		} catch (Exception ex) {
			System.out.println("Exception in printRoundData is : " + ex.getMessage());
		}
		return lstrCipherText;
	}
	
	public static String mixColShiftBit(String pstrHexValue) {
		// Binary value in string format for the input integer
		String binary = Integer.toBinaryString(Integer.parseInt(pstrHexValue, 16));
		
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		
		// Shift to left by 1
		int intvalueIn = Integer.parseInt(binary, 2);
		String shiftedBinary = "";
		
		shiftedBinary = Integer.toBinaryString(intvalueIn << 1);
		shiftedBinary = (shiftedBinary.length() > 8 ? shiftedBinary.substring(1)
		    : shiftedBinary);
		
		// check if upper significant bit is 1,
		// then XOR by 27
		// else return the original value
		if (binary.substring(0, 1).equals("1")) {
			String constant = Integer.toString(27, 2);
			while (constant.length() < 8) {
				constant = "0" + constant;
			}
			return computeXOR(Integer.toHexString(Integer.parseInt(shiftedBinary, 2)),
			    Integer.toHexString(Integer.parseInt(constant, 2)));
		} else {
			return Integer.toHexString(Integer.parseInt(shiftedBinary, 2));
		}
	}
	
	protected static Integer multiply9(Integer InputHex) {
		return ((multiply2(multiply2(multiply2(InputHex)))) ^ InputHex);
		
	}
	
	protected static Integer multiplyB(Integer InputHex) {
		return (multiply2(multiply2(multiply2(InputHex)) ^ InputHex) ^ InputHex);
		
	}
	
	protected static Integer multiplyD(Integer InputHex) {
		return (multiply2(multiply2((multiply2(InputHex) ^ InputHex))) ^ InputHex);
		
	}
	
	protected static Integer multiplyE(Integer InputHex) {
		return multiply2(multiply2((multiply2(InputHex) ^ InputHex)) ^ InputHex);
		
	}
	
	protected static Integer multiply2(Integer InputHex) {
		StringBuilder ABinary = new StringBuilder();
		String ABinString;
		ABinString = Integer.toBinaryString(InputHex);
		
		// NumZero stores the no. of zeroes to pad with
		int NumZero = 8 - ABinString.length();
		ABinary.append(ABinString);
		
		// Padding with zeroes now
		for (int i = 0; i < NumZero; i++) {
			ABinary.insert(0, '0');
		}
		Integer padded;
		padded = Integer.parseInt((ABinary.substring(1) + "0"), 2);
		// shiftedNum stores the number after it has been left shifted by 1
		String shiftedNum = Integer.toHexString(padded);
		Integer Snum = Integer.parseInt(shiftedNum, 16);
		
		// If the MSB of InputHex is 1, xor it with 1B
		if (NumZero == 0) {
			return ((Snum) ^ (0x1b));
		} else {
			return Snum;
		}
		
	}
}
