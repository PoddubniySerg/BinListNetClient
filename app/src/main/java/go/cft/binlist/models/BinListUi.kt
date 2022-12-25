package go.cft.binlist.models

import android.os.Parcel
import android.os.Parcelable
import go.cft.domain.models.entity.BinList

data class BinListUi(
    override val number: NumberBinListUi?,
    override val scheme: String?,
    override val type: String?,
    override val brand: String?,
    override val prepaid: Boolean?,
    override val country: CountryBinListUi?,
    override val bank: BankBinListUi?
) : BinList, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(NumberBinListUi::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readParcelable(CountryBinListUi::class.java.classLoader),
        parcel.readParcelable(BankBinListUi::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(number, flags)
        parcel.writeString(scheme)
        parcel.writeString(type)
        parcel.writeString(brand)
        parcel.writeValue(prepaid)
        parcel.writeParcelable(country, flags)
        parcel.writeParcelable(bank, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BinListUi> {
        override fun createFromParcel(parcel: Parcel): BinListUi {
            return BinListUi(parcel)
        }

        override fun newArray(size: Int): Array<BinListUi?> {
            return arrayOfNulls(size)
        }
    }
}
