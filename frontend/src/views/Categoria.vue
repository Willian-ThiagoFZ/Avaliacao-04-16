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
          :items="categorias"
          sort-by="name"
          class="elevation-3 mt-5 font-weight-bold"
        >
          <template v-slot:top>
            <v-toolbar
              flat
            >
              <v-toolbar-title>Lista de Categorias de Produtos</v-toolbar-title>
              <v-divider
                class="mx-4"
                inset
                vertical
              ></v-divider>
              <v-spacer></v-spacer>
              <v-dialog
                v-model="dialog"
                max-width="500px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="success"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    NOVA CATEGORIA
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
                          sm="12"
                          md="12"
                        >
                          <v-text-field
                            v-model="editedItem.name"
                            label="Nome da Categoria"
                            required
                          ></v-text-field>
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

  import Categorias from '../service/categoria'

  export default {
    name: "categoria",
    data: () => ({
      alert: null,
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'NOME DA CATEGORIA',
          align: 'start',
          sortable: 'name',
          value: 'name',
        },
        { text: 'AÇÕES', value: 'actions', align: 'end', sortable: false },
      ],
      categorias: [],
      editedIndex: -1,
      editedItem: {
        id: null,
        name: '',
      },
      defaultItem: {
        id: null,
        name: '',
      },
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Nova Categoria' : 'Editar Categoria'
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
        Categorias.listar().then(response => {
          this.categorias = response.data
        })
      },

      editItem (item) {
        this.editedIndex = this.categorias.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },

      deleteItem (item) {
        this.editedIndex = this.categorias.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },

      deleteItemConfirm () {
        Categorias.deletar(this.editedItem).then(() => {
          this.categorias.splice(this.editedIndex, 1)
          this.closeDelete()
          this.alert = {type: 'success', text: 'Categoria foi Deletada com Sucesso'}
        }).catch(() => {
          this.alert = {type: 'error', text: 'Categoria em Uso ou não foi Encontrado'}
          this.closeDelete()
        })
      },

      close () {
        this.dialog = false
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
          Categorias.atualizar(this.editedItem).then(response => {
            Object.assign(this.categorias[this.editedIndex], response.data)
            this.close()
            this.alert = {type: 'success', text: 'Categoria foi Atualizada com Sucesso'}
          }).catch(e => {
            this.alert = {type: 'error', text: e.response.data.message}
            this.close()
          })
        } else {
          Categorias.adicionar(this.editedItem).then(response => {
            this.categorias.push(response.data)
            this.close()
            this.alert = {type: 'success', text: 'Categoria Cadastrada com Sucesso'}
          }).catch(e => {
            this.alert = {type: 'error', text: e.response.data.message}
            this.close()
          })
        }
      },
    },
  }
</script>