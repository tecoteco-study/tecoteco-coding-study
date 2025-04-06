package template.programmers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Solution {
	public int[] solution(int n, String[] words) {
		Set<String> usedWords = new HashSet<>();

		for (int i = 0; i < words.length; i++) {
			int person = (i % n) + 1;
			int turn = (i / n) + 1;

			String currentWord = words[i];
			String prevWord = (i == 0) ? null : words[i - 1];

			if (!isValidWord(currentWord, prevWord, usedWords)) {
				return new int[] {person, turn};
			}

			usedWords.add(currentWord);
		}

		return new int[] {0, 0};
	}

	private boolean isValidWord(String currentWord, String prevWord, Set<String> usedWords) {
		if (usedWords.contains(currentWord)) {
			return false;
		}

		if (prevWord != null && prevWord.charAt(prevWord.length() - 1) != currentWord.charAt(0)) {
			return false;
		}

		return true;
	}

	@Nested
	class WordChainTests {

		@Test
		void testCase1() {
			int n = 3;
			String[] words = {
				"tank", "kick", "know",
				"wheel", "land", "dream",
				"mother", "robot", "tank"
			};
			int[] expected = {3, 3};
			assertArrayEquals(expected, solution(n, words));
		}

		@Test
		void testCase2() {
			int n = 5;
			String[] words = {
				"hello", "observe", "effect", "take", "either",
				"recognize", "encourage", "ensure", "establish", "hang",
				"gather", "refer", "reference", "estimate", "executive"
			};
			int[] expected = {0, 0};
			assertArrayEquals(expected, solution(n, words));
		}

		@Test
		void testCase3() {
			int n = 2;
			String[] words = {
				"hello", "one", "even", "never",
				"now", "world", "draw"
			};
			int[] expected = {1, 3};
			assertArrayEquals(expected, solution(n, words));
		}
	}

}

