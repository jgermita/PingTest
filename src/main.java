import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class main {


	public static void main(String[] args) {

		String[] addresses = { "google.com", "facebook.com", "microsoft.com",
				"127.0.0.1" };

		do {
			PingRequestReport report = new PingRequestReport(
					System.currentTimeMillis());
			for (String s : addresses) {
				report.add(new PingRequest(s));
			}

			System.out.println(report.toString());

			System.out.println("\n\n");

			try {
				File f = new File("index.html");

				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(report.toHtml());
				bw.close();

				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);


	}


}
