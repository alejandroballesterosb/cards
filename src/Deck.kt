import java.time.*

class Deck(
    val name: String,
    val id: String = java.util.UUID.randomUUID().toString()) {
    val cards = mutableListOf<Card>()

    fun addCard(){
        println("Adding card to deck")
        print(" Type the question: ")
        var question = readln()
        print(" Type the answer: ")
        var answer = readln()
        if (question != null && answer != null){
            cards.add(Card(question,answer))
            println(" Card added successfully")
        }
        else {
            println("Please, try again")
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
                if (card.nextPracticeDate.toLocalDate().compareTo(now.toLocalDate()) == 0){
                    card.show()
                    card.details()
                }
            }
            now = now.plusDays(1)
        }
    }
}