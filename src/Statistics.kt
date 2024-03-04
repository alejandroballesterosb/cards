import java.time.*

fun histogram(cards: List<Card>) {
    // Group cards by next practice date and count the number of cards for each date
    val groups = cards.groupBy {
        LocalDateTime.parse(it.nextPracticeDate).toLocalDate()
    }.mapValues { (_, group) -> group.size }

    // Print the histogram
    val maxDate = groups.keys.maxOrNull()
    val currentDate = LocalDate.now()
    val endDate = maxDate ?: currentDate
    var date = currentDate

    while (date <= endDate) {
        val count = groups[date] ?: 0
        println("$date -> $count")
        date = date.plusDays(1)
    }
}

fun simulate(
    daysAgo: Long,
    numberOfCards: Long,
    pEasy: Double,
    pDoubt: Double,
    pHard: Double
): List<Card> {
    // Create a list of cards with appropriate dates
    val cards = mutableListOf<Card>()

    val startDate = LocalDateTime.now().minusDays(daysAgo)

    repeat(numberOfCards.toInt()) { index ->
        val card = Card("Question $index", "Answer $index", startDate.toString())
        cards.add(card)
    }

    // Simulation until the current date
    var date: LocalDateTime = LocalDateTime.now().minusDays(daysAgo)
    (1..daysAgo).forEach { _ ->
        // Spaced repetition of the set of cards
        for (card in cards) {
            if (LocalDateTime.parse(card.nextPracticeDate).toLocalDate() == date.toLocalDate()) {
                card.quality = assignQuality(pEasy, pDoubt, pHard)
                card.update(date)
            }
        }
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
