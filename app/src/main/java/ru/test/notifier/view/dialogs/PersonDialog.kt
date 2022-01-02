package ru.test.notifier.view.dialogs

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
import ru.test.notifier.storage.StorageRepository
import ru.test.notifier.view.screens.MainFragment
import ru.test.notifier.view.screens.PersonsFragment
import java.util.*

class PersonDialog : DialogFragment() {

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
        return inflater.inflate(R.layout.dialog_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage = StorageRepository.getInstance()

        val closeButton = view.findViewById<Button>(R.id.negative)
        val addEventButton = view.findViewById<Button>(R.id.positive)

        val firstNameTextView = view.findViewById<TextView>(R.id.first_name)
        val middleNameTextView = view.findViewById<TextView>(R.id.middle_name)
        val lastNameTextView = view.findViewById<TextView>(R.id.last_name)
        val phoneTextView = view.findViewById<TextView>(R.id.phone)
        val avatarTextView = view.findViewById<TextView>(R.id.avatar)


        closeButton.setOnClickListener{ dialog?.dismiss() }
        addEventButton.setOnClickListener{
            storage.savePerson(
                firstNameTextView.text.toString(),
                middleNameTextView.text.toString(),
                lastNameTextView.text.toString(),
                phoneTextView.text.toString(),
                avatarTextView.text.toString()
            )
            parentFragmentManager.setFragmentResult(PersonsFragment.PERSON_REQUEST_CODE, Bundle())
            dialog?.dismiss()
        }
    }


    companion object {
        const val TAG = "PersonDialog"
    }
}
