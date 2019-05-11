package app.matrix.ticketingsystem.ui.base.adapter

import app.matrix.wallpaperpexels.ui.base.adapter.BaseHolder

interface iBaseAdapterPresneter<T: BaseHolder,E:Any> {

     fun getCount(): Int
     fun onAttachAdapter(adapter: BaseAdapter<*, *, *>)
     fun onBind(holder: T, position: Int)
     fun onRecycle(holder: T)

     fun addNewItems(listNewItems: List<E>)
     fun addItem(data: E)
     fun removeItem(position: Int)
     fun getAllElements(): List<E>

}