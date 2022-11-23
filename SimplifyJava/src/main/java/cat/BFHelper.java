package cat;

import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO
 *
 * @author nanyin
 * @version 1.0
 * @date 2021-11-04
 */
public class BFHelper<T> {

    private long numBits;

    /**
     * Number of hashes per element
     */
    private int numHashFunctions;

    /**
     * The funnel to translate Ts to bytes
     */
    private Funnel<? super T> funnel;

    public BFHelper(long numBits, int numHashFunctions, Funnel<? super T> funnel) {
        this.numBits = numBits;
        this.numHashFunctions = numHashFunctions;
        this.funnel = funnel;
    }

    public static <T> BFHelper<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp) {
        checkNotNull(funnel);
        checkArgument(
                expectedInsertions >= 0, "Expected insertions (%s) must be >= 0", expectedInsertions);
        checkArgument(fpp > 0.0, "False positive probability (%s) must be > 0.0", fpp);
        checkArgument(fpp < 1.0, "False positive probability (%s) must be < 1.0", fpp);

        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }

        long numBits = optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
        return new BFHelper<T>(numBits, numHashFunctions, funnel);
    }

    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        // (m / n) * log(2), but avoid truncation due to division!
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    public long[] recordHashOffset(T object) {

        long bitSize = this.numBits;
        long[] record = new long[this.numHashFunctions];

        long hash64 = Hashing.murmur3_128().hashObject(object, this.funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);

        boolean bitsChanged = false;
        for (int i = 1; i <= this.numHashFunctions; i++) {
            int combinedHash = hash1 + (i * hash2);
            // Flip all the bits if it's negative (guaranteed positive number)
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
                long l = combinedHash % bitSize;
                record[i] = l;
            }
        }
        return record;
    }
}
