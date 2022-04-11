
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(21000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(499000000000L);
    private final static String RECIPIENT = "0x35CdDd6cD10863F9F896c8B679f52DbA070fd5Ee";


    public static void main(String[] args) throws Exception {

        // Ether network declaration & instantiation
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));

        // Credential declaration & instantiation
        Credentials credentials = WalletUtils.loadCredentials("Password1234",
                "/Users/haruncansurav/Desktop/Test Files/testfile3");

        // Echoing the address (as a connection test of some sort)
        System.out.println(credentials.getAddress());

        // Transaction Ex 1

        /*
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3j,
                credentials,
                RECIPIENT,
                BigDecimal.valueOf(20),
                Convert.Unit.ETHER).send();
        */

        // Transaction Ex 2

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        Transfer transfer = new Transfer(web3j, transactionManager);
        TransactionReceipt transactionReceipt = transfer.sendFunds(
                RECIPIENT,
                BigDecimal.valueOf(1),
                Convert.Unit.ETHER,
                GAS_PRICE,
                GAS_LIMIT).send();
    }
}
