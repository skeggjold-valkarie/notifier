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
import ru.test.notifier.R
import ru.test.notifier.domain.model.EventModel
import ru.test.notifier.domain.model.PersonEventModel
import ru.test.notifier.presenter.pages.EventsPresenter
import ru.test.notifier.ui.adapters.EventsAdapter
import ru.test.notifier.ui.adapters.ItemTouchListener
import ru.test.notifier.ui.adapters.SwipeHelper
import ru.test.notifier.ui.dialogs.EventDialog
import ru.test.notifier.ui.extensions.DialogListener
import ru.test.notifier.ui.model.ContextButtonModel

class EventsFragment: Fragment(), EventsPresenter.ContentView {

    private var recyclerView: RecyclerView? = null
    private var adapter: EventsAdapter<PersonEventModel>? = null
    private var listener: DialogListener? = null
    private var presenter: EventsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_events)
        adapter = EventsAdapter()
        listener = createDialogListener()
        presenter = EventsPresenter(this)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter

        adapter?.setData(presenter?.getData() ?: emptyList())
        recyclerView?.layoutManager = LinearLayoutManager(view.context)

        val eventButton: FloatingActionButton = view.findViewById(R.id.fab)
        eventButton.setOnClickListener{ showDialog() }
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
                    override fun onAction(position: Int, code: String) {
                        when (code) {
                            EDIT_BUTTON_CODE -> editItem(position)
                            DELETE_BUTTON_CODE -> deleteItem(position)
                        }
                    }
                }
            ))
            itemTouchHelper.attachToRecyclerView(it)
        }

    }

    override fun showError(message: String){
        recyclerView?.let { view ->
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun restoreItem(position: Int, model: PersonEventModel) { adapter?.add(position, model) }

    private fun createDialogListener(): DialogListener = { key, bundle ->
        when(key){
            MAIN_STORE_EVENT_CODE -> storeEvent(bundle)
        }
    }

    private fun storeEvent(bundle: Bundle) {
        val eventModel = bundle.getParcelable<EventModel>(EVENT_MODEL)
        presenter?.storeEvent(eventModel)
    }

    private fun undoAction(){
        recyclerView?.let { view ->
            Snackbar.make(view, resources.getString(R.string.undo), Snackbar.LENGTH_LONG)
                .setAction(resources.getString(R.string.undo)){ presenter?.restoreDeleted() }
                .show()
        }
    }

    // TODO: update method
    private fun editItem(position: Int){
        showDialog()
    }

    private fun deleteItem(position: Int){
        presenter?.let{
            adapter?.removeAt(position)?.let{ model ->
                adapter?.removeAt(position)
                it.deleteEvent(position, model)
                undoAction()
            }
        }
    }

    private fun showDialog(){
        listener?.let {
            val dialog = EventDialog()
            dialog.show(parentFragmentManager, EventDialog.TAG)
            dialog.setFragmentResultListener(MAIN_STORE_EVENT_CODE, it)
        }
    }

    companion object{
        const val MAIN_STORE_EVENT_CODE = "main_store_event_request_code"
        const val EVENT_MODEL = "EventModel"

        const val EDIT_BUTTON_CODE = "edit_button_code"
        const val DELETE_BUTTON_CODE = "delete_button_code"
    }
}