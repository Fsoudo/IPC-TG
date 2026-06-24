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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.DadosApp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.ui.theme.Amarelo
import com.example.easymed.ui.theme.FundoClaro
import com.example.easymed.ui.theme.VerdeTomado
import com.example.easymed.ui.theme.Vermelho

// ============================================================
// ECRÃ F2 - DASHBOARD DO CUIDADOR (Cenário 2)
// Autor: Francisco Soudo (14060)
// ============================================================


@Composable
// função que desenha interface
fun EcraDashboard(aoVerDetalhe: () -> Unit, aoSimularIgnorada: (Int) -> Unit) {


    // ----- dados que este ecrã precisa de mostrar -----

    // 1) há alguma toma a vermelho (ATRASADO)? (ou null se estiver tudo bem).
    //    recalculado pelo Compose sempre que as tomas mudam de estado.
    val tomaAtrasada = DadosApp.tomaAtrasada()

    // 2) só é "tudo em dia" se TODAS as tomas estiverem feitas (verdes).
    //    sem isto, o cartão dizia "em dia" mesmo com tomas ainda pendentes.
    val todasEmDia = DadosApp.tomasFeitas() == DadosApp.tomas.size


    // Column principal: fundo claro a ocupar todo o ecrã
    Column(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // fundo cinzento claro
            .background(FundoClaro)
    ) {
        // barra azul do topo
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        // conteúdo com scroll e margem de 16dp
        Column(
            modifier = Modifier
                // ocupa o espaço disponível
                .fillMaxSize()
                // ativa o scroll vertical
                .verticalScroll(rememberScrollState())
                // margem interior de 16dp
                .padding(16.dp)
        ) {
            // título da secção
            Text(
                text     = "Pacientes:",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            // espaço antes do cartão
            Spacer(modifier = Modifier.height(8.dp))

            // ----- CARTÃO DO ANTÓNIO -----
            // sem alerta: cartão normal; com alerta (vermelho): borda vermelha
            // decide o aspeto do cartão
            val modificadorCartao: Modifier = if (tomaAtrasada != null)
                // com alerta: borda vermelha + clicável
                Modifier.fillMaxWidth().border(2.dp, Vermelho, RoundedCornerShape(12.dp)).clickable { aoVerDetalhe() }
            else
                // sem alerta: só clicável
                Modifier.fillMaxWidth().clickable { aoVerDetalhe() }

            Card(
                // usa o modificador decidido acima
                modifier = modificadorCartao,
                // cantos arredondados
                shape    = RoundedCornerShape(12.dp),
                // fundo branco
                colors   = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    // margem interior de 16dp
                    modifier          = Modifier.padding(16.dp),
                    // alinha ao centro na vertical
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // texto à esquerda (ocupa o espaço livre)
                    Column(modifier = Modifier.weight(1f)) {
                        // nome e idade do paciente
                        Text(
                            text       = "António Ferreira  72 anos",
                            fontSize   = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // o texto e a cor mudam conforme o estado do António
                        // ----- há uma toma a vermelho -----
                        if (tomaAtrasada != null) {
                            // medicamento em falta
                            val medicamento = DadosApp.medicamentoDaToma(tomaAtrasada)
                            // qual a toma em falta
                            Text(
                                text     = "Toma em atraso: " + medicamento.nome + " " + medicamento.dosagem,
                                color    = Vermelho,
                                fontSize = 14.sp
                            )
                            // há quanto tempo
                            Text(
                                text     = tomaAtrasada.horaPrevista + "  Ha mais de 30 minutos",
                                color    = Color.Gray,
                                fontSize = 13.sp
                            )
                        // ----- todas feitas (verde) -----
                        } else if (todasEmDia) {
                            Text(
                                text     = "Todas as tomas em dia",
                                color    = VerdeTomado,
                                fontSize = 14.sp
                            )
                        // ----- ainda há pendentes (amarelo) -----
                        } else {
                            Text(
                                text     = "Ainda há tomas por tomar",
                                color    = Amarelo,
                                fontSize = 14.sp
                            )
                            Text(
                                text     = DadosApp.tomasFeitas().toString() + " de " + DadosApp.tomas.size + " tomas feitas hoje",
                                color    = Color.Gray,
                                fontSize = 13.sp
                            )
                        }
                    }

                    // bolinha de estado: vermelho se há atraso, verde se tudo feito, senão amarelo
                    val corBolinha = if (tomaAtrasada != null) Vermelho else if (todasEmDia) VerdeTomado else Amarelo
                    // a bolinha colorida
                    Box(
                        modifier = Modifier
                            // tamanho 18dp
                            .size(18.dp)
                            // cor decidida acima, em círculo
                            .background(corBolinha, CircleShape)
                    )
                }
            }

            // espaço antes do botão
            Spacer(modifier = Modifier.height(28.dp))

            // ----- BOTÃO "SIMULAR TOMA IGNORADA" (demo do Cenário 2) -----
            // Simulação (sem AlarmManager), como o enunciado permite:
            //   1. Marca a Sinvastatina das 15:00 como ATRASADA (vermelho)
            //   2. Abre o ecrã de alerta F1 (telemóvel da Carla)
            OutlinedButton(
                // o que acontece ao tocar no botão
                onClick = {
                    // PASSO 1: marca a Sinvastatina das 15:00 como ATRASADA (vermelho)
                    // muda o estado dessa toma para ATRASADO
                    DadosApp.simularTomaIgnorada()

                    // PASSO 2: abre o alerta F1 para essa toma
                    // vai buscar a toma que ficou a vermelho
                    val atrasada = DadosApp.tomaAtrasada()
                    // se a encontrou...
                    if (atrasada != null) {
                        // ===> NAVEGAÇÃO -> F1: "Simular toma ignorada" abre o alerta (a rota muda na MainActivity)
                        aoSimularIgnorada(atrasada.id)
                    }
                },
                modifier = Modifier
                    // a toda a largura
                    .fillMaxWidth()
                    // 56dp > 48dp mínimos de acessibilidade
                    .height(56.dp)
            ) {
                // texto dentro do botão
                Text(
                    text     = "Simular toma ignorada (demo)",
                    // texto a vermelho (ação de alerta)
                    color    = Vermelho,
                    fontSize = 16.sp
                )
            }
        }
    }
}