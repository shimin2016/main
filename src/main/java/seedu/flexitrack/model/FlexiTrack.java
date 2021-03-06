package seedu.flexitrack.model;

import javafx.collections.ObservableList;
import seedu.flexitrack.commons.exceptions.IllegalValueException;
import seedu.flexitrack.model.tag.Tag;
import seedu.flexitrack.model.tag.UniqueTagList;
import seedu.flexitrack.model.task.ReadOnlyTask;
import seedu.flexitrack.model.task.Task;
import seedu.flexitrack.model.task.UniqueTaskList;
import seedu.flexitrack.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.flexitrack.model.task.UniqueTaskList.IllegalEditException;
import seedu.flexitrack.model.task.UniqueTaskList.TaskNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps all data at the task-tracker level
 * Duplicates are not allowed (by .equals comparison)
 */
public class FlexiTrack implements ReadOnlyFlexiTrack {

    private final UniqueTaskList task;
    private final UniqueTagList tags;

    {
        task = new UniqueTaskList();
        tags = new UniqueTagList();
    }

    public FlexiTrack() {}

    /**
     * Tasks are copied into this taskstracker
     */
    public FlexiTrack(ReadOnlyFlexiTrack toBeCopied) {
        this(toBeCopied.getUniqueTaskList(), toBeCopied.getUniqueTagList());
    }

    /**
     * Tasks are copied into this taskstracker
     */
    public FlexiTrack(UniqueTaskList tasks, UniqueTagList tags) {
        resetData(tasks.getInternalList(), tags.getInternalList());
    }

    public static ReadOnlyFlexiTrack getEmptyFlexiTrack() {
        return new FlexiTrack();
    }

//// list overwrite operations

    public ObservableList<Task> getTasks() {
        return task.getInternalList();
    }

    public void setTasks(List<Task> tasks) {
        this.task.getInternalList().setAll(tasks);
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.getInternalList().setAll(tags);
    }

    public void resetData(Collection<? extends ReadOnlyTask> newTasks, Collection<Tag> newTags) {
        setTasks(newTasks.stream().map(Task::new).collect(Collectors.toList()));
        setTags(newTags);
    }

    public void resetData(ReadOnlyFlexiTrack newData) {
        resetData(newData.getTaskList(), newData.getTagList());
    }

//// task-level operations

    /**
     * Adds a task to the tasks tracker.
     * Also checks the new task's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the task to point to those in {@link #tags}.
     *
     * @throws UniqueTaskList.DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(Task p) throws DuplicateTaskException {
        syncTagsWithMasterList(p);
        task.add(p);
    }

    /**
     * Edits a Task in the tasks tracker.
     * @throws UniqueTaskList.DuplicateTaskException if an equivalent task already exists.
     * @throws TaskNotFoundException if specified task is not found.
     */
    public String editTask(int taskToEdit, String[] args) throws TaskNotFoundException, IllegalEditException, IllegalValueException{
        return task.edit(taskToEdit, args);
    }
    
    /**
     * Ensures that every tag in this task:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Task task) {
        final UniqueTagList taskTags = task.getTags();
        tags.mergeFrom(taskTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : tags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of task tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : taskTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        task.setTags(new UniqueTagList(commonTagReferences));
    }

    public boolean removeTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException {
        if (task.remove(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }

//// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

//// util methods

    @Override
    public String toString() {
        return task.getInternalList().size() + " tasks, " + tags.getInternalList().size() +  " tags";
        // TODO: refine later
    }

    @Override
    public List<ReadOnlyTask> getTaskList() {
        return Collections.unmodifiableList(task.getInternalList());
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags.getInternalList());
    }

    @Override
    public UniqueTaskList getUniqueTaskList() {
        return this.task;
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        return this.tags;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlexiTrack // instanceof handles nulls
                && this.task.equals(((FlexiTrack) other).task)
                && this.tags.equals(((FlexiTrack) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(task, tags);
    }

    public void markTask(int targetIndex) {
        task.mark(targetIndex,Boolean.TRUE);
    }
    
    public void unmarkTask(int targetIndex) {
        task.mark(targetIndex, Boolean.FALSE);
    }
}
