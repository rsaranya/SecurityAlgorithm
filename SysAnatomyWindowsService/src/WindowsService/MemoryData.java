/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

/**
 *
 * @author Saranya
 */
public class MemoryData implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();
	private static Sigar lobjSigar = new Sigar();
	private JSONObject lobjJsonMemData = new JSONObject();;

	public MemoryData() {
		new Thread(this).start();
	}

	@SuppressWarnings("unchecked")
	private void getDataFromMemClass() {

		Mem lobjMemClass = null;
		try {
			lobjMemClass = lobjSigar.getMem();
			if (lobjMemClass != null) {
				lobjJsonMemData = new JSONObject();
				String lstrRetrievedValues = "";

				lstrRetrievedValues = "" + lobjMemClass.getActualFree() / 1024 / 1024;
				lobjJsonMemData.put("MemoryData_ActualFree", lstrRetrievedValues);
				LOGGER.info("Actual total free system memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getActualUsed() / 1024 / 1024;
				lobjJsonMemData.put("MemoryData_ActualUsed", lstrRetrievedValues);
				LOGGER.info("Actual total used system memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getFree() / 1024 / 1024;
				lobjJsonMemData.put("MemoryData_TotalFreeMemory", lstrRetrievedValues);
				LOGGER.info("Total free system memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getRam();
				lobjJsonMemData.put("MemoryData_RAM", lstrRetrievedValues);
				LOGGER.info("System Random Access Memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getTotal() / 1024 / 1024;
				lobjJsonMemData.put("MemoryData_TotalSystemMemory", lstrRetrievedValues);
				LOGGER.info("Total system memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getUsed() / 1024 / 1024;
				lobjJsonMemData.put("MemoryData_UsedSystemMemory", lstrRetrievedValues);
				LOGGER.info("Total used system memory : " + lstrRetrievedValues + " MB");

				lstrRetrievedValues = "" + lobjMemClass.getUsedPercent();
				lobjJsonMemData.put("MemoryData_PercentageOfUsedMemory", lstrRetrievedValues);
				LOGGER.info("Used Percentage : " + lstrRetrievedValues + " %");

				lstrRetrievedValues = "" + lobjMemClass.getFreePercent();
				lobjJsonMemData.put("MemoryData_PercentageOfFreeMemory", lstrRetrievedValues);
				LOGGER.info("Free Percentage : " + lstrRetrievedValues + " %");
			}

		} catch (SigarException lsigarEx) {
			LOGGER.error("Exception encountered : " + lsigarEx.getMessage());
		} finally {
			lobjMemClass = null;
			lobjJsonMemData = null;
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	public void run() {
		getDataFromMemClass();

		synchronized (GlobalObjects.larrlstJson) {
			if (lobjJsonMemData != null) {
				GlobalObjects.larrlstJson.add(lobjJsonMemData);
			}
		}
	}
}
