package com.example.easymed


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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
// Código de cores (3 estados):
//   TOMADO   -> 🟢 verde    (medicamento tomado)
//   PENDENTE -> 🟡 amarelo  (ainda por tomar)
//   ATRASADO -> 🔴 vermelho (falhou / em atraso)
// ============================================================
enum class EstadoToma {
    // verde - medicamento tomado
    TOMADO,
    // amarelo - ainda por tomar
    PENDENTE,
    // vermelho - falhou / em atraso
    ATRASADO
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
// MEMÓRIA CENTRAL DA APLICAÇÃO 
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

    // TOMA EM FOCO no fluxo a decorrer (alarme do Cenário 1 / alerta do Cenário 2).
    // A MainActivity põe aqui o id antes de abrir esses ecrãs.
    var tomaSelecionadaId by mutableIntStateOf(0)

    // MEDICAÇÃO DO ANTÓNIO - lista FIXA com os 4 medicamentos.
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
    // Estado inicial: Amlodipina já tomada (verde); as outras pendentes (amarelo).
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
    //
    // Estas funções são chamadas pelos ecrãs para obter informação.
    // Não modificam a lista de tomas - só leem.
    // ============================================================

    // Devolve o Medicamento associado a uma Toma (procura pelo id).
    //
    // Recebe: uma Toma (que só tem o medicamentoId - um número).
    // Devolve: o Medicamento correspondente (nome, dosagem, forma).
    // Exemplo: Toma(2, 2, "12:00") -> Medicamento(2, "Metformina", "500mg", "comprimido")
    //
    // Porquê: a Toma só guarda o id do medicamento (número), não o nome.
    // Para mostrar "Metformina 500mg" no ecrã, precisamos de ir buscar
    // o objeto Medicamento completo.
    fun medicamentoDaToma(toma: Toma): Medicamento {
        // percorre os medicamentos um a um
        for (medicamento in medicamentos) {
            // encontrou o medicamento desta toma
            if (medicamento.id == toma.medicamentoId) {
                return medicamento
            }
        }
        // se não encontrar (não deve acontecer), devolve o primeiro
        return medicamentos[0]
    }

    // Procura uma toma pelo número de id (devolve a primeira se não existir).
    //
    // Recebe: um id (número inteiro, ex: 1, 2, 3, 4).
    // Devolve: a Toma com esse id (ou a primeira da lista se não encontrar).
    // Exemplo: tomaPorId(2) -> Toma(2, 2, "12:00")
    //
    // Porquê: quando o utilizador toca no botão "Simular Alarme" ou "Tomei",
    // precisamos de saber QUAL das 4 tomas está em foco. O id é guardado
    // em DadosApp.tomaSelecionadaId e esta função converte-o em Toma.
    fun tomaPorId(id: Int): Toma {
        // percorre as tomas uma a uma
        for (toma in tomas) {
            // encontrou a toma com este id
            if (toma.id == id) {
                return toma
            }
        }
        // se não encontrar (não deve acontecer), devolve a primeira
        return tomas[0]
    }

    // A próxima toma ainda PENDENTE (null se não houver mais hoje).
    //
    // Recebe: nada (usa a lista tomas do DadosApp).
    // Devolve: a primeira Toma com estado PENDENTE, ou null se todas foram tomadas.
    // Exemplo: se Amlodipina já foi tomada e Metformina está pendente,
    //          devolve a Toma da Metformina.
    //
    // Porquê: usada no ecrã M3 (confirmação) e M4 (Hoje) para mostrar
    // "Próxima toma às 12:00" - assim o António não precisa de decorar.
    fun proximaTomaPendente(): Toma? {
        return tomas.firstOrNull { it.estado == EstadoToma.PENDENTE }
    }

    // O primeiro medicamento a vermelho (ATRASADO), se existir.
    // Usado pelo dashboard e pelo detalhe do cuidador para saber se há alerta.
    //
    // Recebe: nada (usa a lista tomas do DadosApp).
    // Devolve: a primeira Toma com estado ATRASADO, ou null se não houver nenhuma.
    //
    // Porquê: o ecrã F2 (dashboard) e F3 (detalhe) precisam de saber se
    // há alguma toma em atraso para mostrar a borda vermelha e o alerta.
    fun tomaAtrasada(): Toma? {
        return tomas.firstOrNull { it.estado == EstadoToma.ATRASADO }
    }

    // Quantas tomas já estão feitas (verdes / TOMADO).
    //
    // Recebe: nada (usa a lista tomas do DadosApp).
    // Devolve: um número inteiro (ex: 2 se já foram tomadas 2 de 4).
    //
    // Porquê: usada no ecrã M4 (Hoje) para mostrar "2 de 4 tomas" e
    // no F2 (dashboard) para saber se "Todas as tomas em dia".
    fun tomasFeitas(): Int {
        return tomas.count { it.estado == EstadoToma.TOMADO }
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
    //
    // Recebe: tomaId (id da toma que o paciente confirmou).
    // Efeito: muda o estado da toma para TOMADO e regista a hora atual.
    // Chamada por: M2 (ecrã notificação), ao tocar no botão "Tomei".
    fun confirmarToma(tomaId: Int) {
        // posição na lista (-1 = não encontrou)
        val posicao = tomas.indexOfFirst { it.id == tomaId }
        if (posicao != -1) {
            tomas[posicao] = tomas[posicao].copy(
                // verde
                estado = EstadoToma.TOMADO,
                // usa a hora prevista como hora de confirmação (demo simplificada)
                horaConfirmada = tomas[posicao].horaPrevista
            )
        }
    }

    // O paciente carrega em "Adiar 15 min" -> a hora prevista avança 15 min.
    // O estado continua PENDENTE (não foi tomado, só adiado).
    //
    // Recebe: tomaId (id da toma que o paciente quer adiar).
    // Efeito: soma 15 minutos à horaPrevista (ex: "12:00" -> "12:15").
    // Chamada por: M2 (ecrã notificação), ao tocar no botão "Adiar 15 min".
    fun adiarToma(tomaId: Int) {
        val posicao = tomas.indexOfFirst { it.id == tomaId }
        if (posicao != -1) {
            // ex: "12:00" -> "12:15"
            val novaHora = somarMinutos(tomas[posicao].horaPrevista, 15)
            tomas[posicao] = tomas[posicao].copy(horaPrevista = novaHora)
        }
    }

    // FUNCIONALIDADE DO FRANCISCO (Cenário 2 - parte 1):
    // Simula o sistema a detetar que a Sinvastatina das 15:00 falhou
    // -> passa a ATRASADO (vermelho). Na demo usamos um botão.
    //
    // Recebe: nada ( assume sempre a Sinvastatina = medicamentoId 3).
    // Efeito: muda o estado da toma da Sinvastatina para ATRASADO.
    // Chamada por: F2 (dashboard da cuidadora), ao tocar em "Simular toma ignorada".
    fun simularTomaIgnorada() {
        // Sinvastatina (Cenário 2)
        val posicao = tomas.indexOfFirst { it.medicamentoId == 3 }
        if (posicao != -1) {
            tomas[posicao] = tomas[posicao].copy(
                // vermelho
                estado         = EstadoToma.ATRASADO,
                horaConfirmada = null
            )
        }
    }

    // ============================================================
    // FUNÇÃO AUXILIAR DE HORAS
    // ============================================================

    // Soma minutos a uma hora em texto. Ex: somarMinutos("15:00", 30) -> "15:30".
    //
    // Recebe: hora (string "HH:mm", ex: "09:00") e minutos (inteiro, ex: 15).
    // Devolve: string com a hora resultante (ex: "09:15").
    // Se ultrapassar as 24:00, dá a volta à meia-noite (ex: "23:45" + 30 -> "00:15").
    //
    // Porquê: usada na função adiarToma() para somar 15 minutos à hora
    // prevista, e no F1 para calcular a hora do alerta (hora da toma + 30 min).
    fun somarMinutos(hora: String, minutos: Int): String {
        // divide "09:00" em ["09", "00"]
        val partes = hora.split(":")
        // transforma horas e minutos num único número de minutos
        // ex: "09:30" -> 9*60 + 30 = 570 minutos desde meia-noite
        val horasEmMinutos = partes[0].toInt() * 60
        val minutosExistentes = partes[1].toInt()
        // soma tudo: hora da toma + minutos a adicionar
        val totalMinutos = (horasEmMinutos + minutosExistentes + minutos) % 1440
        // 1440 = 24*60 = todos os minutos do dia; o % dá a volta à meia-noite
        // ex: "23:45" + 30 = 1425 -> 1425 % 1440 = 1425 (ainda hoje)
        // ex: "23:45" + 60 = 1485 -> 1485 % 1440 = 45 (já é amanhã = "00:45")

        // junta como "HH:mm" (com zero à esquerda se preciso)
        return String.format("%02d:%02d", totalMinutos / 60, totalMinutos % 60)
    }
}