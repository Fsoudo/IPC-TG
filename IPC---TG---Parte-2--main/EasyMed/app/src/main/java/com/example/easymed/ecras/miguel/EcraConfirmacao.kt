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
// ECRA M3 - CONFIRMACAO DA TOMA (Cenario 1)
// Autor: Miguel Pauzinho (27131)
// ============================================================

// @param tomaId:     o id da toma que acabou de ser confirmada (vem do M2)
// @param aoVerResumo: acao a executar no botao "Ver resumo do dia"
//                     (definida na MainActivity; volta ao ecra "Hoje")
@Composable
fun EcraConfirmacao(tomaId: Int, aoVerResumo: () -> Unit) {
    // vai buscar a toma pelo id; tomaPorId devolve null se nao existir
    val toma = DadosApp.tomaPorId(tomaId)
    if (toma == null) {
        return // protecao: sem toma valida nao ha nada para desenhar
    }
    // o medicamento associado a esta toma (nome, dosagem, etc.)
    val medicamento = DadosApp.medicamentoDaToma(toma)

    // procura a proxima toma que ainda falta fazer hoje (pode nao haver)
    val proxima = DadosApp.proximaTomaPendente()

    // Column = caixa VERTICAL: empilha os filhos de cima para baixo.
    // O modifier define o aspeto e o comportamento da caixa.
    Column(
        modifier = Modifier
            .fillMaxSize()                 // ocupa o ecra todo
            .background(Color.White)       // fundo branco (ecra de sucesso)
            .navigationBarsPadding()       // nao deixa o conteudo por baixo da barra do sistema
    ) {
        // cabecalho azul reutilizado (componente de Componentes.kt)
        CabecalhoAzul(titulo = "EasyMed", subtitulo = "")

        // segunda Column: o conteudo central, com scroll e centrado na horizontal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // permite rolar em ecras pequenos
                .padding(24.dp),                       // margem interior de 24dp
            horizontalAlignment = Alignment.CenterHorizontally // tudo centrado ao meio
        ) {
            // Spacer = espaco vazio (aqui, 32dp de altura) para "empurrar" o conteudo
            Spacer(modifier = Modifier.height(32.dp))

            // o grande visto verde do prototipo M3.
            // Box = caixa que SOBREPOE os filhos (uns por cima dos outros);
            // aqui e um circulo verde com o icone do visto centrado dentro.
            Box(
                modifier = Modifier
                    .size(120.dp)                              // circulo de 120x120dp
                    .background(VerdeEscuroVisto, CircleShape), // fundo verde em forma de circulo
                contentAlignment = Alignment.Center             // centra o icone dentro da Box
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,           // o desenho do "visto"
                    contentDescription = "Toma confirmada com sucesso", // texto para o TalkBack
                    tint = Color.White,                         // pinta o icone de branco
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            // titulo grande de sucesso (fontWeight.Bold = negrito)
            Text(
                text = "Toma Registada!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))
            // nome + dosagem do medicamento confirmado (ex.: "Amlodipina 5mg")
            Text(
                text = medicamento.nome + " " + medicamento.dosagem,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(28.dp))

            // proxima toma: poupa memoria ao utilizador (Nielsen: reconhecimento
            // em vez de memorizacao - o Antonio nao tem de decorar o que vem a seguir)
            Text(
                text = "Próxima toma",
                color = CinzentoPendente,
                fontSize = 14.sp
            )
            // "if/else": se ainda houver proxima toma mostra-a; senao, mensagem final.
            if (proxima != null) {
                val proximoMedicamento = DadosApp.medicamentoDaToma(proxima)
                Text(
                    text = proximoMedicamento.nome + " " + proximoMedicamento.dosagem + "  " + proxima.horaPrevista,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Não há mais tomas hoje 🎉",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // botao unico e grande para voltar ao resumo do dia (ecra M4).
            // onClick = aoVerResumo: chama a acao recebida da MainActivity.
            Button(
                onClick = aoVerResumo,
                modifier = Modifier
                    .fillMaxWidth()      // botao a toda a largura (facil de acertar)
                    .height(56.dp)       // acessibilidade: alvo de toque grande (>48dp)
            ) {
                Text(text = "Ver resumo do dia", fontSize = 17.sp)
            }
        }
    }
}
