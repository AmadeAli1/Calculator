package com.example.calculator.ui.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.ui.model.CalculoState
import com.example.calculator.ui.model.Symbol
import net.objecthunter.exp4j.ExpressionBuilder

class CalculadoraViewModel : ViewModel() {
    val expression = MutableLiveData("")
    val expressionResult = MutableLiveData("")
    private val expressionState = mutableListOf<CalculoState>()

    fun insertExpression(symbol: Symbol) {
        when (symbol) {
            Symbol.Clear -> {
                if (expression.value!!.isNotEmpty()) {
                    expression.value = ""
                    expressionResult.value = ""
                    expressionState.clear()
                }
            }
            Symbol.Del -> {
                if (expression.value!!.isNotEmpty().and(expressionState.isNotEmpty())) {
                    val last = expressionState.removeLast()
                    expression.value = last.expression
                    expressionResult.value = last.result.toString()
                }
                else{
                    expression.value=""
                    expressionResult.value=""
                }
            }
            Symbol.Ans -> {

            }
            Symbol.Equal -> {

            }
            else -> {
                expression.value = expression.value!!.plus(symbol.value)
                val exp = expression.value!!
                calculate(expression = exp)
            }
        }

    }

    private fun calculate(expression: String) {
        try {
            val validate = ExpressionBuilder(expression).build()
            if (validate.validate().isValid) {
                val result = validate.evaluate()
                expressionResult.value = result.toString()
                expressionState.add(CalculoState(result = result, expression = expression))
            }
            else{
                Log.d("CLSL", "Invalido: $expression")
            }
        } catch (e: Exception) {
            e.stackTrace
            Log.d("CLS", "insertExpression: ${e.message}")
        }
    }

}