package moblima;

/**
 * Interface to be implemented in all databases.
 */
public interface Database {
	/**
	 * Updates the File that saves the data of the database.
	 */
	public void updateDatabase();
	/**
	 * Fetches data from the File that saves data of the database.
	 */
	public void fetchDatabase();
}
