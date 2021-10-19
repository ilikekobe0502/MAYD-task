package com.mayd.homework.model.api.model.response


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "history")
data class Shorten(
    @PrimaryKey
    @SerializedName("code")
    var code: String,
    @ColumnInfo
    @SerializedName("short_link")
    var shortLink: String,
    @ColumnInfo
    @SerializedName("full_short_link")
    var fullShortLink: String,
    @ColumnInfo
    @SerializedName("short_link2")
    var shortLink2: String,
    @ColumnInfo
    @SerializedName("full_short_link2")
    var fullShortLink2: String,
    @ColumnInfo
    @SerializedName("short_link3")
    var shortLink3: String,
    @ColumnInfo
    @SerializedName("full_short_link3")
    var fullShortLink3: String,
    @ColumnInfo
    @SerializedName("share_link")
    var shareLink: String,
    @ColumnInfo
    @SerializedName("full_share_link")
    var fullShareLink: String,
    @ColumnInfo
    @SerializedName("original_link")
    var originalLink: String,
): Parcelable