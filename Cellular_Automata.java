import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

// (IMPORTANT: Update The Text File's Directory To a Directory In Your PC) in Line 30

public class Cellular_Automata {
//PUT THE RULE NUMBER YOU WANT TO SEE HERE
	static int Rule = 110;				//Put Any Of The 256 Rules Here, I'm Using Rule 110 For Demonstration	
	static int Size = 10;  				//You Can Update The Size Of The Cellular Automata Here
	static int ITERS = 1000;			//You Can Update The Number Of Times The Cells Will Be Updated
	
	static int[] Rule_Array = new int[8];

	public static void main(String[] args) {
		
		String Rule_Binary = Integer.toBinaryString(Rule);	//Convert Rule From Decimal To Binary
		int RBL = Rule_Binary.length();						//Binary String's Length
		int counter = 0;
	//This Part Stores The Binary String Inside the Rule_Array Array
		for(int i = RBL-1 ; i >= 0-(8-RBL) ; i --){
			if(i >= 0){
			Rule_Array[7-counter] = Rule_Binary.charAt(i)-'0';
			}else{
			Rule_Array[7-counter] = 0;	
			}
			counter++;
		}
		
	//This Portion Takes Your Console Output And Puts It In A TextFile So That You Can View The Cellular Automata Later
		try{
		PrintStream myconsole = new PrintStream(new File("A://CellularAutomata.txt")); //Put The Directory Where You Want The Text File To Be Saved
		System.setOut(myconsole);
		}catch(FileNotFoundException fx){
		}
	
		String out = "";				//Empty Initialized String That Will Later Be a (Black / White) Block Depending On The Cell's Value: 1 or 0
		String BlackSquare = "██"; 		//Black Block for cells that are equal to 1
		String WhiteSquare = "     ";	//White Block for cells that are equal to 0 (5 spaces = 1 black square in MS Word)
		
		int[] A = new int[Size];	//Cells BEFORE UPDATING
	
		int[] B = new int[Size];	//Cells After Updating
		
		A[Size/2] = 1;				//Center Cell's Value = 1 = Black Square
		
		String LE, RE, MC = "";		// LE = Left Edge Cell, MC = Main Center Cells, RE = Right Edge Cell
		
	//THIS LOOP PRINTS OUT THE FIRST CELLS ARRANGEMENT PRIOR TO UPDATING
		for(int i = 0 ; i < Size; i ++){
			out = (A[i] == 1) ? BlackSquare : WhiteSquare; //Conditional statement (if cell = 1, print black square, if cell = 0 print white space)
			System.out.print(out);
		}
		System.out.println("");
		
	//MAIN UPDATING LOOP THAT USES THE UPDATE METHOD BELOW
		for (int i = 0; i < ITERS; i++) {

	// UPDATING LEFT EDGE AKA THE FIRST CELL
			LE = String.valueOf(A[Size-1]) + String.valueOf(A[0]) + String.valueOf(A[1]); //Stores The Cell's Value And The Value Of Its Two Neighbors in a String
			B[0] = Update(LE); 									//Sends The String to the Update Function To Apply The Rule's Logic And Return Either 1, 0
			out = (B[0] == 1) ? BlackSquare : WhiteSquare;
			System.out.print(out);

	// UPDATING CENTER PORTIONS
			for (int j = 1; j < Size-1; j++) {
				MC = String.valueOf(A[j - 1]) + String.valueOf(A[j]) + String.valueOf(A[j + 1]);
				B[j] = Update(MC);
				out = (B[j] == 1) ? BlackSquare : WhiteSquare;
			System.out.print(out);
				MC = "";
			}
			
	// UPDATING RIGHT EDGE AKA THE LAST CELL
			RE = String.valueOf(A[Size-2]) + String.valueOf(A[Size-1]) + String.valueOf(A[0]);
			B[Size-1] = Update(RE);
			out = (B[Size-1] == 1) ? BlackSquare : WhiteSquare;
			System.out.println(out);
			
	//Stores B's Values In A For The Next Iteration
			for(int ik = 0 ; ik < Size ; ik ++){
			A[ik] = B[ik];
			}
		}
	}
	
	private static int Update(String x) {
		
		String a = x;
		int returned = 0;
		//Compare
		switch (a) {
		case "000":
			returned = Rule_Array[7];
			break;
		case "001":
			returned = Rule_Array[6];
			break;
		case "010":
			returned = Rule_Array[5];
			break;
		case "011":
			returned = Rule_Array[4];
			break;
		case "100":
			returned = Rule_Array[3];
			break;
		case "101":
			returned = Rule_Array[2];
			break;
		case "110":
			returned = Rule_Array[1];
			break;
		case "111":
			returned = Rule_Array[0];
			break;
		}
		return returned;
	}
}
