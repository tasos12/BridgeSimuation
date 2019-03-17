import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class BridgeObject{
	
	private Queue<CarThread> bridge;
	
	private volatile boolean trafficLight = false;
	private volatile int redCars = 0;		//Kokkina amaksia pou hrthan
	private volatile int blueCars = 0;		//Blue amaksia pou hrthan
	private volatile int redCarsPassed = 0;	//Kokkina amaksia pou perasan
	private volatile int blueCarsPassed = 0;//Blue amaksia pou perasan
	
	public BridgeObject() {
		bridge = new LinkedBlockingQueue<CarThread>(1);	//Queue with max capacity
	}
	
	public void switchTraffic() {
		trafficLight = !trafficLight;
	}
	
	public void incRedCars() {
		redCars++;
	}
	
	public void incBlueCars() {
		blueCars++;
	}
	
	public void incRedCarsPassed() {
		redCarsPassed++;
	}
	
	public void incBlueCarsPassed() {
		blueCarsPassed++;
	}
	
	public synchronized void addCar(CarThread car) {
		bridge.add(car);
	}
	
	public synchronized void pollCar() {
		bridge.poll();
	}

	public Queue<CarThread> getBridge() {
		return bridge;
	}
	
	public boolean getTraffic() {
		return trafficLight;
	}

	public int getRedCars() {
		return redCars;
	}

	public int getBlueCars() {
		return blueCars;
	}

	public int getRedCarsPassed() {
		return redCarsPassed;
	}

	public int getBlueCarsPassed() {
		return blueCarsPassed;
	}

	
}
