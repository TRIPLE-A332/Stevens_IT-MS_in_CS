import java.io.*;

public class CandySampler {
    // Function to calculate the number of unique arrangements
    public static long countArrangements(int n, int s, int l) {
        // DP array
        long[][][] dp = new long[n + 1][s + 1][l + 1];

        // Base case: Only one way to arrange a sampler of size 0 (leave it empty)
        for (int i = 0; i <= s; i++) {
            for (int j = 0; j <= l; j++) {
                dp[0][i][j] = 1;
            }
        }

        // Fill the DP table
        for (int size = 1; size <= n; size++) {
            for (int sq = 0; sq <= s; sq++) {
                for (int ln = 0; ln <= l; ln++) {
                    // Adding square candies
                    if (sq > 0) {
                        dp[size][sq][ln] += dp[size - 1][sq][ln] * sq;
                    }
                    // Adding long candies
                    if (ln > 0 && size >= 2) {
                        dp[size][sq][ln] += dp[size - 2][sq][ln] * ln;
                    }
                }
            }
        }

        return dp[n][s][l];
    }

    public static void main(String[] args) {
        // File handling for input and output
        try {
            // Reading input from "input.txt"
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int testCases = Integer.parseInt(br.readLine().trim());
            int[][] inputs = new int[testCases][3];

            for (int i = 0; i < testCases; i++) {
                String[] parts = br.readLine().trim().split(" ");
                inputs[i][0] = Integer.parseInt(parts[0]); // n
                inputs[i][1] = Integer.parseInt(parts[1]); // s
                inputs[i][2] = Integer.parseInt(parts[2]); // l
            }
            br.close();

            // Writing output to "output.txt"
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < testCases; i++) {
                int n = inputs[i][0], s = inputs[i][1], l = inputs[i][2];
                long result = countArrangements(n, s, l);
                bw.write(result + "\n");
            }
            bw.close();

            System.out.println("Output written to output.txt");

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}
