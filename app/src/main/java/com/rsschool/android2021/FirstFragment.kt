package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        communicator = activity as Communicator

        generateButton?.setOnClickListener {
            var min = -3
            var max = -2
            try {
                min = view.findViewById<EditText>(R.id.min_value).text.toString().toInt()
                max = view.findViewById<EditText>(R.id.max_value).text.toString().toInt()
            }
            catch (e: Exception) {
                Toast.makeText(this.context, "Введите числа Integer", LENGTH_SHORT).show()
            }
            if (min in 0..max && max >= 0) {
                communicator.openSecondFragment(min, max)
            } else if (min > max){
                Toast.makeText(this.context, "MIN > MAX", LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, "Ввод не корректен", LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}