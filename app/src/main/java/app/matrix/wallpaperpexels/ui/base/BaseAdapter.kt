package app.matrix.wallpaperpexels.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T:BaseHolder,E:Any,P:iBaseAdapterPresneter<T,E>>: RecyclerView.Adapter<T>() {

    abstract fun getHolder(parent: ViewGroup, viewType: Int): T
    abstract fun getViewType(position: Int): Int

    private var presenter:P? = null

    fun BaseAdapter(presenter: P) {
        this.presenter = presenter
        this.presenter!!.onAttachAdapter(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return getHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
    return presenter!!.getCount()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        presenter!!.onBind(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}