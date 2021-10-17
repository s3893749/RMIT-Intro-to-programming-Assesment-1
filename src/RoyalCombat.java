import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoyalCombat {
	
	//**---------- INTIAL GAME VARIABLES ----------**\\
	//This section is the highest level of code, it allows all variables to be accessible in all subsequential methods.
	
	//Interface Window Size
	static int interfaceWidth = 1280;
	static int interfaceHeight = 720;
	
	//Create GTerm Window
	static GTerm gt = new GTerm(interfaceWidth, interfaceHeight);
	
	//generate new random object for random choices
	static Random random = new Random();

	//-- Player Army Variables --\\
	
		//Set Player Generals Name
			static String generalName = "";
		//Army Strength refers to the amount of health the player has
			static int armyStrength = 50; 
		//The Starting Strength variable is set separately so we can generate the correct amount of dead soldiers at the end of a battle.
			static int armyStartingStrength =  armyStrength;
		//Army Combat level increases your damage output
			static int armyCombatLevel = 0;
		//Army Defense level reduces incoming damage
			static int armyDefenceLevel = 0;
	
	//-- Computer Army Variables --\\
			
		//Creates the array for random enemy names
			static List<String> enemeyGeneral = new ArrayList<String>(); 
		//Sets Initial Computer AI Health
			static int enemyStrength = 50;
		//Create Enemy Starting strength to recreate dead enemy on a battle win.
			static int enemyStartingStrength = enemyStrength;
			
			
	//-- Game Variables --\\
			
			//Game turns to track how long the game has been played
				static int gameTurns = 0;
			//Game Max turns before end
				static int maxTurns = 7;
			//Set the Unit Price for buying new soldiers
				static int unitPrice = 4;
			//Level upgrade cost sets the cost needed to upgrade to buy a new level
				static int levelUpgradeCost = 10;
			//coins are used for currently to buy new men and upgrades
				static int armyCoins = 0;
			//create list of battle location names
				static List<String> battleLocation = new ArrayList<String>(); 

	//-- Interface Variables --\\
				
			//create action feed GUI
					static int actionFeed = -1;
			//create player health GUI | -1 = null 
					static int armyHeathFeed = -1;
			//create enemy Health GUI  | -1 = null
					static int enemyArmyHealthFeed = -1;
	
	
	//**---------- START MAIN FUNCTION ----------**\\
	//The main function is ran at the start of the program and is called first before anything else happens, in this situation im using it as a constructor to initialize the player and game functions.	
					
	public static void main(String[] args) {
		
        //Adds battle locations that the game can choose when loading a battle
	        battleLocation.add("Lilybaeum");
	        battleLocation.add("Malta");
	        battleLocation.add("Catalonia");
	        battleLocation.add("Ticinus");
	        battleLocation.add("Trebia");
	        battleLocation.add("Cissa");
	        battleLocation.add("Ebro River");
	        battleLocation.add("Lake Trasimene");
	        battleLocation.add("Cannea");
        
        //add generals to enemy list for the game to choose when loading a battle
	        enemeyGeneral.add("Hannibal");
	        enemeyGeneral.add("Naughtyus");
	        enemeyGeneral.add("Maximus");
	        
	     //start story *** ADDED VERY LAST MINIUTE !!!
			gt.setXY(0, 0);
			gt.addImageIcon("welcome-screen-1.png");
			gt.showMessageDialog("As you drift in and out of consciousness,\n"
					+ "Memories of your family and village flood your mind,\n"
					+ "A great sadness comes over you you can still smell the air,\n"
					+ "You can see see the houses and soliders in your mind...");
			gt.showMessageDialog("But for now its time to wake up!");
			gt.clear();
			gt.addImageIcon("welcome-screen-2.png");
			gt.showMessageDialog("As you wake up you can see the blood and death all around you,\nYour little village in rome has been brutally attacked by Hannibal of Carthage");
			gt.showMessageDialog("The only thing on your mind now......");
			gt.showMessageDialog("YOU MUST WARM THE CAPITAL!!");
			gt.clear();
			gt.addImageIcon("welcome-screen-3.png");
			gt.showMessageDialog("You muster the last four survivors from the attack and set off to the captial at once...\nYou must warn them of his attack before its too late!");
			gt.clear();
			gt.addImageIcon("forest-ambush.png");
			gt.showMessageDialog("Hannibal's Generals have blocked the way to rome!\n"
					+ "You will need to fight your way to the capital! \n"
					+ "Your men look at you with confidence");
			gt.showMessageDialog("let the battle begin!");
			
			//-- Battle Load Background Art --\\
			
			//Load Grass Background 	** This can be swapped for a random choice array for more variation **
				gt.setXY(0, 0);
				gt.addImageIcon("background-grass.png");
	        
        //check for player is setup by seeing if the player name is not set
			generalName = gt.getInputString("Please enter your name:");
			
		//while loop to lock the player into entering a name
			while(generalName == null || generalName.contentEquals("")) {
				generalName = gt.getInputString("Please enter your generals name:");
				
			}
		
		
		//Once the while loop completes set the default starting values
			armyCombatLevel = 1;
			armyDefenceLevel = 1;
			armyCoins = 50; 
			
		//now load a game battle to start 
			gt.clear();
			game("battle");
	}
	
	
	
	//**---------- START MAIN GAME LOOP ----------**\\
	//The main game functions serves as the function that sets interfaces and keeps track of what bit of the game is currently loading, we can specify the level we wish to load with game("level");
	
	public static void game(String event) {
		//check for the end of the game
		
		if(gameTurns > maxTurns) {
			endBattle();
		}else {
	
		//Increase the turn timer every time the game function is called
			gameTurns ++;
			
		//-- GUI setup --\\
			
			//**Due to time constrains the GUI does not update every time the player performs an action, it only updates when the game is called**
		
		//-- Bottom Tool Bar --\\
			
			//set bottom level indicator for army combat level        
				gt.setXY(interfaceWidth/2-50, interfaceHeight-100);
				gt.setFontSize(20);
				gt.setFontColor(255, 255, 255);
				gt.println(String.valueOf(armyCombatLevel));
				gt.setFontSize(12);
				gt.setFontColor(0, 0, 0);
				gt.setXY(0, 0);
		
			//set bottom level indicator for army defense level		
				gt.setXY(interfaceWidth/2+40, interfaceHeight-100);
				gt.setFontSize(20);
				gt.setFontColor(255, 255, 255);
				gt.println(String.valueOf(armyDefenceLevel));
				gt.setFontSize(12);
				gt.setFontColor(0, 0, 0);
				gt.setXY(0, 0);	

		
			//set bottom level indicator for army coins				
				gt.setXY(interfaceWidth/2+130, interfaceHeight-100);
				gt.setFontSize(20);
				gt.setFontColor(255, 255, 255);
				gt.println(String.valueOf(armyCoins));
				gt.setFontSize(12);
				gt.setFontColor(0, 0, 0);
				gt.setXY(0, 0);
			
		
			//setup bottom level indicator art
				gt.setXY(interfaceWidth/2-120, interfaceHeight-140);
				gt.addImageIcon("bottom-tool-bar-X3.png");
				gt.setXY(0, 0);
		
		//-- Side Bar --\\
		
			//add player health GUI display
				gt.setXY(20, 5);
				armyHeathFeed = gt.addTable(150, 50, "Your Strength");
				gt.setXY(0, 0);
					
			//Setup the live action feed
				gt.setXY(25, 150);
				actionFeed = gt.addTable(200, 420, "latest Actions");
				gt.setXY(0, 0);
		
		//-- Select the correct game event to load --\\
				
		//check event for chosen one and load the event
			if(event.contains("battle")) {
				battle();
			}
			
			if(event.contains("camp")) {
				camp();
			}
		}
		
	}
	
	
	//**---------- START BATTLE EVENT ----------**\\
	
	//This event contains all the primary battle code and serves as a constructor for the "battle" setting up variables and loading additional functions as required.


	private static void battle(){
		
	//-- Battle Variables --\\
		
		//Select a random battle location from the list of locations
			int randomLocation = random.nextInt(battleLocation.size());
		//Sets a random enemy general to fight
			int randomGeneral = random.nextInt(enemeyGeneral.size());
		//Increase army size by game turns, 10 units is 1 man so turn 2 would add 20 HP or 2 men
			int enemyStrengthIncrease = gameTurns*10;
		//Add the increase to the total enemy strength for the battle
			enemyStrength = armyStrength+enemyStrengthIncrease;
		//Set enemy starting strength	<---------------------------->   ** Used later to spawn dead bodies**
			enemyStartingStrength = enemyStrength;
		//set player army starting strength		<---------------------------->   ** Used later to spawn dead bodies**
			armyStartingStrength = armyStrength;
		//Initialize player choice variable for later use.
			String playerChoice = "";

		//Setup Enemy Heath Display updated battleUpdate(); ** DOES NOT DISPLAY ON CAMP **
			gt.setXY(200, 5);
			enemyArmyHealthFeed = gt.addTable(150, 50, "Enemy Strength");
			gt.setXY(0, 0);
			
		//load GUI Update Function ** The Variable input is for at camp so here we are saying atCamp = false **
		battleUpdate(false);
		
	//-- Battle GUI Setup --\\
		
		//Print initial GUI elements to G Term Interface Window
			gt.setXY(500, 10);
			gt.setFontColor(255, 255, 255);
			gt.setFontSize(24);
			gt.println("Battle Of " + battleLocation.get(randomLocation));
			gt.setXY(0, 0);
			
		//Print total game turns to G Term Interface Window
			gt.setXY(610, 40);
			gt.setFontSize(12);
			gt.println("Game turn: " + gameTurns);
			gt.setFontColor(0, 0, 0);
			gt.setXY(0, 0);
			
		//Print Combatants in GUI to Bar
			gt.setXY(500, 70);
			gt.setFontSize(16);
			gt.setFontColor(255, 255, 255);
			gt.println("General " + generalName + " Vs General "+ enemeyGeneral.get(randomGeneral));
			gt.setFontColor(0, 0, 0);
			gt.setXY(0, 0);
		
		//Load GUI Art Work.
	
			gt.addImageIcon("gui.png");
			
			gt.setXY(0, 0);
			gt.addImageIcon("sidebar.png");
			gt.setXY(0, 180);

	//-- Battle Generate Army --\\
			
		//Call Army Generation function
			battleGenerateArmy();
		
	//-- Battle Load Background Art --\\
		
		//Load Grass Background 	** This can be swapped for a random choice array for more variation **
			gt.setXY(0, 0);
			gt.addImageIcon("background-grass.png");
	
		
	//-- Start Player Choice Input Loop --\\
		
		// Request first selection
			playerChoice = gt.getInputString("Choose Your Next Move!\nDefend, Retreat, Attack");

		// Keep Asking Unless a accepted player choice is triggered
			while(playerChoice == null || playerChoice.contentEquals("")) {
				playerChoice = gt.getInputString("Choose Your Next Move!\nDefend, Retreat, Attack");
	
			}
			
		// Load Player Choice Function to perform checks
			battleChoice(playerChoice);
		
	}
	
	//**---------- BATTLE CHOICE EVENT ----------**\\
	
	//This function contains all the choice making code for the battle, it is split off as a function / method to improve readability 
		
	
	private static void battleChoice(String Choice) {
		
		
		//Perform First Check for "Attack Command"
		
		if(Choice.contentEquals("attack")) {
			
			//-- Enemy Attack Damage Belt by player --\\
			
				//Calculate the enemyDamage Sustained in the attack with a random number X Combat Level
					int enemyDamage = (int) (Math.floor(Math.random() * (15 - 5 + 5) + 2)*armyCombatLevel);
				//	X (Times) the damage by the Army Strength Divided in half, This allows for larger armies to do more damage.
					int attackDamage = enemyDamage*armyStrength/50;
				// Process the Attack Damage to the enemy strength.
					enemyStrength -=  attackDamage;
	
			//-- Process attack damage sustained to player --\\

				//Calculate the Damage sustained by the player in the attack, This is a random range - army defense level.
					int playerDamage = (int) Math.floor(Math.random() * (15 - 5 + 5) + 2) - armyDefenceLevel;
				//X (Times) the damage by the Army Strength Divided in half, This allows for larger armies to do more damage.
					int sustainedDamage = playerDamage*enemyStrength/50;
				//Apply the damage to the player army's total strength.
					armyStrength -= sustainedDamage;
			
				//-- Update the live action feed for the damage Belt and Sustained in the action --\\
							
					gt.addRowToTable(actionFeed, "You Sustained: "+sustainedDamage+ " Damage");
					gt.addRowToTable(actionFeed, "Your Delt: "+attackDamage+ " Damage");

				//Update the GUI for player and Enemy Health	
					
					battleUpdate(false);
			
			//-- Process the win loose loop to see if the player has lost or won! --\\
				
					battleWinLose();
					
			}
		
			//-- Process player retreat command  and check for coins before proceeding  ** This code will trigger if the player has the correct amount of coins ** --\\
			
			else if(Choice.contentEquals("retreat") && armyCoins >=20) {
				
				// Deduct the cost of a retreat from the players coins
					armyCoins -= 20;
				// Reset the action feed to be blank!
					gt.clearRowsOfTable(actionFeed);
				// Clear the G Term Interface Window of data
					gt.clear();
				// Reset the background to white
					gt.setBackgroundColor(255, 255 , 255);
					
				//Load the camp event
					game("camp");
					
		}
		
		//-- Process the retreat code ** This section refers to the player if he doesn't have the required money ** --\\
		
			else if (Choice.contentEquals("retreat") && armyCoins < 20) {
			
			//Process Error for lack of money
				gt.showWarningDialog("You Need 20 Gold to Retreat! Fight to the Death!");
			//update GUI
				battleUpdate(false);
			
			//Re-process win loose loop ** This kicks the player back into the main battle loop
				
				battleWinLose();
				
			
		}
			
		//-- Process code for the defend action --\\
		
			else if (Choice.contentEquals("defend")){

			//create the damage that needs to be applied to the player
				
				int playerDamage = (int) Math.floor(Math.random() * (5 - 2 + 1) + 2) - armyDefenceLevel;

			//Apply the damage reduction to the player
				
				armyStrength -= playerDamage;
			
			//	Process damage sustained to the enemy army during the attack via combat level and random range
			
				int enemyDamage = (int) (Math.floor(Math.random() * (15 - 5 + 1) + 2)*armyCombatLevel);
				
			// Process the damage to the enemy army
				enemyStrength -= enemyDamage;
			
			//-- Process damage to live action log for player feed back--\\
				
				gt.addRowToTable(actionFeed, "You Sustained: "+playerDamage+ " Damage");
				gt.addRowToTable(actionFeed, "Your Delt: "+enemyDamage+ " Damage");
			
			//update The GUI for player and Computer army Health
			
				battleUpdate(false);
			
			//Run the Win/Loose Loop to check for a game end
			
				battleWinLose();
		}
		
		//-- Check for quite command and close game if triggered --\\
		
		else if(Choice.contentEquals("quite")) {
		
			//Clear G Term Interface Window
			
				gt.clear();
				
			//Close G Term Window & java program
				
			gt.close();
		}
	else 
	{
		//-- This Final else statement will force the place back into the Win/Loose Loop continuing the game loop --\\
		
		battleWinLose();
		
		}
			
	}
	
	//-- Process player Win Loose loop to check for a end to the battle  --\\
	
	private static void battleWinLose() {
		
		//Check for the enemy strength being 0
			if(enemyStrength <= 0) {
				
				//clear G Term Window
				gt.clear();
				
			//-- Display Victory Text ** Reset font size, color and text to suite --\\
				
				gt.setFontSize(100);
				gt.setXY(50, 50);
				gt.setFontColor(255, 255, 255);
				gt.println("You Were Victorius");
				gt.setFontSize(12);
				gt.setXY(0, 0);
				
			//-- Process the player win --\\	
				
				//Add coins to the player for the win
					armyCoins += 25;
				// Generate the win art work based on the army strength sizes seet at the start of the battle
					battleGenerateArmyWin();
				//load background image
				gt.addImageIcon("background-grass.png");
				
			//-- Display Win Pop up box --\\
				gt.showMessageDialog("Battle Won! You've earned $25 Coins \n You Have Defeated a enemy army");
				
			//-- Clear screen and load camp event --\\	
				gt.clear();
				gt.setBackgroundColor(255, 255, 255);
				game("camp");
				
		//Check for the player army strength being 0
		}else if (armyStrength <= 0) {
			
			//load the amount of turns survived by the player and convert to string
				String turns = String.valueOf(gameTurns-1);
			//clear G Term Interface	
				gt.clear();

			//load AI army Scene 
				battleGenerateArmyDefeated();
			
			//-- Process the player Loose --\\	
			
				gt.setXY(0, 0);
			//Set Background image
				gt.addImageIcon("background-grass.png");
			//Reset background color
				gt.setBackgroundColor(255, 255, 255);
			//Display Loose Dialog 
			gt.showErrorDialog("Your Amry was defeated!!! \n You Survived " + turns + " Turn");
			//End Game
			gt.clear();
			gt.close();
		} 
		else
		{
			// Else continue the game loop \\
			
			//Get initial choice input
				String Choice = gt.getInputString("Choose Your Next Move!\nDefend, Retreat, Attack");
			//Start the while loop
				while(Choice == null || Choice.contentEquals("")) {
					Choice = gt.getInputString("Choose Your Next Move!\nDefend, Retreat, Attack");
				}
			//Process battle choice in function
			battleChoice(Choice);
		}
		
	}
	
	//-- Process player Win Loose loop to check for a end to the battle  --\\
	
	//-- This function generates the player and computer army based on there strength/health --\\
	
	public static void battleGenerateArmy() {
		
		//-- Create Player Army  --\\
		
			//Divide strength by 10, this means a strength of 10 = 1 soldier in the game.
				int armySizeMax = armyStrength /10;
			//while loop to spawn units --- Create current size variable to count up to max size variable
				int armySizeCurrent = 0;
				
		//-- Start Spawning Loop  --\\
				while(armySizeCurrent < armySizeMax) {
			
			//create random values for unit location
				int randomX = (int) Math.floor(Math.random() * (640 - 1 + 1));
				int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));

			//edit random values so the men appear inside the window
				if(randomY >= 600) {
					randomY -= 128;
				}else if (randomY <= 128) {
					randomY += 128;
				}
				
			//increase army count
				armySizeCurrent ++;
			//add unit to screen
				gt.setXY(randomX, randomY);
				gt.addImageIcon("roman-soldier-X3.png");
			//set g term XY
				gt.setXY(0, 0);
			
		}
				
		
		//-- Create Enemy AI Army  --\\
		
		//Divide strength by 10, this means a strength of 10 = 1 soldier in the game.
				int EnemyarmySizeMax = enemyStrength/10;
	
			//while loop to spawn units --- Create current size variable to count up to max size variable
				int EnemyarmySizeCurrent = 0;
				while(EnemyarmySizeCurrent < EnemyarmySizeMax) {
			//create random values for unit location
				int randomX = (int) Math.floor(Math.random() * (1280 - 640 + 640));
				int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
			//edit random values so the men appear inside the window
				if(randomY >= 400) {
					randomY -= 128;
				}else if (randomY <= 128) {
					randomY += 128;
				}
				
			//increase army count
				EnemyarmySizeCurrent ++;
			//add unit to screen
				gt.setXY(randomX, randomY);
				gt.addImageIcon("carthaginian-soldier-x3.png");
			//set g term XY
				gt.setXY(0, 0);
			
		}
	}

	//-- Generate the army for when the player is defeated  --\\
	
	//-- This function generates the player and computer army based on there strength/health with them player army being dead--\\
	
	public static void battleGenerateArmyDefeated() {

		//Divide strength by 10, this means a strength of 10 = 1 soldier in the game.
			int armySizeCurrent = 0;
		//while loop to spawn units --- Create current size variable to count up to max size variable
			while(armySizeCurrent < armyStartingStrength/10) {
			
			//create random values for unit location
			int randomX = (int) Math.floor(Math.random() * (640 - 1 + 1));
			int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
		
			
			//edit random values so the men appear inside the window
			if(randomY >= 600) {
				randomY -= 128;
			}else if (randomY <= 128) {
				randomY += 128;
			}
			
			//increase army count
			armySizeCurrent ++;
			//add unit to screen
			gt.setXY(randomX, randomY);
			gt.addImageIcon("roman-soldier-32-DEADx3.png");
			//set gterm XY
			gt.setXY(0, 0);
			
		}
			
		// ** ENEMY ARMY Divide strength by 10, this means a strength of 10 = 1 soldier in the game.
		int EnemyarmySizeMax = enemyStrength/10;

		//while loop to spawn units --- Create current size variable to count up to max size variable
		int EnemyarmySizeCurrent = 0;
		while(EnemyarmySizeCurrent < EnemyarmySizeMax) {
			
			//create random values for unit location
			int randomX = (int) Math.floor(Math.random() * (1280 - 640 + 640));
			int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
			
			//edit random values so the men appear inside the window
			if(randomY >= 400) {
				randomY -= 128;
			}else if (randomY <= 128) {
				randomY += 128;
			}
			
			//increase army count
			EnemyarmySizeCurrent ++;
			//add unit to screen
			gt.setXY(randomX, randomY);
			gt.addImageIcon("carthaginian-soldier-x3.png");
			//set gterm XY
			gt.setXY(0, 0);
			
		}
	}

	//-- Generate the army for when the player wins a battle  --\\
	
	//-- This creates a win for the player in a battle!\\	
	
	public static void battleGenerateArmyWin() {

	//start while loop and increase army size current count until it hits the army strength/10 size
	int armySizeCurrent = 0;
	while(armySizeCurrent < armyStrength/10) {
		
		//create random values for unit location
		int randomX = (int) Math.floor(Math.random() * (640 - 1 + 1));
		int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
	
		
		//edit random values so the men appear inside the window
		if(randomY >= 600) {
			randomY -= 128;
		}else if (randomY <= 128) {
			randomY += 128;
		}
		
		//increase army count
		armySizeCurrent ++;
		//add unit to screen
		gt.setXY(randomX, randomY);
		gt.addImageIcon("roman-soldier-X3.png");
		//set gterm XY
		gt.setXY(0, 0);
		
	}
	
	
	//create enemy starting size for dead units
	int EnemyarmySizeMax = enemyStartingStrength/10;

	//start while loop and increase army size current count until it hits the army strength/10 size
	int EnemyarmySizeCurrent = 0;
	while(EnemyarmySizeCurrent < EnemyarmySizeMax) {
		
		//create random values for unit location
		int randomX = (int) Math.floor(Math.random() * (1280 - 640 + 640));
		int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
		
		//edit random values so the men appear inside the window
		if(randomY >= 400) {
			randomY -= 128;
		}else if (randomY <= 128) {
			randomY += 128;
		}
		
		//increase army count
		EnemyarmySizeCurrent ++;
		//add unit to screen
		gt.setXY(randomX, randomY);
		gt.addImageIcon("carthaginian-soldier-DEAD-32.png");
		//set gterm XY
		gt.setXY(0, 0);
		
	}
}

	
	//-- UPDATE BATTLE HEALTH GUI  --\\
	
	//-- This updates the main battle Health GUI --\\		

	private static void battleUpdate(boolean atCamp) {
		
		//check if the player is at camp or not - if at camp it will not update or display the enemy health UI
			if (atCamp) {
		//update player army health
			String currentStrength = String.valueOf(armyStrength);
			gt.clearRowsOfTable(armyHeathFeed);
			gt.addRowToTable(armyHeathFeed, currentStrength+ "%");
			}else {
		//update player army health
			String currentStrength = String.valueOf(armyStrength);
			gt.clearRowsOfTable(armyHeathFeed);
			gt.addRowToTable(armyHeathFeed, currentStrength+ "%");
		//update enemy army Health
			String currentEnemyStrength = String.valueOf(enemyStrength);
			gt.clearRowsOfTable(enemyArmyHealthFeed);
			gt.addRowToTable(enemyArmyHealthFeed, currentEnemyStrength+ "%");
		}
	}

	
	//-- LOAD PLAYER CAMP  --\\
	
	//-- This function creates the player camp event and loads the downstream functions required for it --\\	
	
	private static void camp() {
		
		//print initial GUI elements to G Term
			gt.setFontColor(255, 255, 255);
			gt.setXY(520, 10);
			gt.setFontSize(24);
			gt.println("You have setup Camp!");
		//print total game turn
			gt.setXY(610, 40);
			gt.setFontSize(12);
			gt.println("Game turn: " + gameTurns);
			gt.setFontColor(0, 0, 0);
			gt.setXY(0, 0);
		//print combatants in top GUI bar
			gt.setXY(480, 70);
			gt.setFontSize(16);
			gt.setFontColor(255, 255, 255);
			gt.println("Welcome to base camp General " + generalName);
			gt.setFontColor(0, 0, 0);
			gt.setXY(0, 0);
		//load GUI artwork
			gt.addImageIcon("gui.png");
		//load GUI Side bar
			gt.setXY(0, 0);
			gt.addImageIcon("sidebar.png");
			gt.setXY(0, 0);
		
		//update GUI
				battleUpdate(true);
		//generate Camp function
			battleGenerateCamp();
		//load background
			gt.setXY(0, 0);
			gt.addImageIcon("background-grass.png");	
		//get player choice from string
			String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
		//start player choice loop 
		while(playerChoice == null || playerChoice.contentEquals("")) {
			playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
		}
		//pass player choice onto campChoice function to process.
		campChoice(playerChoice);
		
		
	}
	
	//-- GENERATE CAMP SITE  --\\
	
	//-- This function generates the camp with the player army and tents -- Each tent will for 2 men, meaning 20 guys will spawn 10 tents --\\	
	
	private static void battleGenerateCamp() {
		
		//-- Spawn Army  --\\
		
		//get army size divided by 10
			int armySizeMax = armyStrength /10;

		//start spawning while loop
			int armySizeCurrent = 0;
			while(armySizeCurrent < armySizeMax) {
		//create random values for unit location
			int randomX = (int) Math.floor(Math.random() * (1280 - 1 + 1));
			int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
		//edit random values so the men appear inside the window
			if(randomY >= 600) {
				randomY -= 128;
			}else if (randomY <= 128) {
				randomY += 128;
			}
			
		//increase army count
			armySizeCurrent ++;
		//add unit to screen
			gt.setXY(randomX, randomY);
			gt.addImageIcon("roman-soldier-32-guardx3.png");
		//set g term XY
			gt.setXY(0, 0);
			
		}
		
		//-- Spawn Tents  --\\
			
			//Calculate tent numbers by halving soldier number.
				int armyTentSizeMax = armyStrength /10/2;
			//while loop for spawning tents
				int armyTentSizeCurrent = 0;
				while(armyTentSizeCurrent < armyTentSizeMax) {	
			//create random values for unit location
				int randomX = (int) Math.floor(Math.random() * (1280 - 1 + 1));
				int randomY = (int) Math.floor(Math.random() * (720 - 1 + 1));
			//edit random values so the men appear inside the window
				if(randomY >= 400) {
					randomY -= 256;
				}else if (randomY <= 256) {
					randomY += 256;
				}
			//increase army count
				armyTentSizeCurrent ++;
			//add unit to screen
				gt.setXY(randomX, randomY);
				gt.addImageIcon("tent.png");
			//set g term XY
				gt.setXY(0, 0);
		}
	}
	
	//-- Process camp choices  --\\
	
	//-- This function will process the choices for player input for the camp --\\	
	
	private static void campChoice(String choice) {
		
		//first it will grab the choice string from the parent camp function
	
		//check if the string = buy 
			if(choice.contentEquals("buy")) {	
		//Ask how many units it wants you want to buy
			String buyChoice = gt.getInputString("How many units would\n you like to buy?");
		
		//start error checking & create a is numerical value	
			boolean buyNumberIsNumerical = true;
		//run a try and catch loop to check for a error on the number input
			try {
				//convert string to integer 
				int buyNumber = Integer.parseInt(buyChoice);
				System.out.println("Testing For Numerical Number " + buyNumber);
			} catch (NumberFormatException e) {
				//catch the error and set is numerical to false
				buyNumberIsNumerical = false;
			}
		
		// if the number is numerical process this code
		if (buyNumberIsNumerical) { 
			//convert sting got number
				int buyNumber = Integer.parseInt(buyChoice);
			//Calculate the cost by timing the buy number by the unit price
				int cost = buyNumber*unitPrice;
			
			//check for coins
			if (armyCoins >= cost) {
				
				//grab total cost and convert to string
					String totalCost = String.valueOf(cost);
				//grab the buy number and convert to string
					String totalBuyNumber =  String.valueOf(buyNumber);
				//process the payment
					armyCoins -= cost;
				//add the new units to the army
					armyStrength += buyNumber;
					gt.showMessageDialog("You got "+totalBuyNumber+" New Units For $"+totalCost);
					gt.addRowToTable(actionFeed, "You Spent: $"+cost);
					gt.addRowToTable(actionFeed, "You Have: $"+armyCoins+"Remaining");
					//update the battle UI
					battleUpdate(true);
					
				//reset choice 
					String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
				//continue choice loop
					while(playerChoice == null || playerChoice.contentEquals("")) {
						playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
					}
				//process camp choice via choice function
					campChoice(playerChoice);
			}else {
				
				//else display lack of money warning
					gt.showWarningDialog("You need more coins to buy that many units");
				//reset choice to the main input loop
					String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
					while(playerChoice == null || playerChoice.contentEquals("")) {
						playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
					}
				//process choice via choice function
					campChoice(playerChoice);
			}
			
			
		}else {
			//else if that all fails process the non valid number from the try catch
				gt.showWarningDialog("Please enter a valid number");
			//reset player input loop
				String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
			//start while loop
				while(playerChoice == null || playerChoice.contentEquals("")) {
					playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");

				}
			//Process choice via camp
				campChoice(playerChoice);
		}
			
	//-- Process Camp Upgrades  --\\
		
	//-- Process the upgrade choices for the camp --\\	

		//if the choice = upgrade
		}else if (choice.contentEquals("upgrade")){
			//process the next step, select combat or defense upgrade
			choice = gt.getInputString("Would you like to upgrade your\n Combat or Defense");
			//process combat selection
				if (choice.contentEquals("combat")) {
			//get input number for how many combat levels
				String buyNumber = gt.getInputString("How many levels do you want to buy?");
			//run error checking for a numerical number
				boolean buyNumberIsNumerical = true;
			//start try loop for error
				try {
					//convert string to integer
						int buyChoice = Integer.parseInt(buyNumber);
						System.out.println("Testing For Numerical Number " + buyChoice);
				} catch (NumberFormatException e) {
					//catch error if it is thrown and set numerical to false
					buyNumberIsNumerical = false;
				}
				
				//process upgrade if the number is numerical and does not error out
				if (buyNumberIsNumerical) { 
					//get buy choice and convert to integer
						int buyChoice = Integer.parseInt(buyNumber);
					//process payment to coins
						armyCoins -= buyChoice*levelUpgradeCost;
					//process upgrade to combat level
						armyCombatLevel += buyChoice;
					//add text to action feed field
						gt.addRowToTable(actionFeed, "You Spent: $"+buyChoice*levelUpgradeCost);
					//show payment success
						gt.showMessageDialog("You Just Upgraded Combat by "+buyChoice+" For $"+levelUpgradeCost*buyChoice);
					
					//reset player input loop
						String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
					//start while loop for input		
						while(playerChoice == null || playerChoice.contentEquals("")) {
							playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");

						}
					//pass choice to choice function for process
						campChoice(playerChoice);
					
				}else {
					//if there is a error assume its not a number and provide the error
						gt.showWarningDialog("Please enter a valid number");
					//reset the player input loop
						String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
					//start the while loop
						while(playerChoice == null || playerChoice.contentEquals("")) {
							playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");

						}
					//pass input to camp choice
						campChoice(playerChoice);
					}
				}else if (choice.contentEquals("defense")) {
					//process defense upgrade & get input for how many levels to upgrade
						String buyNumber = gt.getInputString("How many levels do you want to buy?");
					//perform error checking on number
						boolean buyNumberIsNumerical = true;
	
						try {
							int buyChoice = Integer.parseInt(buyNumber);
							System.out.println("Testing For Numerical Number " + buyChoice);
						} catch (NumberFormatException e) {
							buyNumberIsNumerical = false;
						}
					//process code if it is a valid number
					if (buyNumberIsNumerical) { 
						//process code if it is a number
							int buyChoice = Integer.parseInt(buyNumber);
						//process payment to coins
							armyCoins -= buyChoice*levelUpgradeCost;
						//process defense upgrade
							armyDefenceLevel += buyChoice;
						//update live feed table to reflect the changes
							gt.addRowToTable(actionFeed, "You Spent: $"+buyChoice*levelUpgradeCost);
							gt.showMessageDialog("You Just Upgraded Defense by "+buyChoice+" For $"+levelUpgradeCost*buyChoice);
						//reset game input loop
							String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
						//start while loop
							while(playerChoice == null || playerChoice.contentEquals("")) {
								playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
	
							}
						//process choice with choice function
							campChoice(playerChoice);
						//else assume there was no valid number and reset input
					}else {
						//display warning for incorrect numerical input
							gt.showWarningDialog("Please enter a valid number");
							String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
						//reset player choice while loop	
							while(playerChoice == null || playerChoice.contentEquals("")) {
								playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
	
							}
						//pass choice to function to handle input
						campChoice(playerChoice);
					}
				
					//else ask the player for a new input
			}else {
				//get choice string
					String playerChoice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");

				//start while loop input
					while(playerChoice == null || playerChoice.contentEquals("")) {
						playerChoice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
	
					}
				//pass choice to camp to process
					campChoice(playerChoice);
			}

		//run pack up camp code
		}else if (choice.contentEquals("packup")){
			//reset G Term
				gt.clear();
			//run the battle turn
				game("battle");

		}
		//quite the game
		else if(choice.contentEquals("quite")) {
		//quite game and reset g term
			gt.clear();
			gt.close();
		}else {
		
		//restart game choice loop with input
		choice  = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
		//restart while loop input
		while(choice == null || choice.contentEquals(""))
			{
				choice = gt.getInputString("Camp Upgrades!\nBuy, Upgrade, Packup");
			}
		campChoice(choice);
		}
			
	}

	
	//-- FINAL END GAME FUNCTION  --\\
	
	// Last minute addition to complete story
	
	private static void endBattle() {
		gt.clear();
		//-- Display Victory Text ** Reset font size, color and text to suite --\\
		
		gt.setFontSize(50);
		gt.setXY(50, 50);
		gt.setFontColor(255, 255, 255);
		gt.println("You made It! You have warned the captial!");
		gt.setFontSize(25);
		gt.println("Created for RMIT ITP Assignment 1");
		gt.setFontSize(12);
		gt.setXY(0, 0);
		gt.addImageIcon("arrival-captial.png");
		gt.showMessageDialog("You made it to the captial!\n"
				+ "The captial army is moving to combat Hannibal\n"
				+ "Rome is saved!");
		gt.showWarningDialog("Created by Jack Harris (S3893749)");
		gt.showErrorDialog("Thanks for Playing!\n"
				+ "The Game will now exit");
		
		gt.clear();
		gt.close();
	}
	
	
}
