import Vue from 'vue'
import VueRouter from 'vue-router'
import Fornecedor from '../views/Fornecedor.vue'
import Categoria from '../views/Categoria.vue'
import Produto from '../views/Produto.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'produto',
    component: Produto
  },
  {
    path: '/fornecedores',
    name: 'fornecedor',
    component: Fornecedor
  },
  {
    path: '/categorias',
    name: 'categoria',
    component: Categoria
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
