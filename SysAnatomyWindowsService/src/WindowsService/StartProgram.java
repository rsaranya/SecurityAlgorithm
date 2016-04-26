package WindowsService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Uptime;

public class StartProgram {

	private static final Logger LOGGER = Logger.getLogger(StartProgram.class.getName());
	private static Sigar sigar = new Sigar();

	
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");

			// Spawn Thread to run the function calls asynchronously
			//new CpuData();
			//new MemoryData();  
			//new SystemData();
			new FileSystemData();
			//Uptime lobjUptime = sigar.getUptime();
			//lobjUptime.getUptime();
			
			//sigar.getDirStat(name)
		} catch (Exception ex) {
			LOGGER.error("Eception occured : " + ex.getMessage());
		}
	}
}
