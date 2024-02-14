class Cloze (
    question: String,
    answer: String) : Card(question, answer){

    override fun show(){
        print(" $question (ENTER to see answer)")
        readln()

        val replacedAnswer = question.replace(Regex("\\*(.*?)\\*")) {answer}

        print(" $replacedAnswer (Type 0 -> Difficult 3 -> Doubt 5 -> Easy): ")
        do {
            quality = readln().toIntOrNull()
            if(quality != 0 && quality != 3 && quality != 5){
                print("Please, try again (Type 0 -> Difficult 3 -> Doubt 5 -> Easy): ")
            }
        } while (quality != 0 && quality != 3 && quality != 5)
        update(nextPracticeDate)
    }
}