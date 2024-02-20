fun main() {
    var choice: Int
    val deck = Deck("English")

    do {
        println("1. Add card")
        println("2. List of cards")
        println("3. Simulation")
        println("4. Read cards from file")
        println("5. Write cards to file")
        println("6. Exit")
        print("Choose an option: ")
        choice = readLine()?.toIntOrNull() ?: 0

        when (choice) {
            1 -> deck.addCard()
            2 -> deck.listCards()
            3 -> deck.simulate(10)
            4 -> deck.readCards("data/cards.txt")
            5 -> deck.writeCards("data/cards.txt")
            6 -> println("Exiting...")
            else -> println("Invalid choice. Please enter a number between 1 and 4.")
        }
    } while (choice != 6)
}