package com.elyeproj.simplestappwithdagger2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    //Внедрение зависимости (Dagger создаст экземпляр класса Info и передаст ссылку на созданный объект)
    @Inject
    lateinit var info: Info
    @Inject
    lateinit var bankService: BankService
    @Inject
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Окончательная настройка Dagger, после которой начинает работать @Inject
        DaggerBox.create().poke(this)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler.adapter = customAdapter
        text_view.text = info.text
        var intent: Intent = Intent(this, SecondActivity::class.java)
        button.setOnClickListener { startActivity(intent) }
        bankService.getKurs(5).enqueue(object : retrofit2.Callback<ArrayList<Kurs>> {

            override fun onFailure(call: Call<ArrayList<Kurs>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ArrayList<Kurs>>, response: Response<ArrayList<Kurs>>) {

                var array: ArrayList<Kurs> = response.body()!!

                customAdapter.setData(array)
            }

        })

    }
}

class Info {
    val text = "Hello Dagger 2"
}

@Module
class InfoModule {
    @Provides
    fun getInfo(): Info {
        return Info()
    }
}

@Module
class KursModule {
    @Provides
    fun getKurs(): BankService {
        return BankService.create()
    }
}

@Module
class CustomAdapterModule {
    @Provides
    fun getCustomAdapter(): CustomAdapter {
        return CustomAdapter(arrayListOf())
    }
}


@Component(modules = arrayOf(InfoModule::class, KursModule::class, CustomAdapterModule::class))
interface Box {
    //иньекция зависимости произойдет в эту активити
    fun poke(app: MainActivity)

    fun poke(app: SecondActivity)
}

