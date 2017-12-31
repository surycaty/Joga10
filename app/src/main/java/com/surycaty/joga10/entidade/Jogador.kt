package com.surycaty.joga10.entidade

import java.io.Serializable

/**
 * Created by negus on 27/09/17.
 */

class Jogador : Serializable {

    var id: Int = 0
    var nome: String? = null
    var posicao: String? = null
    var level: Int = 0

    constructor() {}

    constructor(id: Int, nome: String, posicao: String, level: Int) {
        this.id = id
        this.nome = nome
        this.posicao = posicao
        this.level = level
    }

}
