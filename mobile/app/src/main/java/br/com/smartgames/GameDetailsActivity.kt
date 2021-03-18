package br.com.smartgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import br.com.smartgames.entities.Game
import kotlinx.android.synthetic.main.activity_game_details.*

class GameDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        val btnComprar = findViewById<Button>(R.id.btn_comprar)
        btnComprar.setOnClickListener{
            Toast.makeText(this, "Compra realizada com sucesso!", Toast.LENGTH_LONG).show()
        }

        val game = intent.getSerializableExtra("GAME") as Game
        tv_game_name.text = game.nomeJogo
        tv_game_price.text = "R$" + game.preco.toString()
        tv_game_plataform.text = game.plataforma
        tv_game_description.text = game.descricao

        val googleMapsLink = "https://www.google.com/maps/embed/v1/place?key=AIzaSyAJtNvXknHzpykLAxgECr_jrDyJX81Hi9Q&q=${game.rua}, ${game.numero},${game.bairro}, ${game.cidade}"
        val youtubeLink = game.video

        val webViewVideo = findViewById<WebView>(R.id.webview_video)
        val webViewMap = findViewById<WebView>(R.id.webview_map)

        val youtubeHtml =
            "<html>" +
                    "<body>" +
                    "<iframe width=\"100%\" height=\"600\" src=\"${youtubeLink}\" frameborder=\"0\" allowfullscreen />" +
                    "</body>" +
                    "</html>"

        val mapsHtml =
            "<html>" +
                    "<body>" +
                    "<iframe width=\"100%\" height=\"600\" src=\"${googleMapsLink}\" allowfullscreen=\"\" />" +
                    "</body>" +
                    "</html>"


        webViewVideo.settings.javaScriptEnabled = true
        webViewVideo.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webViewVideo.settings.loadWithOverviewMode = true
        webViewVideo.settings.useWideViewPort = true

        webViewMap.settings.javaScriptEnabled = true
        webViewMap.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webViewMap.settings.loadWithOverviewMode = true
        webViewMap.settings.useWideViewPort = true

        webViewVideo.loadData(youtubeHtml, "text/html", "utf-8")
        webViewMap.loadData(mapsHtml, "text/html", "utf-8")

    }

}
