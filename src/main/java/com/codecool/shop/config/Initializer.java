package com.codecool.shop.config;

import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.PublisherDao;
import com.codecool.shop.dao.implementation.GenreDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.PublisherDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Publisher;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        GenreDao productCategoryDataStore = GenreDaoMem.getInstance();
        PublisherDao supplierDataStore = PublisherDaoMem.getInstance();

        //setting up a new supplier
        Publisher amazon = new Publisher("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Publisher lenovo = new Publisher("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        Genre tablet = new Genre("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        // Peti's books
        productDataStore.add(new Product("The diving bell and the butterfly", 49.9f, "USD", "The Diving Bell and the Butterfly is a memoir by journalist Jean-Dominique Bauby. It describes his life before and after suffering a massive stroke that left him with locked-in syndrome.", tablet, amazon));
        productDataStore.add(new Product("The troop", 49.9f, "USD", "Once a year, scoutmaster Tim Riggs leads a troop of boys into the Canadian wilderness for a three-day camping trip - tradition as comforting and reliable as a good ghost story and a roaring bonfire. But when an unexpected intruder - shockingly thin, disturbingly pale, and voraciously hungry - stumbles upon their campsite, Tim and the boys are exposed to something far more frightening than any tale of terror. The human carrier of a bioengineered nightmare. An inexplicable horror that spreads faster than fear. A harrowing struggle for survival that will pit the troop against the elements, the infected ... and one another.", tablet, amazon));
        productDataStore.add(new Product("Player piano", 49.9f, "USD", "The story takes place in a near-future society that is almost totally mechanized, eliminating the need for human laborers. The widespread mechanization creates conflict between the wealthy upper class, the engineers and managers, who keep society running, and the lower class, whose skills and purpose in society have been replaced by machines. The book uses irony and sentimentality, which were to become hallmarks developed further in Vonnegut's later works", tablet, amazon));
        productDataStore.add(new Product("Homeland", 49.9f, "USD", "The young prince of a royal house, Drizzt grows to maturity in the vile world of his dark kin. Possessing honor beyond the scope of his unprincipled society, young Drizzt faces an inevitable dilemma. Can he live in a world that rejects integrity?", tablet, amazon));
        productDataStore.add(new Product("A scanner darkly", 49.9f, "USD", "Substance D – otherwise known as Death – is the most dangerous drug ever to find its way onto the black market. It destroys the links between the brain's two hemispheres, leading first to disorentation and then to complete and irreversible brain damage. Bob Arctor, undercover narcotics agent, is trying to find a lead to the source of supply, but to pass as an addict he must become a user, and soon, without knowing what is happening to him, he is as dependent as any of the addicts he is monitoring.", tablet, amazon));

        // Kornel's books
        productDataStore.add(new Product("The Snowman", 11.33f, "USD", "The night the first snow falls a young boy wakes to find his mother gone.", tablet, amazon));
        productDataStore.add(new Product("Harry Potter and the Philosopher's Stone", 14.33f, "USD", "Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive.", tablet, amazon));
        productDataStore.add(new Product("Angels & Demons", 11.93f, "USD", "An ancient secret brotherhood. A devastating new weapon of destruction. An unthinkable target.", tablet, amazon));
        productDataStore.add(new Product("And Then There Were None", 11.11f, "USD", "Ten strangers are lured to an isolated island mansion off the Devon coast by a mysterious 'U.N.Owen'.", tablet, amazon));
        productDataStore.add(new Product("The Hundred-Year-Old Man Who Climbed Out Of The Window And Disappeared", 8.99f, "USD", "Sitting quietly in his room in an old people's home, Allan Karlsson is waiting for a party he doesn't want to begin.", tablet, amazon));

        // Gabor's books
        productDataStore.add(new Product("Robert Merle - Malevil", 49.9f, "USD", "Malevil is a 1972 science fiction novel by French writer Robert Merle. The story's events take place in rural France in the late twentieth century during the unexpected outbreak of nuclear war.", tablet, amazon));
        productDataStore.add(new Product("Ilf-Petrov - 12 Chairs", 479, "USD", "The Twelve Chairs is a classic satirical novel by the Odessan Soviet authors Ilf and Petrov, published in 1928. Its plot follows characters attempting to obtain jewelry hidden in a chair. Its main character Ostap Bender reappears in the book's sequel The Golden Calf, in spite of his apparent death in Chairs.", tablet, lenovo));
        productDataStore.add(new Product("George Orwell - Animal Farm", 89, "USD", "Animal Farm is an allegorical novella by George Orwell, first published in England on 17 August 1945. The book tells the story of a group of farm animals who rebel against their human farmer, hoping to create a society where the animals can be equal, free, and happy. Ultimately, however, the rebellion is betrayed and the farm ends up in a state as bad as it was before, under the dictatorship of a pig named Napoleon.", tablet, amazon));
        productDataStore.add(new Product("Tom Sharpe - The Wilt Alternative", 89, "USD", "In this, the second of Tom Sharpe's chronicles about Henry Wilt, our hero is no longer the victim of his own uncontrolled fantasies. He becomes the unintentional participant in a terrorist siege that he is forced to find an answer to the problems of power, which have corrupted greater men than he.", tablet, amazon));
        productDataStore.add(new Product("Richard Scarry - Busytown", 89, "USD", "Busytown is a fictional town depicted in several books by the children's author Richard Scarry. Busytown is inhabited by an assortment of anthropomorphic animals, including Huckle Cat, Lowly Worm, Mr. Frumble, police Sergeant Murphy, Mr. Fixit, Bananas Gorilla and Hilda Hippo.", tablet, amazon));
    }
}
