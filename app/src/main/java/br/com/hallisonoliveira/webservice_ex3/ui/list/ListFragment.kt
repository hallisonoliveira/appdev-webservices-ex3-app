package br.com.hallisonoliveira.webservice_ex3.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
            ::onItemclick,
            ::onDeleteClick
        )


        productsList.adapter = adapter

        viewModel.list()
        setObservers()
    }

    private fun setObservers() {
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer { list ->
            adapter.submitList(list)
        })
    }

    private fun onItemclick(shopping: Shopping) {
        Log.d("TAG", "${shopping.name}")
    }

    private fun onDeleteClick(id: String) {
        viewModel.remove(id)
    }

}