package WindowsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.NetConnection;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetRoute;
import org.hyperic.sigar.NetStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.json.simple.JSONObject;

import Util.GlobalObjects;

public class NetworkData implements Runnable {

	private static Sigar sigar = new Sigar();
	private final Logger LOGGER = LogManager.getLogger();
	private JSONObject lobjJsonFileSystemData = new JSONObject();

	/**
	 * 
	 */
	public NetworkData() {
		new Thread(this).start();
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		getNetworkData();
		getNetworkInterfaceList();
		getNetConnections();
		getNetRoute();
		synchronized (GlobalObjects.larrlstJson) {
			if (lobjJsonFileSystemData != null) {
				GlobalObjects.larrlstJson.add(lobjJsonFileSystemData);
			}
		}
	}

	/**
	 * 
	 */
	private void getNetworkInterfaceList() {
		String[] larrNetInterface = null;
		try {
			larrNetInterface = sigar.getNetInterfaceList();
			for (String lstrValue : larrNetInterface) {
				getNetworkInterfaceConfig(lstrValue);
				getNetworkInterfaceStat(lstrValue);
			}
		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkInterfaceList : " + sigarEx.getMessage());
		} finally {
			larrNetInterface = null;
		}
	}

	@SuppressWarnings("unchecked")
	private void getNetworkInterfaceStat(String lstrValue) {
		NetStat lobjNetStat = null;
		try {
			lobjNetStat = sigar.getNetStat();
			String lstrRetrievedValues = "";

			lstrRetrievedValues = "" + lobjNetStat.getAllInboundTotal();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TotalInbound", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TotalInbound : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetStat.getAllOutboundTotal();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TotalOutbound", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TotalOutbound : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetStat.getTcpEstablished();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TcpEstablished", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TcpEstablished : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetStat.getTcpIdle();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TcpIdle", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TcpIdle : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetStat.getTcpListen();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TcpListen", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TcpListen : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetStat.getTcpTimeWait();
			lobjJsonFileSystemData.put("NetworkData_NetStat_TcpWaitTime", lstrRetrievedValues);
			LOGGER.info("NetworkData_NetStat_TcpWaitTime : " + lstrRetrievedValues);

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkInterfaceList : " + sigarEx.getMessage());
		} finally {
			lobjNetStat = null;
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getNetConnections() {
		NetConnection[] lobjNetConnections = null;
		try {
			int flags = NetFlags.CONN_SERVER | NetFlags.CONN_CLIENT | NetFlags.CONN_TCP;

			lobjNetConnections = sigar.getNetConnectionList(flags);
			int count = 0;
			String lstrRetrievedValues = "";

			for (NetConnection conn : lobjNetConnections) {

				lstrRetrievedValues = "" + conn.getLocalAddress();
				lobjJsonFileSystemData.put("NetworkData_NetConn" + (count + 1) + "_LocalAddress", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetConn" + (count + 1) + "_LocalAddress : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + conn.getLocalPort();
				lobjJsonFileSystemData.put("NetworkData_NetConn" + (count + 1) + "_LocalPort", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetConn" + (count + 1) + "_LocalPort : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + conn.getStateString();
				lobjJsonFileSystemData.put("NetworkData_NetConn" + (count + 1) + "_State", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetConn" + (count + 1) + "_State : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + conn.getTypeString();
				lobjJsonFileSystemData.put("NetworkData_NetConn" + (count + 1) + "_Type", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetConn" + (count + 1) + "_Type : " + lstrRetrievedValues);

				count++;
			}
		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkData : " + sigarEx.getMessage());
		} finally {
			lobjNetConnections = null;
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getNetworkInterfaceConfig(String pstrValue) {
		LOGGER.info("Inside getNetworkConnections ");
		NetInterfaceConfig lobjNetInterfaceConfig = null;
		try {
			lobjNetInterfaceConfig = sigar.getNetInterfaceConfig(pstrValue);
			String lstrRetrievedValues = "";

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getAddress();
			lobjJsonFileSystemData.put("NetworkData_Config_Address", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Address : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getDescription();
			lobjJsonFileSystemData.put("NetworkData_Config_Description", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Description : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getBroadcast();
			lobjJsonFileSystemData.put("NetworkData_Config_Broadcast", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Broadcast : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getDestination();
			lobjJsonFileSystemData.put("NetworkData_Config_Destination", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Destination : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getFlags();
			lobjJsonFileSystemData.put("NetworkData_Config_Flags", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Flags : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getHwaddr();
			lobjJsonFileSystemData.put("NetworkData_Config_HwAddress", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_HwAddress : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getMetric();
			lobjJsonFileSystemData.put("NetworkData_Config_Metric", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Metric : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getMtu();
			lobjJsonFileSystemData.put("NetworkData_Config_Mtu", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Mtu : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getName();
			lobjJsonFileSystemData.put("NetworkData_Config_Name", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Name : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getNetmask();
			lobjJsonFileSystemData.put("NetworkData_Config_Netmask", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Natmask : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInterfaceConfig.getType();
			lobjJsonFileSystemData.put("NetworkData_Config_Type", lstrRetrievedValues);
			LOGGER.info("NetworkData_Config_Type : " + lstrRetrievedValues);

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkData : " + sigarEx.getMessage());
		} finally {
			lobjNetInterfaceConfig = null;
		}
		LOGGER.info("**************************************");
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getNetworkData() {
		LOGGER.info("Inside getNetworkData ");
		NetInfo lobjNetInfo = null;
		try {
			lobjNetInfo = sigar.getNetInfo();

			String lstrRetrievedValues = "";

			lstrRetrievedValues = "" + lobjNetInfo.getHostName();
			lobjJsonFileSystemData.put("NetworkData_HostName", lstrRetrievedValues);
			LOGGER.info("Host Name : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInfo.getPrimaryDns();
			lobjJsonFileSystemData.put("NetworkData_PrimaryDNS", lstrRetrievedValues);
			LOGGER.info("Primary DNS : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInfo.getDefaultGateway();
			lobjJsonFileSystemData.put("NetworkData_DefaultGateway", lstrRetrievedValues);
			LOGGER.info("Default Gateway : " + lstrRetrievedValues);

			lstrRetrievedValues = "" + lobjNetInfo.getSecondaryDns();
			lobjJsonFileSystemData.put("NetworkData_SecondaryDNS", lstrRetrievedValues);
			LOGGER.info("Secondary DNS : " + lstrRetrievedValues);

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkData : " + sigarEx.getMessage());
		} finally {
			lobjNetInfo = null;
		}
		LOGGER.info("**************************************");
	}

	@SuppressWarnings("unchecked")
	private void getNetRoute() {
		NetRoute[] lobjNetRouteArr = null;
		try {
			String lstrRetrievedValues = "";
			int count = 0;
			lobjNetRouteArr = sigar.getNetRouteList();
			for (NetRoute lobjNetRoute : lobjNetRouteArr) {
				lstrRetrievedValues = "" + lobjNetRoute.getDestination();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_Destination", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_Destination : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjNetRoute.getFlags();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_Flags", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_Flags : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjNetRoute.getGateway();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_Gateway", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_Gateway : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjNetRoute.getIfname();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_IfName", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_IfName : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjNetRoute.getMask();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_Mask", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_Mask : " + lstrRetrievedValues);

				lstrRetrievedValues = "" + lobjNetRoute.getMetric();
				lobjJsonFileSystemData.put("NetworkData_NetRoute" + (count + 1) + "_Metric", lstrRetrievedValues);
				LOGGER.info("NetworkData_NetRoute" + (count + 1) + "_Metric : " + lstrRetrievedValues);

				count++;
			}

		} catch (SigarException sigarEx) {
			LOGGER.error("Exception in getNetworkData : " + sigarEx.getMessage());
		} finally {
			lobjNetRouteArr = null;
		}
	}
}
