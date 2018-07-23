package vinova.intern.nhomxnxx.mexplorer.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle


import com.snatik.storage.Storage


import vinova.intern.nhomxnxx.mexplorer.R

class ConfirmDeleteDialog : DialogFragment() {
    private val mPath: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val msg: String
        val path = arguments.getString(PATH)
        val storage = Storage(activity)
        val file = storage.getFile(path!!)
        if (file.isDirectory) {
            msg = "You are about to delete the folder with all it's content for real."
        } else {
            msg = "You are about to delete the file"
        }
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.label_delete) { dialog, which -> (activity as ConfirmListener).onConfirmDelete(path) }
        builder.setNegativeButton(R.string.label_cancel, null)
        return builder.create()
    }

    interface ConfirmListener {
        fun onConfirmDelete(path: String?)
    }

    companion object {

        private val PATH = "path"

        fun newInstance(path: String): ConfirmDeleteDialog {
            val fragment = ConfirmDeleteDialog()
            val args = Bundle()
            args.putString(PATH, path)
            fragment.arguments = args
            return fragment
        }
    }
}
