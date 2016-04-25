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
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;

/**
 *
 * @author Saranya
 */
public class CpuData implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(CpuData.class.getName());
	private static Sigar sigar = new Sigar();

	/**
	 * 
	 */
	public CpuData() {
		new Thread(this).start();
	}

	/**
	 * 
	 */
	private static void getDataFromCpu() {
		LOGGER.info("Inside getDataFromCpu");

		try {
			Cpu lcpuInstance = sigar.getCpu();
			if (lcpuInstance != null) {
				LOGGER.info("Total system cpu idle time : " + lcpuInstance.getIdle());
				LOGGER.info("Total system cpu time servicing interrupts : " + lcpuInstance.getIrq());
				LOGGER.info("Total system cpu nice time : " + lcpuInstance.getNice());
				LOGGER.info("Total system cpu time servicing softirqs : " + lcpuInstance.getSoftIrq());
				LOGGER.info("Total system cpu involuntary wait time : " + lcpuInstance.getStolen());
				LOGGER.info("Total system cpu kernel time : " + lcpuInstance.getSys());
				LOGGER.info("Total system cpu time : " + lcpuInstance.getTotal());
				LOGGER.info("Total system cpu user time : " + lcpuInstance.getUser());
				LOGGER.info("Total system cpu io wait time : " + lcpuInstance.getWait());
			}
		} catch (SigarException sigarEx) {
			LOGGER.error("Exception encountered : " + sigarEx.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	private static void getDataFromCpuInfo() {
		LOGGER.info("Inside getDataFromCpuInfo");

		try {
			CpuInfo[] lcpuInfoInstance = sigar.getCpuInfoList();
			int count = 0;
			int lintArrLength = lcpuInfoInstance.length;
			if (lcpuInfoInstance != null && lintArrLength > 0) {
				while (count < lintArrLength) {
					LOGGER.info("CPU cache size : " + lcpuInfoInstance[count].getCacheSize());
					LOGGER.info("Number of CPU cores per CPU socket : " + lcpuInfoInstance[count].getCoresPerSocket());
					LOGGER.info("CPU speed : " + lcpuInfoInstance[count].getMhz());
					LOGGER.info("CPU model : " + lcpuInfoInstance[count].getModel());
					LOGGER.info("Total CPU cores (logical) : " + lcpuInfoInstance[count].getTotalCores());
					LOGGER.info("Total CPU sockets (physical) : " + lcpuInfoInstance[count].getTotalSockets());
					LOGGER.info("CPU vendor id : " + lcpuInfoInstance[count].getVendor());
				}
			}
		} catch (Exception sigarEx) {
			LOGGER.error("Exception encountered : " + sigarEx.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	private static void getDataFromCpuPerc() {
		LOGGER.info("Inside getDataFromCpuPerc (Output in Percentage)");

		try {
			CpuPerc lcpuInfoInstance = sigar.getCpuPerc();
			LOGGER.info("Sum of User + Sys + Nice + Wait : " + lcpuInfoInstance.getCombined() * 100);
			LOGGER.info("idle : " + lcpuInfoInstance.getIdle() * 100);
			LOGGER.info("cpu time servicing interrupts : " + lcpuInfoInstance.getIrq() * 100);
			LOGGER.info("cpu nice time : " + lcpuInfoInstance.getNice() * 100);
			LOGGER.info("system : " + lcpuInfoInstance.getSys() * 100);
			LOGGER.info("user : " + lcpuInfoInstance.getUser() * 100);
			LOGGER.info("wait : " + lcpuInfoInstance.getWait() * 100);
			LOGGER.info("stolen : " + lcpuInfoInstance.getStolen() * 100);
		} catch (Exception sigarEx) {
			LOGGER.error("Exception encountered : " + sigarEx.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	public void run() {
		getDataFromCpu();
		getDataFromCpuInfo();
		getDataFromCpuPerc();
	}
}
