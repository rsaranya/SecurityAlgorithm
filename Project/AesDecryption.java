/**
 * @author Saranya, Dixita
 * @CWID 20062589
 * @Program Utilizes the AES Key generation class to generate round keys and
 * create a cipher text from a given plain text
 */
public class AesDecryption {
	
	// The fixed matrix used for column mixing
	static String[][] Inv_GaloisMatrix = { { "0E", "0B", "0D", "09" },
	    { "09", "0E", "0B", "0D" }, { "0D", "09", "0E", "0B" },
	    { "0B", "0D", "09", "0E" } };
	
	public static void DecryptUsingAes(String pstrCipherText) {
		GlobalObjects.genumAlgoMode = GlobalObjects.ALGO_MODE.DECRYPT;
		
	}
}
