package haven.pathfinder;


public class Node
{
    public enum Type {
    	MOOR(0.5),
    	GRASS(0.5),
    	HEATH(0.5),
    	PAVEMENT(0.5),
    	MOUNTAIN(0.5),
    	NORMAL(1),
    	SWAMP(3),
    	THICKET(3),
    	BLOCK(Double.MAX_VALUE),
    	BLOCK_DYNAMIC(Double.MAX_VALUE),
    	NOT_IMPLEMENTED(1);
    	
    	private final double w;
	    Type(double w) { this.w = w; }
	    public double getValue() { return w; }
    }
    
    public int x, y;
    public int clearance;
    public static Node srcNode;
    public static Node dstNode;

    public Type type = Type.NORMAL;
    
    private transient double distFromSrc = -1;
    private transient double distFromDst = -1;

    private boolean partOfPath = false;
    public boolean pathTraversed = false;


    public Node() {
    }
    
    public Node(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public void addToPathFromSrc(double distSoFar){
    	distFromSrc = distSoFar + type.getValue();
    }
    
    public void addToPathFromDst(double distSoFar) {
    	distFromDst = distSoFar + type.getValue();
    }
    
    public static Node getSrc() {
        return srcNode;
    }
    
    public static Node getDst() {
        return dstNode;
    }
    
    public boolean isSrc() {
        return srcNode == this;
    }
    
    public boolean isDst() {
        return dstNode == this;
    }
    
    public boolean isPartOfPath() {
        return partOfPath;
    }
    
    public void setPartOfPath(boolean isInPath) {
        partOfPath = isInPath;
    }
   
    public double distFromSrc() {
        if(Node.srcNode == this)
        	return 0;
        if(type == Node.Type.BLOCK || type == Node.Type.BLOCK_DYNAMIC)
        	return -1;
        
        return distFromSrc;
    }
    
    public double distFromDst() {
        if(Node.dstNode == this)
        	return 0;
        if(type == Node.Type.BLOCK || type == Node.Type.BLOCK_DYNAMIC)
        	return -1;
        
        return distFromDst;
    }
}