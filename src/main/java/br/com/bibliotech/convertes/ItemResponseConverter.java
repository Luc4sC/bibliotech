package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Item;
import br.com.bibliotech.responses.CopyResponse;
import br.com.bibliotech.responses.ItemResponse;
import br.com.bibliotech.responses.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ItemResponseConverter implements Converter<ItemResponse, Item> {

    @Autowired
    private CopyResponseConverter copyResponseConverter;

    @Autowired
    private LoanResponseConverter loanResponseConverter;

    @Override
    public ItemResponse convert(Item item) {
        CopyResponse copyResponse = copyResponseConverter.convert(item.getCopy());
        LoanResponse loanResponse = loanResponseConverter.convert(item.getLoan());
        return new ItemResponse(loanResponse, copyResponse);
    }

    @Override
    public List<ItemResponse> convertEach(List<Item> items) {
        List<ItemResponse> itemResponses = new ArrayList<>();

        items.forEach(item -> {
            ItemResponse itemResponse = convert(item);
            itemResponses.add(itemResponse);
        });

        return itemResponses;
    }
}
