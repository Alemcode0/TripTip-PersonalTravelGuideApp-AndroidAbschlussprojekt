package com.example.abschlissprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlissprojekt.FirebaseViewModel
import com.example.abschlissprojekt.R
import com.example.abschlissprojekt.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: FirebaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btToRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.btLogin.setOnClickListener {
            val email: String = binding.tietEmail.text.toString()
            val pass: String = binding.tietPass.text.toString()

            if (email != "" && pass != "") {
                viewModel.login(email, pass)
            }
        }

        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }
}