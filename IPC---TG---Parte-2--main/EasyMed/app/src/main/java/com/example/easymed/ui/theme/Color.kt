package com.example.easymed.ui.theme


import androidx.compose.ui.graphics.Color

// ============================================================
// CORES DA EASYMED (definidas no protótipo Figma do TG1)
//
//
//
// Código de cores dos estados de uma toma (decidido no TG1):
//   Verde    = toma confirmada a horas
//   Amarelo  = toma registada com atraso (pelo cuidador)
//   Vermelho = toma ignorada (passaram +30 min sem confirmação)
//   Cinzento = toma ainda pendente
//
// Estas cores são usadas em toda a app para o utilizador
// perceber o estado "de relance" (heurística de Nielsen:
// visibilidade do estado do sistema).
//
// O valor 0xFFrrggbb é a cor em hexadecimal: FF = opacidade total,
// seguido dos componentes vermelho (rr), verde (gg) e azul (bb).
//
//
// ============================================================

// azul principal (cabeçalhos e botões)
val AzulEasyMed = Color(0xFF2196F3)
// azul escuro do cartão de resumo do ecrã "Hoje"
val AzulEscuro = Color(0xFF1C2B3A)
// estado: tomado
val VerdeTomado = Color(0xFF4CAF50)
// amarelo - tomas pendentes (por tomar)
val Amarelo = Color(0xFFFFC107)
// vermelho - tomas em atraso / alerta
val Vermelho = Color(0xFFE53935)
// estado: pendente
val CinzentoPendente = Color(0xFF9E9E9E)
// fundo cinzento claro dos ecrãs
val FundoClaro = Color(0xFFF2F2F2)
// fundo escuro dos ecrãs de "telemóvel bloqueado"
val FundoEcraBloqueado = Color(0xFF16161E)
// círculo verde escuro do ecrã de confirmação (M3)
val VerdeEscuroVisto = Color(0xFF2E5B30)
