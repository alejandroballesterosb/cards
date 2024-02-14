class Cloze (
    question: String,
    answer: String) : Card(question, answer){

    override fun show(){
        val answerAux = answer
        answer = question.replace(Regex("\\*(.*?)\\*")) {answer}
        super.show()
        answer = answerAux
    }
}