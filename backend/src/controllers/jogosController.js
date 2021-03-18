const connection = require("../database/connection")

module.exports = {

    getAll(req, res) {
        connection.query("SELECT * FROM jogos", (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })

            return res.json(results)
        })
    },

    create(req, res) {
        const jogo = {
            id: 0,
            ...req.body,
            imagem: req.file.filename
        }

        connection.query("INSERT INTO jogos VALUES (0, ?, ? ,?, ?, ?)", 
        [jogo.nome, jogo.descricao, jogo.plataforma, req.file.filename, jogo.video],
        (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })  

            jogo.id = results.insertId

            return res.json(jogo)
        })
    }
}