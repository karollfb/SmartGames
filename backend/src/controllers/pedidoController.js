const connection = require("../database/connection")

module.exports = {

    newOrder(req, res) {
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