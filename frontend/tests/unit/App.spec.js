import { mount } from "@vue/test-utils"
import App from "../../src/App"

const wrapper = mount(App);
describe("APP", () => {

    test('Verifica se é uma instância do Vue', () => {
        expect(wrapper.vm).toBeTruthy();
    }),

    test('verifica se o nav-bar esta inicia fechado', () => {
        expect(wrapper.vm.$data.drawer).toEqual(false);
    }),

    test('verifica todas as rotas do Nav-bar', () => {
        expect(wrapper.vm.$data.items).toEqual([
            { title: 'Produtos', icon: 'mdi-cart-arrow-down', to: '/'},
            { title: 'Fornecedor', icon: 'mdi-airplane-check', to: '/fornecedores'},
            { title: 'Categoria dos Produtos', icon: 'mdi-view-dashboard', to: '/categorias'},
          ]);
    })

})