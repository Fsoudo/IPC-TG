package com.example.easymed


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// ============================================================
// MODELOS DE DADOS DA EASYMED (TG2)
// Autores: Miguel Pauzinho (27131) e Francisco Soudo (14060)
//
//
//
// São as "caixas" onde a app guarda a informação (medicamento, toma).
//
//
// ============================================================

// ============================================================
// ENUM: ESTADO DE UMA TOMA
//
// "enum class" = uma lista fechada de valores possíveis (como um semáforo).
//
// Código de cores (4 estados):
//   PENDENTE  -> ⬜ cinzento  (ainda não está na hora)
//   TOMADO    -> 🟢 verde     (medicamento tomado a horas)
//   ATRASO    -> 🟡 amarelo   (toma registada pelo cuidador com atraso)
//   IGNORADO  -> 🔴 vermelho  (passaram +30 min sem qualquer ação)
// ============================================================
enum class EstadoToma {
    // cinzento - ainda não está na hora
    PENDENTE,
    // verde - tomado a horas
    TOMADO,
    // amarelo - registado com atraso pelo cuidador
    ATRASO,
    // vermelho - ignorado (passaram +30 min)
    IGNORADO
}

// ============================================================
// DATA CLASS: MEDICAMENTO
//
// "data class" = classe simples para guardar dados. O Kotlin cria
// automaticamente equals(), toString() e copy() para nós.
// Todos os campos são "val" (a lista de medicamentos do António é fixa).
// ============================================================
data class Medicamento(
    // número único para identificar este medicamento (1, 2, 3, 4)
    val id: Int,
    // nome do medicamento, ex: "Amlodipina"
    val nome: String,
    // quantidade, ex: "5mg"
    val dosagem: String,
    // tipo, ex: "comprimido", "cápsula", "xarope"
    val forma: String
)
// Nota: a hora da toma vive na classe Toma (horaPrevista), não aqui.

// ============================================================
// DATA CLASS: TOMA
//
// Representa UMA toma agendada para hoje (um medicamento a uma hora).
//
// NOTA COMPOSE: os campos são "val" (imutáveis). Para "alterar" uma
// toma criamos uma CÓPIA com o valor novo (toma.copy(estado = ...)) e
// substituímos a antiga na lista - só assim o Compose redesenha os ecrãs.
// ============================================================
data class Toma(
    // número único da toma (1, 2, 3, 4)
    val id: Int,
    // qual o medicamento (liga ao Medicamento.id)
    val medicamentoId: Int,
    // hora a que devia ser tomada, ex: "09:00"
    val horaPrevista: String,
    // hora real da confirmação ("?" = pode ser null)
    val horaConfirmada: String? = null,
    // estado inicial = PENDENTE
    val estado: EstadoToma = EstadoToma.PENDENTE
)

// ============================================================
// MEMÓRIA CENTRAL DA APLICAÇÃO (TG2)
//
// "object" = singleton = existe UMA SÓ instância em toda a app, por
// isso todos os ecrãs leem e alteram OS MESMOS dados. As variáveis
// observáveis (mutableStateOf / mutableStateListOf) avisam o Compose
// quando mudam, para os ecrãs se redesenharem sozinhos.
//
// Ligação paciente-cuidador (simulada): num produto real seriam dois
// telemóveis ligados por um servidor; no protótipo os dois perfis
// partilham este mesmo DadosApp.
// ============================================================
object DadosApp {

    // MODO ATUAL: false = paciente (António) | true = cuidador (Carla).
    // O utilizador troca de modo no ecrã Perfil.
    var modoCuidador by mutableStateOf(false)

    // TOMA EM FOCO no fluxo a decorrer (alarme do Cenário 1).
    // A MainActivity põe aqui o id antes de abrir o M1.
    var tomaSelecionadaId by mutableIntStateOf(0)

    // MEDICAÇÃO DO ANTÓNIO - lista FIXA com os 4 medicamentos (do TG1).
    val medicamentos = listOf(
        // tensão arterial (09:00)
        Medicamento(1, "Amlodipina",   "5mg",   "comprimido"),
        // diabetes (12:00)
        Medicamento(2, "Metformina",   "500mg", "comprimido"),
        // colesterol (15:00)
        Medicamento(3, "Sinvastatina", "20mg",  "comprimido"),
        // coração (20:00)
        Medicamento(4, "Atenolol",     "50mg",  "comprimido")
    )

    // TOMAS DE HOJE - lista observável (mutableStateListOf).
    // Estado inicial: Amlodipina já tomada (verde); as outras pendentes (cinzento).
    val tomas = mutableStateListOf(
        // Amlodipina - já tomada (verde)
        Toma(1, 1, "09:00", "09:04", EstadoToma.TOMADO),
        // Metformina - pendente (demo do Cenário 1)
        Toma(2, 2, "12:00"),
        // Sinvastatina - pendente (demo do Cenário 2)
        Toma(3, 3, "15:00"),
        // Atenolol - pendente
        Toma(4, 4, "20:00")
    )

    // ============================================================
    // FUNÇÕES DE CONSULTA (só leem os dados, não alteram nada)
    // ============================================================

    // Devolve o Medicamento associado a uma Toma (procura pelo id).
    fun medicamentoDaToma(toma: Toma): Medicamento {
        for (medicamento in medicamentos) {
            if (medicamento.id == toma.medicamentoId) return medicamento
        }
        return medicamentos[0]
    }

    // Procura uma toma pelo número de id (devolve a primeira se não existir).
    fun tomaPorId(id: Int): Toma {
        for (toma in tomas) {
            if (toma.id == id) return toma
        }
        return tomas[0]
    }

    // A próxima toma ainda PENDENTE (null se não houver mais hoje).
    fun proximaTomaPendente(): Toma? {
        return tomas.firstOrNull { it.estado == EstadoToma.PENDENTE }
    }

    // Lista de todas as tomas PENDENTES.
    fun tomasPendentes(): List<Toma> {
        return tomas.filter { it.estado == EstadoToma.PENDENTE }
    }

    // A toma IGNORADA (vermelha) - passaram +30 min sem confirmação.
    // Usada pelo dashboard e pelo alerta F1.
    fun tomaIgnorada(): Toma? {
        return tomas.firstOrNull { it.estado == EstadoToma.IGNORADO }
    }

    // A toma com ATRASO (amarela) - o cuidador já registou mas fora de horas.
    // Usada pelo dashboard depois de "Forçar Registo".
    fun tomaComAtraso(): Toma? {
        return tomas.firstOrNull { it.estado == EstadoToma.ATRASO }
    }

    // Quantas tomas já estão feitas (verdes / TOMADO).
    fun tomasFeitas(): Int {
        return tomas.count { it.estado == EstadoToma.TOMADO }
    }

    // ============================================================
    // FUNÇÕES DE DATA E HORA
    // ============================================================

    // Hora atual do dispositivo como String "HH:mm".
    // Usada no ecrã F1 (relógio da Carla) e em confirmarToma().
    fun horaAtual(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }

    // Data de hoje como String legível, ex: "terça, 24 de junho".
    // Usada no cabeçalho do F3 (detalhe das tomas) e no relógio do F1.
    fun dataDeHoje(): String {
        return SimpleDateFormat("EEEE, d 'de' MMMM", Locale("pt", "PT")).format(Date())
    }

    // Dia da semana de hoje como índice 0..6 (0=Seg, 6=Dom).
    // Usado no QuadradosDaSemana para colorir "hoje" conforme o estado.
    fun indiceDiaDaSemana(): Int {
        val cal = Calendar.getInstance()
        // Calendar.DAY_OF_WEEK: 1=Dom, 2=Seg, ..., 7=Sáb → converte para 0=Seg, 6=Dom
        return (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7
    }

    // ============================================================
    // FUNÇÕES DE AÇÃO (alteram o estado das tomas)
    //
    // Processo: encontrar a posição (indexOfFirst) -> criar uma CÓPIA
    // alterada (.copy()) -> substituir na lista. É a substituição que
    // "acorda" o Compose para redesenhar os ecrãs.
    // ============================================================

    // FUNCIONALIDADE DO MIGUEL (Cenário 1):
    // O paciente carrega em "Tomei" -> a toma passa a TOMADO (verde),
    // com a hora real da confirmação.
    fun confirmarToma(tomaId: Int) {
        val posicao = tomas.indexOfFirst { it.id == tomaId }
        if (posicao != -1) {
            tomas[posicao] = tomas[posicao].copy(
                estado = EstadoToma.TOMADO,
                horaConfirmada = horaAtual()
            )
        }
    }

    // O paciente carrega em "Adiar 15 min" -> a hora prevista avança 15 min.
    fun adiarToma(tomaId: Int) {
        val posicao = tomas.indexOfFirst { it.id == tomaId }
        if (posicao != -1) {
            val novaHora = somarMinutos(tomas[posicao].horaPrevista, 15)
            tomas[posicao] = tomas[posicao].copy(horaPrevista = novaHora)
        }
    }

    // FUNCIONALIDADE DO FRANCISCO (Cenário 2 - parte 1):
    // Simula o sistema a detetar que a Sinvastatina das 15:00 foi ignorada
    // -> passa a IGNORADO (vermelho). Na demo usamos um botão.
    fun simularTomaIgnorada() {
        // Sinvastatina (Cenário 2)
        val posicao = tomas.indexOfFirst { it.medicamentoId == 3 }
        if (posicao != -1) {
            tomas[posicao] = tomas[posicao].copy(
                estado         = EstadoToma.IGNORADO,
                horaConfirmada = null
            )
        }
    }

    // FUNCIONALIDADE DO FRANCISCO (Cenário 2 - parte 2):
    // A cuidadora confirma (após ligar) que o António tomou o medicamento.
    // Estado: IGNORADO -> ATRASO (amarelo), fica a amarelo porque foi fora de horas.
    // Chamada por: F3 (detalhe), ao confirmar o diálogo "Forçar registo".
    fun forcarRegisto(tomaId: Int) {
        val posicao = tomas.indexOfFirst { it.id == tomaId }
        if (posicao != -1) {
            tomas[posicao] = tomas[posicao].copy(
                estado         = EstadoToma.ATRASO,
                horaConfirmada = horaAtual()
            )
        }
    }

    // ============================================================
    // FUNÇÃO AUXILIAR DE HORAS
    // ============================================================

    // Soma minutos a uma hora em texto. Ex: somarMinutos("15:00", 30) -> "15:30".
    fun somarMinutos(hora: String, minutos: Int): String {
        val partes = hora.split(":")
        val horasEmMinutos = partes[0].toInt() * 60
        val minutosExistentes = partes[1].toInt()
        val totalMinutos = (horasEmMinutos + minutosExistentes + minutos) % 1440
        return String.format("%02d:%02d", totalMinutos / 60, totalMinutos % 60)
    }
}
