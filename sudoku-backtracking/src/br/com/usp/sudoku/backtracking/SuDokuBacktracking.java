package br.com.usp.sudoku.backtracking;

public class SuDokuBacktracking {

	private Integer[][] sudoku;
	private final int size;
	private final int boxOffset;

	public SuDokuBacktracking(Integer[][] sudoku) {
		super();
		this.sudoku = sudoku;
		this.size = sudoku.length;
		this.boxOffset = (int) Math.sqrt(this.size);
	}

	public void solve() {
		if (process(0, 0)) {
			print(false);
		} else {
			System.err.println("Solution not found!");
		}
	}

	private boolean process(int row, int col) {
		if (row == size) {
			row = 0;
			if (++col == size) {
				return true;
			}
		}

		if (sudoku[row][col] != 0) {
			return process(row + 1, col);
		}

		for (int value = 1; value <= size; value++) {
			if (isLegal(row, col, value)) {
				sudoku[row][col] = value;
				if (process(row + 1, col)) {
					return true;
				}
			}
		}

		sudoku[row][col] = 0;
		return false;
	}

	protected boolean isLegal(int row, int col, int value) {
		int boxRow = ((row / 3) * 3) - 1;
		int boxCol = 0;

		for (int i = 0; i < size; i++) {
			if (value == sudoku[row][i] || value == sudoku[i][col]) {
				return false;
			} else {
				int index = i % boxOffset;
				if (index == 0) {
					boxRow++;
					boxCol = (col / 3) * 3;
				}

				if (value == sudoku[boxRow][boxCol + index]) {
					return false;
				}
			}
		}

		return true;
	}

	protected synchronized void print(boolean isFormatted) {
		if (isFormatted) {
			for (int i = 0; i < size; i++) {
				if (i % 3 == 0) {
					System.out.println(" -----------------------");
				}
				for (int j = 0; j < size; j++) {
					if (j % 3 == 0) {
						System.out.print("| ");
					}
					System.out.printf("%s ", sudoku[i][j] == 0 ? " " : Integer.toString(sudoku[i][j]));
				}
				System.out.println("|");
			}
			System.out.println(" -----------------------");
		} else {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.printf("%d ", sudoku[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
