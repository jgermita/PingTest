import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingRequest {

	private String ipAddress = null;

	private String reqData = "";
	
	private int avg = 0;
	private String alias = "";

	public int n = 0;

	public PingRequest(String address, int n, String alias) {
		this.ipAddress = address;
		this.alias = alias;
		this.n = n;
		// Ping address
		try {
			Process p = Runtime.getRuntime().exec(
					"ping " + address + " -n " + n);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				reqData = reqData + s + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// System.out.println(reqData);
		
		// Parse output
		String time = "";
		// If host is unreachable, ping won't contain average stats
		if (!reqData.contains(", Average = ")) {
			time = "-1";
		} else {
			time = reqData.split(", Average = ")[1].split("ms")[0];
		}
		avg = Integer.parseInt(time);
	}

	public void ping() {
		// Ping address
		try {
			Process p = Runtime.getRuntime().exec(
					"ping " + ipAddress + " -n " + n);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				reqData = reqData + s + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// System.out.println(reqData);
		
		// Parse output
		String time = "";
		// If host is unreachable, ping won't contain average stats
		if (!reqData.contains(", Average = ")) {
			time = "-1";
		} else {
			time = reqData.split(", Average = ")[1].split("ms")[0];
		}
		avg = Integer.parseInt(time);
	}
	
	public String getAddress() {
		return ipAddress;
	}

	public int getAverageTime() {
		return avg;
	}

	public String getHealth() {
		int input = avg;
		if (input == -1) {
			return "HOST UNREACHABLE!";
		}

		double health = (1030 - (double) input) / 1000.0;
		health = health * 100.0;
		health = Math.min(health, 100.0);
		return health + "%";

	}

	public String getAlias() {
		return this.alias;
	}

	public String toString() {
		return "Pings to " + ipAddress + " took an average of " + avg
				+ "ms to complete!";
	}

}
