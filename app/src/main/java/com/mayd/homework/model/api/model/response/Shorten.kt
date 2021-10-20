package com.mayd.homework.model.api.model.response


import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "history")
data class Shorten(
    @PrimaryKey
    @SerializedName("code")
    var code: String,
    @SerializedName("short_link")
    var shortLink: String,
    @SerializedName("full_short_link")
    var fullShortLink: String,
    @SerializedName("short_link2")
    var shortLink2: String,
    @SerializedName("full_short_link2")
    var fullShortLink2: String,
    @SerializedName("short_link3")
    var shortLink3: String,
    @SerializedName("full_short_link3")
    var fullShortLink3: String,
    @SerializedName("share_link")
    var shareLink: String,
    @SerializedName("full_share_link")
    var fullShareLink: String,
    @SerializedName("original_link")
    var originalLink: String,
): Parcelable