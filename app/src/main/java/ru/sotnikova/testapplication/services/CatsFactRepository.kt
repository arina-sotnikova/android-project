package ru.sotnikova.testapplication.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import ru.sotnikova.testapplication.data.CatsFact
import ru.sotnikova.testapplication.services.RetrofitClient.getInstance

class CatsFactRepository {
    private val api: CatsFactsApiService

    init {
        api =
            getInstance("https://cat-fact.herokuapp.com").create<T>(CatsFactsApiService::class.java)
    }

    val catsFactsResp: LiveData<List<Any>>
        get() {
            val data: MutableLiveData<List<CatsFact>> = MutableLiveData<List<CatsFact>>()
            api.getFacts().enqueue(object : Callback<List<CatsFact?>?>() {
                override fun onResponse(
                    call: Call<List<CatsFact?>?>,
                    response: Response<List<CatsFact?>?>
                ) {
                    if (response.isSuccessful) {
                        data.setValue(response.body())
                    } else {
                        // Handle error
                    }
                }

                override fun onFailure(call: Call<List<CatsFact?>?>, t: Throwable) {
                    print(t)
                }
            })
            return catsFactsResp
        }
}
