package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * TimeUnits.SECOND.milliseconds
        TimeUnits.MINUTE -> value * TimeUnits.MINUTE.milliseconds
        TimeUnits.HOUR -> value * TimeUnits.HOUR.milliseconds
        TimeUnits.DAY -> value * TimeUnits.DAY.milliseconds
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = this.time - date.time

    return when (val absDiff = if (diff < 0) diff * (-1) else diff) {
        in 0..1 * TimeUnits.SECOND.milliseconds -> "только что"
        in 1 * TimeUnits.SECOND.milliseconds..45 * TimeUnits.SECOND.milliseconds -> "${humanizePrefix(diff)}несколько секунд${humanizePostfix(diff)}"
        in 45 * TimeUnits.SECOND.milliseconds..75 * TimeUnits.SECOND.milliseconds -> "${humanizePrefix(diff)}минуту${humanizePostfix(diff)}"
        in 75 * TimeUnits.SECOND.milliseconds..45 * TimeUnits.MINUTE.milliseconds ->
            "${humanizePrefix(diff)}${humanize(absDiff, TimeUnits.MINUTE)}${humanizePostfix(diff)}"
        in 45 * TimeUnits.MINUTE.milliseconds..75 * TimeUnits.MINUTE.milliseconds -> "${humanizePrefix(diff)}час${humanizePostfix(diff)}"
        in 75 * TimeUnits.MINUTE.milliseconds..22 * TimeUnits.HOUR.milliseconds ->
            "${humanizePrefix(diff)}${humanize(absDiff, TimeUnits.HOUR)}${humanizePostfix(diff)}"
        in 22 * TimeUnits.HOUR.milliseconds..26 * TimeUnits.HOUR.milliseconds -> "${humanizePrefix(diff)}день${humanizePostfix(diff)}"
        in 26 * TimeUnits.HOUR.milliseconds..360 * TimeUnits.DAY.milliseconds ->
            "${humanizePrefix(diff)}${humanize(absDiff, TimeUnits.DAY)}${humanizePostfix(diff)}"
        else -> if (diff < 0) "более года назад" else "более чем через год"
    }
}

private fun humanizePrefix(value: Long): String = if (value > 0) "через " else ""

private fun humanizePostfix(value: Long): String = if (value < 0) " назад" else ""

private fun humanize(milliseconds: Long, timeUnits: TimeUnits): String {
    var time = milliseconds / timeUnits.milliseconds
    val (unitName1, unitName2, unitName3) = timeUnits.getUnitNames()
    var result = "$time"
    val unitRank = time % 10
    time /= 10
    val tenthRank = time % 10

    val unitName =
            if (tenthRank != 1L) {
                when (unitRank) {
                    1L -> unitName1
                    in 2..4 -> unitName2
                    else -> unitName3
                }
            } else {
                unitName3
            }
    result += " $unitName"
    return result
}

enum class TimeUnits(val milliseconds: Long) {
    SECOND(1000L) {
        override fun getUnitNames() = Triple("секунду", "секунды", "секунд")
    },
    MINUTE(60 * SECOND.milliseconds) {
        override fun getUnitNames() = Triple("минуту", "минуты", "минут")
    },
    HOUR(60 * MINUTE.milliseconds) {
        override fun getUnitNames() = Triple("час", "часа", "часов")
    },
    DAY(24 * HOUR.milliseconds) {
        override fun getUnitNames() = Triple("день", "дня", "дней")
    };

    abstract fun getUnitNames() : Triple<String, String, String>

    fun plural(value: Int): String {
        return humanize(value * milliseconds, this)
    }
}