package go.cft.binlist.models

import android.os.Parcel
import android.os.Parcelable
import go.cft.domain.models.entity.CountryBinList

data class CountryBinListUi(
    override val numeric: String?,
    override val alpha2: String?,
    override val name: String?,
    override val emoji: String?,
    override val currency: String?,
    override val latitude: Int?,
    override val longitude: Int?
) : CountryBinList, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numeric)
        parcel.writeString(alpha2)
        parcel.writeString(name)
        parcel.writeString(emoji)
        parcel.writeString(currency)
        parcel.writeValue(latitude)
        parcel.writeValue(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryBinListUi> {
        override fun createFromParcel(parcel: Parcel): CountryBinListUi {
            return CountryBinListUi(parcel)
        }

        override fun newArray(size: Int): Array<CountryBinListUi?> {
            return arrayOfNulls(size)
        }
    }
}
