package com.example.easymed.ecras.francisco

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
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
import com.example.easymed.ui.theme.VermelhoIgnorado

// ============================================================
// ECRA F1 - NOTIFICACAO CRITICA NO ECRA BLOQUEADO (Cenario 2)
// Autor: Francisco Soudo (14060)
// ============================================================

// @param aoVerDetalhe: acao ao tocar no alerta (MainActivity leva ao detalhe F3)
@Composable
fun EcraAlertaCritico(aoVerDetalhe: () -> Unit) {
    // procura a toma que foi ignorada (e a razao de o alerta existir).
    // tomaIgnorada() devolve null se nao houver nenhuma a vermelho.
    val toma = DadosApp.tomaIgnorada()

    // Box ocupa o ecra com o fundo escuro de "telemovel bloqueado" (da Carla)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoEcraBloqueado)
    ) {
        // Column centra o conteudo; os *Padding evitam as barras do sistema
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // identificacao do dono do telemovel (feedback do TG1: o ecra
            // bloqueado da Carla era parecido com o do Antonio e baralhava)
            Text(
                text = "📱 Telemóvel de Carla (cuidadora)",
                color = Color(0x99FFFFFF),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // relogio do telemovel da Carla: hora da toma + 30 min de tolerancia.
            // comeca com a hora atual e, se houver toma ignorada, mostra a hora
            // prevista + 30 min (foi quando o sistema decidiu que estava "ignorada").
            var horaRelogio = DadosApp.horaAtual()
            if (toma != null) {
                horaRelogio = DadosApp.somarMinutos(toma.horaPrevista, 30)
            }
            Text(
                text = horaRelogio,
                color = Color.White,
                fontSize = 72.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = DadosApp.dataDeHoje(),
                color = Color(0xAAFFFFFF),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(56.dp))

            // so mostra o cartao de alerta se existir mesmo uma toma ignorada
            if (toma != null) {
                val medicamento = DadosApp.medicamentoDaToma(toma)

                // notificacao critica: borda vermelha (.border) para se distinguir
                // de uma notificacao normal (visibilidade do estado).
                // .clickable leva ao detalhe (F3) quando se toca no cartao.
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, VermelhoIgnorado, RoundedCornerShape(16.dp)) // contorno vermelho
                        .clickable { aoVerDetalhe() },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(VermelhoIgnorado, RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "EasyMed - Alerta",
                                color = VermelhoIgnorado,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "António não confirmou a toma",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                        Text(
                            text = medicamento.nome + " " + medicamento.dosagem + "  30 min de atraso",
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Toque para ver mais detalhes",
                            color = VermelhoIgnorado,
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                // protecao: se nao houver toma ignorada nao ha alerta para mostrar
                Text(
                    text = "Sem alertas neste momento.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "(Demo) Toque na notificação para abrir o detalhe",
                color = Color(0x88FFFFFF),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
