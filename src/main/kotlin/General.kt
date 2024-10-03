import java.util.concurrent.atomic.AtomicBoolean
import kotlin.system.exitProcess

class General():Input() {
    private val archives: MutableList<Archive> = mutableListOf()
    private val genNavigationMap: Map<Int, () -> Unit> = mapOf(
        1 to ::showArchives,
        2 to ::watchArchive,
        3 to ::createArchive,
        0 to ::exit
    )
    private val checkStatus = AtomicBoolean(false)
    private val textMenu: String = "1 - Просмотр доступных архивов\n" +
            "2 - Выбрать архив\n" +
            "3 - Создать архив\n" +
            "0 - Завершить работу приложения\n" +
            "______________________"

    fun start() {
        if (checkStatus.compareAndSet(false, true)) {
            println("Добро пожаловать в заметки pre-alpha-beta-meta v0.001 ≽^•⩊•^≼")
        }
        navigation(textMenu,genNavigationMap)
    }

    private fun showArchives() {
        if(archives.isEmpty()) {
            println("Не обнаружено доступных архивов\n" +
                    "______________________")
            start()
        }
        else {
            println("Список архивов:")
            for ((index, archive) in archives.withIndex()) {
                println("${index + 1} - ${archive.name}")
            }
            println("______________________")
            start()
        }
    }

    private fun createArchive() {
        val nameArchive: String = textInput()
        archives.add(Archive(nameArchive, ::start))
        println("Архив \"$nameArchive\" создан успешно!\n" +
                "______________________")
        start()
    }

    private fun watchArchive() {
        println("Выберите архив для просмотра:")
        while (true){
            val input: Int = intInput() - 1
            try {
                //archives[input].navigation(archives[input].textMenu, archives[input].genNavigationMap)
                archives[input].start()
            } catch (e: Exception) {
                println("Архива с таким номером не существует\n" +
                        "______________________")
                start()
            }
        }
    }

    private fun exit (){
        println("Программа завершена, до скорых встреч! ᕙ(  •̀ ᗜ •́  )ᕗ")
        exitProcess(0)
    }

}
