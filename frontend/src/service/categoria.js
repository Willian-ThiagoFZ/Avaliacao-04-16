import { http } from './config'

export default {

  listar: () => {
    return http.get('tipoProduto')
  },

  adicionar: (categoria) => {
    return http.post('tipoProduto', categoria)
  },

  atualizar: (categoria) => {
    return http.put('tipoProduto', categoria)
  },

  deletar: (categoria) => {
    return http.delete(`tipoProduto/${categoria.id}`)
  },

}