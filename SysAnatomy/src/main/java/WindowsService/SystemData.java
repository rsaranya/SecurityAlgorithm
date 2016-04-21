/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystemUsage;

/**
 *
 * @author Saranya
 */
public class SystemData {

  private static final Logger LOGGER = Logger.getLogger(MemoryData.class.getName());
  //private static final Sigar sigar = new Sigar();

  public static void getSystemStatistics(Sigar sigar) {
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
}
