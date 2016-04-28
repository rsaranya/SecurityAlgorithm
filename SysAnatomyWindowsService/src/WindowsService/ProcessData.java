package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcExe;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

public class ProcessData implements Runnable {
	private static Sigar sigar = new Sigar();
	private final Logger LOGGER = LogManager.getLogger();
	private JSONObject lobjJsonProcessData = new JSONObject();

	/**
	 * Constructor of the class Spawns a thread which fetches data from the user
	 * system.
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

	/**
	 * Calls functions to fetch process related data.
	 * Fetched the list of Process Id's from the Sigar class instance.
	 */
	private void getProcessData() {
		try {
			long[] llngProcList = sigar.getProcList();

			long pid = 0;
			for (int count = 0; count < llngProcList.length; count++) {
				pid = llngProcList[count];

				getDataFromProcMem(pid);
				getDataFromProcState(pid);
				getDataFromProcCred(pid);

				ProcExe exe = new ProcExe();
				exe = new ProcExe();
				exe.gather(sigar, pid);
				LOGGER.info(exe.getName());
			}

		} catch (Exception sigarEx) {
			LOGGER.error("Exception in getProcessData : " + sigarEx.getMessage());
		}
	}

	/**
	 * 
	 * @param pid
	 */
	private void getDataFromProcCred(long pid) {
		ProcCredName cred = new ProcCredName();
		try {
			cred = new ProcCredName();
			cred.gather(sigar, pid);
			LOGGER.info(cred.getGroup());

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getDataFromProcCred : " + sigarEx.getMessage());
		}
	}

	/**
	 * 
	 * @param pid
	 */
	private void getDataFromProcMem(long pid) {
		ProcMem memory = null;
		try {
			// String lstrRetrievedValues = "";
			memory = new ProcMem();
			memory.gather(sigar, pid);

			LOGGER.info(Long.toString(memory.getSize()));

			LOGGER.info(Long.toString(memory.getMajorFaults()));

			LOGGER.info(Long.toString(memory.getMinorFaults()));

			LOGGER.info(Long.toString(memory.getPageFaults()));

			LOGGER.info(Long.toString(memory.getResident()));

			LOGGER.info(Long.toString(memory.getShare()));

			LOGGER.info(Long.toString(memory.getSize()));

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getDataFromProcMem : " + sigarEx.getMessage());
		}
	}

	/**
	 * 
	 * @param pid
	 */
	private void getDataFromProcState(long pid) {
		ProcState state = null;
		try {
			// String lstrRetrievedValues = "";
			state = new ProcState();
			state.gather(sigar, pid);

			LOGGER.info(state.getName());

			LOGGER.info(state.getNice());

			LOGGER.info(state.getPpid());

			LOGGER.info(state.getPpid());

			LOGGER.info(state.getPriority());

			LOGGER.info(state.getProcessor());

			LOGGER.info(state.getState());

			LOGGER.info(state.getThreads());

			LOGGER.info(state.getTty());
		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getDataFromProcState : " + sigarEx.getMessage());
		}
	}
}
