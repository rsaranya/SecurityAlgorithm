/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;/**
 *
 * @author Saranya
 */
public class CpuData {

  private static final Logger LOGGER = Logger.getLogger(CpuData.class.getName());
  private static Sigar sigar = new Sigar();
  /**
   * 
   * @param psigar
   */
  public CpuData()
  {
    getDataFromCpu();
    getDataFromCpuInfo();
    getDataFromMultiProcCpu();
  }
  
  /**
   * 
   */
  private static void getDataFromCpu() {
    LOGGER.info("inside getDataFromCpuClass");
    LOGGER.info("**************************************");
    LOGGER.info("*** Informations about the CPU: ***");
    LOGGER.info("**************************************\n");
    
    try {
      Cpu lcpuInstance = new Cpu();
      lcpuInstance.gather(sigar);

      // From CPU class
      LOGGER.info("Total system cpu idle time : "
        + lcpuInstance.getIdle());
      LOGGER.info("Total system cpu time servicing interrupts : "
        + lcpuInstance.getIrq());
      LOGGER.info("Total system cpu nice time : "
        + lcpuInstance.getNice());
      LOGGER.info("Total system cpu time servicing softirqs : "
        + lcpuInstance.getSoftIrq());
      LOGGER.info("Total system cpu involuntary wait time : "
        + lcpuInstance.getStolen());
      LOGGER.info("Total system cpu kernel time : "
        + lcpuInstance.getSys());
      LOGGER.info("Total system cpu time : "
        + lcpuInstance.getTotal());
      LOGGER.info("Total system cpu user time : "
        + lcpuInstance.getUser());
      LOGGER.info("Total system cpu io wait time : "
        + lcpuInstance.getWait());

      CpuInfo lcpuInfoInstance = new CpuInfo();
      LOGGER.info("CPU cache size : " + lcpuInfoInstance.getCacheSize());

    } catch (SigarException sigarEx) {
      LOGGER.error("Exception encountered : " + sigarEx.getMessage());
    }
    LOGGER.info("\n**************************************\n");
  }

  /**
   * 
   */
  private static void getDataFromCpuInfo() {
    LOGGER.info("inside getDataFromCpuInfoClass");
    LOGGER.info("**************************************");
    LOGGER.info("*** Informations about the CPU: ***");
    LOGGER.info("**************************************\n");

    try {
      CpuInfo lcpuInfoInstance = new CpuInfo();
      LOGGER.info("CPU cache size : " + lcpuInfoInstance.getCacheSize());
      LOGGER.info("Number of CPU cores per CPU socket : " + lcpuInfoInstance.getCoresPerSocket());
      LOGGER.info("CPU speed : " + lcpuInfoInstance.getMhz());
      LOGGER.info("CPU model : " + lcpuInfoInstance.getModel());
      LOGGER.info("Total CPU cores (logical) : " + lcpuInfoInstance.getTotalCores());
      LOGGER.info("Total CPU sockets (physical) : " + lcpuInfoInstance.getTotalSockets());
      LOGGER.info("CPU vendor id : " + lcpuInfoInstance.getVendor());
    } catch (Exception sigarEx) {
      LOGGER.error("Exception encountered : " + sigarEx.getMessage());
    }

    LOGGER.info("\n**************************************\n");
  }
  
  /**
   * 
   */
  private static void getDataFromMultiProcCpu() {
    LOGGER.info("inside getDataFromCpuInfoClass");
    LOGGER.info("**************************************");
    LOGGER.info("*** Informations about the CPU: ***");
    LOGGER.info("**************************************\n");

    try {
      CpuInfo lcpuInfoInstance = new CpuInfo();
      LOGGER.info("CPU cache size : " + lcpuInfoInstance.getCacheSize());
      LOGGER.info("Number of CPU cores per CPU socket : " + lcpuInfoInstance.getCoresPerSocket());
      LOGGER.info("CPU speed : " + lcpuInfoInstance.getMhz());
      LOGGER.info("CPU model : " + lcpuInfoInstance.getModel());
      LOGGER.info("Total CPU cores (logical) : " + lcpuInfoInstance.getTotalCores());
      LOGGER.info("Total CPU sockets (physical) : " + lcpuInfoInstance.getTotalSockets());
      LOGGER.info("CPU vendor id : " + lcpuInfoInstance.getVendor());
    } catch (Exception sigarEx) {
      LOGGER.error("Exception encountered : " + sigarEx.getMessage());
    }

    LOGGER.info("\n**************************************\n");
  }
}
