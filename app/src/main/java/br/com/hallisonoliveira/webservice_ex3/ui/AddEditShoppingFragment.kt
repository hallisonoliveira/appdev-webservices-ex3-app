package br.com.hallisonoliveira.webservice_ex3.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.hallisonoliveira.webservice_ex3.R
import br.com.hallisonoliveira.webservice_ex3.model.domain.Shopping
import br.com.hallisonoliveira.webservice_ex3.util.DateUtils
import kotlinx.android.synthetic.main.fragment_add_edit_shopping.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset

class AddEditShoppingFragment : Fragment() {

    private val viewModel : AddEditShoppingViewModel by viewModel()

    private val args: AddEditShoppingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_edit_shopping, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.shoppingId?.let { viewModel.getShopping(it) }

        calendarButton.setOnClickListener {
            val localDate = LocalDate.now()
            DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    shelflifeEditText.setText(
                        DateUtils().formatDateToShow(LocalDate.of(year, month + 1, dayOfMonth))
                    )

                }, localDate.year, localDate.monthValue - 1, localDate.dayOfMonth
            ).apply {
                datePicker.minDate = LocalDate.now().atTime(12,0)
                    .toInstant(ZoneOffset.UTC).toEpochMilli()
            }.show()
        }

        setObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save_shopping, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> saveShopping()
        }

        return super.onOptionsItemSelected(item)
    }


    private fun setObservers() {
        viewModel.shoppingLiveData.observe(viewLifecycleOwner, Observer { shopping ->
            handleShopping(shopping)
        })

        viewModel.saveShoppingStatusLiveData.observe(viewLifecycleOwner, Observer { status ->
            handleSaveStatus(status)
        })
    }

    private fun handleShopping(shopping: Shopping) {
        with(shopping) {
            nameEditText.setText(name)
            brandEditText.setText(brand)
            amountEditText.setText(amount.toString())
            shelflifeEditText.setText(shelfLife)
        }
    }

    private fun saveShopping() {
        viewModel.saveShopping(
            Shopping(
                id = args.shoppingId ?: "",
                name = nameEditText.text.toString().trim(),
                brand = brandEditText.text.toString().trim(),
                amount = amountEditText.text.toString().trim().toInt(),
                shelfLife = shelflifeEditText.text.toString()
            )
        )
    }

    private fun handleSaveStatus(status: SaveRequestStatus) {
        when(status) {
            is SaveRequestStatus.Success -> {
                Toast.makeText(requireContext(), "Shopping salvo com sucesso!", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
            is SaveRequestStatus.Error -> {
                Toast.makeText(requireContext(), "Erro ao salvar. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}