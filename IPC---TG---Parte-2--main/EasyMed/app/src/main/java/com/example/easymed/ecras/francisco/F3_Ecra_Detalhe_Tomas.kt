package com.example.easymed.ecras.francisco


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.CartaoToma
import com.example.easymed.DadosApp
import com.example.easymed.ui.theme.FundoClaro

// ============================================================
// ECRÃ F3 - DETALHE DAS TOMAS DO ANTÓNIO (Cenário 2)
// Autor: Francisco Soudo (14060)
// ============================================================


@Composable
// função que desenha interface
fun EcraDetalheTomas(aoVoltar: () -> Unit, aoVerHistorico: () -> Unit) {
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
            // nome do paciente no cabeçalho
            titulo = "António Ferreira",
            // data fixa (hardcoded)
            subtitulo = "quinta-feira, 25 de junho",
            // passa a ação -> aparece a seta de voltar
            aoVoltar = aoVoltar
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
            // lista das tomas do dia (mesmo cartão usado no ecrã do paciente,
            // para haver consistência entre os dois lados da app)
            // percorre todas as tomas de hoje
            for (toma in DadosApp.tomas) {
                // desenha o cartão desta toma
                CartaoToma(toma)
                // espaço entre cartões
                Spacer(modifier = Modifier.height(8.dp))
            }

            // espaço antes dos botões
            Spacer(modifier = Modifier.height(16.dp))

            // ----- "Ligar ao António" (apenas visual nesta versão) -----
            Button(
                // ===> SEM AÇÃO: botão apenas visual (nesta versão não liga)
                onClick = { },
                modifier = Modifier
                    // a toda a largura
                    .fillMaxWidth()
                    // alvo de toque grande (acessibilidade)
                    .height(56.dp)
            ) {
                // ícone de telefone dentro do botão
                Icon(
                    // desenho do telefone
                    imageVector = Icons.Filled.Phone,
                    // o texto ao lado já descreve o botão
                    contentDescription = null
                )
                // espaço entre o ícone e o texto
                Spacer(modifier = Modifier.width(8.dp))
                // texto do botão
                Text(text = "Ligar ao António", fontSize = 17.sp)
            }

            // espaço antes do link do histórico
            Spacer(modifier = Modifier.height(8.dp))

            // botão "plano" (só texto) para o histórico
            TextButton(
                // ===> NAVEGAÇÃO -> F4: "Ver histórico semanal" (a rota muda na MainActivity)
                onClick = aoVerHistorico,
                modifier = Modifier
                    // a toda a largura
                    .fillMaxWidth()
                    // alvo mínimo de acessibilidade (48dp)
                    .height(48.dp)
            ) {
                // texto do botão
                Text(text = "Ver histórico semanal", fontSize = 15.sp)
            }
        }
    }
}