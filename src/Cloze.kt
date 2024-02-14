class Cloze (
    question: String,
    answer: String) : Card(question, answer){

    override fun show(){
        val replacedAnswer = question.replace(Regex("\\*(.*?)\\*")) {answer}
        val answeraux = answer
        answer = replacedAnswer
        super.show()
        answer = answeraux
    }
}