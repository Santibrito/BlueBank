const { createApp } = Vue

createApp({
	data() {
		return {
      clientBD: [],
      clientLoans:  [],
      cardType: '',
      cardColor: '',
      cardColorFilter: '',
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
      this.cards = this.clientBD.cards;
      this.cardsFilter = this.clientBD.card.sort((a, b) => {return a.id - b.id;})
      this.cardColorFilter = this.cardsFilter.map(card => card.color)
      console.log(this.cardColor.length);

    })
    },
    createCard() {
        axios
          .post(
            "/api/clients/current/cards",
            `cardType=${this.cardType}&cardColor=${this.cardColor}`,
            { headers: { "content-type": "application/x-www-form-urlencoded" } }
          )
          .then((response) => {
            console.log("created" + response);
            return (window.location.href = "/web/cards.html");
          });
      },
},
computed() {},
}).mount('#app')

