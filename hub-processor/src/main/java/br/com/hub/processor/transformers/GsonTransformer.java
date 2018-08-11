package br.com.hub.processor.transformers;

import br.com.hub.domain.Transaction;
import br.com.hub.domain.builders.TransactionBuilder;
import com.google.gson.Gson;
import org.springframework.integration.transformer.GenericTransformer;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
public class GsonTransformer {

    public static GenericTransformer<Object, Transaction> fromJson() {
        return o -> {
            try {
                return new Gson().fromJson(new String((byte[]) o), Transaction.class);
            }catch (Exception ex){
                return TransactionBuilder.aTransaction().build();
            }

        };
    }

    public static GenericTransformer<Object, byte[]> toJson() {
        return o -> new Gson().toJson(o).getBytes();
    }
}
