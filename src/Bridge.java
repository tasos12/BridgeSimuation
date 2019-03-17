import java.util.Scanner;

public class Bridge {

	//Constants
	public static final int CARS = 50;				//Arithmos amaksiwn pou tha erthoun mexri to telos toy programmatos
	public static final int TRAVERSE_TIME = 500;	//Xronos prospelashs
	public static final int CAR_FREQUENCY = 200;	//Megistos xronos ms mexri thn epomeni afoiksh
	public static long START_TIME;					//Arxikh stigmh toy programmatos
	
	private static BridgeObject bridge;				//Koinoxrhsto antikeimo
	
	public static void main(String[] args) {
		
		//Instantiate
		bridge = new BridgeObject();
		
		
		//Epilogh scenariou
		int scenario = readUserInput();
		START_TIME = System.currentTimeMillis();
		
		//Draw Bridge
		printBridge();
		
		//Arxh paragwgou nhmatwn
		new CarFactory(bridge, scenario).start();	
	}
	
	private static void printBridge() {
		String leftText = "Left Side";
		String bridgeText = "Bridge";
		String rightText = "Right Side";
		
		System.out.printf("%s%45s%35s\n", leftText, bridgeText, rightText);
	}
	
	private static int readUserInput() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Epilogh Scenariou");
		int n = reader.nextInt();
		reader.close();
		return n;
	}
}




