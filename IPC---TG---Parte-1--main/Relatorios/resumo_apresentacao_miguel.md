# Resumo da Apresentação — Miguel Pauzinho (27131)

**Projeto:** EasyMed · Equipa 14 · IPBeja / ESTIG · 2025/2026

---

## 1. Sistema Semelhante Analisado: Medisafe

| | |
|---|---|
| **Design** | Cores suaves (branco, azul, verde), interface focada na simplicidade |
| **Funcionalidades** | Registo de medicamentos, alertas por horário, confirmação com um toque, histórico de aderência, notificações familiares, relatórios exportáveis |
| **Pontos Positivos** | Visibilidade clara do estado do sistema, simplicidade de uso, ícones visuais por medicamento, alertas personalizáveis |
| **Pontos Negativos** | Registo inicial complexo, funcionalidades premium excessivamente bloqueadas, navegação pouco intuitiva |

**Lição aplicada à EasyMed:** simplicidade e foco na tarefa principal; não bloquear funcionalidades essenciais.

---

## 2. Persona 1: António Ferreira (Utilizador Principal)

- **Perfil:** Homem, 72 anos, viúvo, ex-agricultor, 4ª classe
- **Local:** Aldeia do Bispo (zona rural)
- **Condição:** Toma 4 medicamentos diários para doenças crónicas; dedos com tremor; visão reduzida
- **Literacia digital:** Muito baixa — usa o telemóvel apenas para chamadas e SMS
- **Objetivos:**
  - Nunca falhar um medicamento
  - Saber sempre quais medicamentos já foram tomados no dia
- **Frustrações:**
  - Texto pequeno e menus complexos
  - Notificações que não percebe
  - Excesso de informação no ecrã

---

## 3. Cenário de Utilização 1: Confirmar Toma de Medicamento

**Narrativa:** António recebe um alerta às 09:00 para tomar a Amlodipina 5mg.

1. Ouve som e sente vibração do telemóvel
2. Vê notificação grande com nome do medicamento e dois botões: **"Tomei"** e **"Adiar"**
3. Vai ao armário buscar o medicamento
4. Toma o comprimido com água
5. Prime o botão **"Tomei"** na notificação expandida
6. Recebe confirmação visual: visto verde com hora registada
7. Ao meio-dia, repete o processo para a Metformina
8. No final do dia, o ecrã principal mostra todos os medicamentos marcados a verde

---

## 4. Análise de Tarefas (HTA — Cenário 1)

```
0. Confirmar toma de medicamento
  1. Receber alerta de medicação
      1.1 Ouvir som/sentir vibração
      1.2 Pegar no telemóvel
      1.3 Ler notificação (nome do medicamento e hora)
  2. Tomar o medicamento
      2.1 Ir ao local de armazenamento
      2.2 Identificar o medicamento correto
      2.3 Ingerir o comprimido com água
  3. Confirmar na app
      3.1 Voltar ao telemóvel
      3.2 Premir o botão "Tomei"
      3.3 Verificar mensagem de confirmação (visto verde)
  4. (Opcional) Ver resumo diário
      4.1 Abrir app EasyMed
      4.2 Verificar ecrã principal
      4.3 Confirmar medicamento marcado a verde
```

**Princípio:** Fluxo linear e direto — mínimo de passos entre a notificação e a confirmação.

---

## 5. Princípios de Usabilidade Aplicados (Nielsen)

| Princípio | Aplicação |
|---|---|
| **Visibilidade do Estado do Sistema** | Ecrã principal com estado de cada medicamento por código de cores |
| **Correspondência com o Mundo Real** | Ícones que representam a forma física do medicamento (comprimido, cápsula); linguagem natural "Tomei" |
| **Prevenção de Erros** | Nome completo e dosagem exibidos antes da confirmação; botões "Tomei" e "Adiar" claramente separados para evitar toques acidentais |
| **Reconhecimento em vez de Memorização** | Alertas proativos e listas cronológicas — o utilizador não precisa de memorizar horários |

---

## 6. Protótipo Figma — Cenário 1 (Paciente)

| Ecrã | Descrição |
|---|---|
| **M1** | Ecrã bloqueado com notificação de alerta de toma |
| **M2** | Notificação expandida com botões "Tomei" e "Adiar" |
| **M3** | Ecrã de confirmação com visto verde e hora registada |
| **M4** | Ecrã principal com resumo das tomas diárias |

**Fluxo de navegação:** Linear — Ecrã Bloqueado → Notificação → Confirmação → Ecrã Principal.

**Guia de estilo unificado:** Cor primária `#4A90E2` · Tipografia Roboto · Botões mínimos 48×48dp · Compatível com TalkBack (acessibilidade Android)

---

## 7. Organização e Reflexão Final

- **Metodologia:** Scrum com gestão de tarefas em Trello; versionamento em GitHub; design colaborativo em Figma
- **Uso de IA:** Claude (Anthropic) usado para decomposição de tarefas HTA, geração de diagramas e brainstorming; todo o conteúdo foi revisto e validado manualmente
- **Reflexão:** A acessibilidade tem de ser a prioridade central — o design deve adaptar-se ao utilizador com baixa literacia digital (António) através de feedback imediato, botões grandes e linguagem simples. A EasyMed só é útil se o utilizador principal conseguir usá-la de forma autónoma
