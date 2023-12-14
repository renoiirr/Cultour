package com.capstone.cultour.data.pref

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class UserModel(
    val email: String?,
    val password: String?
): Parcelable