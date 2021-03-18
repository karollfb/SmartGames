package br.com.smartgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.smartgames.adapters.GamesAdapter
import br.com.smartgames.entities.Game
import br.com.smartgames.http.HttpHelper
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.IOException

class MainActivity : AppCompatActivity(), GamesAdapter.GameClickListener {

    private lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        doGamesRequest()
    }

    private fun setupRecyclerView() {
        adapter = GamesAdapter(this)
        rv_games.layoutManager = LinearLayoutManager(this)
        rv_games.adapter = adapter
    }

    private fun doGamesRequest(){
        val httpHelper = HttpHelper()

        doAsync {
            httpHelper.get("/lojas/jogos", object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val gson = Gson()

                    val games = gson.fromJson(body, Array<Game>::class.java)

                    uiThread {
                        adapter.setItems(games.asList())
                    }
                }
            })
        }
    }

    override fun onGameDetailsClicked(game: Game) {
        val intent = Intent(this, GameDetailsActivity::class.java)
        intent.putExtra("GAME", game)

        startActivity(intent)
    }
}
