import services.AuthenticationService;
import services.options.BankingOptionsService;
import services.options.StartingOptionsService;
import services.options.UserOptions;

public class Application {

    public static void main(String[] args) {
        StartingOptionsService userInputService = new StartingOptionsService();
        BankingOptionsService bankingOptionsService = new BankingOptionsService();

        while (true) {
            UserOptions userOptions;
            if (AuthenticationService.getCurrentUser() == null) {
                userOptions = userInputService;
            } else {
                userOptions = bankingOptionsService;
            }
            userOptions.showOptions();
        }

    }

}