package ru.sotnikova.testapplication.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sotnikova.testapplication.data.CatsFact

//object RetrofitClientV1 {
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://cat-fact.herokuapp.com")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val service: CatsFactsApiService = retrofit.create(CatsFactsApiService::class.java)
//
//    suspend fun getRandomCatFact(): CatsFact {
//        var catsFactResp: CatsFact? = null
//        service.getFacts()
//            .enqueue(object : Callback<Array<CatsFact>> {
//                override fun onFailure(call: Call<Array<CatsFact>>, t: Throwable) {
//                    print(t.message)
//                }
//                override fun onResponse(
//                    call: Call<Array<CatsFact>>,
//                    response: Response<Array<CatsFact>>
//                ) {
//                    if (response.isSuccessful) catsFactResp = response.body()!![0]
//                    else print("todo") //todo
//                }
//            })
//        return catsFactResp!!
//    }
//}