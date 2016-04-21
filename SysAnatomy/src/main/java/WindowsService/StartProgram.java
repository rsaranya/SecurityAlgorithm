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
    System.out.println("**************************************");
    System.out.println("*** Informations about the Memory: ***");
    System.out.println("**************************************\n");

    Mem mem = null;
    try {
      mem = sigar.getMem();

    } catch (SigarException se) {
      se.printStackTrace();
    }

    System.out.println("Actual total free system memory: "
      + mem.getActualFree() / 1024 / 1024 + " MB");
    System.out.println("Actual total used system memory: "
      + mem.getActualUsed() / 1024 / 1024 + " MB");
    System.out.println("Total free system memory ......: " + mem.getFree()
      / 1024 / 1024 + " MB");
    System.out.println("System Random Access Memory....: " + mem.getRam()
      + " MB");
    System.out.println("Total system memory............: " + mem.getTotal()
      / 1024 / 1024 + " MB");
    System.out.println("Total used system memory.......: " + mem.getUsed()
      / 1024 / 1024 + " MB");

    System.out.println("\n**************************************\n");

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
      se.printStackTrace();
    }

    System.out.print(mem.getUsedPercent() + "\t");
    System.out.print((cpuperc.getCombined() * 100) + "\t");
    System.out.print(filesystemusage.getUsePercent() + "\n");
  }

  public static void FetchUsingSigar() throws SigarException {
    Cpu cpuInfo = new Cpu();
    cpuInfo.gather(sigar);
  //  while (true) {
      System.out.println("Total system cpu idle time : " + cpuInfo.getIdle());
      System.out.println("Total system cpu time servicing interrupts : " + cpuInfo.getIrq());
      System.out.println("Total system cpu nice time : " + cpuInfo.getNice());
      System.out.println("Total system cpu time servicing softirqs : " + cpuInfo.getSoftIrq());
      System.out.println("Total system cpu involuntary wait time : " + cpuInfo.getStolen());
      System.out.println("Total system cpu kernel time : " + cpuInfo.getSys());
      System.out.println("Total system cpu time : " + cpuInfo.getTotal());
      System.out.println("Total system cpu user time : " + cpuInfo.getUser());
      System.out.println("Total system cpu io wait time : " + cpuInfo.getWait());
  //  }
  }

  public static void main(String[] args) {
    getInformationsAboutMemory();
    getSystemStatistics();
    try {
      FetchUsingSigar();
    } catch (SigarException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
