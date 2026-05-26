# Guia de Trabalho — TG2: Implementação e Avaliação de um Protótipo Funcional
**Equipa:** Francisco Manuel Baião Soudo (Nº14060) · Miguel Pauzinho (Nº27131)
**UC:** IPC — Interação Pessoa-Computador | IPBeja 2026

---

## 1. Contexto: O que foi feito na Parte 1

### Aplicação: EasyMed
App móvel Android de gestão de medicação, com dois perfis de utilizador:
- **Paciente** (Cenário 1 — Miguel): idoso que confirma as tomas diárias.
- **Cuidador informal** (Cenário 2 — Francisco): familiar que monitoriza remotamente.

### Os dois cenários

| | **Cenário 1 — Miguel** | **Cenário 2 — Francisco** |
|---|---|---|
| **Persona** | António Ferreira, 72, reformado, vive sozinho | Carla Sousa, 46, enfermeira, monitoriza o pai |
| **Tarefa principal** | Confirmar a toma de medicamento | Reagir a alerta de toma ignorada |
| **Sistema similar analisado** | MediSafe | MyTherapy |
| **Ecrãs** | M1–M4 | F1–F4 |

### Projeto Figma

🔗 **Ficheiro Figma da equipa:** [EasyMed — TG1](https://www.figma.com/design/hRIuwAsRf21tAVVn0yHLX8/EasyMed---TG1?node-id=0-1&t=q7KIIRb9II86FJBW-1)

> Os mockups abaixo correspondem aos ecrãs de média fidelidade exportados do Figma. **Falta criar o ecrã F5 (Plano de Medicação)** no Figma antes do redesign Android.

### Ecrãs do protótipo Figma (baixa/média fidelidade)

**Cenário 1 — Miguel (Paciente)**

| ID | Ecrã | Descrição |
|----|------|-----------|
| M1 | Ecrã Bloqueado | Alerta de toma com nome do medicamento |
| M2 | Notificação Expandida | Botões grandes "Tomei" / "Adiar" |
| M3 | Confirmação | Visto verde + mensagem "Medicamento registado às HH:MM" |
| M4 | Ecrã Principal | Resumo diário das tomas (verde/vermelho/amarelo) |

**Cenário 2 — Francisco (Cuidador)**

| ID | Ecrã | Descrição |
|----|------|-----------|
| F1 | Ecrã Bloqueado | Notificação crítica de toma ignorada |
| F2 | Dashboard Cuidador | Cartões com estado visual de cada paciente |
| F3 | Detalhe das Tomas | Lista cronológica + botão "Ligar ao Paciente" |
| F4 | Histórico Semanal | Calendário + exportação de relatório PDF |
| F5 | Plano de Medicação | Gestão do plano de medicação do paciente (adicionar / editar / remover medicamentos) |

### Galeria de mockups (Figma)

#### Cenário 1 — Miguel (Paciente)

| M1 — Ecrã Bloqueado | M2 — Notificação Expandida |
|:---:|:---:|
| ![M1](assets/figma/M1%20-%20Ecra%20Bloqueado.png) | ![M2](assets/figma/M2%20-%20Notificacao%20Expandida.png) |

| M3 — Confirmação | M4 — Ecrã Principal |
|:---:|:---:|
| ![M3](assets/figma/M3%20-%20Confirmacao.png) | ![M4](assets/figma/M4%20-%20Ecra%20Principal.png) |

#### Cenário 2 — Francisco (Cuidador)

| F1 — Ecrã Bloqueado | F2 — Dashboard Cuidador |
|:---:|:---:|
| ![F1](assets/figma/F1%20-%20Ecra%20Bloqueado.png) | ![F2](assets/figma/F2%20-%20Dashboard%20Cuidador.png) |

| F3 — Detalhe das Tomas | F4 — Histórico Semanal |
|:---:|:---:|
| ![F3](assets/figma/F3%20-%20Detalhe%20Tomas%20Antonio.png) | ![F4](assets/figma/F4%20-%20Historico%20Semanal.png) |

| F5 — Plano de Medicação |
|:---:|
| *(por criar no Figma — novo ecrã para gestão do plano de medicação)* |

### HTAs — Cenário 1 e Cenário 2

```
CENÁRIO 1 (Miguel)
0. Confirmar a toma de medicamento
   ├── 1. Receber o alerta de toma
   ├── 2. Tomar o medicamento
   ├── 3. Confirmar a toma na app (botão "Tomei")
   └── 4. Verificar o resumo do dia [opcional]
```

```
CENÁRIO 2 (Francisco)
0. Reagir a uma notificação de toma ignorada
   ├── 1. Receber e reconhecer o alerta
   ├── 2. Diagnóstico de contexto (dashboard → detalhe)
   ├── 3. Intervenção direta (ligar ao paciente)
   ├── 4. Verificação de ciclo (estado atualiza)
   └── 5. Análise de tendências [opcional]
```

### Decisões de design partilhadas (a manter na Parte 2)
- **Semântica de cores:** 🟢 Verde (tomado), 🔴 Vermelho (ignorado), 🟡 Amarelo (atraso)
- **Heurísticas Nielsen:** visibilidade do estado, prevenção de erros, correspondência com o mundo real, estética minimalista
- **Acessibilidade:** botões ≥ 48×48dp, texto ajustável, TalkBack
- **Navegação:** Bottom Navigation Bar (Hoje / Medicamentos / Histórico / Perfil)
- **Arquitetura:** concêntrica, centrada no "Sumário Diário"

### Ferramentas
- **Design:** Figma · **Gestão:** Trello · **Versões:** GitHub · **Comunicação:** WhatsApp
- Repo Parte 1: https://github.com/Fsoudo/IPC-TG1

---

## 2. O que pede a Parte 2

| Componente | Cotação | Descrição |
|---|---|---|
| Protótipo de Alta Fidelidade | 10 valores | App Android funcional com redesign baseado no feedback |
| Funcionalidades Implementadas | 5 valores | 1 funcionalidade por cenário (1 Miguel + 1 Francisco) |
| Avaliação com Utilizadores | 3 valores | Sessão de teste + questionário + participar em 2 projetos de colegas |
| Apresentação Final | 2 valores | Demo + metodologia + resultados (Parte 1 + Parte 2) |

---

## 3. Divisão de tarefas

### 3.1 Tarefas conjuntas (decisão e execução partilhada)

- [ ] Criar **repositório GitHub novo** para a app Android (ex.: `EasyMed-Android`) — partilhado pelos dois
- [ ] Definir **paleta de cores, tipografia e tema Material** únicos para toda a app
- [ ] Implementar a **Bottom Navigation Bar** comum (Hoje / Medicamentos / Histórico / Perfil)
- [ ] Definir **ecrã inicial de seleção de perfil** (Paciente / Cuidador) ou login simulado
- [ ] Criar **modelo de dados mock partilhado** (paciente António Ferreira, lista de medicamentos, estados)
- [ ] Integrar os dois fluxos no mesmo APK e garantir consistência visual
- [ ] Redigir o **relatório final em conjunto** (estrutura na Fase 5)
- [ ] Preparar a **apresentação final** (cada um apresenta o seu cenário)
- [ ] Cada um participa como utilizador em **2 projetos de colegas**

### 3.2 FASE 1 — Redesign do Protótipo (Alta Fidelidade)

**Francisco (Cenário 2 — Cuidador):**
- [ ] Rever feedback do docente sobre os ecrãs F1–F4 do low-fi
- [ ] Implementar `LockScreenNotificationActivity` (F1)
- [ ] Implementar `CaregiverDashboardActivity` (F2)
- [ ] Implementar `MedicationDetailActivity` (F3)
- [ ] Implementar `WeeklyHistoryActivity` (F4)
- [ ] Implementar `MedicationPlanActivity` (F5) — gestão do plano de medicação por paciente
- [ ] Garantir que a distinção visual das notificações é mais clara que a do MyTherapy
- [ ] O botão "Ligar ao Paciente" deve ser CTA principal no F3
- [ ] F5 deve ser acessível a partir do detalhe do paciente (F3) via botão/ícone "Gerir Plano"

**Miguel (Cenário 1 — Paciente):**
- [ ] Rever feedback do docente sobre os ecrãs M1–M4 do low-fi
- [ ] Implementar `MedicationAlertLockScreenActivity` (M1)
- [ ] Implementar `MedicationNotificationExpandedActivity` (M2) com botões grandes "Tomei" / "Adiar"
- [ ] Implementar `IntakeConfirmationActivity` (M3) — feedback visual claro
- [ ] Implementar `PatientHomeActivity` (M4) — resumo diário com código de cores
- [ ] Garantir tipografia grande (mínimo 18sp) e botões muito generosos (≥ 64dp) para idosos com dedos trémulos
- [ ] Evitar menus profundos — máximo 1 nível para ação crítica

---

### 3.3 FASE 2 — Funcionalidades a Implementar

**Francisco (Cenário 2 — Cuidador):**

> **Funcionalidades prioritárias:**
> 1. Dashboard do Cuidador com atualização de estado em tempo real (F2 + F3)
> 2. Gestão do Plano de Medicação por paciente (F5)

**Dashboard + Detalhe (F2 + F3):**
- [ ] Lista de pacientes no dashboard com indicador de cor por estado
- [ ] Toque num paciente → navega para detalhe das tomas
- [ ] Lista de medicamentos com estados: ✅ Tomado / ❌ Ignorado / ⏳ Pendente / ⚠️ Atraso
- [ ] Botão **"Ligar ao paciente"** com `Intent.ACTION_DIAL`
- [ ] Simulação de transição de estado (botão "Simular toma" para a demo)

**Plano de Medicação (F5):**
- [ ] Botão "Gerir Plano" no detalhe do paciente (F3) abre o F5
- [ ] Lista dos medicamentos atuais do paciente (nome, dose, horário, frequência)
- [ ] Botão **"Adicionar Medicamento"** (FAB) abre formulário com:
  - Nome do medicamento (texto)
  - Dose (ex.: 20mg)
  - Horário(s) de toma (TimePicker, vários permitidos)
  - Frequência (diária / dias específicos da semana)
  - Notas opcionais
- [ ] Editar medicamento existente (toque no item)
- [ ] Remover medicamento (swipe ou botão com confirmação)
- [ ] Persistência local (SharedPreferences ou Room) para guardar entre sessões
- [ ] Validação de campos obrigatórios antes de gravar
- [ ] Após adicionar/editar, o plano reflete-se no dashboard (F2) e detalhe (F3) do paciente

**Miguel (Cenário 1 — Paciente):**

> **Funcionalidade prioritária:** Notificação acionável de toma + Confirmação (M2 + M3)

- [ ] Sistema de **notificação Android** com `NotificationCompat` e botões de ação ("Tomei" / "Adiar")
- [ ] `BroadcastReceiver` que recebe o clique no botão "Tomei" e regista a toma
- [ ] Ecrã de confirmação (M3) com visto verde, nome do medicamento e hora atual
- [ ] Atualização do estado da toma no ecrã principal (M4) — verde após confirmação
- [ ] Botão **"Adiar 15 min"** que reagenda o alarme com `AlarmManager`
- [ ] Simulação: botão na app para disparar uma notificação de teste

**Alternativa partilhada se o tempo apertar:** implementar apenas com dados estáticos, mas garantir navegação e cliques reais.

---

### 3.4 FASE 3 — Avaliação com Utilizadores

#### Planeamento conjunto
- [ ] Recrutar **mínimo 2–3 utilizadores cada** (podem ser os mesmos para os dois cenários)
- [ ] Acordar metodologia: **observação + think-aloud + questionário SUS**
- [ ] Preparar **folha de registo** comum (template em 3.6)
- [ ] Gravar sessões (ecrã + áudio)

#### Tarefas a propor — Francisco (Cenário 2)

| # | Tarefa | Critério |
|---|--------|----------|
| F-T1 | "Recebeste uma notificação. Descobre qual o medicamento em falta do António." | ≤ 3 cliques |
| F-T2 | "Liga ao paciente através da app." | Aciona o botão "Ligar ao Paciente" |
| F-T3 | "Consulta o histórico semanal do António." | Chega ao F4 |
| F-T4 | "Adiciona um novo medicamento (Aspirina 100mg, 08:00, diário) ao plano do António." | Grava com sucesso e o medicamento aparece no plano |

#### Tarefas a propor — Miguel (Cenário 1)

| # | Tarefa | Critério |
|---|--------|----------|
| M-T1 | "Recebeste um alerta na app. Confirma que tomaste o medicamento." | Carrega em "Tomei" sem entrar na app |
| M-T2 | "Adia o próximo alerta por 15 minutos." | Usa o botão "Adiar" |
| M-T3 | "Verifica quais os medicamentos que já tomaste hoje." | Chega ao ecrã M4 |

#### Métricas (obrigatórias para os dois)
- Número de cliques · Tempo de conclusão · Erros · Taxa de sucesso

---

### 3.5 Template — Folha de Registo (comum)

```
Avaliador: _______________  Data: _______________  Participante nº: ___
Cenário avaliado: [ ] Cenário 1 (Paciente)   [ ] Cenário 2 (Cuidador)

TAREFA 1 — _________________________
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

TAREFA 2 — _________________________
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

TAREFA 3 — _________________________
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

Comentários gerais do utilizador:
______________________________________________________________
```

### 3.6 Questionário pós-teste (SUS simplificado, comum)

Escala 1 (discordo totalmente) a 5 (concordo totalmente):

1. Achei a app fácil de usar.
2. Consegui completar as tarefas sem precisar de ajuda.
3. A informação mais importante estava sempre visível.
4. As cores e ícones ajudaram-me a perceber o estado da medicação.
5. Voltaria a usar esta app no contexto real (como paciente ou cuidador).

---

### 3.7 Participação em projetos de colegas

| Aluno | Projeto 1 | Projeto 2 |
|---|---|---|
| Francisco | _______________ | _______________ |
| Miguel | _______________ | _______________ |

---

### FASE 4 — Relatório Final (conjunto)

**Estrutura do PDF único:**

```
Parte 1 (já feita)
  - Ferramentas e organização da equipa
  - Sistemas similares (MediSafe — Miguel · MyTherapy — Francisco)
  - Personas (António — Miguel · Carla — Francisco)
  - Cenários 1 e 2
  - Análise de tarefas (HTA1 + HTA2)
  - Estilos e dispositivos de interação
  - Protótipo de baixa fidelidade (M1–M4 + F1–F4)

Parte 2 (a fazer)
  - Protótipo de alta fidelidade — screenshots da app Android
      → Subseção Miguel (ecrãs M1–M4)
      → Subseção Francisco (ecrãs F1–F4)
  - Funcionalidades implementadas
      → Miguel: Notificação acionável + Confirmação de toma
      → Francisco: Dashboard com estado em tempo real + ACTION_DIAL + Gestão do Plano de Medicação
  - Avaliação com utilizadores
      → Metodologia conjunta
      → Resultados Miguel (M-T1, M-T2, M-T3)
      → Resultados Francisco (F-T1, F-T2, F-T3)
      → Análise comparativa e comentários
      → Projetos em que participámos como utilizadores
```

**Nome do ficheiro ZIP:**
```
TG2_[nº_equipa]_14060_27131.zip
```
Contém: relatório PDF + apresentação + link GitHub da app.

**Entrega:** Moodle da UC.

---

### FASE 5 — Apresentação Final

**Estrutura conjunta (cada parte vale 2 valores):**

| Bloco | Quem apresenta | Conteúdo |
|---|---|---|
| Intro (1 min) | Em conjunto | Equipa, EasyMed, contexto |
| Parte 1 — Cenário 1 | Miguel | Persona António, HTA1, ecrãs M1–M4 |
| Parte 1 — Cenário 2 | Francisco | Persona Carla, HTA2, ecrãs F1–F4 |
| Parte 2 — Demo Miguel | Miguel | Notificação acionável + confirmação |
| Parte 2 — Demo Francisco | Francisco | Dashboard + ACTION_DIAL |
| Parte 2 — Avaliação | Em conjunto | Metodologia, resultados, conclusões |
| Q&A | Em conjunto | — |

---

## 4. Checklist de Entrega

### App Android (conjunto)
- [ ] App corre em emulador ou dispositivo real
- [ ] Ecrã inicial permite escolher perfil (Paciente / Cuidador)
- [ ] Paleta de cores e tema consistentes
- [ ] Bottom Navigation Bar funcional
- [ ] Código no GitHub (repositório TG2 novo)

### Francisco (Cenário 2)
- [ ] Ecrãs F1–F5 navegáveis
- [ ] Dashboard com código de cores
- [ ] Botão "Ligar ao Paciente" funcional (ACTION_DIAL)
- [ ] Simulação de atualização de estado
- [ ] Gestão do plano de medicação (adicionar / editar / remover) funcional e persistente

### Miguel (Cenário 1)
- [ ] Ecrãs M1–M4 navegáveis
- [ ] Notificação Android com ações "Tomei" / "Adiar"
- [ ] Confirmação de toma com feedback visual
- [ ] Botões grandes (≥ 64dp) e tipografia acessível
- [ ] Reagendamento via AlarmManager

### Avaliação
- [ ] Mínimo 2 utilizadores testaram cada cenário
- [ ] Sessões gravadas (Francisco + Miguel)
- [ ] Folhas de registo preenchidas
- [ ] Questionário SUS aplicado
- [ ] Cada um participou em 2 projetos de colegas

### Relatório
- [ ] PDF único com Parte 1 + Parte 2 (Miguel + Francisco)
- [ ] Link do repositório GitHub da app incluído
- [ ] Contribuições individuais identificáveis
- [ ] Submetido no Moodle

### Apresentação
- [ ] Demo ao vivo dos dois cenários
- [ ] Parte 1 coberta (2 valores)
- [ ] Parte 2 coberta (2 valores)

---

## 5. Referências da Parte 1 a reutilizar

### Francisco
- HTA: `Docs/05_analise_tarefas/francisco_hta2/hta2_francisco.md`
- Protótipos: `Docs/07_prototipo/francisco/`
- Sistema similar: **MyTherapy** — evitar sobrecarga funcional, processo de inserção extenso, notificações pouco distintas

### Miguel
- HTA: `Docs/05_analise_tarefas/miguel_hta1/hta1_miguel.md`
- Protótipos: `Docs/07_prototipo/miguel/`
- Sistema similar: **MediSafe**

### Conjunto
- Repositório Parte 1: https://github.com/Fsoudo/IPC-TG1
- Projeto Figma: https://www.figma.com/design/hRIuwAsRf21tAVVn0yHLX8/EasyMed---TG1?node-id=0-1&t=q7KIIRb9II86FJBW-1

---

*Guia revisto em 2026-05-26 — divisão de tarefas entre Francisco (Cenário 2 / Cuidador) e Miguel (Cenário 1 / Paciente).*
