package com.example.diary.ui.Fragments

import android.os.Binder
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.diary.Model.Notes
import com.example.diary.R
import com.example.diary.ViewModel.NotesViewModel
import com.example.diary.databinding.FragmentCreateNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentCreateNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subtitle)
        binding.edtNotes.setText(oldNotes.data.notes)
        when (oldNotes.data.priority) {
            "1" -> {
                priority ="1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done)
                binding.pRed.setImageResource(R.drawable.red_dot)
                binding.pYellow.setImageResource(R.drawable.yellow_dot)
            }
            "2" -> {
                priority ="2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done)
                binding.pRed.setImageResource(R.drawable.red_dot)
                binding.pGreen.setImageResource(R.drawable.green_dot)
            }
            "3" -> {
                priority ="3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done)
                binding.pGreen.setImageResource(R.drawable.green_dot)
                binding.pYellow.setImageResource(R.drawable.yellow_dot)
            }
        }
        binding.pGreen.setOnClickListener {
            priority="1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done)
            binding.pRed.setImageResource(R.drawable.red_dot)
            binding.pYellow.setImageResource(R.drawable.yellow_dot)

        }
        binding.pYellow.setOnClickListener {
            priority="2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done)
            binding.pRed.setImageResource(R.drawable.red_dot)
            binding.pGreen.setImageResource(R.drawable.green_dot)

        }
        binding.pRed.setOnClickListener {
            priority="3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done)
            binding.pGreen.setImageResource(R.drawable.green_dot)
            binding.pYellow.setImageResource(R.drawable.yellow_dot)

        }
        binding.btnSaveNotes.setOnClickListener {
            upadteNotes(it)
        }


        return binding.root
    }

    private fun upadteNotes(it: View?) {
        val title= binding.edtTitle.text.toString()
        val subTitle= binding.edtSubTitle.text.toString()
        val notes= binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        val data= Notes(oldNotes.data.id,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority

        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

}