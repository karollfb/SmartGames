package br.com.smartgames.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.smartgames.R
import br.com.smartgames.entities.Game
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.game_item.view.*

class GamesAdapter(private val gameClickListener: GameClickListener) : RecyclerView.Adapter<GamesViewHolder>() {

    interface GameClickListener{
        fun onGameDetailsClicked(game: Game)
    }

    private val items = arrayListOf<Game>()

    fun setItems(games: List<Game>) {
        items.clear()
        items.addAll(games)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return GamesViewHolder(itemView, parent.context, gameClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }
}

class GamesViewHolder(itemView: View, private val context: Context, private val gameClickListener: GamesAdapter.GameClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(game: Game) {
        Glide.with(context).load("http://192.168.42.36:3001/images/${game.foto}").into(itemView.img_game)
        itemView.tv_game_name.text = game.nomeJogo
        itemView.tv_game_plataform.text = game.plataforma
        itemView.tv_game_price.text = "R$" + "%.2f".format(game.preco).replace(".", ",")
        itemView.tv_game_location.text = game.bairro

        itemView.btn_details.setOnClickListener{
            gameClickListener.onGameDetailsClicked(game)
        }
    }
}