package com.codecool.shop.config;

import com.codecool.shop.dao.AuthorDao;
import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.RecommenderDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Recommender;
import com.codecool.shop.model.Author;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Memory Stores
        AuthorDao authorDataStore = AuthorDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        GenreDao genreDataStore = GenreDaoMem.getInstance();
        RecommenderDao recommenderDataStore = RecommenderDaoMem.getInstance();

        // DataBase Stores
        GenreDao genreDataStoreDB = GenreDaoJdbc.getInstance();
        AuthorDao authorDataStoreDB = AuthorDaoJdbc.getInstance();
        ProductDao productDataStoreDB = ProductDaoJdbc.getInstance();
        RecommenderDao supplierDataStoreDB = RecommenderDaoJdbc.getInstance();


        //setting up a new Recommender
        Recommender peti = new Recommender("Peti");
        supplierDataStoreDB.add(peti);
        Recommender kornel = new Recommender("Kornel");
        supplierDataStoreDB.add(kornel);
        Recommender gabor = new Recommender("Gabor");
        supplierDataStoreDB.add(gabor);
        Recommender olic = new Recommender("Olic");
        supplierDataStoreDB.add(olic);

        Integer petiID = supplierDataStoreDB.findByName(peti.getName());
        Integer kornelID = supplierDataStoreDB.findByName(kornel.getName());
        Integer gaborID = supplierDataStoreDB.findByName(gabor.getName());
        Integer olicID = supplierDataStoreDB.findByName(olic.getName());



        //setting up authors
        Author jeanDominiqueBauby = new Author("Jean-Dominique Bauby");
        Author nickCutter = new Author("Nick Cutter");
        Author kurtVonnegut = new Author("Kurt Vonnegut");
        Author rASalvatore = new Author("R. A. Salvatore");
        Author philipKDick = new Author("Philip K. Dick");
        Author joNesbo = new Author("Jo Nesbo");
        Author jKRowling = new Author("J. K. Rowling");
        Author danBrown = new Author("Dan Brown");
        Author agathaChristie = new Author("Agatha Christie");
        Author jonasJonasson = new Author("Jonas Jonasson");
        Author robertMerle = new Author("Robert Merle");
        Author ilfPetrov = new Author("Ilf-Petrov");
        Author georgeOrwell = new Author("George Orwell");
        Author tomSharpe = new Author("Tom Sharpe");
        Author richardScarry = new Author("Richard Scarry");
        Author georgeRRMartin = new Author("George R. R. Martin");
        authorDataStoreDB.add(jeanDominiqueBauby);
        authorDataStoreDB.add(nickCutter);
        authorDataStoreDB.add(kurtVonnegut);
        authorDataStoreDB.add(rASalvatore);
        authorDataStoreDB.add(philipKDick);
        authorDataStoreDB.add(joNesbo);
        authorDataStoreDB.add(jKRowling);
        authorDataStoreDB.add(danBrown);
        authorDataStoreDB.add(agathaChristie);
        authorDataStoreDB.add(jonasJonasson);
        authorDataStoreDB.add(robertMerle);
        authorDataStoreDB.add(ilfPetrov);
        authorDataStoreDB.add(georgeOrwell);
        authorDataStoreDB.add(tomSharpe);
        authorDataStoreDB.add(richardScarry);
        authorDataStoreDB.add(georgeRRMartin);

        for (Author author : authorDataStoreDB.getAll()) {
            authorDataStore.add(author);
        }

        Integer jdbID = authorDataStoreDB.findByName(jeanDominiqueBauby.getName());
        Integer nickCutterID = authorDataStoreDB.findByName(nickCutter.getName());
        Integer kurtVonnegutID = authorDataStoreDB.findByName(kurtVonnegut.getName());
        Integer salvatoreID = authorDataStoreDB.findByName(rASalvatore.getName());
        Integer pkdID = authorDataStoreDB.findByName(philipKDick.getName());
        Integer joNesboID = authorDataStoreDB.findByName(joNesbo.getName());
        Integer jkrID = authorDataStoreDB.findByName(jKRowling.getName());
        Integer danBrownID = authorDataStoreDB.findByName(danBrown.getName());
        Integer agathaChID = authorDataStoreDB.findByName(agathaChristie.getName());
        Integer jonasJoID = authorDataStoreDB.findByName(jonasJonasson.getName());
        Integer robertMerleID = authorDataStoreDB.findByName(robertMerle.getName());
        Integer ilfPetrovID = authorDataStoreDB.findByName(ilfPetrov.getName());
        Integer georgeOrwellID = authorDataStoreDB.findByName(georgeOrwell.getName());
        Integer tomSharpeID = authorDataStoreDB.findByName(tomSharpe.getName());
        Integer richardScarryID = authorDataStoreDB.findByName(richardScarry.getName());
        Integer georgeRRMartingID = authorDataStoreDB.findByName(georgeRRMartin.getName());



        //setting up a new Genre
        Genre thriller = new Genre("Thriller");
        Genre childrensBook = new Genre("Childrens");
        Genre fantasy = new Genre("Fantasy");
        Genre satire = new Genre("Satire");
        genreDataStore.add(thriller);
        genreDataStore.add(childrensBook);
        genreDataStore.add(satire);
        genreDataStore.add(fantasy);

        genreDataStoreDB.add(thriller);
        genreDataStoreDB.add(fantasy);
        genreDataStoreDB.add(childrensBook);
        genreDataStoreDB.add(satire);

        Integer thrillerId = genreDataStoreDB.findByName(thriller.getName());
        Integer fantasyId = genreDataStoreDB.findByName(fantasy.getName());
        Integer childrensBookId = genreDataStoreDB.findByName(childrensBook.getName());
        Integer satireId = genreDataStoreDB.findByName(satire.getName());

//        setting up products and printing it
//         Peti's books
        // Store books in the database via ProductDaoJDBC


        // Setting products to store them in the memory
        // Peti's books
        productDataStore.add(new Product("The diving bell and the butterfly", 8.99f, "USD", "The Diving Bell and the Butterfly is a memoir by journalist Jean-Dominique Bauby. It describes his life before and after suffering a massive stroke that left him with locked-in syndrome.", thrillerId, petiID, jdbID));
        productDataStore.add(new Product("The troop", 14.54f, "USD", "Once a year, scoutmaster Tim Riggs leads a troop of boys into the Canadian wilderness for a three-day camping trip - tradition as comforting and reliable as a good ghost story and a roaring bonfire. But when an unexpected intruder - shockingly thin, disturbingly pale, and voraciously hungry - stumbles upon their campsite, Tim and the boys are exposed to something far more frightening than any tale of terror. The human carrier of a bioengineered nightmare. An inexplicable horror that spreads faster than fear. A harrowing struggle for survival that will pit the troop against the elements, the infected ... and one another.", thrillerId, petiID, nickCutterID));
        productDataStore.add(new Product("Player piano", 12.82f, "USD", "The story takes place in a near-future society that is almost totally mechanized, eliminating the need for human laborers. The widespread mechanization creates conflict between the wealthy upper class, the engineers and managers, who keep society running, and the lower class, whose skills and purpose in society have been replaced by machines. The book uses irony and sentimentality, which were to become hallmarks developed further in Vonnegut's later works", satireId, petiID, kurtVonnegutID));
        productDataStore.add(new Product("Homeland", 12.84f, "USD", "The young prince of a royal house, Drizzt grows to maturity in the vile world of his dark kin. Possessing honor beyond the scope of his unprincipled society, young Drizzt faces an inevitable dilemma. Can he live in a world that rejects integrity?", fantasyId, petiID, salvatoreID));
        productDataStore.add(new Product("A scanner darkly", 8.92f, "USD", "Substance D - otherwise known as Death - is the most dangerous drug ever to find its way onto the black market. It destroys the links between the brain's two hemispheres, leading first to disorentation and then to complete and irreversible brain damage. Bob Arctor, undercover narcotics agent, is trying to find a lead to the source of supply, but to pass as an addict he must become a user, and soon, without knowing what is happening to him, he is as dependent as any of the addicts he is monitoring.", fantasyId, petiID, pkdID));

        // Kornel's books
        productDataStore.add(new Product("The Snowman", 11.33f, "USD", "The night the first snow falls a young boy wakes to find his mother gone.", thrillerId, kornelID, joNesboID));
        productDataStore.add(new Product("Harry Potter and the Philosopher's Stone", 14.33f, "USD", "Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive.", fantasyId, kornelID, jkrID));
        productDataStore.add(new Product("Angels & Demons", 11.93f, "USD", "An ancient secret brotherhood. A devastating new weapon of destruction. An unthinkable target.", thrillerId, kornelID, danBrownID));
        productDataStore.add(new Product("And Then There Were None", 11.11f, "USD", "Ten strangers are lured to an isolated island mansion off the Devon coast by a mysterious 'U.N.Owen'.", thrillerId, kornelID, agathaChID));
        productDataStore.add(new Product("The Hundred-Year-Old Man Who Climbed Out Of The Window And Disappeared", 8.99f, "USD", "Sitting quietly in his room in an old people's home, Allan Karlsson is waiting for a party he doesn't want to begin.", thrillerId, kornelID, jonasJoID));

        // Gabor's books
        productDataStore.add(new Product("Malevil", 19.9f, "USD", "Malevil is a 1972 science fiction novel by French writer Robert Merle. The story's events take place in rural France in the late twentieth century during the unexpected outbreak of nuclear war.", thrillerId, gaborID, robertMerleID));
        productDataStore.add(new Product("12 Chairs", 14.79f, "USD", "The Twelve Chairs is a classic satirical novel by the Odessan Soviet authors Ilf and Petrov, published in 1928. Its plot follows characters attempting to obtain jewelry hidden in a chair. Its main character Ostap Bender reappears in the book's sequel The Golden Calf, in spite of his apparent death in Chairs.", satireId, gaborID, ilfPetrovID));
        productDataStore.add(new Product("Animal Farm", 12.6f, "USD", "Animal Farm is an allegorical novella by George Orwell, first published in England on 17 August 1945. The book tells the story of a group of farm animals who rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy. Ultimately, however, the rebellion is betrayed and the farm ends up in a state as bad as it was before, under the dictatorship of a pig named Napoleon.", satireId, gaborID, georgeOrwellID));
        productDataStore.add(new Product("The Wilt Alternative", 8.99f, "USD", "In this, the second of Tom Sharpe's chronicles about Henry Wilt, our hero is no longer the victim of his own uncontrolled fantasies. He becomes the unintentional participant in a terrorist siege that he is forced to find an answer to the problems of power, which have corrupted greater men than he.", satireId, gaborID, tomSharpeID));
        productDataStore.add(new Product("Busytown", 12.89f, "USD", "Busytown is a fictional town depicted in several books by the children's author Richard Scarry. Busytown is inhabited by an assortment of anthropomorphic animals, including Huckle Cat, Lowly Worm, Mr. Frumble, police Sergeant Murphy, Mr. Fixit, Bananas Gorilla and Hilda Hippo.", childrensBookId, gaborID, richardScarryID));

        productDataStore.add(new Product("PRE_ORDER - Toilet paper origami", 12.89f, "USD", "Useful skill for the time of quarantine", thrillerId, gaborID, richardScarryID));

        // Olic
        productDataStore.add(new Product("The World of Ice & Fire", 20.05f, "USD", "THE WORLD OF ICE AND FIRE. This lavishly illustrated volume is a comprehensive history of the Seven Kingdoms, providing vividly constructed accounts of the epic battles, bitter rivalries, and daring rebellions that lead to the events of A Song of Ice and Fire and HBO's Game of Thrones.", fantasyId, olicID, georgeRRMartingID));
        productDataStore.add(new Product("Digital Fortress", 15.25f, "USD", "Digital Fortress is a techno-thriller novel written by American author Dan Brown and published in 1998 by St. Martin's Press. The book explores the theme of government surveillance of electronically stored information on the private lives of citizens, and the possible civil liberties and ethical implications of using such technology.", thrillerId, olicID, danBrownID));
        // Store products (books) in the database - Missing parameters
        for (Product product: productDataStore.getAll()) {
            productDataStoreDB.add(product);
        }
    }


}
