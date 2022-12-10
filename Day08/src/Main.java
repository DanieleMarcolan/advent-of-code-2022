import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/8">Day 8 challenge</a>
 */
public class Main {
	public static List<List<Integer>> MATRIX = new ArrayList<>();
	public static Integer ROW_LENGTH;
	public static Integer COLUMN_LENGTH;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("resources/input.txt"));
		String inputRow = br.readLine();

		while (inputRow != null) {
			List<Integer> row = new ArrayList<>();
			for (Character c : inputRow.toCharArray()) {
				row.add(Integer.parseInt(String.valueOf(c)));
			}
			MATRIX.add(row);

			inputRow = br.readLine();
		}

		ROW_LENGTH = MATRIX.get(0).size();
		COLUMN_LENGTH = MATRIX.size();

		System.out.println(computePart1Solution());
		System.out.println(computePart2Solution());
	}

	/**
	 * The solution for the first part consists of backtrack over the row and the column of each element of the matrix, checking if almost one side is visible.
	 *
	 * Temporal complexity O(n * m * 4^(max(n, m)))
	 * Spatial complexity O(n * m), the matrix
	 */
	static Integer computePart1Solution() {
		Integer result = 0;

		result = result + (ROW_LENGTH * 2) + ((COLUMN_LENGTH - 2) * 2); // Adding borders

		for (Integer i = 1; i < ROW_LENGTH - 1; i++) {
			for (Integer j = 1; j < COLUMN_LENGTH - 1; j++) {
				if (isVisibleUp(i, j) || isVisibleDown(i, j) || isVisibleRight(i, j) || isVisibleLeft(i, j)) {
					result++;
				}
			}
		}

		return result;
	}

	/**
	 * The solution for the second part is the same as the first one, counting the highest distance sight instead.
	 *
	 * Temporal complexity O(n * m * 4^(max(n, m)))
	 * Spatial complexity O(n * m), the matrix
	 */
	static Integer computePart2Solution() {
		Integer highestScore = 0;

		for (Integer i = 1; i < ROW_LENGTH - 1; i++) { // Borders have always score 0
			for (Integer j = 1; j < COLUMN_LENGTH - 1; j++) {
				Integer localScore = highestScoreUp(i, j) * highestScoreDown(i, j) * highestScoreRight(i, j) * highestScoreLeft(i, j);

				if (localScore > highestScore) {
					highestScore = localScore;
				}
			}
		}

		return highestScore;
	}

	static Boolean isVisibleUp(Integer i, Integer j) {
		Boolean isVisible = true;
		for (Integer x = 0; x < i; x++) {
			if (MATRIX.get(x).get(j) >= MATRIX.get(i).get(j)) {
				isVisible = false;
				break;
			}
		}

		return isVisible;
	}

	static Boolean isVisibleDown(Integer i, Integer j) {
		Boolean isVisible = true;
		for (Integer x = i + 1; x < COLUMN_LENGTH; x++) {
			if (MATRIX.get(x).get(j) >= MATRIX.get(i).get(j)) {
				isVisible = false;
				break;
			}
		}

		return isVisible;
	}

	static Boolean isVisibleLeft(Integer i, Integer j) {
		Boolean isVisible = true;
		for (Integer y = 0; y < j; y++) {
			if (MATRIX.get(i).get(y) >= MATRIX.get(i).get(j)) {
				isVisible = false;
				break;
			}
		}

		return isVisible;
	}

	static Boolean isVisibleRight(Integer i, Integer j) {
		Boolean isVisible = true;
		for (Integer y = j + 1; y < ROW_LENGTH; y++) {
			if (MATRIX.get(i).get(y) >= MATRIX.get(i).get(j)) {
				isVisible = false;
				break;
			}
		}

		return isVisible;
	}

	static Integer highestScoreUp(Integer i, Integer j) {
		Integer localScore = 0;
		for (Integer x = i - 1; x >= 0; x--) {
			localScore++;
			if (MATRIX.get(x).get(j) >= MATRIX.get(i).get(j)) {
				break;
			}
		}

		return localScore;
	}

	static Integer highestScoreDown(Integer i, Integer j) {
		Integer localScore = 0;
		for (Integer x = i + 1; x < COLUMN_LENGTH; x++) {
			localScore++;
			if (MATRIX.get(x).get(j) >= MATRIX.get(i).get(j)) {
				break;
			}
		}

		return localScore;
	}

	static Integer highestScoreLeft(Integer i, Integer j) {
		Integer localScore = 0;
		for (Integer y = j - 1; y >= 0; y--) {
			localScore++;
			if (MATRIX.get(i).get(y) >= MATRIX.get(i).get(j)) {
				break;
			}
		}

		return localScore;
	}

	static Integer highestScoreRight(Integer i, Integer j) {
		Integer localScore = 0;
		for (Integer y = j + 1; y < ROW_LENGTH; y++) {
			localScore++;
			if (MATRIX.get(i).get(y) >= MATRIX.get(i).get(j)) {
				break;
			}
		}

		return localScore;
	}
}