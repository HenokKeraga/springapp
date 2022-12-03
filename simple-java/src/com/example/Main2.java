package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {
//        var map = new HashMap<>(Map.ofEntries(
//                Map.entry(1, new ArrayList<>(List.of(new Doc(1, new ArrayList<>(List.of("r", "z"))), new Doc(1, new ArrayList<>(List.of("f", "g"))), new Doc(1, new ArrayList<>(List.of("r", "z")))))),
//                Map.entry(2, new ArrayList<>(List.of(new Doc(2, new ArrayList<>(List.of("a", "f"))), new Doc(2, new ArrayList<>(List.of("b", "f"))))))
//        ));
        List<Doc> lists = Arrays.asList(
                new Doc(1, new ArrayList<>(List.of("r", "z"))),
                new Doc(1, new ArrayList<>(List.of("f", "g"))),
                new Doc(1, new ArrayList<>(List.of("r", "z"))),
                new Doc(2, new ArrayList<>(List.of("a", "f"))),
                new Doc(2, new ArrayList<>(List.of("b", "f")))
        );

        var collect = lists
                .stream()
                .collect(Collectors.groupingBy(Doc::id, LinkedHashMap::new, Collectors.toList()));
        var collect1 = lists
                .stream()
                .collect(Collectors.groupingBy(Doc::id, Collectors.reducing((doc, doc2) -> {
                    List<String> list = new ArrayList<>();
                    list.addAll(doc.member());
                    list.addAll(doc2.member());
                    list.sort(String::compareTo);
                    return new Doc(doc.id(),list);
                }))).values().stream().map(Optional::get).collect(Collectors.toList());
        System.out.println(collect1);

        var reduce = collect.values().stream()
                .map(docs -> docs.stream().reduce((doc, doc2) -> {
                    doc.member().addAll(doc2.member());

                    return doc;
                }).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        System.out.println(reduce);
    }

}


record Doc(int id, List<String> member) {
}
