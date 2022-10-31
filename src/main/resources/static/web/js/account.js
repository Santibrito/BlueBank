const { createApp } = Vue

createApp({
	data() {
		return {
      clientAccounts: [],
      dataApi: [],
		}
	},
	created() {
    
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');
    this.showAccount(myParam)
    
	},
	mounted() {},

	methods: {
    showAccount(id){
      axios.get(`/api/accounts/${id}`)
      .then((response)=>{
          this.dataApi=response.data
          this.clientAccounts=this.dataApi.transaction
          this.clientAccounts = [...this.clientAccounts].sort((a, b) => b.id - a.id)
          console.log(this.dataApi);
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
