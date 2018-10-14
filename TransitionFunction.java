
public class TransitionFunction {
	int NrTransitions;

	String current_state[] = new String[10000];
	char tape1_read_symbol[] = new char[10000];
	char tape2_read_symbol[] = new char[10000];
	String next_state[] = new String[10000];
	char tape1_write_symbol[] = new char[10000];
	char tape1_direction[] = new char[10000];
	char tape2_write_symbol[] = new char[10000];
	char tape2_direction[] = new char[10000];

	public TransitionFunction(int NrTransitions) {
		this.NrTransitions = NrTransitions;
	}

	public void SetTransition(int nr, String current_state, char tape1_read_symbol, char tape2_read_symbol,
			String next_state, char tape1_write_symbol, char tape1_direction, char tape2_write_symbol,
			char tape2_direction) {
		this.current_state[nr] = current_state;
		this.tape1_read_symbol[nr] = tape1_read_symbol;
		this.tape2_read_symbol[nr] = tape2_read_symbol;
		this.next_state[nr] = next_state;
		this.tape1_write_symbol[nr] = tape1_write_symbol;
		this.tape1_direction[nr] = tape1_direction;
		this.tape2_write_symbol[nr] = tape2_write_symbol;
		this.tape2_direction[nr] = tape2_direction;
	}
	
	public int GetTransition(String current_state, char tape1_read_symbol, char tape2_read_symbol) {
		for (int i = 0; i < NrTransitions; i++) {
			if (this.current_state[i].compareTo(current_state) == 0)
				if (this.tape1_read_symbol[i] == tape1_read_symbol)
					if (this.tape2_read_symbol[i] == tape2_read_symbol)
						return i;
		}
		return -1;
	}
}
