package baseclass.uiinterfaces

/**
 * Created by Matrix
 */

interface IBaseAdapterPresenter<T : BaseHolder, E : Any> {

    val count: Int
    val allElements: List<E>
    fun onAttachAdapter(adapter: BaseAdapter<*, *, *>)
    fun onBind(holder: T, position: Int)
    fun onRecycle(holder: T)

    fun addNewItems(listNewItems: List<E>)
    fun addItem(data: E)
    fun removeItem(position: Int)
}
