package miituo.com.kotlincourse.Model

class Notas{

    var notaId: Int? = 0
    var titulo: String? = ""
    var description: String? = ""

    constructor(notaId: Int?, titulo: String?, description: String?) {
        this.notaId = notaId
        this.titulo = titulo
        this.description = description
    }
}
