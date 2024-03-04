import java.time.*
import java.io.File

class Deck(
    val name: String,
    val id: String = java.util.UUID.randomUUID().toString()) {
    val cards = mutableListOf<Card>()

    fun addCard(){
        var type : Int?
        println("Adding card to deck")
        print(" Type the type (0 -> Card 1 -> Cloze): ")
        do{
            type = readln().toIntOrNull()
            if (type != 0 && type != 1)
                print(" Please, try again (0 -> Card 1 -> Cloze): ")
        } while (type != 0 && type != 1)
        print(" Type the question: ")
        var question = readln()
        print(" Type the answer: ")
        var answer = readln()
        if (question != null && answer != null){
            if (type == 0)
                cards.add(Card(question,answer))
            else
                cards.add(Cloze(question,answer))
            println(" Card added successfully")
        }
        else {
            println("Please, try again")
        }
    }

    fun removeCard() {
        println("Removing card from deck")
        print("Enter the question of the card to remove: ")
        val questionToRemove = readLine()

        if (questionToRemove != null) {
            val matchingCard = cards.find { it.question == questionToRemove }
            if (matchingCard != null) {
                cards.remove(matchingCard)
                println("Card with question \"$questionToRemove\" removed successfully")
            } else {
                println("Card with question \"$questionToRemove\" not found in the deck.")
            }
        } else {
            println("Invalid input. Please try again.")
        }
    }

    fun listCards(){
        cards.forEach { card ->
            println(" ${card.question} -> ${card.answer}")
        }
    }

    fun simulate(period: Int) {
        println("Simulation of deck $name:")
        var now = LocalDateTime.now()

        for (i in 1..period + 1) {
            println("Current date: ${now.toLocalDate()}")
            for (card in cards){
                if (LocalDateTime.parse(card.nextPracticeDate).toLocalDate().compareTo(now.toLocalDate()) == 0){
                    card.step(LocalDateTime.parse(card.nextPracticeDate))
                }
            }
            now = now.plusDays(1)
        }
        for (card in cards) {
            card.nextPracticeDate = card.date
            card.quality = null
            card.easiness = 2.5
            card.repetitions = 0
            card.interval = 1L
        }
    }

    fun writeCards(name: String) {
        val file = File(name)

        file.printWriter().use { out ->
            cards.forEach { card ->
                out.println(card.toString())
            }
        }
        println("Cards have been written to $name.")
    }

    fun readCards(name: String) {
        val file = File(name)
        if (!file.exists()) {
            println("File $name does not exist.")
            return
        }

        file.forEachLine { line ->
            cards.add(Card.fromString(line))
        }

        println("Cards have been read from $name.")
    }
}