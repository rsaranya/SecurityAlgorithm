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
/**
 *
 * @author Saranya
 */
public class CpuData {

  private static final Logger LOGGER = Logger.getLogger(CpuData.class.getName());
  private static Sigar sigar = new Sigar();
  
  public CpuData(Sigar psigar)
  {
    this.sigar = psigar;
    getCpuData();
  }
  
  private void getCpuData() {
    try {
      Cpu cpuInfo = new Cpu();
      cpuInfo.gather(sigar);

      LOGGER.info("Total system cpu idle time : "
        + cpuInfo.getIdle());
      LOGGER.info("Total system cpu time servicing interrupts : "
        + cpuInfo.getIrq());
      LOGGER.info("Total system cpu nice time : "
        + cpuInfo.getNice());
      LOGGER.info("Total system cpu time servicing softirqs : "
        + cpuInfo.getSoftIrq());
      LOGGER.info("Total system cpu involuntary wait time : "
        + cpuInfo.getStolen());
      LOGGER.info("Total system cpu kernel time : "
        + cpuInfo.getSys());
      LOGGER.info("Total system cpu time : "
        + cpuInfo.getTotal());
      LOGGER.info("Total system cpu user time : "
        + cpuInfo.getUser());
      LOGGER.info("Total system cpu io wait time : "
        + cpuInfo.getWait());
    } catch (SigarException sigarEx) {
      LOGGER.error("Exception encountered : " + sigarEx.getMessage());
    }
  }
}
