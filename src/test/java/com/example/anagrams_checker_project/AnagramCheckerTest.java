package com.example.anagrams_checker_project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnagramCheckerTest {
    @ParameterizedTest
    @MethodSource("testcases")
    void testAreAnagramsUsingSorting(String first, String second, boolean result) {
        assertThat(AnagramChecker.areAnagramsUsingSorting(first, second)).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("testcases")
    void testAreAnagramsUsingCount(String first, String second, boolean result) {
        assertThat(AnagramChecker.areAnagramsUsingCounting(first, second)).isEqualTo(result);
    }

    static Stream<Arguments> testcases() {
        return Stream.of(
                Arguments.of("listen", "silent", true),
                Arguments.of("debit card", "bad credit", true),
                Arguments.of("hello", "world", false),
                Arguments.of("hello", "hola", false),
                Arguments.of("listen", "silentt", false),
                Arguments.of(null, "silent", false),
                Arguments.of("listen", null, false),
                Arguments.of(null, null, false)
        );
    }


    @ParameterizedTest
    @MethodSource("performanceTestcases")
    void testPerformance(String text1, String text2) {
        long sortingStartTime = System.nanoTime();
        AnagramChecker.areAnagramsUsingSorting(text1, text2);
        long sortingEndTime = System.nanoTime();
        long sortingTime = sortingEndTime - sortingStartTime;

        long countingStartTime = System.nanoTime();
        AnagramChecker.areAnagramsUsingCounting(text1, text2);
        long countingEndTime = System.nanoTime();
        long countingTime = countingEndTime - countingStartTime;

        System.out.println("Sorting Time: " + sortingTime + " ns");
        System.out.println("Counting Time: " + countingTime + " ns");

        assertThat(AnagramChecker.areAnagramsUsingSorting(text1, text2))
                .isEqualTo(AnagramChecker.areAnagramsUsingCounting(text1, text2));
    }

    // Rest of the methods are unchanged...

    static Stream<Arguments> performanceTestcases() {
        return Stream.of(
                Arguments.of("listen", "silent"),
                Arguments.of("debit card", "bad credit"),
                Arguments.of("hello", "world"),
                Arguments.of("listen", "silentt")
        );
    }
}