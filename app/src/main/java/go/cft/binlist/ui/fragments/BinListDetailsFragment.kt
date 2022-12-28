package go.cft.binlist.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.R
import dagger.hilt.android.AndroidEntryPoint
import go.cft.binlist.databinding.FragmentBinListDetailsBinding
import go.cft.binlist.ui.activities.MainActivity
import go.cft.binlist.ui.viewmodels.BinListDetailsViewModel
import go.cft.domain.models.entity.BankBinList
import go.cft.domain.models.entity.CountryBinList
import go.cft.domain.models.entity.IdBinList
import go.cft.domain.models.entity.NumberBinList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class BinListDetailsFragment @Inject constructor() : Fragment() {

    companion object {
        const val ID_BIN_LIST_ARG_KEY = "id_bin_list"
    }

    private val viewModel by viewModels<BinListDetailsViewModel>()
    private var binding: FragmentBinListDetailsBinding? = null

    private var idBinList: IdBinList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { idBinList = it.getParcelable(ID_BIN_LIST_ARG_KEY) }
        binding = FragmentBinListDetailsBinding.inflate(inflater, container, false)

        val actionBar = (activity as MainActivity?)!!.supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { it ->
            with(it) {
                binTextView.text = idBinList?.id ?: ""
                schemeNetworkTextView.text = idBinList?.binList?.scheme ?: ""
                brandTextView.text = idBinList?.binList?.brand ?: ""
                fillNumber(idBinList?.binList?.number)
                when (idBinList?.binList?.type?.lowercase()) {
                    "debit" -> selectText(typeDebitTextView, typeCreditTextView)
                    "credit" -> selectText(typeCreditTextView, typeDebitTextView)
                    else -> {
                        clearSelection(typeDebitTextView, typeCreditTextView)
                    }
                }
                when (idBinList?.binList?.prepaid) {
                    true -> selectText(prepaidYesTextView, prepaidNoTextView)
                    false -> selectText(prepaidNoTextView, prepaidYesTextView)
                    else -> {
                        clearSelection(prepaidYesTextView, prepaidNoTextView)
                    }
                }
                fillCountry(idBinList?.binList?.country)
                fillBankContacts(idBinList?.binList?.bank)

                viewModel.uriStringUrlFlow.onEach { uriStringUrl ->
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uriStringUrl)
                    )
                    startActivity(browserIntent)
                }.launchIn(viewLifecycleOwner.lifecycleScope)

                viewModel.uriStringPhoneFlow.onEach { uriStringPhone ->
                    val phoneIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse(uriStringPhone)
                    )
                    startActivity(phoneIntent)
                }.launchIn(viewLifecycleOwner.lifecycleScope)

                it.bankUrlTextView.setOnClickListener { _ ->
                    viewModel.getUriStringUrl(it.bankUrlTextView.text.toString())
                }

                it.bankPhoneTextView.setOnClickListener { _ ->
                    viewModel.getUriStringPhone(it.bankPhoneTextView.text.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun fillNumber(numberBinList: NumberBinList?) {
        binding?.let {
            with(it) {
                if (numberBinList == null) {
                    cardNumberLengthTextView.text = ""
                    clearSelection(cardNumberLuhnYesTextView, cardNumberLuhnNoTextView)
                } else {
                    cardNumberLengthTextView.text = idBinList?.binList?.number?.length.toString()
                    when (idBinList?.binList?.number?.luhn) {
                        true -> selectText(cardNumberLuhnYesTextView, cardNumberLuhnNoTextView)
                        false -> selectText(cardNumberLuhnNoTextView, cardNumberLuhnYesTextView)
                        else -> {
                            clearSelection(cardNumberLuhnYesTextView, cardNumberLuhnNoTextView)
                        }
                    }
                }
            }
        }
    }

    private fun fillCountry(countryBinList: CountryBinList?) {
        binding?.let {
            with(it) {
                if (countryBinList == null) {
                    countryEmojiAndNameTextView.text = ""
                    countryLatitudeTextView.text = ""
                    countryLongitudeTextView.text = ""
                } else {
                    countryEmojiAndNameTextView.text = buildString {
                        append(countryBinList.emoji ?: "")
                        append(" ")
                        append(countryBinList.name ?: "")
                    }
                    countryLatitudeTextView.text = countryBinList.latitude.toString()
                    countryLongitudeTextView.text =
                        countryBinList.longitude.toString()
                }
            }
        }
    }

    private fun fillBankContacts(bankBinList: BankBinList?) {
        binding?.let {
            with(it) {
                if (bankBinList == null) {
                    bankNameAndCityTextView.text = ""
                } else {
                    bankNameAndCityTextView.text = buildString {
                        append(bankBinList.name ?: "")
                        append(", ")
                        append(bankBinList.city ?: "")
                    }
                }
                bankUrlTextView.text = bankBinList?.url ?: ""
                bankPhoneTextView.text = bankBinList?.phone ?: ""
            }
        }
    }

    private fun selectText(textViewSelected: TextView, simpleTextView: TextView) {
        textViewSelected
            .setTextAppearance(R.style.TextAppearance_AppCompat_SearchResult_Title)
        simpleTextView
            .setTextAppearance(R.style.TextAppearance_AppCompat)
    }

    private fun clearSelection(textView1: TextView, textView2: TextView) {
        textView1.setTextAppearance(R.style.TextAppearance_AppCompat)
        textView2.setTextAppearance(R.style.TextAppearance_AppCompat)
    }
}