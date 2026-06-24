package com.example.easymed


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.easymed.ecras.EcraPerfil
import com.example.easymed.ecras.francisco.EcraAlertaCritico
import com.example.easymed.ecras.francisco.EcraDashboard
import com.example.easymed.ecras.francisco.EcraDetalheTomas
import com.example.easymed.ecras.francisco.EcraHistoricoSemanal
import com.example.easymed.ecras.miguel.EcraBloqueado
import com.example.easymed.ecras.miguel.EcraConfirmacao
import com.example.easymed.ecras.miguel.EcraHoje
import com.example.easymed.ecras.miguel.EcraNotificacao
import com.example.easymed.ui.theme.EasyMedTheme

// ============================================================
// EASYMED - TG2 de IPC (2025/2026)
// Equipa 14: Miguel Pauzinho (27131) e Francisco Soudo (14060)
//
//
//
// Este ficheiro contém:
//   - MainActivity: o ponto de entrada da app
//   - AppEasyMed: o composable "raiz" que gere a navegação
//   - BarraDeNavegacao: a barra de botões na parte de baixo
//
// NOTA SOBRE OS ALARMES (defesa):
//   O enunciado pede para "ilustrar o funcionamento geral mesmo que
//   alguns aspetos não estejam totalmente implementados". Por isso
//   os lembretes/alertas são SIMULADOS: os botões "Simular Alarme"
//   (paciente) e "Simular toma ignorada" (cuidadora) abrem os ecrãs
//   bloqueados (M1/M2 e F1) diretamente, sem AlarmManager nem
//   notificações reais do sistema. Mantém a demo e os testes fiáveis.
//
//
// ============================================================

// ============================================================
// MainActivity é a "porta de entrada" de qualquer app Android.
// Estende ComponentActivity (classe base para apps com Compose).
// ============================================================
// a Activity principal da app
class MainActivity : ComponentActivity() {

    // onCreate() é chamado pelo Android quando a app é iniciada.
    // método de arranque da Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        // chama o onCreate da classe mãe (obrigatório)
        super.onCreate(savedInstanceState)

        // enableEdgeToEdge() faz a app desenhar por trás das barras do sistema.
        // app desenha de ponta a ponta do ecrã
        enableEdgeToEdge()

        // setContent define o que a app mostra no ecrã.
        // define a interface (em Compose)
        setContent {
            // aplica o tema visual da EasyMed
            EasyMedTheme {
                // chama o composable principal da app
                AppEasyMed()
            }
        }
    }
}

// ============================================================
// COMPOSABLE RAIZ - NAVEGAÇÃO DA APP
//
// Este composable é o "maestro" que decide qual ecrã mostrar.
//
// Como funciona a navegação:
//   - Guardamos o nome do ecrã atual numa variável de estado
//     chamada "ecraAtual" (ex: "hoje", "inicio", etc.)
//   - Um "when" (como um switch) escolhe qual composable desenhar
//   - Para mudar de ecrã, basta mudar o valor de "ecraAtual"
//   - O Compose redesenha a app sozinho quando a variável muda
// ============================================================
@Composable
// função que desenha interface
fun AppEasyMed() {

    // -------------------------------------------------------
    // VARIÁVEL DE ESTADO DA NAVEGAÇÃO
    //
    // ecraAtual: o nome do ecrã que está a ser mostrado agora.
    //   Valor inicial "hoje" = o app abre no ecrã do paciente.
    // -------------------------------------------------------
    // ecrã mostrado agora (começa no "hoje")
    var ecraAtual by remember { mutableStateOf("hoje") }

    // -------------------------------------------------------
    // ESCOLHA DO ECRÃ A MOSTRAR
    //
    // Há dois tipos de ecrãs:
    //   - Ecrãs "a ecrã inteiro": sem barra de navegação
    //     (ecrãs bloqueados M1/M2/F1, confirmação M3, detalhe F3,
    //      histórico F4)
    //   - Ecrãs "normais": com a barra de navegação em baixo
    //     (Hoje, Inicio, Perfil - apanhados pelo "else")
    // -------------------------------------------------------
    // escolhe o que desenhar conforme o ecrã atual
    when (ecraAtual) {

        // --------------------------------------------------
        // CENÁRIO 1 (Miguel) - ecrãs bloqueados simulados + confirmação
        // --------------------------------------------------

        // M1: ecrã bloqueado (relógio + cartão da notificação)
        // mostra o ecrã bloqueado M1
        "m1_bloqueado" -> EcraBloqueado(
            // qual a toma do lembrete
            tomaId = DadosApp.tomaSelecionadaId,
            // tocar -> abre o M2
            aoAbrirNotificacao = { ecraAtual = "m2_notificacao" }
        )

        // M2: notificação expandida com "Tomei" / "Adiar 15 min"
        // mostra a notificação expandida M2
        "m2_notificacao" -> EcraNotificacao(
            // qual a toma
            tomaId = DadosApp.tomaSelecionadaId,
            // ação do botão "Tomei"
            aoTomei = {
                // FUNCIONALIDADE DO CENÁRIO 1: regista a toma (verde) e vai ao M3
                // marca a toma como TOMADO (verde)
                DadosApp.confirmarToma(DadosApp.tomaSelecionadaId)
                // avança para o ecrã de confirmação
                ecraAtual = "m3_confirmacao"
            },
            // ação do botão "Adiar 15 min"
            aoAdiar = {
                // adia 15 min e volta ao ecrã principal
                // empurra a hora 15 min para a frente
                DadosApp.adiarToma(DadosApp.tomaSelecionadaId)
                // volta ao ecrã "Hoje"
                ecraAtual = "hoje"
            }
        )

        // M3: confirmação com o grande visto verde
        // mostra o ecrã de confirmação M3
        "m3_confirmacao" -> EcraConfirmacao(
            // a toma que acabou de ser confirmada
            tomaId = DadosApp.tomaSelecionadaId,
            // "Ver resumo" -> volta ao "Hoje"
            aoVerResumo = { ecraAtual = "hoje" }
        )

        // --------------------------------------------------
        // CENÁRIO 2 (Francisco) - lado da cuidadora Carla
        // --------------------------------------------------

        // F1: ecrã de alerta bloqueado (toma ignorada)
        // mostra o alerta bloqueado F1 - a toma ignorada é lida diretamente de DadosApp.tomaIgnorada()
        "f1_alerta" -> EcraAlertaCritico(
            // "Ver detalhe" -> F3
            aoVerDetalhe = { ecraAtual = "f3_detalhe" }
        )

        // F3: detalhe das tomas do António (com botão "Forçar Registo")
        // mostra o detalhe das tomas F3
        "f3_detalhe" -> EcraDetalheTomas(
            // seta de voltar -> dashboard
            aoVoltar      = { ecraAtual = "inicio" },
            // "Ver histórico" -> F4
            aoVerHistorico = { ecraAtual = "f4_semana" }
        )

        // F4: histórico semanal do António
        // mostra o histórico semanal F4
        "f4_semana" -> EcraHistoricoSemanal(
            // seta de voltar -> detalhe F3
            aoVoltar = { ecraAtual = "f3_detalhe" }
        )

        // --------------------------------------------------
        // ECRÃS "NORMAIS" (com barra de navegação em baixo)
        // --------------------------------------------------
        // qualquer outro ecrã (hoje/inicio/perfil)
        else -> {
            // estrutura com barra inferior (Material 3)
            Scaffold(
                // a parte de baixo do Scaffold
                bottomBar = {
                    // a nossa barra de navegação
                    BarraDeNavegacao(
                        // diz-lhe qual o ecrã selecionado
                        ecraAtual = ecraAtual,
                        // ao tocar num botão, troca de ecrã
                        aoNavegar = { destino -> ecraAtual = destino }
                    )
                }
            // espaço que o Scaffold reserva (para não tapar)
            ) { espacoInterior ->
                // caixa que contém o ecrã atual
                Box(
                    // afasta o conteúdo da barra de baixo
                    modifier = Modifier.padding(
                        // exatamente a altura da barra
                        bottom = espacoInterior.calculateBottomPadding()
                    )
                ) {
                    // escolhe o ecrã "normal" a mostrar
                    when (ecraAtual) {

                        // ---- PACIENTE (António) ----
                        // "Simular Alarme" abre o ecrã bloqueado M1 (simulação).
                        // ecrã "Hoje" do paciente
                        "hoje" -> EcraHoje(
                            // ação do botão "Simular Alarme": guarda a próxima toma pendente e abre M1
                            aoSimularAlarme = {
                                val proxima = DadosApp.proximaTomaPendente()
                                if (proxima != null) {
                                    DadosApp.tomaSelecionadaId = proxima.id
                                }
                                ecraAtual = "m1_bloqueado"
                            }
                        )

                        // ---- CUIDADOR (Carla) ----
                        // "Simular toma ignorada" abre o alerta F1 (simulação).
                        // dashboard da cuidadora
                        "inicio" -> EcraDashboard(
                            // tocar no paciente -> F3
                            aoVerDetalhe  = { ecraAtual = "f3_detalhe" },
                            // o EcraDashboard trata do simularTomaIgnorada() e da notificação;
                            // aqui apenas mudamos para o ecrã F1
                            aoSimularAlerta = { ecraAtual = "f1_alerta" }
                        )

                        // ---- PERFIL: partilhado pelos dois modos ----
                        // ecrã de perfil (troca de modo)
                        "perfil" -> EcraPerfil(
                            // ação do botão "Mudar para o modo..."
                            aoMudarModo = {
                                // inverte paciente <-> cuidador
                                DadosApp.modoCuidador = !DadosApp.modoCuidador
                                // se passou a cuidador...
                                if (DadosApp.modoCuidador) {
                                    // ...abre o dashboard
                                    ecraAtual = "inicio"
                                // se passou a paciente...
                                } else {
                                    // ...abre o ecrã "Hoje"
                                    ecraAtual = "hoje"
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// BARRA DE NAVEGAÇÃO INFERIOR
//
// A NavigationBar do Material 3 é a barra de botões no fundo do
// ecrã. É o padrão de navegação do Android. Os separadores mudam
// conforme o modo atual:
//   Paciente: Hoje - Perfil
//   Cuidador: Inicio - Perfil
// Recebe o ecrã aberto agora (ecraAtual) e a função chamada quando
// o utilizador toca num botão (aoNavegar).
// ============================================================
@Composable
// função que desenha interface
fun BarraDeNavegacao(ecraAtual: String, aoNavegar: (String) -> Unit) {
    // a barra de botões do fundo (Material 3)
    NavigationBar {

        // se estamos no modo cuidador...
        if (DadosApp.modoCuidador) {

            // ---- MODO CUIDADOR: 2 separadores ----

            // separador "Inicio"
            NavigationBarItem(
                // fica marcado se o ecrã atual é o "inicio"
                selected  = ecraAtual == "inicio",
                // ao tocar: vai para o "inicio"
                onClick   = { aoNavegar("inicio") },
                // ícone de casa (+ TalkBack)
                icon      = { Icon(Icons.Filled.Home, contentDescription = "Início") },
                // texto por baixo do ícone
                label     = { Text("Início") }
            )
            // separador "Perfil"
            NavigationBarItem(
                // marcado se estamos no "perfil"
                selected  = ecraAtual == "perfil",
                // ao tocar: vai para o "perfil"
                onClick   = { aoNavegar("perfil") },
                // ícone de pessoa
                icon      = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                // texto por baixo do ícone
                label     = { Text("Perfil") }
            )

        // senão (modo paciente)...
        } else {

            // ---- MODO PACIENTE: 2 separadores ----

            // separador "Hoje"
            NavigationBarItem(
                // marcado se estamos no "hoje"
                selected  = ecraAtual == "hoje",
                // ao tocar: vai para o "hoje"
                onClick   = { aoNavegar("hoje") },
                // ícone de casa
                icon      = { Icon(Icons.Filled.Home, contentDescription = "Hoje") },
                // texto por baixo do ícone
                label     = { Text("Hoje") }
            )
            // separador "Perfil"
            NavigationBarItem(
                // marcado se estamos no "perfil"
                selected  = ecraAtual == "perfil",
                // ao tocar: vai para o "perfil"
                onClick   = { aoNavegar("perfil") },
                // ícone de pessoa
                icon      = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                // texto por baixo do ícone
                label     = { Text("Perfil") }
            )
        }
    }
}