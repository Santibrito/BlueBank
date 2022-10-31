const { createApp } = Vue

createApp({
	data() {
		return {
      email: '',
    password: '',
    emailRegister: '',
    passwordRegister: '',
    name: '',
    lastName:   '',
    msjError: '',
		}
	},
	created() {


 
	},
	mounted() {},

	methods: {
    login() {
        axios.post("/api/login", "email=" + this.email + "&password=" + this.password, {
					headers: { 'content-type':'application/x-www-form-urlencoded' },
		}).then(response => {
            window.location.href = '/web/accounts.html'
        })
        .catch(function (error) {
            
            if (error.response) {
              
              console.log(error.response.data);
              console.log(error.response.status);
              console.log(error.response.headers);
              document.getElementById('msjError').innerHTML = "* Usuario o contraseña incorrecta, Vuelve a intentarlo"
            } else if (error.request) {
             
              console.log(error.request);
            } else {
              

              console.log('Error', error.message);
            }
            console.log(error.config);
          });

        
    },

    logout() {
        axios.post('api/logout').then(response => window.location.href = '/index.html')
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
    
    register(){
        axios.post('/api/clients',"name="+this.name+"&lastName="+ this.lastName +"&email="+this.emailRegister+"&password="+this.passwordRegister,{headers:{'content-type':'application/x-www-form-urlencoded'}})
        .then(response => {
           this.email = this.emailRegister,
           
            this.password = this.passwordRegister,
        this.login()
        }) .catch(function (error) {
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
     
       
    }

},


computed() {},
}).mount('#app')

