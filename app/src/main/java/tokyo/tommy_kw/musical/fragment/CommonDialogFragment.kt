package tokyo.tommy_kw.musical.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment

/**
 * Created by tommy on 15/10/15.
 */
class CommonDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    interface OnDialogClickListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    companion object {
        enum class ButtonType {
            POSITIVE, NEGATIVE
        }

        fun newInstance(title: String, message: String, type: ButtonType, fragment: Fragment): CommonDialogFragment {
            val fragment = CommonDialogFragment()
            val bundle = Bundle()
            bundle.putString("title", title)
            bundle.putString("message", message)
            bundle.putString("type", type.name)
            fragment.arguments = bundle
            fragment.setTargetFragment(fragment, 0)
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        retainInstance = true
        val title = arguments.getString("title")
        val message = arguments.getString("message")
        val type = arguments.getString("type")
        val dialog = AlertDialog.Builder(activity).setTitle(title).setMessage(message).setPositiveButton("OK", this)

        if (type == ButtonType.NEGATIVE.name) {
            dialog.setNegativeButton("Cancel", this)
        }

        return dialog.create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        val listener: OnDialogClickListener

        try {
            if (targetFragment != null) {
                listener = targetFragment as OnDialogClickListener
            } else {
                listener = activity as OnDialogClickListener
            }
        } catch (e: ClassCastException) {
            return
        }

        when (which) {
            DialogInterface.BUTTON_POSITIVE -> listener.onPositiveClick()
            DialogInterface.BUTTON_NEGATIVE -> listener.onNegativeClick()
        }
    }
}