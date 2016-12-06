package DataTypes;

// from http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
public class Pair <L, R> {
    public L left;
    public R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override public boolean equals(Object o) {
      if (!(o instanceof Pair)) 
          return false;
      Pair pairo = (Pair) o;
      return this.left.equals(pairo.left) && this.right.equals(pairo.right);
    }
}
