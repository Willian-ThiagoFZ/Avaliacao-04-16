import { http } from './config'

export default {

  listar: () => {
    return http.get('produto')
  },

  adicionar: (produto) => {
    return http.post('produto', produto)
  },

  atualizar: (produto) => {
    return http.put('produto', produto)
  },

  deletar: (produto) => {
    return http.delete(`produto/${produto.id}`)
  },

  // ESTOQUE

  adicionarNoEstoque: (produto, quantidade) => {
    return http.patch(`produto/addstock/${produto.id}`, {"quantity": quantidade})
  },

  removerDoEstoque: (produto, quantidade) => {
    return http.patch(`produto/removestock/${produto.id}`, {"quantity": quantidade})
  },

}