/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.SysInfo;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

/**
 *
 * @author Saranya
 */
public class SystemData implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(SystemData.class.getName());
	private static Sigar sigar = new Sigar();
	private JSONObject lobjJsonSystemData = null;

	public SystemData() {
		Thread lthreadSystemData = new Thread(this);
		lthreadSystemData.start();
	}

	private static void getSystemStatistics() {
		CpuPerc lobjCpuPerc = null;
		FileSystemUsage lobjFileSystemUsage = null;
		try {
			lobjCpuPerc = sigar.getCpuPerc();
			lobjFileSystemUsage = sigar.getFileSystemUsage("C:");
		} catch (SigarException se) {
			LOGGER.error(se);
		}

		LOGGER.info("combined " + (lobjCpuPerc.getCombined() * 100) + "\t");
		LOGGER.info("used " + lobjFileSystemUsage.getUsePercent() + "\n");

		LOGGER.info("**************************************");
	}

	public static void getSystemDetails() {
		OperatingSystem lobjOperatingSystem = OperatingSystem.getInstance();

		try {
			if (lobjOperatingSystem != null) {
				LOGGER.info("System Architecture : " + lobjOperatingSystem.getArch());
				LOGGER.info("Machine Endian : " + lobjOperatingSystem.getCpuEndian());
				LOGGER.info("Machine Data Model : " + lobjOperatingSystem.getDataModel());
				LOGGER.info("System Description : " + lobjOperatingSystem.getDescription());
				LOGGER.info("Machine Name : " + lobjOperatingSystem.getName());
				LOGGER.info("Machine Vendor : " + lobjOperatingSystem.getVendor());
				LOGGER.info("Machine Vendor Code Name : " + lobjOperatingSystem.getVendorCodeName());
				LOGGER.info("Machine Vendor Name : " + lobjOperatingSystem.getVendorName());
				LOGGER.info("Machine Vendor Version : " + lobjOperatingSystem.getVendorVersion());
				LOGGER.info("Machine Version : " + lobjOperatingSystem.getVersion());
			}
		} catch (Exception sigarEx) {
			LOGGER.error("Exception occured : " + sigarEx.getMessage());
		}

	}

	public void run() {
		//getSystemStatistics();
		getSystemDetails();
		synchronized (GlobalObjects.larrlstJson) {
			GlobalObjects.larrlstJson.add(lobjJsonSystemData);
		}

	}
}
