
package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;

/**
 * Captures CPU related data from the client system
 *
 * @author Saranya
 */
public class CpuData implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();
	private static Sigar sigar = new Sigar();
	private JSONObject lobjJsonCpuData = new JSONObject();;

	/**
	 * Constructor of the class
	 * Spawns a thread which fetches data from the user system.
	 */
	public CpuData() {
		new Thread(this).start();
	}

	/**
	 * Fetches CPU related data and inserts into a JSON object
	 * Uses SIGAR's Cpu class to fetch details.
	 */
	@SuppressWarnings("unchecked")
	private void getDataFromCpu() {
		LOGGER.info("Inside getDataFromCpu");
		Cpu[] lobjCpuList = null;
		
		try {
			String lstrRetrievedValues = "";
			lobjCpuList = sigar.getCpuList();
			double ldblUptime = sigar.getUptime().getUptime();
		
			LOGGER.info("System Up time : " + ldblUptime);
			
			int count = 0;
			int lintArrLength = lobjCpuList.length;
			if (lobjCpuList != null && lintArrLength > 0) {
				while (count < lintArrLength) {
					lstrRetrievedValues = "" + lobjCpuList[count].getIdle();
					lobjJsonCpuData.put("CpuData_IdleTime", lstrRetrievedValues);
					LOGGER.info("Total system cpu idle time : " + lstrRetrievedValues);
					
					lstrRetrievedValues = "" + lobjCpuList[count].getIrq();
					lobjJsonCpuData.put("CpuData_TimeServicingInterupts", lstrRetrievedValues);
					LOGGER.info("Total system cpu time servicing interrupts : " + lstrRetrievedValues);
					
					lstrRetrievedValues = "" + lobjCpuList[count].getNice();
					lobjJsonCpuData.put("CpuData_NiceTime", lstrRetrievedValues);
					LOGGER.info("Total system cpu nice time : " + lobjCpuList[count].getNice());
					
					lstrRetrievedValues = "" + lobjCpuList[count].getSoftIrq();
					lobjJsonCpuData.put("CpuData_TimeServicingSoftInterupts", lstrRetrievedValues);
					LOGGER.info("Total system cpu time servicing softirqs : " + lstrRetrievedValues);
					
					lstrRetrievedValues = "" + lobjCpuList[count].getStolen();
					lobjJsonCpuData.put("CpuData_InvoluntaryWaitTime", lstrRetrievedValues);
					LOGGER.info("Total system cpu involuntary wait time : " + lstrRetrievedValues);
					
					LOGGER.info("Total system cpu kernel time : " + lobjCpuList[count].getSys());
					LOGGER.info("Total system cpu time : " + lobjCpuList[count].getTotal());
					LOGGER.info("Total system cpu user time : " + lobjCpuList[count].getUser());
					LOGGER.info("Total system cpu io wait time : " + lobjCpuList[count].getWait());
					count++;
				}
			}
		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getDataFromCpu : " + sigarEx.getMessage());
		} finally {
			lobjCpuList = null;
		}
		LOGGER.info("**************************************");
	}

	/**
	 * Fetches CPU related data and inserts into a JSON object
	 * Uses SIGAR's CpuInfo class to fetch details. 
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
			LOGGER.error("Exception in getDataFromCpuInfo : " + sigarEx.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * Fetches CPU related data and inserts into a JSON object
	 * Uses SIGAR's CpuPerc class to fetch details. 
	 */
	private static void getDataFromCpuPerc() {
		LOGGER.info("Inside getDataFromCpuPerc (Output in Percentage)");

		CpuPerc[] lArrCpuPerc = null;
		try {
			lArrCpuPerc = sigar.getCpuPercList();
			int count = 0;
			int lintArrLength = lArrCpuPerc.length;
			if (lArrCpuPerc != null && lintArrLength > 0) {
				while (count < lintArrLength) {
					LOGGER.info("Sum of User + Sys + Nice + Wait : " + lArrCpuPerc[count].getCombined() * 100);
					LOGGER.info("idle : " + lArrCpuPerc[count].getIdle() * 100);
					LOGGER.info("cpu time servicing interrupts : " + lArrCpuPerc[count].getIrq() * 100);
					LOGGER.info("cpu nice time : " + lArrCpuPerc[count].getNice() * 100);
					LOGGER.info("system : " + lArrCpuPerc[count].getSys() * 100);
					LOGGER.info("user : " + lArrCpuPerc[count].getUser() * 100);
					LOGGER.info("wait : " + lArrCpuPerc[count].getWait() * 100);
					LOGGER.info("stolen : " + lArrCpuPerc[count].getStolen() * 100);
					count++;
				}
			}
		} catch (Exception sigarEx) {
			LOGGER.error("Exception in getDataFromCpuPerc : " + sigarEx.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * Called when thread starts
	 * Calls the functions to fetch CPU related data
	 * And adds the JSON object into a global array
	 */
	public void run() {
		getDataFromCpu();
		getDataFromCpuInfo();
		getDataFromCpuPerc();
		
		synchronized (GlobalObjects.larrlstJson) {
			if (lobjJsonCpuData != null) {
				GlobalObjects.larrlstJson.add(lobjJsonCpuData);
			}
		}
	}
}
