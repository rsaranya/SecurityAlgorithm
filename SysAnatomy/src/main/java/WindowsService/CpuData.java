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
	 * @param psigar
	 */
	public CpuData() {
		Thread lthreadCpuData = new Thread(this);
		lthreadCpuData.start();
	}

	/**
	 * 
	 */
	private static void getDataFromCpu() {
		LOGGER.info("inside getDataFromCpu");

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
		LOGGER.info("inside getDataFromCpuInfo");

		try {
			CpuInfo[] lcpuInfoInstance = sigar.getCpuInfoList();
			if (lcpuInfoInstance.length != 0) {
				LOGGER.info("CPU cache size : " + lcpuInfoInstance[0].getCacheSize());
				LOGGER.info("Number of CPU cores per CPU socket : " + lcpuInfoInstance[0].getCoresPerSocket());
				LOGGER.info("CPU speed : " + lcpuInfoInstance[0].getMhz());
				LOGGER.info("CPU model : " + lcpuInfoInstance[0].getModel());
				LOGGER.info("Total CPU cores (logical) : " + lcpuInfoInstance[0].getTotalCores());
				LOGGER.info("Total CPU sockets (physical) : " + lcpuInfoInstance[0].getTotalSockets());
				LOGGER.info("CPU vendor id : " + lcpuInfoInstance[0].getVendor());
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
