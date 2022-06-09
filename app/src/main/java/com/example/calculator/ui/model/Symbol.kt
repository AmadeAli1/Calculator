package com.example.calculator.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class Symbol(
    val value: String,
    val color: Color = Color.Black,
    val textSize: TextUnit = 14.sp
) {
    One("1"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Zero("0"),
    Clear("C", Color.Cyan, 18.sp),
    Del("DEL", Color.Cyan, 14.sp),
    Percent("%", Color.Red, 20.sp),
    Div("/", Color.Red, 20.sp),
    Multiply("*", Color.Red, 20.sp),
    Minus("-", Color.Red, 20.sp),
    Plus("+", Color.Red, 20.sp),
    Equal("=", Color.Cyan, 24.sp),
    Ans("ANS", Color.Black),
    Point(".", Color.Black);

}