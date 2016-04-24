package WindowsService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StartProgram {

	private static final Logger LOGGER = Logger.getLogger(StartProgram.class.getName());
	
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");

			// Spawn Thread to run the function calls asynchronously
			
			new CpuData();
			new MemoryData();  
			new SystemData();
			//new ConnectServer();

		} catch (Exception ex) {
			LOGGER.error("Eception occured : " + ex.getMessage());
		}
	}
}
