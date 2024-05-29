package com.example.file;

// import StemmerPorterRU;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.file.StemmerPorterRU;

public class TextProcessor {

    private static final Set<String> STOP_WORDS = Set.of(
        "и", "в", "во", "не", "что", "он", "на", "я", "с", "со",
        "как", "а", "то", "все", "она", "так", "его", "но", "да",
        "ты", "к", "у", "же", "вы", "за", "бы", "по", "только",
        "ее", "мне", "было", "вот", "от", "меня", "еще", "нет",
        "о", "из", "ему", "теперь", "когда", "даже", "ну", "вдруг",
        "ли", "если", "уже", "или", "ни", "быть", "был", "него",
        "до", "вас", "нибудь", "опять", "уж", "вам", "ведь",
        "там", "потом", "себя", "ничего", "ей", "может", "они",
        "тут", "где", "есть", "надо", "ней", "для", "мы", "тебя",
        "их", "чем", "была", "сам", "чтоб", "без", "будто", "чего",
        "раз", "тоже", "себе", "под", "будет", "ж", "тогда", "кто",
        "этот", "того", "потому", "этого", "какой", "совсем",
        "ним", "здесь", "этом", "один", "почти", "мой", "тем",
        "чтобы", "нее", "сейчас", "были", "куда", "зачем", "всех",
        "никогда", "можно", "при", "наконец", "два", "об", "другой",
        "хоть", "после", "над", "больше", "тот", "через", "эти",
        "нас", "про", "всего", "них", "какая", "много", "разве",
        "три", "эту", "моя", "впрочем", "хорошо", "свою", "этой",
        "перед", "иногда", "лучше", "чуть", "том", "нельзя",
        "такой", "им", "более", "всегда", "конечно", "всю", "между"
    );

    public static List<String> extractKeywords(String text, int frequencyThreshold) {
        String cleanedText = text.toLowerCase()
                                 .replaceAll("[^а-яёa-z\\s]", "")
                                 .replaceAll("\\s+", " ");
        StemmerPorterRU stemmer = new StemmerPorterRU();
    
        List<String> words = Arrays.asList(cleanedText.split(" "));
        // Стеммируем слова и удаляем стоп-слова
        List<String> stemmedWords = words.stream()
                                         .map(StemmerPorterRU::stem)
                                         .filter(word -> !STOP_WORDS.contains(word))
                                         .collect(Collectors.toList());
    
        // Подсчитываем частоту каждого слова
        Map<String, Long> wordFrequencies = stemmedWords.stream()
                                                        .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println(wordFrequencies);
    
        // Оставляем только те слова, которые встречаются больше или равно frequencyThreshold
        List<String> keywords = wordFrequencies.entrySet().stream()
        .filter(entry -> entry.getValue() >= frequencyThreshold)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());


        return keywords;
    }
}
