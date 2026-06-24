package com.example.easymed.ecras.miguel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.DadosApp
import com.example.easymed.ui.theme.FundoEcraBloqueado
import com.example.easymed.ui.theme.VerdeTomado

// ============================================================
// ECRÃ M2 - NOTIFICAÇÃO EXPANDIDA (Cenário 1)
// Autor: Miguel Pauzinho (27131)
// ============================================================


@Composable
// função que desenha interface
fun EcraNotificacao(tomaId: Int, aoTomei: () -> Unit, aoAdiar: () -> Unit) {


    // ----- dados que este ecrã precisa de mostrar -----

    // 1) com o id, encontra a Toma certa na DadosApp (a "memória" da app).
    val toma = DadosApp.tomaPorId(tomaId)

    // 2) a partir do medicamentoId da Toma, vai buscar o Medicamento
    //    (nome, dosagem e forma) para o mostrar no cartão.
    val medicamento = DadosApp.medicamentoDaToma(toma)


    // Box = fundo escuro a ocupar todo o ecrã
    Box(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // aplica a cor de fundo escuro ao ecrã bloqueado
            .background(FundoEcraBloqueado)
    ) {
        // empilha relógio + horas + notificação
        Column(
            modifier = Modifier
                // ocupa todo o espaço do ecrã
                .fillMaxSize(),
            // alinha tudo ao centro do ecrã na horizontal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // espaço no topo
            Spacer(modifier = Modifier.height(40.dp))

            // Identificação de quem pertence o telemóvel
            Text(text = "Telemóvel de António (paciente)", color = Color(0x99FFFFFF), fontSize = 15.sp)
            // espaço antes do relógio
            Spacer(modifier = Modifier.height(35.dp))

            // relógio grande com a hora prevista da toma
            Text(text = toma.horaPrevista, color = Color.White, fontSize = 64.sp, fontWeight = FontWeight.Light)
            // data fixa
            Text(text = "terça, 23 de junho", color = Color(0xAAFFFFFF), fontSize = 15.sp)

            // espaço antes do cartão da notificação
            Spacer(modifier = Modifier.height(50.dp))

            // cartão da notificação expandida
            Card(
                // a toda a largura
                modifier = Modifier.fillMaxWidth(),
                // cor de fundo da notificação
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4))
            ) {
                // Conteúdo da notificação
                Column(modifier = Modifier.padding(16.dp)) {
                    // Identificação da notificação (nome da app, pequeno)
                    Text(text = "EasyMed", fontSize = 13.sp, color = Color.Gray)
                    // pequeno espaço
                    Spacer(modifier = Modifier.height(4.dp))
                    // nome + dosagem do medicamento (destaque)
                    Text(
                        text = medicamento.nome + " " + medicamento.dosagem,
                        fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black
                    )
                    // quantidade + hora prevista
                    Text(
                        text = "1 " + medicamento.forma + "  -  Hora prevista: " + toma.horaPrevista,
                        fontSize = 15.sp, color = Color.DarkGray
                    )
                    // espaço antes dos botões
                    Spacer(modifier = Modifier.height(20.dp))
                    // os dois botões lado a lado
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // ADIAR: ação secundária (contorno, menos destaque)
                        OutlinedButton(
                            // ===> NAVEGAÇÃO -> M4 (Hoje): "Adiar 15 min" adia a toma e volta ao ecrã principal
                            onClick = aoAdiar,
                            // ocupa 1 parte da largura; alvo de 56dp
                            modifier = Modifier.weight(1f).height(56.dp)
                        ) {
                            // texto do botão "Adiar"
                            Text(text = "Adiar 15 min", color = Color.Gray, fontSize = 15.sp)
                        }
                        // espaço entre os dois botões
                        Spacer(modifier = Modifier.width(12.dp))
                        // TOMEI: ação principal (verde, maior)
                        Button(
                            // ===> NAVEGAÇÃO -> M3: "Tomei" confirma a toma e abre a confirmação
                            onClick = aoTomei,
                            // mais largo (1.3) = mais destaque
                            modifier = Modifier.weight(1.3f).height(56.dp),
                            // fundo verde
                            colors = ButtonDefaults.buttonColors(containerColor = VerdeTomado)
                        ) {
                            // texto do botão "Tomei"
                            Text(text = "Tomei", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
