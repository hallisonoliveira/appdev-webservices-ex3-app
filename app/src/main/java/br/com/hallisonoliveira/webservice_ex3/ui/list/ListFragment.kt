package br.com.hallisonoliveira.webservice_ex3.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.hallisonoliveira.webservice_ex3.R
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModel()
    private lateinit var adapter: ShoppingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShoppingAdapter(
            ::onItemClick,
            ::onDeleteClick
        )
        productsList.adapter = adapter

        addButton.setOnClickListener {
            findNavController().navigate(
                ListFragmentDirections
                    .actionFragmentListToAddEditShoppingFragment(null)
            )
        }

        viewModel.list()
        setObservers()
    }

    private fun setObservers() {
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })
    }

    private fun onItemClick(shoppingId: String) {
        findNavController().navigate(
            ListFragmentDirections.actionFragmentListToAddEditShoppingFragment(shoppingId)
        )
    }

    private fun onDeleteClick(id: String) {
        viewModel.remove(id)
    }

}