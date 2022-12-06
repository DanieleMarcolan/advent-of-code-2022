import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://adventofcode.com/2022/day/5">Day 5 challenge</a>
 */
public class Main {
	public static void main(String[] args) throws IOException {
		// Hardcoded input because the parsing is a pain.
		List<Character[]> inputs = new ArrayList<>();
		Character[] input1 = {'R', 'G', 'J', 'B', 'T', 'V', 'Z'};
		Character[] input2 = {'J', 'R', 'V', 'L'};
		Character[] input3 = {'S', 'Q', 'F'};
		Character[] input4 = {'Z', 'H', 'N', 'L', 'F', 'V', 'Q', 'G'};
		Character[] input5 = {'R', 'Q', 'T', 'J', 'C', 'S', 'M', 'W'};
		Character[] input6 = {'S', 'W', 'T', 'C', 'H', 'F'};
		Character[] input7 = {'D', 'Z', 'C', 'V', 'F', 'N', 'J'};
		Character[] input8 = {'L', 'G', 'Z', 'D', 'W', 'R', 'F', 'Q'};
		Character[] input9 = {'J', 'B', 'W', 'V', 'P'};
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);
		inputs.add(input4);
		inputs.add(input5);
		inputs.add(input6);
		inputs.add(input7);
		inputs.add(input8);
		inputs.add(input9);

		System.out.println(computePart1Solution(inputs));
		System.out.println(computePart2Solution(inputs));
	}

	/**
	 * The solution for the first part consists of a single iteration, adding each column of values in a different stack;
	 * then just pop() and push() for each instruction.
	 *
	 * Temporal complexity O(n * m), where n is the number of instructions and m the maximum number of values to move.
	 * Spatial complexity O(x * y), where x is the number of columns and y the length of the maximum one.
	 */
	public static String computePart1Solution(List<Character[]> inputs) throws IOException {
		List<Stack<Character>> stacks = new ArrayList<>();
		for (Character[] input : inputs) {
			Stack<Character> stack = new Stack<>();
			for (Character elem : input) {
				stack.push(elem);
			}

			stacks.add(stack);
		}

		BufferedReader br = new BufferedReader(new FileReader("resources/input.txt"));
		String instruction = br.readLine();

		while (instruction != null) {
			String[] splitInstruction = instruction.split("[^0-9]");
			List<Integer> parsedInstruction = new ArrayList<>();
			for (String elem : splitInstruction) {
				if (!elem.isBlank()) {
					parsedInstruction.add(Integer.parseInt(elem));
				}
			}

			for (int i = 0; i < parsedInstruction.get(0); i++) {
				if (!stacks.get(parsedInstruction.get(1) - 1).isEmpty()) {
					Character elementToMove = stacks.get(parsedInstruction.get(1) - 1).pop();
					stacks.get(parsedInstruction.get(2) - 1).push(elementToMove);
				}
				else {
					break;
				}
			}

			instruction = br.readLine();
		}
		br.close();

		StringBuilder result = new StringBuilder();
		for (Stack<Character> stack : stacks) {
			if (!stack.isEmpty()) {
				result.append(stack.pop());
			}
		}

		return result.toString();
	}

	/**
	 * The solution for the second part is the same of the first one, except lists are used instead of stacks.
	 *
	 * Temporal complexity O(n * m), where n is the number of instructions and m the maximum number of values to move.
	 * Spatial complexity O(x * y), where x is the number of columns and y the length of the maximum one.
	 */
	public static String computePart2Solution(List<Character[]> inputs) throws IOException {
		List<List<Character>> lists = new ArrayList<>();
		for (Character[] input : inputs) {
			List<Character> list = new ArrayList<>();
			for (Character elem : input) {
				list.add(elem);
			}

			lists.add(list);
		}

		BufferedReader br = new BufferedReader(new FileReader("resources/input.txt"));
		String instruction = br.readLine();

		while (instruction != null) {
			String[] splitInstruction = instruction.split("[^0-9]");
			List<Integer> parsedInstruction = new ArrayList<>();
			for (String elem : splitInstruction) {
				if (!elem.isBlank()) {
					parsedInstruction.add(Integer.parseInt(elem));
				}
			}

			List<Character> whereToRemove = lists.get(parsedInstruction.get(1) - 1);
			List<Character> elementsToMove = new ArrayList<>();
			for (int i = whereToRemove.size() - parsedInstruction.get(0); i < whereToRemove.size(); i++) {
				elementsToMove.add(whereToRemove.get(i));
			}
			List<Character> whereToAdd = lists.get(parsedInstruction.get(2) - 1);
			whereToAdd.addAll(elementsToMove);
			List<Character> reducedList = whereToRemove.subList(0, whereToRemove.size() - parsedInstruction.get(0));
			lists.set(parsedInstruction.get(1) - 1, reducedList);

			instruction = br.readLine();
		}
		br.close();

		StringBuilder result = new StringBuilder();
		for (List<Character> list : lists) {
			if (!list.isEmpty()) {
				result.append(list.remove(list.size() - 1));
			}
		}

		return result.toString();
	}
}