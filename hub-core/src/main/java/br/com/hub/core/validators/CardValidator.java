package br.com.hub.core.validators;

import br.com.hub.domain.Transaction;

import java.util.regex.Pattern;

/**
 * Created by STELO\renato.5a on 8/11/18.
 */
public class CardValidator {
    private static Pattern pattern = Pattern.compile("^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$");

    public static void validateTrancaton(Transaction transaction) {
        if (transaction == null) throw new RuntimeException();
        if (transaction.getAction() == null
                || transaction.getAction().isEmpty()) throw new RuntimeException();
        if (transaction.getCardnumber() == null
                || transaction.getCardnumber().isEmpty()
                || !pattern.matcher(transaction.getCardnumber()).matches())
            throw new RuntimeException();
        if (transaction.getAmount() == null) {
            if (!transaction.getAction().equals("balance")) {
                if (transaction.getAmount().isInfinite()
                        || transaction.getAmount().isNaN()
                        || transaction.getAmount() < 0) throw new RuntimeException();
            }
        }

    }
}
