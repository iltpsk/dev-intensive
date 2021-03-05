package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    private const val DELIMITER: String = " "

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = if (fullName?.isNotBlank() == true) fullName.trim().split(DELIMITER) else null
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var result: String? = null
        if (firstName?.isNotBlank() == true) {
            result = "" + firstName.trim().toUpperCase(Locale.ROOT)[0]
        }

        if (lastName?.isNotBlank() == true) {
            result = (result ?: "") + lastName.trim().toUpperCase(Locale.ROOT)[0]
        }

        return result
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val result = StringBuilder()
        for (c in payload) {
            if (c == ' ') {
                result.append(divider)
            } else {
                result.append(dictionary[c] ?: c)
            }
        }
        return result.toString()
    }

    private val dictionary: Map<Char, String> = hashMapOf(
            'А' to "A",
            'Б' to "B",
            'В' to "V",
            'Г' to "G",
            'Д' to "D",
            'Е' to "E",
            'Ё' to "E",
            'Ж' to "Zh",
            'З' to "Z",
            'И' to "I",
            'Й' to "I",
            'К' to "K",
            'Л' to "L",
            'М' to "M",
            'Н' to "N",
            'О' to "O",
            'П' to "P",
            'Р' to "R",
            'С' to "S",
            'Т' to "T",
            'У' to "U",
            'Ф' to "F",
            'Х' to "H",
            'Ц' to "C",
            'Ч' to "Ch",
            'Ш' to "Sh",
            'Щ' to "Sh'",
            'Ъ' to "",
            'Ы' to "I",
            'Ь' to "",
            'Э' to "E",
            'Ю' to "Yu",
            'Я' to "Ya",

            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
    )
}