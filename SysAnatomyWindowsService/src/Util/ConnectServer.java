package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import WindowsService.MemoryData;

public class ConnectServer implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger(MemoryData.class.getName());

	public ConnectServer() {
		new Thread(this).start();
	}

	public void sendJsonToServer(JSONObject pobjJsonToSend) {
		// TODO Auto-generated method stub
		try {
			
			// Now pass JSON File Data to REST Service
			try {
				LOGGER.info("Inside sendJsonToServer");
				URL url = new URL("http://localhost:8080/SysAnatomyWebService/api/crunchifyService");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(pobjJsonToSend.toString());
				out.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				while (in.readLine() != null) {
				}
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}

		} catch (Exception ex) {
			LOGGER.error("Exception in sendJsonToServer : " + ex.getMessage());
		}
	}

	public void run() {
		synchronized (GlobalObjects.larrlstJson) {
			while (true) {
				if (!GlobalObjects.larrlstJson.isEmpty()) {
					sendJsonToServer(GlobalObjects.larrlstJson.remove(0));
				}
			}
		}
	}
}