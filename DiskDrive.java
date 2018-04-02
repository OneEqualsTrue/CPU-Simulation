package Lab_5;

public class DiskDrive {

	int position = 512; // Start in middle for best chance

	/*
	 * Just some fancy Simulation. Disk starts on 512/1024 for best possible
	 * results. (Max wait time is 512, as oppose to 1023 if it were to start on
	 * 0 or 1024)
	 */
	public DiskDrive() throws InterruptedException {

		System.out.print("BOOTING, PLEASE WAIT.");
		for (int i = 0; i < MAIN.N; i++) {
			Thread.sleep(300);
			System.out.print(".");
		}
		System.out.println("\nDISK\tBOOTED ON DEFAULT TRACK 512\n----");
	}

	/*
	 * Synchronized method to access disk. Disk usage is simulated by waiting
	 * the appropriate ammount of time to move from the current track position,
	 * to the next one to be. Synchronization makes sure one thread at a time
	 * can simulate using the DiskDrive.
	 */
	public synchronized void useTheDisk(int track) throws InterruptedException {

		System.out.println("DISK\tMoving from " + position + " to " + track);
		Thread.sleep(Math.abs(position - track) + 1);
		// Absolute value of position and track dictates the ammount of time
		// (or needle steps) inbetween tracks for proper simulation time
		this.position = track; // Set track position accordingly
	}
}
