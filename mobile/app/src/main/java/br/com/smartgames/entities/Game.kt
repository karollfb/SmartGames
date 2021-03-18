package br.com.smartgames.entities

import java.io.Serializable

data class Game(
    var id_jogo: Int,
    var id_loja: Int,
    var preco: Double,
    var nomeJogo: String,
    var descricao: String,
    var plataforma: String,
    var foto: String,
    var video: String,
    var nomeLoja: String,
    var rua: String,
    var numero: Int,
    var bairro: String,
    var cep: String,
    var cidade: String
) : Serializable {
    override fun toString(): String {
        return "Game(id_jogo=$id_jogo, id_loja=$id_loja, preco=$preco, nomeJogo='$nomeJogo', descricao='$descricao', plataforma='$plataforma', foto='$foto', video='$video', nomeLoja='$nomeLoja', rua='$rua', numero=$numero, bairro='$bairro', cep='$cep', cidade='$cidade')"
    }
}