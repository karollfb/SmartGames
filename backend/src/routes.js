const express = require('express')
const multer  = require('multer')
const path = require('path')

const router = express.Router()

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, __dirname + '/../images')
    },
    filename: function (req, file, cb) {
        const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9)
        cb(null, uniqueSuffix + path.extname(file.originalname))
    }
})

const upload = multer({ storage: storage })

// Controllers
const jogosController = require("./controllers/jogosController")
const lojasController = require("./controllers/lojasController")
const pedidoController = require("./controllers/pedidoController")

router.get("/jogos", jogosController.getAll)
router.post("/jogos", upload.single('foto'), jogosController.create)
router.get("/lojas", lojasController.getAll)
router.post("/lojas", lojasController.create)
router.get("/lojas/jogos", lojasController.getGames)
router.post("/lojas/jogos", lojasController.createGame)
router.post("/pedidos", pedidoController.novoPedido)

module.exports = router