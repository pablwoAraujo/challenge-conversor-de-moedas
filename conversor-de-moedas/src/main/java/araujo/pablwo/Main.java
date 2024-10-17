package araujo.pablwo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String base = "BRL";
    private static final String[] currencyCodes = {"BRL", "USD", "EUR", "GBP", "JPY", "KRW", "ARS"};
    private static final List<ConversionHistory> history = new ArrayList<>();

    private static final HttpService service = new HttpService();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        base = "BRL";

        String menuOption;
        while (true) {
            printMenu();
            menuOption = input.nextLine();

            String finalBase = base;
            Object[] target = Arrays.stream(currencyCodes).filter(predicate -> !predicate.equals(finalBase)).toArray();
            switch (menuOption) {
                case "1":
                    convert(input, base, target[0].toString());
                    break;
                case "2":
                    convert(input, base, target[1].toString());
                    break;
                case "3":
                    convert(input, base, target[2].toString());
                    break;
                case "4":
                    convert(input, base, target[3].toString());
                    break;
                case "5":
                    convert(input, base, target[4].toString());
                    break;
                case "6":
                    convert(input, base, target[5].toString());
                    break;
                case "7":
                    changeCurrencyBase(input);
                    break;
                case "8":
                    otherConversions(input);
                    break;
                case "9":
                    viewConversionHistory();
                    break;
                case "0":
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            System.out.print("-- Pressione ENTER para voltar --");
            input.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("Seja Bem Vindo Ao Conversor de Moedas");

        int count = 0;
        for (String code : currencyCodes) {
            if (!base.equals(code)) {
                count++;
                System.out.printf("%d. %s para %s%n", count, base, code);
            }
        }

        System.out.println("7. Mude sua moeda favorita");
        System.out.println("8. Veja mais opções de moedas");
        System.out.println("9. Ver histórico de conversões");
        System.out.println("0. Sair");

        System.out.println("-- digite o número da operação correspondente --");
    }

    public static void convert(Scanner input, String base, String target) {
        System.out.print("Digite o quanto você quer converter: ");
        double amount = 0;

        try {
            String inputText = input.nextLine();
            amount = Double.parseDouble(inputText.replace(',', '.'));

            LocalDateTime date = LocalDateTime.now();
            ConversionResult result = service.getConversionResult(base, target, amount);
            history.add(new ConversionHistory(date, result.baseCode(), result.targetCode(), amount, result.conversionRate(), result.conversionResult()));
            System.out.printf("Resultado: %.2f %s%n", result.conversionResult(), target);
        } catch (Exception e) {
            System.out.println("Falha na conversão, tente novamente!");
        }
    }

    private static void changeCurrencyBase(Scanner input) {
        System.out.println("Digite o número correspondente a moeda escolhida");

        int count2 = 0;
        for (String code : currencyCodes) {
            count2++;
            System.out.printf("%d. %s%n", count2, code);
        }

        try {
            String newBase = input.nextLine();
            base = currencyCodes[Integer.parseInt(newBase) - 1];
        } catch (Exception e) {
            System.out.println("Falha ao trocar de moeda, tente novamente!");
        }
    }

    public static void otherConversions(Scanner input) {
        System.out.println("Veja todas as opções disponíveis para conversão");

        Currencies[] currencies = Currencies.values();
        boolean controle = true;

        for (Currencies moeda : currencies) {
            if (controle) {
                System.out.printf("%-5s%-32s%-31s |   ", moeda.name(), moeda.currencyName(), moeda.country());
            } else {
                System.out.printf("%-5s%-33s%s\n", moeda.name(), moeda.currencyName(), moeda.country());
            }
            controle = !controle;
        }
        System.out.println();

        System.out.print("Digite a sigla da moeda base: ");
        String baseCurrency = input.nextLine();

        System.out.print("Digite a sigla da moeda convertida: ");
        String targetCurrency = input.nextLine();

        convert(input, baseCurrency, targetCurrency);
    }


    public static void viewConversionHistory() {
        if (history.isEmpty()) {
            System.out.println("Nenhuma conversão foi realizada!");
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            history.forEach((a) -> {
                String rate = String.format("(1 %s = %f %s)", a.base(), a.rate(), a.target());
                String formattedDate = a.date().format(format);

                System.out.printf("[%s] %.2f %s para %s %s = %.2f %s\n", formattedDate, a.amount(), a.base(), a.target(), rate, a.result(), a.target());
            });
        }
    }
}