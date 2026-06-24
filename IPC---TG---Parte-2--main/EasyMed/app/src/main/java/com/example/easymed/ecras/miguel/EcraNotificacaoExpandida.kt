package com.example.easymed.ecras.miguel

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.easymed.ui.theme.FundoEcraBloqueado
import com.example.easymed.ui.theme.VerdeTomado

// ============================================================
// ECRA M2 - NOTIFICACAO EXPANDIDA (Cenario 1)
// Autor: Miguel Pauzinho (27131)
//
// A notificacao aberta, com os dois botoes de acao:
//   "Tomei"        -> botao VERDE, GRANDE e a direita (acao principal)
//   "Adiar 15 min" -> botao cinzento, com menos destaque
//
// Decisao de desenho (prevencao de erros, Nielsen): o "Tomei" e
// maior e mais forte que o "Adiar" para o Antonio nao confirmar
// por engano a acao errada. Os botoes tem 56dp de altura
// (> 48dp minimos de acessibilidade do Android).
// ============================================================

// @param tomaId:  a toma que a notificacao esta a anunciar (vem do M1)
// @param aoTomei: acao do botao "Tomei" (MainActivity leva ao ecra M3)
// @param aoAdiar: acao do botao "Adiar 15 min" (MainActivity volta ao "Hoje")
@Composable
fun EcraNotificacaoExpandida(tomaId: Int, aoTomei: () -> Unit, aoAdiar: () -> Unit) {
    // vai buscar a toma pelo id; null = nao existe (protecao com "return")
    val toma = DadosApp.tomaPorId(tomaId)
    if (toma == null) {
        return
    }
    val medicamento = DadosApp.medicamentoDaToma(toma) // medicamento desta toma

    // o "contexto" e necessario para mostrar mensagens Toast do Android
    // (LocalContext.current da-nos o contexto do ecra atual)
    val contexto = LocalContext.current

    // Box ocupa o ecra todo com o fundo escuro de "telemovel bloqueado"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoEcraBloqueado)
    ) {
        // Column centra o conteudo; os *Padding evitam as barras do sistema
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()     // espaco para a barra de estado (topo)
                .navigationBarsPadding() // espaco para a barra de navegacao (fundo)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // identificacao do dono do telemovel (feedback do TG1)
            Text(
                text = "📱 Telemóvel de António (paciente)",
                color = Color(0x99FFFFFF),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = toma.horaPrevista,
                color = Color.White,
                fontSize = 64.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = DadosApp.dataDeHoje(),
                color = Color(0xAAFFFFFF),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // notificacao expandida com a informacao completa do medicamento
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "EasyMed",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = medicamento.nome + " " + medicamento.dosagem,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "1 " + medicamento.forma + "  -  Hora prevista: " + toma.horaPrevista,
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // os dois botoes de acao, lado a lado dentro de uma Row.
                    // "weight" reparte a largura: o "Tomei" leva 1.3 e o "Adiar" 1,
                    // por isso o "Tomei" fica MAIOR (acao principal mais destacada).
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // ADIAR: OutlinedButton (so contorno) = aspeto "fraco" de
                        // proposito, por ser a acao secundaria (prevencao de erros)
                        OutlinedButton(
                            onClick = {
                                DadosApp.adiarToma(toma.id) // soma 15 min a hora prevista
                                // Toast = mensagem curta que aparece e desaparece;
                                // feedback imediato para o utilizador saber o que aconteceu
                                Toast.makeText(
                                    contexto,
                                    "Toma adiada 15 minutos",
                                    Toast.LENGTH_SHORT
                                ).show()
                                aoAdiar() // muda de ecra (volta ao "Hoje")
                            },
                            modifier = Modifier
                                .weight(1f)   // ocupa 1 parte da largura
                                .height(56.dp)
                        ) {
                            Text(text = "Adiar 15 min", color = Color.Gray, fontSize = 15.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp)) // espaco entre os dois botoes

                        // TOMEI: Button solido verde, grande, em destaque (acao principal)
                        Button(
                            onClick = {
                                // FUNCIONALIDADE DO CENARIO 1: regista a toma
                                // com a hora real e o estado passa a verde
                                DadosApp.confirmarToma(toma.id)
                                aoTomei() // muda de ecra (vai para a confirmacao M3)
                            },
                            modifier = Modifier
                                .weight(1.3f) // ocupa MAIS largura que o "Adiar"
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = VerdeTomado) // verde
                        ) {
                            Text(
                                text = "Tomei",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
