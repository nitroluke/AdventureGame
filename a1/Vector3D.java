package esof322.a1;

public class Vector3D {
    /**
     * The tolerable difference between two doubles considered equivalent
     */
    public static final double EQUAL_TOLERANCE = 0.001;
    /**
     * The first component of the vector
     */
    public final double x;
    /**
     * The second component of the vector
     */
    public final double y;
    /**
     * The third component of the vector
     */
    public final double z;
    /**
     * Constructs a Vector3D from three components
     * @param x The first component of the vector
     * @param y The second component of the vector
     * @param z The third component of the vector
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    /**
     * Determines if two doubles are similar enough to be equal using EQUAL_TOLERANCE
     * @param a The first double to compare
     * @param b The second double to compare
     * @return Whether the two doubles are equal
     */
    private boolean dequals(double a, double b) {
        return Math.abs(a - b) < EQUAL_TOLERANCE;
    }
    /**
     * Determines if two Vector3D's are similar enough to be equal using EQUAL_TOLERANCE
     * @param v The vector to compare with this one
     * @return Whether the two vectors are equal
     */
    public boolean equals(Vector3D v) {
        return dequals(this.x, v.x) && dequals(this.y, v.y) && dequals(this.z, v.z);
    }
    

    /**
     * Scales a vector by a scalar
     * @param scale The scalar to scale the vector
     * @return the scaled vector
     */
    public Vector3D scale(double scale){
        return new Vector3D(scale*x, scale*y, scale*z);
    }
}
