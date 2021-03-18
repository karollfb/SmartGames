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

    novoPedido(req, res) {
        const pedido = {
            id: 0,
            ...req.body
        }
        
        connection.query("INSERT INTO pedidos VALUES (?, ?, ?, ?)", 
        [0, pedido.id_jogosloja, pedido.nome, pedido.cpf],
        (err, results) => {
            if (err) 
                return res.status(500).json({ error: "Erro interno no servidor" })  

            pedido.id = results.insertId

            return res.json(pedido)
        })
    }
    
}