import java.time.*
import java.util.*
import kotlin.math.max
import kotlin.math.roundToLong

open class Card(
    var question: String,
    var answer: String,
    var date: String = LocalDateTime.now().toString(),
    var id: String = UUID.randomUUID().toString()
){
    var quality: Int? = null
    var easiness: Double = 2.5
    var repetitions: Int = 0
    var interval: Long = 1L
    var nextPracticeDate = date

    open fun show(){
        print(" $question (ENTER to see answer)")
        readln()
        print(" $answer (Type 0 -> Difficult 3 -> Doubt 5 -> Easy): ")
        do {
            quality = readln().toIntOrNull()
            if(quality != 0 && quality != 3 && quality != 5){
                print("Please, try again (Type 0 -> Difficult 3 -> Doubt 5 -> Easy): ")
            }
        } while (quality != 0 && quality != 3 && quality != 5)
    }

    fun step(date: LocalDateTime) {
        show()
        update(date)
        details()
    }

    fun details() {
        println(" eas = ${"%.2f".format(easiness)} rep = $repetitions int = $interval next = ${LocalDateTime.parse(nextPracticeDate).toLocalDate()}")
    }

    fun update(currentDate: LocalDateTime) {
        repetitions = if (quality == 0) 0 else repetitions + 1

        easiness = max(1.3, easiness + 0.1 - (5 - quality!!)*(0.08 + (5-quality!!)*0.02))

        interval = when {
            repetitions <= 1 -> 1L
            repetitions == 2 -> 6L
            else -> (interval * easiness).roundToLong()
        }

        nextPracticeDate = currentDate.plusDays(interval).toString()
    }

    fun simulate(period: Long) {
        println("Simulation of the card $question:")
        var now = LocalDateTime.now()

        for (i in 1..period + 1) {
            println("Date: ${now.toLocalDate()}")
            if (LocalDateTime.parse(nextPracticeDate).toLocalDate().compareTo(now.toLocalDate()) == 0){
                step(LocalDateTime.parse(nextPracticeDate))
            }
            now = now.plusDays(1)
        }
    }

    open fun getType(): String {
        return "card"
    }

    override fun toString(): String {
        return "${this.getType()} | $question | $answer | $date | $id | $easiness | $repetitions | $interval | $nextPracticeDate"
    }

    companion object {
        fun fromString(cad: String): Card {
            val parts = cad.split("|").map { it.trim() }
            val instance = parts[0]
            val question = parts[1]
            val answer = parts[2]
            val date = parts.getOrNull(3) ?: LocalDateTime.now().toString()
            val id = parts.getOrNull(4) ?: UUID.randomUUID().toString()
            val easiness = parts.getOrNull(5)?.toDoubleOrNull() ?: 2.5
            val repetitions = parts.getOrNull(6)?.toIntOrNull() ?: 0
            val interval = parts.getOrNull(7)?.toLongOrNull() ?: 1L
            val nextPracticeDate = parts.getOrNull(8) ?: LocalDateTime.now().toString()
            if (instance == "cloze") {
                return Cloze(question, answer).apply {
                    this.date = date
                    this.id = id
                    this.easiness = easiness
                    this.repetitions = repetitions
                    this.interval = interval
                    this.nextPracticeDate = nextPracticeDate
                }
            }
            return Card(question, answer).apply {
                this.date = date
                this.id = id
                this.easiness = easiness
                this.repetitions = repetitions
                this.interval = interval
                this.nextPracticeDate = nextPracticeDate
            }
        }
    }
}