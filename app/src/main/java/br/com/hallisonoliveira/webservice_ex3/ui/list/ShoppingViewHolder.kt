package br.com.hallisonoliveira.webservice_ex3.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import kotlinx.android.synthetic.main.item_product.view.*

class ShoppingViewHolder(
    itemView: View,
    private val onItemClick: (String) -> Unit,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(item: Shopping) {
        itemView.nameTextView.text = item.name
        itemView.amountTextView.text = item.amount.toString()
        itemView.brandTextView.text = item.brand
        itemView.shelflifeTextView.text = item.shelfLife

        itemView.deleteButton.setOnClickListener {
            it.run { onDeleteClick.invoke(item.id!!) }
        }

        itemView.setOnClickListener {
            it.run { onItemClick.invoke(item.id!!) }
        }
    }

}