import model.Account;
import model.SavingsAccount;
import util.Repository;

public class RepositoryDemo {

    public static void main(String[] args) {

        Repository<Account> repo = new Repository<>();

        Account a1 = new SavingsAccount("Purv", 5000, 1000);
        Account a2 = new SavingsAccount("Rahul", 2000, 500);
        Account a3 = new SavingsAccount("Amit", 9000, 1000);

        repo.saveAll(a1, a2, a3);

        System.out.println("===== FIND ACCOUNT =====");
        System.out.println(repo.findById(a2.getAccountNumber()));

        System.out.println("\n===== ALL ACCOUNTS =====");
        for (Account account : repo.findAll()) {
            System.out.println(account);
        }

        System.out.println("\nTotal accounts: " + repo.size());

        System.out.println("\n===== AVERAGE =====");
        Integer[] numbers = {10, 20, 30, 40};
        System.out.println("Average: " + Repository.average(numbers));

        System.out.println("\n===== DELETE ACCOUNT =====");
        System.out.println("Deleted: " + repo.delete(a2.getAccountNumber()));
        System.out.println("Remaining accounts: " + repo.size());
    }
}

