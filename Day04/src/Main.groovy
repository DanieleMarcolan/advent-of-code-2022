/**
 * <a href="https://adventofcode.com/2022/day/4">Day 4 challenge</a>
 */
static void main(String[] args) {
	println computePart1Solution()
	println computePart2Solution()
}

/**
 * The solution for the first part consists of a single iteration, splitting the input in four integers and checking if they overlap.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart1Solution() {
	def amount = 0

	new File("../resources/input.txt").eachLine {pair ->
		def splitPair = pair.split('[,-]')
		int[] intervals = new int[splitPair.size()]
		for (def i = 0; i < splitPair.size(); i++) {
			intervals[i] = splitPair[i].toInteger()
		}

		if (intervals[0] <= intervals[2] && intervals[1] >= intervals[3]
			|| intervals[0] >= intervals[2] && intervals[1] <= intervals[3]) {
			amount++
		}

		return
	}

	return amount
}

/**
 * The solution for the second part is the same, just changing the condition.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart2Solution() {
	def amount = 0

	new File("../resources/input.txt").eachLine { pair ->
		def splitPair = pair.split('[,-]')
		int[] intervals = new int[splitPair.size()]
		for (def i = 0; i < splitPair.size(); i++) {
			intervals[i] = splitPair[i].toInteger()
		}

		if (!((intervals[0] < intervals[2] && intervals[1] < intervals[3] && intervals[1] < intervals[2])
			|| (intervals[0] > intervals[2] && intervals[1] > intervals[3] && intervals[0] > intervals[3]))) {
			amount++
		}

		return
	}

	return amount
}