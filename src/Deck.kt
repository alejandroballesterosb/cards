import java.time.*

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