package go.cft.domain.models.entity

interface BinList {
    val number: NumberBinList?
    val scheme: String?
    val type: String?
    val brand: String?
    val prepaid: Boolean?
    val country: CountryBinList?
    val bank: BankBinList?
}