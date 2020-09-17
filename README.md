# **Champion Recommendations**

##A *unique* Account Balance Manager and Champion Recommender 
##-Helps you keep tabs on your league balance so you know exactly when you can buy something
##-Suggests which [League Of Legends](https://na.leagueoflegends.com/en-us/) Champions you will enjoy playing

*Champion Recommendations* will:
- Help you track your balances
- Allow you to check up on your progress towards purchasing champions
- Allow you to track your in game transactions and how they impact your balances
- Recommend champions based on a desired difficulty so you can ease into the game and progress
- Track your recommended champions 
- Allocate your in-game currency effectively so you can purchase champions you want
- Allow you to create lists of favorite champions

*Champion Recommendations* is ideal for:
- Players who want to keep a tab on their balances
- New players who have yet to pick a beginning champion
- Players that want to track their progress towards acquiring certain champions
- Novice players who are looking to grow their champion pool
- Players who wish to categorize their favorite champions and
- Any player who wishes to best allocate their in-game currency for champions

*Champion Recommendations* was designed with the intentions of introducing more of my friends to the wonderful game that 
is [League Of Legends](https://na.leagueoflegends.com/en-us/). League has been a large part of my life as I have been 
playing for more than 5 years and have spent over 3000 hours on the game. I have made numerous friends through league
and have introduced numerous other friends to league. The majority of them love the game but find it difficult to delve
into such an expanse game with almost 150 unique champions and limited in-game currency to purchase them with. 
Hence, I was inspired to create this project to ease them into the game and help them determine where to track and 
allocate their in-game currency to buy champions they will most likely enjoy. This includes recommending them a champion
to start with and introduce them to more advanced champions that they could explore once they have more experience with 
the game.

##User Stories
- As a user, I want to maintain an account with a name, region, and balances for each of my currencies
- As a user, I want to purchase a champion with both types of currency
- As a user, I want to track my progress towards purchasing a desired champion using both types of currency
- As a user, I want to be able to visualize all of my account details on screen in a account summary
- As a user, I want to receive champion recommendations based on a desired champion difficulty
- As a user, I want to be able to favourite champions and add them to a collection dubbed *Favourites*
- As a user, I want to be able to save my accounts
- As a user, I want to be able to load my accounts

##Instructions for Grader
- Fullscreen the window on start-up to see all buttons
- There is a background image and start-up sound when you run the application
- Each button when clicked will display the options with an icon that is related to the function of that option
- Earning either currency can be done by inputting the account that earned/purchased the currency along with the amount earned/spent
- Purchasing a champion with either type of currency can be done by inputting the account that you wish to purchase it for along with the name of the champion
- Purchasing any champion will play an audio component of their catchphrase when you select them in game
- Checking if a champion is purchasable with either type of currency either type of currency can be done by inputting the account that you wish to purchase it for along with the name of the champion
- Receiving a recommendation can be done by inputting a difficulty level of champions you wish to be recommended
- Favouriting a champion can be done by inputting the account that you wish to favourite it for along with the name of the champion
- You can view all your account details (balances, champions owned/recommended/favourited) by printing out the details for a select account
- The save button saves all account details to file
- Likewise the load button loads all account details from the file

##Phase 4:Task 2
- I have chosen to make a class robust
- The class I have chosen is the LeagueOfLegendsAccount class
- I have made the methods earnInGame(), purchasePremium(), makeBlueEssencePurchase(), and makeRiotPointsPurchase() more robust
- I removed all the required pre-conditions and now all methods throw checked exceptions
- earnInGame() needed non-negative values and now throws the NegativeValue exception
- purchasePremium() needed certain integer values that corresponded to select gift card values and now throws the ImpossibleValue exception
- both the makePurchase methods used to require a specific champion name but now they throw the PurchaseFail exception
- All the classes have the required tests in LeagueOfLegendsAccountTest (one for when the exception is expected and another where the exception is not expected)

##Phase 4: Task 3
- I have identified low cohesion in my LeagueChampionApp class
- Often I have to make:
- *JFrame*,
- *JButton*, 
- *JLabel*,
- *JTextField* 
- objects when the purpose of the class is actually to run the execution of user input and not construct graphical elements
- Thus I made separate classes that perform the creation of such objects 
- I also added implementation in each of these classes that modifies the objects to my desired size and position
- This allows me to refactor a lot of lines and separate the creation and modification of these objects from the processing of user input increasing the cohesion of my LeagueChampionApp class
