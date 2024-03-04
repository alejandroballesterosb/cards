fun main() {
    var choice: Int
    val deck = Deck("English")

    do {
        println("1. Add card")
        println("2. Remove card")
        println("3. List of cards")
        println("4. Simulation")
        println("5. Read cards from file")
        println("6. Write cards to file")
        println("7. Show histogram of a simulation of 100 cards")
        println("8. Exit")
        print("Choose an option: ")
        choice = readLine()?.toIntOrNull() ?: 0

        when (choice) {
            1 -> deck.addCard()
            2 -> deck.removeCard()
            3 -> deck.listCards()
            4 -> deck.simulate(10)
            5 -> deck.readCards("data/cards.txt")
            6 -> deck.writeCards("data/cards.txt")
            7 -> {val cards = simulate(30, 1000, 0.90, 0.08, 0.02)
                histogram(cards)}
            8 -> println("Exiting...")
            else -> println("Invalid choice. Please enter a number between 1 and 4.")
        }
    } while (choice != 8)
}