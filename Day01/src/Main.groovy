/**
 * <a href="https://adventofcode.com/2022/day/1">Day 1 challenge</a>
 */
static void main(String[] args) {
	print computePart1Solution()
	print computePart2Solution()
}

/**
 * The solution for the first part consists of a single iteration while the input is been reading, saving the local maximum.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart1Solution() {
	def maxCalories = 0
	def caloriesPerElf = 0

	new File('../input.txt').eachLine {calories ->
		if (calories != "") {
			caloriesPerElf += (calories as Integer)
		}
		else {
			if (caloriesPerElf > maxCalories) {
				maxCalories = caloriesPerElf
			}

			caloriesPerElf = 0
		}
	}

	return maxCalories
}

/**
 * The solution for the second part just saves the three maximums found, keeping a single iteration over the input.
 *
 * Temporal complexity O(n)
 * Spatial complexity O(1)
 */
static computePart2Solution() {
	def threeMaxCalories = [0, 0, 0]
	def caloriesPerElf = 0

	new File('../input.txt').eachLine {calories ->
		if (calories != "") {
			caloriesPerElf += (calories as Integer)
		}
		else {
			// keeping them sorted by ascending order, and making them shift when I find a new maximum
			if (caloriesPerElf > threeMaxCalories[2]) {
				threeMaxCalories[0] = threeMaxCalories[1]
				threeMaxCalories[1] = threeMaxCalories[2]
				threeMaxCalories[2] = caloriesPerElf
			}
			else if (caloriesPerElf > threeMaxCalories[1]) {
				threeMaxCalories[0] = threeMaxCalories[1]
				threeMaxCalories[1] = caloriesPerElf
			}
			else if (caloriesPerElf > threeMaxCalories[0]) {
				threeMaxCalories[0] = caloriesPerElf
			}

			caloriesPerElf = 0
		}
	}

	def maxCaloriesSum = 0
	threeMaxCalories.each(maxCalories ->
		maxCaloriesSum += 	maxCalories
	)

	return maxCaloriesSum
}