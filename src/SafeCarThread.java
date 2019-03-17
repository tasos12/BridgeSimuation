/*
 * Nhma asfalous prospelasshs
 */

class SafeCarThread extends CarThread {	

	public SafeCarThread(int id, String color, BridgeObject bridge) {
		super(id, color, bridge);
	}

	@Override
	public void run() {
		synchronized(bridge.getBridge()) {
			criticalSection();
		}
	}
}