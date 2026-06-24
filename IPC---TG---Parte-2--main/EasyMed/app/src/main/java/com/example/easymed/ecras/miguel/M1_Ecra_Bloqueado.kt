package com.example.easymed.ecras.miguel


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

// ============================================================
// ECRÃ M1 - ECRÃ BLOQUEADO DO PACIENTE (Cenário 1)
// Autor: Miguel Pauzinho (27131)
// ============================================================


@Composable
// função que desenha interface
fun EcraBloqueado(tomaId: Int, aoAbrirNotificacao: () -> Unit) {


    // ----- dados que este ecrã precisa de mostrar -----

    // 1) com o id, encontra a Toma certa na DadosApp (a "memória" da app).
    //    A Toma guarda: id, medicamentoId, horaPrevista, estado.
    val toma = DadosApp.tomaPorId(tomaId)

    // 2) a Toma só tem o medicamentoId (um número), não o nome do remédio.
    //    medicamentoDaToma usa esse número para ir buscar o objeto
    //    Medicamento, que tem: nome, dosagem e forma.
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
            Text(text = toma.horaPrevista, color = Color.White, fontSize = 72.sp, fontWeight = FontWeight.Light)
            // data fixa
            Text(text = "terça, 23 de junho", color = Color(0xAAFFFFFF), fontSize = 15.sp)

            // espaço antes do cartão da notificação
            Spacer(modifier = Modifier.height(50.dp))

            // cartão da notificação - tocar abre o M2
            Card(
                modifier = Modifier
                    // preenche a largura do ecrã
                    .fillMaxWidth()
                    // torna a notificação clicável para levar ao próximo ecrã
                    // ===> NAVEGAÇÃO -> M2: tocar no cartão abre a notificação expandida (a rota muda na MainActivity)
                    .clickable { aoAbrirNotificacao() },
                // cor de fundo da notificação
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4))
            ) {
                // Conteúdo da notificação
                Column(modifier = Modifier.padding(16.dp)) {
                    // Identificação da notificação
                    Text(text = "EasyMed", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    // medicamento + dosagem + quantidade (ex: "Metformina 500mg 1 comprimido")
                    Text(
                        text = medicamento.nome + " " + medicamento.dosagem + "  1 " + medicamento.forma,
                        fontSize = 15.sp, color = Color.DarkGray
                    )
                    // Detalhes - Sugere ao utilizador para tocar na notificação
                    Text(text = "Toque para abrir", fontSize = 15.sp, color = Color.Gray)
                }
            }
        }
    }
}