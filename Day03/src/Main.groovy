/**
 * <a href="https://adventofcode.com/2022/day/3">Day 3 challenge</a>
 */
static void main(String[] args) {
	println computePart1Solution()
	println computePart2Solution()
}

/**
 * The solution for the first part consists of a single iteration, splitting the input in two hash set in order to remove the duplicates for each half;
 * then the two hash sets are merged in a single one, finding the duplicate value.
 *
 * Temporal complexity O(n * m), where n is the number of rows and m is the length of each one
 * Spatial complexity O(m)
 */
static computePart1Solution() {
	def prioritiesSum = 0

	new File("../resources/input.txt").eachLine { rucksack ->
		def firstHalf = rucksack.substring(0, (rucksack.size() / 2 as Integer))
		def secondHalf = rucksack.substring(rucksack.size() / 2 as Integer)
		firstHalf = firstHalf.toCharArray() as HashSet
		secondHalf = secondHalf.toCharArray() as HashSet

		for (def item : secondHalf) {
			if (!firstHalf.add(item)) {
				// using ASCII value
				if (item >= 97 && item <= 122) {
					prioritiesSum = prioritiesSum + (item as Character) - 96
				}
				else {
					prioritiesSum = prioritiesSum + (item as Character) - 38
				}
			}
		}
	}

	return prioritiesSum
}

/**
 * The solution for the second part is similar to the first one, but every three rows it finds the duplicate by merging three hash sets.
 *
 * Temporal complexity O(n * m), where n is the number of rows and m is the length of each one
 * Spatial complexity O(m)
 */
static computePart2Solution() {
	def prioritiesSum = 0

	def rucksacks = []
	def rucksacksNumber = 0
	new File("../resources/input.txt").eachLine { rucksack ->
		rucksacksNumber++
		rucksacks[rucksacksNumber - 1] = rucksack.toCharArray() as HashSet

		if (rucksacksNumber == 3) {
			rucksacksNumber = 0
			def candidates = [] as HashSet

			for (def item : rucksacks[1]) {
				if (!rucksacks[0].add(item)) {
					candidates.add(item)
				}
			}

			candidates = candidates as HashSet

			for (def item : rucksacks[2]) {
				if (!candidates.add(item)) {
					// using ASCII value
					if (item >= 97 && item <= 122) {
						prioritiesSum = prioritiesSum + (item as Character) - 96
					}
					else {
						prioritiesSum = prioritiesSum + (item as Character) - 38
					}
				}
			}
		}
	}

	return prioritiesSum
}