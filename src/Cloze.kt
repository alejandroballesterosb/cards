class Cloze (
    question: String,
    answer: String) : Card(question, answer){

    override fun show(){
        val answeraux = answer
        answer = question.replace(Regex("\\*(.*?)\\*")) {answer}
        super.show()
        answer = answeraux
    }
}