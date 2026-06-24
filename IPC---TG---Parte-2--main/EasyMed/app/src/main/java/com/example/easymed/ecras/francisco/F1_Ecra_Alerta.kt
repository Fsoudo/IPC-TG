package com.example.easymed.ecras.francisco


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.easymed.ui.theme.Vermelho

// ============================================================
// ECRÃ F1 - ECRÃ BLOQUEADO/ALERTA DA CUIDADORA (Cenário 2)
// Autor: Francisco Soudo (14060)
//
//
//
// A notificação toda é clicável: tocar em
// qualquer parte do cartão abre o detalhe das tomas (F3). É uma
// notificação minimalista (heurística de Nielsen: estética e design
// minimalista).
//
//
// ============================================================


@Composable
// função que desenha interface
fun EcraAlerta(tomaId: Int, aoVerDetalhe: () -> Unit) {


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
            // tudo centrado na horizontal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // espaço no topo
            Spacer(modifier = Modifier.height(40.dp))

            // Identificação de quem pertence o telemóvel
            Text(text = "Telemóvel de Carla (cuidadora)",
                color = Color(0x99FFFFFF),
                fontSize = 15.sp)
            // espaço antes do relógio
            // espaço antes do relógio
            Spacer(modifier = Modifier.height(35.dp))

            // relógio da Carla: hora prevista + 30 min (quando o sistema deteta a falha)
            Text(
                // hora da toma + 30 min (momento do alerta)
                text = DadosApp.somarMinutos(toma.horaPrevista, 30),
                color = Color.White,
                fontSize = 72.sp,
                fontWeight = FontWeight.Light
            )

            // data fixa
            Text(text = "quinta, 25 de junho", color = Color(0xAAFFFFFF), fontSize = 15.sp)

            // espaço entre a data e a notificação
            Spacer(modifier = Modifier.height(50.dp))

            // cartão de alerta - a notificação TODA é clicável
            Card(
                modifier = Modifier
                    // preenche a largura do ecrã
                    .fillMaxWidth()

                    // torna a notificação clicável para levar ao próximo ecrã
                    // ===> NAVEGAÇÃO -> F3: tocar na notificação toda abre o detalhe (a rota muda na MainActivity)
                    .clickable { aoVerDetalhe() },
                // cor de fundo da notificação
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4))
            ) {

                // Conteúdo da notificação
                Column(modifier = Modifier.padding(16.dp)) {

                    // Identificação da notificação
                    Text(
                        text = "EasyMed - Alerta",
                        color = Vermelho,

                        // letra negrito
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    // Espaço entre "EasyMed - Alerta" e o Título da notificação
                    Spacer(modifier = Modifier.height(8.dp))

                    // Título da notificação
                    Text(
                        text = "António não confirmou a toma",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    // Assunto da notificação
                    Text(
                        text = medicamento.nome + " " + medicamento.dosagem + "  -  30 min de atraso",
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )

                    // Espaço antes de "Toque para ver detalhes"
                    Spacer(modifier = Modifier.height(12.dp))

                    // Detalhes - Sugere ao utilizador para clicar na notificação
                    Text(
                        text = "Toque para ver mais detalhes",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}