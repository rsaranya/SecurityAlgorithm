package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

public class ProcessData implements Runnable {
	private static Sigar sigar = new Sigar();
	private final Logger LOGGER = LogManager.getLogger(FileSystemData.class.getName());
	private JSONObject lobjJsonProcessData = new JSONObject();

	/**
	 * 
	 */
	public ProcessData() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		getProcessData();
		
		synchronized (GlobalObjects.larrlstJson) {
			if (lobjJsonProcessData != null) {
				GlobalObjects.larrlstJson.add(lobjJsonProcessData);
			}
		}
	}

	private void getProcessData() {
		try {
			long[] llngProcList = sigar.getProcList();
			for(int count = 0; count < llngProcList.length; count++)
			{
				sigar.getProcArgs(llngProcList[count]); 
			}
			
		} catch (Exception sigarEx) {
			LOGGER.error("Exception in getProcessData : " + sigarEx.getMessage());
		}
	}
}
