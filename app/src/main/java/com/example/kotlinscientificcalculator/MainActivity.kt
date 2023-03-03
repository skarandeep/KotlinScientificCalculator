package com.example.kotlinscientificcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinscientificcalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

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
            binding.inputView.append("-")
            val str = binding.inputView.toString()
            if (!str.get(index = str.length - 1).equals("-")) {
                binding.inputView.text = (binding.inputView.toString() + "-")
            }
        }

        binding.buttonMultiply.setOnClickListener {
            binding.inputView.append("*")
            val str = binding.inputView.toString()
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
            binding.inputView.text = "Fact" + str
            if (str.isEmpty()) {
                Toast.makeText(this, "Please enter a valid number first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var number: Int = str.toInt()
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
            binding.inputView.text = "square" + str
            if (str.isEmpty()) {
                Toast.makeText(this, "Please enter a valid number first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var square: Double = str.toDouble() * str.toDouble()
                binding.outputView.text = square.toString()
            }
        }


        binding.buttonSqrt.setOnClickListener {
            var str = binding.inputView.toString()
            if (str.isEmpty()) {
                Toast.makeText(this, "Please enter a valid number first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.inputView.text = "Sqrt" + str
                var sqroot = Math.sqrt(str.toDouble())
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
                    str = str.dropLast(1)
                    binding.inputView.text = str
                }
            }


            binding.buttonEquals.setOnClickListener {

                var expression = ExpressionBuilder(binding.inputView.text.toString()).build()
                var result = expression.evaluate()
                var longResult = result.toLong()

                if (result == longResult.toDouble()) {
                    binding.outputView.text = longResult.toString()
                } else {
                    binding.outputView.text = result.toString()
                }
            }
        }
    }

    fun factorial(num: Int): Int {
        return if (num == 1 || num == 0) 1 else num * factorial(num - 1)
        //using regression function
    }
}


//TODO - animations to button presses