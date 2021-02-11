Explain how you ensure user is the right one starting the app :
	
	Nous avons créer une activité "Login" qui, à l'ouverture de l'application, demande à l'utilisateur d'entrer un code PIN de 8 caractères. Ce code est ensuite 
	hashé en SHA-256 puis comparé au hash du code PIN valide (ici 14576485).

How do you securely save user's data on your phone ?

	On utilise la librairie SQLCipher qui chiffre la base de données en AES-256.

How did you hide the API url ?

	On obfusque le code de notre application afin d'empecher la lecture des différentes variable contenues.

	