import java.util.Random;

public class CarFactory extends Thread {
	
	private Random randomFactory;
	private BridgeObject bridge;
	private int scenario;
	
	public CarFactory(BridgeObject bridge, int scenario) {
		randomFactory = new Random();
		this.bridge = bridge;
		this.scenario = scenario;
	}
	
	@Override
	public void run() {
		int time = randomFactory.nextInt(Bridge.CAR_FREQUENCY);
		try {
			for(int i=0;i<Bridge.CARS;i++) {
				sleep(time);
				String type = getRandomCar();
				if(type == "Red") {
					bridge.incRedCars();//Auksish amaksiwn pou perimenoun
					switch(scenario) {	//Epilogh nhmatos analoga to scenario
						case 1: new UnsafeCarThread(bridge.getRedCars(), type, bridge).Start();
								break;
						case 2: new SafeCarThread(bridge.getRedCars(), type, bridge).Start();
								break;
						case 3: new SyncSafeCarThread(bridge.getRedCars(), type, bridge).Start();
								break;
						case 4: new SyncSafeCarThreadImproved(bridge.getRedCars(), type, bridge).Start();
								break;
					}
				}
				else if(type == "Blue") {
					bridge.incBlueCars();//Auksish amaksiwn pou perimenoun
					switch(scenario) {	//Epilogh nhmatos analoga to scenario
						case 1: new UnsafeCarThread(bridge.getBlueCars(), type, bridge).Start();
								break;
						case 2: new SafeCarThread(bridge.getBlueCars(), type, bridge).Start();
								break;
						case 3: new SyncSafeCarThread(bridge.getBlueCars(), type, bridge).Start();
								break;
						case 4: new SyncSafeCarThreadImproved(bridge.getBlueCars(), type, bridge).Start();
								break;
					}
				}
			}			
		} catch (InterruptedException e) {}
	}
	
	private String getRandomCar() {
		if(randomFactory.nextBoolean())
			return "Red";
		return "Blue";		
	}
}