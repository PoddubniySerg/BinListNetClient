package go.cft.binlist.models

import android.os.Parcel
import android.os.Parcelable
import go.cft.domain.models.entity.BankBinList

data class BankBinListUi(
    override val name: String?,
    override val url: String?,
    override val phone: String?,
    override val city: String?
) : BankBinList, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(phone)
        parcel.writeString(city)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BankBinListUi> {
        override fun createFromParcel(parcel: Parcel): BankBinListUi {
            return BankBinListUi(parcel)
        }

        override fun newArray(size: Int): Array<BankBinListUi?> {
            return arrayOfNulls(size)
        }
    }
}
