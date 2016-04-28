package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcCred;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcTime;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.ptql.ProcessFinder;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

public class ProcessData implements Runnable {
	private static Sigar sigar = new Sigar();
	private final Logger LOGGER = LogManager.getLogger();
	private JSONObject lobjJsonProcessData = new JSONObject();

	/**
	 * 
	 */
	public ProcessData() {
		// Thread(this).start();
		getProcessData();
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
			
			ProcMem memory = new ProcMem();
			ProcState state = new ProcState();
			ProcCred cred = new ProcCred();
			
			//String lstrRetrievedValues = "";
			long pid = 0;
			for (int count = 0; count < llngProcList.length; count++) {
				pid = 0;
				pid = llngProcList[count];
				
				memory = new ProcMem();
				memory.gather(sigar, pid);
				System.out.println(Long.toString(memory.getSize()));

				state = new ProcState();
				state.gather(sigar, pid);
				System.out.println(state.getName());

				cred = new ProcCred();
				cred.gather(sigar, pid);
				System.out.println(state.getName());
				

			}

		} catch (Exception sigarEx) {
			LOGGER.error("Exception in getProcessData : " + sigarEx.getMessage());
		}
	}
}
