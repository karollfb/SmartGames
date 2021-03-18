const express = require('express')
const cors = require('cors')

// Router com as rotas da API
const router = require("./routes")

// Criando a instância do express
const app = express()
const port = 3001 

app.use(cors())
app.use(express.json())

app.use("/images", express.static('images/'))
app.use(router)

app.listen(port, () => {
    console.log(`Server running on localhost:${port}...`)
})