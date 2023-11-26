export default {
    data() {
        return {
            candidat: {
                nom: '',
                prenom: '',
                email: '',
                siteWeb: '',
                dateNaissance: '',
                password: '',
            },
            errorMessages: {
                nom: '',
                prenom: '',
                email: '',
                siteWeb: '',
                dateNaissance: '',
                password: ''

            },
            successMessage: '',
        };
    },
    methods: {
        validateForm() {
            let isValid = true;
            // Vérification du nom
            if (!this.candidat.nom) {
                this.errorMessages.nom = 'Le champ Nom est requis.';
                isValid = false;
            } else {
                this.errorMessages.nom = '';
            }

            // Vérification du prénom
            if (!this.candidat.prenom) {
                this.errorMessages.prenom = 'Le champ Prénom est requis.';
                isValid = false;
            } else {
                this.errorMessages.prenom = '';
            }

            // Vérification de l'email
            if (!this.candidat.email) {
                this.errorMessages.email = 'Le champ Email est requis.';
                isValid = false;
            } else {
                this.errorMessages.email = '';
            }
            // Vérification du mot de passe
            if (!this.candidat.password) {
                this.errorMessages.password = 'Le champ Mot de passe est requis.';
                isValid = false;
            } else {
                this.errorMessages.password = '';
            }


            if (!this.candidat.dateNaissance) {
                this.errorMessages.dateNaissance = 'Le champ date de naissance  est requis.';
                isValid = false;
            } else {
                this.errorMessages.dateNaissance = '';
            }



            return isValid;
        },
        submitForm() {
            if (this.validateForm()) {
                console.log('Formulaire soumis:', this.candidat);

                // Ici, vous pouvez envoyer la requête à l'API pour créer le candidat
                // Utilisez this.candidat pour récupérer les données du formulaire

                // Exemple d'appel à l'API (à adapter à votre structure)
                axios.post('/candidat/create', this.candidat)
                    .then(response => {
                        this.successMessage = 'Candidat créé avec succès.';

                    })
                    .catch(error => {
                        if(error.response.status === 409){
                            this.successMessage = 'EMAIL DEJA PRIS ';
                        }
                        console.error('Erreur lors de la création du candidat :', error);
                        this.successMessage = 'Une erreur s\'est produite lors de la création du candidat.';
                    });
            }
        },
    },
    template:
        `
          <div class="container mt-4">
            <h2 class="mb-4">Création de Candidat</h2>

            <!-- Formulaire de création -->
            <form @submit.prevent="submitForm">
              <div class="form-group">
                <label for="nom">Nom:</label>
                <input type="text" class="form-control" id="nom" v-model="candidat.nom" required>
                <small v-if="errorMessages.nom" class="text-danger">{{ errorMessages.nom }}</small>
              </div>
              <div class="form-group">
                <label for="prenom">Prénom:</label>
                <input type="text" class="form-control" id="prenom" v-model="candidat.prenom" required>
                <small v-if="errorMessages.prenom" class="text-danger">{{ errorMessages.prenom }}</small>
              </div>
              <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" v-model="candidat.email" required>
                <small v-if="errorMessages.email" class="text-danger">{{ errorMessages.email }}</small>
              </div>
              <div class="form-group">
                <label for="siteWeb">Site Web:</label>
                <input type="text" class="form-control" id="siteWeb" v-model="candidat.siteWeb">
                <small v-if="errorMessages.siteWeb" class="text-danger">{{ errorMessages.siteWeb }}</small>

              </div>
              <div class="form-group">
                <label for="dateNaissance">Date de Naissance:</label>
                <input  required type="date" class="form-control" id="dateNaissance" v-model="candidat.dateNaissance">

              </div>
              <div class="form-group">
                <label for="password">Mot de passe:</label>
                <input type="password" class="form-control" id="password" v-model="candidat.password" required>
              </div>

              <!-- Bouton de soumission -->
              <button type="submit" class="btn btn-primary">Créer Candidat</button>

              <!-- Affichage du message de succès ou d'erreur -->
              <div v-if="successMessage" class="mt-4">
                <p class="alert alert-success">{{ successMessage }}</p>
              </div>
            </form>
          </div>
        `,
};
