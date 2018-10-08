package uppgift2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public class StopWatch {
	private long start;
	NumberFormat fmt = new DecimalFormat("#0.00");
	
	public StopWatch() {
	}
	
	public void start() {
			this.start = System.nanoTime();		
	}
	
	public void stop(String time) {
		long stop = System.nanoTime();
		if(time == "m")
			System.out.println("Runtime: "+((stop-start)/1000000)+" ms");
		else
			System.out.println("Runtime: "+((stop-start))+" ns");
	}
	
}
