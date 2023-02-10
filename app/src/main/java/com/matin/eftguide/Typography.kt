package com.matin.eftguide

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val EFTFontFamily = FontFamily(
    Font(R.font.na_num_square_round_b)
)

val typography = Typography(
    defaultFontFamily = EFTFontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Light
    )
)