package com.example.easymed.ecras


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.example.easymed.ui.theme.AzulEasyMed
import com.example.easymed.ui.theme.FundoClaro

// ============================================================
// ECRÃ "PERFIL" (partilhado pelos dois modos)
// Autores: Miguel Pauzinho (27131) e Francisco Soudo (14060)
// ============================================================


@Composable
// função que desenha interface
fun EcraPerfil(aoMudarModo: () -> Unit) {
    // Column = caixa vertical que ocupa o ecrã, com fundo cinzento claro
    Column(
        modifier = Modifier
            // ocupa todo o espaço do ecrã
            .fillMaxSize()
            // fundo cinzento claro
            .background(FundoClaro)
    ) {
        // cabeçalho azul reutilizado
        CabecalhoAzul(titulo = "Perfil", subtitulo = "")

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
            // ----- cartão com a pessoa atual -----
            // Card = cartão com cantos arredondados e ligeira sombra (Material 3)
            Card(
                // a toda a largura
                modifier = Modifier.fillMaxWidth(),
                // cantos arredondados
                shape = RoundedCornerShape(12.dp),
                // fundo branco
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                // Row = caixa HORIZONTAL: coloca o avatar e o texto lado a lado
                Row(
                    // margem interior de 16dp
                    modifier = Modifier.padding(16.dp),
                    // alinha verticalmente ao centro
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // "avatar" simples: círculo azul com um emoji centrado dentro
                    Box(
                        modifier = Modifier
                            // círculo de 56x56dp
                            .size(56.dp)
                            // fundo azul em forma de círculo
                            .background(AzulEasyMed, CircleShape),
                        // centra o emoji dentro do círculo
                        contentAlignment = Alignment.Center
                    ) {
                        // o emoji muda conforme o modo atual (cuidadora ou paciente).
                        // "if (...) A else B" devolve um valor (em Kotlin o if é uma expressão).
                        // emoji conforme o modo
                        val emojiPerfil = if (DadosApp.modoCuidador) "👩‍⚕️" else "👴"
                        // mostra o emoji escolhido
                        Text(
                            text = emojiPerfil,
                            // tamanho grande
                            fontSize = 26.sp
                        )
                    }
                    // espaço horizontal entre avatar e texto
                    Spacer(modifier = Modifier.width(14.dp))
                    // Column interna: empilha o nome e as descrições da pessoa
                    Column {
                        // mostra dados diferentes conforme o modo (cuidadora vs paciente)
                        // ----- dados da Carla (cuidadora) -----
                        if (DadosApp.modoCuidador) {
                            // nome da cuidadora
                            Text(
                                text = "Carla Sousa",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            // papel dela
                            Text(
                                text = "Cuidadora (filha do António) - Enfermeira",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                            // quem ela acompanha
                            Text(
                                text = "A acompanhar: António Ferreira",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        // ----- dados do António (paciente) -----
                        } else {
                            // nome do paciente
                            Text(
                                text = "António Ferreira",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            // idade
                            Text(
                                text = "72 anos",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                            // resumo da medicação
                            Text(
                                text = "4 medicamentos por dia",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // espaço antes do botão
            Spacer(modifier = Modifier.height(20.dp))

            // ----- botão de troca de modo (a "simulação" da ligação) -----
            // No protótipo, em vez de dois telemóveis ligados por um servidor,
            // trocamos de perfil aqui. onClick = aoMudarModo: a MainActivity
            // inverte DadosApp.modoCuidador e leva ao ecrã inicial desse modo.
            Button(
                // ===> NAVEGAÇÃO: vai para Início (modo cuidador) ou Hoje (modo paciente), conforme o modo
                onClick = aoMudarModo,
                modifier = Modifier
                    // a toda a largura
                    .fillMaxWidth()
                    // alvo de toque grande (acessibilidade)
                    .height(56.dp)
            ) {
                // o texto do botão muda conforme o modo em que estamos agora
                // texto conforme o modo
                val textoBotao = if (DadosApp.modoCuidador) "Mudar para o modo Paciente (António)" else "Mudar para o modo Cuidador (Carla)"
                // mostra o texto escolhido
                Text(
                    text = textoBotao,
                    fontSize = 16.sp
                )
            }

        }
    }
}
