private fun simulate(
    daysAgo: Long,
    numberOfCards: Long,
    pEasy: Double,
    pDoubt: Double,
    pHard: Double
): List<Card> {
    // Create a list of cards with appropriate dates
    cards = ...

    // Simulation until the current date
    var date: LocalDateTime = LocalDateTime.now().minusDays(daysAgo)
    (1..daysAgo).forEach {
        // Spaced repetition of the set of cards
        date = date.plusDays(1)
    }

    return cards
}

fun assignQuality(
    pEasy: Double,
    pDoubt: Double,
    pHard: Double
): Int {
    val random = Math.random()
    return when {
        random < pEasy -> 5
        random < pEasy + pDoubt -> 3
        else -> 0
    }
}


fun main() {

}