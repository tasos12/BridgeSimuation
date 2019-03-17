abstract class CarThread extends Thread {		//Genikh classh amaksiwn 
	
	protected int id;
	protected String color;
	protected BridgeObject bridge;
	protected final String RED = "Red";
	protected final String BLUE = "Blue";
	
	public CarThread(int id, String color, BridgeObject bridge) {
		this.id = id;
		this.color = color;
		this.bridge = bridge;
	}
	
	abstract public void run();
	
	/*
	 * Methodos pou typwnei thn afiksh twn amaksiwn kai ksekinaei to nhma
	 */
	public void Start() {
		long timestamp = System.currentTimeMillis() - Bridge.START_TIME;
		if(color == "Blue") {
			String text = "*Blue Car " + id + " arrived at " + timestamp;
			System.out.printf("%120s\n", text);
		}
		else
			System.out.println("*Red Car " + id + " arrived at " + timestamp);
		this.start();
	}
	
	/*
	 * Krisimo tmhma nhmatwn (idio gia kathe ksexwristo nhma)
	 */
	protected void criticalSection(){
		try {
			String text = "--" + color + " Car " + id + " passing  at " + (System.currentTimeMillis() - Bridge.START_TIME);
			System.out.printf("%75s\n",text);
			bridge.addCar(this);		//Start traversing
			sleep(Bridge.TRAVERSE_TIME);//Traversal delay
			bridge.pollCar();			//End traversing
			printTraversed();
		} catch (InterruptedException e) {}
	}
	
	/*
	 * Emfanise oti to amaksi teleiwse th prospelash
	 */
	protected void printTraversed() {
		long timestamp = System.currentTimeMillis() - Bridge.START_TIME;
		if(color == RED) {
			String text = "->Red Car " + id + " passed  at " + timestamp;
			System.out.printf("%110s\n", text);
		}
		else if(color == BLUE) {
			System.out.println("<-Blue Car " + id + " passed  at " + timestamp);
		}
	}
}