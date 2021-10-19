package com.mayd.homework.model.api.model.response

import com.google.gson.annotations.SerializedName

data class ApiBaseItem<T>(
    @SerializedName("ok")
    val ok: Boolean,
    @SerializedName("result")
    val result: T?
)