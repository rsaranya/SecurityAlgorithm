package WindowsService;

import org.apache.log4j.Logger;
import org.hyperic.sigar.FileInfo;
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

					FileInfo lobjFileInfo = sigar.getFileInfo(lstrDirectoryName);
					lobjFileInfo.enableDirStat(true);

					LOGGER.info("Device Name : " + lobjlstFileSystem[count].getDevName());
					LOGGER.info("Directory Name : " + lstrDirectoryName);
					LOGGER.info("Flags : " + lobjlstFileSystem[count].getFlags());
					LOGGER.info("Options : " + lobjlstFileSystem[count].getOptions());
					LOGGER.info("System Type name : " + lobjlstFileSystem[count].getSysTypeName());
					LOGGER.info("Type : " + lobjlstFileSystem[count].getType());
					LOGGER.info("Type Name : " + lobjlstFileSystem[count].getTypeName());
				
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
