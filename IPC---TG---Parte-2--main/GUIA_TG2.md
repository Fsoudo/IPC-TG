# Guia de Trabalho — TG2: Implementação e Avaliação de um Protótipo Funcional
**Francisco Manuel Baião Soudo — Nº14060 | IPC | IPBeja 2026**

---

## 1. Contexto: O que foi feito na Parte 1

### Aplicação: EasyMed
App móvel Android de gestão de medicação de pacientes por cuidadores informais.

### O meu cenário (Cenário 2) — Receber Notificação de Toma Ignorada
- **Persona:** Carla Sousa, 46 anos, Enfermeira, cuidadora do pai (António, 74 anos, pós-AVC, 5 medicamentos/dia)
- **Tarefa principal:** Reagir a um alerta crítico de incumprimento terapêutico (toma ignorada)
- **Fluxo do cenário:**
  1. Carla recebe notificação push no ecrã bloqueado (Sinvastatina 20mg, 30 min de atraso)
  2. Abre a app → Dashboard do Cuidador (código de cores: 🔴 ignorado / 🟢 tomado / 🟡 atraso)
  3. Acede ao detalhe das tomas → usa botão "Ligar ao Paciente"
  4. Confirma toma → estado atualiza em tempo real
  5. Consulta Histórico Semanal → identifica padrão recorrente

### Os 4 ecrãs do protótipo Figma (baixa/média fidelidade)
| ID | Ecrã | Descrição |
|----|------|-----------|
| F1 | Ecrã Bloqueado | Notificação crítica com nome do paciente e medicamento |
| F2 | Dashboard Cuidador | Cartões com estado visual de cada paciente (código de cores) |
| F3 | Detalhe das Tomas | Lista cronológica com semântica de cores + botão "Ligar ao Paciente" |
| F4 | Histórico Semanal | Vista de calendário + exportação de relatório PDF |

### HTA — Estrutura hierárquica (Cenário 2)
```
0. Reagir a uma notificação de toma ignorada
   ├── 1. Receber e reconhecer o alerta (vibração/som + ecrã bloqueado)
   ├── 2. Diagnóstico de contexto (abrir app → dashboard → detalhe)
   ├── 3. Intervenção direta (ligar ao paciente)
   ├── 4. Verificação de ciclo (confirmar atualização do estado na interface)
   └── 5. Análise de tendências [opcional] (histórico semanal → nota para médico)
Plano 0: 1 → 2 → [se ignorada] → 3 → 4 → [opcional] 5
```

### Decisões de design já tomadas (a manter/melhorar na Parte 2)
- **Semântica de cores:** Verde (tomado), Vermelho (ignorado), Amarelo (tomado com atraso)
- **Heurísticas Nielsen aplicadas:** Visibilidade do estado, Prevenção de erros, Correspondência com o mundo real, Estética minimalista
- **Interação:** Manipulação direta (toque em botões ≥ 48×48dp), Bottom Navigation Bar (Hoje / Medicamentos / Histórico / Perfil), notificações push
- **Acessibilidade:** Texto ajustável, compatibilidade TalkBack
- **Navegação:** Arquitetura concêntrica centrada no "Sumário Diário" — máximo 1 nível de profundidade para ações críticas

### Ferramentas da equipa
- **Design:** Figma
- **Gestão:** Trello (Épicos / Tarefas / Sprints)
- **Versões:** GitHub — https://github.com/Fsoudo/IPC-TG1-PART-1
- **Comunicação:** WhatsApp

---

## 2. O que pede a Parte 2

| Componente | Cotação | O que fazer |
|---|---|---|
| Protótipo de Alta Fidelidade | 10 valores | App Android funcional (com redesign baseado no feedback da apresentação do low-fi) |
| Funcionalidades Implementadas | 5 valores | Implementar 1 funcionalidade prioritária do Cenário 2 |
| Avaliação com Utilizadores | 3 valores | Conduzir sessão de teste + questionário; participar como utilizador em 2 projetos de colegas |
| Apresentação Final | 2 valores | Demo + metodologia + resultados (abrange Parte 1 + Parte 2) |

---

## 3. Plano de Trabalho — O que fazer e por onde começar

### FASE 1 — Redesign do Protótipo (Alta Fidelidade no Android)

**Objetivo:** Transformar os ecrãs do Figma (F1–F4) numa app Android funcional, incorporando o feedback recebido na apresentação do low-fi.

**Tarefas:**

- [ ] **Rever o feedback** dado pelo docente na apresentação do protótipo de baixa fidelidade — identificar o que redesenhar
- [ ] **Criar projeto Android** (Android Studio, Java ou Kotlin) com estrutura de navegação:
  - `LockScreenNotificationActivity` (F1)
  - `CaregiverDashboardActivity` (F2)
  - `MedicationDetailActivity` (F3)
  - `WeeklyHistoryActivity` (F4)
- [ ] **Implementar Bottom Navigation Bar** com os 4 separadores: Hoje / Medicamentos / Histórico / Perfil
- [ ] **Aplicar paleta de cores** da EasyMed (consistente com os ecrãs do Miguel — Cenário 1)
- [ ] **Garantir botões ≥ 48×48dp** e tipografia legível (acessibilidade)
- [ ] **Criar dados mock/simulados** para demonstrar o cenário (paciente António, medicamentos, estados)

**Notas de redesign a considerar (baseadas nas limitações identificadas na Parte 1):**
- A distinção visual das notificações deve ser mais clara que a do MyTherapy
- Evitar sobrecarga funcional — manter cada ecrã focado numa só ação crítica
- O botão "Ligar ao Paciente" deve ser o CTA principal no F3, não enterrado no layout

---

### FASE 2 — Funcionalidade a Implementar (1 funcionalidade core)

**Funcionalidade prioritária para o Cenário 2:**

> **Dashboard do Cuidador com atualização de estado em tempo real** (ecrã F2 + F3)

Justificação: É o coração do cenário — a Carla precisa de ver imediatamente o estado do pai. Esta funcionalidade demonstra o código de cores, a lista de tomas e o botão de chamada, cobrindo os passos 2, 3 e 4 do HTA.

**O que implementar concretamente:**
- [ ] Lista de pacientes no dashboard (F2) com indicador de cor por estado
- [ ] Ao tocar num paciente → navega para detalhe das tomas (F3)
- [ ] Lista de medicamentos com estados: ✅ Tomado / ❌ Ignorado / ⏳ Pendente / ⚠️ Atraso
- [ ] Botão "Ligar ao paciente" que abre o marcador telefónico (Intent ACTION_DIAL)
- [ ] Simulação de atualização de estado (ex.: botão "Simular toma" para demo)

**Alternativa se o tempo for escasso:** Implementar apenas F2 + F3 com dados estáticos mas interação real (navegação, cliques, cores).

---

### FASE 3 — Avaliação com Utilizadores

#### 3.1 Planeamento da avaliação

**Metodologia:** Teste de usabilidade com observação + questionário pós-teste (recomendado: SUS — System Usability Scale, ou questionário próprio)

**Participantes:** Mínimo 2–3 utilizadores (os elementos dos grupos de colegas que participarão como utilizadores no vosso projeto)

**Tarefas a propor (baseadas no HTA do Cenário 2):**

| # | Tarefa | Critério de sucesso |
|---|--------|---------------------|
| T1 | "Recebeste uma notificação da EasyMed. Abre a app e descobre qual o medicamento em falta do António." | Chegar ao F3 em ≤ 3 cliques |
| T2 | "Liga ao paciente através da app." | Acionar o botão "Ligar ao Paciente" |
| T3 | "Consulta o histórico semanal do António." | Navegar até F4 com sucesso |

**Métricas a recolher (obrigatório):**
- Número de cliques por tarefa
- Tempo de conclusão de cada tarefa
- Número de erros cometidos
- Taxa de sucesso (tarefa concluída sem ajuda?)

**Instrumentos de registo:**
- [ ] **Folha de registo** para o avaliador (ver template abaixo)
- [ ] **Gravação da sessão** (ecrã do telemóvel + áudio, ou videochamada se remoto)
- [ ] **Questionário pós-teste** (SUS ou equivalente)

**Condução da sessão:**
1. Briefing ao utilizador (explicar o contexto da EasyMed, sem revelar a solução)
2. Warm-up (mostrar a app brevemente)
3. Propor tarefas T1, T2, T3 uma a uma (think-aloud protocol)
4. Registar métricas
5. Aplicar questionário

**Nota:** A avaliação pode ser feita à distância por videoconferência, desde que a app corra remotamente e o utilizador consiga interagir.

---

#### 3.2 Template — Folha de Registo de Sessão

```
Avaliador: _______________  Data: _______________  Participante nº: ___

TAREFA 1 — Identificar medicamento em falta
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

TAREFA 2 — Ligar ao paciente
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

TAREFA 3 — Consultar histórico semanal
  Tempo: ______s  |  Nº cliques: ___  |  Erros: ___  |  Sucesso: S/N
  Observações: __________________________________________________

Comentários gerais do utilizador durante a sessão:
______________________________________________________________
```

---

#### 3.3 Questionário pós-teste (SUS simplificado)

Escala de 1 (discordo totalmente) a 5 (concordo totalmente):

1. Achei a app fácil de usar.
2. Consegui completar as tarefas sem precisar de ajuda.
3. A informação mais importante estava sempre visível.
4. As cores e ícones ajudaram-me a perceber o estado da medicação.
5. Voltaria a usar esta app se fosse cuidador de um familiar.

**Pontuação SUS oficial (opcional):** usar as 10 perguntas padrão do SUS para obter score 0–100.

---

#### 3.4 Participação como utilizador em projetos de colegas

- [ ] Participar em **2 projetos** de outros grupos como utilizador
- [ ] Registar no relatório: **quais os projetos** em que participou (nome do grupo/equipa)

---

### FASE 4 — Relatório Final

**Estrutura do relatório (PDF único do grupo):**

```
Parte 1 (já feita — integrar o relatório individual)
  - Ferramentas e organização da equipa
  - Participação em eventos de informática no IPBeja
  - Sistemas semelhantes (MyTherapy + análise do Miguel)
  - Caracterização dos utilizadores (Personas)
  - Cenários de utilização
  - Análise de tarefas (HTA)
  - Estilos e dispositivos de interação
  - Protótipo de baixa fidelidade (esboços, navegação, justificação)

Parte 2 (a fazer)
  - Protótipo de alta fidelidade (screenshots da app Android)
  - Funcionalidades implementadas (justificação da escolha + descrição)
  - Avaliação com utilizadores:
      → Metodologia escolhida e justificação
      → Planeamento (participantes, instrumentos, tarefas)
      → Resultados das métricas
      → Análise e comentários
      → Projetos em que participámos como utilizadores
```

**Nome do ficheiro ZIP:**
```
TG2_[nº_equipa]_[nº1]_[nº2]_[nº3]_[nº4].zip
```
Contém: relatório PDF + apresentação + link GitHub da app

**Entrega:** Moodle da UC (não aceita e-mail)

**Repositório GitHub:** criar repositório novo para a app Android e incluir o link no relatório.

---

### FASE 5 — Apresentação Final

**Estrutura (Parte 1 + Parte 2, cada uma vale 2 valores):**

- Parte 1: Análise e desenho (persona, cenário, HTA, protótipo low-fi, navegação)
- Parte 2: Demo da app Android + metodologia de avaliação + resultados dos testes

**Cada aluno apresenta o trabalho que desenvolveu** (Francisco apresenta Cenário 2 completo).

---

## 4. Checklist de Entrega

### Protótipo Android
- [ ] App corre num emulador ou dispositivo Android real
- [ ] Ecrãs F1–F4 navegáveis
- [ ] Código de cores implementado (verde/vermelho/amarelo)
- [ ] Bottom Navigation Bar funcional
- [ ] Pelo menos 1 funcionalidade real implementada (não apenas estática)
- [ ] Botão "Ligar ao Paciente" funcional (Intent ACTION_DIAL)
- [ ] Código no GitHub (repositório TG2)

### Avaliação
- [ ] Mínimo 2 utilizadores testaram a app
- [ ] Sessões gravadas
- [ ] Folhas de registo preenchidas
- [ ] Questionário aplicado
- [ ] Resultados analisados no relatório
- [ ] Identificados os 2 projetos de colegas em que participei como utilizador

### Relatório
- [ ] PDF único com Parte 1 + Parte 2
- [ ] Link do repositório GitHub da app incluído
- [ ] Contribuições individuais identificáveis
- [ ] Nome do ficheiro correto (TG2_...)
- [ ] Submetido no Moodle

### Apresentação
- [ ] Demo ao vivo da app
- [ ] Parte 1 coberta (2 valores)
- [ ] Parte 2 coberta (2 valores)

---

## 5. Referências da Parte 1 a reutilizar

- **HTA Cenário 2:** `Docs/05_analise_tarefas/francisco_hta2/hta2_francisco.md`
- **Protótipos Figma:** `Docs/07_prototipo/francisco/navegacao/`
- **Repositório TG1:** https://github.com/Fsoudo/IPC-TG1-PART-1
- **Sistema semelhante analisado:** MyTherapy (Android/iOS) — pontos a evitar: sobrecarga funcional, processo de inserção extenso, notificações pouco distintas

---

*Guia gerado em 2026-05-20 com base no enunciado TG2 e no relatório individual da Parte 1.*
