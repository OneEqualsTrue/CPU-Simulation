package Lab_5;

import java.util.Random;

/*
 * UserJob Object describes a CUP or IO bound job thread
 */
public class UserJob extends Thread {

	String id, bound;
	DiskDrive DiskDrive;
	int CPUinUse;

	/*
	 * Constructor
	 * 
	 * Bound dictates wether the Job is IO or CPU based, chosen randomly.
	 * Random.nextBoolean() will return a True or False randomly.
	 */
	public UserJob(int id, DiskDrive monitor) {
		
		this.id = "PROCESS " + id;
		this.DiskDrive = monitor;
		this.bound = new Random().nextBoolean() ? "IO" : "CPU";
	}

	/*
	 * Method for multithreading, called by this.run(). Simulated 10 times.
	 * 
	 * When running, UserJob simulates doing either a CPU or IO job for some
	 * ammount of time, justified by wait. Then it simulates using the disk
	 * drive from a random location.
	 */
	public void run() {
		
		// Simulate 10 tasks
		for (int i = 0; i < 10; i++) {
			
			// START
			try {
				MAIN.CPU.startCPUuse(this,
						bound == "CPU" ? new Random().nextInt(901) + 100
								: new Random().nextInt(191) + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// END
			MAIN.CPU.endCPUuse(this, new Random().nextInt(1024));
		}
	}
}
