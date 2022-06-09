package com.example.calculator.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Light
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.model.Repository.symbols
import com.example.calculator.ui.model.Symbol
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CustomButton(symbol: Symbol, onClick: (Symbol) -> Unit, isLight: MutableState<Boolean>) {
    val textColor = if (isLight.value) Color.Black else Color.White

    Button(
        onClick = {
            onClick(symbol)
        },
        modifier = Modifier.height(70.dp),
        shape = RoundedCornerShape(15),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =
            if (!isLight.value) Color.DarkGray else
                MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = symbol.value,
            textAlign = TextAlign.Center,
            fontSize = symbol.textSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(1.dp),
            color = if (symbol.color == Color.Black) textColor else symbol.color
        )
    }
}

@Composable
fun SwitchTheme(isLight: MutableState<Boolean>, switch: () -> Unit) {
    Surface(
        elevation = 0.dp,
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(30)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                isLight.value = true
                switch()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Light, contentDescription = null,
                )
            }

            IconButton(onClick = {
                isLight.value = false
                switch()
            }) {
                Icon(
                    imageVector = if (isLight.value) Icons.Outlined.DarkMode else Icons.Filled.DarkMode,
                    contentDescription = null
                )
            }
        }
    }

}

@Composable
fun Grelha(isLight: MutableState<Boolean>) {

    val state = rememberLazyGridState()
    val viewModel: CalculadoraViewModel = viewModel<CalculadoraViewModel>()
    Surface(
        elevation = 8.dp,
        shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp),
            state = state,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = symbols()
            ) {
                CustomButton(
                    symbol = it,
                    onClick = { symbol ->
                        viewModel.insertExpression(symbol = symbol)
                    },
                    isLight = isLight
                )
            }
        }
    }
}

@Composable
fun HomeScreen(isLight: MutableState<Boolean>, switch: () -> Unit) {

    val viewModel: CalculadoraViewModel = viewModel<CalculadoraViewModel>()
    val expression = viewModel.expression.observeAsState()
    val expressionResult = viewModel.expressionResult.observeAsState()

    CalculatorTheme(darkTheme = !isLight.value) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .weight(1.8f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SwitchTheme(isLight = isLight, switch = switch)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(6f)
                ) {

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(horizontal = 32.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = expressionResult.value!!,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )

                        Text(
                            text = expression.value!!,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    }
                }
                Grelha(isLight = isLight)
            }
        }

    }
}