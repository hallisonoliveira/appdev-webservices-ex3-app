package br.com.hallisonoliveira.webservice_ex3.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.hallisonoliveira.webservice_ex3.R
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping

class ShoppingAdapter (
    private val onItemClick: (Shopping) -> Unit,
    private val onDeleteItemClick: (String) -> Unit
) : ListAdapter<Shopping, ShoppingViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            ), onItemClick, onDeleteItemClick
        )
    }
    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Shopping>() {
            override fun areItemsTheSame(oldItem: Shopping, newItem: Shopping): Boolean {
                if (oldItem.name != newItem.name) {
                    return false
                }

                if (oldItem.brand != newItem.brand) {
                    return false
                }

                if (oldItem.amount != newItem.amount) {
                    return false
                }

                if (oldItem.shelfLife != newItem.shelfLife) {
                    return false
                }

                return true
            }

            override fun areContentsTheSame(oldItem: Shopping, newItem: Shopping): Boolean = oldItem::class == newItem::class

        }
    }
}
