const { createApp } = Vue

createApp({
	data() {
		return {
      clientBD: [],
		}
	},
	created() {
    this.axios(); 
    console.log("Hola");
	},
	mounted() {},

	methods: {
    axios() {
      axios.get('http://localhost:8080/api/clients/current')
      .then((response)=>  {
      this.clientBD = response.data
      this.clientLoans = this.clientBD.loans;
      console.log( this.clientLoans);
    })
    },
    createAccount(){
      axios.post('/api/clients/current/accounts')
           .then(response =>
                location.reload(),
                console.log('cuenta creada'))
           .catch( error => error.message + "tu cuenta no fue creada")
    },
    logout(){
      axios.post('/api/logout').then(response =>   window.location.href = '/index.html')
    }

},
computed() {},
}).mount('#app')


