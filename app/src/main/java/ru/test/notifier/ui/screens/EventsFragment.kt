package ru.test.notifier.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ru.test.notifier.NotifierApplication
import ru.test.notifier.R
import ru.test.notifier.presenter.pages.EventsPresenter
import ru.test.notifier.ui.adapters.EventsAdapter
import ru.test.notifier.ui.adapters.SwipeHelper
import ru.test.notifier.ui.adapters.ItemTouchListener
import ru.test.notifier.ui.dialogs.EventDialog
import ru.test.notifier.ui.extensions.DialogListener
import ru.test.notifier.ui.extensions.toBitmap
import ru.test.notifier.ui.model.ContextButtonModel

class EventsFragment: Fragment(), EventsPresenter.ContentView {

    private var recyclerView: RecyclerView? = null
    private var adapter: EventsAdapter? = null
    private var listener: DialogListener? = null
    private var presenter: EventsPresenter? = null

    private var deletedItem: Pair<Int, String?>? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_events)
        adapter = EventsAdapter(view.context)
        listener = createDialogListener()
        presenter = EventsPresenter(this)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter

        updateList()
        recyclerView?.layoutManager = LinearLayoutManager(view.context)

        val eventButton: FloatingActionButton = view.findViewById(R.id.fab)
        eventButton.setOnClickListener{
            listener?.let {
                val dialog = EventDialog()
                dialog.show(parentFragmentManager, EventDialog.TAG)
                dialog.setFragmentResultListener(MAIN_REQUEST_CODE, it)
            }
        }
        recyclerView?.let{
            val itemTouchHelper = ItemTouchHelper(SwipeHelper(
                view.context,
                it,
                listOf(
                    ContextButtonModel(
                        EDIT_BUTTON_CODE,
                        "edit",
                        R.drawable.ic_edit,
                        200
                    ),
                    ContextButtonModel(
                        DELETE_BUTTON_CODE,
                        "delete",
                        R.drawable.ic_delete,
                        200
                    ),
                ),
                object :ItemTouchListener {
                    override fun onAction(position: Int, action: String) {
                        when (action) {
                            EDIT_BUTTON_CODE -> editItem(position)
                            DELETE_BUTTON_CODE -> deleteItem(position)
                        }
                    }
                }
            ))
            itemTouchHelper.attachToRecyclerView(it)
        }

    }

    private fun createDialogListener(): DialogListener = { key, _ ->
        when(key){
            MAIN_REQUEST_CODE -> updateList()
        }
    }

    private fun updateList() = presenter?.let{ adapter?.setData(it.getData()) }

    private fun undoAction(){
        recyclerView?.let { view ->
            deletedItem?.let { item ->
                Snackbar.make(view, "$item is deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo"){
                        adapter?.add(item.first, item.second ?: "")
                        adapter?.notifyItemInserted(item.first)
                        deletedItem = null
                    }.show()
            }
        }
    }

    private fun editItem(position: Int){
        // TODO: make edit button
    }

    private fun deleteItem(position: Int){
        deletedItem = Pair(position, adapter?.removeAt(position))
        adapter?.notifyItemRemoved(position)
        undoAction()
    }

    companion object{
        const val MAIN_REQUEST_CODE = "main_request_code"

        const val EDIT_BUTTON_CODE = "edit_button_code"
        const val DELETE_BUTTON_CODE = "delete_button_code"
    }
}