package ronny.sousa.appcontatos

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ronny.sousa.appcontatos.adapter.AdapterContatos
import ronny.sousa.appcontatos.api.RetrofitClient
import ronny.sousa.appcontatos.model.Contato

class MainActivity : AppCompatActivity() {
    lateinit var recycle: RecyclerView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recycle = findViewById(R.id.recyclerContatos)
        recycle.layoutManager = LinearLayoutManager(this)




        val retrofitCli:RetrofitClient = RetrofitClient();
        retrofitCli.contatoService.getAllContatos().enqueue(object : Callback<List<Contato>> {
            override fun onResponse(call: Call<List<Contato>>, response: Response<List<Contato>>) {
                if (response.isSuccessful) {
                    val contatos = response.body()
                    if(contatos !=null)
                    {
                        val adapter:AdapterContatos= AdapterContatos(contatos,this@MainActivity)
                        recycle.adapter = adapter
                    }
                }
                else
                {
                    // Trate erros de resposta, como 404, 500 etc.
                }
            }

            override fun onFailure(call: Call<List<Contato>>, t: Throwable) {
                // Trate erros de rede ou qualquer outro erro ao fazer a chamada HTTP
                Log.e("retrofit", "onFailure: ",t )
            }
        })

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var adapter: AdapterContatos = recycle.adapter as AdapterContatos

        var position:Int = adapter.posicaoClicada


        when(item.itemId){
            R.id.menuItemLigar -> Toast.makeText(this,"Ligar",Toast.LENGTH_LONG).show()
            R.id.menuItemEmail -> Toast.makeText(this,"Email",Toast.LENGTH_LONG).show()
            R.id.menuItemMapa -> Toast.makeText(this,"Mapa",Toast.LENGTH_LONG).show()
        }
            return true
    }


}