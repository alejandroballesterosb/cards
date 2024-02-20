import java.time.*
import java.util.*

class Cloze (
    question: String,
    answer: String) : Card(question, answer){

    override fun show(){
        val answerAux = answer
        answer = question.replace(Regex("\\*(.*?)\\*")) {answer}
        super.show()
        answer = answerAux
    }

    override fun getType(): String {
        return "cloze"
    }


}