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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.CartaoToma
import com.example.easymed.DadosApp
import com.example.easymed.EstadoToma
import com.example.easymed.ui.theme.AzulEscuro
import com.example.easymed.ui.theme.FundoClaro

// ============================================================
// ECRÃ M4 - ECRÃ PRINCIPAL "HOJE" (Cenário 1)
// Autor: Miguel Pauzinho (27131)
// ============================================================


@Composable
// função que desenha interface
fun EcraHoje(aoSimularAlarme: (Int) -> Unit) {


    // ----- dados que este ecrã precisa de mostrar -----

    // 1) totais calculados a partir dos dados reais em DadosApp. Se uma
    //    toma mudar de estado, o Compose recalcula isto e atualiza o ecrã
    //    sozinho (magia do mutableStateListOf).
    // quantas tomas há hoje no total
    val totalTomas = DadosApp.tomas.size
    // quantas já foram confirmadas
    val feitas     = DadosApp.tomasFeitas()
    // qual é a próxima (só para o texto "Próxima toma")
    val proxima    = DadosApp.proximaTomaPendente()

    // 2) a demo do Cenário 1 é SEMPRE sobre a Metformina das 12:00
    //    (medicamentoId 2): o "Simular Alarme" aponta sempre a esta toma e
    //    NÃO avança para o medicamento seguinte, para a demo ser previsível.
    // Metformina (12:00)
    val tomaDemo = DadosApp.tomas.first { it.medicamentoId == 2 }


    // Column = caixa vertical que empilha os elementos de cima para baixo
    Column(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // fundo cinzento muito claro
            .background(FundoClaro)
    ) {
        // barra azul do topo (sem subtítulo)
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        // coluna com scroll: permite rolar se o conteúdo não couber no ecrã
        Column(
            modifier = Modifier
                // ocupa o espaço disponível
                .fillMaxSize()
                // ativa o scroll vertical
                .verticalScroll(rememberScrollState())
                // margem interior de 16dp
                .padding(16.dp)
        ) {

            // ----- CARTÃO DE RESUMO (fundo azul escuro) -----
            // Mostra de relance quantas tomas foram feitas e quantas faltam.
            // Heurística de Nielsen: Visibilidade do Estado do Sistema.
            Card(
                // cartão a toda a largura
                modifier = Modifier.fillMaxWidth(),
                // cantos arredondados
                shape  = RoundedCornerShape(12.dp),
                // fundo azul escuro
                colors = CardDefaults.cardColors(containerColor = AzulEscuro)
            ) {
                // conteúdo do cartão, com margem
                Column(modifier = Modifier.padding(16.dp)) {
                    // linha: "Hoje" e "X de Y tomas" lado a lado
                    Row {
                        // texto "Hoje" (à esquerda)
                        Text(
                            text     = "Hoje",
                            // branco (contrasta com o azul escuro)
                            color    = Color.White,
                            // a negrito
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            // ocupa o espaço e empurra o resto para a direita
                            modifier = Modifier.weight(1f)
                        )
                        // contagem de tomas (à direita)
                        Text(
                            // ex: "1 de 4 tomas"
                            text     = feitas.toString() + " de " + totalTomas + " tomas",
                            color    = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // espaço antes do título da lista
            Spacer(modifier = Modifier.height(20.dp))

            // título da secção da lista
            Text(
                text     = "Tomas de Hoje",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            // espaço antes dos cartões
            Spacer(modifier = Modifier.height(8.dp))

            // ----- LISTA CRONOLÓGICA DAS TOMAS -----
            // Um CartaoToma por cada toma do dia, por ordem de hora.
            // Heurística: Reconhecimento em vez de Memorização -
            // o António não precisa de se lembrar de nada, está tudo aqui.
            // percorre todas as tomas de hoje
            for (toma in DadosApp.tomas) {
                // desenha o cartão desta toma
                CartaoToma(toma)
                // espaço entre cartões
                Spacer(modifier = Modifier.height(8.dp))
            }

            // espaço antes da "próxima toma"
            Spacer(modifier = Modifier.height(12.dp))

            // indicação da próxima toma (só aparece se houver alguma pendente)
            if (proxima != null) {
                // ...mostra a hora da próxima
                Text(
                    // ex: "Próxima toma às 12:00"
                    text       = "Próxima toma às " + proxima.horaPrevista,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color.DarkGray
                )
            }

            // espaço antes do botão
            Spacer(modifier = Modifier.height(16.dp))

            // ----- BOTÃO "SIMULAR ALARME" (demo do Cenário 1) -----
            // Abre o ecrã bloqueado simulado (M1) SEMPRE para a Metformina das
            // 12:00 (não avança para o medicamento seguinte). É uma simulação
            // (sem AlarmManager), como o enunciado permite para ilustrar o
            // funcionamento geral. Pode ser clicado várias vezes (a toma é
            // reposta a PENDENTE antes de abrir o M1).
            Button(
                // o que acontece ao tocar no botão
                onClick = {
                    // PASSO 1: repõe a Metformina a PENDENTE para a demo poder repetir.
                    // Sem isto, depois de "Tomei" a toma fica TOMADO (verde) e o
                    // fluxo não volta a funcionar (o M2 já não teria efeito).
                    val posicao = DadosApp.tomas.indexOfFirst { it.id == tomaDemo.id }
                    // encontrou a toma na lista
                    if (posicao != -1) {
                        // substitui por uma cópia com estado PENDENTE e sem hora de confirmação
                        DadosApp.tomas[posicao] = DadosApp.tomas[posicao].copy(
                            estado = EstadoToma.PENDENTE,
                            horaConfirmada = null
                        )
                    }
                    // PASSO 2: navega para o M1 (ecrã bloqueado simulado)
                    aoSimularAlarme(tomaDemo.id)            // ===> NAVEGAÇÃO -> M1: abre o alarme da Metformina (12:00)
                },
                modifier = Modifier
                    // botão a toda a largura
                    .fillMaxWidth()
                    // 56dp > 48dp mínimos de acessibilidade do Android
                    .height(56.dp)
            ) {
                // texto dentro do botão
                Text(text = "Simular Alarme (demo)", fontSize = 16.sp)
            }
        }
    }
}