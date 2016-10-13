package seedu.flexitrack.storage;

import com.google.common.eventbus.Subscribe;
import seedu.flexitrack.commons.core.ComponentManager;
import seedu.flexitrack.commons.core.LogsCenter;
import seedu.flexitrack.commons.events.model.FlexiTrackChangedEvent;
import seedu.flexitrack.commons.events.storage.DataSavingExceptionEvent;
import seedu.flexitrack.commons.exceptions.DataConversionException;
import seedu.flexitrack.model.ReadOnlyFlexiTrack;
import seedu.flexitrack.model.UserPrefs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlexiTrackStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(FlexiTrackStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public StorageManager(String addressBookFilePath, String userPrefsFilePath) {
        this(new XmlFlexiTrackStorage(addressBookFilePath), new JsonUserPrefsStorage(userPrefsFilePath));
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public String getFlexiTrackFilePath() {
        return addressBookStorage.getFlexiTrackFilePath();
    }

    @Override
    public Optional<ReadOnlyFlexiTrack> readFlexiTrack() throws DataConversionException, IOException {
        return readFlexiTrack(addressBookStorage.getFlexiTrackFilePath());
    }

    @Override
    public Optional<ReadOnlyFlexiTrack> readFlexiTrack(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readFlexiTrack(filePath);
    }

    @Override
    public void saveFlexiTrack(ReadOnlyFlexiTrack addressBook) throws IOException {
        saveFlexiTrack(addressBook, addressBookStorage.getFlexiTrackFilePath());
    }

    @Override
    public void saveFlexiTrack(ReadOnlyFlexiTrack addressBook, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveFlexiTrack(addressBook, filePath);
    }


    @Override
    @Subscribe
    public void handleAddressBookChangedEvent(FlexiTrackChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveFlexiTrack(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
