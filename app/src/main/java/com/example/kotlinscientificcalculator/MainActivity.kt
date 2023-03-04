package com.example.kotlinscientificcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinscientificcalculator.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.buttonAllclear.setOnClickListener {
            binding.inputView.text = ""
            binding.outputView.text = ""
        }

        binding.buttonZero.setOnClickListener {
            binding.inputView.append("0")
        }
        binding.buttonOne.setOnClickListener {
            binding.inputView.append("1")
        }
        binding.buttonTwo.setOnClickListener {
            binding.inputView.append("2")
        }
        binding.buttonThree.setOnClickListener {
            binding.inputView.append("3")
        }
        binding.buttonFour.setOnClickListener {
            binding.inputView.append("4")
        }
        binding.buttonFive.setOnClickListener {
            binding.inputView.append("5")
        }
        binding.buttonSix.setOnClickListener {
            binding.inputView.append("6")
        }
        binding.buttonSeven.setOnClickListener {
            binding.inputView.append("7")
        }
        binding.buttonEight.setOnClickListener {
            binding.inputView.append("8")
        }
        binding.buttonNine.setOnClickListener {
            binding.inputView.append("9")
        }

        binding.buttonPlus.setOnClickListener {
            binding.inputView.append("+")
        }

        binding.buttonMinus.setOnClickListener {
//            binding.inputView.append("-")
            val str = binding.inputView.toString()
            //checking if user already entered '-' symbol, not allowing user to add '-' symbol again
            if (!str.get(index = str.length - 1).equals("-")) {
                binding.inputView.text = (binding.inputView.text.toString() + "-")
            }
        }

        binding.buttonMultiply.setOnClickListener {
            binding.inputView.append("*")
            val str = binding.inputView.toString()
            //checking if user already entered '*' symbol
            if (!str.get(index = str.length - 1).equals("*")) {
                binding.inputView.text = (binding.inputView.toString() + "*")
            }
        }

        binding.buttonDiv.setOnClickListener {
            binding.inputView.append("/")
        }
        binding.buttonDivbyX.setOnClickListener {
            binding.inputView.append("div by")
        }

        binding.buttonFactorial.setOnClickListener {
            var str: String = binding.inputView.text.toString()
            if (str.isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var number: Int = str.toInt()
                binding.inputView.text = "Fact" + str
                binding.outputView.text = factorial(number).toString()
            }
        }

        binding.buttonDot.setOnClickListener {
            binding.inputView.append(".")
        }
        binding.buttonBraceOpen.setOnClickListener {
            binding.inputView.append("(")
        }
        binding.buttonBraceClose.setOnClickListener {
            binding.inputView.append(")")
        }

        binding.buttonSquare.setOnClickListener {
            var str: String = binding.inputView.text.toString()
            if (str.isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var double: Double = str.toDouble()
                var square: Double = double * double
                binding.inputView.text = "square" + str
                binding.outputView.text = square.toString()
            }
        }


        binding.buttonSqrt.setOnClickListener {
            //TODO - Does not work
            var str = binding.inputView.text.toString()
            //Check if inputView is empty, display toast
            if (str.isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.inputView.text = "√" + str
                //calculating square root using Kotlin fun
                var sqroot = sqrt(str.toDouble())
                binding.outputView.text = sqroot.toString()
            }


            binding.buttonSin.setOnClickListener {
                binding.inputView.append("Sin")
            }
            binding.buttonCos.setOnClickListener {
                binding.inputView.append("Cos")
            }
            binding.buttonTan.setOnClickListener {
                binding.inputView.append("tan")
            }
            binding.buttonLn.setOnClickListener {
                binding.inputView.append("ln")
            }
            binding.buttonLog.setOnClickListener {
                binding.inputView.append("Log")
            }
            binding.buttonPie.setOnClickListener {
                binding.inputView.append("3.142")
            }
            binding.buttonC.setOnClickListener {
                var str: String = binding.inputView.text.toString()
                if (str.isEmpty()) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                } else {
                    //dropping the last entered digit
                    str = str.dropLast(1)
                    binding.inputView.text = str
                }
            }


            binding.buttonEquals.setOnClickListener {
                var str: String = binding.inputView.text.toString()
          //      var result: Double = calculate(str)
          //      binding.outputView.text = result.toString()
                binding.inputView.text = ""
            }
        }
    }

    fun calculate(str: String) {
        //   var result =
        // return object : Any() {
        var position = -1
        var char = 0

        fun nextChar() {
            //traversing char by char
            char = if (++position < str.length) str[position].toInt() else -1
        }

        fun eat(charToEdit: Int): Boolean {
            //            while (char == ''.toInt().nextChar())
            //       }
            //checking extra spacing
            return true
        }
    }

    private fun factorial(num: Int): Long {
        //using recursion function
        return if (num >= 1) {
            return num * factorial(num - 1)
        } else {
                return 1
            }
        }
    }



//TODO - animations to button presses