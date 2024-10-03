import java.util.Scanner

open class Input {
    private val scanner: Scanner = Scanner(System.`in`)

    fun textInput(): String {
        println("Введите текст")
        while (true) {
            try {
                val text: String = scanner.next()
                if (text.isEmpty()) {
                    println(error())
                    scanner.nextLine()
                    continue
                } else return text
            } catch (e: Exception) {
                println(error())

            }
        }
    }

    fun intInput(): Int {
        println("Введите команду для выбора")
        while (true) {
            try {
                val number: Int = scanner.nextInt()
                if (number.toString().isEmpty()) {
                    println(error())
                    continue
                } else return number
            } catch (e: Exception) {
                println(error())
                scanner.nextLine()
            }
        }
    }

    fun navigation(menu:String, navigationMap: Map<Int, () -> Unit>) {
        println(menu)
        while (true) {
            try {
                val choice: Int = intInput()
                if(choice<=3)
                navigationMap[choice]?.invoke()
                else {
                    println(error())
                    continue
                }
            } catch (e: Exception) {
                println(error())
                scanner.nextLine()
            }
        }
    }
    fun error():String{
        return "Ошибка ввода, пожалуйста, попробуйте снова"
    }
}
