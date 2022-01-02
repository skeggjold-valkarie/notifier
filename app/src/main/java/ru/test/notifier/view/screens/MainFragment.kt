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
import ru.test.notifier.storage.StorageRepository
import ru.test.notifier.view.adapters.EventsAdapter
import ru.test.notifier.view.dialogs.EventDialog
import ru.test.notifier.view.dialogs.EventTypeDialog
import ru.test.notifier.view.extensions.DialogListener

class MainFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var storage: StorageRepository
    private lateinit var adapter: EventsAdapter
    private lateinit var listener: DialogListener

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_events)
        storage = StorageRepository.getInstance()
        adapter = EventsAdapter(view.context)
        listener = createDialogListener()

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        updateList()
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val eventButton: FloatingActionButton = view.findViewById(R.id.fab)
        eventButton.setOnClickListener{
            val dialog = EventDialog()
            dialog.show(parentFragmentManager, EventDialog.TAG)
            dialog.setFragmentResultListener(MAIN_REQUEST_CODE, listener)
        }
    }

    private fun createDialogListener(): DialogListener = { key, _ ->
        when(key){
            MAIN_REQUEST_CODE -> updateList()
        }
    }

    private fun updateList(){
        adapter.setData(storage.getAllEvents().map{ it.event })
    }

    companion object{
        const val MAIN_REQUEST_CODE = "main_request_code"
    }
}