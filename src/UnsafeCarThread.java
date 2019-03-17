/*
 * Nhma mh asfalous prospelashs autokinhtwn
 */
public class UnsafeCarThread extends CarThread{	
	
	public UnsafeCarThread(int id, String color, BridgeObject bridge) {
		super(id, color, bridge);
	}

	@Override
	public void run() {
		try {
			criticalSection();
		} catch(IllegalStateException e) { //Ean mpoun sto krisimo tmhma mazi 2 thread
			System.out.printf("%75s\n","Crushed");
		}
	}
}