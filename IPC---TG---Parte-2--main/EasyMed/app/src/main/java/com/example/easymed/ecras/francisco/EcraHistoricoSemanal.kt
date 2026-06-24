package com.example.easymed.ecras.francisco

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.easymed.ui.theme.AmareloAtraso
import com.example.easymed.ui.theme.FundoClaro
import com.example.easymed.ui.theme.VerdeTomado
import com.example.easymed.ui.theme.VermelhoIgnorado

// ============================================================
// ECRA F4 - HISTORICO SEMANAL DO ANTONIO (Cenario 2)
// Autor: Francisco Soudo (14060)
// ============================================================

// @param aoVoltar: acao da seta de "voltar" no cabecalho (regressa ao F3)
@Composable
fun EcraHistoricoSemanal(aoVoltar: () -> Unit) {

    // contagens de exemplo da semana, uma por medicamento
    // (na mesma ordem da lista DadosApp.medicamentos).
    // "listOf" cria uma lista FIXA (so para leitura).
    // Sinvastatina (3.a posicao) tem a pior adesao, pois e o medicamento do cenario 2.
    val contagensSemana = listOf("7/7", "6/7", "3/7", "6/7")
    val coresContagens  = listOf(VerdeTomado, VerdeTomado, VermelhoIgnorado, VerdeTomado)

    // Column principal: fundo claro e espaco para a barra de navegacao do sistema
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoClaro)
            .navigationBarsPadding()
    ) {
        // cabecalho COM seta de voltar (porque passamos aoVoltar != null)
        CabecalhoAzul(
            titulo    = "Histórico - António Ferreira",
            subtitulo = "",
            aoVoltar  = aoVoltar
        )

        // conteudo com scroll e margem de 16dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // ---------- os 7 quadrados da semana ----------
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(12.dp),
                colors   = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    QuadradosDaSemana()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text     = "Medicamentos:",
                fontSize = 16.sp,
                color    = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))

            // ---------- uma linha por medicamento, com a contagem ----------
            // ".indices" da os indices da lista (0,1,2,3). Usamos o indice para
            // ir buscar o medicamento E a contagem/cor correspondentes (mesma posicao).
            for (indice in DadosApp.medicamentos.indices) {
                val medicamento = DadosApp.medicamentos[indice]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier          = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text       = medicamento.nome + " " + medicamento.dosagem,
                                fontSize   = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text     = contagensSemana[indice] + " tomas",
                                color    = coresContagens[indice],
                                fontSize = 14.sp
                            )
                        }
                        // bolinha com a "nota" do medicamento na semana
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(coresContagens[indice], CircleShape)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
