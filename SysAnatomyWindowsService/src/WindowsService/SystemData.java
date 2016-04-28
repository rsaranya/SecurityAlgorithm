
package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.OperatingSystem;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

/**
 *
 * @author Saranya
 */
public class SystemData implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();
	private JSONObject lobjJsonSystemData = null;

	/**
	 * Constructor of the class
	 * Spawns a thread which fetches data from the user system.
	 */
	public SystemData() {
		new Thread(this).start();
	}

	/**
	 * 
	 */
	public static void getSystemDetails() {
		OperatingSystem lobjOperatingSystem = OperatingSystem.getInstance();

		try {
			if (lobjOperatingSystem != null) {
				LOGGER.info("System Architecture : " + lobjOperatingSystem.getArch());
				LOGGER.info("Machine Endian : " + lobjOperatingSystem.getCpuEndian());
				LOGGER.info("Machine Data Model : " + lobjOperatingSystem.getDataModel());
				LOGGER.info("System Description : " + lobjOperatingSystem.getDescription());
				LOGGER.info("Machine Name : " + lobjOperatingSystem.getName());
				LOGGER.info("Machine Vendor : " + lobjOperatingSystem.getVendor());
				LOGGER.info("Machine Vendor Code Name : " + lobjOperatingSystem.getVendorCodeName());
				LOGGER.info("Machine Vendor Name : " + lobjOperatingSystem.getVendorName());
				LOGGER.info("Machine Vendor Version : " + lobjOperatingSystem.getVendorVersion());
				LOGGER.info("Machine Version : " + lobjOperatingSystem.getVersion());
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occured : " + ex.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * Called when thread starts
	 * Calls the functions to fetch System related data
	 * And adds the JSON object into a global array
	 */
	public void run() {
		getSystemDetails();
		synchronized (GlobalObjects.larrlstJson) {
			GlobalObjects.larrlstJson.add(lobjJsonSystemData);
		}
	}
}
