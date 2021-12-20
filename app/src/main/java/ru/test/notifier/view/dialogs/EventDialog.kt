package ru.test.notifier.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.view.WindowManager.LayoutParams
import androidx.fragment.app.DialogFragment

import ru.test.notifier.R


class EventDialog : DialogFragment() {
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
        return inflater.inflate(R.layout.dialog_new_event, container, false)
    }

    companion object {
        const val TAG = "EventDialog"
    }
}
