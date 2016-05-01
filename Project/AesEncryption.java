/**
 * @author Saranya, , Dixita
 * @CWID 20062589
 * @Program Generates 10 round keys for AES algorithm based on an input key.
 *          Used the input key and input plain text to generate cipher text
 */
public class AesEncryption {
	
	// The fixed matrix used for column mixing
	static String[][] GaloisMatrix = { { "02", "03", "01", "01" },
	    { "01", "02", "03", "01" }, { "01", "01", "02", "03" },
	    { "03", "01", "01", "02" } };
	
	// Used to hold the entered Data in the form of an Array
	private String[][] larInputData = null;
	
	public AesEncryption() {
		try {
			larInputData = new String[GlobalObjects.gintArrayRowSize][4];
			GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.ENCRYPT;
		} catch (Exception ex) {
			System.out.println("Exception in AesDecryption : " + ex.getMessage());
		}
	}
	
	/**
	 * Makes the function calls to generate the round keys and print them
	 *
	 * @param pstrInputText
	 *          : Contains the plain text from the input file.
	 * @param pstrInputKey
	 *          : Contains the input key from the input file.
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
			generateDataMatrix(pstrInputText);
			
		} catch (Exception ex) {
			System.out.println("Exception in aesRoundKeys ");
			ex.printStackTrace();
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
	protected String[][] aesStateXOR(String[][] sHex, String[][] keyHex) {
		String[][] outStateHex = new String[sHex.length][sHex[0].length];
		for (int i = 0; i < sHex.length; i++) {
			for (int j = 0; j < sHex[i].length; j++) {
				outStateHex[i][j] = GlobalObjects.computeXOR(sHex[i][j], keyHex[i][j]);
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
	protected String[][] aesShiftRow(String[][] inStateHex) {
		String[][] outStateHex = new String[4][4];
		int row = 0;
		for (int i = 0; i < inStateHex.length; i++, row++) {
			for (int j = 0; j < inStateHex[0].length; j++) {
				outStateHex[i][j] = inStateHex[i][(j + row) % 4];
			}
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
	public String[][] aesMixColumn(String[][] inStateHex) {
		String sum = "0";
		
		String[][] returnMatrix = new String[inStateHex.length][inStateHex[0].length];
		int rowCount = 0;
		int stateCol = 0;
		while (stateCol <= 3) {
			for (int row = 0; row < 4 && stateCol < 4; row++, rowCount++) {
				sum = "0";
				for (int col = 0, stateRow = 0; col < 4; col++, stateRow++) {
					if (null != GaloisMatrix[row][col]) {
						switch (GaloisMatrix[row][col]) {
							case "02":
								sum = GlobalObjects.computeXOR(sum,
								    GlobalObjects.shiftBit(inStateHex[stateRow][stateCol]));
								break;
							case "03":
								// Split it as 02 and 01
								// Multiply 02 with first part of hex
								String temp = GlobalObjects.computeXOR(
								    inStateHex[stateRow][stateCol],
								    GlobalObjects.shiftBit(inStateHex[stateRow][stateCol]));
								sum = GlobalObjects.computeXOR(sum, temp);
								break;
							case "01":
								sum = GlobalObjects.computeXOR(sum,
								    inStateHex[stateRow][stateCol]);
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
	
	/**
	 * Computes data for one full round of AES encryption
	 * 
	 * @param larRoundData
	 *          : Intermediate Round Data
	 * @param larRoundKey
	 *          : Intermediate Round Keys
	 * @param pintRoundCount
	 *          : Round Number
	 * @return larInputToNextStep: Intermediate Round Data to be used in the next
	 *         step.
	 */
	protected String[][] computeDataForEachRound(String[][] larRoundData,
	    String[][] larRoundKey, int pintRoundCount) {
		String[][] larInputToNextStep;
		larInputToNextStep = null;
		
		// Step 1: Perform XOR of the data and round key.
		larInputToNextStep = aesStateXOR(larRoundData, larRoundKey);
		
		// Step 2: Perform Nibble Substitution of the XOR
		if (larInputToNextStep != null) {
			try {
				larInputToNextStep = GlobalObjects.aesNibbleSub(larInputToNextStep);
			} catch (Exception ex) {
				System.out.println("Exception in Nibble substitution");
				ex.printStackTrace();
			}
		}
		
		// Step 3: Perform Shifting on Rows
		if (larInputToNextStep != null) {
			try {
				larInputToNextStep = aesShiftRow(larInputToNextStep);
			} catch (Exception ex) {
				System.out.println("Exception in Shifting rows");
				ex.printStackTrace();
			}
		}
		
		// Step 4: Perform Mixing of column if not the last round
		if (pintRoundCount != GlobalObjects.BYTE_ROUND
		    .getValue(GlobalObjects.genumNoOfRounds)) {
			if (larInputToNextStep != null) {
				try {
					larInputToNextStep = aesMixColumn(larInputToNextStep);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return larInputToNextStep;
	}
	
	/**
	 * Encrypts the plaintext with the key to produce a ciphertext.
	 * 
	 * @param pstrInputText
	 *          : Plain text taken from the input file
	 * @param pstrInputKey
	 *          : Key taken from the input file
	 */
	protected String aes(String pstrInputText, String pstrInputKey) {
		try {
			// If the Input key and Text is not empty
			// Process the keys and Input to generate cipher text.
			if (!(pstrInputText.trim().equals("")
			    && pstrInputKey.trim().equals(""))) {
				// Generate Matrices for the given Key and Text.
				aesRoundKeys(pstrInputText, pstrInputKey);
				return EncryptUsingAes();
			}
		} catch (Exception ex) {
			System.out.println("Exception in aes ");
			ex.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	private String EncryptUsingAes() {
		try {
			int roundCount = 0;
			String[][] larRoundKey = new String[GlobalObjects.gintArrayRowSize][GlobalObjects.gintArrayRowSize];
			String[][] larRoundData = new String[GlobalObjects.gintArrayRowSize][GlobalObjects.gintArrayRowSize];
			
			// Copy the Input Data matrix into a local Matrix.
			for (int row = 0; row < GlobalObjects.gintArrayRowSize; row++) {
				for (int col = 0; col < GlobalObjects.gintArrayRowSize; col++) {
					larRoundData[row][col] = larInputData[row][col];
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
					larRoundData = aesStateXOR(larRoundData, larRoundKey);
					roundCount++;
				} else {
					roundCount++;
					larRoundData = computeDataForEachRound(larRoundData, larRoundKey,
					    roundCount);
				}
			}
			if (larRoundData != null) {
				return printRoundData(larRoundData);
			}
		} catch (Exception ex) {
			System.out.println("Exception in EncryptUsingAes");
		}
		return "";
	}
	
	/**
	 * Generates the matrix for the input text from the file.
	 * 
	 * @param pstrInputText
	 *          : Input text received from the file
	 */
	private void generateDataMatrix(String pstrInputText) {
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
	private String printRoundData(String[][] larRoundData) {
		String lstrCipherText = "";
		try {
			for (int cols = 0; cols < 4; cols++) {
				for (int row = 0; row < 4; row++) {
					lstrCipherText += larRoundData[row][cols].toUpperCase();
				}
			}
			System.out.println("Cipher Text : " + lstrCipherText);
		} catch (Exception ex) {
			System.out.println("Exception in printRoundData is : " + ex.getMessage());
		}
		return lstrCipherText;
	}
}
