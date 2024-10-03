import kotlin.reflect.KFunction

class Archive(
    val name:String,
    private val returnToGeneral: () -> Unit,
    private val notes: MutableList<Note> = mutableListOf()): Input() {
    private val genNavigationMap: Map<Int, () -> Unit> = mapOf(
        1 to ::showNotes,
        2 to ::watchNotes,
        3 to ::createNote,
        0 to ::returnToGeneral.invoke()
        )

    private val textMenu: String = "1 - Просмотр доступных заметок\n" +
            "2 - Выбрать заметку\n" +
            "3 - Создать заметку\n" +
            "0 - Вернуться в основное меню"

    fun start() {
        navigation(this.textMenu,this.genNavigationMap)
    }

    private fun showNotes() {
        if(notes.isEmpty()) {
            println("Не обнаружено доступных заметок\n" +
                    "______________________")
            start()
        }
        else {
            println("Вывожу заметки:")
            for ((index, note) in notes.withIndex()) {
                println("${index + 1} - ${note.header}")
            }
            println("______________________")
            start()
        }
    }
    private fun watchNotes() {
        println("Введите номер заметки:")
        while (true) {
            val input: Int = intInput()-1
            try {
                if (notes.size >= input) {
                    println("${notes[input].showContent()}\n______________________")
                    start()
                } else {
                    println("Заметки с таким номером не существует.\n______________________")
                    start()
                }
            } catch (e: Exception) {
                println(error())
            }
        }
    }
    private fun createNote() {
        println("Введите заголовок:")
        val headerName: String = textInput()
        println("Введите содержание:")
        val contentText: String = textInput()
        notes.add(Note(headerName, contentText))
        println("Заметка \"$headerName\" создана успешно!")
        start()
    }
}

