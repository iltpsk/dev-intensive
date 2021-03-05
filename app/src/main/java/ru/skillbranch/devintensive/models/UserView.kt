package ru.skillbranch.devintensive.models

import java.util.*

class UserView(val id: String,
               val fullName: String?,
               val nickName: String?,
               var avatar: String?,
               var status: String? = "offline",
               var initials: String?) {
}