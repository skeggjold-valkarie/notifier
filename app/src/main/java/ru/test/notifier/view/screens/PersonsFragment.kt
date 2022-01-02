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
import ru.test.notifier.presenter.pages.PersonsPresenter
import ru.test.notifier.storage.StorageRepository
import ru.test.notifier.view.adapters.EventsAdapter
import ru.test.notifier.view.dialogs.EventDialog
import ru.test.notifier.view.dialogs.PersonDialog
import ru.test.notifier.view.extensions.DialogListener

class PersonsFragment: Fragment(), PersonsPresenter.ContentView {

    private var recyclerView: RecyclerView? = null
    private var adapter: EventsAdapter? = null
    private var presenter: PersonsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_persons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_events)
        adapter = EventsAdapter(view.context)
        val listener = createDialogListener()
        presenter = PersonsPresenter(this)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
        updateList()
        recyclerView?.layoutManager = LinearLayoutManager(view.context)

        val eventButton: FloatingActionButton = view.findViewById(R.id.fab)
        eventButton.setOnClickListener{
            val dialog = PersonDialog()
            dialog.show(parentFragmentManager, PersonDialog.TAG)
            dialog.setFragmentResultListener(PERSON_REQUEST_CODE, listener)
        }
    }

    private fun createDialogListener(): DialogListener = { key, _ ->
        when(key){
            PERSON_REQUEST_CODE -> updateList()
        }
    }

    private fun updateList() = presenter?.let{ adapter?.setData(it.getData()) }

    companion object{
        const val PERSON_REQUEST_CODE = "person_request_code"
    }

}