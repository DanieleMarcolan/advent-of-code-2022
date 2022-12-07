/**
 * <a href="https://adventofcode.com/2022/day/6">Day 6 challenge</a>
 */
static void main(String[] args) {
	println computeSolution(4)
	println computeSolution(14)
}

/**
 * The solution for both parts consists of a single iteration of the input string, using a circular array as a buffer
 * and a HashSet to check if all the characters in the buffer are different.
 *
 * Temporal complexity O(n), where n is the length of the string
 * Spatial complexity O(1), since the buffer size is constant
 */
static computeSolution(int bufferSize) {
	def buffer = []
	for (int i = 0; i < bufferSize; i++) {
		buffer.add('')
	}

	Integer length = 0

	def input = new File("../resources/input.txt").getText()
	for (def code : input.chars) {
		buffer.add(code)
		buffer.remove(0)
		length++
		HashSet set = new HashSet()
		set.addAll(buffer)
		if (set.size() == bufferSize) {
			return length
		}
	}
}