package ronny.sousa.appcontatos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val BASE_URL = "https://web-jgzyytlyiep5.up-de-fra1-1.apps.run-on-seenode.com/"  // Substitua pela URL base da sua API

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val contatoService: ContatoService = retrofit.create(ContatoService::class.java)

}