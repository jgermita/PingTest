import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PingRequestReport {
	
	ArrayList<PingRequest> list = new ArrayList();
	long time = 0;
	
	public PingRequestReport(long time) {
		this.time = time;
	}
	
	public void add(PingRequest p) {
		list.add(p);
	}

	public String toString() {
		long yourmilliseconds = time;
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(yourmilliseconds);
		String data = ("Time: " + resultdate.toString()) + "\n";

		data = data + ("Address\t\tAverage Time\tHealth") + "\n";
		for (PingRequest p : list) {
			
			data = data + (p.getAddress() + "\t" + p.getAverageTime() + "\t\t"
					+ p.getHealth()) + "\n";
		}
		return data;
	}

	public String toHtml() {
		String data = toString().replace("\n", "<br>");

		return "<meta http-equiv=\"refresh\" content=\"5\" >"
				+ "<META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\">"
				+ "<META HTTP-EQUIV=\"EXPIRES\" CONTENT=\"Mon, 22 Jul 2002 11:12:01 GMT\"><br><br>"
				+ data;
	}

}
