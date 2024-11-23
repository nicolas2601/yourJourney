package co.edu.unab.cardona_joya_moerno.yourjourney.Models

open class Users(
    val name: String = "",
    val birthday: String = "",
    val email: String = "",
    val urlImageprofile: String = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "birthday" to birthday,
            "email" to email,
            "urlImageprofile" to urlImageprofile
        )
    }
}