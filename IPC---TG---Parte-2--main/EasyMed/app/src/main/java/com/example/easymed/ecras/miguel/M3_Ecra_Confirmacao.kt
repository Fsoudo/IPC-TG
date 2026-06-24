package com.example.easymed.ecras.miguel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.example.easymed.ui.theme.CinzentoPendente
import com.example.easymed.ui.theme.VerdeEscuroVisto

// ============================================================
// ECRÃ M3 - CONFIRMAÇÃO DA TOMA (Cenário 1)
// Autor: Miguel Pauzinho (27131)
// ============================================================


@Composable
// função que desenha interface
fun EcraConfirmacao(tomaId: Int, aoVerResumo: () -> Unit) {


    // ----- dados que este ecrã precisa de mostrar -----

    // 1) com o id (vem do M2), encontra a Toma que acabou de ser confirmada.
    val toma = DadosApp.tomaPorId(tomaId)

    // 2) a partir do medicamentoId da Toma, vai buscar o Medicamento
    //    (nome, dosagem) para o mostrar no ecrã de sucesso.
    val medicamento = DadosApp.medicamentoDaToma(toma)

    // 3) procura a próxima toma que ainda falta fazer hoje (pode não haver).
    val proxima = DadosApp.proximaTomaPendente()


    // Column = caixa VERTICAL: empilha os filhos de cima para baixo
    Column(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // fundo branco (ecrã de sucesso)
            .background(Color.White)
            // não deixa o conteúdo por baixo da barra do sistema
            .navigationBarsPadding()
    ) {
        // cabeçalho azul reutilizado (componente de Componentes.kt)
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        // segunda Column: o conteúdo central, com scroll e centrado na horizontal
        Column(
            modifier = Modifier
                // ocupa o espaço disponível
                .fillMaxSize()
                // permite rolar em ecrãs pequenos
                .verticalScroll(rememberScrollState())
                // margem interior de 24dp
                .padding(24.dp),
            // tudo centrado ao meio na horizontal
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // espaço no topo para "empurrar" o conteúdo
            Spacer(modifier = Modifier.height(32.dp))

            // o grande visto verde do protótipo M3.
            // Box = caixa que SOBREPÕE os filhos; aqui um círculo verde
            // com o ícone do visto centrado dentro.
            Box(
                modifier = Modifier
                    // círculo de 120x120dp
                    .size(120.dp)
                    // fundo verde em forma de círculo
                    .background(VerdeEscuroVisto, CircleShape),
                // centra o ícone dentro da Box
                contentAlignment = Alignment.Center
            ) {
                // o ícone do visto, dentro do círculo
                Icon(
                    // o desenho do "visto"
                    imageVector = Icons.Filled.Check,
                    // texto para o TalkBack
                    contentDescription = "Toma confirmada com sucesso",
                    // pinta o ícone de branco
                    tint = Color.White,
                    // tamanho do ícone 64dp
                    modifier = Modifier.size(64.dp)
                )
            }

            // espaço abaixo do visto
            Spacer(modifier = Modifier.height(16.dp))
            // título grande de sucesso (fontWeight.Bold = negrito)
            Text(
                // mensagem de sucesso
                text = "Toma Registada!",
                // tamanho grande
                fontSize = 28.sp,
                // a negrito
                fontWeight = FontWeight.Bold
            )

            // espaço
            Spacer(modifier = Modifier.height(12.dp))
            // nome + dosagem do medicamento confirmado (ex.: "Amlodipina 5mg")
            Text(
                text = medicamento.nome + " " + medicamento.dosagem,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            // espaço antes da "próxima toma"
            Spacer(modifier = Modifier.height(28.dp))

            // próxima toma: poupa memória ao utilizador (Nielsen: reconhecimento
            // em vez de memorização - o António não tem de decorar o que vem a seguir)
            Text(
                // rótulo "Próxima toma"
                text = "Próxima toma",
                // cinzento (rótulo secundário)
                color = CinzentoPendente,
                fontSize = 14.sp
            )
            // "if/else": se ainda houver próxima toma mostra-a; senão, mensagem final
            if (proxima != null) {
                // medicamento da próxima toma
                val proximoMedicamento = DadosApp.medicamentoDaToma(proxima)
                // mostra nome + dosagem + hora da próxima
                Text(
                    text = proximoMedicamento.nome + " " + proximoMedicamento.dosagem + "  " + proxima.horaPrevista,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                // mensagem final de dia concluído
                Text(
                    text = "Não há mais tomas hoje 🎉",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // espaço antes do botão
            Spacer(modifier = Modifier.height(40.dp))

            // botão único e grande para voltar ao resumo do dia (ecrã M4)
            Button(
                // ===> NAVEGAÇÃO -> M4 (Hoje): "Ver resumo do dia" (a rota muda na MainActivity)
                onClick = aoVerResumo,
                modifier = Modifier
                    // botão a toda a largura (fácil de acertar)
                    .fillMaxWidth()
                    // acessibilidade: alvo de toque grande (>48dp)
                    .height(56.dp)
            ) {
                // texto dentro do botão
                Text(text = "Ver resumo do dia", fontSize = 17.sp)
            }
        }
    }
}