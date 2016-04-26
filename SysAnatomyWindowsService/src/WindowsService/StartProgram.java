package WindowsService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.hyperic.sigar.Sigar;

public class StartProgram {

	private static final Logger LOGGER = Logger.getLogger(StartProgram.class.getName());
	//private static Sigar sigar = new Sigar();

	
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");

			// Spawn Thread to run the function calls asynchronously
			//new CpuData();
			//new MemoryData();  
			//new SystemData();
			//new FileSystemData();
			new NetworkData();
			
		} catch (Exception ex) {
			LOGGER.error("Eception occured : " + ex.getMessage());
		}
	}
}
