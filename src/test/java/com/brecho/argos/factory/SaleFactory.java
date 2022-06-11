package com.brecho.argos.factory;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.brecho.argos.domain.sale.core.enums.InventoryItemStatus;
import com.brecho.argos.domain.sale.core.models.*;
import com.brecho.argos.domain.user.core.enums.Role;
import com.brecho.argos.domain.user.core.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class SaleFactory implements TemplateLoader {
    private static final String UUID_REGEX = "[0-9a-fa-f]{8}\\-[0-9a-fa-f]{4}\\-[0-9a-fa-f]{4}\\-[0-9a-fa-f]{4}\\-[0-9a-fa-f]{12}";
    public static final String VALID_SALE = "valid_sale";
    public static final String VALID_SALE_ITEM = "valid_sale_item";
    public static final String VALID_INVENTORY_ITEM = "valid_inventory_item";
    public static final String VALID_PRODUCT = "valid_product";
    public static final String VALID_PRODUCT_CLASSIFICATION = "valid_product_classification";
    public static final String VALID_BUYER = "valid_buyer";
    public static final String VALID_SELLER = "valid_seller";

    @Override
    public void load() {
        Fixture.of(Sale.class).addTemplate(VALID_SALE, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("buyer", one(User.class, VALID_BUYER));
            add("saleItems", has(3).of(SaleItem.class, VALID_SALE_ITEM));
        }});

        Fixture.of(SaleItem.class).addTemplate(VALID_SALE_ITEM, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("product", one(Product.class, VALID_PRODUCT));
            add("amount", random(Integer.class, range(1, 10)));
        }});

        Fixture.of(InventoryItem.class).addTemplate(VALID_INVENTORY_ITEM, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("status", InventoryItemStatus.AVAILABLE);
            add("amount", random(Integer.class, range(1, 10)));
            add("createdAt", LocalDateTime.now());
            add("updatedAt", LocalDateTime.now());
            add("product", one(Product.class, VALID_PRODUCT));
        }});

        Fixture.of(Product.class).addTemplate(VALID_PRODUCT, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("name", random("Panela", "Talher", "Prato", "Escorredor", "Faca"));
            add("price", random(BigDecimal.class, range(10.00, 400.00)));
            add("createdAt", LocalDateTime.now());
            add("updatedAt", LocalDateTime.now());
            add("seller", one(User.class, VALID_SELLER));
            add("productClassification", one(ProductClassification.class, VALID_PRODUCT_CLASSIFICATION));
        }});

        Fixture.of(ProductClassification.class).addTemplate(VALID_PRODUCT_CLASSIFICATION, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("value", random("Classificação X", "Classificação Y", "Classificação Z"));
        }});

        Fixture.of(User.class).addTemplate(VALID_BUYER, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("username", random("mpsouza", "margato", "alijesus"));
            add("password", "pass123");
            add("name", random("Matheus Pera", "Osvaldo Maisgato", "Alisson Abençoado Jesus"));
            add("email", random("email@gmail.com", "email@yahoo.com", "email@apple.com"));
            add("cpf", random("34501620198", "451022559876", "31605188475"));
            add("birthdate", LocalDate.of(1998, 8, 15));
            add("role", Role.BUYER);
            add("createdAt", LocalDateTime.now());
            add("updatedAt", LocalDateTime.now());
        }});

        Fixture.of(User.class).addTemplate(VALID_SELLER, new Rule(){{
            add("id", regex(UUID_REGEX));
            add("username", random("nick", "robert", "darthvader"));
            add("password", "pass456");
            add("name", random("Nickson Patt", "Robert Nigro", "Darth Vader"));
            add("email", random("mail@gmail.com", "mail@yahoo.com", "mail@apple.com"));
            add("cpf", random("465512036050", "45102239645", "45781026525"));
            add("birthdate", LocalDate.of(1995, 4, 6));
            add("role", Role.SELLER);
            add("createdAt", LocalDateTime.now());
            add("updatedAt", LocalDateTime.now());
        }});


    }
}
