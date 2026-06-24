package com.example.easymed.ecras.francisco


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.easymed.CabecalhoAzul
import com.example.easymed.DadosApp
import com.example.easymed.QuadradosDaSemana
import com.example.easymed.ui.theme.FundoClaro

// ============================================================
// ECRÃ F4 - HISTÓRICO SEMANAL DO ANTÓNIO (Cenário 2)
// Autor: Francisco Soudo (14060)
// ============================================================


@Composable
// função que desenha interface
fun EcraHistoricoSemanal(aoVoltar: () -> Unit) {

    // Column principal: fundo claro e espaço para a barra de navegação do sistema
    Column(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // fundo cinzento claro
            .background(FundoClaro)
            // afasta da barra de navegação do sistema
            .navigationBarsPadding()
    ) {
        // cabeçalho COM seta de voltar (porque passamos aoVoltar)
        CabecalhoAzul(
            // título do cabeçalho
            titulo    = "Histórico - António Ferreira",
            // sem subtítulo
            subtitulo = "",
            // ===> NAVEGAÇÃO: F4 é o fim do fluxo; a seta de voltar regressa ao F3 (não há "próximo ecrã")
            aoVoltar  = aoVoltar
        )

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
            // ----- os 7 quadrados da semana -----
            Card(
                // a toda a largura
                modifier = Modifier.fillMaxWidth(),
                // fundo branco
                colors   = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                // conteúdo do cartão, com margem
                Column(modifier = Modifier.padding(16.dp)) {
                    // componente com os 7 quadrados (Componentes.kt)
                    QuadradosDaSemana()
                }
            }

            // espaço antes da lista de medicamentos
            Spacer(modifier = Modifier.height(20.dp))
            // título da secção
            Text(
                text     = "Medicamentos:",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            // espaço antes dos cartões
            Spacer(modifier = Modifier.height(8.dp))

            // ----- uma linha por medicamento, com a contagem -----
            // ".indices" dá os índices da lista (0,1,2,3). Usamos o índice para
            // ir buscar o medicamento E a contagem/cor correspondentes (mesma posição).
            // percorre os medicamentos por posição
            for (indice in DadosApp.medicamentos.indices) {
                // medicamento desta posição
                val medicamento = DadosApp.medicamentos[indice]
                Card(
                    // cartão a toda a largura
                    modifier = Modifier.fillMaxWidth(),
                    // fundo branco
                    colors   = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        // margem interior
                        modifier          = Modifier.padding(16.dp),
                        // alinha ao centro na vertical
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // nome do medicamento
                        Column(modifier = Modifier.weight(1f)) {
                            // nome + dosagem do medicamento
                            Text(
                                text       = medicamento.nome + " " + medicamento.dosagem,
                                fontSize   = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                // espaço entre cartões
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}