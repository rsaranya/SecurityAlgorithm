package Util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import WindowsService.MemoryData;


public class ConnectServer implements Runnable  {
	private static final Logger LOGGER =  LogManager.getLogger(MemoryData.class.getName());
	
	public ConnectServer()
	{
		new Thread(this).start();
	}

	public void sendJsonToServer(JSONObject pobjJsonToSend) {
		// TODO Auto-generated method stub
		try{
		//Code to send serialized Json to the server
		pobjJsonToSend.toJSONString();
		}catch(Exception ex)
		{
			LOGGER.error("Exception in sendJsonToServer : "+ ex.getMessage());
		}
	}

	public void run() {
		synchronized (GlobalObjects.larrlstJson) {
			while(!GlobalObjects.larrlstJson.isEmpty())
				sendJsonToServer(GlobalObjects.larrlstJson.remove(0));
		}
	}
}
