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

/**
 *
 * @author Saranya
 */
public class MemoryData {

  private static final Logger LOGGER = Logger.getLogger(MemoryData.class.getName());
  private static Sigar sigar = new Sigar();
  
  public MemoryData()
  {
    getDataFromMemClass();
  }
  

  public static void getDataFromMemClass() {
   
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

    LOGGER.info("Used Percentage................: " + mem.getUsedPercent()
      / 1024 / 1024 + " MB");
    LOGGER.info("Free Percentage................: " + mem.getFreePercent()
      / 1024 / 1024 + " MB");
    
    LOGGER.info("\n**************************************\n");

  }
}
