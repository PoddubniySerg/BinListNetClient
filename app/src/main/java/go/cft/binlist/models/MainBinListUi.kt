package go.cft.binlist.models

import android.os.Parcel
import android.os.Parcelable
import go.cft.domain.models.entity.IdBinList

data class MainBinListUi(
    override val id: String?,
    override val binList: BinListUi?
): IdBinList, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(BinListUi::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(binList, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainBinListUi> {
        override fun createFromParcel(parcel: Parcel): MainBinListUi {
            return MainBinListUi(parcel)
        }

        override fun newArray(size: Int): Array<MainBinListUi?> {
            return arrayOfNulls(size)
        }
    }
}
