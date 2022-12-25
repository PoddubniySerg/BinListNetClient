package go.cft.binlist.models

import android.os.Parcel
import android.os.Parcelable
import go.cft.domain.models.entity.NumberBinList

data class NumberBinListUi(
    override val length: Int?,
    override val luhn: Boolean?
) : NumberBinList, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(length)
        parcel.writeValue(luhn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NumberBinListUi> {
        override fun createFromParcel(parcel: Parcel): NumberBinListUi {
            return NumberBinListUi(parcel)
        }

        override fun newArray(size: Int): Array<NumberBinListUi?> {
            return arrayOfNulls(size)
        }
    }
}
