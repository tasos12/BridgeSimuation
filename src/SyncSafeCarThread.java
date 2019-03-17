/*
 * Nhma asfalous prospelashs me enallagh 1 ble 1 kokkino
 */

public class SyncSafeCarThread extends CarThread{
	
	public SyncSafeCarThread(int id, String color, BridgeObject bridge) {
		super(id, color, bridge);
	}

	@Override
	public void run() {
		while(true) {
			synchronized(bridge.getBridge()) {
				//Fanari anoixto gia kokinna || dn uparxoun ble amaksia
				if((bridge.getTraffic() && this.color==RED) || !(bridge.getBlueCars()>bridge.getBlueCarsPassed())) { //traffic light on for red|| no blue cars
					bridge.switchTraffic();				//Epetrpse kokkina amaksia na perasoun
					criticalSection();
					bridge.incRedCarsPassed();			//Auksish twn amaksiwn p perasan
					bridge.getBridge().notify();		//notify thread p perimenoun
					break;	
				} 
				//Fanari anoixto gia ble || dn uparxoun kokkina amaksia
				else if((!bridge.getTraffic() && this.color==BLUE) || !(bridge.getRedCars()>bridge.getRedCarsPassed())) {
					bridge.switchTraffic();				//Epetrepse ble amaksia na perasoun
					criticalSection();
					bridge.incBlueCarsPassed();			//Auksish twn amaksiwn p perasan
					bridge.getBridge().notify();		//notify thread p perimenoun
					break;
				}
				else {
					try {
						bridge.getBridge().wait();
					} catch (InterruptedException e) {}
				}
			}
		}
	}
}