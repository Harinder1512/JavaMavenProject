import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DB_BackupSample {

	public static void main(String[] args) {

		try {
			Date objDate = new Date();

			SimpleDateFormat formatter = new SimpleDateFormat("E dd-MMMM-yyyy ");

			String strDate = formatter.format(objDate);

			System.out.println(strDate);

			Runtime rt = Runtime.getRuntime();
			Process p;
			ProcessBuilder pb;
			rt = Runtime.getRuntime();
			pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.2\\bin\\pg_dump.exe", "--host", "localhost",
					"--port", "5432", "--username", "postgres", "--no-password", "--format", "tar", "--blobs",
					"--verbose", "--file", "D://database//DB_backup  " + strDate + ".sql", "MarriageDev");

			try {

				final Map<String, String> env = pb.environment();
				env.put("PGPASSWORD", "postgres");
				p = pb.start();
				final BufferedReader r = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String line = r.readLine();
				while (line != null) {
					System.err.println(line);
					line = r.readLine();
				}
				r.close();
				p.waitFor();
				System.out.println("Message " + p.exitValue());
				if (p.exitValue() == 0) {
					System.out.println("Success");
				} else {
					System.out.println("Unsuccessful");
				}

			} catch (IOException | InterruptedException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println("In Exception" + e.getMessage());
		}
	}

}
