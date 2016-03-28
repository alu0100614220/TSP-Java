import java.sql.Time;

public class Timer {
	long start;
	long end;
	public Timer() {
	}
	public void start(){
		this.start = System.currentTimeMillis();
	}
	public void stop(){
		this.end = System.currentTimeMillis();
	}
	
	public String getTiempo(){
		return "Took: " + ((this.end - this.start)) + "ms";
	}
	
}
