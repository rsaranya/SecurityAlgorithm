package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Saranya
 *
 */
public class RunWindowsService {

	private static Logger LOGGER = LogManager.getLogger();
	private static boolean stop = false;

	/**
	 * Entry point for the program. Calls the start function to ensure the
	 * application runs as a service.
	 * 
	 * @param args
	 *            : Command line arguments are received here.
	 */
	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception ex) {
			if (LOGGER != null)
				LOGGER.error("Exception in main : " + ex.getMessage());
		}
	}

	/**
	 * Spawns a thread for each class in order to allow parallel collection of
	 * data.
	 */
	private static void SpawnThreads() {
		try {
			// Spawn Thread to run the function calls asynchronously
			new CpuData();
			new MemoryData();
			new SystemData();
			new FileSystemData();
			new NetworkData();
			new ProcessData();
		} catch (Exception ex) {
			if (LOGGER != null)
				LOGGER.error("Exception in SpawnThreads : " + ex.getMessage());
		}
	}

	/**
	 * Called when service is started.
	 * 
	 * @param args
	 */
	public static void start(String[] args) {
		try {
			LOGGER.info("start");
			while (!stop) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				SpawnThreads();
			}
		} catch (Exception ex) {
			if (LOGGER != null)
				LOGGER.error("Exception in start : " + ex.getMessage());
		}
	}

	/**
	 * Called when service is stopped.
	 * 
	 * @param args
	 */
	public static void stop(String[] args) {
		LOGGER.info("stop");
		stop = true;
	}
}
