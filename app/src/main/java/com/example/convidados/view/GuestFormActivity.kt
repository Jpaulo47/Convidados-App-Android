package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        setListeners()
        observe()
        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this) {
            if (!it.equals(getString(R.string.error))) {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {

            if (binding.editName.text.isEmpty()) {
                binding.editName.error = getString(R.string.error_name)
                return
            }

            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel().apply {
                id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(model)
            finish()
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

}