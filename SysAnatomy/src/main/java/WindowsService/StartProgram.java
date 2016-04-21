package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class StartProgram {

  private static final Logger LOGGER = Logger.getLogger(StartProgram.class.getName());
  private static final Sigar sigar = new Sigar();

  public static void main(String[] args) {
    CpuData.getDataFromCpu(sigar);
    MemoryData.getDataFromMemClass(sigar);
    SystemData.getSystemStatistics(sigar);
  }
}
