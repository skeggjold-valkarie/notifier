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
import ru.test.notifier.domain.model.PersonModel
import ru.test.notifier.ui.screens.PersonsFragment.Companion.PERSON_REQUEST_CODE
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

        val closeButton = view.findViewById<Button>(R.id.negative)
        val addEventButton = view.findViewById<Button>(R.id.positive)

        val firstNameTextView = view.findViewById<TextView>(R.id.first_name)
        val middleNameTextView = view.findViewById<TextView>(R.id.middle_name)
        val lastNameTextView = view.findViewById<TextView>(R.id.last_name)
        val phoneTextView = view.findViewById<TextView>(R.id.phone)
        val avatarTextView = view.findViewById<TextView>(R.id.avatar)


        closeButton.setOnClickListener{ dialog?.dismiss() }
        addEventButton.setOnClickListener{
            val personModel = PersonModel(
                firstName = firstNameTextView.text.toString(),
                middleName = middleNameTextView.text.toString(),
                lastName = lastNameTextView.text.toString(),
                phone = phoneTextView.text.toString(),
                avatar = avatarTextView.text.toString()
            )

            val bundle = Bundle()
            bundle.putParcelable(PERSON_MODEL, personModel)
            parentFragmentManager.setFragmentResult(PERSON_REQUEST_CODE, bundle)
            dialog?.dismiss()
        }
    }


    companion object {
        const val TAG = "PersonDialog"
        const val PERSON_MODEL = "PersonModel"
    }
}
