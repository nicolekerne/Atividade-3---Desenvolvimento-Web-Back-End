import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [descricao, setDescricao] = useState('')
  const [dataInicio, setDataInicio] = useState('')
  const [dataFim, setDataFim] = useState('')
  const [mensagem, setMensagem] = useState('')
  const [projetos, setProjetos] = useState([])
  const [erroLista, setErroLista] = useState('')

  async function carregarProjetos() {
    try {
      const resposta = await fetch('http://localhost:8081/api/projetos')

      if (!resposta.ok) {
        throw new Error('Não foi possível carregar os projetos.')
      }

      const dados = await resposta.json()
      setProjetos(dados)
      setErroLista('')
    } catch (erro) {
      console.error('Erro ao carregar projetos:', erro)
      setErroLista('Não foi possível carregar os projetos. Verifique se o backend está rodando.')
    }
  }

  useEffect(() => {
    carregarProjetos()
  }, [])

  async function cadastrarProjeto(event) {
    event.preventDefault()
    setMensagem('')

    try {
      const resposta = await fetch('http://localhost:8081/api/projetos', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          descricao,
          dataInicio,
          dataFim,
        }),
      })

      if (!resposta.ok) {
        const erro = await resposta.text()
        throw new Error(erro || 'Não foi possível cadastrar o projeto.')
      }

      setMensagem('Projeto cadastrado com sucesso!')
      setDescricao('')
      setDataInicio('')
      setDataFim('')

      await carregarProjetos()
    } catch (erro) {
      console.error('Erro ao cadastrar projeto:', erro)
      setMensagem(erro.message || 'Erro ao cadastrar o projeto.')
    }
  }

  return (
    <main className="container">
      <header>
        <h1>Controle de Projetos</h1>
        <p>Cadastre e organize seus projetos.</p>
      </header>

      <section className="card">
        <h2>Novo projeto</h2>

        <form onSubmit={cadastrarProjeto}>
          <label htmlFor="descricao">Descrição</label>
          <input
            id="descricao"
            type="text"
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            placeholder="Digite a descrição do projeto"
            required
          />

          <label htmlFor="dataInicio">Data de início</label>
          <input
            id="dataInicio"
            type="date"
            value={dataInicio}
            onChange={(e) => setDataInicio(e.target.value)}
            required
          />

          <label htmlFor="dataFim">Data de término</label>
          <input
            id="dataFim"
            type="date"
            value={dataFim}
            onChange={(e) => setDataFim(e.target.value)}
            required
          />

          <button type="submit">Cadastrar projeto</button>
        </form>

        {mensagem && <p className="mensagem">{mensagem}</p>}
      </section>

      <section className="card">
        <h2>Projetos cadastrados</h2>

        <button type="button" onClick={carregarProjetos}>
          Atualizar lista
        </button>

        {erroLista && <p className="mensagem">{erroLista}</p>}

        {!erroLista && projetos.length === 0 && (
          <p>Nenhum projeto cadastrado.</p>
        )}

        {projetos.map((projeto) => (
          <article className="projeto" key={projeto.id}>
            <h3>{projeto.descricao}</h3>
            <p>
              <strong>Data de início:</strong> {projeto.dataInicio}
            </p>
            <p>
              <strong>Data de término:</strong> {projeto.dataFim}
            </p>
          </article>
        ))}
      </section>
    </main>
  )
}

export default App