const { createApp } = Vue

createApp({
	data() {
		return {
      clientsBD: [],
      apiBD: [],
      form: {
        name: '',
        lastName: '',
        email: ''
    },
    
		}
	},
	created() {
    this.axios(); 
        
	},
	mounted() {},
	methods: {
    axios() {
      axios.get('http://localhost:8080/api/clients')
      .then((response)=>  {
      this.clientsBD = response.data
    }).catch(function (error) {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // error.request is an instance of XMLHttpRequest in the browser and an instance of
        // http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
      console.log(error.config);
    });
    },

    submit(){
      axios.post("http://localhost:8080/rest/clients", this.form).then(() => {
        console.log(this.form)
      })
      .then(() => this.axios());
    },
    /*
    idClient(client) {
      let idLink = client._links.self.href
      return idLink.split("http://localhost:8080/api/clients")[1]
   },
    
    deleteClient(client) {
      this.axios()
      let id = this.idClient(client)
      axios.delete("/clients/" + id).then((x) => console.log(x)).catch(x => x)
      this.axios()
    },
 */
},
computed() {},
}).mount('#app')
