/*
 * Nhma asfalous prospelashs me enallagh 1 ble 1 kokkino kai prosarmogh
 */
public class SyncSafeCarThreadImproved extends CarThread{	
	
	public SyncSafeCarThreadImproved(int id, String color, BridgeObject bridge) {
		super(id, color, bridge);
	}

	@Override
	public void run() {
		while(true) {
			synchronized(bridge) {
				//Fanari anoixto gia kookinna || dn uparxoun ble amaksia || kokkina amaksia < ble amaksia*2
				if((bridge.getTraffic() && this.color==RED) || !(bridge.getBlueCars()>bridge.getBlueCarsPassed())
						|| bridge.getRedCars() < bridge.getBlueCars()*2) { 
					bridge.switchTraffic();	//Epetrpse kokkina amaksia na perasoun
					criticalSection();
					bridge.incRedCarsPassed();
					bridge.notify();		//start waiting thread
					break;	
				} 
				//Fanari anoixto gia ble || dn uparxoun kokkina amaksia || ble amaksia < kokkina amaksia*2
				else if((!bridge.getTraffic() && this.color==BLUE) || !(bridge.getRedCars()>bridge.getRedCarsPassed())
						|| bridge.getBlueCars() < bridge.getRedCars()*2) {
					bridge.switchTraffic();	//Epetrepse ble amaksia na perasoun
					criticalSection();
					bridge.incBlueCarsPassed();
					bridge.notify();		//start waiting thread
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
