import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Fornecedor from '../views/Fornecedor.vue'
import Categoria from '../views/Categoria.vue'
import Produto from '../views/Produto.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/produtos',
    name: 'Produto',
    component: Produto
  },
  {
    path: '/fornecedores',
    name: 'Fornecedor',
    component: Fornecedor
  },
  {
    path: '/categorias',
    name: 'Categoria',
    component: Categoria
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
