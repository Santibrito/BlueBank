const { createApp } = Vue

createApp({
	data() {
		return {
      clientBD: [],
      clientLoans:  [],
		}
	},
	created() {
    this.axios(); 
	},
	mounted() {},

	methods: {
    axios() {
      axios.get('http://localhost:8080/api/clients/current')
      .then((response)=>  {
      this.clientBD = response.data
      this.clientLoans = this.clientBD.loans;
      

    })
    },
    createAccount(){
      axios.post('/api/clients/current/accounts')
           .then(response =>
                location.reload(),
                console.log('cuenta creada'))
           .catch( error => error.message + "tu cuenta no fue creada")
      },

},
computed() {},
}).mount('#app')


