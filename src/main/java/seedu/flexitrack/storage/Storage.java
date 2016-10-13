package seedu.flexitrack.storage;

import seedu.flexitrack.commons.events.model.FlexiTrackChangedEvent;
import seedu.flexitrack.commons.events.storage.DataSavingExceptionEvent;
import seedu.flexitrack.commons.exceptions.DataConversionException;
import seedu.flexitrack.model.ReadOnlyFlexiTrack;
import seedu.flexitrack.model.UserPrefs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends FlexiTrackStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    String getFlexiTrackFilePath();

    @Override
    Optional<ReadOnlyFlexiTrack> readFlexiTrack() throws DataConversionException, IOException;

    @Override
    void saveFlexiTrack(ReadOnlyFlexiTrack addressBook) throws IOException;

    /**
     * Saves the current version of the Address Book to the hard disk.
     *   Creates the data file if it is missing.
     * Raises {@link DataSavingExceptionEvent} if there was an error during saving.
     */
    void handleAddressBookChangedEvent(FlexiTrackChangedEvent abce);
}
