package ui;

import exceptions.ImpossibleValue;
import exceptions.NegativeValue;
import exceptions.PurchaseFail;
import model.LeagueOfLegendsAccount;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

// Some code provided from TellerApp
// All champion data provided from League Of Legends official application

// League Of Legends account manager and champion recommendation application
public class LeagueChampionApp extends JPanel {

    private static final String SAVE_FILE = "./data/save.txt";

    private static final String ACCOUNTS_FILE = "./data/leagueoflegendsaccounts.txt";
    private LeagueOfLegendsAccount mainAccount;
    private LeagueOfLegendsAccount altAccount;
    private Scanner input;

    private JFrame main;

    // EFFECTS: runs the League Of Legends account manager and champion recommendation application
    public LeagueChampionApp() {
        init();

        main = new JFrame("League Champion App");
        main.setFont(new Font("Arial", Font.PLAIN, 40));
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setPreferredSize(new Dimension(938, 950));
        main.setLocationRelativeTo(null); //centers in middle of screen
        main.getContentPane().setLayout(new BoxLayout(main.getContentPane(), BoxLayout.Y_AXIS));

        startUpMain();

        makeEarnButton();
        makePurchaseButton();
        makeAcquireButton();
        makeObtainButton();
        makeCheckBEButton();
        makeGetRPButton();
        makeReceiveButton();
        makeFavouriteButton();
        makePrintButton();
        makeSaveButton();
        makeLoadButton();

        main.pack();
        main.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Generates the background image for main along with the initialize sound
    public void startUpMain() {
        ImageIcon icon = new ImageIcon("./data/Untitled.png");
        JLabel accountImage = new JLabel(icon);
        accountImage.setPreferredSize(new Dimension(938, 536));
        main.add(accountImage);
        playSound("./data/welcometolol.wav");
    }

    // MODIFIES: this
    // EFFECTS: Constructs the earn button for the application
    public void makeEarnButton() {
        JButton earn = new JButton("Earn in game currency");
        new Button(earn, main);
//        earn.setAlignmentX(Component.LEFT_ALIGNMENT);
//        earn.setPreferredSize(new Dimension(2500, 100));
//        earn.setFont(new Font("Arial", Font.PLAIN, 40));
        earn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doEarnBlueEssence();
            }
        });
//        main.add(earn);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the purchase button for the application
    public void makePurchaseButton() {
        JButton purchase = new JButton("Purchase premium currency");
        new Button(purchase, main);
//        purchase.setAlignmentX(Component.LEFT_ALIGNMENT);
//        purchase.setPreferredSize(new Dimension(2500, 100));
//        purchase.setFont(new Font("Arial", Font.PLAIN, 40));
        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBuyRiotPoints();
            }
        });
//        main.add(purchase);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the acquire button for the application
    public void makeAcquireButton() {
        JButton acquire = new JButton("Acquire a new champion with Blue Essence");
        new Button(acquire, main);
//        acquire.setAlignmentX(Component.LEFT_ALIGNMENT);
//        acquire.setPreferredSize(new Dimension(2500, 100));
//        acquire.setFont(new Font("Arial", Font.PLAIN, 40));
        acquire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acquireChampionUsingBlueEssence();
            }
        });
//        main.add(acquire);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the obtain button for this application
    public void makeObtainButton() {
        JButton obtain = new JButton("Obtain a new champion using Riot Points");
        new Button(obtain, main);
//        obtain.setAlignmentX(Component.LEFT_ALIGNMENT);
//        obtain.setPreferredSize(new Dimension(2500, 100));
//        obtain.setFont(new Font("Arial", Font.PLAIN, 40));
        obtain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtainChampionUsingRiotPoints();
            }
        });
//        main.add(obtain);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the checkBE button for this application
    public void makeCheckBEButton() {
        JButton checkBE = new JButton("Check if a champion can be purchased with Blue Essence");
        new Button(checkBE, main);
//        checkBE.setAlignmentX(Component.LEFT_ALIGNMENT);
//        checkBE.setPreferredSize(new Dimension(2500, 100));
//        checkBE.setFont(new Font("Arial", Font.PLAIN, 40));
        checkBE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBlueEssenceBalanceDifference();
            }
        });
//        main.add(checkBE);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the getRP button for this application
    public void makeGetRPButton() {
        JButton getRP = new JButton("Check if a champion can be purchased with Riot Points");
        new Button(getRP, main);
//        getRP.setAlignmentX(Component.LEFT_ALIGNMENT);
//        getRP.setPreferredSize(new Dimension(2500, 100));
//        getRP.setFont(new Font("Arial", Font.PLAIN, 40));
        getRP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRiotPointsBalanceDifference();
            }
        });
//        main.add(getRP);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the receive button for this application
    public void makeReceiveButton() {
        JButton receive = new JButton("Receive a new champion recommendation");
        new Button(receive, main);
//        receive.setAlignmentX(Component.LEFT_ALIGNMENT);
//        receive.setPreferredSize(new Dimension(2500, 100));
//        receive.setFont(new Font("Arial", Font.PLAIN, 40));
        receive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiveChampionRecommendation();
            }
        });
//        main.add(receive);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the favourite button for this application
    public void makeFavouriteButton() {
        JButton favourite = new JButton("Favourite a champion");
        new Button(favourite, main);
//        favourite.setAlignmentX(Component.LEFT_ALIGNMENT);
//        favourite.setPreferredSize(new Dimension(2500, 100));
//        favourite.setFont(new Font("Arial", Font.PLAIN, 40));
        favourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                favouriteChampion();
            }
        });
//        main.add(favourite);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the print button for this application
    public void makePrintButton() {
        JButton print = new JButton("Print out account details");
        new Button(print, main);
//        print.setAlignmentX(Component.LEFT_ALIGNMENT);
//        print.setPreferredSize(new Dimension(2500, 100));
//        print.setFont(new Font("Arial", Font.PLAIN, 40));
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLeagueOfLegendsAccount();
            }
        });
//        main.add(print);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the save button for this application
    public void makeSaveButton() {
        JButton save = new JButton("Save account details");
        new Button(save, main);
//        save.setAlignmentX(Component.LEFT_ALIGNMENT);
//        save.setPreferredSize(new Dimension(2500, 100));
//        save.setFont(new Font("Arial", Font.PLAIN, 40));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAccounts();
                displayMessage("Accounts saved to file");
            }
        });
//        main.add(save);
    }

    // MODIFIES: this
    // EFFECTS: Constructs the save button for this application
    public void makeLoadButton() {
        JButton load = new JButton("Load saved accounts");
        new Button(load, main);
//        load.setAlignmentX(Component.LEFT_ALIGNMENT);
//        load.setPreferredSize(new Dimension(2500, 100));
//        load.setFont(new Font("Arial", Font.PLAIN, 40));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAccounts();
                displayMessage("Accounts loaded from file");
            }
        });
//        main.add(load);
    }

//// League Of Legends account manager and champion recommendation application
//public class LeagueChampionApp {
//    private static final String ACCOUNTS_FILE = "./data/leagueoflegendsaccounts.txt";
//    private LeagueOfLegendsAccount main;
//    private LeagueOfLegendsAccount alt;
//    private Scanner input;
//
//    public LeagueChampionApp() {
//        runLeagueChampionApp();
//    }


//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runLeagueChampionApp() {
//        boolean keepGoing = true;
//        String command = null;
//        input = new Scanner(System.in);
//        loadAccounts();
//
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nWe await your return Summoner!");
//    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes accounts with default values
    private void loadAccounts() {
        try {
            List<LeagueOfLegendsAccount> leagueOfLegendsAccounts = Reader.readAccounts(new File(ACCOUNTS_FILE));
            mainAccount = leagueOfLegendsAccounts.get(0);
            altAccount = leagueOfLegendsAccounts.get(1);
        } catch (IOException e) {
            init();
        }
    }

//    // REQUIRES: an integer >= 0 representing how much Blue Essence was earned
//    // MODIFIES: this
//    // EFFECTS: conducts an addition of in game currency
//    private void doAccountSaving() {
//        JFrame recordWindow = new JFrame("Enter which account you would like to save: (mainAccount or altAccount)");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
//        initiateAccountSavingFields(recordWindow);
//
//        recordWindow.pack();
//        recordWindow.setVisible(true);
//    }
//
//    // EFFECTS: Initiates texts fields for doEarnBlueEssence()
//    private void initiateAccountSavingFields(JFrame frame) {
//        frame.add(new JLabel("Which account would you like to save, mainAccount or altAccount?"));
//        JTextField account = new JTextField();
//        frame.add(account);
//
//        initiateAccountSavingEnter(frame, account);
//    }
//
//    // EFFECTS: initiates enter button in doEarnBlueEssence() and adds the designated amount of Blue Essence to the
//    //          desired account
//    private void initiateAccountSavingEnter(JFrame frame, JTextField account) {
//        JButton enter = new JButton("Record");
//        enter.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    saveAccounts(selectLeagueOfLegendsAccount(account.getText()));
//                    displayMessage("Accounts saved to file!");
//                } catch (Exception e) {
//                    displayMessage("Invalid input.");
//                }
//                frame.setVisible(false);
//            }
//        });
//        frame.add(enter);
//    }


    // EFFECTS: saves state of main and alt accounts to ACCOUNTS_FILE
    private void saveAccounts() {
        try {
            Writer writer = new Writer(new File(ACCOUNTS_FILE));
            writer.write(mainAccount);
            writer.write(altAccount);
            writer.close();
            System.out.println("Accounts saved to file " + ACCOUNTS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + ACCOUNTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // Displays a message on to the screen
    private void displayMessage(String str) {
        JFrame window = new JFrame("Message");
        window.setPreferredSize(new Dimension(1000, 500));
        window.setFont(new Font("Arial", Font.PLAIN, 40));
        window.setLayout(new BorderLayout(5, 5));

        JLabel msg = new JLabel("<html>" + str + "</html>", SwingConstants.CENTER);
        msg.setPreferredSize(new Dimension(750, 250));
        msg.setFont(new Font("Arial", Font.PLAIN, 40));
        window.add(msg, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error while trying to play sound");
            e.printStackTrace();
        }
    }


//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("e")) {
//            doEarnBlueEssence();
//        } else if (command.equals("c")) {
//            doRegionChange();
//        } else if (command.equals("n")) {
//            doNameChange();
//        } else if (command.equals("b")) {
//            doBuyRiotPoints();
//        } else if (command.equals("a")) {
//            acquireChampionUsingBlueEssence();
//        } else if (command.equals("o")) {
//            obtainChampionUsingRiotPoints();
//        } else if (command.equals("d")) {
//            checkBlueEssenceBalanceDifference();
//        } else if (command.equals("g")) {
//            getRiotPointsBalanceDifference();
//        } else if (command.equals("r")) {
//            receiveChampionRecommendation();
//        } else if (command.equals("f")) {
//            favouriteChampion();
//        } else if (command.equals("s")) {
//            saveAccounts();
//        } else if (command.equals("p")) {
//            printLeagueOfLegendsAccount();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        mainAccount = new LeagueOfLegendsAccount("North America (NA)", "Doublelift",
                10000, 1000);
        altAccount = new LeagueOfLegendsAccount("Republic of Korea (KR)", "Faker",
                10000, 10000);
    }

//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tc -> Change account region");
//        System.out.println("\tn -> Change in game name");
//        System.out.println("\te -> Earn in game currency");
//        System.out.println("\tb -> Purchase premium currency");
//        System.out.println("\ta -> Acquire a new champion with Blue Essence");
//        System.out.println("\to -> Obtain a new champion using Riot Points");
//        System.out.println("\td -> Check if a champion can be purchased by Blue Essence");
//        System.out.println("\tg -> Check if a champion can be purchased by Riot Points");
//        System.out.println("\tr -> Receive a new champion recommendation");
//        System.out.println("\tf -> Favourite a champion");
//        System.out.println("\ts -> save Accounts to file");
//        System.out.println("\tp -> print out account details");
//        System.out.println("\tq -> quit");
//    }

//    // EFFECTS: prompts user to select a LeagueOfLegends account and returns it
//    private LeagueOfLegendsAccount selectLeagueOfLegendsAccount() {
//        String selection = "";  // force entry into loop
//
//        while (!(selection.equals("m") || selection.equals("a"))) {
//            System.out.println("m for main");
//            System.out.println("a for alt");
//            selection = input.next();
//            selection = selection.toLowerCase();
//        }
//
//        if (selection.equals("m")) {
//            return mainAccount;
//        } else {
//            return altAccount;
//        }
//    }

    // EFFECTS: prompts user to input a string that selects a LeagueOfLegends account and returns it
    private LeagueOfLegendsAccount selectLeagueOfLegendsAccount(String n) {
        if (n.equals("mainAccount")) {
            return mainAccount;
        } else {
            return altAccount;
        }
    }

//    // REQUIRES: a string representing the new region of choice
//    // MODIFIES: this
//    // EFFECTS: conducts a region change
//    private void doRegionChange() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter new region of choice:");
//        String newRegion = input.next();
//
//        if (newRegion != "") {
//            selected.setRegion(newRegion);
//        } else {
//            System.out.println("Region must be at least one character long\n");
//        }
//
//        printRegion(selected);
//    }
//
//    private void printRegion(LeagueOfLegendsAccount selected) {
//        System.out.println("Account now located in " + selected.getRegion());
//    }

//    // REQUIRES: a string representing the new name of choice
//    // MODIFIES: this
//    // EFFECTS: conducts a name change
//    private void doNameChange() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter new name of choice:");
//        String newName = input.next();
//
//        if (newName != "") {
//            selected.setInGameName(newName);
//        } else {
//            System.out.println("Name must be at least one character long\n");
//        }
//
//        printInGameName(selected);
//    }
//
//    private void printInGameName(LeagueOfLegendsAccount selected) {
//        System.out.println("Account now named " + selected.getInGameName());
//    }


//    // REQUIRES: an integer >= 0 representing how much Blue Essence was earned
//    // MODIFIES: this
//    // EFFECTS: conducts an addition of in game currency
//    private void doEarnBlueEssence() {
//        LeagueOfLegendsAccount destination = selectLeagueOfLegendsAccount();
//        System.out.print("Enter earned amount: (Blue Essence)");
//        int amount = input.nextInt();
//
//        if (amount < 0) {
//            System.out.println("Cannot earn negative amount\n");
//        } else {
//            destination.earnInGame(amount);
//        }
//
//        printBlueEssenceBalance(destination);
//    }

//    private void printBlueEssenceBalance(LeagueOfLegendsAccount destination) {
//        System.out.println("New Blue Essence balance is " + destination.getBlueEssenceBalance());
//    }

    // REQUIRES: an integer >= 0 representing how much Blue Essence was earned
    // MODIFIES: this
    // EFFECTS: conducts an addition of in game currency
    private void doEarnBlueEssence() {
        JFrame recordWindow = new JFrame("Earn in game currency");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateEarnBlueEssenceFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for doEarnBlueEssence()
    private void initiateEarnBlueEssenceFields(JFrame frame) {
        JLabel q1 = new JLabel("Add amount to mainAccount or altAccount?");
        new Label(q1);
//        q1.setPreferredSize(new Dimension(750, 100));
//        q1.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q1);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(750, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q2 = new JLabel("Amount of Blue Essence Earned");
        new Label(q2);
//        q2.setPreferredSize(new Dimension(750, 100));
//        q2.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q2);

        JTextField amount = new JTextField();
        new TextField(amount);
//        amount.setPreferredSize(new Dimension(750, 100));
//        amount.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(amount);

        ImageIcon icon = new ImageIcon("./data/be.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateEarnBlueEssenceEnter(frame, account, amount);
    }

    // EFFECTS: initiates enter button in doEarnBlueEssence() and adds the designated amount of Blue Essence to the
    //          desired account
    private void initiateEarnBlueEssenceEnter(JFrame frame, JTextField account, JTextField amount) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 30));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int designated = Integer.parseInt(amount.getText());
                    selectLeagueOfLegendsAccount(account.getText()).earnInGame(designated);
                    displayMessage(printBlueEssenceBalance(selectLeagueOfLegendsAccount(account.getText())));
                } catch (NegativeValue e) {
                    displayMessage("Reload failed, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: creates a string representation of the updated blue essence balance
    private String printBlueEssenceBalance(LeagueOfLegendsAccount destination) {
        return "New Blue Essence balance is " + destination.getBlueEssenceBalance();
    }

//    // REQUIRES: an integer that is one of: 5, 10, 20, 35, 50, or 100
//    // MODIFIES: this
//    // EFFECTS: conducts a purchase of premium currency
//    private void doBuyRiotPoints() {
//        LeagueOfLegendsAccount destination = selectLeagueOfLegendsAccount();
//        System.out.print("Enter amount you wish to spend: ($)");
//        int amount = input.nextInt();
//
//        if (amount < 0) {
//            System.out.println("Can't spend a negative amount\n");
//        } else {
//            destination.purchasePremium(amount);
//        }
//
//        printRiotPointsBalance(destination);
//    }

//    private void printRiotPointsBalance(LeagueOfLegendsAccount destination) {
//        System.out.println("New Riot Points balance is " + destination.getRiotPointsBalance());
//    }

    // REQUIRES: an integer that is one of: 5, 10, 20, 35, 50, or 100
    // MODIFIES: this
    // EFFECTS: conducts a purchase of premium currency
    private void doBuyRiotPoints() {
        JFrame recordWindow = new JFrame("Purchase Premium Currency");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateBuyRiotPointsFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for doBuyRiotPoints()
    private void initiateBuyRiotPointsFields(JFrame frame) {
        JLabel q3 = new JLabel("Make purchase for mainAccount or altAccount?");
        new Label(q3);
//        q3.setPreferredSize(new Dimension(750, 100));
//        q3.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q3);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(750, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q4 = new JLabel("Amount of money you wish to spend (5, 10, 20, 35, 50, or 100)$");
        new Label(q4);
//        q4.setPreferredSize(new Dimension(750, 100));
//        q4.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q4);

        JTextField amount = new JTextField();
        new TextField(amount);
//        amount.setPreferredSize(new Dimension(750, 100));
//        amount.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(amount);

        ImageIcon icon = new ImageIcon("./data/rp.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateBuyRiotPointsEnter(frame, account, amount);
    }

    // EFFECTS: initiates enter button in doBuyRiotPoints() and adds the corresponding amount of Riot Points to the
    //          desired account
    private void initiateBuyRiotPointsEnter(JFrame frame, JTextField account, JTextField amount) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int designated = Integer.parseInt(amount.getText());
                    selectLeagueOfLegendsAccount(account.getText()).purchasePremium(designated);
                    displayMessage(printRiotPointsBalance(selectLeagueOfLegendsAccount(account.getText())));
                } catch (ImpossibleValue e) {
                    displayMessage("Reload failed, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // Produces a string representation of the updated Riot Points balance
    private String printRiotPointsBalance(LeagueOfLegendsAccount destination) {
        return "New Riot Points balance is " + destination.getRiotPointsBalance();
    }

//    // REQUIRES: the name of the champion the user wants to purchase
//    // MODIFIES: this
//    // EFFECTS: purchases a champion by deducting the required Blue Essence and adds it to the account collection
//    private void acquireChampionUsingBlueEssence() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter the name of the champion you wish to purchase");
//        String championName = input.next();
//
//        if (championName.equals("Blitzcrank")) {
//            System.out.println(selected.makeBlueEssencePurchase("Blitzcrank"));
//        } else if (championName.equals("Darius")) {
//            System.out.println(selected.makeBlueEssencePurchase("Darius"));
//        } else if (championName.equals("Syndra")) {
//            System.out.println(selected.makeBlueEssencePurchase("Syndra"));
//        } else if (championName.equals("Lucian")) {
//            System.out.println(selected.makeBlueEssencePurchase("Lucian"));
//        } else if (championName.equals("Akali")) {
//            System.out.println(selected.makeBlueEssencePurchase("Akali"));
//        } else if (championName.equals("Sejuani")) {
//            System.out.println(selected.makeBlueEssencePurchase("Sejuani"));
//        } else if (championName.equals("Fiddlesticks")) {
//            System.out.println(selected.makeBlueEssencePurchase("Fiddlesticks"));
//        } else {
//            System.out.println("Not a valid Champion");
//        }
//    }

    // REQUIRES: the name of the champion must be one of the possible options
    // MODIFIES: this
    // EFFECTS: conducts a purchase of a champion by subtracting the cost in Blue Essence
    //          and adding it to the owned collection
    private void acquireChampionUsingBlueEssence() {
        JFrame recordWindow = new JFrame("Acquire a  new champion with Blue Essence");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateBlueEssencePurchaseFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for acquireChampionUsingBlueEssence()
    private void initiateBlueEssencePurchaseFields(JFrame frame) {
        JLabel q5 = new JLabel("Make purchase for mainAccount or altAccount?");
//        q5.setPreferredSize(new Dimension(1000, 100));
//        q5.setFont(new Font("Arial", Font.PLAIN, 40));
        new Label(q5);
        frame.add(q5);

        JTextField account = new JTextField();
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        new TextField(account);
        frame.add(account);

        JLabel q6 = new JLabel("Name of the champion you wish to purchase?");
//        q6.setPreferredSize(new Dimension(1000, 100));
//        q6.setFont(new Font("Arial", Font.PLAIN, 40));
        new Label(q6);
        frame.add(q6);

        JTextField name = new JTextField();
//        name.setPreferredSize(new Dimension(1000, 100));
//        name.setFont(new Font("Arial", Font.PLAIN, 40));
        new TextField(name);
        frame.add(name);

        ImageIcon icon = new ImageIcon("./data/shopicon.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateBlueEssencePurchaseEnter(frame, account, name);
    }

    // EFFECTS: initiates enter button in acquireChampionUsingBlueEssence(),
    //          subtracts the appropriate Blue Essence cost of the champion from the account's balance
    //          adds the champion to the collection "owned"
    private void initiateBlueEssencePurchaseEnter(JFrame frame, JTextField account, JTextField name) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nameText = name.getText();
                    displayMessage(runBlueEssencePurchase(selectLeagueOfLegendsAccount(account.getText()), nameText));
                    runBlueEssencePurchase2(nameText);
                } catch (PurchaseFail e) {
                    displayMessage("Champion not purchased, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps process the the actual purchase of the champion
    private String runBlueEssencePurchase(LeagueOfLegendsAccount account, String championName) throws PurchaseFail {
        if (championName.equals("Blitzcrank")) {
            return account.makeBlueEssencePurchase("Blitzcrank");
        } else if (championName.equals("Darius")) {
            return account.makeBlueEssencePurchase("Darius");
        } else if (championName.equals("Syndra")) {
            return account.makeBlueEssencePurchase("Syndra");
        } else if (championName.equals("Lucian")) {
            return account.makeBlueEssencePurchase("Lucian");
        } else if (championName.equals("Akali")) {
            return account.makeBlueEssencePurchase("Akali");
        } else if (championName.equals("Sejuani")) {
            return account.makeBlueEssencePurchase("Sejuani");
        } else if (championName.equals("Fiddlesticks")) {
            return account.makeBlueEssencePurchase("Fiddlesticks");
        } else {
            return "Not a valid champion";
        }
    }

    // EFFECTS: helps process the the sound of the champion purchased
    private void runBlueEssencePurchase2(String championName) {
        if (championName.equals("Blitzcrank")) {
            playSound("./data/Blitzcrank.wav");
        } else if (championName.equals("Darius")) {
            playSound("./data/Darius.wav");
        } else if (championName.equals("Syndra")) {
            playSound("./data/Syndra.wav");
        } else if (championName.equals("Lucian")) {
            playSound("./data/Lucian.wav");
        } else if (championName.equals("Akali")) {
            playSound("./data/Akali.wav");
        } else if (championName.equals("Sejuani")) {
            playSound("./data/Sejuani.wav");
        } else if (championName.equals("Fiddlesticks")) {
            playSound("./data/Fiddlesticks.wav");
        }
    }


//    // REQUIRES: the name of the champion the user wants to purchase
//    // MODIFIES: this
//    // EFFECTS: purchases a champion by deducting the required Riot Points and adds it to the account collection
//    private void obtainChampionUsingRiotPoints() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter the name of the champion you wish to purchase");
//        String championName = input.next();
//
//        if (championName.equals("Blitzcrank")) {
//            System.out.println(selected.makeRiotPointsPurchase("Blitzcrank"));
//        } else if (championName.equals("Darius")) {
//            System.out.println(selected.makeRiotPointsPurchase("Darius"));
//        } else if (championName.equals("Syndra")) {
//            System.out.println(selected.makeRiotPointsPurchase("Syndra"));
//        } else if (championName.equals("Lucian")) {
//            System.out.println(selected.makeRiotPointsPurchase("Lucian"));
//        } else if (championName.equals("Akali")) {
//            System.out.println(selected.makeRiotPointsPurchase("Akali"));
//        } else if (championName.equals("Sejuani")) {
//            System.out.println(selected.makeRiotPointsPurchase("Sejuani"));
//        } else if (championName.equals("Fiddlesticks")) {
//            System.out.println(selected.makeRiotPointsPurchase("Fiddlesticks"));
//        } else {
//            System.out.println("Not a valid Champion");
//        }
//    }

    // REQUIRES: the name of the champion must be one of the possible options
    // MODIFIES: this
    // EFFECTS: conducts a purchase of a champion by subtracting the cost in Riot Points
    //          and adding it to the owned collection
    private void obtainChampionUsingRiotPoints() {
        JFrame recordWindow = new JFrame("Obtain a new champion with Riot Points");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateRiotPointsPurchaseFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for obtainChampionUsingRiotPoints()
    private void initiateRiotPointsPurchaseFields(JFrame frame) {
        JLabel q7 = new JLabel("Make purchase for mainAccount or altAccount?");
//        q7.setPreferredSize(new Dimension(1000, 100));
//        q7.setFont(new Font("Arial", Font.PLAIN, 40));
        new Label(q7);
        frame.add(q7);

        JTextField account = new JTextField();
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        new TextField(account);
        frame.add(account);

        JLabel q8 = new JLabel("Name of the champion you wish to purchase?");
//        q8.setPreferredSize(new Dimension(1000, 100));
//        q8.setFont(new Font("Arial", Font.PLAIN, 40));
        new Label(q8);
        frame.add(q8);

        JTextField name = new JTextField();
//        name.setPreferredSize(new Dimension(1000, 100));
//        name.setFont(new Font("Arial", Font.PLAIN, 40));
        new TextField(name);
        frame.add(name);

        ImageIcon icon = new ImageIcon("./data/shopicon.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateRiotPointsPurchaseEnter(frame, account, name);
    }

    // EFFECTS: initiates enter button in acquireChampionUsingBlueEssence(),
    //          subtracts the appropriate Riot Points cost of the champion from the account's balance
    //          adds the champion to the collection "owned"
    private void initiateRiotPointsPurchaseEnter(JFrame frame, JTextField account, JTextField name) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nameText = name.getText();
                    displayMessage(runRiotPointsPurchase(selectLeagueOfLegendsAccount(account.getText()), nameText));
                    runRiotPointsPurchase2(nameText);
                } catch (PurchaseFail e) {
                    displayMessage("Champion not purchased, invalid input");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps process the the actual purchase of the champion
    private String runRiotPointsPurchase(LeagueOfLegendsAccount account, String championName) throws PurchaseFail {
        if (championName.equals("Blitzcrank")) {
            return account.makeRiotPointsPurchase("Blitzcrank");
        } else if (championName.equals("Darius")) {
            return account.makeRiotPointsPurchase("Darius");
        } else if (championName.equals("Syndra")) {
            return account.makeRiotPointsPurchase("Syndra");
        } else if (championName.equals("Lucian")) {
            return account.makeRiotPointsPurchase("Lucian");
        } else if (championName.equals("Akali")) {
            return account.makeRiotPointsPurchase("Akali");
        } else if (championName.equals("Sejuani")) {
            return account.makeRiotPointsPurchase("Sejuani");
        } else if (championName.equals("Fiddlesticks")) {
            return account.makeRiotPointsPurchase("Fiddlesticks");
        } else {
            return "Not a valid champion";
        }
    }

    // EFFECTS: helps process the the sound of the champion purchased
    private void runRiotPointsPurchase2(String championName) {
        if (championName.equals("Blitzcrank")) {
            playSound("./data/Blitzcrank.wav");
        } else if (championName.equals("Darius")) {
            playSound("./data/Darius.wav");
        } else if (championName.equals("Syndra")) {
            playSound("./data/Syndra.wav");
        } else if (championName.equals("Lucian")) {
            playSound("./data/Lucian.wav");
        } else if (championName.equals("Akali")) {
            playSound("./data/Akali.wav");
        } else if (championName.equals("Sejuani")) {
            playSound("./data/Sejuani.wav");
        } else if (championName.equals("Fiddlesticks")) {
            playSound("./data/Fiddlesticks.wav");
        }
    }


    // REQUIRES: the name of the champion the user wants to purchase
    // EFFECTS: checks if a champion can be purchased already or returns how remaining amount of Blue Essence is needed
//    private void checkBlueEssenceBalanceDifference() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter the name of the champion you wish to purchase");
//        String championName = input.next();
//
//        if (championName.equals("Blitzcrank")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Blitzcrank"));
//        } else if (championName.equals("Darius")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Darius"));
//        } else if (championName.equals("Syndra")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Syndra"));
//        } else if (championName.equals("Lucian")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Lucian"));
//        } else if (championName.equals("Akali")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Akali"));
//        } else if (championName.equals("Sejuani")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Sejuani"));
//        } else if (championName.equals("Fiddlesticks")) {
//            System.out.println(selected.balanceBlueEssenceDifference("Fiddlesticks"));
//        } else {
//            System.out.println("Not a valid Champion");
//        }
//    }

    // REQUIRES: the name of the champion must be one of the possible options
    // EFFECTS: checks if a champion can be purchased using Blue Essence
    //          by looking at the difference between a champion's cost in Blue Essence and the account's balance
    private void checkBlueEssenceBalanceDifference() {
        JFrame recordWindow = new JFrame("Check if a champion can be purchased with Blue Essence");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateBlueEssenceDifferenceFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for checkBlueEssenceBalanceDifference()
    private void initiateBlueEssenceDifferenceFields(JFrame frame) {
        JLabel q7 = new JLabel("Check difference for mainAccount or altAccount?");
        new Label(q7);
//        q7.setPreferredSize(new Dimension(1000, 100));
//        q7.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q7);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q8 = new JLabel("Name of the champion you wish to check the difference for?");
        new Label(q8);
//        q8.setPreferredSize(new Dimension(1000, 100));
//        q8.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q8);

        JTextField name = new JTextField();
        new TextField(name);
//        name.setPreferredSize(new Dimension(1000, 100));
//        name.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(name);

        ImageIcon icon = new ImageIcon("./data/be.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateBlueEssenceDifferenceEnter(frame, account, name);
    }

    // EFFECTS: initiates enter button in checkBlueEssenceBalanceDifference()
    //          and checks the difference between a champion's Blue Essence cost and a designated account's balance
    private void initiateBlueEssenceDifferenceEnter(JFrame frame, JTextField account, JTextField name) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nameText = name.getText();
                    displayMessage(runBlueEssenceDifference(selectLeagueOfLegendsAccount(account.getText()), nameText));
                } catch (PurchaseFail e) {
                    displayMessage("No difference calculated, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps run the actual difference checker
    private String runBlueEssenceDifference(LeagueOfLegendsAccount account, String championName) throws PurchaseFail {
        if (championName.equals("Blitzcrank")) {
            return account.balanceBlueEssenceDifference("Blitzcrank");
        } else if (championName.equals("Darius")) {
            return account.balanceBlueEssenceDifference("Darius");
        } else if (championName.equals("Syndra")) {
            return account.balanceBlueEssenceDifference("Syndra");
        } else if (championName.equals("Lucian")) {
            return account.balanceBlueEssenceDifference("Lucian");
        } else if (championName.equals("Akali")) {
            return account.balanceBlueEssenceDifference("Akali");
        } else if (championName.equals("Sejuani")) {
            return account.balanceBlueEssenceDifference("Sejuani");
        } else if (championName.equals("Fiddlesticks")) {
            return account.balanceBlueEssenceDifference("Fiddlesticks");
        } else {
            return "Not a valid champion";
        }
    }

    // REQUIRES: the name of the champion the user wants to purchase
    // EFFECTS: checks if a champion can be purchased already or returns how much remaining Riot Points are needed
//    private void getRiotPointsBalanceDifference() {
//        LeagueOfLegendsAccount selected = selectLeagueOfLegendsAccount();
//        System.out.print("Enter the name of the champion you wish to purchase");
//        String championName = input.next();
//
//        if (championName.equals("Blitzcrank")) {
//            System.out.println(selected.balanceRiotPointsDifference("Blitzcrank"));
//        } else if (championName.equals("Darius")) {
//            System.out.println(selected.balanceRiotPointsDifference("Darius"));
//        } else if (championName.equals("Syndra")) {
//            System.out.println(selected.balanceRiotPointsDifference("Syndra"));
//        } else if (championName.equals("Lucian")) {
//            System.out.println(selected.balanceRiotPointsDifference("Lucian"));
//        } else if (championName.equals("Akali")) {
//            System.out.println(selected.balanceRiotPointsDifference("Akali"));
//        } else if (championName.equals("Sejuani")) {
//            System.out.println(selected.balanceRiotPointsDifference("Sejuani"));
//        } else if (championName.equals("Fiddlesticks")) {
//            System.out.println(selected.balanceRiotPointsDifference("Fiddlesticks"));
//        } else {
//            System.out.println("Not a valid Champion");
//        }
//    }

    // REQUIRES: the name of the champion must be one of the possible options
    // EFFECTS: checks if a champion can be purchased using Riot Points
    //          by looking at the difference between a champion's cost in Riot Points and the account's balance
    private void getRiotPointsBalanceDifference() {
        JFrame recordWindow = new JFrame("Check if a champion can be purchased with Riot Points");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateRiotPointsDifferenceFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for getRiotPointsBalanceDifference()
    private void initiateRiotPointsDifferenceFields(JFrame frame) {
        JLabel q7 = new JLabel("Check difference for mainAccount or altAccount?");
        new Label(q7);
//        q7.setPreferredSize(new Dimension(1000, 100));
//        q7.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q7);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q8 = new JLabel("Name of the champion you wish to check the difference for?");
        new Label(q8);
//        q8.setPreferredSize(new Dimension(1000, 100));
//        q8.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q8);

        JTextField name = new JTextField();
        new TextField(name);
//        name.setPreferredSize(new Dimension(1000, 100));
//        name.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(name);

        ImageIcon icon = new ImageIcon("./data/rp.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(750, 100));
        frame.add(label);

        initiateRiotPointsDifferenceEnter(frame, account, name);
    }

    // EFFECTS: initiates enter button in getRiotPointsBalanceDifference()
    //          and checks the difference between a champion's Riot Points cost and a designated account's balance
    private void initiateRiotPointsDifferenceEnter(JFrame frame, JTextField account, JTextField name) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nameText = name.getText();
                    displayMessage(runRiotPointsDifference(selectLeagueOfLegendsAccount(account.getText()), nameText));
                } catch (PurchaseFail e) {
                    displayMessage("No difference calculated, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps run the actual difference checker
    private String runRiotPointsDifference(LeagueOfLegendsAccount account, String championName) throws PurchaseFail {
        if (championName.equals("Blitzcrank")) {
            return account.balanceRiotPointsDifference("Blitzcrank");
        } else if (championName.equals("Darius")) {
            return account.balanceRiotPointsDifference("Darius");
        } else if (championName.equals("Syndra")) {
            return account.balanceRiotPointsDifference("Syndra");
        } else if (championName.equals("Lucian")) {
            return account.balanceRiotPointsDifference("Lucian");
        } else if (championName.equals("Akali")) {
            return account.balanceRiotPointsDifference("Akali");
        } else if (championName.equals("Sejuani")) {
            return account.balanceRiotPointsDifference("Sejuani");
        } else if (championName.equals("Fiddlesticks")) {
            return account.balanceRiotPointsDifference("Fiddlesticks");
        } else {
            return "Not a valid champion";
        }
    }

    // REQUIRES: difficulty is one of the possible options
    // MODIFIES: this
    // EFFECTS: prints a list of recommended champions for a given difficulty
    //          and adds all of them to the Recommended collection
    private void receiveChampionRecommendation() {
        JFrame recordWindow = new JFrame("Receive a new champion recommendation");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateChampionRecommendationFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for receiveChampionRecommendation()
    private void initiateChampionRecommendationFields(JFrame frame) {
        JLabel q5 = new JLabel("Receive recommendation for mainAccount or altAccount?");
        new Label(q5);
//        q5.setPreferredSize(new Dimension(1000, 100));
//        q5.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q5);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q6 = new JLabel("Difficulty of champions you wish to be recommended(Beginner, Novice, or Advanced)?");
        new Label(q6);
//        q6.setPreferredSize(new Dimension(1000, 100));
//        q6.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q6);

        JTextField difficulty = new JTextField();
        new TextField(difficulty);
//        difficulty.setPreferredSize(new Dimension(1000, 100));
//        difficulty.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(difficulty);

        ImageIcon icon = new ImageIcon("./data/recommendicon.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(1000, 100));
        frame.add(label);

        initiateChampionRecommendationEnter(frame, account, difficulty);
    }

    // EFFECTS: initiates enter button in receiveChampionRecommendation()
    //          and adds the resulting recommended champions to the Recommended collection
    private void initiateChampionRecommendationEnter(JFrame frame, JTextField account, JTextField difficulty) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String difficultyLevel = difficulty.getText();
                    displayMessage(runRecommendation(selectLeagueOfLegendsAccount(account.getText()), difficultyLevel));
                } catch (Exception e) {
                    displayMessage("No recommendations provided, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps run the actual recommendation
    private String runRecommendation(LeagueOfLegendsAccount account, String difficultyLevel) {
        if (difficultyLevel.equals("Beginner")) {
            return championRecommendation(account, "Beginner");
        } else if (difficultyLevel.equals("Novice")) {
            return championRecommendation(account, "Novice");
        } else if (difficultyLevel.equals("Advanced")) {
            return championRecommendation(account, difficultyLevel);
        } else {
            return "Not an existing classification of difficulty (Beginner, Novice, Advanced)";
        }
    }

    // REQUIRES: a keyword that specifies the difficulty of champions the user wishes to receive recommendations for
    // MODIFIES: this
    // EFFECTS: provides a user a list of recommended champions based on a given difficulty
    private String championRecommendation(LeagueOfLegendsAccount account, String difficultyLevel) {
        if (difficultyLevel.equals("Beginner")) {
            account.myRecommended.addChampionName("Darius");
            return "For Beginner difficulty we recommend Darius";
        } else if (difficultyLevel.equals("Novice")) {
            account.myRecommended.addChampionName("Blitzcrank");
            account.myRecommended.addChampionName("Lucian");
            account.myRecommended.addChampionName("Sejuani");
            return "For Novice difficulty we recommend Blitzcrank, Lucian, and Sejuani";
        } else if (difficultyLevel.equals("Advanced")) {
            account.myRecommended.addChampionName("Syndra");
            account.myRecommended.addChampionName("Akali");
            account.myRecommended.addChampionName("Fiddlesticks");
            return "For Advanced difficulty we recommend Syndra, Akali, and Fiddlesticks";
        } else {
            return "Not an existing classification of difficulty (Beginner, Novice, Advanced)";
        }
    }


    // REQUIRES: the name of the champion must be one of the options
    // MODIFIES: this
    // EFFECTS: adds a champion to the Favourite collection
    private void favouriteChampion() {
        JFrame recordWindow = new JFrame("Favourite a champion");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateFavouriteChampionFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for favouriteChampion()
    private void initiateFavouriteChampionFields(JFrame frame) {
        JLabel q5 = new JLabel("Favourite champion for mainAccount or altAccount?");
        new Label(q5);
//        q5.setPreferredSize(new Dimension(1200, 100));
//        q5.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q5);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(1200, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        JLabel q6 = new JLabel("Name of the champion you wish to favourite?");
        new Label(q6);
//        q6.setPreferredSize(new Dimension(1200, 100));
//        q6.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q6);

        JTextField name = new JTextField();
        new TextField(name);
//        name.setPreferredSize(new Dimension(1200, 100));
//        name.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(name);

        ImageIcon icon = new ImageIcon("./data/m7fflair.png");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(1200, 250));
        frame.add(label);

        initiateFavouriteChampionEnter(frame, account, name);
    }

    // EFFECTS: initiates enter button in favouriteChampion()
    //          and adds the desired champion to the Favourite Collection
    private void initiateFavouriteChampionEnter(JFrame frame, JTextField account, JTextField name) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String nameText = name.getText();
                    displayMessage(runFavouriteChampion(selectLeagueOfLegendsAccount(account.getText()), nameText));
                } catch (Exception e) {
                    displayMessage("Champion not added to favourites, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: helps run the favourite function
    private String runFavouriteChampion(LeagueOfLegendsAccount account, String championName) {
        if (championName.equals("Blitzcrank")) {
            return makeFavouriteChampion(account, "Blitzcrank");
        } else if (championName.equals("Darius")) {
            return makeFavouriteChampion(account, "Darius");
        } else if (championName.equals("Syndra")) {
            return makeFavouriteChampion(account, "Syndra");
        } else if (championName.equals("Lucian")) {
            return makeFavouriteChampion(account, "Lucian");
        } else if (championName.equals("Akali")) {
            return makeFavouriteChampion(account, "Akali");
        } else if (championName.equals("Sejuani")) {
            return makeFavouriteChampion(account, "Sejuani");
        } else if (championName.equals("Fiddlesticks")) {
            return makeFavouriteChampion(account, "Fiddlesticks");
        } else {
            return "Not a valid champion";
        }
    }

    // REQUIRES: the name of the champion the user wishes to favorite
    // MODIFIES: this
    // EFFECTS: adds the champion's name to the collection of favourite champions
    private String makeFavouriteChampion(LeagueOfLegendsAccount account, String championName) {
        if (championName.equals("Blitzcrank")) {
            account.myFavourites.addChampionName("Blitzcrank");
        } else if (championName.equals("Darius")) {
            account.myFavourites.addChampionName("Darius");
        } else if (championName.equals("Syndra")) {
            account.myFavourites.addChampionName("Blitzcrank");
        } else if (championName.equals("Lucian")) {
            account.myFavourites.addChampionName("Blitzcrank");
        } else if (championName.equals("Akali")) {
            account.myFavourites.addChampionName("Blitzcrank");
        } else if (championName.equals("Sejuani")) {
            account.myFavourites.addChampionName("Blitzcrank");
        } else if (championName.equals("Fiddlesticks")) {
            account.myFavourites.addChampionName("Blitzcrank");
        }
        return championName + "is now a favourite champion!";
    }


    // REQUIRES: a LeagueOfLegendsAccount
    // EFFECTS: prints the account details for a given LeagueOfLegends account
    private void printLeagueOfLegendsAccount() {
        JFrame recordWindow = new JFrame("Print out account details");
//        recordWindow.setLocationRelativeTo(null);
//        recordWindow.getContentPane().setLayout(new BoxLayout(recordWindow.getContentPane(), BoxLayout.Y_AXIS));
        initiateAccountPrintFields(recordWindow);
        new Frame(recordWindow);
//        recordWindow.pack();
//        recordWindow.setVisible(true);
    }

    // EFFECTS: Initiates texts fields for printLeagueOfLegendsAccount()
    private void initiateAccountPrintFields(JFrame frame) {
        JLabel q5 = new JLabel("Print details for mainAccount or altAccount?");
        new Label(q5);
//        q5.setPreferredSize(new Dimension(1000, 100));
//        q5.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(q5);

        JTextField account = new JTextField();
        new TextField(account);
//        account.setPreferredSize(new Dimension(1000, 100));
//        account.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.add(account);

        ImageIcon icon = new ImageIcon("./data/printicon.JPG");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(1000, 100));
        frame.add(label);

        initiateAccountPrintEnter(frame, account);
    }

    // EFFECTS: initiates enter button in printLeagueOfLegendsAccount()
    //          and prints out account details in one long string
    private void initiateAccountPrintEnter(JFrame frame, JTextField account) {
        JButton enter = new JButton("Confirm");
        new Confirm(enter);
//        enter.setPreferredSize(new Dimension(100, 100));
//        enter.setFont(new Font("Arial", Font.PLAIN, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    displayMessage(printLeagueOfLegendsAccountDetails(selectLeagueOfLegendsAccount(account.getText())));
                } catch (Exception e) {
                    displayMessage("Account not printed, invalid input.");
                }
                frame.setVisible(false);
            }
        });
        frame.add(enter);
    }

    // EFFECTS: Produces a string representation of the details of a LeagueOfLegends account
    private String printLeagueOfLegendsAccountDetails(LeagueOfLegendsAccount account) {
        return "[ region = " + account.getRegion() + ",\n  name = " + account.getInGameName() + ", \n"
                + "blueEssenceBalance = " + account.getBlueEssenceBalance() + "BE" + ", \n"
                + "riotPointsBalance = " + account.getRiotPointsBalance() + "RP" + ", \n"
                + account.getCollectionSize() + " Champions owned" + ", \n"
                + "Champions owned = "
                + account.myCollection.getListOfChampionName(account.myCollection) + ", \n"
                + account.getRecommendedSize() + " Recommended Champions" + ",\n"
                + "Recommended Champions = "
                + account.myRecommended.getListOfChampionName(account.myRecommended) + ", \n"
                + account.getFavouritesSize() + " Favourite Champions" + ", \n"
                + "Favourite Champions = "
                + account.myFavourites.getListOfChampionName(account.myFavourites) + "\n]";
    }

}
