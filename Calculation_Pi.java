import java.util.concurrent.Callable;

public class Calculation_Pi implements Callable<Double>{
	
	private int myId;
	private long myStart;
	private long myStop;
	private double mySum; 
	private static double sum = 0;

	
	public Calculation_Pi(int myId) {
		
		this.myId = myId;
		myStart = myId * (Main.numSteps / Main.numThreads);
		myStop = myStart + (Main.numSteps / Main.numThreads);
		if(myId == (Main.numThreads - 1))
			myStop = Main.numSteps;
		mySum = 0;
	}
	
	public void run() {
		
		
        for (long i = myStart; i < myStop; ++i) {
            double x = ((double)i+0.5)*Main.step;
            mySum += 4.0/(1.0+x*x);
        }
        
        sum += mySum; 
	}

	public static double getSum() {
		return sum;
	}

	@Override
	public Double call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
