package com.example.calculator.ui.model

object Repository {

    fun symbols() = listOf<Symbol>(
        Symbol.Clear, Symbol.Del, Symbol.Percent, Symbol.Div,
        Symbol.Seven, Symbol.Eight, Symbol.Nine, Symbol.Multiply,
        Symbol.Four, Symbol.Five, Symbol.Six, Symbol.Minus,
        Symbol.One, Symbol.Two, Symbol.Three, Symbol.Plus,
        Symbol.Zero, Symbol.Point, Symbol.Ans, Symbol.Equal
    )
}