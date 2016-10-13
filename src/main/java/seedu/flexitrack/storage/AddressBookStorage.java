package seedu.flexitrack.storage;

import seedu.flexitrack.commons.exceptions.DataConversionException;
import seedu.flexitrack.model.ReadOnlyFlexiTrack;

import java.io.IOException;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.flexitrack.model.FlexiTrack}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    String getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFlexiTrack}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFlexiTrack> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyFlexiTrack> readAddressBook(String filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFlexiTrack} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyFlexiTrack addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyFlexiTrack)
     */
    void saveAddressBook(ReadOnlyFlexiTrack addressBook, String filePath) throws IOException;

}
