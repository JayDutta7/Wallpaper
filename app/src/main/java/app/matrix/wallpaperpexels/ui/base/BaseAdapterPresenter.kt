package app.matrix.wallpaperpexels.ui.base

import java.util.*

abstract class BaseAdapterPresenter<T:BaseHolder,E:Any>:iBaseAdapterPresneter<T,E> {

    private var list: MutableList<E>? = null
    private var adapter: BaseAdapter<*,*,*>? = null

    override fun addNewItems(listNewItems: List<E>) {
        val currentSize = getCount()
        list!!.addAll(listNewItems)
        adapter!!.notifyItemRangeInserted(currentSize, getCount())
    }

    override fun addItem(data: E) {
        list!!.add(data)
        adapter!!.notifyItemInserted(getCount())
    }

    override fun removeItem(position: Int) {
        list!!.removeAt(position)
    }

    override fun getAllElements(): List<E> {
        return list!!
    }

    override fun getCount(): Int {
        return when {
            list != null -> list!!.size
            else -> 0
        }
    }

    override fun onAttachAdapter(adapter: BaseAdapter<*, *, *>) {
        this.adapter=adapter
    }

    override fun onBind(holder: T, position: Int) {

    }

    override fun onRecycle(holder: T) {

    }
}