const connection = require("../database/connection")

module.exports = {

    getGames(req, res) {
        connection.query(`SELECT jogosloja.*, jogos.nome AS nomeJogo, jogos.descricao, 
        jogos.plataforma, jogos.foto, jogos.video, lojas.nome AS nomeLoja, lojas.rua, lojas.numero, lojas.bairro, lojas.cep, lojas.cidade FROM jogosloja 
        INNER JOIN jogos ON jogosloja.id_jogo = jogos.id
        INNER JOIN lojas ON jogosloja.id_loja = lojas.id`, 
        (err, results) => {

                console.log(err)

            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })

            return res.json(results)
        })
    },

    createGame(req, res) {
        const jogosLoja = req.body

        connection.query("INSERT INTO jogosloja VALUES (?, ?, ?, ?)", 
        [0, jogosLoja.id_jogo, jogosLoja.id_loja, jogosLoja.preco],
        (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })  

            return res.json(jogosLoja)
        })
    },

    getAll(req, res) {
        connection.query("SELECT * FROM lojas", (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })

            return res.json(results)
        })
    },

    create(req, res) {
        const loja = {
            id: 0,
            ...req.body,
        }

        connection.query("INSERT INTO lojas VALUES (0, ?, ? ,?, ?, ?, ?)", 
        [loja.nome, loja.rua, loja.numero, loja.bairro, loja.cep, loja.cidade],
        (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })  

            loja.id = results.insertId

            return res.json(loja)
        })
    }
}