<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" app color="grey lighten-2">
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6">
            Administração
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list
        dense
        nav
      >
        <v-list-item
          v-for="item in items"
          :key="item.title"
          :to="item.to"
          link
          class="mt-5"
          @click="drawer = !drawer"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider class="mx-2"></v-divider>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app color="primary" dark>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="mr-2">Sistema de Estoque</v-toolbar-title>
      <v-icon>mdi-store-check-outline</v-icon>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data () {
    return {
      items: [
        { title: 'Controle de Estoque', icon: 'mdi-briefcase-check', to: '/'},
        { title: 'Produtos', icon: 'mdi-cart-arrow-down', to: '/produtos'},
        { title: 'Fornecedor', icon: 'mdi-airplane-check', to: '/fornecedores'},
        { title: 'Categoria dos Produtos', icon: 'mdi-view-dashboard', to: '/categorias'},
      ],
      right: null,
      drawer: false,
    }
  },
};
</script>
