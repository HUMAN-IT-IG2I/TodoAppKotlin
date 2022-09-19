package fr.human.it.ig2i.todoappkotlin.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun displayToast(context: Context, @StringRes stringResourceId: Int) {
    Toast.makeText(context, stringResourceId, Toast.LENGTH_LONG).show()
}