const mysql = require('mysql2')

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'Yuki-kun051118',
    database: 'db_jogos'
})

module.exports = connection