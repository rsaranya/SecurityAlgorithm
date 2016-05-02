/**
 * @author Saranya, Dixita
 * @CWID 20062589, 20061841
 * @Program Utilizes the AES Key generation class to generate round keys and
 *          create a cipher text from a given plain text
 */
public class AesDecryption {
	
	public static void DecryptUsingAes(String pstrCipherText) {
		GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.DECRYPT;
		
		GlobalObjects.generateDataMatrix(pstrCipherText);
		
		decryptWithRoundKeys();
	}
	
	private static void decryptWithRoundKeys() {
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
				GlobalObjects.printRoundData(larRoundData);
			}
		} catch (Exception ex) {
			System.out.println("Exception in decryptWithRoundKeys");
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
}

	
	public static void DecryptUsingAes(String pstrCipherText) {
		GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.DECRYPT;
		
		GlobalObjects.generateDataMatrix(pstrCipherText);
		
		decryptWithRoundKeys();
	}
	
	private static void decryptWithRoundKeys() {
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
			
			for (int col = 0; col < GlobalObjects.larWMatrix[0].length; col++) {
				for (int row = 0; row < GlobalObjects.larWMatrix.length; row++) {
					System.out.print(GlobalObjects.larWMatrix[row][col]);
				}
				System.out.println();
			}
			
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
				System.out.println("Rounds : " + roundCount);
				
				if (roundCount == 0 || roundCount == GlobalObjects.BYTE_ROUND
				    .getValue(GlobalObjects.genumNoOfRounds)) {
					System.out.println("Rounds Data");
					for (int row = 0; row < larRoundData.length; row++) {
						for (int col = 0; col < larRoundData[row].length; col++) {
							System.out.print(larRoundData[row][col] + "\t");
						}
						System.out.println();
					}
					
					System.out.println("Rounds Key");
					for (int row = 0; row < larRoundKey.length; row++) {
						for (int col = 0; col < larRoundKey[row].length; col++) {
							System.out.print(larRoundKey[row][col] + "\t");
						}
						System.out.println();
					}
					
					// Step 1: Perform XOR of the data and round key.
					larRoundData = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);
					
					System.out.println("After State XOR ");
					for (int row = 0; row < larRoundData.length; row++) {
						for (int col = 0; col < larRoundData[row].length; col++) {
							System.out.print(larRoundData[row][col] + "\t");
						}
						System.out.println();
					}
					
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
				GlobalObjects.printRoundData(larRoundData);
			}
		} catch (Exception ex) {
			System.out.println("Exception in decryptWithRoundKeys");
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
	
	/**
	 * Makes the function calls to generate the round keys and print them
	 * 
	 * @param pstrCipherText
	 * @param pstrInputKey
	 *
	 * @param pstrInputText
	 *          : Contains the plain text from the input file.
	 * @param pstrInputKey
	 *          : Contains the input key from the input file.
	 */
	public final static void aesDecryptRoundKeys(String pstrInputKey,
	    String pstrCipherText) {
		try {
			// Step 1: Generate a Matrix of the user entered key.
			GlobalObjects.generateKeyMatrix(pstrInputKey);
			
			// Step 2: Generate the W matrix containing the key generated.
			GlobalObjects.generateWMatrix();
			
			// Step 3: Print first 10 round keys generated.
			GlobalObjects.printRoundKeys();
			
			// Step 4 : Generate a Matrix of the user entered data or plain
			// text.
			GlobalObjects.generateDataMatrix(pstrCipherText);
			
		} catch (Exception ex) {
			System.out.println("Exception in aesRoundKeys ");
			ex.printStackTrace();
		}
	}
}
