package com.elyeproj.simplestappwithdagger2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import javax.inject.Inject

class CustomAdapter  constructor(val userList: ArrayList<Kurs>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

//создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
// Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder,
// и каждый дочерний view-компонент может быть найден и сохранен.

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_layout, p0, false)
        return ViewHolder(v);
    }
//принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте
    override fun onBindViewHolder(vh: ViewHolder, posit: Int) {
        // vh - поле
        vh?.sale?.text = "Продажа: "+userList[posit].sale
        vh?.buy?.text = "Покупка: "+userList[posit].buy
        vh?.ccy?.text = "Валюта: "+userList[posit].ccy
    }
//возвращает общее количество элементов списка. Значения списка передаются с помощью конструктора.
    override fun getItemCount(): Int {
        return userList.size
    }
// содержит ссылки эллемента разметки
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sale = itemView.findViewById<TextView>(R.id.sale)
        val buy = itemView.findViewById<TextView>(R.id.buy)
        val ccy = itemView.findViewById<TextView>(R.id.ccy)

    }
    fun setData(users: ArrayList<Kurs>){
        userList.clear()
        userList.addAll(users)
        //notifyItemRangeInserted(0, categoryModel.size)
        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!$userList")
        notifyDataSetChanged()
    }

}