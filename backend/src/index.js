const express = require('express')
const cors = require('cors')

const router = require("./routes")

const app = express()
const port = 3001 

app.use(cors())
app.use(express.json())

app.use("/images", express.static('images/'))
app.use(router)

app.listen(port, () => {
    console.log(`Server running on localhost:${port}...`)
})