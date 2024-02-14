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
    var nextPracticeDate = LocalDateTime.parse(date)

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
        update(nextPracticeDate)
    }

    fun details() {
        println(" eas = ${"%.2f".format(easiness)} rep = $repetitions int = $interval next = ${nextPracticeDate.toLocalDate()}")
    }

    fun update(currentDate: LocalDateTime) {
        if(quality == 0)
            repetitions = 0
        else
            repetitions++

        easiness = max(1.3, easiness + 0.1 - (5 - quality!!)*(0.08 + (5-quality!!)*0.02))

        interval = when {
            repetitions <= 1 -> 1L
            repetitions == 2 -> 6L
            else -> (interval * easiness).roundToLong()
        }

        nextPracticeDate = currentDate.plusDays(interval)
    }

    fun simulate(period: Long) {
        println("Simulation of the card $question:")
        var now = LocalDateTime.now()

        for (i in 1..period + 1) {
            println("Date: ${now.toLocalDate()}")
            if (nextPracticeDate.toLocalDate().compareTo(now.toLocalDate()) == 0){
                show()
                details()
            }
            now = now.plusDays(1)
        }
    }
}