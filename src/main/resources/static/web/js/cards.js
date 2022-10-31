const { createApp } = Vue

createApp({
	data() {
		return {
      clientBD: [],
      
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
      console.log(this.clientBD.card);
    })
    },
    logout() {
      axios.post('api/logout').then(response => console.log('signed out!!!'))
      .catch(function (error) {
          if (error.response) {
           
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

},
computed() {},
}).mount('#app')
