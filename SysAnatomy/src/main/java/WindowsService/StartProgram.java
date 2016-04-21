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

  public static void getInformationsAboutMemory() {
    LOGGER.info("inside getInformationsAboutMemory");
    LOGGER.info("**************************************");
    LOGGER.info("*** Informations about the Memory: ***");
    LOGGER.info("**************************************\n");

    Mem mem = null;
    try {
      mem = sigar.getMem();

    } catch (SigarException se) {
      LOGGER.error(se);
    }

    LOGGER.info("Actual total free system memory: "
      + mem.getActualFree() / 1024 / 1024 + " MB");
    LOGGER.info("Actual total used system memory: "
      + mem.getActualUsed() / 1024 / 1024 + " MB");
    LOGGER.info("Total free system memory ......: " + mem.getFree()
      / 1024 / 1024 + " MB");
    LOGGER.info("System Random Access Memory....: " + mem.getRam()
      + " MB");
    LOGGER.info("Total system memory............: " + mem.getTotal()
      / 1024 / 1024 + " MB");
    LOGGER.info("Total used system memory.......: " + mem.getUsed()
      / 1024 / 1024 + " MB");

    LOGGER.info("\n**************************************\n");

  }

  public static void getSystemStatistics() {
    Mem mem = null;
    CpuPerc cpuperc = null;
    FileSystemUsage filesystemusage = null;
    try {
      mem = sigar.getMem();
      cpuperc = sigar.getCpuPerc();
      filesystemusage = sigar.getFileSystemUsage("C:");
    } catch (SigarException se) {
      LOGGER.error(se);
    }

    LOGGER.info(mem.getUsedPercent() + "\t");
    LOGGER.info((cpuperc.getCombined() * 100) + "\t");
    LOGGER.info(filesystemusage.getUsePercent() + "\n");
  }

  public static void FetchUsingSigar() throws SigarException {
    Cpu cpuInfo = new Cpu();
    cpuInfo.gather(sigar);
  //  while (true) {
    LOGGER.info("Total system cpu idle time : " + cpuInfo.getIdle());
    LOGGER.info("Total system cpu time servicing interrupts : " + cpuInfo.getIrq());
    LOGGER.info("Total system cpu nice time : " + cpuInfo.getNice());
    LOGGER.info("Total system cpu time servicing softirqs : " + cpuInfo.getSoftIrq());
    LOGGER.info("Total system cpu involuntary wait time : " + cpuInfo.getStolen());
    LOGGER.info("Total system cpu kernel time : " + cpuInfo.getSys());
    LOGGER.info("Total system cpu time : " + cpuInfo.getTotal());
    LOGGER.info("Total system cpu user time : " + cpuInfo.getUser());
    LOGGER.info("Total system cpu io wait time : " + cpuInfo.getWait());
  //  }
  }

  public static void main(String[] args) {
    getInformationsAboutMemory();
    getSystemStatistics();
    try {
      FetchUsingSigar();
    } catch (SigarException e) {
      // TODO Auto-generated catch block
      LOGGER.error(e);
    }
  }

}
