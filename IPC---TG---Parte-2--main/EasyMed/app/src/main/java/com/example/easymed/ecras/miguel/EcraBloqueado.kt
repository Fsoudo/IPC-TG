package com.example.easymed.ecras.miguel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.DadosApp
import com.example.easymed.ui.theme.FundoEcraBloqueado

// ============================================================
// ECRA M1 - ECRA BLOQUEADO COM NOTIFICACAO (Cenario 1)
// Autor: Miguel Pauzinho (27131)
//
// Simula o telemovel bloqueado do Antonio na hora da toma.
// E a versao de alta fidelidade do prototipo "M1 - Ecra Bloqueado"
// do TG1 (Figma). O relogio grande mostra a hora PREVISTA da toma,
// porque na historia do cenario o alarme toca exatamente a essa hora.
//
// Nota (planeamento TG2): as notificacoes reais do Android
// precisavam de permissoes e de AlarmManager; ficou decidido usar
// o "plano B" - simular o ecra bloqueado dentro da app, o que ate
// e mais fiel ao prototipo do TG1 e mais fiavel na demo e nos
// testes com utilizadores.
// ============================================================

// @param tomaId:             a toma que o alarme esta a anunciar (escolhida na MainActivity)
// @param aoAbrirNotificacao: acao quando se toca na notificacao (leva ao ecra M2)
@Composable
fun EcraBloqueado(tomaId: Int, aoAbrirNotificacao: () -> Unit) {
    // vai buscar a toma e o medicamento que o alarme esta a anunciar
    val toma = DadosApp.tomaPorId(tomaId)
    if (toma == null) {
        return // protecao: se a toma nao existir, nao desenha nada
    }
    val medicamento = DadosApp.medicamentoDaToma(toma)

    // Box = caixa que ocupa o ecra todo, com fundo escuro (telemovel bloqueado)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoEcraBloqueado)
    ) {
        // Column empilha o relogio, a data e o cartao da notificacao.
        // Os *Padding garantem que nada fica por baixo das barras do sistema.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally // tudo centrado ao meio
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // identificacao do dono do telemovel (feedback do TG1: tornar
            // explicito de que utilizador/cenario se trata na demo)
            Text(
                text = "📱 Telemóvel de António (paciente)",
                color = Color(0x99FFFFFF),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // relogio grande, como num ecra bloqueado verdadeiro.
            // mostra a hora PREVISTA da toma (na historia, o alarme toca a essa hora).
            Text(
                text = toma.horaPrevista,
                color = Color.White,
                fontSize = 72.sp,                 // texto enorme (relogio)
                fontWeight = FontWeight.Light      // tracos finos, estilo relogio
            )
            Text(
                text = DadosApp.dataDeHoje(),
                color = Color(0xAAFFFFFF),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(56.dp))

            // a notificacao da EasyMed - texto grande e direto
            // (heuristica: visibilidade do estado do sistema).
            // .clickable { ... } torna o cartao tocavel: ao tocar, executa a acao.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { aoAbrirNotificacao() }, // tocar na notificacao expande-a (passa ao M2)
                shape = RoundedCornerShape(16.dp),                              // cantos arredondados
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4)) // cinzento muito claro
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Text(
                            text = "EasyMed",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = medicamento.nome + " " + medicamento.dosagem + "  1 " + medicamento.forma,
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Hora prevista: " + toma.horaPrevista,
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // empurra o texto de ajuda para o fundo do ecra
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "(Demo) Toque na notificação para a expandir",
                color = Color(0x88FFFFFF),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
