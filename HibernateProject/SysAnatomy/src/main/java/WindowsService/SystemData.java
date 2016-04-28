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
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.SysInfo;

/**
 *
 * @author Saranya
 */
public class SystemData {

  private static final Logger LOGGER = Logger.getLogger(SystemData.class.getName());
  private static Sigar sigar = new Sigar();
  
  public SystemData()
  {
    getSystemStatistics();
    getSystemDetails();
  }
  
  
  private static void getSystemStatistics() {
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

  public static void getSystemDetails() {
    SysInfo lsysInfoInstance = new SysInfo();

    LOGGER.info("System Architecture : \t" + lsysInfoInstance.getArch());
    LOGGER.info("System Description : \t" + lsysInfoInstance.getDescription());
    LOGGER.info("Machine : \t" + lsysInfoInstance.getMachine());
    LOGGER.info("Machine Name : \t" + lsysInfoInstance.getName());
    LOGGER.info("Machine Vendor : \t" + lsysInfoInstance.getVendor());
    LOGGER.info("Machine Vendor Name : \t" + lsysInfoInstance.getVendorName());
  }
}
