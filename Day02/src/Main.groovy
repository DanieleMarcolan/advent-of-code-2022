/**
 * <a href="https://adventofcode.com/2022/day/2">Day 2 challenge</a>
 */
static void main(String[] args) {
	println computePart1Solution()
	println computePart2Solution()
}

/**
 * The solution for the first part consists of a single iteration while the input is been reading, updating the score.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart1Solution() {
	def score = 0
	new File("../resources/input.txt").eachLine { match ->
		switch (match.charAt(2)) {
			case 'X':
				score += 1
				switch (match.charAt(0)) {
					case 'A':
						score += 3
						break
					case 'B':
						break
					case 'C':
						score += 6
						break
				}
				break
			case 'Y':
				score += 2
				switch (match.charAt(0)) {
					case 'A':
						score += 6
						break
					case 'B':
						score += 3
						break
					case 'C':
						break
				}
				break
			case 'Z':
				score += 3
				switch (match.charAt(0)) {
					case 'A':
						break
					case 'B':
						score += 6
						break
					case 'C':
						score += 3
						break
				}
				break
		}

		return score
	}
}

/**
 * The solution for the second part is the same, just changing the values.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart2Solution() {
	def score = 0
	new File("../resources/input.txt").eachLine { match ->
		switch (match.charAt(2)) {
			case 'X':
				switch (match.charAt(0)) {
					case 'A':
						score += 3
						break
					case 'B':
						score += 1
						break
					case 'C':
						score += 2
						break
				}
				break
			case 'Y':
				score += 3
				switch (match.charAt(0)) {
					case 'A':
						score += 1
						break
					case 'B':
						score += 2
						break
					case 'C':
						score += 3
						break
				}
				break
			case 'Z':
				score += 6
				switch (match.charAt(0)) {
					case 'A':
						score += 2
						break
					case 'B':
						score += 3
						break
					case 'C':
						score += 1
						break
				}
				break
		}

		return score
	}
}