package ru.test.notifier.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.test.notifier.R
import ru.test.notifier.presenter.pages.EventsPresenter
import ru.test.notifier.view.adapters.EventsAdapter
import ru.test.notifier.view.dialogs.EventDialog
import ru.test.notifier.view.extensions.DialogListener

class EventsFragment: Fragment(), EventsPresenter.ContentView {

    private var recyclerView: RecyclerView? = null
    private var adapter: EventsAdapter? = null
    private var listener: DialogListener? = null
    private var presenter: EventsPresenter? = null

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
    }

    private fun createDialogListener(): DialogListener = { key, _ ->
        when(key){
            MAIN_REQUEST_CODE -> updateList()
        }
    }

    private fun updateList() = presenter?.let{ adapter?.setData(it.getData()) }

    companion object{
        const val MAIN_REQUEST_CODE = "main_request_code"
    }
}