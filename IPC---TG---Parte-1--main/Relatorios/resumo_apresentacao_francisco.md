# Resumo da Apresentação — Francisco Soudo (14060)

**Projeto:** EasyMed · Equipa 14 · IPBeja / ESTIG · 2025/2026

---

## 1. Sistema Semelhante Analisado: MyTherapy

| | |
|---|---|
| **Design** | Interface limpa, tons azul/branco, baixa carga cognitiva |
| **Funcionalidades** | Lembretes avançados, inventário, diário de saúde, modo Team, relatórios PDF |
| **Pontos Positivos** | Acessibilidade, agenda visual clara, confirmação segura de tomas, gamificação |
| **Pontos Negativos** | Risco de sobrecarga funcional, inserção demorada, notificações difíceis de distinguir, navegação de configurações complexa |

**Lição aplicada à EasyMed:** evitar excesso de funcionalidades; focar na simplicidade.

---

## 2. Persona 2: Carla Sousa (Utilizador Secundário)

- **Perfil:** Mulher, 46 anos, enfermeira, residente em Beja
- **Papel:** Cuidadora familiar do pai (74 anos, pós-AVC, 5 medicamentos diários)
- **Literacia digital:** Acima da média
- **Objetivos:**
  - Ser alertada imediatamente se o pai falhar uma toma
  - Acompanhar histórico de aderência para partilhar em consultas médicas
- **Frustrações:**
  - Apps sem suporte a monitorização remota
  - Ter de ligar constantemente para confirmar tomas
  - Interfaces com excesso de informação

---

## 3. Cenário de Utilização 2: Toma Ignorada

**Narrativa:** Carla, no final do turno hospitalar, recebe alerta da EasyMed porque o pai não confirmou a toma das 15h (Sinvastatina 20mg).

1. Recebe notificação crítica no telemóvel
2. Abre a app — vê ícone vermelho no perfil do pai
3. Liga ao pai (adormeceu); instrui-o a tomar o medicamento
4. Acompanha em tempo real a mudança de estado: **vermelho → amarelo** (toma com atraso confirmada)
5. Revê resumo semanal — identifica padrão de falhas nas tomas da tarde
6. Anota observação para a próxima consulta médica

---

## 4. Análise de Tarefas (HTA — Cenário 2)

```
0. Reagir a notificação de toma ignorada
  1. Receber alerta
      1.1 Sentir vibração/ouvir som
      1.2 Ler notificação
  2. Consultar detalhe na app
      2.1 Abrir EasyMed
      2.2 Aceder ao perfil do pai
      2.3 Identificar medicamento em falta (ícone vermelho)
      2.4 Verificar restantes tomas do dia
  3. Contactar o paciente
      3.1 Ligar ao pai
      3.2 Informar sobre toma em falta
      3.3 Confirmar que vai tomar
  4. Monitorizar confirmação
      4.1 Aguardar confirmação do pai na app
      4.2 Verificar mudança de ícone para amarelo (toma tardia)
  5. (Opcional) Analisar padrão de tomas
      5.1 Consultar resumo semanal
      5.2 Identificar padrões de falhas
      5.3 Registar observação para o médico
```

**Princípio:** Reduzir ao mínimo o número de toques para atingir a ação crítica.

---

## 5. Princípios de Usabilidade Aplicados (Nielsen)

| Princípio | Aplicação |
|---|---|
| **Visibilidade do Estado do Sistema** | Código de cores em tempo real: Verde = Tomado, Amarelo = Atrasado, Vermelho = Ignorado |
| **Prevenção de Erros** | Botões de confirmação com swipe obrigatório — evita *fat-finger errors* |
| **Correspondência com o Mundo Real** | Histórico cronológico semelhante a uma agenda; linguagem não técnica |
| **Estética e Design Minimalista** | Ecrã de crise limpo, focado numa única ação: "Ligar ao Paciente" |

---

## 6. Protótipo Figma — Cenário 2 (Cuidador)

| Ecrã | Descrição |
|---|---|
| **F1** | Ecrã bloqueado com notificação de toma ignorada |
| **F2** | Dashboard do Cuidador com estado dos medicamentos do pai |
| **F3** | Detalhe das tomas diárias com ícones de estado por medicamento |
| **F4** | Histórico semanal e relatório de aderência |

**Fluxo de navegação:** Concêntrico — a notificação leva diretamente à ação central, sem perda de contexto em situações de urgência.

**Guia de estilo unificado:** Cor primária `#4A90E2` · Tipografia Roboto · Botões mínimos 48×48dp

---

## 7. Organização e Reflexão Final

- **Metodologia:** Scrum com gestão de tarefas em Trello; versionamento em GitHub; design em Figma
- **Uso de IA:** Claude (Anthropic) usado como co-piloto para estruturação de textos, geração de diagramas Mermaid e brainstorming; todo o conteúdo foi revisto e validado manualmente
- **Reflexão:** A app só funciona plenamente se o utilizador secundário (cuidador) tiver uma interface rápida e sem sobrecarga para monitorizar e agir em crise — o papel do cuidador é tão crítico quanto o do paciente
