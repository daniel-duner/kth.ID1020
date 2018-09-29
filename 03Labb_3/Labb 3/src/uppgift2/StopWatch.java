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
		this.start = System.currentTimeMillis();
	}
	
	public void stop() {
		long stop = System.currentTimeMillis();
		System.out.println("Runtime: "+(stop-start)+" ms");
	}
}
