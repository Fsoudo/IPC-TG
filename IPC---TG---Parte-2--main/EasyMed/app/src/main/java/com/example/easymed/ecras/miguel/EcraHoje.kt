package com.example.easymed.ecras.miguel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.CartaoToma
import com.example.easymed.DadosApp
import com.example.easymed.NotificacoesHelper
import com.example.easymed.ui.theme.AzulEscuro
import com.example.easymed.ui.theme.FundoClaro
import com.example.easymed.ui.theme.VerdeTomado

// ============================================================
// ECRA M4 - ECRA PRINCIPAL "HOJE" (Cenario 1)
// Autor: Miguel Pauzinho (27131)
//
// E o ecra onde o Antonio passa a maior parte do tempo:
// mostra a lista das tomas de hoje com cores, um cartao de
// resumo no topo e a indicacao da proxima toma.
// Prototipo de referencia: "M4 - Ecra Principal" (TG1).
//
// O botao "Simular Alarme" e o inicio da demo do Cenario 1:
//   1. Envia uma notificacao REAL para a barra do Android
//   2. Muda o ecra para M1 (ecra bloqueado)
//   3. O utilizador continua: M1 -> M2 -> M3 -> volta aqui
//
// @param aoSimularAlarme: funcao chamada pelo botao "Simular Alarme";
//   definida na MainActivity e muda o ecra para "m1_bloqueado"
// ============================================================
@Composable
fun EcraHoje(aoSimularAlarme: () -> Unit) {

    // LocalContext.current da-nos o "contexto" do Android.
    // Precisamos dele para enviar a notificacao real.
    val contexto = LocalContext.current

    // Calcula os totais a partir dos dados reais em DadosApp.
    // Se uma toma mudar de estado, o Compose recalcula isto
    // e atualiza o ecra sozinho (magia do mutableStateListOf).
    val totalTomas = DadosApp.tomas.size           // quantas tomas ha hoje no total
    val feitas     = DadosApp.tomasFeitas()         // quantas ja foram confirmadas
    val proxima    = DadosApp.proximaTomaPendente() // qual e a proxima (null se nao houver)

    // Column = caixa vertical que empilha os elementos de cima para baixo
    Column(
        modifier = Modifier
            .fillMaxSize()             // ocupa o ecra todo
            .background(FundoClaro)    // fundo cinzento muito claro
    ) {
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        // coluna com scroll: permite rolar se o conteudo nao couber no ecra
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // ---- CARTAO DE RESUMO (fundo azul escuro) ----
            // Mostra de relance quantas tomas foram feitas e quantas faltam.
            // Heuristica de Nielsen: Visibilidade do Estado do Sistema.
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape  = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = AzulEscuro)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    // linha: "Hoje" e "X de Y tomas" lado a lado
                    Row {
                        Text(
                            text     = "Hoje",
                            color    = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text     = feitas.toString() + " de " + totalTomas + " tomas",
                            color    = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text     = "Tomas de Hoje",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            // ---- LISTA CRONOLOGICA DAS TOMAS ----
            // Um CartaoToma por cada toma do dia, por ordem de hora.
            // Heuristica: Reconhecimento em vez de Memorizacao -
            // o Antonio nao precisa de se lembrar de nada, esta tudo aqui.
            for (toma in DadosApp.tomas) {
                CartaoToma(toma)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            // indicacao da proxima toma (ou mensagem de parabens se acabou)
            if (proxima != null) {
                Text(
                    text       = "Próxima toma às " + proxima.horaPrevista,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color.DarkGray
                )
            } else {
                Text(
                    text       = "Não há mais tomas pendentes hoje!",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color      = VerdeTomado
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ---- BOTAO "SIMULAR ALARME" (demo do Cenario 1) ----
            // *** NOTIFICACOES - PASSO 3 DE 3 (parte Miguel) ***
            //
            // Quando o utilizador carrega neste botao:
            //   1. Enviamos uma notificacao REAL para a barra do Android
            //      (o utilizador ve a notificacao a aparecer no topo do ecra)
            //   2. Chamamos aoSimularAlarme() para mudar o ecra para M1
            //
            // O botao fica desativado (enabled = false) quando nao ha
            // mais tomas pendentes (nao faz sentido simular um alarme
            // se nao ha nada para tomar).
            Button(
                onClick = {
                    if (proxima != null) {
                        // vai buscar a informacao do medicamento desta toma
                        val medicamento = DadosApp.medicamentoDaToma(proxima)

                        // *** envia a notificacao REAL ***
                        // Esta chamada comunica com o Android para mostrar
                        // a notificacao na barra de notificacoes do telemovel.
                        NotificacoesHelper.enviarNotificacaoToma(
                            contexto         = contexto,           // acesso ao Android
                            nomeMedicamento  = medicamento.nome,   // ex: "Amlodipina"
                            dosagem          = medicamento.dosagem,// ex: "5mg"
                            hora             = proxima.horaPrevista// ex: "09:00"
                        )
                    }
                    // muda o ecra para M1 (ecra bloqueado)
                    // a logica de guardar o tomaAlarmeId esta em MainActivity
                    aoSimularAlarme()
                },
                enabled  = proxima != null, // desativado se nao ha tomas pendentes
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp) // 56dp > 48dp minimos de acessibilidade do Android
            ) {
                Text(text = "Simular Alarme (demo)", fontSize = 16.sp)
            }
        }
    }
}
