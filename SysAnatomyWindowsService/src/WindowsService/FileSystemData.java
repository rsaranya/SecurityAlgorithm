package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.DirStat;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

public class FileSystemData implements Runnable {
	private static Sigar sigar = new Sigar();
	private final Logger LOGGER = Logger.getLogger(FileSystemData.class.getName());
	private JSONObject lobjJsonFileSystemData = new JSONObject();

	/**
	 * 
	 */
	public FileSystemData() {
		new Thread(this).start();
	}

	/**
	 * 
	 */
	private void getSystemStatistics() {
		FileSystem[] lobjlstFileSystem = null;
		try {
			lobjlstFileSystem = sigar.getFileSystemList();

			int count = 0;
			int lintarrLength = lobjlstFileSystem.length;
			if (lobjlstFileSystem != null && lintarrLength > 0) {
				while (count < lintarrLength) {
					String lstrDirectoryName = lobjlstFileSystem[count].getDirName();

					getFileSystemInfo(lobjlstFileSystem[count]);
					getFileSystemUsage(lstrDirectoryName);
					
					count++;
					LOGGER.info("**************************************");
				}
			}
		} catch (SigarException se) {
			LOGGER.error("Exception in getSystemStatistics : " + se.getMessage());
		} finally {
			lobjlstFileSystem = null;
		}

	}

	/**
	 * 
	 * @param pobjFileSystem
	 */
	@SuppressWarnings("unchecked")
	private void getFileSystemInfo(FileSystem pobjFileSystem) {
		try {
			String lstrRetrievedValues = "";
			String lstrDirectoryName = pobjFileSystem.getDirName();

			DirStat lobjDirStat = sigar.getDirStat(lstrDirectoryName);
			LOGGER.info("Disk usage : " + lobjDirStat.getDiskUsage());

			lstrRetrievedValues = pobjFileSystem.getDevName();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_DeviceName", lstrRetrievedValues);
			LOGGER.info("Device Name : " + lstrRetrievedValues);

			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_DirectoryName", lstrRetrievedValues);
			LOGGER.info("Directory Name : " + lstrDirectoryName);

			lstrRetrievedValues = "" + pobjFileSystem.getFlags();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_Flags", lstrRetrievedValues);
			LOGGER.info("Flags : " + lstrRetrievedValues);

			lstrRetrievedValues = pobjFileSystem.getOptions();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_Options", lstrRetrievedValues);
			LOGGER.info("Options : " + lstrRetrievedValues);

			lstrRetrievedValues = pobjFileSystem.getSysTypeName();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_SystemTypeName", lstrRetrievedValues);
			LOGGER.info("System Type name : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + pobjFileSystem.getType();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_Type", lstrRetrievedValues);
			LOGGER.info("Type : " + lstrRetrievedValues);

			lstrRetrievedValues = pobjFileSystem.getTypeName();
			lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_TypeName", lstrRetrievedValues);
			LOGGER.info("Type Name : " + lstrRetrievedValues);
		} catch (Exception ex) {
			LOGGER.error("Exception in getFileSystemInfo : " + ex.getMessage());
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 * @param lstrDirectoryName
	 */
	@SuppressWarnings("unchecked")
	private void getFileSystemUsage(String lstrDirectoryName) {
		LOGGER.info("Inside getFileSystemUsage ");

		FileSystemUsage lobjFileSysUsage = new FileSystemUsage();
		try {
			lobjFileSysUsage.gather(sigar, lstrDirectoryName);

			if (lobjFileSysUsage != null) {
				String lstrRetrievedValues = "";

				lstrRetrievedValues = "" + lobjFileSysUsage.getAvail() / 1024.0 / 1024.0;
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_AvailableSpace", lstrRetrievedValues);
				LOGGER.info("Available : " + lstrRetrievedValues + " GB");

				lstrRetrievedValues = "" + lobjFileSysUsage.getDiskQueue();
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_DiskQueue", lstrRetrievedValues);
				LOGGER.info("Disk Queue : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjFileSysUsage.getFiles();
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_NumberOfFiles", lstrRetrievedValues);
				LOGGER.info("Files : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjFileSysUsage.getFreeFiles();
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_FreeFiles", lstrRetrievedValues);
				LOGGER.info("Free Files : " + lobjFileSysUsage.getFreeFiles());

				lstrRetrievedValues = "" + lobjFileSysUsage.getFree() / 1024.0 / 1024.0;
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_FreeSpace", lstrRetrievedValues);
				LOGGER.info("Free : " + lstrRetrievedValues + " GB");

				lstrRetrievedValues = "" + lobjFileSysUsage.getTotal() / 1024.0 / 1024.0;
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_TotalSpace", lstrRetrievedValues);
				LOGGER.info("Total : " + lstrRetrievedValues + " GB");

				lstrRetrievedValues = "" + lobjFileSysUsage.getUsed() / 1024.0 / 1024.0;
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_UsedSpace", lstrRetrievedValues);
				LOGGER.info("Used : " + lstrRetrievedValues + " GB");

				lstrRetrievedValues = "" + lobjFileSysUsage.getUsePercent() * 100;
				lobjJsonFileSystemData.put("FileSystem_" + lstrDirectoryName + "_UsedSpacePercent",
						lstrRetrievedValues);
				LOGGER.info("Use Percentage : " + lstrRetrievedValues + " %");
			}
		} catch (SigarException e) {
			LOGGER.error("Exception in getFileSystemUsage : " + e.getMessage());
		} finally {
			lobjFileSysUsage = null;
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	public void run() {
		getSystemStatistics();
		synchronized (GlobalObjects.larrlstJson) {
			if (lobjJsonFileSystemData != null) {
				GlobalObjects.larrlstJson.add(lobjJsonFileSystemData);
			}
		}
	}

}
