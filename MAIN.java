package Lab_5;

public class MAIN {

	static int N = 3; // number of CPU's
	static int K = 20; // number of UserJobs
	static CPUmonitor CPU = new CPUmonitor(); // Gobally visible CPU

	/*
	 * Main method for Lab_5 package
	 */
	public static void main(String[] args) {

		DiskDrive monitor = null;
		try {
			monitor = new DiskDrive(); // Simulating booting up disk drive
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * This loop creates a new UserJob and immediately starts it, K times.
		 * Every thread recieves a unique ID name and the DiskDrive monitor.
		 */
		for (int i = 1; i <= K; i++)
			new UserJob(i, monitor).start();
	}

}
