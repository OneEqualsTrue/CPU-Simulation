package Lab_5;

import java.util.concurrent.Semaphore;

public class CPUmonitor {

	Semaphore SimulatedCPU = new Semaphore(MAIN.N);
	boolean[] CPUs = new boolean[MAIN.N];

	/*
	 * Job can acquire a CPU to simulate a burst, assigns it a CPU.
	 */
	public void startCPUuse(UserJob j, int time) throws InterruptedException {

		// Request CPU
		SimulatedCPU.acquire();
		allocateCPU(j);

		System.out.println("CPU" + (j.CPUinUse) + "\t" + j.id + " Starting "
				+ j.bound + " burst of length " + time);

		Thread.sleep(time); // Use CUP for a while
	}

	/*
	 * Cleans up after Job completes Simulated task: (1) Releases CPU so another
	 * JOb may use it. (2) Simulates disk access after each job. (3) Relseases
	 * Job's CPU so another can use it.
	 */
	public void endCPUuse(UserJob j, int time) {

		SimulatedCPU.release();
		deallocateCPU(j);

		System.out.println(
				"DISK\t" + j.id + " requesting to access track " + time);
		try {
			j.DiskDrive.useTheDisk(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("DISK\t" + j.id + " done accessing track " + time);
	}

	/*
	 * Searches for available CPU. At this point, the job has already secured a
	 * spot, but it now needs to look for a SPECIFIC CPU to use, so it scans the
	 * array for one. Method is synchronized, because there is concurrent access
	 * to the isAvailable[] array.
	 */
	public synchronized void allocateCPU(UserJob j) {
		
		for (int i = 0; i < MAIN.N; i++)
			if (CPUs[i] != true) {
				j.CPUinUse = i;
				CPUs[i] = true; // Simulate CPU is in use
				break; // No need to continue scan
			}
	}

	/*
	 * Releases CPU position from array. Method is synchronized, because there
	 * is concurrent access to the isAvailable[] array.
	 */
	public synchronized void deallocateCPU(UserJob j) {
		
		CPUs[j.CPUinUse] = false;
		System.out.println("CPU" + j.CPUinUse + "\tReleased");
	}

}
