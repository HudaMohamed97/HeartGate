package dev.cat.mahmoudelbaz.heartgate.myAccount.oldChat

import java.io.Serializable

data class UserDataModel(var name: String?,
                         var email: String?, var phoneNum: String?, var birthDate: String?,
                         var gender: String?) : Serializable
