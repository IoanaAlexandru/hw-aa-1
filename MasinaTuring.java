import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MasinaTuring {

	private static Scanner s;
	private static PrintWriter w;

	public static void main(String[] args) {
		boolean ok = true;
		int NrInitialStates, NrFinalStates, i, j;
		String InitialStates[] = new String[1000];
		String FinalStates[] = new String[1000];

		try {
			s = new Scanner(new File("task2.in"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			w = new PrintWriter("task1.out");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Reading data from input file

		NrInitialStates = s.nextInt();
		for (i = 0; i < NrInitialStates; i++) {
			InitialStates[i] = s.next();
		}

		NrFinalStates = s.nextInt();
		for (i = 0; i < NrFinalStates; i++) {
			FinalStates[i] = s.next();
		}

		String CurrentState = s.next();

		int NrTransitions = s.nextInt();
		TransitionFunction f = new TransitionFunction(NrTransitions);
		for (i = 0; i < NrTransitions; i++) {
			f.SetTransition(i, s.next(), s.next().charAt(0), s.next().charAt(0), s.next(), s.next().charAt(0),
					s.next().charAt(0), s.next().charAt(0), s.next().charAt(0));
		}

		char Tape1[] = new char[10000];
		char Tape2[] = new char[10000];
		String Tape1s = s.next();
		String Tape2s = s.next();
		// Turning the tapes from strings to character arrays
		for (i = 0; i < Tape1s.length(); i++)
			Tape1[i] = Tape1s.charAt(i);
		for (i = 0; i < Tape2s.length(); i++)
			Tape2[i] = Tape2s.charAt(i);

		// Setting the reading heads to position 1,
		// initialising transition number and length varibles
		int Head1 = 1, Head2 = 1, t, Length1 = Tape1s.length(), Length2 = Tape2s.length();

		do {
			// Finding the right transition (if it exists)
			t = f.GetTransition(CurrentState, Tape1[Head1], Tape2[Head2]);

			// Checking if the machine is stuck
			if (t == -1) {
				w.println("The machine has blocked!");
				break;
			}

			if (CurrentState.compareTo(f.next_state[t]) == 0 && f.tape1_direction[t] == 'H'
					&& f.tape2_direction[t] == 'H') {
				w.println("The machine has blocked!");
				break;
			}

			// Changing the state and writing on the tapes
			CurrentState = f.next_state[t];
			Tape1[Head1] = f.tape1_write_symbol[t];
			Tape2[Head2] = f.tape2_write_symbol[t];

			// Moving the reading heads in the correct direction
			if (f.tape1_direction[t] == 'L')
				Head1--;
			else if (f.tape1_direction[t] == 'R') {
				Head1++;
				if (Head1 >= Length1) { // The tape is infinite to the right
					Tape1[Head1] = '#';
					Length1++;
				}
			}

			if (f.tape2_direction[t] == 'L')
				Head2--;
			else if (f.tape2_direction[t] == 'R') {
				Head2++;
				if (Head2 >= Length2) {
					Tape2[Head2] = '#';
					Length2++;
				}
			}

			// Checking if the machine has finished
			for (i = 0; i < NrFinalStates; i++) {
				if (CurrentState.compareTo(FinalStates[i]) == 0) {
					for (j = 0; j < Length1; j++)
						w.print(Tape1[j]);
					w.println();
					for (j = 0; j < Length2; j++)
						w.print(Tape2[j]);
					w.println();
					ok = false;
					break;
				}
			}
		} while (ok);
		
		w.close();
		s.close();
	}

}
