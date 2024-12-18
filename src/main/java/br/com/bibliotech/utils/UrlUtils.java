package br.com.bibliotech.utils;

import br.com.bibliotech.domain.model.*;

public class UrlUtils {

    private static final String API_DOMAIN = "bibliotech";

    public static String getAuthorUrl(Author author) {
        return String.format("/%s/author/%s", API_DOMAIN, author.getStageName());
    }

    public static String getCategoryUrl(Category category) {
        return String.format("/%s/category/%s", API_DOMAIN, category.getName());
    }

    public static String getGenreUrl(Genre genre) {
        return String.format("/%s/author/%s", API_DOMAIN, genre.getName());
    }

    public static String getPublisherUrl(Publisher publisher) {
        return String.format("/%s/author/%s", API_DOMAIN, publisher.getTradeName());
    }

    public static String getLoanRequestUrl(LoanRequest request) {
        return String.format("/%s/request/%s", API_DOMAIN, request.getId());
    }
}
