package com.example.kotlinscientificcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinscientificcalculator.databinding.ActivityMainBinding
import kotlin.math.*

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
            val str = binding.inputView.toString()
            //checking if user already entered '-' symbol, not allowing user to add '-' symbol again
            if (!str.get(index = str.length - 1).equals("-")) {
                binding.inputView.append("-")
            }
        }

        binding.buttonMultiply.setOnClickListener {
            val str = binding.inputView.toString()
            //checking if user already entered '*' symbol
            if (!str.get(index = str.length - 1).equals("*")) {
                binding.inputView.append("*")
//                binding.inputView.text = (binding.inputView.toString() + "*")
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
                binding.inputView.text = "sqrt" + str
                //calculating square root using Kotlin fun
                var sqroot = sqrt(str.toDouble())
                binding.outputView.text = sqroot.toString()
            }


            binding.buttonSin.setOnClickListener {
                binding.inputView.append("sin")
            }
            binding.buttonCos.setOnClickListener {
                binding.inputView.append("cos")
            }
            binding.buttonTan.setOnClickListener {
                binding.inputView.append("tan")
            }
            binding.buttonLn.setOnClickListener {
                binding.inputView.append("ln")
            }
            binding.buttonLog.setOnClickListener {
                binding.inputView.append("log")
            }
            binding.buttonPie.setOnClickListener {
                binding.inputView.append("3.14159265")
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
                var result: Double = calculation(str)
                binding.outputView.text = result.toString()
                binding.inputView.text = ""
            }
        }
    }

    private fun calculation(str: String): Double {

        return object : Any() {
            var position = -1
            var char = 0

            fun nextChar() {
                //traversing char by char from input string
                char = if (++position < str.length) {
                    //using .code in place of .toInt()
                    str[position].digitToInt()
                } else -1
            }

            fun eat(charToEdit: Int): Boolean {
                //checking extra spacing
                while (char == Character.MIN_VALUE.digitToInt()) nextChar()

                if (char == charToEdit) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                //get the next operator
                var x = parseExpression()
                if (position < str.length) throw java.lang.RuntimeException("Unexpected: " + char.toChar())
                return x
            }

            fun parseExpression(): Double {
                //check for addition or subtraction
                var x = parseTerm()
                while (true) {
                    if (eat('+'.digitToInt())) x += parseTerm()
                    else if (eat('-'.toInt())) x -= parseTerm()
                    else return x
                }
            }

            fun parseTerm(): Double {
                //check for parse factor
                var x = parseFactor()
                while (true) {
                    if (eat('*'.digitToInt())) x *= parseFactor()
                    else if (eat('/'.digitToInt())) x /= parseFactor()
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.digitToInt())) return parseFactor()
                if (eat('-'.digitToInt())) return -parseFactor()
                var x: Double
                var startPosition = position
                if (eat('('.digitToInt())) {
                    x = parseExpression()
                    eat(')'.digitToInt())
                } else if (char >= '0'.digitToInt() && char <= '9'.digitToInt() || char == '.'.digitToInt()) {
                    while (char >= '0'.digitToInt() && char <= '9'.digitToInt() || char == '.'.digitToInt()) nextChar()
                    x = str.substring(startPosition, position).toDouble()

                } else if (char >= 'a'.digitToInt() && char <= 'z'.digitToInt()) {
                    while(char >='a'.digitToInt() && char<='z'.digitToInt()) nextChar()
                    var func = str.substring(startPosition, position)
                    x = parseFactor()

                    x = when (func) {
                        "sqrt" -> sqrt(x)
                        "sin" -> sin(Math.toRadians(x))
                        "cos" -> cos(Math.toRadians(x))
                        "tan" -> tan(Math.toRadians(x))
                        "log" -> log10(x)
                        "ln" -> ln(x)
                        else ->
                            throw  RuntimeException(
                            "Unknown Exception : $func"
                        )
                    }
                }
                else {
                    throw RuntimeException("Unexpected : " + char.toChar())
                }
                if(eat('⌃'.toInt())) x = Math.pow(x,parseFactor())
                return x
            }
        }.parse()
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