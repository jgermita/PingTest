import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class main {


	public static void main(String[] args) {

		String outputFile = "index.html";
		int pings = 4;
		PingRequestReport report = new PingRequestReport(
				System.currentTimeMillis());
//		for (String s : addresses) {
//			System.out.println("Pinging " + s + "...");
//			report.add(new PingRequest(s, 8));
//		}
		
		int interval = 60;
		try {
			File config = new File("config.txt");
			BufferedReader br = new BufferedReader(new FileReader(config));

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("output=")) {
					outputFile = line.split("output=")[1];
				} else if (line.startsWith("pings=")) {
					pings = Integer.parseInt(line.split("pings=")[1]);
				} else if (line.startsWith("a=")) {
					String address = line.split("a=")[1].split(",")[0];
					String alias = line.split("a=")[1].split(",")[1];
					report.add(new PingRequest(address, pings, alias));
				} else if (line.startsWith("interval=")) {
					interval = Integer.parseInt(line.split("interval=")[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		do {
			report = new PingRequestReport(
					System.currentTimeMillis());
			try {
				File config = new File("config.txt");
				BufferedReader br = new BufferedReader(new FileReader(config));

				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.startsWith("output=")) {
						outputFile = line.split("output=")[1];
					} else if (line.startsWith("pings=")) {
						pings = Integer.parseInt(line.split("pings=")[1]);
					} else if (line.startsWith("a=")) {
						String address = line.split("a=")[1].split(",")[0];
						String alias = line.split("a=")[1].split(",")[1];
						report.add(new PingRequest(address, pings, alias));
					} else if (line.startsWith("interval=")) {
						interval = Integer.parseInt(line.split("interval=")[1]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			report.setInterval(interval);
			

			report.update();

			System.out.println(report.toString());

			System.out.println("\n\n");

			// Write to html file

			try {
				File f = new File("index.html");

				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(report.toHtml());
				bw.close();

				Thread.sleep(interval * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);


	}


}
