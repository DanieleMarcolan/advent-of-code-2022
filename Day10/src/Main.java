import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(computePart1Solution());
	}

	static Integer computePart1Solution() throws IOException {
		Integer cycles = 0;
		Integer registerValue = 1;
		Integer result = 0;

		List<Integer> indexes = new ArrayList<>();
		List<Boolean> visited = new ArrayList<>();
		for (int i = 20; i <= 220; i += 40) {
			indexes.add(i);
			visited.add(Boolean.FALSE);
		}

		Boolean halfCycle = Boolean.FALSE;
		BufferedReader br = new BufferedReader(new FileReader("resources/input.txt"));
		String line = br.readLine();

		while (line != null) {
			String[] instruction = line.split(" +");
			Integer valueToAdd = 0;
			if (halfCycle) {
				halfCycle = Boolean.FALSE;
			}

			if ("addx".equals(instruction[0])) {
				cycles += 2;
				valueToAdd = Integer.parseInt(instruction[1]);
				registerValue += valueToAdd;
				halfCycle = Boolean.TRUE;
			}
			else {
				cycles++;
			}

			if (indexes.contains(cycles) || indexes.contains(cycles - 1)) {
				Integer i = indexes.indexOf(cycles);
				if (i == -1) {
					i = indexes.indexOf(cycles - 1);

				}
				if (Boolean.FALSE.equals(visited.get(i))) {
					result += indexes.get(i) * (halfCycle ? registerValue - valueToAdd : registerValue);
					visited.set(i, Boolean.TRUE);
				}
			}

			line = br.readLine();
		}

		return result;
	}
}