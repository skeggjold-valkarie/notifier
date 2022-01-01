package ru.test.notifier.view.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.view.WindowManager.LayoutParams
import android.widget.Button
import androidx.fragment.app.DialogFragment

import ru.test.notifier.R
import ru.test.notifier.storage.StorageRepository

import android.app.DatePickerDialog.OnDateSetListener
import java.text.SimpleDateFormat
import java.util.*

import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import ru.test.notifier.model.ListObject
import ru.test.notifier.view.screens.EventsFragment
import ru.test.notifier.view.screens.MainFragment


class EventDialog : DialogFragment() {

    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            val params = window?.attributes
            params?.width = LayoutParams.MATCH_PARENT
            params?.height = LayoutParams.MATCH_PARENT
            window?.attributes = params
        }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage = StorageRepository.getInstance()

        val closeButton = view.findViewById<Button>(R.id.negative)
        val addEventButton = view.findViewById<Button>(R.id.positive)

        val eventDateText = view.findViewById<TextView>(R.id.description)

        val eventSpinner = view.findViewById<Spinner>(R.id.event)
        val personSpinner = view.findViewById<Spinner>(R.id.person)

        val eventTypes = storage.getAllEventTypes()?.map{ ListObject(it.id, it.title ?: "")}
        val users = storage.getAllUsers()?.map{ ListObject(it.id, it.firstName ?: "")}

        setSpinnerAdapter(eventSpinner, eventTypes)
        setSpinnerAdapter(personSpinner, users)

        val date =
            OnDateSetListener { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
                eventDateText.text = dateFormat.format(calendar.time)
            }

        closeButton.setOnClickListener{ dialog?.dismiss() }
        eventDateText.setOnClickListener{
            activity?.let{
                DatePickerDialog(
                    it,
                    date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }

        }
        addEventButton.setOnClickListener{
            storage.saveEvent(
                (personSpinner.selectedItem as? ListObject)?.id,
                (eventSpinner.selectedItem as? ListObject)?.id,
                calendar.time)
            parentFragmentManager.setFragmentResult(MainFragment.MAIN_REQUEST_CODE, Bundle())
            dialog?.dismiss()
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner, data: List<ListObject>?){

        activity?.let {
            val adapter = ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                data ?: emptyList()
            )
            spinner.adapter = adapter
        }
    }

    companion object {
        const val TAG = "EventDialog"
        const val DATE_FORMAT = "MM/dd/yyyy"
    }
}
