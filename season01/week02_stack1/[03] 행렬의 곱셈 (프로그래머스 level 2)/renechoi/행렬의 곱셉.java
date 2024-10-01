class Solution {

	public int[][] solution(int[][] arr1, int[][] arr2) {
		return multiplyMatrices(arr1, arr2);
	}

	private int[][] multiplyMatrices(int[][] arr1, int[][] arr2) {
		int rows1 = arr1.length;
		int cols1 = arr1[0].length;
		int cols2 = arr2[0].length;

		int[][] result = new int[rows1][cols2];

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				result[i][j] = calculateDotProduct(arr1, arr2, i, j, cols1);
			}
		}

		return result;
	}

	// arr1의 i번째 행과 arr2의 j번째 열의 내적을 계산
	private int calculateDotProduct(int[][] arr1, int[][] arr2, int row, int col, int commonLength) {
		int sum = 0;
		for (int k = 0; k < commonLength; k++) {
			sum += arr1[row][k] * arr2[k][col];
		}
		return sum;
	}
}