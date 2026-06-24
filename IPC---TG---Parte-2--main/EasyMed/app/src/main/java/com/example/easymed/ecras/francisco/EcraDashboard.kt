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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.DadosApp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.NotificacoesHelper
import com.example.easymed.ui.theme.AmareloAtraso
import com.example.easymed.ui.theme.FundoClaro
import com.example.easymed.ui.theme.VerdeTomado
import com.example.easymed.ui.theme.VermelhoIgnorado

// ============================================================
// ECRA F2 - DASHBOARD DO CUIDADOR (Cenario 2)
// Autor: Francisco Soudo (14060)
// @param aoVerDetalhe:    vai para o ecra F3 (tomas do Antonio)
// @param aoSimularAlerta: vai para o ecra F1 (notificacao critica)
// ============================================================
@Composable
fun EcraDashboard(aoVerDetalhe: () -> Unit, aoSimularAlerta: () -> Unit) {

    // LocalContext.current da-nos o "contexto" do Android.
    // Precisamos dele para enviar a notificacao real de alerta.
    val contexto = LocalContext.current

    // Verifica o estado atual do Antonio:
    //   tomaIgnorada  != null -> ha uma toma IGNORADA (vermelho)
    //   tomaAtrasada  != null -> ha uma toma com ATRASO (amarelo)
    //   ambas null           -> tudo em dia (verde)
    // Estes valores sao recalculados pelo Compose sempre que as
    // tomas mudam de estado (porque DadosApp usa mutableStateListOf).
    val tomaIgnorada = DadosApp.tomaIgnorada()
    val tomaAtrasada = DadosApp.tomaComAtraso()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoClaro)
    ) {
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text     = "Pacientes:",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            // ---- CARTAO DO ANTONIO ----
            // sem alerta: cartao normal; com alerta: borda vermelha
            val modificadorCartao: Modifier = if (tomaIgnorada != null)
                Modifier.fillMaxWidth().border(2.dp, VermelhoIgnorado, RoundedCornerShape(12.dp)).clickable { aoVerDetalhe() }
            else
                Modifier.fillMaxWidth().clickable { aoVerDetalhe() }

            Card(
                modifier = modificadorCartao,
                shape    = RoundedCornerShape(12.dp),
                colors   = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier          = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text       = "António Ferreira  72 anos",
                            fontSize   = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // o texto e a cor mudam conforme o estado do Antonio
                        if (tomaIgnorada != null) {
                            // caso 1: ha uma toma IGNORADA (vermelho)
                            val medicamento = DadosApp.medicamentoDaToma(tomaIgnorada)
                            Text(
                                text     = "Toma em atraso: " + medicamento.nome + " " + medicamento.dosagem,
                                color    = VermelhoIgnorado,
                                fontSize = 14.sp
                            )
                            Text(
                                text     = tomaIgnorada.horaPrevista + "  Ha mais de 30 minutos",
                                color    = Color.Gray,
                                fontSize = 13.sp
                            )
                        } else if (tomaAtrasada != null) {
                            // caso 2: o cuidador ja registou, mas com atraso (amarelo)
                            Text(
                                text     = "Toma registada com atraso (vigilancia)",
                                color    = Color(0xFFB28704), // amarelo escuro (melhor contraste no fundo branco)
                                fontSize = 14.sp
                            )
                            Text(
                                text     = "Registada as " + (tomaAtrasada.horaConfirmada ?: ""),
                                color    = Color.Gray,
                                fontSize = 13.sp
                            )
                        } else {
                            // caso 3: tudo em dia (verde)
                            Text(
                                text     = "Todas as tomas em dia",
                                color    = VerdeTomado,
                                fontSize = 14.sp
                            )
                        }
                    }

                    // bolinha de estado: uma so cor de relance
                    // vermelho / amarelo / verde conforme o estado
                    val corBolinha = when {
                        tomaIgnorada != null -> VermelhoIgnorado
                        tomaAtrasada != null -> AmareloAtraso
                        else                 -> VerdeTomado
                    }
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .background(corBolinha, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ---- BOTAO "SIMULAR TOMA IGNORADA" (demo do Cenario 2) ----
            // *** NOTIFICACOES - PASSO 3 DE 3 (parte Francisco) ***
            //
            // Quando a Carla (ou o demonstrador) carrega neste botao:
            //   1. Muda a Sinvastatina das 15:00 para estado IGNORADO (vermelho)
            //   2. Envia uma notificacao REAL para a barra do Android
            //      (como se o sistema tivesse detetado a toma nao confirmada)
            //   3. Muda o ecra para F1 (notificacao critica)
            //
            // Porquê OutlinedButton (contorno) em vez de Button (solido)?
            // E propositado: e um botao de DEMO, nao uma acao do utilizador
            // real. O contorno indica "acao secundaria/tecnica".
            OutlinedButton(
                onClick = {
                    // PASSO 1: atualiza o estado da toma para IGNORADO
                    // (a Sinvastatina das 15:00 e marcada a vermelho)
                    DadosApp.simularTomaIgnorada()

                    // PASSO 2: envia a notificacao REAL de alerta para o Android
                    // Vamos buscar a toma que acabou de ser ignorada para ter
                    // o nome do medicamento e a hora certa na notificacao
                    val tomaIgnoradaAgora = DadosApp.tomaIgnorada()
                    if (tomaIgnoradaAgora != null) {
                        val medicamento = DadosApp.medicamentoDaToma(tomaIgnoradaAgora)

                        // envia a notificacao de alerta (aparece na barra do telemovel)
                        NotificacoesHelper.enviarNotificacaoAlerta(
                            contexto        = contexto,
                            nomeMedicamento = medicamento.nome,
                            dosagem         = medicamento.dosagem,
                            horaPrevista    = tomaIgnoradaAgora.horaPrevista
                        )
                    }

                    // PASSO 3: muda o ecra para F1 (notificacao critica)
                    aoSimularAlerta()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp) // 56dp > 48dp minimos de acessibilidade
            ) {
                Text(
                    text     = "Simular toma ignorada (demo)",
                    color    = VermelhoIgnorado,
                    fontSize = 16.sp
                )
            }
        }
    }
}
