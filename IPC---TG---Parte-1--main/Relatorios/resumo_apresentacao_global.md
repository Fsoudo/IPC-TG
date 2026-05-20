# Resumo da Apresentação Global — EasyMed

**Projeto:** Interação Pessoa-Computador · TG1  
**Equipa 14:** Francisco Soudo (14060) e Miguel Pauzinho (27131)  
**IPBeja / ESTIG · 2025/2026**

---

## Slide 1 — Identificação do Projeto

- **App:** EasyMed — gestão de medicação para idosos e cuidadores
- **Equipa 14:** Francisco Soudo (14060) + Miguel Pauzinho (27131)
- **Instituição:** IPBeja / ESTIG · Ano Letivo 2025/2026

---

## Slide 2 — Análise de Sistemas Semelhantes

| Sistema | Responsável | Principais Conclusões |
|---|---|---|
| **MyTherapy** | Francisco | Design limpo e acessível, mas risco de sobrecarga funcional e configurações complexas |
| **Medisafe** | Miguel | Foco na gestão de medicação, boa usabilidade base, mas navegação pouco intuitiva e funcionalidades premium excessivas |

**Conclusão de equipa:** A EasyMed deve priorizar simplicidade e eliminar funcionalidades excessivas, colocando a acessibilidade em primeiro lugar.

---

## Slide 3 — Personas

| | Persona 1 — António Ferreira | Persona 2 — Carla Sousa |
|---|---|---|
| **Responsável** | Miguel | Francisco |
| **Perfil** | 72 anos, viúvo, ex-agricultor, 4ª classe | 46 anos, enfermeira, casada |
| **Papel** | Utilizador Principal (paciente) | Utilizador Secundário (cuidadora) |
| **Literacia digital** | Muito baixa | Acima da média |
| **Principal objetivo** | Nunca falhar um medicamento | Monitorizar o pai à distância e ser alertada rapidamente |
| **Principal frustração** | Texto pequeno, menus complexos | Apps sem suporte a monitorização remota |

---

## Slide 4 — Cenários de Utilização

**Cenário 1 — Confirmar Toma (António)**
- António recebe alerta sonoro às 09:00 para a Amlodipina 5mg
- Vê notificação grande com botões "Tomei" e "Adiar"
- Toma o medicamento e confirma com um toque
- Recebe visto verde com hora registada

**Cenário 2 — Toma Ignorada (Carla)**
- Carla recebe alerta crítico: pai não confirmou toma das 15:00
- Verifica estado vermelho no dashboard, liga ao pai
- Acompanha mudança de estado em tempo real: **vermelho → amarelo**
- Revê histórico semanal e identifica padrão de falhas

---

## Slide 5 — Análise de Tarefas (HTA)

**HTA Cenário 1** *(Miguel)*
```
0. Confirmar toma
  1. Receber alerta → 2. Tomar medicamento → 3. Confirmar na app → 4. (Opcional) Ver resumo diário
```

**HTA Cenário 2** *(Francisco)*
```
0. Reagir a toma ignorada
  1. Receber alerta → 2. Consultar detalhe na app → 3. Contactar pai
  → 4. Monitorizar confirmação → 5. (Opcional) Analisar padrão semanal
```

**Abordagem comum:** reduzir ao mínimo o número de toques para atingir o objetivo em ambos os cenários.

---

## Slide 6 — Estilos e Dispositivos de Interação

**Estilos de interação definidos pela equipa:**

| Estilo | Descrição |
|---|---|
| Manipulação direta | Toques largos e gestos swipe |
| Menus e listas | Navegação inferior e listas cronológicas simples |
| Notificações proativas | Push notifications para alertas de toma |

**Dispositivo principal:** Smartphone Android com ecrã tátil

**Acessibilidade:** botões ≥ 48×48dp · som e vibração complementares · compatível com TalkBack

---

## Slide 7 — Princípios de Usabilidade (Nielsen)

| Princípio | Aplicação na EasyMed |
|---|---|
| **Visibilidade do Estado do Sistema** | Código de cores em tempo real: Verde = Tomado · Amarelo = Atrasado · Vermelho = Ignorado |
| **Prevenção de Erros** | Swipe ou botões com confirmação clara para evitar *fat-finger errors* |
| **Correspondência com o Mundo Real** | Linguagem natural ("Tomei"), ícones de medicamentos, listas como agenda diária |
| **Estética e Design Minimalista** | Ecrãs limpos sem distrações, focados na ação essencial de cada momento |

---

## Slide 8 — Protótipos Figma e Navegação

**Cenário 1 — Paciente (Miguel)**

| Ecrã | Descrição |
|---|---|
| M1 | Ecrã bloqueado com notificação de alerta |
| M2 | Notificação expandida com "Tomei" e "Adiar" |
| M3 | Confirmação com visto verde e timestamp |
| M4 | Ecrã principal com resumo das tomas do dia |

*Fluxo linear:* M1 → M2 → M3 → M4

**Cenário 2 — Cuidador (Francisco)**

| Ecrã | Descrição |
|---|---|
| F1 | Ecrã bloqueado com notificação de toma ignorada |
| F2 | Dashboard do cuidador com estado do paciente |
| F3 | Detalhe das tomas diárias por medicamento |
| F4 | Histórico semanal e relatório de aderência |

*Fluxo concêntrico:* F1 → F2 → Perfil do pai (hub central) → F3 / Chamada / F4

**Guia de estilo unificado:** Cor primária `#4A90E2` · Tipografia Roboto · Botões mínimos 48×48dp

---

## Slide 9 — Organização do Trabalho e Scrum

- **Metodologia:** Scrum com divisão clara de tarefas por elemento
- **Ferramentas:**
  - Trello — gestão do backlog e sprints
  - GitHub — versionamento de relatórios e documentação
  - Figma — design colaborativo com guia de estilo partilhado
  - WhatsApp — comunicação e alinhamento contínuo
- **Divisão do trabalho:** Cenário 1 (Miguel) vs Cenário 2 (Francisco), com validação mútua contínua

---

## Slide 10 — Utilização de IA no Projeto

- **Ferramenta:** Claude (Anthropic) — usado como co-piloto por ambos os elementos
- **Casos de uso:**
  - Estruturação e revisão de textos
  - Brainstorming para criação de personas
  - Geração de código Mermaid para diagramas HTA e navegação
  - Suporte ao layout inicial no Figma via MCP
- **Compromisso ético:** todo o conteúdo gerado por IA foi revisto, validado e aprovado manualmente, garantindo alinhamento com a teoria IPC

---

## Slide 11 — Reflexão Final da Equipa

> **Miguel:** "O design tem de se adaptar ao utilizador com baixa literacia digital — feedback imediato e botões grandes não são opcionais, são a base."

> **Francisco:** "A app só funciona se o cuidador tiver uma interface rápida e sem sobrecarga para monitorizar e agir em situações de crise. O utilizador secundário é tão crítico quanto o principal."

**Conclusão:** O Figma permitiu materializar estes conceitos num protótipo visualmente coerente, com uma linguagem de design unificada e muito próximo de uma aplicação real.
