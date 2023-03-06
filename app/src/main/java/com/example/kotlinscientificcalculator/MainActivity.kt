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
            val inputString = binding.inputView.text.toString()

            // Split the input string into individual characters
            val inputChars = inputString.toCharArray()

            // Initialize variables to keep track of the current number and operation
            var currentNumber = ""
            var currentOperation = '+'

            // Initialize a list to store the intermediate results
            val intermediateResults = mutableListOf<Double>()

            // Loop through each character in the input string
            for (char in inputChars) {
                // If the character is a digit, add it to the current number
                if (char.isDigit() || char == '.') {
                    currentNumber += char
                } else {
                    // If the character is not a digit, parse the current number and
                    // apply the current operation to the intermediate results list
                    val number = currentNumber.toDoubleOrNull() ?: return@setOnClickListener
                    when (currentOperation) {
                        '+' -> intermediateResults.add(number)
                        '-' -> intermediateResults.add(-number)
                        '*' -> {
                            val lastIndex = intermediateResults.lastIndex
                            intermediateResults[lastIndex] = intermediateResults[lastIndex] * number
                        }
                        '/' -> {
                            val lastIndex = intermediateResults.lastIndex
                            intermediateResults[lastIndex] = intermediateResults[lastIndex] / number
                        }
                    }

                    // Set the current operation to the new character and reset the current number
                    currentOperation = char
                    currentNumber = ""
                }
            }

            // Parse the final number and apply the final operation to the intermediate results list
            val finalNumber = currentNumber.toDoubleOrNull() ?: return@setOnClickListener
            when (currentOperation) {
                '+' -> intermediateResults.add(finalNumber)
                '-' -> intermediateResults.add(-finalNumber)
                '*' -> {
                    val lastIndex = intermediateResults.lastIndex
                    intermediateResults[lastIndex] = intermediateResults[lastIndex] * finalNumber
                }
                '/' -> {
                    val lastIndex = intermediateResults.lastIndex
                    intermediateResults[lastIndex] = intermediateResults[lastIndex] / finalNumber
                }
            }

            // Compute the final result by summing up the intermediate results
            val finalResult = intermediateResults.sum()

            // Update the input view to display the final result
            binding.outputView.setText(finalResult.toString())
        }


//            binding.buttonEquals.setOnClickListener {
//                var str: String = binding.inputView.text.toString()
//                var result: Double = calculation(str)
//                binding.outputView.text = result.toString()
//                binding.inputView.text = ""
//            }
//        }
//    }

//    private fun calculation(str: String): Double {
//
//        return object : Any() {
//            //variables to track position in str and individual characters (toInt)
//            var position = -1
//            var char = 0
//
//            fun nextCharacter() {
//                //traversing char by char in the input string, incrementing position each time moving from left to right
//                // same sequence as entered by user
//                char = if (++position < str.length) {
//                    str[position].code
//                } else -1
//            }
//
//            fun spacesInExpression(charToEat: Int): Boolean {
//                //checking <SPACES> in the expression
//                while (char == "".toInt()) nextCharacter() // or while (char == ' '.toInt()) nextChar()
//
//                if (char == charToEat) { //checking character position. if both are equal we return true and go to next Character
//                    nextCharacter()
//                    return true
//                }
//                return false
//            }
//
//            fun parse(): Double {
//                //parsing the whole expression to get answer/result by calling parseExpression fun
//                nextCharacter()
//                //get the next operator
//                var parseTheExpression: Double = parseExpression()
//                if (position < str.length) throw RuntimeException("Unexpected: " + char.toChar())
//                return parseTheExpression
//            }
//
//            fun parseExpression(): Double {
//                //check and perform only addition or subtraction
//                var parser: Double = parseTerm()
//                while (true) {
//                    if (spacesInExpression('+'.digitToInt())) parser += parseTerm()
//                    else if (spacesInExpression('-'.digitToInt())) parser -= parseTerm()
//                    else return parser
//                }
//            }
//
//            fun parseTerm(): Double {
//                //check and perform only mult or div
//                var parser: Double = parseFactor()
//                while (true) {
//                    if (spacesInExpression('*'.digitToInt())) parser *= parseFactor()
//                    else if (spacesInExpression('/'.digitToInt())) parser /= parseFactor()
//                    else return parser
//                }
//            }
//
//            fun parseFactor(): Double {
//                //checking the factors and eprforming operations
//                if (spacesInExpression('+'.digitToInt())) return parseFactor()
//                if (spacesInExpression('-'.digitToInt())) return -parseFactor()
//
//                var answer: Double
//                var startPosition = position
//                //checking for opening and closing parenthesis
//                if (spacesInExpression('('.digitToInt())) {
//                    answer = parseExpression()
//                    spacesInExpression(')'.digitToInt())
//                } else if (char >= '0'.digitToInt() && char <= '9'.digitToInt() || char == '.'.digitToInt()) {
//                    while (char >= '0'.digitToInt() && char <= '9'.digitToInt() || char == '.'.digitToInt()) nextCharacter()
//                    //getting substring from entered expression string from start to current position
//                    answer = str.substring(startPosition, position).toDouble()
//
//                } else if (char >= 'a'.digitToInt() && char <= 'z'.digitToInt()) {
//                    //checking for operator in the entered expression
//                    while (char >= 'a'.digitToInt() && char <= 'z'.digitToInt()) nextCharacter()
//                    var func = str.substring(startPosition, position)
//                    answer = parseFactor()
//
//                    //checking for operators to perform operation accordingly
//                    answer = when (func) {
//                        "sqrt" -> sqrt(answer)
//                        "sin" -> sin(Math.toRadians(answer))
//                        "cos" -> cos(Math.toRadians(answer))
//                        "tan" -> tan(Math.toRadians(answer))
//                        "log" -> log10(answer)
//                        "ln" -> ln(answer)
//                        else ->
//                            throw RuntimeException(
//                                "Unknown Exception : $func"
//                            )
//                    }
//                } else {
//                    throw RuntimeException("Unexpected : " + char.toChar())
//                }
//                if(spacesInExpression('⌃'.digitToInt())) answer = answer.pow(parseFactor())
//                return answer
//            }
//            //in the end calling a parse for the expression
//        }.parse()
//    }



    }

    fun factorial(num: Int): Long {
        //using recursion function
        return if (num >= 1) {
            return num * factorial(num - 1)
        } else {
            return 1
        }
    }
}


//TODO - animations to button presses