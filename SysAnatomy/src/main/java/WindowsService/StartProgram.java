package WindowsService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hyperic.sigar.Sigar;

public class StartProgram {

  private static final Logger LOGGER = Logger.getLogger(StartProgram.class
      .getName());
  private static final Sigar sigar = new Sigar();

  public static void main(String[] args) {
    try {
      PropertyConfigurator.configure("log4j.properties");
      
      CpuData lcpuData = new CpuData(sigar);     
      MemoryData.getMemoryData(sigar);
      SystemData.getSystemStatistics(sigar);
    } catch (Exception ex) {
      LOGGER.error("Eception occured : " + ex.getMessage());
    }
  }
}
