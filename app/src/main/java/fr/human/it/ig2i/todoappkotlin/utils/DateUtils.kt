package fr.human.it.ig2i.todoappkotlin.utils

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
private val timeFormat = SimpleDateFormat("HH:mm", Locale.FRANCE)

fun extractDateFormatDateField(date: Date): String = dateFormat.format(date)

fun extractTimeFormatDateField(date: Date): String = timeFormat.format(date)