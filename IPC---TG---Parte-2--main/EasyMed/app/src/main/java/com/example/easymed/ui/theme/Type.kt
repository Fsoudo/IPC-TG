package com.example.easymed.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================================
// TIPOGRAFIA DA EASYMED
// Acessibilidade: o tamanho base do texto é 18sp (o normal do
// Android é 16sp), porque o nosso utilizador principal (António,
// 72 anos) tem dificuldade em ler letras pequenas. Os "sp"
// respeitam o tamanho de letra do sistema, por isso se o utilizador
// aumentar a letra nas definições do Android, a app acompanha.
// ============================================================
// cria o conjunto de estilos da app
val Typography = Typography(
    // "bodyLarge" = estilo do texto normal/corpo
    bodyLarge = TextStyle(
        // usa a fonte padrão do sistema
        fontFamily = FontFamily.Default,
        // espessura normal (sem negrito)
        fontWeight = FontWeight.Normal,
        // tamanho base 18sp (maior, para idosos)
        fontSize = 18.sp,
        // altura de cada linha (espaço entre linhas)
        lineHeight = 26.sp,
        // pequeno espaço entre letras (legibilidade)
        letterSpacing = 0.5.sp
    )
)