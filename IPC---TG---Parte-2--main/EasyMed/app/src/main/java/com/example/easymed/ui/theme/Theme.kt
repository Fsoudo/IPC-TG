package com.example.easymed.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Esquema de cores claro da aplicação, construído com as cores do protótipo do TG1
// "private" = só usado dentro deste ficheiro
private val EsquemaCoresClaro = lightColorScheme(
    // cor principal (botões, navegação selecionada)
    primary = AzulEasyMed,
    // cor do texto em cima da cor principal
    onPrimary = Color.White,
    // cor secundária (usada por alguns componentes)
    secondary = VerdeTomado,
    // cor do texto em cima da cor secundária
    onSecondary = Color.White,
    // cor de erro/alerta do Material 3
    error = Vermelho,
    // cor de fundo geral dos ecrãs
    background = FundoClaro,
    // cor dos cartões
    surface = Color.White
)

// ============================================================
// TEMA VISUAL DA EASYMED
//
//
//
// Decisões (justificadas no relatório do TG2):
// - Usamos SEMPRE o tema claro: o público-alvo são idosos e o tema
//   claro com alto contraste é mais fácil de ler.
// - NÃO usamos as "cores dinâmicas" do Android 12+ (o padrão dos
//   projetos das aulas), porque o código de cores verde/amarelo/
//   vermelho definido no TG1 tem significado e não pode mudar de
//   telemóvel para telemóvel.
//
//
// ============================================================
@Composable
// função que desenha interface
// recebe os ecrãs a mostrar dentro do tema
fun EasyMedTheme(content: @Composable () -> Unit) {
    // aplica o tema Material 3 a tudo o que está dentro
    MaterialTheme(
        // usa o esquema de cores claro definido acima
        colorScheme = EsquemaCoresClaro,
        // usa a tipografia definida em Type.kt
        typography = Typography,
        // desenha o conteúdo recebido com este tema
        content = content
    )
}