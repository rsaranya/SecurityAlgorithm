
/**
 * @author: Saranya, Dixita
 * @CWID: 20062589, 20061841
 * @Program: Contains the functions specific to Decryption process.
 */
public class AesDecryption {

	/**
	 * Makes function call to all decryption functions in a sequence.
	 * 
	 * @param pstrCipherText
	 *            : Cipher text to be decrypted.
	 * @return : Returns the unpadded and decrypted cipher text
	 */
	private static String DecryptUsingAes(String pstrCipherText) {
		// Set the Algo mode to DECRYPT
		GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.DECRYPT;

		// Create a 4 x 4 matrix of the Cipher Text.
		GlobalObjects.generateDataMatrix(pstrCipherText);

		// Decrypt the cipher text
		return decryptWithRoundKeys();
	}

	/**
	 * Uses the Round Keys to decrypt the Cipher Text
	 * 
	 * @return : Padded Decrypted Plain Text
	 */
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
			int noOfRounds = GlobalObjects.BYTE_ROUND.getValue(GlobalObjects.genumNoOfRounds);
			// Take 4x4 matrix from 4x44 | 4x48 | 4x56 as round keys

			for (int lintDecrement = GlobalObjects.gintWArrayColSize - 4; lintDecrement >= 0
					&& roundCount <= noOfRounds; lintDecrement -= 8) {
				// Copy the 4x4 Round Key matrix from the global 4x44 | 4x48 |
				// 4x56 matrix into a local Matrix.
				for (int col = 0; col < 4 && lintDecrement < GlobalObjects.gintWArrayColSize; col++, lintDecrement++) {
					for (int row = 0; row < 4; row++) {
						larRoundKey[row][col] = GlobalObjects.larWMatrix[row][lintDecrement];
					}
				}

				// In the 1st round, perform State XOR, Shift Rows and Nibble
				// Substitution
				// In the last round, Perform State XOR with the last round key.
				if (roundCount == 0 || roundCount == GlobalObjects.BYTE_ROUND.getValue(GlobalObjects.genumNoOfRounds)) {
					// Step 1: Perform XOR of the data and round key.
					larRoundData = GlobalObjects.aesStateXOR(larRoundData, larRoundKey);

					if (roundCount == 0) {
						// Step 2: Perform Shifting on Rows
						if (larRoundData != null) {
							larRoundData = GlobalObjects.aesShiftRow(larRoundData);
						}

						// Step 3: Perform Nibble Substitution of the XOR
						if (larRoundData != null) {
							larRoundData = GlobalObjects.aesNibbleSub(larRoundData);
						}
					}
					roundCount++;
				} else {
					larRoundData = computeDataForEachRound(larRoundData, larRoundKey, roundCount);
					roundCount++;
				}
			}
			// Prints the decrypted data
			if (larRoundData != null) {
				return GlobalObjects.printRoundData(larRoundData);
			}

			return "";
		} catch (Exception ex) {
			System.out.println("Exception in decryptWithRoundKeys");
			return "";
		}
	}

	/**
	 * Computed the data for each round
	 * 
	 * @param larRoundData
	 *            : Input to be decrypted, received from the previous round.
	 * @param larRoundKey
	 *            : Key to be used for this round for decryption.
	 * @param pintRoundCount
	 *            : Round Number to keep track of the number of rounds left and
	 *            skip mix columns for the last round
	 * @return : Returns data computed at each round.
	 */
	private static String[][] computeDataForEachRound(String[][] larRoundData, String[][] larRoundKey,
			int pintRoundCount) {

		String[][] larInputToNextStep;
		larInputToNextStep = larRoundData;
		try {
			// Step 1: Perform XOR of the data and round key.
			larInputToNextStep = GlobalObjects.aesStateXOR(larInputToNextStep, larRoundKey);

			// Step 2: Perform Mixing of column if not
			// the last round
			if (pintRoundCount != GlobalObjects.BYTE_ROUND.getValue(GlobalObjects.genumNoOfRounds)) {
				if (larInputToNextStep != null) {
					larInputToNextStep = GlobalObjects.aesMixColumn(larInputToNextStep);
				}
			}

			// Step 3: Perform Shifting on Rows
			if (larInputToNextStep != null) {
				larInputToNextStep = GlobalObjects.aesShiftRow(larInputToNextStep);
			}

			// Step 4: Perform Nibble Substitution of the XOR
			if (larInputToNextStep != null) {
				larInputToNextStep = GlobalObjects.aesNibbleSub(larInputToNextStep);
			}
		} catch (Exception ex) {
			System.out.println("Exception in decryptWithRoundKeys");
		}
		return larInputToNextStep;
	}

	/**
	 * This function removes padding from the decrypted text. It checks for the
	 * hex value of '&' and gets the data before the '&'
	 *
	 * @param paddedText
	 *            the text for which padded data needs to be removed
	 * @return the original text.
	 */
	private static String removePadding(String paddedText) {
		try {
			String str = "";
			if ((paddedText.length() % GlobalObjects.gintInputBlockSize) == 0) {
				String padHex = Integer.toHexString((int) GlobalObjects.lstrDelimiter);

				if (paddedText.contains(padHex)) {
					int index = paddedText.lastIndexOf(padHex);
					str = paddedText.substring(0, index);
				}
				return str;
			} else {
				return "";
			}
		} catch (Exception ex) {
			System.out.println("Exception in removePadding():" + ex.getMessage());
			return "";
		}

	}

	/**
	 * Makes function calls to decrypt the cipher text
	 * 
	 * @param lstrCipherText
	 *            : Cipher Text to be decypted
	 * @return : Decrypted Plain Text
	 */
	public static String aesDecrypt(String lstrCipherText) {
		String decryptMsgList = DecryptUsingAes(lstrCipherText);

		// Remove Padding after Decryption
		decryptMsgList = removePadding(decryptMsgList);

		// Remove the extra Delimiter added before padding
		decryptMsgList = removeAnExtraDelimiter(decryptMsgList);

		return decryptMsgList;
	}

	/**
	 * Removes the extra delimiter added to the text.
	 * 
	 * @param pstrInputText
	 *            : Contains the decrypted text with extra delimiter characters
	 * @return : Plain Text without the extra delimiter character
	 */
	private static String removeAnExtraDelimiter(String pstrInputText) {
		// Check if the input text contains the delimiter i.e. '&&' in hex
		// i.e. : "2626"
		// Append the original '&' to the string
		String lstrHexOfDelimiter = Integer.toHexString((int) (GlobalObjects.lstrDelimiter)).toString();
		String[] larInputText = pstrInputText.split(lstrHexOfDelimiter + lstrHexOfDelimiter);

		if (larInputText.length > 0) {
			pstrInputText = larInputText[0];
			for (int count = 1; count < larInputText.length; count++) {
				pstrInputText += lstrHexOfDelimiter;
				pstrInputText += larInputText[count];
			}
		}
		return pstrInputText;
	}
}
