package ru.test.notifier.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager.LayoutParams
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ru.test.notifier.R
import ru.test.notifier.data.db.DataBaseRepository
import ru.test.notifier.domain.model.EventTypeModel
import java.util.*
import ru.test.notifier.ui.screens.EventTypesFragment


class EventTypeDialog : DialogFragment() {

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
        return inflater.inflate(R.layout.dialog_event_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val closeButton = view.findViewById<Button>(R.id.negative)
        val addEventButton = view.findViewById<Button>(R.id.positive)

        val titleTextView = view.findViewById<TextView>(R.id.title)
        val descriptionTextView = view.findViewById<TextView>(R.id.description)

        closeButton.setOnClickListener{ dialog?.dismiss() }
        addEventButton.setOnClickListener{
            val model = EventTypeModel(
                title = titleTextView.text.toString(),
                description = descriptionTextView.text.toString()
            )
            val bundle = Bundle()
            bundle.putParcelable(EVENT_TYPE_MODEL, model)
            parentFragmentManager.setFragmentResult(EventTypesFragment.EVENT_REQUEST_CODE, bundle)
            dialog?.dismiss()
        }
    }

    companion object {
        const val TAG = "EventTypeDialog"
        const val EVENT_TYPE_MODEL = "EventTypeModel"
    }
}
