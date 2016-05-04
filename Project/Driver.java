
/**
 * @author: Saranya, Dixita
 * @CWID: 20062589,20061841
 * @Program: Utilizes the AES Key generation class to generate round keys 
 * Create a cipher text from a given plain text and
 * Decrypt the cipher text to retrieve the original Text
 */
import java.util.*;

/**
 * Contains the main class which is the entry point to the program. Makes
 * function calls to encrypt then decrypt the Input key.
 */
public class Driver {

	private static Scanner lscanInput;

	/**
	 * Is the Entry point to the program Fetches the key from the user and calls
	 * the Round key generator.
	 *
	 * @param args
	 *            : The command line argument containing the file name.
	 */
	public static void main(String[] args) {
		try {
			lscanInput = new Scanner(System.in);
			// Taking Input Key from the file.
			String inputKey = "";
			if (lscanInput.hasNext()) {
				inputKey = lscanInput.nextLine();
			} else {
				System.out.println("Input Key not found.");
				System.exit(0);
			}

			// Taking Input Text from the file.
			String inputPlainText = "";
			if (lscanInput.hasNext()) {
				inputPlainText = lscanInput.nextLine();
			} else {
				System.out.println("Input Text not found.");
				System.exit(0);
			}

			// If condition makes sure that user has entered
			// exactly 32 hexadecimal digits
			if ((inputKey.matches("[0-9A-F]+$")) && (inputPlainText.matches("[0-9A-F]+$"))) {

				// Add padding if the size is less than the required size
				// Size will be 32- , 48- , 64 - bits
				int lintInputKeySize = inputKey.length();

				// The number of rows remain constant
				// for all key sizes.
				GlobalObjects.gintArrayRowSize = 4;

				// Number of Rounds based on Bit size
				GlobalObjects.genumNoOfRounds = GlobalObjects.BYTE_ROUND.getEnum(lintInputKeySize);

				// Set the column size based on Bit size.
				// E.g.: Key Size = 48; Col Size = 48 /8 = 6
				// (6 is the number of columns the Input Key utilizes)
				GlobalObjects.gintArrayColSize = (lintInputKeySize / 8);

				// The number of columns for the entire array
				// containing all the round keys
				// E.g.: For 48-bytes, No Of Rounds, 12
				// Total Array Size = 4 * (12 + 1) = 52
				GlobalObjects.gintWArrayColSize = 4
						* (GlobalObjects.BYTE_ROUND.getValue(GlobalObjects.genumNoOfRounds) + 1);

				// Pad and then Encrypt, followed by Decryption.
				checkForPaddingAndEncrypt(inputPlainText, inputKey);
			} else {
				// If user input is incorrect, terminate the program
				System.out.println("Invalid Values in the Input File.");
				System.exit(0);
			}

		} catch (Exception ex) {
			System.out.println("Exception in main : " + ex.getMessage());
		}
	}

	/**
	 * Pads the Input text if needed Breaks the Input key into blocks of size
	 * 32-bytes. Encrypts the Input Data using the Keys And Decrypts the
	 * Encrypted Text.
	 * 
	 * @param pstrInputText:
	 *            Contains the Input Text entered by the user.
	 * @param pstrInputKey:
	 *            Contains the Input Key entered by the user.
	 */
	private static void checkForPaddingAndEncrypt(String pstrInputText, String pstrInputKey) {
		// Call the aesKeyGen class and pass the plain text
		// and input key as string to the constructor
		System.out.println("Original Text : "+ pstrInputText);

		AesEncryption aesKeyGen = new AesEncryption();

		pstrInputText = appendAnExtraDelimiter(pstrInputText);

		int startIndex = 0;
		int loopCount;
		// Find the No Of Blocks to generate for the given input.
		if (pstrInputText.length() % GlobalObjects.gintInputBlockSize != 0) {
			loopCount = pstrInputText.length() / GlobalObjects.gintInputBlockSize + 1;
		} else {
			loopCount = pstrInputText.length() / GlobalObjects.gintInputBlockSize;
		}

		String lstrTotalDecryptedText = "";
		// Loop to create blocks up till the Number of Blocks
		String strBlock[] = new String[loopCount];
		int blockSent = 0;
		for (int noOfBlocks = 0; noOfBlocks < loopCount; noOfBlocks++) {
			int minLength = Math.min(GlobalObjects.gintInputBlockSize,
					(pstrInputText.length() - blockSent * GlobalObjects.gintInputBlockSize));
			strBlock[noOfBlocks] = pstrInputText.substring(startIndex, startIndex + minLength);
			blockSent++;

			// Contains Padded Text
			String temp = aesKeyGen.addPadding(strBlock[noOfBlocks]);

			// Function call for Encryption
			String lstrCipherText = aesKeyGen.aesEncrypt(temp, pstrInputKey);

			// Function call for Decryption
			lstrTotalDecryptedText += AesDecryption.aesDecrypt(lstrCipherText);

			// Keep track of the Index of the last element sent.
			startIndex += GlobalObjects.gintInputBlockSize;
		}
		System.out.println("Decypted Text: " + lstrTotalDecryptedText);

	}

	/**
	 * Appends an extra delimiter to an existing delimiter in the Input Text
	 * 
	 * @param pstrInputText
	 *            : Contains the Input Text to be checked and appended with the
	 *            delimiter.
	 * @return : Returns the Input text appended with an extra delimiter.
	 */
	private static String appendAnExtraDelimiter(String pstrInputText) {
		// Check if the input text contains the delimiter i.e. '&' in hex
		// i.e. : "26"
		// Append an extra '&' before the existing '&'
		boolean lblnIsEndWithDelimiter = false;
		String lstrHexOfDelimiter = Integer.toHexString((int) (GlobalObjects.lstrDelimiter)).toString();

		// Check if the last byte is '&'
		// if yes, then set the flag to true.
		// At the end of padding, if this flag is true
		// Add the delimter to the end of the Input Text
		if (pstrInputText.substring(pstrInputText.length() - 2, pstrInputText.length()).equals(lstrHexOfDelimiter)) {
			lblnIsEndWithDelimiter = true;
		}

		// Split the Input string based on delimiter
		// Add the text without the delimiter
		// Add the delimiter twice.
		// Add the remaining text.
		String[] larInputText = pstrInputText.split(lstrHexOfDelimiter);
		if (larInputText.length > 0) {
			pstrInputText = larInputText[0];
			for (int count = 1; count < larInputText.length; count++) {
				if (larInputText[count].length() % 2 == 0) {
					pstrInputText += lstrHexOfDelimiter;
				}
				pstrInputText += lstrHexOfDelimiter;
				pstrInputText += larInputText[count];
			}
		}

		if (lblnIsEndWithDelimiter)
			pstrInputText += lstrHexOfDelimiter;

		return pstrInputText;
	}
}
