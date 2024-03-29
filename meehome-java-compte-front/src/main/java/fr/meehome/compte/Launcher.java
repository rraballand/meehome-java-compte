package fr.meehome.compte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.meehome.compte.services.ICompteService;
import fr.meehome.compte.services.IUserService;
import fr.meehome.compte.services.dto.CompteDto;

public class Launcher {

    public static BufferedReader bufferedReader;

    public static ApplicationContext applicationContext;

    public static ICompteService compteService;

    public static IUserService userService;

    public static void init() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        applicationContext = new ClassPathXmlApplicationContext("fr/meehome/compte/services/applicationContext.xml");
        userService = (IUserService ) applicationContext.getBean("userServiceImpl");
        compteService = (ICompteService ) applicationContext.getBean("compteServiceImpl");
    }

    public static void main(String[] args) {

        init();

        boolean run = true;

        System.out.println("---------------------------------");
        System.out.println("- Welcome                       -");
        System.out.println("---------------------------------");

        while (run) {
            try {
                switch (showAccueil()) {
                    case 1:
                        showListeCompte();
                        break;
                    case 2:
                        nouveauCompte();
                        break;
                    case 'Q':
                        run = false;
                        break;
                    default:
                        try {
                            showAccueil();
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            printError();
                        } catch (IOException e) {
                            e.printStackTrace();
                            printError();
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                printError();
            } catch (IOException e) {
                e.printStackTrace();
                printError();
            }
        }

        System.out.println("---------------------------------");
        System.out.println("- Good bye                      -");
        System.out.println("---------------------------------");
    }

    private static int showAccueil() throws NumberFormatException, IOException {
        System.out.println("---------------------------------");
        System.out.println("1 - Liste des comptes");
        System.out.println("2 - Nouveau compte");
        System.out.println("Q - Quitter");
        System.out.println("---------------------------------");
        return Integer.parseInt(bufferedReader.readLine());
    }

    private static void showListeCompte() {
        System.out.println("---------------------------------");
        System.out.println("- Liste des comptes        -");
        System.out.println("---------------------------------");
        List<CompteDto> listCompteDto = compteService.getAll();
        if (listCompteDto.isEmpty()) {
            System.out.println("liste vide...");
        } else {
            for (CompteDto compteDto : listCompteDto) {
                System.out.println(compteDto.getLibelle());
            }
        }
    }

    private static void nouveauCompte() throws NumberFormatException, IOException {
        System.out.println("---------------------------------");
        System.out.println("- Nouveau compte        -");
        System.out.println("---------------------------------");
        System.out.println("- Nom du compte : ");
        String name = bufferedReader.readLine();

        List<CompteDto> listCompteDto = new ArrayList<CompteDto>();
        CompteDto compteDto = new CompteDto();
        compteDto.setLibelle(name);
        listCompteDto.add(compteDto);
        compteService.add(listCompteDto);
    }

    private static void printError() {
        System.out.println("---------------------------------");
        System.out.println("Erreur de lecture, veuillez reessayer");
        System.out.println("---------------------------------");
    }
}
