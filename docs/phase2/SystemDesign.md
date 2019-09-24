<h2> CRC CARDS </h2>

<h3> -> Class Name: OfficialGame </h3>

Parent Class (if any): StateBasedGame (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Puts all of the game states together
- Main class; where we execute the whole game
- Creates the initial display window

Collaborators:
- LoginState
- LoginAttemptState
- RegisterState
- MenuState
- StoreState
- LeaderboardState
- StatsState
- GameState

<h3> -> Class Name: LoginState</h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays the Login/Register and Exit Game buttons
- When button is pressed, go to appropriate state/view
- Goes back to main menu

Collaborators:
- OfficialGame 

<h3> -> Class Name: LoginAttemptState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays username and password textfield
- Checks if the user has entered the correct username and password
- Goes back to main menu

Collaborators:
- OfficialGame 

<h3> -> Class Name: RegisterState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays username, password, email, DOB, first name, and last name textfield
- Checks for valid characters used 

Collaborators:
- OfficialGame 

<h3> -> Class Name: MenuState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Buttons to start singleplayer game, view leaderboards, view personal stats, view store
- Goes back to main menu

Collaborators:
- OfficialGame 

<h3> -> Class Name: LeaderBoardState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays the top scores in the game
- User able to search for their name in the global leaderboard
- Button to return to MenuState

Collaborators:
- OfficialGame 

<h3> -> Class Name: StatsState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays users personal highscore 
- Displays other interesting stats in the game (ex. total zombie kills)
- Button to return to MenuState

Collaborators:
- OfficialGame 

<h3> -> Class Name: StoreState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays top selling items
- User can buy retextures of the game in here
- Button to return to MenuState

Collaborators:
- OfficialGame 

<h3> -> Class Name: GameState </h3>

Parent Class (if any): BasicGameState (from Slick2D lib)

Classname Subclasses (if any): NONE

Responsibilities:
- Displays the player
- Displays the map 
- Displays the HUD
- Player can kill zombies 
- Accumulate points over the duration of the game 
- Spawn zombies when it can
- Player buys upgrades/weapons
- Menu to end/quit game

Collaborators:
- OfficialGame 

<h3> -> Class Name: Actor </h3>

Parent Class (if any): NONE

Classname Subclasses (if any): Player, Zombie

Responsibilities:
- Movement for actor
- Increment/decrement health for actor

Collaborators:
- Player
- Zombie

<h3> -> Class Name: Player </h3>

Parent Class (if any): Actor

Classname Subclasses (if any): NONE

Responsibilities:
- Movement for actor
- Increment/decrement health for actor
- Keep track of kills
- Keep track of ammo 
- Keep track of the weapons the player is holding

Collaborators:
- NONE

<h3> -> Class Name: Zombie </h3>

Parent Class (if any): Actor

Classname Subclasses (if any): NONE

Responsibilities:
- Movement for actor
- Increment/decrement health for actor
- AI for zombies (path finding)

Collaborators:
- NONE

<h3> -> Class Name: Weapon </h3>

Parent Class (if any): NONE

Classname Subclasses (if any): NONE

Responsibilities:
- Shoot when player clicks to shoot
- Deal damage to actor if collision happens

Collaborators:
- Player

<h3> -> Class Name: GameLogic </h3>

Parent Class (if any): NONE

Classname Subclasses (if any): NONE

Responsibilities:
- Keep track of all actor's positions on the map 
- Spawning algorithm for zombies
- A store on the map for the player to buy items
- Each level, increase difficulty 

Collaborators:
- Player
- Zombie
- Weapon
