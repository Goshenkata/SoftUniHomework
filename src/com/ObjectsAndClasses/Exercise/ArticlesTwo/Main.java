package com.ObjectsAndClasses.Exercise.ArticlesTwo;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] comm = scanner.nextLine().split(", ");
            articleList.add(new Article(comm[0], comm[1], comm[2]));
        }
        String sortBy = scanner.nextLine();
        switch (sortBy) {
            case "title":
                articleList.stream().sorted(Comparator.comparing(Article::getTitle)).forEach(System.out::println);
                break;
            case "content":
                articleList.stream().sorted(Comparator.comparing(Article::getContent)).forEach(System.out::println);
                break;
            case "author":
                articleList.stream().sorted(Comparator.comparing(Article::getAuthor)).forEach(System.out::println);
                break;
        }
    }
}
