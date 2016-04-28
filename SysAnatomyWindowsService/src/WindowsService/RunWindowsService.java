package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunWindowsService {

	private static Logger LOGGER = LogManager.getLogger();
	private static boolean stop = false;

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception ex) {
			if (LOGGER != null)
				LOGGER.error("Exception occured : " + ex.getMessage());
		}
	}

	private static void SpawnThreads() {
		// Spawn Thread to run the function calls asynchronously
		/*new CpuData();
		new MemoryData();
		new SystemData();
		new FileSystemData();
		new NetworkData();*/
		new ProcessData();
	}

	public static void start(String[] args) {
		LOGGER.info("start");
		while (!stop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			SpawnThreads();
		}
	}

	public static void stop(String[] args) {
		LOGGER.info("stop");
		stop = true;
	}
}
