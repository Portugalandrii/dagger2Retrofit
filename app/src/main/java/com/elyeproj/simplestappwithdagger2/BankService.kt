package com.elyeproj.simplestappwithdagger2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//анотация меняет поведение метода не меняя код
//https://api.privatbank.ua/p24api/pubinfo?json&exchange&cursid=5
interface BankService {
    //https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5
    //Каждый метод представляет собой одну реализацию вызова API
    @GET("pubinfo?json&exchange")  // запрос методом GET
    fun getKurs(@Query("coursid")id:Int) : Call<ArrayList<Kurs>>   //функция выводит объект класса Call,
    // который содержит результат работы сервера

    companion object {   // сингитон - статический объект одиночка, для обращения к которому не требуется создание класса

        var BASE_URL = "http://api.privatbank.ua/p24api/"

        fun create() : BankService {    //эта функция реализует интерфейс BankService с помощью класса Retrofit

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(BankService::class.java)

        }
    }
}