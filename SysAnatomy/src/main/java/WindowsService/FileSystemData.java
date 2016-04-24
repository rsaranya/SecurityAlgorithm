package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class FileSystemData implements Runnable {
	private static Sigar sigar = new Sigar();
	private static final Logger LOGGER = Logger.getLogger(SystemData.class.getName());

	/**
	 * 
	 */
	public FileSystemData() {
		new Thread(this).start();
	}

	/**
	 * 
	 */
	private static void getSystemStatistics() {
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
			LOGGER.error("Exception encountered : " + se.getMessage());
		}

	}

	/**
	 * 
	 * @param pobjFileSystem
	 */
	private static void getFileSystemInfo(FileSystem pobjFileSystem) {
		LOGGER.info("Device Name : " + pobjFileSystem.getDevName());
		LOGGER.info("Directory Name : " + pobjFileSystem.getDirName());
		LOGGER.info("Flags : " + pobjFileSystem.getFlags());
		LOGGER.info("Options : " + pobjFileSystem.getOptions());
		LOGGER.info("System Type name : " + pobjFileSystem.getSysTypeName());
		LOGGER.info("Type : " + pobjFileSystem.getType());
		LOGGER.info("Type Name : " + pobjFileSystem.getTypeName());
	}

	/**
	 * 
	 * @param lstrDirectoryName
	 */
	private static void getFileSystemUsage(String lstrDirectoryName) {
		FileSystemUsage lobjFileSysUsage;
		try {
			lobjFileSysUsage = sigar.getFileSystemUsage(lstrDirectoryName);

			if (lobjFileSysUsage != null) {
				LOGGER.info("\t  File System Usage ");
				LOGGER.info("Available : " + lobjFileSysUsage.getAvail() / 1024.0 / 1024.0);
				LOGGER.info("Disk Queue : " + lobjFileSysUsage.getDiskQueue());
				LOGGER.info("Files : " + lobjFileSysUsage.getFiles());
				LOGGER.info("Free Files : " + lobjFileSysUsage.getFreeFiles());
				LOGGER.info("Free : " + lobjFileSysUsage.getFree() / 1024.0 / 1024.0);
				LOGGER.info("Total : " + lobjFileSysUsage.getTotal() / 1024.0 / 1024.0);
				LOGGER.info("Used : " + lobjFileSysUsage.getUsed() / 1024.0 / 1024.0);
				LOGGER.info("Use Percentage : " + lobjFileSysUsage.getUsePercent() * 100);
			}
		} catch (SigarException e) {
			LOGGER.error("Exception encountered : " + e.getMessage());
		}
	}

	public void run() {
		getSystemStatistics();
	}
}
