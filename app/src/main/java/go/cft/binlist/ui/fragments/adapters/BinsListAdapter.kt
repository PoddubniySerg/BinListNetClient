package go.cft.binlist.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import go.cft.binlist.databinding.ItemBinListBinding
import go.cft.domain.models.entity.ListItemBinList

class BinsListAdapter(
    val onClick: (ListItemBinList) -> Unit,
    val removeItem: (ListItemBinList) -> Unit
) : ListAdapter<ListItemBinList, BinListViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinListViewHolder {
        return BinListViewHolder(
            ItemBinListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BinListViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            binTextView.text = item.bin

            countryTextView.text = item.country

            bankTextView.text = item.bank

            deleteButton.setOnClickListener {
                removeItem(item)
            }

            root.setOnClickListener {
                item?.let {
                    onClick(item)
                }
            }
        }
    }
}

class BinListViewHolder(val binding: ItemBinListBinding) : ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<ListItemBinList>() {
    override fun areItemsTheSame(oldItem: ListItemBinList, newItem: ListItemBinList): Boolean {
        return oldItem.bin == newItem.bin
    }

    override fun areContentsTheSame(oldItem: ListItemBinList, newItem: ListItemBinList): Boolean {
        return oldItem == newItem
    }
}