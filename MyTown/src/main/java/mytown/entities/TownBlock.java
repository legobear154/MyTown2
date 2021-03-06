package mytown.entities;

import mytown.entities.town.Town;


/**
 * Defines what a Town block is. A single chunk that belongs to a single town.
 * 
 * @author Joe Goett
 */
public class TownBlock {
	public static String keyFormat = "%s;%s;%s";

	private int id;
	private int dim;
	private int x, z;
	private Town town;
	private String key;


	
	/**
	 * Used internally only!
	 * 
	 * @param id
	 * @param x
	 * @param z
	 * @param dim
	 */
	public TownBlock(int id, Town town, int x, int z, int dim) {
		this(town, x, z, dim);
		this.id = id;
	}

	/**
	 * Creates a TownBlock with the given x, z, and dim
	 * 
	 * @param x
	 * @param z
	 * @param dim
	 */
	public TownBlock(Town town, int x, int z, int dim) {
		this.x = x;
		this.z = z;
		this.dim = dim;
		this.town = town;
		key = String.format(TownBlock.keyFormat, dim, x, z);
	}

	/**
	 * Returns the id of the block. Used only when loading/saving!
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Used internally only
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the dim the block resides in
	 * 
	 * @return
	 */
	public int getDim() {
		return dim;
	}

	/**
	 * Returns the x position of the block
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the z position of the block
	 * 
	 * @return
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Returns the Town that this TownBlock belongs to
	 * 
	 * @return
	 */
	public Town getTown() {
		return town;
	}

	/**
	 * Used to store the TownBlock in a Map for easy access
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Returns whether or not the block specified by X and Z coords is in this TownBlock
	 * Note: Y param is not needed since chunks are only X and Z dependant
	 * 
	 * @param x
	 * @param z
	 * @param dim
	 * @return
	 */
	public boolean isBlockInChunk(int x, int z, int dim) {
		return (x >> 4 == this.x && z >> 4 == this.z && dim == this.dim);
	}
	
	@Override
	public String toString() {
		return String.format("[Dim:%s;X:%s;Z:%s]", this.dim, this.x << 4, this.z << 4);
	}

}