import { http } from './config'

export default {

  listar: () => {
    return http.get('fornecedor')
  },

  adicionar: (fornecedor) => {
    return http.post('fornecedor', fornecedor)
  },

  atualizar: (fornecedor) => {
    return http.put('fornecedor', fornecedor)
  },

  deletar: (fornecedor) => {
    return http.delete(`fornecedor/${fornecedor.id}`)
  },

}