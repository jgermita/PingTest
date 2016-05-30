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
		String data = "";
		for (PingRequest p : list) {
			
			data = data + (p.getAddress() + "\t" + p.getAverageTime() + "\t\t"
					+ p.getHealth()) + "\n";
		}
		return data;
	}

	public void update() {
		this.time = System.currentTimeMillis();
		for (PingRequest p : list) {
			p.ping();
		}
	}

	public String toHtml() {
		String data = "";
		// String data = toString().replace("\n", "<br>");

		long yourmilliseconds = time;
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(yourmilliseconds);

		data = data + ("<head></head>");
		data = data
				+ ("<br><b>Last Updated: </b>" + (resultdate.toString()) + "<br>");

		data = data
				+ ("<table border=\"1\" class=\"sortable\"><TR id=\"headers\"><td>Address<td>Comments<td>Avg Ping Time<td>Health<br>");

		for (PingRequest p : list) {
			data = data + ("<TR id=\"data\"><td>");

			String healthColor = "";
			double health = 0.0;
			if (p.getHealth().equals("HOST UNREACHABLE!")) {
				health = -1.0;
			} else {
				health = Double.parseDouble(p.getHealth().split("%")[0]);
			}

			if (health <= 0.0) {
				healthColor = "7c7c7c";
			} else if (health > 0 && health <= 30.0) {
				healthColor = "ff0000";
			} else if (health > 50.0 && health < 80.0) {
				healthColor = "ffff00";
			} else {
				healthColor = "00ff00";
			}

			data = data
					+ (p.getAddress() + "<td>" + p.getAlias() + "<td>"
							+ p.getAverageTime()
							+ "<td bgcolor=\"" + healthColor + "\">" + p
.getHealth()) + "<br>";
		}

		return "<meta http-equiv=\"refresh\" content=\"60\" >"
				+ "<META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\">"
				+ "<META HTTP-EQUIV=\"EXPIRES\" CONTENT=\"Mon, 22 Jul 2002 11:12:01 GMT\">"
				+ "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"tables.css\"><meta name=\"viewport\" content=\"width=device-width, user-scalable=no\"><script src=\"sorttable.js\"></script></head>"
				+ "<br><br>"

				+ data;
	}

}
