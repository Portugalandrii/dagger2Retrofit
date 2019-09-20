package com.elyeproj.simplestappwithdagger2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {
    @Inject
    lateinit var info: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        DaggerBox.create().poke(this)
        //с помощью Dagger нам не нужно создавать обьект класса Info, он инжектится автоматически
        // info = Info()

        textView2.text = info.text
    }
}
