const mysql = require('mysql2')

// Conexão do banco de dados
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Yuki-kun051118',
    database: 'db_jogos'
})

// Exporta as funções que vão ser utilizadas nas rotas da API
module.exports = {

    getAll(req, res) {
        connection.query("SELECT * FROM jogos", (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })

            return res.json(results)
        })
    },

    getById(req, res) {
    
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