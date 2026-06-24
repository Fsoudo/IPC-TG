package com.example.easymed


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easymed.ui.theme.AmareloAtraso
import com.example.easymed.ui.theme.AzulEasyMed
import com.example.easymed.ui.theme.CinzentoFuturo
import com.example.easymed.ui.theme.CinzentoPendente
import com.example.easymed.ui.theme.VerdeTomado
import com.example.easymed.ui.theme.VermelhoIgnorado

// ============================================================
// COMPONENTES REUTILIZADOS NOS VÁRIOS ECRÃS (TG2)
// Autores: Miguel Pauzinho (27131) e Francisco Soudo (14060)
//
//
//
// Em vez de repetir o mesmo código em todos os ecrãs, criamos aqui
// peças de interface que os dois cenários usam. Isto também garante
// CONSISTÊNCIA visual (outra heurística de Nielsen).
//
//
// ============================================================


// ============================================================
// CABEÇALHO AZUL usado no topo de quase todos os ecrãs.
//
// O fundo azul prolonga-se por trás da barra de estado do Android
// (o statusBarsPadding empurra apenas o TEXTO para baixo). Recebe um
// título grande, um subtítulo pequeno (pode ser "" para não aparecer)
// e, se aoVoltar não for null, mostra uma seta de "voltar".
// ============================================================
@Composable
// função que desenha interface
fun CabecalhoAzul(titulo: String, subtitulo: String, aoVoltar: (() -> Unit)? = null) {
    // Box = caixa que serve de fundo azul
    Box(
        modifier = Modifier
            // ocupa toda a largura do ecrã
            .fillMaxWidth()
            // pinta o fundo de azul da marca
            .background(AzulEasyMed)
    ) {
        // Row = põe a seta e o texto lado a lado
        Row(
            modifier = Modifier
                // empurra o conteúdo para baixo da barra de estado
                .statusBarsPadding()
                // margem interior (8dp lados, 12dp cima/baixo)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            // alinha verticalmente ao centro
            verticalAlignment = Alignment.CenterVertically
        ) {
            // seta de voltar (só aparece nos ecrãs de detalhe)
            // só desenha a seta se recebermos a ação de voltar
            if (aoVoltar != null) {
                // botão com ícone; ao tocar chama aoVoltar
                IconButton(onClick = aoVoltar) {
                    // desenha o ícone da seta
                    Icon(
                        // seta "para trás" (vira em RTL)
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        // para o TalkBack
                        contentDescription = "Voltar ao ecra anterior",
                        // pinta a seta de branco
                        tint = Color.White
                    )
                }
            // se não há ação de voltar...
            } else {
                // ...deixa só um pequeno espaço no lugar da seta
                Spacer(modifier = Modifier.width(8.dp))
            }
            // Column = empilha o título e o subtítulo
            Column {
                // o título grande
                Text(
                    // texto recebido por parâmetro
                    text = titulo,
                    // branco (contrasta com o azul)
                    color = Color.White,
                    // tamanho grande
                    fontSize = 22.sp,
                    // a negrito
                    fontWeight = FontWeight.Bold
                )
                // só mostra o subtítulo se não estiver vazio
                if (subtitulo != "") {
                    // o subtítulo pequeno
                    Text(
                        // texto recebido por parâmetro
                        text = subtitulo,
                        // branco com transparência (CC = ~80%)
                        color = Color(0xCCFFFFFF),
                        // tamanho pequeno
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


// ============================================================
// ÍCONE DE ESTADO de uma toma com o código de cores do TG1.
//
// É usado no ecrã "Hoje" do paciente e no detalhe do cuidador, para
// o estado se ler "de relance" sem ser preciso ler texto. Recebe o
// estado da toma (TOMADO, PENDENTE, ATRASADO).
// ============================================================
@Composable
// função que desenha interface
fun IconeEstado(estado: EstadoToma) {
    // a cada estado corresponde uma COR e uma DESCRIÇÃO; a descrição é lida
    // pelo leitor de ecrã TalkBack (para não depender só da cor - acessibilidade)
    // cor da bola (decidida abaixo)
    val cor: Color
    // texto para o TalkBack
    val descricao: String
    // escolhe cor + descrição conforme o estado
    when (estado) {
        // verde - tomado a horas
        EstadoToma.TOMADO   -> { cor = VerdeTomado;      descricao = "Toma confirmada" }
        // cinzento - ainda pendente
        EstadoToma.PENDENTE -> { cor = CinzentoPendente; descricao = "Toma pendente" }
        // amarelo - registada com atraso pelo cuidador
        EstadoToma.ATRASO   -> { cor = AmareloAtraso;    descricao = "Toma em atraso" }
        // vermelho - ignorada (passaram +30 min)
        EstadoToma.IGNORADO -> { cor = VermelhoIgnorado; descricao = "Toma ignorada" }
    }
    // a "bola" de estado: um círculo CHEIO com a cor do estado
    Box(
        modifier = Modifier
            // tamanho da bola
            .size(28.dp)
            // círculo cheio com a cor do estado
            .background(cor, CircleShape)
            // texto que o TalkBack lê
            .semantics { contentDescription = descricao }
    )
}


// ============================================================
// CARTÃO BRANCO com a informação de UMA toma (hora, nome, dosagem
// e estado).
//
// Usado na lista do ecrã "Hoje" (Miguel/M4) e no detalhe do cuidador
// (Francisco/F3) - o aspeto é igual nos dois para haver consistência.
// Recebe a toma a mostrar no cartão.
// ============================================================
@Composable
// função que desenha interface
fun CartaoToma(toma: Toma) {
    // descobre o medicamento desta toma (nome, dosagem, forma)
    // busca o medicamento ligado a esta toma
    val medicamento = DadosApp.medicamentoDaToma(toma)

    // Card = cartão branco com cantos arredondados (Material 3)
    Card(
        // o cartão ocupa toda a largura
        modifier = Modifier.fillMaxWidth(),
        // cantos arredondados de 12dp
        shape = RoundedCornerShape(12.dp),
        // fundo branco
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        // Row = coloca o texto (esquerda) e o ícone de estado (direita) lado a lado
        Row(
            // margem interior de 16dp
            modifier = Modifier.padding(16.dp),
            // alinha ao centro na vertical
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Column com weight(1f) = "estica" e empurra o ícone para a direita,
            // ocupando todo o espaço que sobra à esquerda
            // ocupa o espaço livre à esquerda
            Column(modifier = Modifier.weight(1f)) {
                // 1a linha: a hora prevista
                Text(
                    // ex: "09:00"
                    text = toma.horaPrevista,
                    // cinzento (informação secundária)
                    color = CinzentoPendente,
                    // tamanho pequeno
                    fontSize = 14.sp
                )
                // 2a linha: nome + dosagem
                Text(
                    // ex: "Amlodipina 5mg"
                    text = medicamento.nome + " " + medicamento.dosagem,
                    // tamanho maior (informação principal)
                    fontSize = 18.sp,
                    // a negrito
                    fontWeight = FontWeight.Bold
                )
                // linha de baixo: quantidade + hora real (se já foi confirmada).
                // o texto e a cor mudam conforme o estado da toma (when = "switch").
                // texto da 3a linha (decidido abaixo)
                val textoBaixo: String
                // cor da 3a linha (decidida abaixo)
                val corBaixo: Color
                // escolhe texto e cor conforme o estado
                when (toma.estado) {
                    // TOMADO (verde) - confirmada a horas
                    EstadoToma.TOMADO -> {
                        textoBaixo = "1 " + medicamento.forma + " - tomada às " + toma.horaConfirmada
                        corBaixo = VerdeTomado
                    }
                    // PENDENTE (cinzento) - ainda por tomar
                    EstadoToma.PENDENTE -> {
                        textoBaixo = "1 " + medicamento.forma
                        corBaixo = CinzentoPendente
                    }
                    // ATRASO (amarelo) - o cuidador registou fora de horas
                    EstadoToma.ATRASO -> {
                        textoBaixo = "1 " + medicamento.forma + " - registada com atraso às " + toma.horaConfirmada
                        corBaixo = AmareloAtraso
                    }
                    // IGNORADO (vermelho) - passaram +30 min sem ação
                    EstadoToma.IGNORADO -> {
                        textoBaixo = "1 " + medicamento.forma + " - ignorada (+30 min)"
                        corBaixo = VermelhoIgnorado
                    }
                }
                // desenha a 3a linha decidida acima
                Text(text = textoBaixo, color = corBaixo, fontSize = 14.sp)
            }
            // à direita: o ícone colorido do estado
            IconeEstado(toma.estado)
        }
    }
}


// ============================================================
// FILA COM OS 7 QUADRADOS DA SEMANA (Seg..Dom), como no protótipo F4.
//
// As cores são determinadas por corDoDia(): dias passados ficam a verde
// (dados de exemplo, como o enunciado permite), hoje reflete o estado
// real das tomas, e dias futuros ficam cinzento claro.
// ============================================================
@Composable
// função que desenha interface
fun QuadradosDaSemana() {
    // nomes dos 7 dias
    val nomesDias = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")

    Row(
        // ocupa toda a largura
        modifier = Modifier.fillMaxWidth(),
        // espaço igual entre os quadrados
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // um quadrado por dia (Seg..Dom)
        for (indice in 0..6) {
            // empilha quadrado + nome, centrados
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // o quadrado colorido do dia
                Box(
                    modifier = Modifier
                        // tamanho do quadrado
                        .size(width = 40.dp, height = 36.dp)
                        // cor dinâmica: verde (passado), estado real (hoje), cinzento (futuro)
                        .background(corDoDia(indice), RoundedCornerShape(8.dp))
                )
                // espaço entre o quadrado e o nome
                Spacer(modifier = Modifier.height(4.dp))
                // nome do dia
                Text(text = nomesDias[indice], fontSize = 12.sp, color = CinzentoPendente)
            }
        }
    }
}

// Escolhe a cor do quadrado de um dado dia da semana (índice 0=Seg, 6=Dom).
// Dias passados: sempre verde (dados de exemplo); hoje: reflete o estado real;
// dias futuros: cinzento claro (sem dados).
fun corDoDia(indice: Int): androidx.compose.ui.graphics.Color {
    val hoje = DadosApp.indiceDiaDaSemana()
    return when {
        indice < hoje -> VerdeTomado      // dia passado (exemplo: tudo tomado)
        indice > hoje -> CinzentoFuturo   // dia futuro (sem dados ainda)
        // hoje: vermelho se ignorada, amarelo se com atraso, verde se tudo feito
        DadosApp.tomaIgnorada() != null  -> VermelhoIgnorado
        DadosApp.tomaComAtraso() != null -> AmareloAtraso
        else                             -> VerdeTomado
    }
}