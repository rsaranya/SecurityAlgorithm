package WindowsService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StartProgram {

  private static final Logger LOGGER = Logger.getLogger(StartProgram.class
      .getName());

  public static void main(String[] args) {
    try {
      PropertyConfigurator.configure("log4j.properties");

      new CpuData(); // CpuData lcpuData =
      new MemoryData();// MemoryData lmemData =
      new SystemData(); // SystemData lsysData =

    } catch (Exception ex) {
      LOGGER.error("Eception occured : " + ex.getMessage());
    }
  }
}
