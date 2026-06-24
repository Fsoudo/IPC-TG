package com.example.easymed.ecras.francisco

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.CabecalhoAzul
import com.example.easymed.CartaoToma
import com.example.easymed.DadosApp
import com.example.easymed.ui.theme.AmareloAtraso
import com.example.easymed.ui.theme.FundoClaro
import com.example.easymed.ui.theme.VermelhoIgnorado

// ============================================================
// ECRA F3 - DETALHE DAS TOMAS DO ANTONIO (Cenario 2)
// Autor: Francisco Soudo (14060)
//
// A Carla ve aqui as tomas do dia do pai, uma a uma, com o
// codigo de cores. Tem duas acoes importantes:
//
//   1. "Ligar ao António": abre o marcador de telefone do Android
//      com o numero ja escrito (Intent ACTION_DIAL, nao precisa de
//      permissoes especiais).
//
//   2. "Forçar Registo da Toma": e a 2.a funcionalidade do Cenario 2.
//      Depois de falar com o pai ao telefone, a Carla regista
//      manualmente a toma que estava IGNORADA (vermelha). O estado
//      passa a ATRASO (amarelo) - nao a verde, porque foi feita fora
//      de horas. Este botao SO aparece quando existe uma toma ignorada.
//
// Prevencao de erros (heuristica de Nielsen): registar uma toma de
// outra pessoa e uma acao com peso clinico, por isso pedimos uma
// CONFIRMACAO EXTRA num dialogo antes de a gravar (evita o "fat-finger",
// o toque acidental). E a mesma ideia do "swipe de confirmacao" que
// estava previsto no prototipo do TG1.
// ============================================================

@Composable
fun EcraDetalheTomas(aoVoltar: () -> Unit, aoVerHistorico: () -> Unit) {
    val contexto = LocalContext.current

    // toma que esta IGNORADA (vermelha), se existir. E a que a Carla
    // vai querer "forcar". Se nao houver nenhuma, vale null e o botao
    // "Forçar Registo" nem aparece.
    val tomaIgnorada = DadosApp.tomaIgnorada()

    // VARIAVEL DE ESTADO: controla se o dialogo de confirmacao esta
    // aberto ou fechado. Comeca em "false" (fechado).
    //   - "remember" faz a variavel sobreviver aos redesenhos do ecra
    //   - "by mutableStateOf" faz o Compose redesenhar quando ela muda
    // Quando vale true, o AlertDialog (mais abaixo) aparece por cima do ecra.
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoClaro)
            .navigationBarsPadding()
    ) {
        CabecalhoAzul(
            titulo = "António Ferreira",
            subtitulo = DadosApp.dataDeHoje(),
            aoVoltar = aoVoltar
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // lista das tomas do dia (mesmo cartao usado no ecra do paciente,
            // para haver consistencia entre os dois lados da app)
            for (toma in DadosApp.tomas) {
                CartaoToma(toma)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ---------- atalho para ligar ao pai ----------
            // ACTION_DIAL abre o telefone com o numero preenchido;
            // a chamada so acontece se a Carla carregar em "ligar" -
            // mais uma vez, prevencao de erros
            Button(
                onClick = {
                    val intencao = Intent(Intent.ACTION_DIAL, Uri.parse("tel:962000111"))
                    contexto.startActivity(intencao)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = null // o texto ao lado ja descreve o botao
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Ligar ao António", fontSize = 17.sp)
            }

            // ---------- FORÇAR REGISTO (so aparece se houver toma ignorada) ----------
            // FUNCIONALIDADE DO CENARIO 2 (parte 2): depois da chamada, a Carla
            // marca a toma como feita (com atraso). O "if" garante que o botao
            // so existe quando faz sentido - quando ha mesmo uma toma a vermelho.
            if (tomaIgnorada != null) {
                Spacer(modifier = Modifier.height(12.dp))

                // OutlinedButton (contorno) com cor vermelha: e uma acao
                // de excecao/atencao, por isso destaca-se da cor azul normal.
                OutlinedButton(
                    onClick = { mostrarDialogo = true }, // abre o dialogo (nao regista ainda!)
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = VermelhoIgnorado)
                ) {
                    Text(text = "Forçar registo da toma", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = aoVerHistorico,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Ver histórico semanal", fontSize = 15.sp)
            }
        }
    }

    // ============================================================
    // DIALOGO DE CONFIRMACAO (prevencao de erros)
    //
    // So e desenhado quando "mostrarDialogo" e true E ainda ha uma
    // toma ignorada. O AlertDialog do Material 3 ja traz o aspeto
    // tipico de uma caixa de confirmacao do Android:
    //   - confirmButton: a acao que avanca ("Confirmar registo")
    //   - dismissButton: a acao que cancela ("Cancelar")
    // ============================================================
    if (mostrarDialogo && tomaIgnorada != null) {
        val medicamento = DadosApp.medicamentoDaToma(tomaIgnorada)

        AlertDialog(
            // tocar fora do dialogo ou no "X" do sistema = cancelar
            onDismissRequest = { mostrarDialogo = false },
            title = {
                Text(text = "Forçar registo da toma?", fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    text = "Vais registar a toma de " + medicamento.nome + " " +
                        medicamento.dosagem + " como feita com atraso. " +
                        "Confirma apenas depois de teres a certeza (ex.: falaste com o António)."
                )
            },
            confirmButton = {
                // BOTAO QUE CONFIRMA: aqui sim chamamos a funcionalidade.
                TextButton(
                    onClick = {
                        // chama a funcionalidade: estado IGNORADO -> ATRASO (amarelo)
                        DadosApp.forcarRegisto(tomaIgnorada.id)
                        mostrarDialogo = false // fecha o dialogo
                        // feedback imediato para a Carla saber que resultou
                        Toast.makeText(
                            contexto,
                            "Toma registada com atraso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text(text = "Confirmar registo", color = AmareloAtraso, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                // BOTAO QUE CANCELA: so fecha o dialogo, nao altera nada.
                TextButton(onClick = { mostrarDialogo = false }) {
                    Text(text = "Cancelar", color = Color.Gray)
                }
            }
        )
    }
}
