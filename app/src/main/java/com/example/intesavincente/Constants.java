package com.example.intesavincente;

public class Constants {

    public static final String SHARED_PREFERENCES_FILE_NAME = "it.unimib.worldnews.preferences";
    public static final String SHARED_PREFERENCES_COUNTRY_OF_INTEREST = "COUNTRY_OF_INTEREST";
    public static final String SHARED_PREFERENCES_TOPICS_OF_INTEREST = "TOPICS_OF_INTEREST";

    public static final String NEWS_API_BASE_URL = "https://newsapi.org/v2/";
    public static final String TOP_HEADLINES_ENDPOINT = "top-headlines";
    public static final String TOP_HEADLINES_COUNTRY_PARAMETER = "country";
    public static final String NEWS_API_KEY = "";

    public static final String LAST_UPDATE = "last_update";
    public static final String PAGE_SIZE = "pageSize";
    public static final int MAX_NEWS_RESULTS_PER_PAGE = 10;
    public static final int FRESH_TIMEOUT = 60*1000; // 1 minute in milliseconds

    public static final String NEWS_DATABASE_NAME = "news_db";
    public static final int DATABASE_VERSION = 1;

    public static final String AUTHENTICATION_TOKEN = "AUTHENTICATION_TOKEN";
    public static final String USER_ID = "USER_ID";
    public static final String DEFAULT_WEB_CLIENT_ID = "96542353010-e74gqtjvt7k58nod92ojb6s3sqtm0kuu.apps.googleusercontent.com";

    public static final String FIREBASE_DATABASE_URL = "https://intesavincente-e720b-default-rtdb.europe-west1.firebasedatabase.app/";

    public static final String USER_COLLECTION = "users";
    public static final String USER_EMAIL = "email";
}
