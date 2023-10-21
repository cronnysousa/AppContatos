package ronny.sousa.appcontatos.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ronny.sousa.appcontatos.R
import ronny.sousa.appcontatos.model.Contato

class AdapterContatos(
    private val contatos: List<Contato>,
    val context:Context
): Adapter<AdapterContatos.ContatoViewHolder>()
{

    public var posicaoClicada: Int = -1

    class ContatoViewHolder(
        itemView: View,
        val context:Context
    ) : RecyclerView.ViewHolder(itemView),
        OnCreateContextMenuListener {
        val nomeTextView: TextView = itemView.findViewById(R.id.nomeTextView)
        val telefoneTextView: TextView = itemView.findViewById(R.id.telefoneTextView)
        val fotoImageView: ImageView = itemView.findViewById(R.id.fotoImageView)

        init {
            itemView.setOnCreateContextMenuListener(this)

        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {

            MenuInflater(context).inflate(R.menu.menu_contato,menu)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ContatoViewHolder {
        val view = LayoutInflater
                        .from(parent.context)
                        .inflate(
                            R.layout.item_contato,
                            parent,
                            false)

        return ContatoViewHolder(view,context)
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(
        holder: ContatoViewHolder,
        position: Int
    )
    {
        val contato = contatos[position]
        holder.nomeTextView.text = contato.nome
        holder.telefoneTextView.text = contato.telefone
        holder.itemView.setOnLongClickListener { v ->
            posicaoClicada = holder.getAdapterPosition()
            false
        }

    }
}


