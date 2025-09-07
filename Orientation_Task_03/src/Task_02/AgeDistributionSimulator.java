package Task_02;

public class AgeDistributionSimulator {
    public static void main(String[] args) {
        final int SAMPLES = 1000;
        final int MAX_AGE = 30;

        // Age distribution data for student group
        int ageDistribution[][] = {
                {15, 18},
                {35, 19},
                {60, 20},
                {80, 21},
                {92, 22},
                {97, 23},
                {100, 24}
        };

        // Array to count generated ages
        int generatedAges[] = new int[MAX_AGE + 1];

        System.out.println("Age Distribution Simulator");
        System.out.println("Generating " + SAMPLES + " age samples...\n");

        // Run multiple test runs with different seeds to show variability
        for (int testRun = 1; testRun <= 3; testRun++) {
            System.out.println("Test Run " + testRun);

            // Reset counters for each test run
            for (int i = 0; i <= MAX_AGE; i++) {
                generatedAges[i] = 0;
            }

            // Generate ages according to the distribution
            for (int i = 1; i <= SAMPLES; i++) {
                int randomNum = (int)(Math.random() * 100) + 1;
                int j = 0;

                // Search for the correct age range
                while (j < ageDistribution.length && randomNum > ageDistribution[j][0]) {
                    j++;
                }

                // Increment count for the found age
                if (j < ageDistribution.length) {
                    generatedAges[ageDistribution[j][1]]++;
                }
            }

            // Output results for this test run
            System.out.println("Age  Count  Percentage  Expected%");

            double expectedPercentages[] = {15, 20, 25, 20, 12, 5, 3}; // Expected percentages
            int ages[] = {18, 19, 20, 21, 22, 23, 24}; // Corresponding ages

            for (int i = 0; i < ages.length; i++) {
                int age = ages[i];
                int count = generatedAges[age];
                double actualPercentage = (double)count / SAMPLES * 100;

                System.out.printf("%-4d %-6d %-10.1f %-9.1f\n",
                        age, count, actualPercentage, expectedPercentages[i]);
            }

            System.out.println();
        }

        // Demonstrate seeded randomness for reproducibility
        System.out.println("Demonstrating Reproducibility with Seeds");

        java.util.Random seededRandom1 = new java.util.Random(12345);
        java.util.Random seededRandom2 = new java.util.Random(12345);

        System.out.println("Two generators with same seed (12345):");
        System.out.print("Generator 1: ");
        for (int i = 0; i < 5; i++) {
            int randomNum = seededRandom1.nextInt(100) + 1;
            int j = 0;
            while (j < ageDistribution.length && randomNum > ageDistribution[j][0]) {
                j++;
            }
            if (j < ageDistribution.length) {
                System.out.print(ageDistribution[j][1] + " ");
            }
        }

        System.out.print("\nGenerator 2: ");
        for (int i = 0; i < 5; i++) {
            int randomNum = seededRandom2.nextInt(100) + 1;
            int j = 0;
            while (j < ageDistribution.length && randomNum > ageDistribution[j][0]) {
                j++;
            }
            if (j < ageDistribution.length) {
                System.out.print(ageDistribution[j][1] + " ");
            }
        }

        // Show different seeds produce different sequences
        java.util.Random seededRandom3 = new java.util.Random(54321);
        System.out.print("Generator 3 (seed 54321): ");
        for (int i = 0; i < 5; i++) {
            int randomNum = seededRandom3.nextInt(100) + 1;
            int j = 0;
            while (j < ageDistribution.length && randomNum > ageDistribution[j][0]) {
                j++;
            }
            if (j < ageDistribution.length) {
                System.out.print(ageDistribution[j][1] + " ");
            }
        }
    }
}
