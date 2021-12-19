<template>
  <v-container>
    <v-row>
      <v-col
        cols="12"
        md="12"
      >
      <v-alert elevation="6" v-if="this.alert" :type="this.alert.type">{{ this.alert.text }}</v-alert>
        <v-data-table
          :headers="headers"
          :items="produtos"
          sort-by="name"
          class="elevation-3 mt-5 font-weight-bold"
        >
          <template v-slot:top>
            <v-toolbar
              flat
            >
              <v-toolbar-title>Lista de Produtos</v-toolbar-title>
              <v-divider
                class="mx-4"
                inset
                vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
                v-model="dialog"
                max-width="800px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="success"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    ADICIONAR PRODUTO
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="text-h5">{{ formTitle }}</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.name"
                            label="Nome do Produto"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.precoCompra"
                            label="Preço de Compra"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="4"
                          md="4"
                        >
                          <v-text-field
                            v-model="editedItem.precoVenda"
                            label="Preço de Venda"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="6"
                          md="6"
                        >
                          <v-select
                            v-model="editedItem.tipoProduto"
                            :items="categorias"
                            label="Categoria do Produto"
                            item-text="name"
                            return-object
                          ></v-select>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="6"
                          md="6"
                        >
                          <v-select
                            v-model="editedItem.fornecedor"
                            :items="fornecedores"
                            label="Fornecedor"
                            item-text="name"
                            return-object
                          ></v-select>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="close"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="save"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog
                v-model="dialogEstoque"
                max-width="650px"
              >
                <v-card>
                  <v-card-title>
                    <span class="text-h5">Controle de Estoque</span>
                  </v-card-title>

                  <v-card-text>
                    <v-container>
                      <v-row>
                        <v-col
                          cols="12"
                          sm="6"
                          md="6"
                        >
                          <v-text-field
                            v-model="editedItem.name"
                            label="Nome do Produto"
                            disabled
                          ></v-text-field>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="6"
                          md="6"
                        >
                          <v-text-field
                            v-model="editedItem.balance"
                            label="Quantidade Atual de Estoque"
                            disabled
                          ></v-text-field>
                        </v-col>
                        <v-col
                          cols="12"
                          sm="12"
                          md="12"
                        >
                          <v-text-field
                            v-model="estoque"
                            label="Valor de produtos para ADICIONAR ou REMOVER do ESTOQUE"
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-card-text>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="blue darken-1"
                      class="mr-4"
                      text
                      @click="close"
                    >
                      Cancel
                    </v-btn>
                    <v-btn
                      color="success"
                      class="mr-4"
                      @click="addEstoque"
                    >
                      Adcionar no Estoque
                    </v-btn>

                    <v-btn
                      color="error"
                      class="mr-4"
                      @click="removeEstoque"
                    >
                      Remover do Estoque
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-dialog v-model="dialogDelete" max-width="430px">
                <v-card>
                  <v-card-title class="text-h5">Você deseja remover este registro?</v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                    <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                    <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-toolbar>
          </template>
          <!-- eslint-disable-next-line -->
          <template v-slot:item.actions="{ item }">
            <v-icon
              medium
              class="mr-3"
              color="orange darken-4"
              @click="editEstoque(item)"
            >
              mdi-cart-arrow-down
            </v-icon>
            <v-icon
              medium
              class="mr-2"
              color="primary"
              @click="editItem(item)"
            >
              mdi-pencil
            </v-icon>
            <v-icon
              medium
              color="red"
              @click="deleteItem(item)"
            >
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

  import Produtos from '../service/produto'
  import Categorias from '../service/categoria'
  import Fornecedores from '../service/fornecedor'

  export default {
    name: "produto",
    data: () => ({
      alert: null,
      dialog: false,
      dialogEstoque: false,
      dialogDelete: false,
      headers: [
        {
          text: 'NOME DO PRODUTO',
          align: 'start',
          value: 'name',
        },
        {
          text: 'QUANTIDADE EM ESTOQUE',
          value: 'balance',
        },
        {
          text: 'PREÇO DE COMPRA',
          value: 'precoCompra',
        },
        {
          text: 'PREÇO DE VENDA',
          value: 'precoVenda',
        },
        {
          text: 'CATEGORIA',
          value: 'tipoProduto.name',
        },
        {
          text: 'FORNECEDOR',
          value: 'fornecedor.name',
        },
        { text: 'AÇÕES', value: 'actions', align: 'end', sortable: false },
      ],
      produtos: [],
      categorias: [],
      fornecedores: [],
      estoque: 0,
      editedIndex: -1,
      editedItem: {
        id: null,
        name: '',
        precoCompra: null,
        precoVenda: null,
        fornecedor: null,
        tipoProduto: null,
      },
      defaultItem: {
        id: null,
        name: '',
        precoCompra: null,
        precoVenda: null,
        fornecedor: null,
        tipoProduto: null,
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Novo Produto' : 'Editar Produto'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

    created () {
      this.initialize()
    },

    methods: {
      initialize () {
        Produtos.listar().then(response => {
          this.produtos = response.data
        })

        Categorias.listar().then(response => {
          this.categorias = response.data
        })

        Fornecedores.listar().then(response => {
          this.fornecedores = response.data
        })
      },

      editItem (item) {
        this.editedIndex = this.produtos.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      editEstoque (item) {
        this.editedIndex = this.produtos.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogEstoque = true
      },

      deleteItem (item) {
        this.editedIndex = this.produtos.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },

      deleteItemConfirm () {
        Produtos.deletar(this.editedItem).then(() => {
          this.produtos.splice(this.editedIndex, 1)
          this.closeDelete()
          this.alert = {type: 'success', text: 'Produto foi Deletado com Sucesso'}
        }).catch(() => {
          this.alert = {type: 'error', text: 'Produto em Uso ou não foi Encontrado'}
          this.closeDelete()
        })
      },

      close () {
        this.dialog = false
        this.dialogEstoque = false
        this.estoque = 0
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      save () {
        if (this.editedIndex > -1) {
          Produtos.atualizar(this.editedItem).then(response => {
            Object.assign(this.produtos[this.editedIndex], response.data)
            this.close()
            this.alert = {type: 'success', text: 'Produto foi Atualizado com Sucesso'}
          }).catch(e => {
            this.alert = {type: 'error', text: e.response.data.message}
            this.close()
          })
        } else {
          Produtos.adicionar(this.editedItem).then(response => {
            this.produtos.push(response.data)
            this.close()
            this.alert = {type: 'success', text: 'Produto Cadastrado com Sucesso'}
          }).catch(e => {
            this.alert = {type: 'error', text: e.response.data.message}
            this.close()
          })
        }
      },

      addEstoque () {
        Produtos.adicionarNoEstoque(this.editedItem, parseInt(this.estoque)).then(response => {
          Object.assign(this.produtos[this.editedIndex], response.data)
          this.close()
          this.alert = {type: 'success', text: 'Produtos Adicionados no Estoque com Sucesso!!'}
        }).catch(e => {
          this.alert = {type: 'error', text: e.response.data.message}
          this.close()
        })
      },

      removeEstoque () {
        Produtos.removerDoEstoque(this.editedItem, parseInt(this.estoque)).then(response => {
          Object.assign(this.produtos[this.editedIndex], response.data)
          this.close()
          this.alert = {type: 'success', text: 'Produtos Removidos do Estoque com Sucesso!!'}
        }).catch(e => {
          this.alert = {type: 'error', text: e.response.data.message}
          this.close()
        })
      },

    },
  }
</script>