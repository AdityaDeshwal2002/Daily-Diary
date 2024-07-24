package com.example.diary.ui.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.diary.Model.Notes
import com.example.diary.R
import com.example.diary.ViewModel.NotesViewModel
import com.example.diary.databinding.FragmentCreateNotesBinding
import java.lang.String.format
import java.util.*


class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    var priority: String ="1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done)

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
            createNotes(it)
        }



        return binding.root
    }

    private fun createNotes(it: View?) {

        val title= binding.edtTitle.text.toString()
        val subTitle= binding.edtSubTitle.text.toString()
        val notes= binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        val data=Notes(null,title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority

        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)


    }
}