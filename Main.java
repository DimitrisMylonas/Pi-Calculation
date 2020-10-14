import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	static double step;
	static int numThreads;
	static long numSteps;
	
    public static void main(String[] args) {

    	//ReentrantLock lock = new ReentrantLock(true);
    	
        /* parse command line */
        if (args.length != 1) {
		System.out.println("arguments:  number_of_steps");
                System.exit(1);
        }
        try {
		numSteps = Long.parseLong(args[0]); 
        } catch (NumberFormatException e) {
		System.out.println("argument "+ args[0] +" must be long int");
		System.exit(1);
        }
        
        /* Number of CPUs */
        int cores = Runtime.getRuntime().availableProcessors(); 
        System.out.println("cores = " + cores);
        /* Number of threads */
        numThreads = cores; 
        System.out.println("Threads = " + numThreads);
        
        /* start timing */
        long startTime = System.currentTimeMillis(); //xekinaei h katametrisi toy xronoy ypologismou tou pi
        
        step = 1.0 / (double)numSteps; 
        
        ExecutorService executor = Executors.newFixedThreadPool(cores);
		
        for(int i = 0; i < numThreads; i++){
        	executor.execute((Runnable) new Calculation_Pi(i));
       
        	
        }
        try {
			executor.shutdown();
			executor.awaitTermination( 60, TimeUnit.SECONDS);
			}
		catch (InterruptedException e) {
			System.out.println("tasks interrupted");
			}
		finally {
			if (!executor.isTerminated()) {
				System.out.println("cancel non-finished tasks");
			} else 
				System.out.println("all tasks finished");
				executor.shutdownNow();
			}

        double pi = Calculation_Pi.getSum() * step; //teliko pi

        
        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}

