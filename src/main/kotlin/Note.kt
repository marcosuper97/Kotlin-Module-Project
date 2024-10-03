data class Note(
    val header:String,
    val content: String){
    fun showContent(): String{
        return "${header.uppercase()}\n$content"
    }
}